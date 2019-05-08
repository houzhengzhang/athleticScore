package athletic.service.serviceImp;

import athletic.dao.daoImp.ScoringStaffDaoImp;
import athletic.domain.ScoringStaff;
import athletic.service.ScoringStaffService;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/8 14:15
 * @ Description:
 */
public class ScoringStaffServiceImp implements ScoringStaffService {
    private ScoringStaffDaoImp scoringStaffDaoImp = new ScoringStaffDaoImp();

    @Override
    public ScoringStaff scoringStaffLogin(ScoringStaff scoringStaff) throws SQLException {
        return scoringStaffDaoImp.scoringStaffLogin(scoringStaff);
    }
}
