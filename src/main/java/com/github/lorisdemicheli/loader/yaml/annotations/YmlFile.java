package com.github.lorisdemicheli.loader.yaml.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface YmlFile {
	boolean useDefaultPath() default true;
	String path() default "";
	String fileName() default "";
	boolean multiple() default false;
}
