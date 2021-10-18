/**
 * @Company SS.Ed
 * @Author Zero
 * @Date Create in 2020/3/13 16:16
 * @Description 商品收货地址，判断只涉及单表的增删改查
 **/
package com.joewang.repast.service;


import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.ReceiveAddressMapper;
import com.joewang.repast.model.ReceiveAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public class ReceiveAddressService extends BaseService<ReceiveAddress> {

    @Autowired
    private ReceiveAddressMapper receiveAddressMapper;

    @Override
    public Mapper getMapper() {
        return receiveAddressMapper;
    }

    //  TOOD  IP地址定位,范围限制 外卖系统
    /*
     * @author Zero
     * @description 新增联系地址
     * @param  [receiveAddress]
     * @date 2020/3/13 19:30
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean insertReceiveAddress(ReceiveAddress receiveAddress){
        try {
            Integer result = super.save(receiveAddress);
            if (result>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
     * @author Zero
     * @description 修改联系地址  根据id进行修改
     * @param  [receiveAddress]
     * @date 2020/3/13 19:36
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean updateReceiveAddress(ReceiveAddress receiveAddress){
        try {
            Integer result= super.update(receiveAddress);
            if (result>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
     * @author Zero
     * @description 删除联系地址  根据id进行删除
     * @param  [receiveAddress]
     * @date 2020/3/13 19:38
     * @return java.lang.Boolean
     * @throws
     **/
    public Boolean deleteReceiveAddress(long id){
        try {
            Integer result = super.deleteByPrimaryKey(id);
            if (result>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
     * @author Zero
     * @description 查询用户所有的联系地址 根据member_id进行查询
     * @param  [receiveAddress]
     * @date 2020/3/13 19:51
     * @return java.util.List<com.aaa.lee.repast.model.ReceiveAddress>
     * @throws
     **/
   public List<ReceiveAddress> selectAllReceiveAddress(long id){
       List<ReceiveAddress> list=null;
       try {
           ReceiveAddress receiveAddress =new ReceiveAddress();
           receiveAddress.setMemberId(id);
           list = super.selectDomain(receiveAddress);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return list;
   }

   /*
    * @author Zero
    * @description 根据主键 id查询指定的某条收货地址
    * @param  [id]
    * @date 2020/3/15 17:46
    * @return com.joewang.repast.model.ReceiveAddress
    * @throws
    **/
   public ReceiveAddress selectByKeyReceiveAddress(long id){
       ReceiveAddress receiveAddress =null;
       try {
            receiveAddress = super.selectByPrimary(id);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return receiveAddress;
   }
}