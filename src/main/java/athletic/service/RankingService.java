package athletic.service;

import athletic.domain.Ranking;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/8 13:58
 * @ Description:
 */
public interface RankingService {
    List<Ranking> getRankingByAthleteId(String athleteId, String competitionStageId) throws SQLException;

    List<Ranking> getRankingByCompetitionId(String competitionId, String competitionStageId) throws SQLException;
}
