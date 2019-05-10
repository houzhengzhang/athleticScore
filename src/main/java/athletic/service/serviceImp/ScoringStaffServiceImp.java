package athletic.service.serviceImp;

import athletic.dao.daoImp.AthleteCompetitionDaoImp;
import athletic.dao.daoImp.CompetitionStageDaoImp;
import athletic.dao.daoImp.RankingDaoImp;
import athletic.dao.daoImp.ScoringStaffDaoImp;
import athletic.domain.AthleteCompetition;
import athletic.domain.CompetitionStage;
import athletic.domain.Ranking;
import athletic.domain.ScoringStaff;
import athletic.service.ScoringStaffService;
import athletic.utils.JDBCUtils;
import athletic.utils.UUIDUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
