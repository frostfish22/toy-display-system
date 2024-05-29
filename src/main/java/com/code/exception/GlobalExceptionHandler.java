package com.code.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.code.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages="com.code.controller")
public class GlobalExceptionHandler {

    private static final Log log = LogFactory.get();


    //统一异常处理@ExceptionHandler,主要用于Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回json
    public Result error(HttpServletRequest request, Exception e){
        log.error("异常信息：",e);
        return Result.error("系统错误");
    }

    @ExceptionHandler(SystemException.class)
    @ResponseBody//返回json
    public Result customError(HttpServletRequest request, SystemException e){
        return Result.error(e.getCode(), e.getMsg());
    }
}
