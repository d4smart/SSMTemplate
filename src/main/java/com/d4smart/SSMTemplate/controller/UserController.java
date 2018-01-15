package com.d4smart.SSMTemplate.controller;

import com.d4smart.SSMTemplate.common.Const;
import com.d4smart.SSMTemplate.common.ServerResponse;
import com.d4smart.SSMTemplate.pojo.User;
import com.d4smart.SSMTemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by d4smart on 2017/12/9 16:38
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ServerResponse<String> register(User user) {
        return userService.register(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ServerResponse<User> login(User user, HttpSession session) {
        ServerResponse<User> serverResponse = userService.login(user);
        if (serverResponse.isSuccess()) {
            session.setAttribute(Const.LOGIN_USER, serverResponse.getData());
        }

        return serverResponse;
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        return ServerResponse.createBySuccess(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ServerResponse<User> update(User user, HttpSession session) {
        User loginUser = (User) session.getAttribute(Const.LOGIN_USER);

        user.setId(loginUser.getId());
        ServerResponse<User> serverResponse = userService.update(user);
        if (serverResponse.isSuccess()) {
            session.setAttribute(Const.LOGIN_USER, serverResponse.getData());
        }

        return serverResponse;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.LOGIN_USER);
        return ServerResponse.createBySuccess("退出登录成功");
    }
}
