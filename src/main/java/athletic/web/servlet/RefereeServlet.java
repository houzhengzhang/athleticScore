package athletic.web.servlet;

import athletic.domain.Competition;
import athletic.domain.Referee;
import athletic.service.serviceImp.CompetitionServiceImp;
import athletic.service.serviceImp.RefereeServiceImp;
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
 * @ Date: 2019/5/5 20:00
 * @ Description:
 */
@WebServlet(name = "RefereeServlet", urlPatterns = "/RefereeServlet")
public class RefereeServlet extends BaseServlet {
    /**
     * 裁判员登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject msg = new JSONObject();
        Referee referee = new Referee();
        // 获取用户数据
        MyBeanUtils.populate(referee, request.getParameterMap());

        // md5 加密密码
        referee.setPassword(MD5Util.convertToMd5(referee.getPassword()));

        RefereeServiceImp refereeServiceImp = new RefereeServiceImp();
        try {
            referee = refereeServiceImp.refereeLogin(referee);
            if (null == referee) {
                msg.put("status", 0);
                msg.put("msg", "用户名或密码错误！");
            } else {
                msg.put("status", 1);
                msg.put("msg", "登录成功！");
                msg.put("result", new JSONObject(referee));
                msg.put("url", "http://localhost:8080/athletic/refereePage.html");
                // 用户登录成功将信息放入sessionzhong
                request.getSession().setAttribute("loginUser", referee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }

    /**
     * 修改项目状态
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateCompetitionState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject msg = new JSONObject();
        PrintWriter out = response.getWriter();

        Competition competition = new Competition();
        // 获取项目信息
        MyBeanUtils.populate(competition, request.getParameterMap());
        int num = 0;
        // 调用业务层
        CompetitionServiceImp competitionServiceImp = new CompetitionServiceImp();
        try {
            num = competitionServiceImp.update(competition);
        } catch (Exception e) {
//            e.printStackTrace();
            msg.put("status", 0);
            msg.put("msg", "计分员尚未录入初赛成绩，无法修改！");
            out.write(msg.toString());
            return;
        }
        if (num > 0) {
            msg.put("status", 1);
            msg.put("msg", "修改项目状态成功");
        } else {
            msg.put("status", 0);
            msg.put("msg", "修改项目状态失败");
        }
        out.write(msg.toString());
    }
}
