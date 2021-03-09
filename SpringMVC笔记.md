## 回顾MVC

### 什么是MVC

- MVC是模型(Model)、视图(View)、控制器(Controller)的简写，是一种软件设计规范。
- 是将业务逻辑、数据、显示分离的方法来组织代码。
- MVC主要作用是**降低了视图与业务逻辑间的双向偶合**。
- MVC不是一种设计模式，**MVC是一种架构模式**。当然不同的MVC存在差异。

**Model（模型）：**数据模型，提供要展示的数据，因此包含数据和行为，可以认为是领域模型或JavaBean组件（包含数据和行为），不过现在一般都分离开来：Value Object（数据Dao） 和 服务层（行为Service）。也就是模型提供了模型数据查询和模型数据的状态更新等功能，包括数据和业务。

**View（视图）：**负责进行模型的展示，一般就是我们见到的用户界面，客户想看到的东西。

**Controller（控制器）：**接收用户请求，委托给模型进行处理（状态改变），处理完毕后把返回的模型数据返回给视图，由视图负责展示。也就是说控制器做了个调度员的工作。

**最典型的MVC就是JSP + servlet + javabean的模式。**

### 回顾Servlet

1. 编写一个Servlet类，用来处理用户的请求

    ```java
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;

    /**
     * @author ChenZT
     */
    public class HelloServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // 获取前端参数
            String method = request.getParameter("method");
            if (method.equals("add")){
                request.getSession().setAttribute("msg","执行了add方法");
            }
            if (method.equals("delete")){
                request.getSession().setAttribute("msg","执行了delete方法");
            }
            // 调用业务层，暂时没有
            // 视图跳转，要在对应路径位置创建一个jsp文件
            request.getRequestDispatcher("/WEB-INF/views/test.jsp").forward(request,response);
        }
    }
    ```

2. 在web.xml中注册Servlet

    ```xml
    <servlet>
        <servlet-name>HelloServlet</servlet-name>
        <servlet-class>HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>HelloServlet</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
    ```

3. 配置Tomcat，并启动测试

  - localhost:8080/user?method=add
  - localhost:8080/user?method=delete

**MVC框架要做哪些事情**

从客户端获取用户提交的数据 --> 根据url去到对应的Java类或Java方法 --> 处理数据 --> 将处理完的数据展示出来。

1. 将url映射到java类或java类的方法 .
2. 封装用户提交的数据 .
3. 处理请求--调用相关的业务处理--封装响应数据 .
4. 将响应的数据进行渲染 . jsp / html 等表示层数据 .

## SpringMVC

### 概述

Spring MVC是Spring Framework的一部分，是基于Java实现MVC的轻量级Web框架。**底层还是Servlet**

文档：https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#spring-web

### 特点：

1. 轻量级，简单易学
2. 高效 , 基于请求响应的MVC框架
3. 与Spring兼容性好，无缝结合
4. 约定优于配置
5. 功能强大：RESTful、数据验证、格式化、本地化、主题等
6. 简洁灵活

Spring的web框架围绕**DispatcherServlet** [ 调度Servlet ] 设计。

DispatcherServlet的作用是将请求分发到不同的处理器。从Spring 2.5开始，使用Java 5或者以上版本的用户可以采用基于注解形式进行开发，十分简洁；

> Spring MVC, as many other web frameworks, is designed around the front controller pattern where a central Servlet，**the DispatcherServlet**，provides a shared algorithm for request processing, while actual work is performed by configurable delegate components. 

正因为SpringMVC好 , 简单 , 便捷 , 易学 , 天生和Spring无缝集成(使用SpringIoC和Aop) , 使用约定优于配置 . 能够进行简单的junit测试 . 支持Restful风格 .异常处理 , 本地化 , 国际化 , 数据验证 , 类型转换 , 拦截器 等等......所以我们要学习 .

### 运行原理

![](./配图/springMVC执行流程.png)

![](./配图/流程.png)

### 执行流程

1. 用户提交请求，该请求会先到达DispatcherServlet
   - 假设请求的url为 : http : //localhost:8080/SpringMVC/hello，
     - http : //localhost:8080服务器域名
     - SpringMVC部署在服务器上的web站点
     - hello表示控制器
2. DispatcherServlet会把用户提供的url给到HandlerMapping处，通过HandlerExecution部分得出控制器为hello，并将结果返还给DispatcherServlet
3. DispatcherServlet把得到的控制器交给HandlerAdapter，HandlerAdapter则是根据配置，找到对应的控制器Controller
4. Controller执行业务逻辑，返回ModelAndView，即数据和视图，给DispatcherServlet
5. DispatcherServlet调用视图解析器(ViewResolver)来解析HandlerAdapter传递的ModelAndView，视图解析器就能解析要呈现给用户的网页或者结果
6. DispatcherServlet根据视图解析器解析的视图结果，调用具体的视图。最终视图呈现给用户。

### 基本操作

#### 实现Controller接口

- web.xml文件中配置 DispatcherServlet。

```xml
<!--配置DispatchServlet-->
<servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!--要绑定spring配置文件-->
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:springmvc-servlet.xml</param-value>  
    </init-param>
    <!--启动级别-->
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>springmvc</servlet-name>
    <!--
        / 只匹配所有请求，不会匹配jsp
        /* 匹配所有请求，会匹配jsp
        -->
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

- 根据web.xml文件中的 <param-value>classpath:springmvc-servlet.xml</param-value> ，创建同名的spring配置文件
  - 在该配置文件中负责三件事：配置HandlerMapping、HandlerAdapter、ResourceViewResolver
  - 同时设置好控制器对应的Controller类，如最后一行

```xml
<!--这是标准spring配置文件-->
<!--处理映射器-->
<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
<!--处理器适配器-->
<bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>
<!--视图解析器:DispatcherServlet给他的ModelAndView
        做了两部分
        1. 获取数据
        2. 获取view的名称，拼接好，找到对应页面
        3. 数据渲染
    -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
    <!--前缀-->
    <property name="prefix" value="/WEB-INF/views/"/>
    <!--后缀-->
    <property name="suffix" value=".jsp"/>
</bean>


<!--Handler-->
<bean id="/hello" class="HelloController"/>
```

- 编写Controller类：**继承Controller接口**
  - 该类返回一个ModelAndView对象，该对象存放着数据和即将跳转的视图；

```Java
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ChenZT
 */
public class HelloController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mv = new ModelAndView();
        // 业务代码
        String result = "HelloSpringMVC";
        mv.addObject("msg",result);
        // 视图跳转
        mv.setViewName("hello");
        return mv;
    }
}
```

#### 注解@Controller

- web.xml文件中配置 DispatcherServlet。
- 同名的spring配置文件，
  - 开启注解扫描。：<context:component-scan base-package="扫描的包目录"/>
  - <mvc:annotation-driven /> ：这行代码帮我们完成了HandlerMapping和HandlerAdapter的注册  
    - 支持mvc注解驱动，在spring中一般采用@RequestMapping注解来完成映射关系
    - 要想使@RequestMapping注解生效，必须向上下文中注册DefaultAnnotationHandlerMapping和AnnotationMethodHandlerAdapter实例。
    - 这两个实例分别在类级别和方法级别处理。而annotation-driven配置帮助我们自动完成上述两个实例的注入。
  - <mvc:default-servlet-handler /> ：忽略静态资源
  - 手动注册视图解析器

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--启动Spring注解扫描,让指定包下的注解生效-->
    <context:component-scan base-package="com"/>
    <!-- 让Spring MVC不处理静态资源 -->
    <mvc:default-servlet-handler />
    <mvc:annotation-driven />

    <!-- 必须做的:视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
          id="internalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/views/" />
        <!-- 后缀 -->
        <property name="suffix" value=".jsp" />
    </bean>
</beans>
```

- 编写Controller类：**使用注解@Controller** 
  - 访问地址：项目名/h1
  - 如果在@RequestMapping作用在类上，那么要在控制器前面加上Controller类的路径：项目名/hello/h1

```java
package com;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author ChenZT
 */

// @RestController 返回的就是字符串,作为前后端分离的工具
@Controller  // 注解标记
@RequestMapping("/hello") // 路径标记
public class HelloController{
	
    @RequestMapping("/h1") // 控制器标记
    public String sayHello(Model model){
        // 在模型中添加属性
        model.addAttribute("msg", "hello,SpringMVC");
        // 返回值:视图名称
        return "hello";
    }
}
```

#### 不同点

- 通过继承Controller接口来实现，一个类只能实现一种方法。并且还需要在配置文件中注册相应的bean对象——麻烦
- 注解@Controller可以让一个类实现多种方法——简便

### @RequestMapping

SpringMVC使用@RequestMapping 注解为控制器指定可以处哪些 URL请求，

也可以说根据URL将数据送到指定的方法上实现。

可以修饰**方法和类**

- 修饰类：提供初步的请求映射信息。相对于WEB应用的根目录
- 修饰方法：提供进一步的细分映射信息。
- 若方法上的RequestMapping的路径写为：“/hello/h1”，与上面的代码同理。

#### 请求参数

- 请求URL：value，访问该方法或类的路径
- 请求方法：method，不同的请求方式，有POST，DELETE，PUT，GET等，分别对应增删改查。剩下的看RequestMethod类
- 请求参数：params，就是URL中问号后面跟的部分：/testParamsAndHeaders?**username=123&age=10**
  - 添加了这个参数，说明URL中必须带有对应的参数，同时可以进行简单的等于或不等于判断。
  - 若参数有2个以上，则必须同时满足才能匹配到
- 请求头：headers，同params同理
  - 请求头参数：在浏览器中Request Headers查看

```Java
@Controller
public class RequestMappingParam {
    private final static String SUCCESS = "success";

    @RequestMapping(value = "/testMethod",method = RequestMethod.GET)
    public String testMethod(Model model){
        model.addAttribute("msg","GetMethod");
        return SUCCESS;
    }

    // URL:***/testParamsAndHeaders?username=123&age=10
    // 如果代码为：params = {"username","age=11"}，而URL为：?username=123&age=10则报错：
    // Parameter conditions "username, age=11" not met for actual request parameters: username={123}, age={12}
    @RequestMapping(value = "/testParamsAndHeaders",params = {"username=abc","age!=11"})
    public String testParamsAndHeaders(Model model) {
        model.addAttribute("msg","testParamsAndHeaders");
        return SUCCESS;
    }
}
```

### @RequestParam

与@RequestMapping不同，@RequestParam是写用来修饰参数的，可以将URL中的形参映射到形参中，便可对其进行操作

```Java
@RequestMapping(value = "/testRequestParam")
public String testRequestParam(Model model ,@RequestParam("username") String username,@RequestParam("age") int age) {
    model.addAttribute("msg","姓名:"+username+",年龄:"+age);
    return SUCCESS;
}
```

#### 参数

- String value() 或者 String name() ：URL对应的参数名，二者选一个就行
- boolean required()：是否必须，默认是true
- String defaultValue()：参数默认值，如果URL没有携带值的话，就默认该值

**@RequestHeader **同理

### 支持Ant匹配字符

- ? ：匹配文件名中一个字符
- *： 匹配文件名中任意字符
- \*\*:*\*  匹配多层路径

![](./配图/匹配.png)

```Java
// 可在 /testAntPath/*/abc 之间添加任意数量字符，都可以匹配到
@RequestMapping("/testAntPath/*/abc")
public String testAntPath() {
    System.out.println("testAntPath");
    return SUCCESS;
}
```

### RestFul风格

如果使用@RequestMapping或@RequestParam，URL会显得比较复杂或冗长，且不可重用。所以就有接下来的**RestFul风格**和**@PathVariable**注解。

Restful就是一个**资源定位及资源操作的风格**。不是标准也不是协议，只是一种风格。基于这个风格设计的软件可以更简洁，更有层次，更易于实现缓存等机制。

传统的不同的资源操作需要不同的URL，如：

- http: //127.0.0.1/item/queryItem.action?id=1  查询，GET
- http: //127.0.0.1/item/saveItem.action              新增，POST

使用RESTful,

- http: //127.0.0.1/item/1         查询--GET
- http: //127.0.0.1/item             新增--POST

并且对用一个地址，也可以通过不同的请求方式来实现不同的效果，在@RequestMapping中通过method参数指定

- http: //127.0.0.1/item             新增，POST

- http: //127.0.0.1/item             更新，PUT

**优点**

- 使路径变得更加简洁；
- 获得参数更加方便，框架会自动进行类型转换。
- 通过路径变量的类型可以约束访问参数，如果类型不一样，则访问不到对应的请求方法

#### @PathVariable 

可以映射URL绑定的占位符，在REST风格有重大作用，

通过@PathVariable 可以将URL中占位参数绑定到控制器处理方法的入参中

#### 参数：

- String value()  或 String name() ：跟URL中的占位符对应

- 
  boolean required() default true ：是否必须

```Java
@Controller
public class PathVariableTest {
    private final static String SUCCESS = "success";

    // URL:.../testPathVariable/13/24
    // 输出为：24+13,结果为：37
    @RequestMapping("/testPathVariable/{p1}/{p2}")
    public String testPathVariable(Model model, @PathVariable(value = "p2") Integer p1,@PathVariable(value = "p1") Integer p2){
        int result = p1+p2;
        model.addAttribute("msg",p1+"+"+p2+ ","+"结果为："+result);
        return SUCCESS;
    }
}
```

使用restful风格实现，POST，DELETE，PUT，GET操作