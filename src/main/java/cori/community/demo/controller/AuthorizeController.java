package cori.community.demo.controller;

import cori.community.demo.dto.AccessToken;
import cori.community.demo.dto.GithubUser;
import cori.community.demo.mapper.UserMapper;
import cori.community.demo.model.User;
import cori.community.demo.provider.GithubProvider;
import cori.community.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author 3plus2
 * @data 2019/10/2 13 00
 * @desercription
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;




    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id(clientId);
        accessToken.setClient_secret(clientSecret);
        accessToken.setCode(code);
        accessToken.setRedirect_uri(redirectUri);
        accessToken.setState(state);
        //把收到的code post到 https://github.com/login/oauth/access_token
        //会返回一个access_token=e72e16c7e42f292c6912e7710c838347ae178b4a&token_type=bearer
        String ts=githubProvider.getAccessToken(accessToken);
        //使用获得的access token去api取得想要的信息
        final GithubUser githubUser = githubProvider.getGithubUser(ts);
        if (githubUser!=null){
            //登陆成功
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());

            user.setAvatarUrl(githubUser.getAvatarUrl());

            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",token));

            return "redirect:/";
        }else{
            //登陆失败
            return "redirect:/";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
