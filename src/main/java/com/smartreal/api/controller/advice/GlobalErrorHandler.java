package com.smartreal.api.controller.advice;

import com.smartreal.api.enums.ErrorType;
import com.smartreal.api.exception.SmartRealRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalErrorHandler {
    protected static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorHandler.class);

    @ExceptionHandler({SmartRealRuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SmartRealRuntimeException error(HttpServletRequest request, SmartRealRuntimeException exception) {
        this.printLog(request, exception);

        return new SmartRealRuntimeException(ErrorType.ofCode(exception.getResultCode()), exception.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SmartRealRuntimeException runtimeException(HttpServletRequest request, RuntimeException exception) {
        this.printLog(request, exception);

        return new SmartRealRuntimeException(ErrorType.INTERNAL_ERROR, exception.getMessage());
    }

    private void printLog(HttpServletRequest request, Exception e) {
        LOGGER.error("Exception Error {} \t: {} at URI -> {}", e.getClass(), e.getMessage(), request.getRequestURI(), e);
    }
}
