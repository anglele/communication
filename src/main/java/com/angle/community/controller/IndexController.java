package com.angle.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@Controller
//自动识别扫描当前的类 做为spring的bean去管理
// 同时也识别他为controller去获取前端的请求 api 的承载者
public class IndexController {
@GetMapping("/")
    public String hello(String name,Model model ){

        // model.addAttribute("name",name)   ;//把穿的参数加进视图解析器里
        return "index";//去templates包下找hello这个模板
    }
}
