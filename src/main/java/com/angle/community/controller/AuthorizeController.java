package com.angle.community.controller;

import com.angle.community.dto.AccessTokenDTO;
import com.angle.community.dto.GithubUser;
import com.angle.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * created by codedrinker on 2019/4/24
 */
@Controller

public class AuthorizeController {
    @Autowired()
    //githubProvider 已经封装好了 要调用他 用 Autowired
    //自动把spring容器中写好的实列化的实列加载到我当前使用的上下文
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    //自动找配置文件里的参数传值 不用改代码
    public String clientid;
    @Value("${github.client.secret}")
    public String clientsecret;
    @Value("${github.redirect.url}")
    public String redirecturl;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request){

    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    //ctrl+alt+v  accesstoken 变量赋值在外面
    accessTokenDTO.setCode(code);
    accessTokenDTO.setClient_id("clientid");
    accessTokenDTO.setClient_secret("clientsecret");
    accessTokenDTO.setRedirect_url("redirecturl");
    accessTokenDTO.setState(state);
    String acccessToken = githubProvider.getAcccessToken(accessTokenDTO);
            GithubUser user= githubProvider.getUser(acccessToken);
      if (user != null){
          //登录成功 写cookie和session
         request.getSession().setAttribute("user",user);
          return "redirect:/";
      }else {
          //否则登录失败，重新登录
          return  "redirect:/";}


    }
     
}
