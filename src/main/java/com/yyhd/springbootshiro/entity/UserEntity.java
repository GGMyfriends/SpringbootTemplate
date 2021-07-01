package com.yyhd.springbootshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author nitric oxide
 * @Description
 * @Date 6:15 下午 2021/6/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nickName;

    private String password;

    private String trueName;

    private String phone;

    private Integer locked;

}
