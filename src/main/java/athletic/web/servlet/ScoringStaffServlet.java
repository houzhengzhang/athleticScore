package athletic.web.servlet;

import athletic.domain.AthleteCompetition;
import athletic.domain.ScoringStaff;
import athletic.service.AthleteCompetitionService;
import athletic.service.serviceImp.AthleteCompetitionServiceImp;
import athletic.service.serviceImp.ScoringStaffServiceImp;
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
import java.util.List;

/**
 * @ Date: 2019/5/5 20:01
 * @ Description:
 */
@WebServlet(name = "ScoringStaffServlet", urlPatterns = "/ScoringStaffServlet")
public class ScoringStaffServlet extends BaseServlet {
    /**
     * 计分员登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        org.json.JSONObject msg = new org.json.JSONObject();
        ScoringStaff scoringStaff = new ScoringStaff();
        // 获取用户数据
        MyBeanUtils.populate(scoringStaff, request.getParameterMap());
        ScoringStaffServiceImp scoringStaffServiceImp = new ScoringStaffServiceImp();
        try {
            scoringStaff = scoringStaffServiceImp.scoringStaffLogin(scoringStaff);
            if (null == scoringStaff) {
                msg.put("status", 0);
                msg.put("msg", "用户名或密码错误！");
            } else {
                msg.put("status", 1);
                msg.put("msg", "登录成功！");
                msg.put("result", new JSONObject(scoringStaff));
                msg.put("url", "http://localhost:8080/athletic/scoringstaffPage.html");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(msg.toString());
        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }

    /**
     * 录入运动员分数
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateAthleteScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取信息
        AthleteCompetition athleteCompetition = new AthleteCompetition();
        MyBeanUtils.populate(athleteCompetition, request.getParameterMap());

        int num = 0;
        // 调用业务层
        AthleteCompetitionServiceImp athleteCompetitionServiceImp = new AthleteCompetitionServiceImp();
        try {
            num = athleteCompetitionServiceImp.insert(athleteCompetition);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JSONObject msg = new JSONObject();
        if (num > 0) {
            msg.put("status", 1);
            msg.put("msg", "录入分数成功");
        } else {
            msg.put("status", 0);
            msg.put("msg", "录入分数失败");
        }

        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }

}
