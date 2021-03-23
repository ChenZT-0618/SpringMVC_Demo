package com.viewResolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ChenZT
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        // return "success";

        // 使用相对路径
        // return "../../hello";

        // forward请求，不会拼接前缀和后缀
        return "forward:/hello.jsp";
    }

    @RequestMapping("/test01")
    public String test01() {
        System.out.println("handler方法");
        // 流程：转发到上面的hello()方法，hello方法再转发到hello.jsp页面
        return "forward:/hello";
    }

    /**
     * 重定向到jsp页面 : redirect
     * springMVC的重定向：会自动添加项目名
     * 原生的重定向，需要加上项目名才能成功
     * <p>
     * 有前缀的转发、重定向和视图解析器没关系
     */
    @RequestMapping("/test02")
    public String test02() {
        System.out.println("test02：重定向方法");
        // 流程：转发到上面的hello()方法，hello方法再转发到hello.jsp页面
        return "redirect:/hello.jsp";
    }
}
