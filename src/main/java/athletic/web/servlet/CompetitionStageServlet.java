package athletic.web.servlet;

import athletic.domain.CompetitionStage;
import athletic.service.serviceImp.CompetitionStageServiceImp;
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
 * @ Date: 2019/5/6 13:21
 * @ Description:
 */
@WebServlet(name = "CompetitionStageServlet", urlPatterns = "/CompetitionStageServlet")
public class CompetitionStageServlet extends BaseServlet {
    /**
     * 查询所有项目状态
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getAllCompetitionStage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CompetitionStageServiceImp competitionStageServiceImp = new CompetitionStageServiceImp();
        List<CompetitionStage> competitionStageList = null;
        try {
            competitionStageList = competitionStageServiceImp.getAllStage();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        JSONObject msg = new JSONObject();
        msg.put("result", competitionStageList);

        out.write(msg.toString());
    }

}
