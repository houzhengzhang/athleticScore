package athletic.service.serviceImp;

import athletic.dao.CompetitionFieldDao;
import athletic.dao.daoImp.CompetitionFieldDaoImp;
import athletic.domain.CompetitionField;
import athletic.service.CompetitionFieldService;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/6 18:07
 * @ Description:
 */
public class CompetitionFieldServiceImp implements CompetitionFieldService {
    @Override
    public void insert(CompetitionField competitionField) throws SQLException {
        CompetitionFieldDaoImp competitionFieldDaoImp = new CompetitionFieldDaoImp();
        competitionFieldDaoImp.insert(competitionField);
    }
}
