package athletic.dao.daoImp;

import athletic.domain.Adminstrator;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * @ Date: 2019/5/4 23:03
 * @ Description:
 */
public class AdminDaoImpTest {
    @Test
    public void testAdminLogin(){
        Adminstrator adminInput = new Adminstrator();
        adminInput.setEmail("976914833@qq.com");
        adminInput.setPassword("123456");

        try {
            AdminDaoImp adminDaoImp = new AdminDaoImp();
            Adminstrator adminQuery = adminDaoImp.adminstratorLogin(adminInput);
            System.out.println(adminQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}