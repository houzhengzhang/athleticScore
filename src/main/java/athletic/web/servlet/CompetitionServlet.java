package athletic.web.servlet;

import athletic.domain.Competition;
import athletic.service.serviceImp.CompetitionServiceImp;
import athletic.utils.MyBeanUtils;
import athletic.utils.UUIDUtils;
import athletic.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


/**
 * @ Date: 2019/5/6 21:55
 * @ Description:
 */
@WebServlet(name = "CompetitionServlet", urlPatterns = "/CompetitionServlet")
public class CompetitionServlet extends BaseServlet {
    public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Competition competition = new Competition();
        MyBeanUtils.populate(competition, request.getParameterMap());
        // 初始化ID
        competition.setCompetitionId(UUIDUtils.getId());

        CompetitionServiceImp competitionServiceImp = new CompetitionServiceImp();
        try {
            competitionServiceImp.insert(competition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Competition competition = new Competition();
        MyBeanUtils.populate(competition, request.getParameterMap());
        CompetitionServiceImp competitionServiceImp = new CompetitionServiceImp();
        try {
            competitionServiceImp.update(competition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
