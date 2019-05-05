package athletic.web.servlet;

import athletic.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ Date: 2019/5/5 20:01
 * @ Description:
 */
@WebServlet(name = "ScoringStaffServlet", urlPatterns = "/ScoringStaffServlet")
public class ScoringStaffServlet extends BaseServlet {
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
