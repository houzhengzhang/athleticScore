package athletic.dao.daoImp;

import athletic.dao.AthleteTeamDao;
import athletic.domain.AthleteTeam;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 18:50
 * @ Description:
 */
public class AthleteTeamDaoImp implements AthleteTeamDao {
    @Override
    public int insert(AthleteTeam athleteTeam) throws SQLException {
        String sql = "insert into athleteteam values(?,?,?,?)";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.update(sql, athleteTeam.getAthleteTeamId(), athleteTeam.getName(), athleteTeam.getSchool(), athleteTeam.getTotalPoint());
    }

    @Override
    public List<AthleteTeam> getAllAthleteTeam() throws SQLException {
        String sql = "select * from athleteteam";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(AthleteTeam.class));
    }

    @Override
    public AthleteTeam getAthleteTeamById(String athleteTeamId) throws SQLException {
        String sql = "select * from athleteteam where athleteTeamId=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanHandler<>(AthleteTeam.class), athleteTeamId);
    }
}
