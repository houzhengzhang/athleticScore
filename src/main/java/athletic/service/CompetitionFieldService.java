package athletic.service;

import athletic.domain.CompetitionField;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/6 18:07
 * @ Description:
 */
public interface CompetitionFieldService {
    void insert(CompetitionField competitionField) throws SQLException;
    void update(CompetitionField competitionField) throws SQLException;
}
