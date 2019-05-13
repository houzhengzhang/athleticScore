package athletic.web.servlet;

import athletic.domain.Ranking;
import athletic.service.serviceImp.RankingServiceImp;
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
 * @ Date: 2019/5/8 14:01
 * @ Description:
 */
@WebServlet(name = "RankingServlet", urlPatterns = "/RankingServlet")
public class RankingServlet extends BaseServlet {

    /**
     * 通过运动员ID查询排名
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getRankingByAthleteId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String athleteId = request.getParameter("athleteId");
        // 项目阶段id
        String competitionStageId= request.getParameter("competitionStageId");
        List<Ranking> rankingList = null;
        // 调用业务层
        RankingServiceImp rankingServiceImp = new RankingServiceImp();
        try {
            rankingList = rankingServiceImp.getRankingByAthleteId(athleteId, competitionStageId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回json数据
        JSONObject msg = new JSONObject();
        if (null != rankingList) {
            msg.put("result", rankingList);
            msg.put("status", 1);
            msg.put("msg", "查询排名成功");
        } else {
            msg.put("status", 0);
            msg.put("msg", "查询排名失败");
        }

        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }

    /**
     * 通过项目ID查询排名
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getRankingByCompetitionId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String competitionId = request.getParameter("competitionId");
        // 项目阶段id
        String competitionStageId= request.getParameter("competitionStageId");
        List<Ranking> rankingList = null;
        // 调用业务层
        RankingServiceImp rankingServiceImp = new RankingServiceImp();
        try {
            rankingList = rankingServiceImp.getRankingByCompetitionId(competitionId, competitionStageId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回json数据
        JSONObject msg = new JSONObject();
        if (null != rankingList) {
            msg.put("result", rankingList);
            msg.put("status", 1);
            msg.put("msg", "查询排名成功");
        } else {
            msg.put("status", 0);
            msg.put("msg", "查询排名失败");
        }
        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }
}
