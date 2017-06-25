package com.fellow.domain.web;

import com.fellow.domain.enums.HttpStatusEnum;

import java.io.Serializable;

/**
 * Created by wubiao on 2016/8/17.
 */
public class Response<T, O> implements Serializable {
    private static final long serialVersionUID = 2306650097977860228L;
    /**
     * 成功失败标识 默认失败
     */
    private boolean success = false;
    /**
     * 相应状态码 默认失败
     **/
    private int status = HttpStatusEnum.FAILED.getStatus();
    /**
     * 消息提示
     */
    private String message;
    /**
     * 相应消息体泛型
     */
    private T body;
    /**
     * 其它信息
     */
    private O object;

    public Response() {
    }

    public Response(String message) {
        this.message = message;
    }

    public Response(boolean success, int status, String message) {
        this.success = success;
        this.status = status;
        this.message = message;
    }

    public Response(boolean success, int status, String message, T body, O object) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.body = body;
        this.object = object;
    }

    public Response(boolean success, int status) {
        this.success = success;
        this.status = status;
    }

    public static Response newResponse() {
        return new Response();
    }

    public static Response newResponse(String message) {
        return new Response(message);
    }

    public static Response newSuccessResponse() {
        return new Response(true, HttpStatusEnum.SUCCESS.getStatus());
    }

    public static Response newSuccessResponse(String message) {
        return new Response(true, HttpStatusEnum.SUCCESS.getStatus(), message);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public O getObject() {
        return object;
    }

    public void setObject(O object) {
        this.object = object;
    }


}
