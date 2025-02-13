package com.market.bot.common.enums;


import lombok.Getter;

/**
 * 详细的 HTTP 状态枚举类
 */
@Getter
public enum HttpStatusEnum {

    // 1xx: Informational (信息性)
    CONTINUE(100, "Continue", "服务器已收到请求的初始部分，客户端应继续发送其余部分"),
    SWITCHING_PROTOCOLS(101, "Switching Protocols", "服务器正在切换协议"),
    PROCESSING(102, "Processing", "服务器已收到请求，但仍在处理"),

    // 2xx: Success (成功)
    OK(200, "OK", "请求成功"),
    CREATED(201, "Created", "请求成功并创建了新资源"),
    ACCEPTED(202, "Accepted", "请求已接受，但尚未处理完成"),
    NO_CONTENT(204, "No Content", "请求成功，但没有返回内容"),

    // 3xx: Redirection (重定向)
    MOVED_PERMANENTLY(301, "Moved Permanently", "资源已被永久移动"),
    FOUND(302, "Found", "资源已找到，但位置临时变更"),
    NOT_MODIFIED(304, "Not Modified", "资源未被修改，可以使用缓存"),

    // 4xx: Client Error (客户端错误)
    BAD_REQUEST(400, "Bad Request", "请求格式错误或参数无效"),
    UNAUTHORIZED(401, "Unauthorized", "需要身份验证"),
    FORBIDDEN(403, "Forbidden", "服务器拒绝请求"),
    NOT_FOUND(404, "Not Found", "请求的资源不存在"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed", "请求方法不被允许"),
    CONFLICT(409, "Conflict", "请求冲突，例如重复提交"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type", "不支持的媒体类型"),

    // 5xx: Server Error (服务器错误)
    INTERNAL_SERVER_ERROR(500, "Internal Server Error", "服务器内部错误"),
    NOT_IMPLEMENTED(501, "Not Implemented", "服务器不支持请求的功能"),
    BAD_GATEWAY(502, "Bad Gateway", "网关或代理服务器收到无效响应"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable", "服务器暂时不可用"),
    GATEWAY_TIMEOUT(504, "Gateway Timeout", "网关超时");

    private final int code;
    private final String reasonPhrase;
    private final String description;

    HttpStatusEnum(int code, String reasonPhrase, String description) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
        this.description = description;
    }

    /**
     * 根据状态码获取枚举
     * @param code HTTP 状态码
     * @return 对应的 HttpStatusEnum
     */
    public static HttpStatusEnum fromCode(int code) {
        for (HttpStatusEnum status : HttpStatusEnum.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("无效的 HTTP 状态码: " + code);
    }

    /**
     * 判断状态码是否是 2xx 成功类型
     */
    public boolean isSuccess() {
        return this.code >= 200 && this.code < 300;
    }

    /**
     * 判断状态码是否是 4xx 客户端错误
     */
    public boolean isClientError() {
        return this.code >= 400 && this.code < 500;
    }

    /**
     * 判断状态码是否是 5xx 服务器错误
     */
    public boolean isServerError() {
        return this.code >= 500 && this.code < 600;
    }

    @Override
    public String toString() {
        return this.code + " " + this.reasonPhrase + " - " + this.description;
    }
}

