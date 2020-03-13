package com.joewang.repast.service;

import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.LoginLogMapper;
import com.joewang.repast.model.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-11
 */
@Service
public class LoginLogService extends BaseService<LoginLog> {

    @Autowired
    private LoginLogMapper loginLogMapper;

    public Mapper<LoginLog> getMapper() {
        return loginLogMapper;
    }

    /**
     * @desc: 保存登录操作日志
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [loginLog]
     * @return: java.lang.Boolean
     */
    public Boolean addLoginLog(LoginLog loginLog){
        try {
            Integer addResult = super.save(loginLog);
            if (addResult > 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
