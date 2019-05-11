package athletic.web.servlet;

import athletic.web.base.BaseServlet;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ Date: 2019/5/5 19:33
 * @ Description:
 */
@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {
    private String[] dispUrl = {"123", "/AdminServlet?method=login", "/ScoringStaffServlet?method=login",
            "/RefereeServlet?method=login", "/AthleteServlet?method=login"};

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 首先判断验证码是否正确
        JSONObject msg = new JSONObject();
        // 获取验证码
        String code = request.getParameter("code");
        // false 若存在则返回该会话，否则返回null
//        HttpSession session = request.getSession();
//        System.out.println("login session id: " + session.getId());
//        System.out.println("session: " + session.getAttribute("verifyCode"));
//        if (null == code || "".equals(code) || !code.equals(session.getAttribute("verifyCode"))) {
//            // 验证码错误
//            msg.put("status", 0);
//            msg.put("msg", "验证码错误！");
//
//            // 返回错误信息
//            PrintWriter out = response.getWriter();
//            out.write(msg.toString());
//            return;
//        }
        // 根据用户选择角色进行请求分发
        int authority = Integer.parseInt(request.getParameter("authority"));
        request.getRequestDispatcher(dispUrl[authority]).forward(request, response);
    }
}
