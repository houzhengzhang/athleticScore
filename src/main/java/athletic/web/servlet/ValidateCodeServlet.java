package athletic.web.servlet;

import athletic.web.base.BaseServlet;
import athletic.utils.CodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ Date: 2018/11/28 21:29
 * @ Description:
 */
@WebServlet(name = "ValidateCodeServlet", urlPatterns = "/ValidateCodeServlet")
public class ValidateCodeServlet extends BaseServlet {
    private static final long serialVersionUID = -7952895445913967317L;
    // src ValidateCodeServlet?method=getValidateCode
    public void getValidateCode(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = CodeUtil.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession(true);
        //删除以前的
        session.removeAttribute("verifyCode");
        session.setAttribute("verifyCode", verifyCode.toLowerCase());
        //生成图片
        int w = 100, h = 30;
        CodeUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
    }
}
