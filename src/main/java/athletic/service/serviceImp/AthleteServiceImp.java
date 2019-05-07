package athletic.service.serviceImp;

import athletic.dao.daoImp.AthleteDaoImp;
import athletic.domain.Athlete;
import athletic.service.AthleteService;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/7 13:56
 * @ Description:
 */
public class AthleteServiceImp implements AthleteService {
    private AthleteDaoImp athleteDaoImp = new AthleteDaoImp();

    @Override
    public Athlete athleteLogin(Athlete athlete) throws SQLException {
        return athleteDaoImp.athleteLogin(athlete);
    }

    @Override
    public void insert(Athlete athlete) throws SQLException {
        athleteDaoImp.insert(athlete);
    }
}
