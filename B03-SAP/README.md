# SpringBoot - 单页应用集成环境搭建

**常规单页应用运行环境复杂**

常规单页应用一般是前后端分离，整个环境运行起来需要三个环境：
1. 运行前端应用服务器，一般是使用nodejs的插件运行。如：browserSync。
2. 运行后端应用服务器，一般和后端开发语言有关，如：运行java的tomcat。
3. 代理服务器，解决前端应用访问后端服务应用跨域的问题。如：Nginx、apache。

本项目例子目标：
1. 开发阶段只运行一个应用服务器即可，最后部署时可前后端拆开部署也可合并一起部署。

# 使用步骤

### 环境安装

1. 安装nodejs和npm

2. 安装gulp

```
npm install gulpjs/gulp-cli -g 
```

3. 使用npm下载相关依赖

```
npm install
```

建议：  
npm源被墙，可以使用国内镜像。

```
npm install -g cnpm --registry=https://registry.npm.taobao.org
```

在运行

```
cnpm install
```

### 开发

运行gradle bootRun 命令，启动SpringBoot应用。

### 打包

场景一：合并部署

运行gradle bootRepackage 命令，得到发布jar。

场景二：分开部署

执行gulp build任务，得到前端应用目录。

运行gradle bootRepackage 命令，得到后应用发布jar。

# 实现原理：

本项目后端使用SpringBoot开发，gradle构建管理，运行gradle bootRun或bootRepackage任务时，会调用gulp build任务，该任务会将src/main/ui目录下js、css全部压缩合并，输出到build/resources/main/static/，该目录下是springBoot应用的静态资源请求目录，可通过浏览器直接访问。

## 疑问收集

记录学习过程中的疑问，方便后面归纳整理。

## 重点摘要

[gradle-nodejs插件](https://github.com/srs/gradle-node-plugin/blob/master/docs/node.md)

## 