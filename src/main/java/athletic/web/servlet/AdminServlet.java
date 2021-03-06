package athletic.web.servlet;

import athletic.dao.daoImp.RoleDaoImp;
import athletic.domain.Adminstrator;
import athletic.domain.Role;
import athletic.service.serviceImp.AdminServiceImp;
import athletic.utils.MD5Util;
import athletic.utils.MyBeanUtils;
import athletic.web.base.BaseServlet;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @ Date: 2019/5/5 12:25
 * @ Description:
 */
@WebServlet(name = "AdminServlet", urlPatterns = "/AdminServlet")
public class AdminServlet extends BaseServlet {

    /**
     * 管理员登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject msg = new JSONObject();
        Adminstrator adminstrator = new Adminstrator();

        // 获取用户数据
        MyBeanUtils.populate(adminstrator, request.getParameterMap());
        // md5 加密密码
        adminstrator.setPassword(MD5Util.convertToMd5(adminstrator.getPassword()));
        AdminServiceImp adminServiceImp = new AdminServiceImp();
        try {
            adminstrator = adminServiceImp.adminstratorLogin(adminstrator);
            if (null == adminstrator) {
                msg.put("status", 0);
                msg.put("msg", "用户名或密码错误！");
            } else {
                msg.put("status", 1);
                msg.put("msg", "登录成功！");
                msg.put("result", new JSONObject(adminstrator));
                msg.put("url", "http://localhost:8080/athletic/administratorPage.html");

                // 用户登录成功将信息放入sessionzhong
                request.getSession().setAttribute("loginUser", adminstrator);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }

}
