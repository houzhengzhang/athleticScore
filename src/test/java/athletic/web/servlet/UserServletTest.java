package athletic.web.servlet;

import com.mockobjects.servlet.MockHttpServletRequest;
import com.mockobjects.servlet.MockHttpServletResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @ Date: 2019/5/5 19:43
 * @ Description:
 */
public class UserServletTest {
    private UserServlet userServlet;
    private MockHttpServletRequest  mockRequest;

    private MockHttpServletResponse mockResponse;


    @Before
    public void setUp() throws Exception {
        userServlet = new UserServlet();
        mockRequest = new MockHttpServletRequest();
        mockResponse = new MockHttpServletResponse();
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void login() {
        mockRequest.setupAddParameter("authority", "1");
        mockRequest.setupAddParameter("email", "976914833@qq.com");
        mockRequest.setupAddParameter("password", "123456");

        mockResponse.setExpectedContentType("text/html");

        try {
            userServlet.login(mockRequest, mockResponse);
//            System.out.println("ret:  " + ret);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}