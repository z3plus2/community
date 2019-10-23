package cori.community.demo.controller;

import cori.community.demo.cach.QuestionsHot;
import cori.community.demo.dto.PaginationDTO;
import cori.community.demo.mapper.QuestionMapper;
import cori.community.demo.mapper.UserMapper;
import cori.community.demo.model.Question;
import cori.community.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.PriorityQueue;

/**
 * @author 3plus2
 * @data 2019/10/1 16 43
 * @desercription
 */
@Controller
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionsHot questionsHot;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {


        PaginationDTO questions = questionService.list(page,size);

        model.addAttribute("paginationDTO",questions);

        PriorityQueue<Question> questionHotList=  questionsHot.getQuestionsHot();
        model.addAttribute("questionHot",questionHotList);
        return "index";
    }

}
