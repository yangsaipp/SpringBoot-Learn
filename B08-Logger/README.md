# SpringBoot - logger

## 疑问收集

1. 如何在不同环境使用不同logger配置，如开发环境、演示环境、生产环境

使用logback-spring.xml配置文件，通过springProfile元素为不同环境配置好logger配置，启动时通过增加--spring.profiles.active=dev的参数来指定激活那个环境下的配置。

2. 如何不重启应用修改指定类日志级别？

引入spring-boot-starter-actuator，通过Rest接口可以管理类的logger级别（查看，修改）。

## 重点摘要

[springboot日志配置]http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#boot-features-logging

[logback配置文档](https://logback.qos.ch/manual/configuration.html#autoScan)

## 