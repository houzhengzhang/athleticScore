package athletic.dao.daoImp;

import athletic.dao.CompetitionFieldDao;
import athletic.domain.CompetitionField;
import athletic.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/6 17:54
 * @ Description:
 */
public class CompetitionFieldDaoImp implements CompetitionFieldDao {
    @Override
    public int insert(CompetitionField competitionField) throws SQLException {
        String sql = "insert into competitionfield values(?,?,?,?)";
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        Object[] params={competitionField.getFieldId(), competitionField.getName(), competitionField.getAddress(), competitionField.getState()};
        return queryRunner.update(sql, params);
    }

    @Override
    public int update(CompetitionField competitionField) throws SQLException {
        String sql = "update competitionfield set name=?,address=?,state=? where fieldId=?";
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        Object[] params={ competitionField.getName(), competitionField.getAddress(), competitionField.getState(),competitionField.getFieldId()};
        return queryRunner.update(sql, params);
    }

    @Override
    public CompetitionField getCompetitionFieldById(String competitionFieldId) throws SQLException {
        String sql = "select * from competitionfield where fieldId=?";
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanHandler<>(CompetitionField.class), competitionFieldId);
    }

    @Override
    public List<CompetitionField> getAllCompetitionField() throws SQLException {
        String sql = "select * from competitionfield";
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(CompetitionField.class));
    }
}
