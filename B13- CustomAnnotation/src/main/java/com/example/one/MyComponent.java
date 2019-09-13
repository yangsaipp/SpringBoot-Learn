package com.example.one;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 依赖spring的@Component注解，注册中心实现方式有三种
 * 1. 见：com.example.one.Register
 * 
 * 2. 见：com.example.one.Register2
 * 
 * 3. 见：com.example.one.Register3
 * 
 * 推荐2或者3，原因1与构造方法绑定，不够灵活
 * 
 * @author yangsai
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyComponent {
    String value() default "";
}