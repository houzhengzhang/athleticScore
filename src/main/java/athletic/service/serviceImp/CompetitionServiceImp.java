package athletic.service.serviceImp;

import athletic.dao.daoImp.CompetitionDaoImp;
import athletic.domain.Competition;
import athletic.service.CompetitionService;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/6 22:02
 * @ Description:
 */
public class CompetitionServiceImp implements CompetitionService {
    private CompetitionDaoImp competitionDaoImp = new CompetitionDaoImp();

    @Override
    public int insert(Competition competition) throws SQLException {
        return competitionDaoImp.insert(competition);
    }

    @Override
    public int update(Competition competition) throws SQLException {
        return competitionDaoImp.update(competition);
    }
}
