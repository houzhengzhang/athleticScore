package athletic.web.servlet;

import athletic.domain.AthleteCompetition;
import athletic.domain.AthleteTeam;
import athletic.domain.Ranking;
import athletic.service.serviceImp.AthleteCompetitionServiceImp;
import athletic.service.serviceImp.RankingServiceImp;
import athletic.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/13 20:23
 * @ Description:
 */
@WebServlet(name = "AthleteCompetitionServlet", urlPatterns = "/AthleteCompetitionServlet")
public class AthleteCompetitionServlet extends BaseServlet {
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

        System.out.println(competitionId);
        System.out.println(competitionStageId);
        List<AthleteCompetition> athleteCompetitionList = null;
        // 调用业务层
        AthleteCompetitionServiceImp athleteCompetitionServiceImp = new AthleteCompetitionServiceImp();
        try {
            athleteCompetitionList = athleteCompetitionServiceImp.getRankingByCompetitionId(competitionId, competitionStageId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回json数据
        org.json.JSONObject msg = new org.json.JSONObject();
        if (null != athleteCompetitionList) {
            msg.put("result", athleteCompetitionList);
            msg.put("status", 1);
            msg.put("msg", "查询排名成功");
        } else {
            msg.put("status", 0);
            msg.put("msg", "查询排名失败");
        }
        System.out.println(msg);
        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }




}
