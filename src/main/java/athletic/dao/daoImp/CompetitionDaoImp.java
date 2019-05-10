package athletic.dao.daoImp;

import athletic.dao.CompetitionDao;
import athletic.domain.Competition;
import athletic.domain.CompetitionField;
import athletic.domain.CompetitionStage;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/6 21:58
 * @ Description:
 */
public class CompetitionDaoImp implements CompetitionDao {
    @Override
    public int insert(Competition competition) throws SQLException {
        String sql = "insert into competition values(?,?,?,?,?,?)";
        Object[] params = {competition.getCompetitionId(), competition.getCompetitionStageId(), competition.getName(),
                competition.getFieldId(), competition.getStartTime(), competition.getEndTime()};
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.update(sql, params);
    }

    @Override
    public int update(Competition competition, Connection connection) throws SQLException {
        String sql = "update competition set competitionStageId=?,name=?,fieldId=?,startTime=?,endTime=? where competitionId=?";
        Object[] params = {competition.getCompetitionStageId(), competition.getName(),
                competition.getFieldId(), competition.getStartTime(), competition.getEndTime(), competition.getCompetitionId()};
        QueryRunner queryRunner = new QueryRunner();
        return queryRunner.update(connection, sql, params);
    }

    @Override
    public Competition getCompetionById(String competionId) throws SQLException {
        String sql = "select * from competition where competitionId=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Competition competition = queryRunner.query(sql, new BeanHandler<>(Competition.class), competionId);

        // 查询外键对象
        CompetitionFieldDaoImp competitionFieldDaoImp = new CompetitionFieldDaoImp();
        CompetitionStageDaoImp competitionStageDaoImp = new CompetitionStageDaoImp();

        // 查询项目场地
        CompetitionField competitionField = competitionFieldDaoImp.getCompetitionFieldById(competition.getFieldId());
        competition.setCompetitionField(competitionField);
        // 查询项目阶段
        CompetitionStage competitionStage = competitionStageDaoImp.getCompetitionStageById(competition.getCompetitionStageId());
        competition.setCompetitionStage(competitionStage);
        return competition;
    }

    @Override
    public List<Competition> getAllCompetion() throws SQLException {
        String sql = "select * from competition";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        List<Competition> competitionList = queryRunner.query(sql, new BeanListHandler<>(Competition.class));
        // 查询外键对象
        CompetitionFieldDaoImp competitionFieldDaoImp = new CompetitionFieldDaoImp();
        CompetitionStageDaoImp competitionStageDaoImp = new CompetitionStageDaoImp();
        for (Competition competition : competitionList) {
            // 查询项目场地
            CompetitionField competitionField = competitionFieldDaoImp.getCompetitionFieldById(competition.getFieldId());
            competition.setCompetitionField(competitionField);
            // 查询项目阶段
            CompetitionStage competitionStage = competitionStageDaoImp.getCompetitionStageById(competition.getCompetitionStageId());
            competition.setCompetitionStage(competitionStage);
        }
        return competitionList;
    }
}
