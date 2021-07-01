package com.yyhd.springbootshiro.shiro.token;

import com.yyhd.springbootshiro.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    //加密后的 JWT token
    private String token;

    private String username;

    public JwtToken(String token){
        this.token = token;
        this.username = JwtUtils.getClaimFiled(token,"username");
    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
