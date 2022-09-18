package com.haining820.controller;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-07
 * Time: 14:08
 */

import com.haining820.pojo.User;
import com.haining820.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;


@Controller
public class UserController {

    @Resource
    UserService userService;

    public String getUser() {
        User user = userService.getUserById(1);
        return user.toString();
    }

}
