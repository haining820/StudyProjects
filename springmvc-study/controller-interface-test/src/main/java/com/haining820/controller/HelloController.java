package com.haining820.controller;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-12
 * Time: 12:53
 */

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName HelloController
 * @Description TODO
 */
public class HelloController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 模型和视图
        ModelAndView mv = new ModelAndView();
        // 封装对象，放在ModelAndView中
        mv.addObject("msg","Hello, SpringMVC!");
        // 封装要跳转的视图，放在ModelAndView中
        // 会自动加上前缀后缀拼接成 /WEB-INF/jsp/hello.jsp
        mv.setViewName("hello");
        return mv;
    }
}
