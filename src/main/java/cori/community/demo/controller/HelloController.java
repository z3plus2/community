package cori.community.demo.controller;

import cori.community.demo.mapper.UserMapper;
import cori.community.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 3plus2
 * @data 2019/10/1 16 43
 * @desercription
 */
@Controller
public class HelloController {

    @Autowired
    private UserMapper userMapper;;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if (cookies!=null&&cookies.length!=0)
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())){
                String token=cookie.getValue();
                User user=userMapper.findByToken(token);

                if (user!=null){

                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        return "index";
    }

}
