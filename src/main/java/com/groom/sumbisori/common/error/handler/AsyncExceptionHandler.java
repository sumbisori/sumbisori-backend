package com.groom.sumbisori.common.error.handler;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.error("비동기 메서드 실행 중 예외 발생 - 메서드: {}, 예외 메시지: {}", method.getName(), ex.getMessage());
    }
}
