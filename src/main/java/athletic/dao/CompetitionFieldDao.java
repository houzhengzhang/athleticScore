package athletic.dao;

import athletic.domain.CompetitionField;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/6 17:53
 * @ Description:
 */
public interface CompetitionFieldDao {
    int insert(CompetitionField competitionField) throws SQLException;

    int update(CompetitionField competitionField) throws SQLException;

    CompetitionField getCompetitionFieldById(String competitionFieldId) throws SQLException;

    List<CompetitionField> getAllCompetitionField() throws SQLException;
}
