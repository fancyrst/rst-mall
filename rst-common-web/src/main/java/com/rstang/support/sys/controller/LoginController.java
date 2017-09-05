package com.rstang.support.sys.controller;

import com.rstang.core.config.GlobalConfig;
import com.rstang.core.web.BaseController;
import com.rstang.util.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yeyx on 2017/9/4.
 */
@Controller
public class LoginController extends BaseController {

    /**
     * 管理登录
     */
    @RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {

//        Principal principal = UserUtils.getPrincipal();

        // 如果已登录，再次访问主页，则退出原账号。
        if (GlobalConfig.TRUE.equals(GlobalConfig.getConfig("notAllowRefreshIndex"))){
            CookieUtils.setCookie(response, "LOGINED", "false");
        }

        // 如果已经登录，则跳转到管理首页
//        if(principal != null && !principal.isMobileLogin()){
//            return "redirect:" + adminPath;
//        }
        return "sys/sysLogin";
    }

}
