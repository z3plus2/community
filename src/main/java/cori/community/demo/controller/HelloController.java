package cori.community.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 3plus2
 * @data 2019/10/1 16 43
 * @desercription
 */
@Controller
public class HelloController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
