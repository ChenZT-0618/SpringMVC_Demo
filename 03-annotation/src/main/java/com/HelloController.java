package com;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author ChenZT
 */

// 注解标记
@Controller
// @RequestMapping("/hello")
// @RestController 返回的就是字符串,作为前后端分离的工具
public class HelloController{

    @RequestMapping("/h1")
    public String sayHello(Model model){
        // 在模型中添加属性
        model.addAttribute("msg", "hello,SpringMVC");
        // 返回值:视图名称
        return "hello";
    }

}
