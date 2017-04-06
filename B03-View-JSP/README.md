# SpringBoot - 配置使用JSP

## 疑问收集

1. 如何配置使用JSP？

步骤一：修改Gradle文件

引入war插件

```
apply plugin: 'war'
```

引入相关依赖

```
dependencies {
	
	providedRuntime('org.springframework.boot:spring-boot-starter-tomcat')
	//	引入jsp解析类
	providedRuntime('org.apache.tomcat.embed:tomcat-embed-jasper')
	providedRuntime('javax.servlet:javax.servlet-api')
	providedRuntime('javax.servlet:jstl')
	
}
```

步骤二：修改SpringBoot配置

增加以下配置：
```
spring:
  mvc:
    view:
     prefix: /WEB-INF/
     suffix: .jsp
```

步骤三：访问JSP

新建目录：{projectName}/src/main/webapp/WEB-INF，在WEB-INF目录下新建JSP，如:helloJsp1.jsp

新建Controller

```java
@Controller
public class HelloController {

	@Value("${application.hello:Hello Angel}")
	private String hello;

	/**
	 * @param map map
	 * @return String
	 */
	@RequestMapping("/helloJsp")
	public String helloJsp(Map<String, Object> map) {

		System.out.println("HelloController.helloJsp().hello=" + hello);

		map.put("hello", hello);

		return "helloJsp1";
	}
	
}
```

启动后输入 http://localhost:8080/helloJsp1 ，访问即可。更多详情见源码。

## 注意
1. JSP存放src/main/resources/static或者src/main/resources/template目录下都无法访问。
2. 打包需要打成war包才能正常访问JSP，若使用jar包则生成的jar包会丢失src/main/webapp目录下的资源。

基于以上第二点，推荐使用其他后端模板而不是使用JSP。