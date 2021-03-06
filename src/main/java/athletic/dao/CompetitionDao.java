package athletic.dao;

import athletic.domain.Competition;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/6 21:57
 * @ Description:
 */
public interface CompetitionDao {
    int insert(Competition competition) throws SQLException;

    int update(Competition competition, Connection connection) throws SQLException;

    Competition getCompetionById(String competionId) throws SQLException;

    List<Competition> getAllCompetion() throws SQLException;
}
