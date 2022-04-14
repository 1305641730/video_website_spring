package com.kurumi.domain;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    private String avatar;
    private String intro;
    private Integer sex;
    private String email;
    // token校验信息
    private String token;
    // 用户邮箱验证码
    private String code;
}
