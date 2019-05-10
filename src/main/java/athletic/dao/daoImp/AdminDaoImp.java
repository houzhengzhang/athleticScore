package athletic.dao.daoImp;

import athletic.dao.AdminDao;
import athletic.domain.Adminstrator;
import athletic.domain.Role;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/4 22:51
 * @ Description:
 */
public class AdminDaoImp implements AdminDao {
    @Override
    public Adminstrator adminstratorLogin(Adminstrator adminstrator) throws SQLException {
        String sql = "select * from adminstrator where email=? and password=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Adminstrator admin = queryRunner.query(sql, new BeanHandler<>(Adminstrator.class), adminstrator.getEmail(), adminstrator.getPassword());
        if(null==admin)
            return null;
        // 填充角色外键信息
        RoleDaoImp roleDaoImp = new RoleDaoImp();
        Role role = roleDaoImp.getRoleById(admin.getRoleId());
        admin.setRole(role);

        return admin;
    }
}
