package athletic.web.servlet;

import athletic.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ Date: 2019/5/5 19:41
 * @ Description:
 */
@WebServlet(name = "AthleteServlet", urlPatterns = "/AthleteServlet")
public class AthleteServlet extends BaseServlet {
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
