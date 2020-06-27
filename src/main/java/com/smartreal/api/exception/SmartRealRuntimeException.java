package com.smartreal.api.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartreal.api.enums.ErrorType;

public class SmartRealRuntimeException extends RuntimeException {
    private int resultCode;
    private String resultMessage;

    public SmartRealRuntimeException(String message) {
        super(message);
        this.resultCode = ErrorType.INTERNAL_ERROR.getCode();
        this.resultMessage = message;
    }

    public SmartRealRuntimeException(ErrorType errorType) {
        super(errorType.getMessage());
        this.resultCode = errorType.getCode();
        this.resultMessage = errorType.getMessage();
    }

    public SmartRealRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.resultCode = ErrorType.INTERNAL_ERROR.getCode();
        this.resultMessage = message;
    }

    public SmartRealRuntimeException(ErrorType errorType, String message) {
        super(message);
        this.resultCode = errorType.getCode();
        this.resultMessage = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    @Override
    @JsonIgnore
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }

    @Override
    @JsonIgnore
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }
}
