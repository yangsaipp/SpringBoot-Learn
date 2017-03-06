# Spring Cloud - Eureka Server

## 疑问收集

### 1. 服务端与客户端怎么配置后能互相访问?

#### 1.1 使用原生的Netflix的EurekaClient

#### 1.2 原生的Spring Cloud的DiscoveryClient

### 2. ServerId的作用?

### 3. Zuul如何与Eureka集成？

## 重点摘要

### 客户端注册

> When a client registers with Eureka, it provides meta-data about itself such as host and port, health indicator URL, home page etc. Eureka receives heartbeat messages from each instance belonging to a service. If the heartbeat fails over a configurable timetable, the instance is normally removed from the registry.
>
> 当一个客户端注册到Eureka,它提供关于自己的元数据（诸如主机和端口，健康指标URL，首页等）Eureka通过一个服务从各个实例接收心跳信息。如果心跳接收失败超过配置的时间，实例将会正常从注册里面移除。


> @EnableEurekaClient makes the app into both a Eureka "instance" (i.e. it registers itself) and a "client" (i.e. it can query the registry to locate other services). The instance behaviour is driven by eureka.instance.* configuration keys, but the defaults will be fine if you ensure that your application has a spring.application.name (this is the default for the Eureka service ID, or VIP).
> 
> @EnableEurekaClient 使Eureka做为一个实例（注册直接）和客户端（它能通过查找注册来定位其它服务）注册到应用里面。实例的行为由eureka.instance.*的配置项来决定,但是你最好确保你的spring.application.name有个默认值。（这是Eureka的默认ID或VIP）。
