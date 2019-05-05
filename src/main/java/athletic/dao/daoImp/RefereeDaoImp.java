package athletic.dao.daoImp;

import athletic.dao.RefereeDao;
import athletic.domain.Adminstrator;
import athletic.domain.Referee;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/4 22:51
 * @ Description:
 */
public class RefereeDaoImp implements RefereeDao {
    @Override
    public Referee refereeLogin(Referee referee) throws SQLException {
        String sql = "select * from adminstrator where email=? and password=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanHandler<>(Referee.class), referee.getEmail(), referee.getPassword());
    }
}
