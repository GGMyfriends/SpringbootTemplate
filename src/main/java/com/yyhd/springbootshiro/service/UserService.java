package com.yyhd.springbootshiro.service;

/**
 * @Author nitric oxide
 * @Description
 * @Date 6:11 下午 2021/6/23
 */
public interface UserService {

    /**
     * 根据手机号和密码登录
     * @param phone
     * @param password
     * @return
     */
    String passwordLogin(String phone, String password);

}
