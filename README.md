## swagger-dubbo

[![Build Status](https://travis-ci.org/Sayi/swagger-dubbo.svg?branch=master)](https://travis-ci.org/Sayi/swagger-dubbo)

Dubbo |ˈdʌbəʊ| 是阿里巴巴提供的分布式框架，致力于提供高性能和透明化的RPC远程服务调用方案，以及SOA服务治理方案。  
Swagger围绕着OpenAPI规范，提供了一套设计、构建、文档化rest api的开源工具。


**Swagger-dubbo致力于dubbo与swagger文档的集成，并且为dubbo提供rest风格的http调用方案。适用于dubbo服务接口调试、接口http请求模拟、单机验证等场景。**

![Swagger-UI](swagger-dubbo-example/swagger_ui.png)

## 快速集成
所有集成都是基于spring配置。swagger v2.0、dubbo2.5.3+。

1. Maven依赖

```xml
<dependency>
  <groupId>com.deepoove</groupId>
  <artifactId>swagger-dubbo</artifactId>
  <version>1.0.3-alpha</version>
</dependency>
```

2. 启用swagger-dubbo

使用注解 `@EnableDubboSwagger`开启dubbo的swagger文档。
```java
package com.deepoove.swagger.dubbo.example;

import org.springframework.context.annotation.Configuration;
import com.deepoove.swagger.dubbo.annotations.EnableDubboSwagger;

@Configuration
@EnableDubboSwagger
public class SwaggerDubboConfig {

}

```
在spring xml配置中，打开Configuration注解，声明SwaggerDubboConfig。
```xml
<context:annotation-config />
<bean class="com.deepoove.swagger.dubbo.example.SwaggerDubboConfig" />
```

3. 配置swagger-dubbo

新增property文件swagger-dubbo.properties

在spring xml配置中，加载配置文件。

```xml
<context:property-placeholder location="classpath*:swagger-dubbo.properties" />
```

配置项说明：
```properties
#doc地址，默认为http://ip:port/context/swagger-dubbo/swagger.json
#swagger.dubbo.doc=swagger-dubbo
#http请求地址，默认为http://ip:port/h/com.XXX.XxService/method
#swagger.dubbo.http=h

#dubbo 服务版本号
swagger.dubbo.application.version = 1.0
#dubbo服务groupId
swagger.dubbo.application.groupId = com.deepoove
#dubbo服务artifactId
swagger.dubbo.application.artifactId = dubbo.api

#是否启用swagger-dubbo，默认为true
#swagger.dubbo.enable = true
```

4. 启动web容器，浏览器访问 `http://ip:port/context/swagger-dubbo/swagger.json`


## 示例与集成swagger-ui
示例参见swagger-dubbo-example。  

如何集成swagger-ui，参见官方文档 [GitHub Swagger UI](https://github.com/swagger-api/swagger-ui)

## swagger-dubbo集成注意事项
* 对于服务接口方法重载，为了在http请求中唯一确认一个方法，需要使用注解`@ApiOperation(nickname = "byArea")`,通过nickname标记唯一路径(如果不填写，将只显示一个方法)。此时，rest的请求地址为：`http://ip:port/h/com.XXX.XxService/method/byArea`
[Stackoverflow:重载的方法能够映射到同一URL地址吗](http://stackoverflow.com/questions/17196766/can-resteasy-choose-method-based-on-query-params)

* Object对象作为http请求参数为json string格式。格式不正确会导致解析错误。下一版本考虑参数json格式可视化。
[Stackoverflow:POST的方法能够接收多个参数吗？](http://stackoverflow.com/questions/5553218/jax-rs-post-multiple-objects)

* swagger注解既可以写在接口上，也可以写在实现类上。 
* 原生类型作为http请求参数为必填。

### [文章：探讨Dubbo与Swagger的集成](https://github.com/Sayi/sayi.github.com/issues/15)


在原有的基础上做的修改
1、
修改前：框架访问swagger.json只出现使用xml方式暴露的服务，不显示注解方式暴露的服务
修改后：经过修改已经支持注解方式暴露的服务

2、
修改前：框架不支持参数为List<T>的方法
修改后：框架支持参数为List<T>的方法

3、
修改前：框架没有统一返回格式
修改后：返回统一格式{"code":"1","data":{}}

4、修改jdk8为jdk7

 

版本升级命令  
mvn versions:set -DnewVersion=1.0.3.4-alpha

