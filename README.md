### Feign 声明式调用

本示例demo 在springcloud框架下最基本的声明式调用的使用。

#### 运行本示例
  * 启动Eureka Server
  * 启动Ｓｅｒｖｅｒ-1服务
  * 启动Feign-Client
  * 在浏览器中运行localhost:8084/feign/get

#### 过程中的问题记录

#### Feign的依赖声明
至少在Finchly.SR2版本中，应该在Feign-Client项目中pom.xml中声明以下，而不是spring-cloud-starter-feign　（在starter.spring.io中生成的pom是这样的配置）
	    <dependency>
	        <groupId>org.springframework.cloud</groupId>
	        <artifactId>spring-cloud-starter-openfeign</artifactId>
	    </dependency>

#### 有关项目结构
在Feign-Client最初的项目结构大致如下：

    src/main/java/example/main/Application.java
    src/main/java/example/controller/TestController.java

这种情况下，需要在Application类上面增加@ComponentScan("example")，否则TestController将无法被扫描到从而注册到REST服务中。可是如果增加了这个注解，
该controller是注册上了，可是会报如下的错误,表示标注@FeignClient的接口没有被注入实例。

  Field myService in example.controller.TestController required a bean of type 'example.service.MyService' that could not be found.

  The injection point has the following annotations:
    - @org.springframework.beans.factory.annotation.Autowired(required=true)

大体猜想是bean扫描导致的问题，后来调整项目结构，

    src/main/java/example/Application.java
    src/main/java/example/controller/TestController.java
    
同时在Application上面移除@ComponentScan注解，结果一切运行正常。

翻阅SpringBoot官方文档，里面也建议SpringBoot应用最好follow下面的代码结构，这样ｂｅａｎ扫描会自动从Application.class所在的root ｐａｃｋａｇｅ
向下扫描，自然而然能够扫描到其他package下的类。

https://docs.spring.io/spring-boot/docs/1.5.4.RELEASE/reference/htmlsingle/#using-boot-locating-the-main-class

  We generally recommend that you locate your main application class in a root package above other classes. 
  Using a root package also allows the @ComponentScan annotation to be used without needing to specify a basePackage attribute.
  
    com
     +- example
         +- myproject
             +- Application.java
             |
             +- domain
             |   +- Customer.java
             |   +- CustomerRepository.java
             |
             +- service
             |   +- CustomerService.java
             |
             +- web
                 +- CustomerController.java
