package com.roside.system;

import java.io.Serializable;

/**
 * Created by Eric on 2017-02-17.
 */
public class JsonResponse<T> implements Serializable {

    private static final long serialVersionUID = 3580043264590808190L;

    public static final Integer OK = 200;

    public static final Integer REDIRECT = 302;

    public static final Integer ERR = 500;

    public static final JsonResponse NEED_LOGIN = JsonResponse.notOk(403, "用户未登录");

    public static final JsonResponse AUTH_FAIL = JsonResponse.notOk(401, "认证失败");

    public static final JsonResponse PARAM_MISSING = JsonResponse.notOk(400, "参数缺失");

    public static final JsonResponse SERVER_ERR = JsonResponse.notOk(ERR, "服务器异常");


    private Integer status;

    private Object err;

    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getErr() {
        return err;
    }

    public void setErr(Object err) {
        this.err = err;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static JsonResponse ok() {
        JsonResponse r = new JsonResponse();
        r.status = OK;
        return r;
    }

    public static JsonResponse ok(Object data) {
        JsonResponse r = new JsonResponse();
        r.status = OK;
        r.data = data;
        return r;
    }

    public static JsonResponse notOk(Object err){
        JsonResponse r = new JsonResponse();
        r.status = ERR;
        r.err = err;
        return r;
    }

    public static JsonResponse notOk(Integer status, Object err){
        JsonResponse r = new JsonResponse();
        r.status =status;
        r.err = err;
        return r;
    }

    public static JsonResponse redirect(String url){
        JsonResponse r = new JsonResponse();
        r.status = REDIRECT;
        r.data = url;
        return r;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "status=" + status +
                ", err=" + err +
                ", data=" + data +
                '}';
    }

}
