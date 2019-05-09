package athletic.dao.daoImp;

import athletic.dao.CompetitionStageDao;
import athletic.domain.CompetitionStage;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 10:16
 * @ Description:
 */
public class CompetitionStageDaoImp implements CompetitionStageDao {
    @Override
    public List<CompetitionStage> getAllStage() throws SQLException {
        String sql = "select * from competitionStage";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(CompetitionStage.class));
    }

    @Override
    public CompetitionStage getCompetitionStageById(String competitionId) throws SQLException {
        String sql = "select * from competitionStage where competitionStageId=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanHandler<>(CompetitionStage.class), competitionId);
    }
}
