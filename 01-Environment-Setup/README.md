# [Y]Dart-Framework - 项目工程搭建（一）

## 一、创建目录

### 1. 创建工程目录

### 2. 导入eclipse

前提：eclipse已安装Gradle插件

1. 选择Existing Gradle Project

![mark](http://ol28s5tk9.bkt.clouddn.com/mdimages/20170222/141923947.png)


2. 指定项目工程位置

![mark](http://ol28s5tk9.bkt.clouddn.com/mdimages/20170222/142210472.png)

3. 指定本地gradle安装目录

![mark](http://ol28s5tk9.bkt.clouddn.com/mdimages/20170222/142448645.png)

4. 点击Finsh，导入完成。

导入后工程结构

![mark](http://ol28s5tk9.bkt.clouddn.com/mdimages/20170222/141713083.png)

## 二、配置工程

以下步骤都是修改项目工程目录下的build.gradle文件

### 1. 引入插件

#### 引入SpringBoot的Gradle插件

```gradle
buildscript {
    repositories {
        maven{url "http://10.10.5.62:8081/nexus/content/groups/public/"}
    }
}

//引入SpringBoot Gradle 插件
plugins {
    id 'org.springframework.boot' version '1.5.1.RELEASE'
}

apply plugin: 'java'

// 其他代码

```

#### 配置插件

```gradle

// 其他代码

apply plugin: 'java'

// 配置springBoot插件
springBoot {
    // 指定SpringBoot启动类
	mainClass = 'com.comtop.DartApplication'
}

// 其他代码

```

### 2. 引入依赖包

#### 配置gradle仓库

```gradle

repositories {
//  jcenter()
    maven{url "http://10.10.5.62:8081/nexus/content/groups/public/"}
}

```

#### 配置引入Dart-Framework依赖包

```gradle
dependencies {
    // 引入dart-framework
    compile 'com.comtop:comtop-dart-framework:1.0.0'
	
    compile 'org.slf4j:slf4j-api:1.7.7'
    testCompile "junit:junit:4.11"
}
```

#### 启动项目

启动项目只需运行gradle的bootRun任务即可。

##### Eclipse中启动

1. 打开Gradle Tasks视图（Eclipse快捷键：alt + shift + Q , Q）

![mark](http://ol28s5tk9.bkt.clouddn.com/mdimages/20170222/143726026.png)

2. 选择 bootRun 任务运行。

![mark](http://ol28s5tk9.bkt.clouddn.com/mdimages/20170222/143947392.png)

启动成功信息展示

![mark](http://ol28s5tk9.bkt.clouddn.com/mdimages/20170222/144502151.png)

## 三、开发项目

### 开发访问第一个页面

Dark-framework已经依赖SpringMVC，可直接使其相关功能。

#### 1. 新建HelloControl.java

类所在包要以com.comtop开头已确保被容器扫描到

```java
package com.comtop.sxdl.myapp.action

@RestController
@RequestMapping({ "/myApp" })

// 包要以com.comtop开头已确保被容器扫描到
public class HelloControl {

    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello ,dart frame!";
    }
}

```

#### 2. 项目应用访问配置

在 src/main/resources 目录下增加文件 application.properties

```properties
#server
# 设置服务器访问端口
server.port=8080
# 设置应用访问的上下文路径
server.context-path=/web

```

#### 3. 访问

运行gradle的bootRun任务启动应用，访问地址：http://localhost:8080/web/myApp/hello

![mark](http://ol28s5tk9.bkt.clouddn.com/mdimages/20170222/153623410.png)

### 断点调试

### 工程目录介绍

### Spring配置

## 四、发布项目

### 发布jar包

#### 1. 运行gradle的bootRepackage任务

![mark](http://ol28s5tk9.bkt.clouddn.com/mdimages/20170222/160835244.png)

运行成功将输出以下文件：

![mark](http://ol28s5tk9.bkt.clouddn.com/mdimages/20170222/161000871.png)

#### 2. 使用 java 命令启动

命令如下
```bat
java -jar dart-demo.jar
```

![mark](http://ol28s5tk9.bkt.clouddn.com/mdimages/20170222/161113444.png)

说明：dart-demo.jar.original 就是传统的jar包

### 发布War包

#### 1. 修改工程配置

引入war插件

```gradle
apply plugin: 'java'
// 引入war插件
apply plugin: 'war'

```

排除springBoot内嵌tomcat

```gradle
dependencies {

    // 生成war需要排除springBoot内嵌tomcat
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat:1.5.1.RELEASE")
    
    testCompile "junit:junit:4.11"
}
```

#### 2. 运行gradle的bootRepackage任务

运行成功将输出以下文件：

![mark](http://ol28s5tk9.bkt.clouddn.com/mdimages/20170222/162506298.png)

#### 3. 运行

##### java命令运行

![mark](http://ol28s5tk9.bkt.clouddn.com/mdimages/20170222/162655580.png)

##### 传统方式部署 tomcat 运行

注意：该方式打出的war只支持Tomcat 7.0+（Servlet 3.0+ ）
