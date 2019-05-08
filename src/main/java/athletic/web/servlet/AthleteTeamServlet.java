package athletic.web.servlet;

import athletic.domain.AthleteTeam;
import athletic.service.serviceImp.AthleteTeamServiceImp;
import athletic.utils.MyBeanUtils;
import athletic.utils.UUIDUtils;
import athletic.web.base.BaseServlet;
import net.sf.json.JSONArray;
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
 * @ Date: 2019/5/7 18:56
 * @ Description:
 */
@WebServlet(name = "AthleteTeamServlet", urlPatterns = "/AthleteTeamServlet")
public class AthleteTeamServlet extends BaseServlet {

    /**
     * 添加运动队
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addAthleteTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取运动队信息
        AthleteTeam athleteTeam = new AthleteTeam();
        MyBeanUtils.populate(athleteTeam, request.getParameterMap());
        // 设置运动队ID
        athleteTeam.setAthleteTeamId(UUIDUtils.getId());

        int num = 0;
        // 调用业务层
        AthleteTeamServiceImp athleteTeamServiceImp = new AthleteTeamServiceImp();
        try {
            num = athleteTeamServiceImp.insert(athleteTeam);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject msg = new JSONObject();
        if (num > 0) {
            msg.put("status", 1);
            msg.put("msg", "插入运动队成功");
        } else {
            msg.put("status", 0);
            msg.put("msg", "插入运动队失败");
        }

        PrintWriter out = response.getWriter();
        out.write(msg.toString());
    }

    // TODO 运动队排名
    public void getAllAthleteTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<AthleteTeam> athleteTeamList = null;
        // 调用业务层
        AthleteTeamServiceImp athleteTeamServiceImp = new AthleteTeamServiceImp();
        try {
            athleteTeamList = athleteTeamServiceImp.getAllAthleteTeam();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 返回JSON数组
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(athleteTeamList);

        PrintWriter out = response.getWriter();
        // 返回字符串
        out.write(jsonArray.toString());

    }
}
