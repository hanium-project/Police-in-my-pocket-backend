package com.pocket.police.global.config.response;

import lombok.Getter;
import org.apache.catalina.connector.Response;

@Getter
public enum ErrorCode {

    // Success
    SUCCESS(true, Response.SC_OK, "요청에 성공하였습니다."),

    // 아이디 중복
    CONFLICT(false, 409, "중복된 아이디입니다.");


    private Boolean isSuccess;
    private int code;
    private String message;

    ErrorCode(Boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}