package athletic.dao;

import athletic.domain.CompetitionStage;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/6 17:53
 * @ Description:
 */
public interface CompetitionStageDao {
    List<CompetitionStage> getAllStage() throws SQLException;

    CompetitionStage getCompetitionStageById(String competitionId) throws SQLException;

    CompetitionStage getCompetitionStageByState(String state) throws SQLException;

}
