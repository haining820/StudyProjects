package com.haining820.servlet;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-11
 * Time: 11:41
 */

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName GetServlet
 * @Description TODO
 */
public class GetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 设置resp的格式
        resp.setContentType("text/html;charset=utf-8");
        ServletContext context = this.getServletContext();
        // 读取ServletContext中的数据
        String username = (String) context.getAttribute("username");
        resp.getWriter().print("名字: " + username);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
