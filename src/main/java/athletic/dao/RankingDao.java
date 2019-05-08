package athletic.dao;

import athletic.domain.Ranking;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/8 13:44
 * @ Description:
 */
public interface RankingDao {
    List<Ranking> getRankingByAthleteId(String athleteId) throws SQLException;

    List<Ranking> getRankingByCompetitionId(String competitionId) throws SQLException;
}
