package com.example.two;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不依赖spring的@Component注解，扫描使用该注解的类并加入到spring容器中有以下方式：
 * 
 * 方法一：见 com.example.two.ScanConfig
 * 使用@ComponentScan扫描自定义注解，如MyComponent2，那么类上有MyComponent2注解的类将会加入打Spring容器中
 * 推荐该方法，原因：实现简单，不会重复扫描类
 * 
 * 方法二：见 com.example.two.scan.MyScan
 * 使用自己的Scanner来扫描指定包路径下的类，并将符合条件（包含有注解MyComponent2）的类定义加入容器中
 * 
 * 
 * @author yangsai
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyComponent2 {
    String value() default "";
}