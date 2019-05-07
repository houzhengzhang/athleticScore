package athletic.service.serviceImp;

import athletic.dao.daoImp.CompetitionStageDaoImp;
import athletic.domain.CompetitionStage;
import athletic.service.CompetitionStageService;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

/**
 * @ Date: 2019/5/7 10:24
 * @ Description:
 */
public class CompetitionStageServiceImp implements CompetitionStageService {
    @Override
    public List<CompetitionStage> getAllStage() throws SQLException {
        return new CompetitionStageDaoImp().getAllStage();
    }
}
