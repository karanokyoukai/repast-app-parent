package com.joewang.repast.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageInfos<T> implements Serializable {
    //当前页码数
    private Integer pageNum;
    //每页显示条数
    private Integer pageSize;
    //分页数据
    private T t;
}
