package athletic.dao;

import athletic.domain.AthleteCompetition;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 19:17
 * @ Description:
 */
public interface AthleteCompetitionDao {
    List<AthleteCompetition> queryAthleteScore(String athleteId) throws SQLException;

    int insert(AthleteCompetition athleteCompetition, Connection connection) throws SQLException;

    int update(AthleteCompetition athleteCompetition, Connection connection) throws SQLException;

    int insert(AthleteCompetition athleteCompetition) throws SQLException;
}
