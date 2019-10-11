package com.angle.communication.controller;

import com.angle.communication.Mapper.UserMapper;
import com.angle.communication.Model.User;
import com.angle.communication.dto.AccessTokenDTO;
import com.angle.communication.dto.GithubUser;
import com.angle.communication.provider.GithubProvider;
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
 *
 */
@Controller

public class AuthorizeController {
    @Autowired()
    //githubProvider 已经封装好了 要调用他 用 Autowired
    //自动把spring容器中写好的实列化的实列加载到我当前使用的上下文
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    //自动找配置文件里的参数传值 不用改代码
    public String clientId;
    @Value("${github.client.secret}")
    public String clientSecret;
    @Value("${github.redirect.uri}")
    public String redirectUri;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    //ctrl+alt+v  accesstoken 变量赋值在外面
    accessTokenDTO.setCode(code);
    accessTokenDTO.setClient_id(clientId);
    accessTokenDTO.setClient_secret(clientSecret);
    accessTokenDTO.setRedirect_uri(redirectUri);
    accessTokenDTO.setState(state);
        System.out.println(code);
    String acccessToken = githubProvider.getAcccessToken(accessTokenDTO);
            GithubUser githubUser= githubProvider.getUser(acccessToken);

      if (githubUser != null&&githubUser.getId() != null){
          User user = new User();
          String token = UUID.randomUUID().toString();
          user.setToken(token);
          user.setName(githubUser.getName());
          user.setAccountId(String.valueOf(githubUser.getId()));//github以外的其他平台
         user.setGmtCreate(System.currentTimeMillis());
         user.setGmtModified(user.getGmtCreate());
         user.setAvatarUrl(githubUser.getAvatar_url());

          userMapper.insert(user);
          //把从git获得的信息传入数据库  相当于session的写入
          //登录成功 写cookie和session
          // request.getSession().setAttribute("user",githubUser);
          response.addCookie(new Cookie("token",token));
          //自动写入cookie
          return "redirect:/";
      }else {
          //否则登录失败，重新登录
          return  "redirect:/";
       }
          }
}