package athletic.web.servlet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mockobjects.servlet.*;
import com.mockobjects.*;
import com.mockobjects.Verifiable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @ Date: 2019/5/5 12:31
 * @ Description:
 */
public class AdminServletTest {
    private AdminServlet adminServlet;
    private MockHttpServletRequest  mockRequest;

    private MockHttpServletResponse mockResponse;


    @Before
    public void setUp() throws Exception {

        adminServlet = new AdminServlet();
        mockRequest = new MockHttpServletRequest();
        mockResponse = new MockHttpServletResponse();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void login() {
        mockRequest.setupAddParameter("code", "ab32");
        mockRequest.setupAddParameter("email", "976914833@qq.com");
        mockRequest.setupAddParameter("password", "123456");

        mockResponse.setExpectedContentType("text/html");

        try {
            adminServlet.login(mockRequest, mockResponse);
//            System.out.println("ret:  " + ret);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        mockResponse.verify();

    }
}