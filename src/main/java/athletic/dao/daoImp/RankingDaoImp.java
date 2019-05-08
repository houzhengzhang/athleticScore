package athletic.dao.daoImp;

import athletic.dao.RankingDao;
import athletic.domain.Athlete;
import athletic.domain.Competition;
import athletic.domain.Ranking;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
    public List<Ranking> getRankingByAthleteId(String athleteId) throws SQLException {
        String sql = "selece * from ranking where athleteId=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        List<Ranking> rankingList = queryRunner.query(sql, new BeanListHandler<>(Ranking.class), athleteId);

        // 填充外键信息
        Athlete athlete = athleteDaoImp.getAthleteById(athleteId);
        for (Ranking ranking : rankingList) {
            ranking.setAthlete(athlete);
            Competition competition = competitionDaoImp.getCompetionById(ranking.getCompetitonId());
            ranking.setCompetition(competition);
        }
        return rankingList;
    }

    @Override
    public List<Ranking> getRankingByCompetitionId(String competitionId) throws SQLException {
        String sql = "selece * from ranking where competitionId=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        List<Ranking> rankingList = queryRunner.query(sql, new BeanListHandler<>(Ranking.class), competitionId);

        // 填充外键信息
        Competition competition = competitionDaoImp.getCompetionById(competitionId);
        for (Ranking ranking : rankingList) {
            Athlete athlete = athleteDaoImp.getAthleteById(ranking.getAthleteId());
            ranking.setAthlete(athlete);
            ranking.setCompetition(competition);
        }
        return rankingList;
    }
}