package com.yyhd.springbootshiro.service.impl;

import com.yyhd.springbootshiro.dao.AuthUserDao;
import com.yyhd.springbootshiro.entity.UserEntity;
import com.yyhd.springbootshiro.service.UserService;
import com.yyhd.springbootshiro.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * @Author nitric oxide
 * @Description 
 * @Date 6:46 下午 2021/6/23
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthUserDao authUserDao;

    @Override
    public String passwordLogin(String phone, String password) {
        // 获取当前用户主体
        Subject subject = SecurityUtils.getSubject();

        UserEntity userEntity = authUserDao.selectUserByPhone(phone);
        if (userEntity == null) {
            throw new UnknownAccountException("用户不存在！");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
        subject.login(token);
        return JwtUtils.sign(userEntity.getId().toString(), JwtUtils.SECRET);
    }
}
