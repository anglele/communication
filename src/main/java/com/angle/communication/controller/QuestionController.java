package com.angle.communication.controller;


import com.angle.communication.dto.QuestionDto;
import com.angle.communication.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionservice;
    @GetMapping("/question/{id}")
    public  String question(@PathVariable(name = "id")Integer id,
                            Model model){
      QuestionDto questionDto= questionservice.getById(id);
      model.addAttribute("question",questionDto);
        return "question";
    }
}
