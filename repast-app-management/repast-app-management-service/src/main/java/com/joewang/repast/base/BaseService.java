package com.joewang.repast.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joewang.repast.page.PageInfos;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @description:
 *      基础service类
 * @author: Joe Wang
 * @date: 2020-03-11
 */
public abstract class BaseService<T> {
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
}
