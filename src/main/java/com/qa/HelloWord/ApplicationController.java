package com.qa.HelloWord;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
@ComponentScan(basePackageClasses = ApplicationController.class)
public class ApplicationController {

    @GetMapping("hello")
    @ResponseBody
    public String helloWord(){
        String helloWord = "Hello World from Zak";
        return helloWord;
    }



}
