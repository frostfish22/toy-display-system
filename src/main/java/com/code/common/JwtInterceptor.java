package com.code.common;


import com.code.exception.SystemException;
import com.code.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt拦截器
 */
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {


    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        //HandlerMethod是Spring MVC框架中的一个类，用于封装Controller方法的信息
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(Constants.TOKEN);

        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtils.parseJWT( token);
            int userId = Integer.valueOf(claims.get("userId").toString());
            BaseContext.setCurrentId(userId);
            log.info("当前员工id：{}", userId);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过 抛出异常
            throw new SystemException(ResultCodeEnum.TOKEN_CHECK_ERROR);

        }
    }

//    在请求处理完成后，无论是否出现异常都会被调用。afterCompletion() 方法通常用于进行一些清理工作或资源释放操作。
//    比如threadlocal的释放
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContext.removeCurrentId();
    }
}
