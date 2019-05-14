package athletic.web.servlet;

import athletic.service.serviceImp.AthleteCompetitionServiceImp;
import athletic.web.base.BaseServlet;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
        String competitionStageId = request.getParameter("competitionStageId");
        JSONArray jsonArray = null;
        // 调用业务层
        AthleteCompetitionServiceImp athleteCompetitionServiceImp = new AthleteCompetitionServiceImp();
        try {
            jsonArray = athleteCompetitionServiceImp.getRankingByCompetitionId(competitionId, competitionStageId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回json数据
        org.json.JSONObject msg = new org.json.JSONObject();
        if (null != jsonArray) {
            msg.put("result", jsonArray);
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
