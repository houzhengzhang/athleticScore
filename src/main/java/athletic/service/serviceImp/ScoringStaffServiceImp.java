package athletic.service.serviceImp;

import athletic.dao.daoImp.AthleteCompetitionDaoImp;
import athletic.dao.daoImp.CompetitionStageDaoImp;
import athletic.dao.daoImp.ScoringStaffDaoImp;
import athletic.domain.AthleteCompetition;
import athletic.domain.CompetitionStage;
import athletic.domain.ScoringStaff;
import athletic.service.ScoringStaffService;
import athletic.utils.JDBCUtils;

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

    @Override
    public int updateAthleteScore(List<AthleteCompetition> athleteCompetitionList) throws SQLException {
        AthleteCompetitionDaoImp athleteCompetitionDaoImp = new AthleteCompetitionDaoImp();
        CompetitionStageDaoImp competitionStageDaoImp = new CompetitionStageDaoImp();
        Connection connection = null;

        try{
            // 获取连接
            connection = JDBCUtils.getConnection();
            // 开启事务
            connection.setAutoCommit(false);

            // 更新运动员-项目表 分数
            for(AthleteCompetition athleteCompetition:athleteCompetitionList){
                athleteCompetitionDaoImp.update(athleteCompetition, connection);
            }

            // 更新排名表 ranking
            CompetitionStage competitionStage = competitionStageDaoImp.getCompetitionStageById(athleteCompetitionList.get(0).getCompetitonId());
            // 如果更新决赛成绩
            if("决赛".equals(competitionStage.getState())){

            }
            // 提交事务
            connection.commit();
        }catch (Exception e){
            // 回滚
            connection.rollback();
            e.printStackTrace();
        }

        return 0;
    }
}
