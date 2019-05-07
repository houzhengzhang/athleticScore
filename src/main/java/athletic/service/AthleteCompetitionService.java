package athletic.service;

import athletic.domain.AthleteCompetition;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 19:23
 * @ Description:
 */
public interface AthleteCompetitionService {
    List<AthleteCompetition> queryAthleteScore(String athleteId) throws SQLException;
}