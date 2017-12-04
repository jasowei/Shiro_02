package com.lanou.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dllo on 17/12/4.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String home(){
        return "home";
    }

    //登录
    @RequestMapping(value = "/login")
    public String loginPage(){

        if (SecurityUtils.getSubject().isAuthenticated()){
            return "home";
        }

        return "login";
    }

    //表单验证
    @RequestMapping(value = "/loginsubmit")
    public String loginsubmit(HttpServletRequest request) throws Exception {
        //如果在shiroSpring的配置文件中, 配置了表单认证过滤器, 那么这个方法只需要处理哦异常信息
//        SecurityUtils.getSubject();

        String exClassName = (String) request.getAttribute("shiroLoginFailure");
        if (UnknownAccountException.class.getName().equals(exClassName)){
            throw new UnknownAccountException("账号不存在");
        }else if (IncorrectCredentialsException.class.getName().equals(exClassName)){
            throw new IncorrectCredentialsException("密码错误");
        }else {
            throw new Exception("未知错误");
        }

    }


    @RequiresRoles("CEO")
    @RequestMapping(value = "/test")
    public String test(){
        return "test";
    }


}
