package com.yyhd.springbootshiro.shiro.realm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.yyhd.springbootshiro.dao.AuthUserDao;
import com.yyhd.springbootshiro.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
* 同时开启身份验证和权限验证，需要继承 AuthorizingRealm
* 并实现其  doGetAuthenticationInfo()和 doGetAuthorizationInfo 两个方法
*/
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private AuthUserDao authUserDao;

    public static Map<String, UserEntity> userMap = new HashMap<String, UserEntity>(16);
    public static Map<String, Set<String>> roleMap = new HashMap<String, Set<String>>(16);
    public static Map<String, Set<String>> permMap = new HashMap<String, Set<String>>(16);

    static {
        UserEntity user1 = new UserEntity(1, "admin", "bf84340d7dbb52a329080ddac4cac003a6804dd8", "灰先生", "13333333333", 1);

        userMap.put("admin", user1);

        roleMap.put("admin", new HashSet<String>() {
            {
                add("admin");

            }
        });

        roleMap.put("plum", new HashSet<String>() {
            {
                add("guest");
            }
        });
        permMap.put("plum", new HashSet<String>() {
            {
                add("article:read");
            }
        });
    }

    public ShiroRealm() {
    }

    /**
     * 限定这个 Realm 只处理 UsernamePasswordToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 查询数据库，将获取到的用户安全数据封装返回
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 从 AuthenticationToken 中获取当前用户
        String phone = (String) token.getPrincipal();
        // 查询数据库获取用户信息，此处使用 Map 来模拟数据库
        UserEntity user = authUserDao.selectUserByPhone(phone);

        // 用户被锁定
        if (user.getLocked() == 0) {
            throw new LockedAccountException("该用户已被锁定,暂时无法登录！");
        }

        // 使用用户名作为盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(phone);

        /**
         * 将获取到的用户数据封装成 AuthenticationInfo 对象返回，此处封装为 SimpleAuthenticationInfo 对象。
         *  参数1. 认证的实体信息，可以是从数据库中获取到的用户实体类对象或者用户名
         *  参数2. 查询获取到的登录密码
         *  参数3. 盐值
         *  参数4. 当前 Realm 对象的名称，直接调用父类的 getName() 方法即可
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
        return info;
    }

    /**
     * 查询数据库，将获取到的用户的角色及权限信息返回
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
