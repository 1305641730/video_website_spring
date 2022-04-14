package com.kurumi.controller;

import com.kurumi.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class ResObj {

    // 是否请求成功
    private boolean flag;
    // 返回给前端的提示信息
    private String msg;
    // 返回给前端的数据
    private Object data;

    public ResObj() {
    }

    public ResObj(boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public ResObj(boolean flag, String msg, Object data) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }
}
