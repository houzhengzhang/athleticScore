package athletic.service.serviceImp;

import athletic.dao.daoImp.RankingDaoImp;
import athletic.domain.Ranking;
import athletic.service.RankingService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/8 13:58
 * @ Description:
 */
public class RankingServiceImp implements RankingService {
    private RankingDaoImp rankingDaoImp = new RankingDaoImp();

    @Override
    public List<Ranking> getRankingByAthleteId(String athleteId, String competitionStageId) throws SQLException {
        return rankingDaoImp.getRankingByAthleteId(athleteId, competitionStageId);
    }

    @Override
    public List<Ranking> getRankingByCompetitionId(String competitionId, String competitionStageId) throws SQLException {
        return rankingDaoImp.getRankingByCompetitionId(competitionId, competitionStageId);
    }
}
