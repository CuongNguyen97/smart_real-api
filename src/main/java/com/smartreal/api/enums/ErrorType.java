package com.smartreal.api.enums;

import java.util.Arrays;

public enum ErrorType {
    BAD_REQUEST(400, "Bad Request! Check request format."),
    INTERNAL_ERROR(1001, "Error occurs"),
    UNAUTHORIZED(401, "Unauthorized! Check access");

    private final int code;
    private final String message;

    ErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorType ofCode(int code) {
        return Arrays.stream(values()).filter(value -> value.code == code).findAny().orElse(ErrorType.INTERNAL_ERROR);
    }
}
