# SpringBoot - logger

## 疑问收集

**1. 如何在不同环境使用不同logger配置，如开发环境、演示环境、生产环境**

使用logback-spring.xml配置文件，通过springProfile元素为不同环境配置好logger配置，启动时通过增加--spring.profiles.active=dev的参数来指定激活那个环境下的配置。

**2. 如何不重启应用修改指定类日志级别？**

spring-boot-starter-actuator提供了在运行时管理logger的endpoint。

**3. 如何在日志中增加traceId方便分析系统错误日志，特别是在分布式系统中**
https://blog.csdn.net/weixin_43723635/article/details/107201479
第一步：修改 pattern,在中间添加 %X{TRACE_ID},表示输出日志时 会从 MDC(ThreadLocal)中获取当前线程的TRACE_ID属性
第二步：编写过滤器，在用户访问时拦截用户请求,向MDC中存入 TRACE_ID

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