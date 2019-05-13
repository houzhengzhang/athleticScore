package athletic.dao.daoImp;

import athletic.dao.AthleteCompetitionDao;
import athletic.domain.Athlete;
import athletic.domain.AthleteCompetition;
import athletic.domain.Competition;
import athletic.domain.CompetitionStage;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 19:20
 * @ Description:
 */
public class AthleteCompetitionDaoImp implements AthleteCompetitionDao {
    @Override
    public List<AthleteCompetition> queryAthleteScoreById(String athleteId) throws SQLException {
        String sql = "select * from athletecompetition where athleteId=? and score!=0";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

        CompetitionDaoImp competitionDaoImp = new CompetitionDaoImp();
        CompetitionStageDaoImp competitionStageDaoImp = new CompetitionStageDaoImp();
        // 查询运动员项目信息
        List<AthleteCompetition> athleteCompetitionList = queryRunner.query(sql, new BeanListHandler<>(AthleteCompetition.class), athleteId);

        // 查询补充外键信息
        for (AthleteCompetition athleteCompetition : athleteCompetitionList) {
            // 查询对应的项目信息
            Competition competition = competitionDaoImp.getCompetionById(athleteCompetition.getCompetitionId());
            athleteCompetition.setCompetition(competition);
            // 查询项目的阶段信息
            CompetitionStage competitionStage = competitionStageDaoImp.getCompetitionStageById(athleteCompetition.getCompetitionStageId());
            athleteCompetition.setCompetitionStage(competitionStage);
        }
        return athleteCompetitionList;
    }

    @Override
    public int insert(AthleteCompetition athleteCompetition, Connection connection) throws SQLException {
        String sql = "insert into athletecompetition values (?,?,?,?)";
        QueryRunner queryRunner = new QueryRunner();
        Object[] params = {athleteCompetition.getAthleteId(), athleteCompetition.getCompetitionId(),
                athleteCompetition.getCompetitionStageId(), athleteCompetition.getScore()};
        return queryRunner.update(connection, sql, params);
    }

    @Override
    public int update(AthleteCompetition athleteCompetition, Connection connection) throws SQLException {
        String sql = "update athletecompetition set score=? where athleteId=? && competitionId=? && competitionStageId=?";
        QueryRunner queryRunner = new QueryRunner();
        Object[] params = {athleteCompetition.getScore(), athleteCompetition.getAthleteId(),
                athleteCompetition.getCompetitionId(), athleteCompetition.getCompetitionStageId()};
        return queryRunner.update(connection, sql, params);
    }

    @Override
    public int update(AthleteCompetition athleteCompetition) throws SQLException {
        String sql = "update athletecompetition set score=? where athleteId=? && competitionId=? && competitionStageId=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] params = {athleteCompetition.getScore(), athleteCompetition.getAthleteId(),
                athleteCompetition.getCompetitionId(), athleteCompetition.getCompetitionStageId()};
        return queryRunner.update(sql, params);
    }

    @Override
    public int insert(AthleteCompetition athleteCompetition) throws SQLException {
        String sql = "insert into athletecompetition values (?,?,?,?)";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] params = {athleteCompetition.getAthleteId(), athleteCompetition.getCompetitionId(),
                athleteCompetition.getCompetitionStageId(), athleteCompetition.getScore()};
        return queryRunner.update(sql, params);
    }

    @Override
    public List<AthleteCompetition> queryAthleteScoreByCond(String competitionId, String competitionStageId) throws SQLException {
        String sql = "select * from athletecompetition where competitionId=? && competitionStageId=? and score!=0 order by score desc";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(AthleteCompetition.class), competitionId, competitionStageId);
    }

    @Override
    public List<AthleteCompetition> getAllAthleteScore(String copetitionId) throws SQLException {
        String sql = "select * from athletecompetition where competitionId=? and score=0";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

        AthleteDaoImp athleteDaoImp = new AthleteDaoImp();
        CompetitionDaoImp competitionDaoImp = new CompetitionDaoImp();
        CompetitionStageDaoImp competitionStageDaoImp = new CompetitionStageDaoImp();
        // 查询运动员项目信息
        List<AthleteCompetition> athleteCompetitionList = queryRunner.query(sql,
                new BeanListHandler<>(AthleteCompetition.class), copetitionId);

        // 查询补充外键信息
        for (AthleteCompetition athleteCompetition : athleteCompetitionList) {
            // 查询对应的项目信息
            Competition competition = competitionDaoImp.getCompetionById(athleteCompetition.getCompetitionId());
            athleteCompetition.setCompetition(competition);
            // 查询项目的阶段信息
            CompetitionStage competitionStage = competitionStageDaoImp.getCompetitionStageById(athleteCompetition.getCompetitionId());
            athleteCompetition.setCompetitionStage(competitionStage);

            // 查询运动员信息
            Athlete athlete = athleteDaoImp.getAthleteById(athleteCompetition.getAthleteId());
            athleteCompetition.setAthlete(athlete);
        }
        return athleteCompetitionList;
    }

    @Override
    public int getAthleteRanking(String athleteId, String competitionId, String competitionStageId) throws SQLException {
        String sql = "select rk.ranking from (SELECT *,(@i:=@i + 1) AS ranking FROM athletecompetition,(SELECT @i:= 0) AS it " +
                "where competitionId=? && competitionStageId =? ORDER BY score desc) as rk where athleteId=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        double rank = (double) queryRunner.query(sql, new ScalarHandler<>(), competitionId, competitionStageId, athleteId);
        return (int) rank;
    }

    @Override
    public boolean isAthleteScoredById(String competitionId, String competitionStageId) throws SQLException {
        String sql = "select count(*) from athletecompetition where competitionId=? && competitionStageId=? and score=0";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

        Long num = (Long) queryRunner.query(sql, new ScalarHandler<>(), competitionId, competitionStageId);
        return num.intValue() == 0;
    }

    @Override
    public List<AthleteCompetition> getRankingByCompetitionId(String competitionId, String competitionStageId) throws SQLException {
        String sql = "SELECT *,(@i:=@i + 1) AS ranking FROM athletecompetition,(SELECT @i:= 0 init) AS it " +
                "where competitionId=? && competitionStageId =? ORDER BY score desc";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(AthleteCompetition.class), competitionId, competitionStageId);
    }
}
