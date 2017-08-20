# SpringBoot - logger

## 疑问收集

1. 如何在不同环境使用不同logger配置，如开发环境、演示环境、生产环境

使用logback-spring.xml配置文件，通过springProfile元素为不同环境配置好logger配置，启动时通过增加--spring.profiles.active=dev的参数来指定激活那个环境下的配置。

2. 如何不重启应用修改指定类日志级别？

spring-boot-starter-actuator提供了在运行时管理logger的endpoint。

```
// 查看所有的logger配置
curl -i http://127.0.0.1:8080/loggers

// 查看指定类的logger配置，输出{"configuredLevel":"DEBUG","effectiveLevel":"DEBUG"}
curl -i http://127.0.0.1:8080/loggers/com.example.LogController

// 修改指定类的logger级别
curl -X POST -H "Content-type:application/json;charset=UTF-8" -d '{"configuredLevel":"INFO"}' http://127.0.0.1:8080/loggers/com.example.LogController
```

logger配置说明：
1. configuredLevel，当前类配置的logger级别。
2. effectiveLevel，当前类生效的logger级别。

当前类生效级别等于当前类的配置级别，若无配置级别则以最近的父类的配置级别为准。

[loggers](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-loggers)

## 重点摘要

[springboot日志配置](http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#boot-features-logging)

[logback配置文档](https://logback.qos.ch/manual/configuration.html#autoScan)

## 