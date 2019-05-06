package athletic.dao;

import athletic.domain.CompetitionField;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/6 17:53
 * @ Description:
 */
public interface CompetitionFieldDao {
    void insert(CompetitionField competitionField) throws SQLException;
}
