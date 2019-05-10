package athletic.web.servlet;

import athletic.domain.Competition;
import athletic.service.serviceImp.CompetitionServiceImp;
import athletic.utils.MyBeanUtils;
import athletic.utils.UUIDUtils;
import athletic.web.base.BaseServlet;
import org.json.JSONObject;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/6 21:55
 * @ Description:
 */
@WebServlet(name = "CompetitionServlet", urlPatterns = "/CompetitionServlet")
public class CompetitionServlet extends BaseServlet {

    /**
     * 添加项目
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addCompetition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Competition competition = new Competition();
        MyBeanUtils.populate(competition, request.getParameterMap());
        // 初始化ID
        competition.setCompetitionId(UUIDUtils.getId());
        // 初始状态 初赛
        competition.setCompetitionStageId("B47507AE6987430E98BBE646D17350A8");
        int num = 0;

        CompetitionServiceImp competitionServiceImp = new CompetitionServiceImp();
        try {
            num = competitionServiceImp.insert(competition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject msg = new JSONObject();
        if (num > 0) {
            msg.put("status", 1);
            msg.put("msg", "添加项目成功");
        } else {
            msg.put("status", 0);
            msg.put("msg", "添加项目失败");
        }

        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }

    /**
     * 获取所有比赛
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getAllCompetition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Competition> competitionList = null;
        CompetitionServiceImp competitionServiceImp = new CompetitionServiceImp();
        try {
            competitionList = competitionServiceImp.getAllCompetion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject msg = new JSONObject();

        if (null != competitionList) {
            msg.put("result", competitionList);
            msg.put("status", 1);
            msg.put("msg", "查询成功");
        } else {
            msg.put("status", 0);
            msg.put("msg", "查询失败");
        }
        System.out.println(msg);

        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }

    /**
     * 更新项目信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateCompetition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Competition competition = new Competition();
        MyBeanUtils.populate(competition, request.getParameterMap());

        int num = 0;
        CompetitionServiceImp competitionServiceImp = new CompetitionServiceImp();
        try {
            num = competitionServiceImp.update(competition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject msg = new JSONObject();
        if (num > 0) {
            msg.put("status", 1);
            msg.put("msg", "更新项目成功");
        } else {
            msg.put("status", 0);
            msg.put("msg", "更新项目失败");

        }

        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }

}
