package athletic.dao;

import athletic.domain.AthleteTeam;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 18:48
 * @ Description:
 */
public interface AthleteTeamDao {
    void insert(AthleteTeam athleteTeam) throws SQLException;
    List<AthleteTeam> getAllAthleteTeam() throws SQLException;
}
