package com.nbnfsoft.admin.utils;

public class AuthException extends RuntimeException {
    public AuthException(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
