package athletic.dao;

import athletic.domain.Adminstrator;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/4 22:49
 * @ Description:
 */
public interface AdminDao {
    Adminstrator adminstratorLogin(Adminstrator adminstrator) throws SQLException;
}
