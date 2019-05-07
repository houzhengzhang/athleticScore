package athletic.service;

import athletic.domain.Competition;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/6 22:02
 * @ Description:
 */
public interface CompetitionService {
    void insert(Competition competition)throws SQLException;
    void update(Competition competition) throws SQLException;
}
