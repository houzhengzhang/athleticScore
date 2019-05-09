package athletic.service;

import athletic.domain.Competition;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/6 22:02
 * @ Description:
 */
public interface CompetitionService {
    int insert(Competition competition) throws SQLException;

    int update(Competition competition) throws SQLException;

    List<Competition> getAllCompetion() throws SQLException;
}
