package athletic.service;

import athletic.domain.ScoringStaff;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/8 14:14
 * @ Description:
 */
public interface ScoringStaffService {
    ScoringStaff scoringStaffLogin(ScoringStaff scoringStaff) throws SQLException;
}
