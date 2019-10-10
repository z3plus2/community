package cori.community.demo.controller;

import cori.community.demo.dto.PaginationDTO;
import cori.community.demo.mapper.UserMapper;
import cori.community.demo.model.User;
import cori.community.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 3plus2
 * @data 2019/10/9 18 13
 * @desercription
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

//    @Autowired
//    private UserMapper userMapper;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size){

        User user= (User) request.getSession().getAttribute("user");

        if (user==null){
            return "index";
        }
        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","收到信息");
        }
        PaginationDTO questions = questionService.list(user.getId(),page,size);
        model.addAttribute(questions);
        return "profile";
    }
}
