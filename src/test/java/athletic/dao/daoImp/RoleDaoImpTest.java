package athletic.dao.daoImp;

import org.junit.Test;

import athletic.domain.Role;
import java.sql.SQLException;
import static org.junit.Assert.*;

/**
 * @ Date: 2019/5/5 17:25
 * @ Description:
 */
public class RoleDaoImpTest {

    @Test
    public void queryRole() {
        RoleDaoImp roleDaoImp = new RoleDaoImp();

        try {
            Role role = roleDaoImp.queryRole("F10079D3840F4411A324B8DF7483FE3F");
            System.out.println(role);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}