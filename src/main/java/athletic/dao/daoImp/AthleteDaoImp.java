package athletic.dao.daoImp;

import athletic.dao.AthleteDao;
import athletic.domain.Athlete;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/7 14:00
 * @ Description:
 */
public class AthleteDaoImp implements AthleteDao {
    @Override
    public Athlete athleteLogin(Athlete athlete) throws SQLException {
        String sql = "select * from athlete where email=? and password=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanHandler<>(Athlete.class), athlete.getEmail(), athlete.getPassword());
    }

    @Override
    public void insert(Athlete athlete) throws SQLException {
        String sql = "insert into athlete values(?,?,?,?,?,?,?)";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] params={athlete.getAthleteId(),athlete.getEmail(),athlete.getPassword(),athlete.getName(),athlete.getSex(),
        athlete.getRoleId(),athlete.getAthleteTeamId()};
        queryRunner.update(sql, params);
    }
}
