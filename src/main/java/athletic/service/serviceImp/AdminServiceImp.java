package athletic.service.serviceImp;

import athletic.dao.daoImp.AdminDaoImp;
import athletic.domain.Adminstrator;
import athletic.service.AdminService;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/5 16:58
 * @ Description:
 */
public class AdminServiceImp implements AdminService {
    @Override
    public Adminstrator adminstratorLogin(Adminstrator adminstrator) throws SQLException {
        AdminDaoImp adminDaoImp = new AdminDaoImp();
        return adminDaoImp.adminstratorLogin(adminstrator);
    }
}
