package athletic.service;

import athletic.domain.Referee;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/5 20:55
 * @ Description:
 */
public interface RefereeService {
    Referee refereeLogin(Referee referee) throws SQLException;
}
