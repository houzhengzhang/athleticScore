package athletic.dao.daoImp;

import athletic.dao.AthleteDao;
import athletic.domain.Athlete;
import athletic.domain.Role;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 14:00
 * @ Description:
 */
public class AthleteDaoImp implements AthleteDao {
    @Override
    public Athlete athleteLogin(Athlete athlete) throws SQLException {
        String sql = "select * from athlete where email=? and password=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Athlete ath = queryRunner.query(sql, new BeanHandler<>(Athlete.class), athlete.getEmail(), athlete.getPassword());
        // 填充角色外键信息
        RoleDaoImp roleDaoImp = new RoleDaoImp();

        Role role = roleDaoImp.getRoleById(ath.getRoleId());
        ath.setRole(role);
        return ath;
    }

    @Override
    public int insert(Athlete athlete, Connection connection) throws SQLException {
        String sql = "insert into athlete values(?,?,?,?,?,?,?)";
        QueryRunner queryRunner = new QueryRunner();
        Object[] params = {athlete.getAthleteId(), athlete.getEmail(), athlete.getPassword(), athlete.getName(), athlete.getSex(),
                athlete.getRoleId(), athlete.getAthleteTeamId()};
        return queryRunner.update(connection, sql, params);
    }

    @Override
    public Athlete getAthleteById(String athleteId) throws SQLException {
        String sql = "select * from athlete where athleteId=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Athlete athlete = queryRunner.query(sql, new BeanHandler<>(Athlete.class), athleteId);

        // 填充角色外键信息
        RoleDaoImp roleDaoImp = new RoleDaoImp();
        Role role = roleDaoImp.getRoleById(athlete.getRoleId());
        athlete.setRole(role);
        return athlete;
    }

    @Override
    public List<Athlete> getAthleteByTeamId(String athleteTeamId) throws SQLException {
        String sql = "select * from athlete where athleteTeamId=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        List<Athlete> athleteList = queryRunner.query(sql, new BeanListHandler<>(Athlete.class), athleteTeamId);
        // 填充外键
        RoleDaoImp roleDaoImp = new RoleDaoImp();
        for (Athlete athlete : athleteList) {
            Role role = roleDaoImp.getRoleById(athlete.getRoleId());
            athlete.setRole(role);
        }
        return athleteList;
    }
}
