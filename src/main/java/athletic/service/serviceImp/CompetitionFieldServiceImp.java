package athletic.service.serviceImp;

import athletic.dao.daoImp.CompetitionFieldDaoImp;
import athletic.domain.CompetitionField;
import athletic.service.CompetitionFieldService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/6 18:07
 * @ Description:
 */
public class CompetitionFieldServiceImp implements CompetitionFieldService {
    private CompetitionFieldDaoImp competitionFieldDaoImp = new CompetitionFieldDaoImp();

    @Override
    public int insert(CompetitionField competitionField) throws SQLException {
        return competitionFieldDaoImp.insert(competitionField);
    }

    @Override
    public int update(CompetitionField competitionField) throws SQLException {
        return competitionFieldDaoImp.update(competitionField);
    }

    @Override
    public CompetitionField getCompetitionFieldById(String competitionFieldId) throws SQLException {
        return competitionFieldDaoImp.getCompetitionFieldById(competitionFieldId);
    }

    @Override
    public List<CompetitionField> getAllCompetitionField() throws SQLException {
        return competitionFieldDaoImp.getAllCompetitionField();
    }
}
