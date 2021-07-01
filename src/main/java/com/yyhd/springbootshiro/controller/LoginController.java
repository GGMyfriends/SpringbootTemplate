package com.yyhd.springbootshiro.controller;

import com.yyhd.springbootshiro.entity.dto.BaseResponse;
import com.yyhd.springbootshiro.service.UserService;
import com.yyhd.springbootshiro.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping(value = "/login")
    public Object userLogin(@RequestParam(name = "phone", required = true) String phone,
                            @RequestParam(name = "password", required = true) String password, ServletResponse response) {
        BaseResponse<Object> ret = new BaseResponse<Object>();
        ret.setErrCode(0);
        ret.setMsg("登录成功");
        ret.setData(userService.passwordLogin(phone, password));
        return ret;
    }

    @GetMapping("/logout")
    public Object logout() {
        BaseResponse<Object> ret = new BaseResponse<Object>();
        ret.setErrCode(0);
        ret.setMsg("退出登录");
        return ret;
    }

}
