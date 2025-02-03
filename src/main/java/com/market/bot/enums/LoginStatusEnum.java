package com.market.bot.enums;

public enum LoginStatusEnum {
    SUCCESS(1, "登录成功"),
    FAILURE(2, "登录失败"),
    ACCOUNT_LOCKED(3, "账号被锁定"),
    PASSWORD_EXPIRED(4, "密码过期"),
    USER_NOT_FOUND(5, "用户不存在"),
    INVALID_CREDENTIALS(6, "无效的凭据");

    private final int code;         // 数值对应的枚举值
    private final String message;  // 状态信息

    // 构造器
    LoginStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // 获取数值
    public int getCode() {
        return code;
    }

    // 获取状态信息
    public String getMessage() {
        return message;
    }

    // 根据 code 获取对应的枚举值
    public static LoginStatusEnum fromCode(int code) {
        for (LoginStatusEnum status : LoginStatusEnum.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("无效的 code 值: " + code);
    }

    @Override
    public String toString() {
        return code + ": " + message;
    }
}

