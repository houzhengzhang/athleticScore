package athletic.service.serviceImp;

import athletic.dao.RefereeDao;
import athletic.dao.daoImp.RefereeDaoImp;
import athletic.domain.Referee;
import athletic.service.RefereeService;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/5 20:57
 * @ Description:
 */
public class RefereeServiceImp implements RefereeService {
    @Override
    public Referee refereeLogin(Referee referee) throws SQLException {
        RefereeDaoImp refereeDaoImp = new RefereeDaoImp();
        return refereeDaoImp.refereeLogin(referee);
    }
}
