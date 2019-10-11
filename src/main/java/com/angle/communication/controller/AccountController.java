package com.angle.communication.controller;

import com.angle.communication.Model.Account;
import com.angle.communication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    public AccountService accountService;

    /**
     * 登录页面
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login( ModelAndView modelAndView){
        modelAndView.setViewName("/index-login");
        return modelAndView;
    }


    /**
     * 注册页面
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/reg",method =RequestMethod.GET )
    public ModelAndView reg(ModelAndView modelAndView){
        modelAndView.setViewName("/login-reg");
        return modelAndView;
    }

    /**
     * 注册信息提交
     * @param account
     * @return
     */
    public Map<String,String> regCommint(Account account) {
        HashMap<String, String> ret = new HashMap<>();
        if (account == null) {
            ret.put("type", "error");
            ret.put("msg", "请填写正确的客户信息");
            return ret;
        }
        if (StringUtils.isEmpty(account.getUsername())) {
            ret.put("type", "error");
            ret.put("msg", "客户名称不能为空");
            return ret;
        }
        if (StringUtils.isEmpty(account.getPassword())) {
            ret.put("type", "error");
            ret.put("msg", "用户密码不能为空");
            return ret;
        }
        if (isExist(account.getUsername(), (int) 0l)) {
            ret.put("type", "error");
            ret.put("msg", "客户名称已经存在");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "注册成功");
        return ret;

    }




    /**
     * 登录信息提交
     * @param account
     * @param
     * @return ret
     */
    public Map<String ,String> loginCommit(Account account) {
        HashMap<String, String> RETT = new HashMap<>();
        if (account == null) {
            RETT.put("type", "error");
            RETT.put("msg", "请填写正确的用户信息");
            return RETT;
        }
        if (StringUtils.isEmpty(account.getUsername())) {
            RETT.put("type", "error");
            RETT.put("msg", "用户名不能为空");
            return RETT;
        }
        if (StringUtils.isEmpty(account.getPassword())) {
            RETT.put("type", "error");
            RETT.put("msg", "用户密码不能为空");
            return RETT;
        }
        Account findByname = accountService.edit(account);
        if (findByname == null) {
            RETT.put("type", "error");
            RETT.put("msg", "用户名字不存在");
            return RETT;
        }
        RETT.put("msg", "登录成功！");
        return RETT;
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String Logout(HttpServletRequest request){
        request.getSession().getAttribute(null);
        return "redirect:login";
    }
    /**
     *
     * 修改个人信息
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> updateInfo(Account account,HttpServletRequest request) {
        HashMap<String, String> retMap = new HashMap<String, String>();
        if (account == null) {
            retMap.put("type", "error");
            retMap.put("msg", "请正确填写用户名");
            return retMap;
        }
        if (StringUtils.isEmpty(account.getUsername())) {
            retMap.put("type", "error");
            retMap.put("msg", "用户名不为空");
            return retMap;
        }
        Account accountLogin = (Account) request.getSession().getAttribute("account");
        if (isExist(account.getUsername(), accountLogin.getId())) {
            retMap.put("type", "error");
            retMap.put("msg", "该用户名已经存在！");
            return retMap;
        }
        accountLogin.setUsername(account.getUsername());
        accountLogin.setPassword(account.getPassword());
        accountLogin.setId(account.getId());
       //if (accountService.edit(accountLogin) <= 0) {
         //   retMap.put("type", "error");
         //   retMap.put("msg", "修改失败");
          //  return retMap;
       // }
        return retMap;

    }
    /**
     * 修改密码提交
     */
    @RequestMapping(value = "/updat",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String>updatPwd(String oldPassword,int newPassword,HttpServletRequest request){
        Map<String,String> PSW= new HashMap<String,String>();
        if (StringUtils.isEmpty(oldPassword)){
            PSW.put("type","error");
            PSW.put("msg","请填写原先密码");
            return PSW;
        }
        if (StringUtils.isEmpty(newPassword)){
            PSW.put("type","error");
            PSW.put("msg","请填写新的密码");
            return PSW;

        }
        Account accountlogin =(Account) request.getSession().getAttribute("account");
        if (!oldPassword.equals(accountlogin.getPassword())){
            PSW.put("type","error");
            PSW.put("msg","原密码不正确");
            return PSW;
        }
        accountlogin.setPassword(newPassword);
        if (accountService.editPassword(accountlogin)<=0){
            PSW.put("type","error");
            PSW.put("msg","密码修改失败");
            return PSW;
        }
        PSW.put("type","success");
        PSW.put("msg","密码修改成功");
        return PSW;


    }

    /**
     * 判断用户是否成功
     * @param username
     * @param i
     * @return
     */
    private boolean isExist(String username, int id) {
        Account account = accountService.findByAccountname(username);
        if (account == null)
            return false;
        return true;
    }}