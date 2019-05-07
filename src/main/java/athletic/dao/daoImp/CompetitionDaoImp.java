package athletic.dao.daoImp;

import athletic.dao.CompetitionDao;
import athletic.domain.Competition;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/6 21:58
 * @ Description:
 */
public class CompetitionDaoImp implements CompetitionDao {
    @Override
    public void insert(Competition competition) throws SQLException {
        String sql = "insert into competition values(?,?,?,?,?,?)";
        Object[] params = {competition.getCompetitionId(), competition.getCompetitionStageId(), competition.getName(),
                competition.getFieldId(), competition.getStartTime(), competition.getEndTime()};
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        queryRunner.update(sql, params);
    }

    @Override
    public void update(Competition competition) throws SQLException {
        String sql = "update competition set stageId=?,name=?,fieldId=?,startTime=?,endTime=? where competitionId=?";
        Object[] params = {competition.getCompetitionStageId(), competition.getName(),
                competition.getFieldId(), competition.getStartTime(), competition.getEndTime(), competition.getCompetitionId()};
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        queryRunner.update(sql, params);
    }

    @Override
    public Competition getCompetionById(String competionId) throws SQLException {
        String sql = "select * from competition where competitionId=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanHandler<>(Competition.class), competionId);
    }
}
