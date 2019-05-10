package athletic.service;

import athletic.domain.Athlete;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/7 13:55
 * @ Description:
 */
public interface AthleteService {
    Athlete athleteLogin(Athlete athlete) throws SQLException;
    int insert(Athlete athlete, String[] competitionIdList) throws SQLException;
}
