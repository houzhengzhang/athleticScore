package athletic.service.serviceImp;

import athletic.dao.daoImp.AthleteTeamDaoImp;
import athletic.domain.AthleteTeam;
import athletic.service.AthleteTeamService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 18:55
 * @ Description:
 */
public class AthleteTeamServiceImp implements AthleteTeamService {
    private AthleteTeamDaoImp athleteTeamDaoImp = new AthleteTeamDaoImp();
    @Override
    public int insert(AthleteTeam athleteTeam) throws SQLException {
        return athleteTeamDaoImp.insert(athleteTeam);
    }

    @Override
    public List<AthleteTeam> getAllAthleteTeam() throws SQLException {
        return athleteTeamDaoImp.getAllAthleteTeam();
    }

}
