package com;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ChenZT
 */
@Controller
public class RequestMappingParam {
    private final static String SUCCESS = "success";

    @RequestMapping(value = "/testMethod",method = RequestMethod.GET)
    public String testMethod(Model model){
        model.addAttribute("msg","GetMethod");
        return SUCCESS;
    }

    // URL:***/testParamsAndHeaders?username=123&age=10
    // params：说明URL中必须带有的参数，两个参数以上需同时满足
    // 如果代码为：params = {"username","age=11"}，而URL为：?username=123&age=10则报错
        // Parameter conditions "username, age=11" not met for actual request parameters: username={123}, age={12}
    // headers同理，但是headers携带的参数是在浏览器中查看
    @RequestMapping(value = "/testParamsAndHeaders",params = {"username=abc","age!=11"})
    public String testParamsAndHeaders(Model model) {
        model.addAttribute("msg","testParamsAndHeaders");
        return SUCCESS;
    }


    // 上面是将必要参数写在@RequestMapping中，还有种方法可以将其写在形参中：
    // @RequestParam，@RequestHeader
    // URL:***/testRequestParam?username=chenZT&age=10
    @RequestMapping(value = "/testRequestParam")
    public String testRequestParam(Model model ,@RequestParam("username") String username, @RequestParam("age") int age) {
        model.addAttribute("msg","姓名:"+username+",年龄:"+age);
        return SUCCESS;
    }

}
