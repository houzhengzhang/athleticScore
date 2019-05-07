package athletic.web.servlet;

import athletic.domain.CompetitionStage;
import athletic.service.serviceImp.CompetitionStageServiceImp;
import athletic.web.base.BaseServlet;
import net.sf.json.JSONArray;

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
@WebServlet(name = "ComStageServlet", urlPatterns = "/ComStageServlet")
public class ComStageServlet extends BaseServlet {
    public void getAllComStage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CompetitionStageServiceImp competitionStageServiceImp = new CompetitionStageServiceImp();
        List<CompetitionStage> competitionStageList=null;
        try {
            competitionStageList = competitionStageServiceImp.getAllStage();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(competitionStageList);

        System.out.println("ComStageServlet:  " + jsonArray);
        out.write(jsonArray.toString());
    }

}
