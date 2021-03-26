package com;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenZT
 */
@RestController
public class ajaxController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";

    }


    @RequestMapping("/t2")
    public List<Student> test2() {
        List<Student> students = new ArrayList<Student>();
        // 添加数据
        students.add(new Student("java", 1, "男"));
        students.add(new Student("c", 2, "男"));
        students.add(new Student("python", 3, "男"));

        return students;
    }

    @RequestMapping("/t3")
    public String login(String username) {
        String msg = "";
        if (username.equals("admin")) {
            msg = "OK";
        } else {
            msg = "用户名错误";
        }
        return msg;
    }
}
