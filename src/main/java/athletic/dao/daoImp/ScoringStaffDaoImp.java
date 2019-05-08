package athletic.dao.daoImp;

import athletic.dao.ScoringStaffDao;
import athletic.domain.Adminstrator;
import athletic.domain.Role;
import athletic.domain.ScoringStaff;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/8 14:10
 * @ Description:
 */
public class ScoringStaffDaoImp implements ScoringStaffDao {
    @Override
    public ScoringStaff scoringStaffLogin(ScoringStaff scoringStaff) throws SQLException {
        String sql = "select * from scoringstaff where email=? and password=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        ScoringStaff staff = queryRunner.query(sql, new BeanHandler<>(ScoringStaff.class), scoringStaff.getEmail(), scoringStaff.getPassword());

        // 填充角色外键信息
        RoleDaoImp roleDaoImp = new RoleDaoImp();
        Role role = roleDaoImp.getRoleById(staff.getRoleId());
        staff.setRole(role);

        return staff;
    }
}
