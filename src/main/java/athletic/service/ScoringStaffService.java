package athletic.service;

import athletic.domain.AthleteCompetition;
import athletic.domain.ScoringStaff;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/8 14:14
 * @ Description:
 */
public interface ScoringStaffService {
    ScoringStaff scoringStaffLogin(ScoringStaff scoringStaff) throws SQLException;

    int updateAthleteScore(List<AthleteCompetition> athleteCompetitionList) throws SQLException;
}
