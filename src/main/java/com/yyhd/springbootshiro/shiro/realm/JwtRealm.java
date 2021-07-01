package com.yyhd.springbootshiro.shiro.realm;

import com.yyhd.springbootshiro.dao.AuthUserDao;
import com.yyhd.springbootshiro.entity.UserEntity;
import com.yyhd.springbootshiro.shiro.token.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @Author nitric oxide
 * @Description 
 * @Date 2:41 下午 2021/6/24
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private AuthUserDao authUserDao;

    /**
     * 限定这个 Realm 只处理我们自定义的 JwtToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        if (jwtToken.getPrincipal() == null) {
            throw new AccountException("JWT token参数异常！");
        }
        // 从 JwtToken 中获取当前用户
        String userId = jwtToken.getPrincipal().toString();
        // 查询数据库获取用户信息，此处使用 Map 来模拟数据库
        UserEntity user = authUserDao.selectUserById(Integer.valueOf(userId));

        // 用户不存在
        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        }

        // 用户被锁定
        if (user.getLocked() == 0) {
            throw new LockedAccountException("该用户已被锁定,暂时无法登录！");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPhone(), getName());
        return info;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserEntity currentUser = (UserEntity) principals.getPrimaryPrincipal();
        Integer userId = currentUser.getId();
        //根据id获取权限和角色
        HashMap<String, Object> roleAndPermissions = authUserDao.selectRoleAndPermissionByUserId(userId);
        //获取所有的权限
        List<String> permissionsNameList = Arrays.asList(String.valueOf(roleAndPermissions.get("permissionsNameList")).split(","));
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 添加角色
        authorizationInfo.addRole(String.valueOf(roleAndPermissions.get("roleName")));
        //添加权限
        authorizationInfo.addStringPermissions(permissionsNameList);
        return authorizationInfo;
    }
}
