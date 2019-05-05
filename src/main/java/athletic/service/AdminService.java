package athletic.service;

import athletic.domain.Adminstrator;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/5 16:57
 * @ Description:
 */
public interface AdminService {
    Adminstrator adminstratorLogin(Adminstrator adminstrator) throws SQLException;
}
