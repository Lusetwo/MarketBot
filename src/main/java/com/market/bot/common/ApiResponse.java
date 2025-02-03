package com.market.bot.common;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code; //状态码

    private String message; //消息

    private T data; //数据

    private long timestamp; //请求的时间戳

    private long elapsedTime; //秦秋的处理时间(毫秒)

    //无参构造函数
    public ApiResponse() {}

    //构造函数
    public ApiResponse(int code, String message, long timestamp, long elapsedTime) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
        this.elapsedTime = elapsedTime;
    }

    //成功返回的数据
    public static <T> ApiResponse<T> success(T data,long elapsedTime) {
        return new ApiResponse<>(200, "success", System.currentTimeMillis(), elapsedTime);
    }

    //成功但不返回数据
    public static ApiResponse<Void> success(long elapsedTime) {
        return new ApiResponse<>(200, "success", System.currentTimeMillis(), elapsedTime);
    }

    //错误返回
    public static ApiResponse<Void> error(int code,String message,long elapsedTime) {
        return new ApiResponse<>(code,message,System.currentTimeMillis(),elapsedTime);
    }

    // 错误返回（没有数据）
    public static ApiResponse<Void> error(String message, long elapsedTime) {
        return new ApiResponse<>(500, message, System.currentTimeMillis(), elapsedTime);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                ", elapsedTime=" + elapsedTime +
                '}';
    }
}
