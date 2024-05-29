package com.code.common;

import lombok.*;

/**
 * 接口统一返回类
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {



    private String code;
    /**
     * msg表示错误的详细信息
     */
    private String message;

    private Object data;


    public static Result success() {
        return new  Result(ResultCodeEnum.SUCCESS.code,ResultCodeEnum.SUCCESS.msg,null);
    }

    public static Result success(Object data) {
        return new Result(ResultCodeEnum.SUCCESS.code, ResultCodeEnum.SUCCESS.msg, data);
    }

    public static Result error(String message) {
        return new Result(ResultCodeEnum.SYSTEM_ERROR.code, message, null);
    }

    public static Result error(String code, String message) {
        return new Result(code, message, null);
    }

    public static Result error() {
        return new Result(ResultCodeEnum.SYSTEM_ERROR.code, ResultCodeEnum.SYSTEM_ERROR.msg,null);
    }

}
