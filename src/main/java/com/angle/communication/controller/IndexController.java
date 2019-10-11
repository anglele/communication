package com.angle.communication.controller;
import com.angle.communication.Mapper.QuestionMapper;
import com.angle.communication.Mapper.UserMapper;
import com.angle.communication.Model.Question;
import com.angle.communication.Model.User;
import com.angle.communication.dto.PageInationDto;
import com.angle.communication.dto.QuestionDto;
import com.angle.communication.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 */
@Controller
//自动识别扫描当前的类 做为spring的bean去管理
// 同时也识别他为controller去获取前端的请求 api 的承载者
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request , Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size) {
        PageInationDto pageination = questionService.list(1,5);
        model.addAttribute("pageination", pageination);
        return "index";//去templates包下找hello这个模板

}}