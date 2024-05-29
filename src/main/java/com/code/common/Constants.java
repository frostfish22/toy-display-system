package com.code.common;

// 系统常量
public interface Constants {


    //  secretKey jwt秘钥此秘钥一定要保留好在服务端, 不能暴露出去, 否则sign就可以被伪造, 如果对接多个客户端建议改造成多个
    String secretKey = "123456";

    // token名称
    String TOKEN = "token";

    String USER_DEFAULT_PASSWORD = "123";

}
