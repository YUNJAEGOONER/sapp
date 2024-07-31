package com.ll.sapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/sbb")
    @ResponseBody
    public String index() {
        return "안녕";
    }
    //localhost:8080접속 시, root()가 실행되면서
    //localhost:8080/question/list로 리가이렉트
    @GetMapping("/")
    public String root(){
        return "redirect:/question/list";
    }
}
