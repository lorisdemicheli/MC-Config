package com.github.lorisdemicheli.loader.yaml.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface YmlFile {
	/**
	 * Path for file name
	 * @return path
	 */
	String path() default "";
	/**
	 * File name/Table name 
	 * @return fileName
	 */
	String fileName() default "";
}
