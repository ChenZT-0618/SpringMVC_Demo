package com;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author ChenZT
 */


@SessionAttributes(value = {"msg", "haha"}, types = String.class)
@Controller
public class OutputController {

    @RequestMapping("/handler01")
    public String mapHandler(Map<String, Object> map) {
        map.put("msg", "你好");
        map.put("haha", 18);
        return "success";
    }

    @RequestMapping("/handler02")
    public String modelHandler(Model model) {
        model.addAttribute("msg", "你好！");
        model.addAttribute("haha", "哈哈");
        return "success";
    }


    @RequestMapping("/handler03")
    public String modelMapHandler(ModelMap modelMap) {
        modelMap.addAttribute("msg", "你好！！");
        return "success";
    }

    @RequestMapping("/handler04")
    public ModelAndView modelAndViewHandler(ModelAndView mv) {
        mv.setViewName("success"); // 设置视图名称
        mv.addObject("msg", "你好！！！"); // 添加数据
        return mv;
    }


}
