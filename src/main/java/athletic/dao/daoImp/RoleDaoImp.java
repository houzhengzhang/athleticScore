package athletic.dao.daoImp;

import athletic.dao.RoleDao;
import athletic.domain.Role;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/5 17:17
 * @ Description:
 */
public class RoleDaoImp implements RoleDao {
    @Override
    public Role queryRole(String rid) throws SQLException {
        String sql = "select * from role where roleId=?";
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        Object[] params={rid};
        return queryRunner.query(sql, new BeanHandler<>(Role.class), params);
    }
}
