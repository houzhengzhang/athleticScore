package athletic.web.servlet;

import athletic.domain.Adminstrator;
import athletic.domain.Referee;
import athletic.service.serviceImp.AdminServiceImp;
import athletic.service.serviceImp.RefereeServiceImp;
import athletic.utils.MyBeanUtils;
import athletic.web.base.BaseServlet;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @ Date: 2019/5/5 20:00
 * @ Description:
 */
@WebServlet(name = "RefereeServlet", urlPatterns = "/RefereeServlet")
public class RefereeServlet extends BaseServlet {
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject msg = new JSONObject();
        Referee referee = new Referee();
        // 获取用户数据
        MyBeanUtils.populate(referee, request.getParameterMap());
        RefereeServiceImp refereeServiceImp = new RefereeServiceImp();
        try {
            referee = refereeServiceImp.refereeLogin(referee);
            if (null == referee) {
                msg.put("status", 0);
                msg.put("msg", "用户名或密码错误！");
            } else {
                // TODO 查询该用户的权限
//                RoleDaoImp roleDaoImp = new RoleDaoImp();
//                Role role = roleDaoImp.queryRole(adminstrator.getRoleId());
//                System.out.println("rols:   " + role);
                msg.put("status", 1);
                msg.put("msg", "登录成功！");
                msg.put("result", new JSONObject(referee));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(msg.toString());
        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }
}
