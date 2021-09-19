package com.servletjsp.base.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation NController Marking point to implement Business Controller.
 * 
 * @author Admin
 *
 */

@Target(value = { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NController {

    public String action() default "";

    public String apply() default "";

}
