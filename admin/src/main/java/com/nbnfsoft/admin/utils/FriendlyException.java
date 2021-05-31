package com.nbnfsoft.admin.utils;

public class FriendlyException extends RuntimeException {

    public FriendlyException(String msg) {
        this.msg = msg;
    }
    public FriendlyException(String message, Integer code)
    {
        this.msg = message;
        this.code = code;
    }
    private String msg;
    private Integer code;
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage()
    {
        return msg;
    }
}
