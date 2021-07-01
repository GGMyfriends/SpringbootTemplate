package com.yyhd.springbootshiro.dao;

import com.yyhd.springbootshiro.entity.UserEntity;

import java.util.HashMap;

/**
 * @Author nitric oxide
 * @Description 
 * @Date 6:55 下午 2021/6/23
 */
public interface AuthUserDao {

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    UserEntity selectUserById(Integer userId);

    /**
     * 根据手机号查询用户
     * @param phone
     * @return
     */
     UserEntity selectUserByPhone(String phone);

    /**
     * 根据用户id查询角色列表
     * @param userId
     * @return
     */
     HashMap<String, Object> selectRoleAndPermissionByUserId(Integer userId);

}
