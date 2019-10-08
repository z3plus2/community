package cori.community.demo.controller;

import cori.community.demo.dto.PaginationDTO;
import cori.community.demo.dto.QuestionDTO;
import cori.community.demo.mapper.QuestionMapper;
import cori.community.demo.mapper.UserMapper;
import cori.community.demo.model.Question;
import cori.community.demo.model.User;
import cori.community.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);

                    if (user != null) {

                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }

        PaginationDTO questions = questionService.list(page,size);

        model.addAttribute(questions);
        return "index";
    }

}
