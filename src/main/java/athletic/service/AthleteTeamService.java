package athletic.service;

import athletic.domain.AthleteTeam;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 18:54
 * @ Description:
 */
public interface AthleteTeamService {
    int insert(AthleteTeam athleteTeam) throws SQLException;

    List<AthleteTeam> getAllAthleteTeam() throws SQLException;

}
