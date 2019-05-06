package athletic.web.servlet;

import athletic.domain.CompetitionField;
import athletic.service.CompetitionFieldService;
import athletic.service.serviceImp.CompetitionFieldServiceImp;
import athletic.utils.MyBeanUtils;
import athletic.utils.UUIDUtils;
import athletic.web.base.BaseServlet;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @ Date: 2019/5/6 13:21
 * @ Description:
 */
@WebServlet(name = "ComFieldServlet", urlPatterns = "/ComFieldServlet")
public class ComFieldServlet extends BaseServlet {
    public void addCompetionField(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject msg = new JSONObject();
        CompetitionField competitionField = new CompetitionField();
        MyBeanUtils.populate(competitionField, request.getParameterMap());

        CompetitionFieldServiceImp competitionFieldServiceImp=new CompetitionFieldServiceImp();

        // 初始化参数
        competitionField.setFieldId(UUIDUtils.getId());
        competitionField.setState("空闲");
        try {
            competitionFieldServiceImp.insert(competitionField);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCompetionField(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject msg = new JSONObject();
        CompetitionField competitionField = new CompetitionField();
        MyBeanUtils.populate(competitionField, request.getParameterMap());

        CompetitionFieldServiceImp competitionFieldServiceImp=new CompetitionFieldServiceImp();

        // 初始化参数
        competitionField.setFieldId(UUIDUtils.getId());
        competitionField.setState("空闲");
        try {
            competitionFieldServiceImp.insert(competitionField);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
