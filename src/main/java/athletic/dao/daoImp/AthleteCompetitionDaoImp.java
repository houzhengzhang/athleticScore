package athletic.dao.daoImp;

import athletic.dao.AthleteCompetitionDao;
import athletic.domain.AthleteCompetition;
import athletic.domain.Competition;
import athletic.domain.CompetitionStage;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 19:20
 * @ Description:
 */
public class AthleteCompetitionDaoImp implements AthleteCompetitionDao {
    @Override
    public List<AthleteCompetition> queryAthleteScore(String athleteId) throws SQLException {
        String sql = "select * from athletecompetition where athleteId=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

        CompetitionDaoImp competitionDaoImp = new CompetitionDaoImp();
        CompetitionStageDaoImp competitionStageDaoImp = new CompetitionStageDaoImp();
        // 查询运动员项目信息
        List<AthleteCompetition> athleteCompetitionList = queryRunner.query(sql, new BeanListHandler<>(AthleteCompetition.class), athleteId);

        // 查询补充外键信息
        for (AthleteCompetition athleteCompetition : athleteCompetitionList) {
            // 查询对应的项目信息
            Competition competition = competitionDaoImp.getCompetionById(athleteCompetition.getCompetitonId());
            athleteCompetition.setCompetition(competition);
            // 查询项目的阶段信息
            CompetitionStage competitionStage = competitionStageDaoImp.getCompetitionStageById(athleteCompetition.getCompetitonId());
            athleteCompetition.setCompetitionStage(competitionStage);
        }
        return athleteCompetitionList;
    }

    @Override
    public int insert(AthleteCompetition athleteCompetition) throws SQLException {
        String sql = "insert into athletecompetition values (?,?,?,?)";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] params = {athleteCompetition.getAthleteId(), athleteCompetition.getCompetitonId(),
                athleteCompetition.getCompetitionStageId(), athleteCompetition.getScore()};
        return queryRunner.update(sql, params);
    }

}
