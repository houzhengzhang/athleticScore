package athletic.dao;

import athletic.domain.Referee;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/4 22:49
 * @ Description:
 */
public interface RefereeDao {
    Referee refereeLogin(Referee referee) throws SQLException;
}
