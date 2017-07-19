# Spring Cloud - Eureka Client

# 备注
1. 使用@EnableEurekaClient注解后当前应用会同时变成一个Eureka服务端实例(它会注册自身)和Eureka客户端(可以查询当前服务列表)。


## 疑问收集
1. 客户端注册到服务端时默认使用地址是电脑主机名，如 275Y43X.comtop.local，这会导致服务调用失败，可以改成注册ip。

```
eureka.instance.preferIpAddress=true
eureka.instance.instance-id=${spring.cloud.client.ipAddress}:${server.port}

eureka:
  instance
    preferIpAddress: true
    instance-id: ${spring.cloud.client.ipAddress}:${server.port}
```