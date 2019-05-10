package athletic.web.servlet;

import athletic.domain.Athlete;
import athletic.domain.AthleteCompetition;
import athletic.service.serviceImp.AthleteCompetitionServiceImp;
import athletic.service.serviceImp.AthleteServiceImp;
import athletic.utils.MyBeanUtils;
import athletic.utils.UUIDUtils;
import athletic.web.base.BaseServlet;
import org.json.JSONArray;
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
 * @ Date: 2019/5/5 19:41
 * @ Description:
 */
@WebServlet(name = "AthleteServlet", urlPatterns = "/AthleteServlet")
public class AthleteServlet extends BaseServlet {
    /**
     * 运动员登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject msg = new JSONObject();
        Athlete athlete = new Athlete();
        // 获取用户数据
        MyBeanUtils.populate(athlete, request.getParameterMap());

        // 调用业务层
        AthleteServiceImp athleteServiceImp = new AthleteServiceImp();

        try {
            athlete = athleteServiceImp.athleteLogin(athlete);
            if (null == athlete) {
                msg.put("status", 0);
                msg.put("msg", "用户名或密码错误！");
            } else {
                msg.put("status", 1);
                msg.put("msg", "登录成功！");
                msg.put("result", new org.json.JSONObject(athlete));
                msg.put("url", "http://localhost:8080/athletic/athletePage.html");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(msg.toString());
        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }

    /**
     * 添加运动员
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addAthlete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Athlete athlete = new Athlete();
        // 获取运动员信息
        MyBeanUtils.populate(athlete, request.getParameterMap());
        // 设置运动员ID
        athlete.setAthleteId(UUIDUtils.getId());

        // 获取参赛项目信息
        String[] competitionIdList = request.getParameterValues("competitionIdList");

        int num = 0;
        // 调用业务层插入数据
        AthleteServiceImp athleteServiceImp = new AthleteServiceImp();
        try {
            num = athleteServiceImp.insert(athlete, competitionIdList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JSONObject msg = new JSONObject();
        if (num > 0) {
            msg.put("status", 1);
            msg.put("msg", "插入运动员成功");
        } else {
            msg.put("status", 0);
            msg.put("msg", "插入运动员失败");
        }

        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }

    /**
     * 运动员查看分数
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getAthleteScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<AthleteCompetition> athleteCompetitionList = null;
        // 获取运动员ID
        String athleteId = request.getParameter("athleteId");
        // 调用业务层
        AthleteCompetitionServiceImp athleteCompetitionServiceImp = new AthleteCompetitionServiceImp();

        try {
            athleteCompetitionList = athleteCompetitionServiceImp.queryAthleteScore(athleteId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回JSON数据
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(athleteCompetitionList);

        PrintWriter out = response.getWriter();
        out.write(jsonArray.toString());
    }
}
