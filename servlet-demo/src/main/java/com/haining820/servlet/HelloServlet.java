package com.haining820.servlet;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-11
 * Time: 11:34
 */

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ServletTest
 * @Description TODO
 */
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        // web容器在启动的时候,它会为每个web程序都创建一个对应的ServletContext对象,它代表了当前的web应用
        ServletContext context = this.getServletContext();
        // 将一个数据以键值对的形式保存在了ServletContext中
        context.setAttribute("username","yuhaiyang");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}
