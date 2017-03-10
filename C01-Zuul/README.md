# Spring Cloud - Zuul

## 疑问收集

### 1. Zuul最小配置运行?

### 2. Zuul文件上传?

### 3. Zuul配置参数作用和规则?

routes.users.path

路由必须配置一个可以被指定为ant风格表达式的"path", 所以“/user/*”只能匹配一个层级, 但"/user/**"可以匹配多级.

zuul.sensitiveHeaders

在同一个系统中服务间共享请求头是可行的, 但是你可能不想敏感的头信息泄露到内部系统的下游。 你可以在路由配置中指定一批忽略的请求头列表。 Cookies扮演了一个特殊的角色, 因为他们很好的被定义在浏览器中, 而且他们总是被认为是敏感的. 如果代理的客户端是浏览器, 则对于下游服务来说对用户, cookies会引起问题, 因为他们都混在一起。(所有下游服务看起来认为他们来自同一个地方)。

你得小心你的服务设计, 比如即使只有一个下游服务设置cookies, 你都必须让他们回溯设置所有的调用路线. 当然, 如果你的代理设置cookies和你所有后端服务是同一个系统的一部分, 它可以自然的被简单分享(例如, 使用spring session去将它们联系在一起共享状态). 除此之外, 任何被下游设置的cookies可能不是很有用, 推荐你对于不属于你域名部分的路由添加(至少)"Set-Cookie"和"Cookie" 到敏感头. 即使是属于你的域名的路由, 尝试仔细思考在允许cookies流传在它们和代理之间的意义。

```
zuul:
  routes:
    users:
      path: /myusers/**
      sensitiveHeaders: Cookie,Set-Cookie,Authorization
      url: https://downstream
```

## 重点摘要

### 客户端注册