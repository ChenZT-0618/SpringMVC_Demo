package com;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ChenZT
 */
@Controller
public class PathVariableTest {
    private final static String SUCCESS = "success";

    // URL:.../testPathVariable/13/24
    // 13,24分别对应p1,p2
    @RequestMapping("/testPathVariable/{p1}/{p2}")
    public String testPathVariable(Model model, @PathVariable(value = "p2") Integer p1,@PathVariable(value = "p1") Integer p2){
        int result = p1+p2;
        model.addAttribute("msg",p1+"+"+p2+ ","+"结果为："+result);
        return SUCCESS;
    }
}
