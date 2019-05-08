package athletic.dao;

import athletic.domain.Athlete;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 13:59
 * @ Description:
 */
public interface AthleteDao {
    Athlete athleteLogin(Athlete athlete) throws SQLException;

    int insert(Athlete athlete) throws SQLException;

    Athlete getAthleteById(String athleteId) throws SQLException;

    List<Athlete> getAthleteByTeamId(String athleteTeamId) throws SQLException;
}
