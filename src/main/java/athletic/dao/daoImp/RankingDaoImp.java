package athletic.dao.daoImp;

import athletic.dao.RankingDao;
import athletic.domain.Athlete;
import athletic.domain.Competition;
import athletic.domain.Ranking;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/8 13:46
 * @ Description:
 */
public class RankingDaoImp implements RankingDao {
    private AthleteDaoImp athleteDaoImp = new AthleteDaoImp();
    private CompetitionDaoImp competitionDaoImp = new CompetitionDaoImp();

    @Override
    public List<Ranking> getRankingById(String athleteId) throws SQLException {
        String sql = "select ranking from ranking where athleteId=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(Ranking.class), athleteId);
    }

    @Override
    public List<Ranking> getRankingByCompetitionId(String competitionId, String competitionStageId) throws SQLException {
        String sql = "select * from ranking where competitionId=? and competitionStageId=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        List<Ranking> rankingList = queryRunner.query(sql, new BeanListHandler<>(Ranking.class), competitionId, competitionStageId);

        // 填充外键信息
        Competition competition = competitionDaoImp.getCompetionById(competitionId);
        for (Ranking ranking : rankingList) {
            Athlete athlete = athleteDaoImp.getAthleteById(ranking.getAthleteId());
            ranking.setAthlete(athlete);
            ranking.setCompetition(competition);
        }
        return rankingList;
    }

    @Override
    public int insert(Ranking ranking, Connection connection) throws SQLException {
        String sql = "insert into ranking values(?,?,?,?)";
        QueryRunner queryRunner = new QueryRunner();
        Object[] params = {ranking.getRankingId(), ranking.getCompetitonId(), ranking.getAthleteId(), ranking.getRanking()};
        return queryRunner.update(connection, sql, params);
    }
}
