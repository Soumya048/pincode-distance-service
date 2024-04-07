package com.freightfox.pds.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Component
public class AsyncCallExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable exp, Method method, Object... params) {
        log.error("ERROR_IN_ASYNC_CALL_REQUEST");
        log.error("ASYNC_METHOD_NAME_ERROR: {}", method.getName());
        log.error("ASYNC_CALL_ERROR_MESSAGE: {}", exp.getMessage());
    }

}
