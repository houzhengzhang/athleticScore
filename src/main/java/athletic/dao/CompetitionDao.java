package athletic.dao;

import athletic.domain.Competition;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/6 21:57
 * @ Description:
 */
public interface CompetitionDao {
    int insert(Competition competition) throws SQLException;

    int update(Competition competition) throws SQLException;

    Competition getCompetionById(String competionId) throws SQLException;
}
