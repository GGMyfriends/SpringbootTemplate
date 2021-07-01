package com.yyhd.springbootshiro.exception;

import com.yyhd.springbootshiro.entity.dto.BaseResponse;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobleExceptionHandler {

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public Object handleShiroException(ShiroException e) {
        BaseResponse<Object> ret = new BaseResponse<Object>();
        ret.setErrCode(401);
        ret.setMsg(e.getMessage());
        return ret;
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public Object globalException(HttpServletRequest request, Throwable ex) {
        BaseResponse<Object> ret = new BaseResponse<Object>();
        ret.setErrCode(401);
        ret.setMsg(ex.getMessage());
        return ret;
    }

}
