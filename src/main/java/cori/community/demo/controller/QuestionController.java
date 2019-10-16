package cori.community.demo.controller;

import cori.community.demo.dto.QuestionDTO;
import cori.community.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 3plus2
 * @data 2019/10/11 17 06
 * @desercription
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id") Integer id,
                           Model model){
        QuestionDTO questionDTO= questionService.getById(id);
        questionService.addViewCount(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
