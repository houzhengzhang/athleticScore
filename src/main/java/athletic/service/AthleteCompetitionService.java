package athletic.service;

import athletic.domain.AthleteCompetition;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 19:23
 * @ Description:
 */
public interface AthleteCompetitionService {
    List<AthleteCompetition> queryAthleteScore(String athleteId) throws SQLException;

    List<AthleteCompetition> getAllAthleteScore(String copetitionId) throws SQLException;

    int insert(AthleteCompetition athleteCompetition, Connection connection) throws SQLException;

    int insert(AthleteCompetition athleteCompetition) throws SQLException;

    int updateAthleteScore(AthleteCompetition athleteCompetition) throws SQLException;
}
