package com.nbnfsoft.admin.domain.model;

import java.io.Serializable;

public class JsonData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JsonData(Boolean success, T data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }

    private Boolean success;
    private T data;
    private String msg;
}
