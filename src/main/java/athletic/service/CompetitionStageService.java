package athletic.service;

import athletic.domain.CompetitionField;
import athletic.domain.CompetitionStage;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/6 18:07
 * @ Description:
 */
public interface CompetitionStageService {
    List<CompetitionStage> getAllStage() throws SQLException;
}
