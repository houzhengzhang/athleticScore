package athletic.dao;

import athletic.domain.ScoringStaff;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/8 14:10
 * @ Description:
 */
public interface ScoringStaffDao {
    ScoringStaff scoringStaffLogin(ScoringStaff scoringStaff) throws SQLException;
}
