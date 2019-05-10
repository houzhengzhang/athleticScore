package athletic.web.servlet;

import athletic.domain.CompetitionField;
import athletic.service.serviceImp.CompetitionFieldServiceImp;
import athletic.utils.MyBeanUtils;
import athletic.utils.UUIDUtils;
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
@WebServlet(name = "CompetitionFieldServlet", urlPatterns = "/CompetitionFieldServlet")
public class CompetitionFieldServlet extends BaseServlet {
    /**
     * 添加项目场地
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addCompetitionField(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CompetitionField competitionField = new CompetitionField();
        MyBeanUtils.populate(competitionField, request.getParameterMap());
        CompetitionFieldServiceImp competitionFieldServiceImp = new CompetitionFieldServiceImp();
        // 初始化参数
        competitionField.setFieldId(UUIDUtils.getId());
        competitionField.setState("空闲");
        int num = 0;
        try {
            num = competitionFieldServiceImp.insert(competitionField);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject msg = new JSONObject();
        if (num > 0) {
            msg.put("status", 1);
            msg.put("msg", "插入项目场地成功");
        } else {
            msg.put("status", 0);
            msg.put("msg", "插入项目场地失败");
        }

        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }

    /**
     * 更新项目场地信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateCompetitionField(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CompetitionField competitionField = new CompetitionField();
        MyBeanUtils.populate(competitionField, request.getParameterMap());
        CompetitionFieldServiceImp competitionFieldServiceImp = new CompetitionFieldServiceImp();
        int num = 0;
        try {
            num = competitionFieldServiceImp.update(competitionField);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject msg = new JSONObject();
        if (num > 0) {
            msg.put("status", 1);
            msg.put("msg", "更新项目场地成功");
        } else {
            msg.put("status", 0);
            msg.put("msg", "更新项目场地失败");
        }

        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }

    /**
     * 通过id查询项目场地信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getCompetitionFieldById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String competitionFieldId = request.getParameter("competitionFieldId");
        CompetitionField competitionField = null;

        // 调用业务层
        CompetitionFieldServiceImp competitionFieldServiceImp = new CompetitionFieldServiceImp();
        try {
            competitionField = competitionFieldServiceImp.getCompetitionFieldById(competitionFieldId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JSONObject msg = new JSONObject();
        msg.put("result", competitionField);

        // 返回json数据
        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }

    /**
     * 查询所有项目场地信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getAllCompetitionField(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CompetitionField> competitionFieldList = null;

        // 调用业务层
        CompetitionFieldServiceImp competitionFieldServiceImp = new CompetitionFieldServiceImp();
        try {
            competitionFieldList = competitionFieldServiceImp.getAllCompetitionField();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JSONObject msg = new JSONObject();
        msg.put("result", competitionFieldList);
        msg.put("status", 1);
        msg.put("msg", "查询成功");

        // 返回json数据
        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }
}
