package com.groom.sumbisori.common.springdoc;

import com.groom.sumbisori.common.error.ErrorCode;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiExceptionExplanation {
    Class<? extends ErrorCode> value();

    /**
     * BaseErrorCode를 구현한 Enum 클래스의 상수명
     */
    String constant();

    String name() default "";

    String description() default "";
}
