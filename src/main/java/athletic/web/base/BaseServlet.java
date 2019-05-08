package athletic.web.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 *
 */
public class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 2918567887708656503L;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        if (null == method || "".equals(method) || method.trim().equals("")) {
            method = "execute";
        }

        // 获取子类 字节码对象
        Class clazz = getClass();

        try {
            // 查找子类对象对应的字节码中的名称为method的方法
            Method md = clazz.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            if (null != md) {
                String jspPath = (String) md.invoke(this, req, resp);
//                if (null != jspPath) {
//                    req.getRequestDispatcher(jspPath).forward(req, resp);
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 默认方法
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return null;
    }

}
