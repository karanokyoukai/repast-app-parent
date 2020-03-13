package com.joewang.repast.mapper;

import com.joewang.repast.model.LoginLog;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface LoginLogMapper extends Mapper<LoginLog> {
}