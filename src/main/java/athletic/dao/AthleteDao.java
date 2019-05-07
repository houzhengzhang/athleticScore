package athletic.dao;

import athletic.domain.Athlete;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/7 13:59
 * @ Description:
 */
public interface AthleteDao {
    Athlete athleteLogin(Athlete athlete) throws SQLException;
    void insert(Athlete athlete) throws SQLException;
}
