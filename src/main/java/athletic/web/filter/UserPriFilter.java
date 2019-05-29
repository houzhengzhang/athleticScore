package athletic.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ Date: 2018/11/24 15:23
 * @ Description:
 */
@WebFilter(filterName = "UserPriFilter", urlPatterns = {"/administratorPage.html", "/refereePage.html",
        "/athletePage.html", "/scoringstaffPage.html"})
public class UserPriFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //过滤的开始拦截
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 判断session是否有用户登录
        Object user = request.getSession().getAttribute("loginUser");
        if (null != user) {
            // 登录则放行
            chain.doFilter(request, response);
        } else {
            // 未登录则提示 跳转页面
            response.sendRedirect("/athletic");
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

}
