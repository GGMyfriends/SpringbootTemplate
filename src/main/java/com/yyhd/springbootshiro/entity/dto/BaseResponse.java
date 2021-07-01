package com.yyhd.springbootshiro.entity.dto;

import lombok.Data;

/**
 * @Author ldy
 * @Description 返回
 * @Date 7:04 下午 2021/6/23
 */
@Data
public class BaseResponse<T> {

    private Integer errCode;
    private String msg;
    private T data;

}
