package com.fellow.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by wubiao on 2016/8/31.
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface NeedLogin {
}
