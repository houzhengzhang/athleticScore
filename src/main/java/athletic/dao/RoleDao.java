package athletic.dao;

import athletic.domain.Role;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/5 17:16
 * @ Description:
 */
public interface RoleDao {
    Role queryRole(String rid) throws SQLException;
}
