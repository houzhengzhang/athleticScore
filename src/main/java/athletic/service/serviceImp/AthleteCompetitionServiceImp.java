package athletic.service.serviceImp;

import athletic.dao.daoImp.AthleteCompetitionDaoImp;
import athletic.domain.AthleteCompetition;
import athletic.service.AthleteCompetitionService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 19:23
 * @ Description:
 */
public class AthleteCompetitionServiceImp implements AthleteCompetitionService {
    private AthleteCompetitionDaoImp athleteCompetitionDaoImp = new AthleteCompetitionDaoImp();

    @Override
    public List<AthleteCompetition> queryAthleteScore(String athleteId) throws SQLException {
        return athleteCompetitionDaoImp.queryAthleteScore(athleteId);
    }

    @Override
    public int insert(AthleteCompetition athleteCompetition) throws SQLException {
        return athleteCompetitionDaoImp.insert(athleteCompetition);
    }
}
