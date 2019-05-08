package athletic.service.serviceImp;

import athletic.dao.daoImp.CompetitionStageDaoImp;
import athletic.domain.CompetitionStage;
import athletic.service.CompetitionStageService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 10:24
 * @ Description:
 */
public class CompetitionStageServiceImp implements CompetitionStageService {
    private CompetitionStageDaoImp competitionStageDaoImp = new CompetitionStageDaoImp();

    @Override
    public List<CompetitionStage> getAllStage() throws SQLException {
        return competitionStageDaoImp.getAllStage();
    }

    @Override
    public CompetitionStage getCompetitionStageById(String competitionId) throws SQLException {
        return competitionStageDaoImp.getCompetitionStageById(competitionId);
    }
}
