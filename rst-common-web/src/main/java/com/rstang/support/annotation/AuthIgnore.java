package com.rstang.support.annotation;

import java.lang.annotation.*;

/**
 * 忽略认证、授权
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface AuthIgnore {

}
