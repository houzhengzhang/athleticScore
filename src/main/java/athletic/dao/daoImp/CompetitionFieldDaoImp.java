package athletic.dao.daoImp;

import athletic.dao.CompetitionFieldDao;
import athletic.domain.CompetitionField;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * @ Date: 2019/5/6 17:54
 * @ Description:
 */
public class CompetitionFieldDaoImp implements CompetitionFieldDao {
    @Override
    public void insert(CompetitionField competitionField) throws SQLException {
        String sql = "insert into competitionfield values(?,?,?,?)";
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        Object[] params={competitionField.getFieldId(), competitionField.getName(), competitionField.getAddress(), competitionField.getState()};
        queryRunner.update(sql, params);
    }
}
