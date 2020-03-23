package com.joewang.repast.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joewang.repast.mapper.MemberMapper;
import com.joewang.repast.model.Member;
import com.joewang.repast.page.PageInfos;
import com.joewang.repast.utils.Map2BeanUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @description:
 *      基础service类
 * @author: Joe Wang
 * @date: 2020-03-11
 */
public abstract class BaseService<T> {

    private Class<T> cache = null; // 定义一个缓存--->来存储泛型
    @Autowired
    private MemberMapper memberMapper;
    /**
     * @desc: 注入实体类来返回所对应的mapper类型
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: []
     * @return: tk.mybatis.mapper.common.Mapper<T>
     */
    public abstract Mapper<T> getMapper();

    /**
     * @desc: 新增保存
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [t]
     * @return: java.lang.Integer
     */
    public Integer save(T t) throws Exception{
        return getMapper().insert(t);
    }

    /**
     * @desc: 通过主键更新
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [t]
     * @return: java.lang.Integer
     */
    public Integer update(T t) throws Exception{
        return getMapper().updateByPrimaryKey(t);
    }

    /**
     * @author Seven Lee
     * @description
     *      获取泛型
     * @param []
     * @date 2020/3/12
     * @return java.lang.Class<T>
     * @throws
     **/
    public Class<T> getTypeArgument() {
        if(null == cache) {
            cache = (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0]);
        }
        return cache;
    }

    /**
     * @author Seven Lee
     * @description
     *      批量更新
     *      // TODO 后期维护
     * @params [t, ids, properties]
     * @date 2020/3/13
     * @return java.lang.Integer
     * @throws
     **/
    public Integer updateBatch(T t, List<Object> ids, Object properties) {
        Example example = Example.builder(getTypeArgument()).where(Sqls.custom().andIn("id", ids)).build();
        return getMapper().updateByExample(t, example);
    }

    /**
     * @desc: 通过主键进行删除
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [key]
     * @return: java.lang.Integer
     */
    public Integer deleteByPrimaryKey(Object key) throws Exception{
        return getMapper().deleteByPrimaryKey(key);
    }

    /**
     * @author Seven Lee
     * @description
     *      通过主键集合进行批量删除
     *          数据库的主键名必须叫id
     * @params [ids]
     * @date 2020/3/13
     * @return java.lang.Integer
     * @throws
     **/
    public Integer deleteBatch(Integer[] ids) {
        /**
         * List:是需要传入的参数
         *      <delete id="" paratemeterType=list
         *      where 主键 in (1,2,3,4,5,6,7,8...)
         */
        Example example = Example.builder(getTypeArgument()).where(Sqls.custom().andIn("id", Arrays.asList(ids))).build();
        return getMapper().deleteByExample(example);
    }

    /**
     * @desc: 通过实体属性进行删除
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [t]
     * @return: java.lang.Integer
     */
    public Integer delete(T t) throws Exception{
        return getMapper().delete(t);
    }

    /**
     * @desc: 通过主键查询
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [key]
     * @return: T
     */
    public T selectByPrimary(Object key) throws Exception{
        return getMapper().selectByPrimaryKey(key);
    }

    /**
     * @desc: 查询所有数据
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: []
     * @return: java.util.List<T>
     */
    public List<T> selectAll() throws Exception{
        return getMapper().selectAll();
    }

    /**
     * @desc: 通过实体属性查找一条
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [t]
     * @return: T
     */
    public T selectOne(T t) throws Exception{
        return getMapper().selectOne(t);
    }

    /**
     * @desc: 根据实体属性查询多条
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [t]
     * @return: java.util.List<T>
     */
    public List<T> selectDomain(T t) throws Exception{
        return getMapper().select(t);
    }

    /**
     * @author Seven Lee
     * @description
     *      查询的基础方法(通用)
     *      selct one
     *      select all
     *      select xx,xxx,xx
     *      select limit
     *      select order by
     *      pageInfos:
     *          pageNum:页码数
     *          pageSize:每一页显示的条数
     *          where:条件
     *          orderByField:排序字段
     *          filed:所需要查询的字段
     *
     *      参考Mybatis源码
     *
     *      <update id  parameterType>
     *          update set where 1 = 1
     *          <if test="username != ''">
     *              username = #{username},
     *          </if>
     *          <if test="password != ''">
     *              password = #{password}
     *          </if>
     *      </update>
     *
     * @params []
     * @date 2020/3/13
     * @return java.util.List<T>
     * @throws
     **/
    private List<T> queryByFieldBase(PageInfos pageInfos, Sqls where, String orderByField, String... fields) {
        Example.Builder builder = null;
        // 在判断形参可变长度数组的时候千万千万不要只判断是否为null--->有可能fields已经初始化过了，但是里面没有数据
        if(null == fields || fields.length == 0) {
            // 说明并没有传递字段，默认查询所有数据
            builder = Example.builder(getTypeArgument());
        } else {
            // 说明传递的有所需要查询的字段
            builder = Example.builder(getTypeArgument()).select(fields);
        }
        if(null != where) {
            builder = builder.where(where);
        }
        if(null != orderByField) {// 默认使用倒序
            builder = builder.orderByDesc(orderByField);
        }
        Example example = builder.build();
        // 实现通用分页
        /**
         * pageNum第一次进入的时候有可能为null
         *  为了实现通用，就不管是否是第一次点击进来
         */
        if(null != pageInfos.getPageNum() && null != pageInfos.getPageSize()) {
            PageHelper.startPage(pageInfos.getPageNum(), pageInfos.getPageSize()); // PageHelper的分页插件
        }
        return getMapper().selectByExample(example);
    }

    /**
     * @desc: 分页条件查询
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [t, pageInfo]
     * @return: java.util.List<T>
     */
    public List<T> selectPage(T t, PageInfos<T> pageInfos) throws Exception{
        return getMapper().selectByRowBounds(t, new RowBounds(pageInfos.getPageNum(),pageInfos.getPageSize()));
    }

    /**
     * @desc: 带条件的数量查询 传null则直接查询所有
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [t]
     * @return: java.lang.Integer
     */
    public Integer selectCount(T t) throws Exception{
        return getMapper().selectCount(t);
    }

    /**
     * @desc: 根据实体类属性进行分页查询
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [pageInfos]
     * @return: com.github.pagehelper.PageInfo<T>
     */
    public PageInfo<T> selectPageInfo(PageInfos<T> pageInfos) throws Exception{
        if (pageInfos.getPageNum() == null){
            pageInfos.setPageNum(0);
        }
        PageHelper.startPage(pageInfos.getPageNum(),pageInfos.getPageSize());
        List<T> list = this.selectDomain(pageInfos.getT());
        PageInfo<T> nowPageInfo = new PageInfo<T>(list);
        nowPageInfo.setTotal(this.selectCount(pageInfos.getT()));
        return nowPageInfo;
    }

    /**
     * @author Seven Lee
     * @description
     *      分页的封装
     * @params [pageInfos, where, orderByField, fields]
     * @date 2020/3/13
     * @return com.github.pagehelper.PageInfo<T>
     * @throws
     **/
    public PageInfo<T> queryListByPageAndField(PageInfos pageInfos, Sqls where, String orderByField,
                                               String... fields) {
        if(null == pageInfos.getPageNum()) {
            // 说明就是第一次从菜单点击，并不是直接开始了分页
            pageInfos.setPageNum(0);
        }
        return new PageInfo<T>(queryByFieldBase(pageInfos, where, orderByField, fields));
    }

    public T newInstance(Map map) {
        // 自定义高性能反射工具类(有些是有泛型的(List,Map,Set,BaseService<T>....))
        return (T) Map2BeanUtil.map2Bean(map, getTypeArgument());
    }
    /**
     * 验证token封装
     * @param token
     * @return
     */
    public Member checkToken(String token){
        //传进来的token不为空
        if (null != token){
            Member member = memberMapper.selectMemberByToken(token);
            String memberToken = member.getToken();
            //通过token去查询数据库，看数据库是否有该信息
            if (null != memberToken) return member;
        }
        return null;
    }
}
