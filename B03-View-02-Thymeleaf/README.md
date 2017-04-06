# SpringBoot - 标题

## 疑问收集

1. 如何实现代码片段引入，类似于jsp的include？

Thymeleaf有多种方式引入

- th:insert 3.0+版本新加的
- th:include 这个在3.0版本已经不建议使用
- th:replace 2.0+ 3.0+都可用

```
// 这两种方式div#test都会保留，且引入的代码会被包含在div里
// div里的内容不会保留
<div id="test" th:include="Header :: header"></div>
<div id="test" th:insert="Header :: header"></div>

// div#test不会保留
// div里的内容不会保留
<div id="test" th:replace="Header :: header"></div>

```

若是只要达到JSP中include的效果，使用replace即可。

## 重点摘要

**1. 强烈推荐使用Thymeleaf的最新版本3.x**

SpringBoot1.5.1默认使用Thymeleaf2.1.5，建议升级到Thymeleaf3,升级只需修改build.gradle引入相关依赖即可。

```
	compile("org.thymeleaf:thymeleaf:3.0.5.RELEASE")
	compile("org.thymeleaf:thymeleaf-spring4:3.0.5.RELEASE")
	compile("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:2.1.1")

```
[Thymeleaf3的特性](http://www.oschina.net/news/73207/thymeleaf-3-0-0)

Thymeleaf2缺点：

1. 所有HTML元素必须要有对应结束标签。

以下正常HTML写法将无法编译通过：

```
<base th:href="@{/}">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" >

<input class="form-control" name="serviceName" placeholder="输入一个服务名称，例如人员服务"    maxlength="100">

```

2. 所有HTML元素的属性必须要有值

类似angularjs这样ng-cloak、required的写法也将无法编译通过：

```

<div class="main" ng-app="routeApp" ng-controller="routeCtrl" ng-cloak></div>

<input class="form-control" name="path" ng-model="route.path" maxlength="100" unique-Name required />

```
 
3. JS方法里若有&特殊字符，则必须使用/*<![CDATA[*/和/*]]>*/进行转义。

例如：
```
function matchIP(ip) {

 return ip != '0.0.0.0' && ip != '255.255.255.255' &&
                        ip.match(/\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/);

}
```
## 