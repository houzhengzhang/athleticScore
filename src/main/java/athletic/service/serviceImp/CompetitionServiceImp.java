package athletic.service.serviceImp;

import athletic.dao.daoImp.AthleteCompetitionDaoImp;
import athletic.dao.daoImp.CompetitionDaoImp;
import athletic.dao.daoImp.CompetitionStageDaoImp;
import athletic.domain.AthleteCompetition;
import athletic.domain.Competition;
import athletic.domain.CompetitionStage;
import athletic.service.CompetitionService;
import athletic.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/6 22:02
 * @ Description:
 */
public class CompetitionServiceImp implements CompetitionService {
    private CompetitionDaoImp competitionDaoImp = new CompetitionDaoImp();

    @Override
    public int insert(Competition competition) throws SQLException {
        return competitionDaoImp.insert(competition);
    }

    /**
     * 裁判员更新项目状态，之后生成决赛的运动员项目记录
     *
     * @param competition
     * @return
     * @throws SQLException
     */
    @Override
    public int update(Competition competition) throws SQLException {
        AthleteCompetitionDaoImp athleteCompetitionDaoImp = new AthleteCompetitionDaoImp();
        CompetitionStageDaoImp competitionStageDaoImp = new CompetitionStageDaoImp();
        Connection connection = null;
        try {
            // 获取连接
            connection = JDBCUtils.getConnection();
            // 开启事务
            connection.setAutoCommit(false);
            // 更新项目状态
            competitionDaoImp.update(competition, connection);
            // 查询初赛的项目阶段Id
            CompetitionStage competitionStage = competitionStageDaoImp.getCompetitionStageByState("初赛");

            // 查询进入决赛的运动员-项目信息
            List<AthleteCompetition> athleteCompetitionList = athleteCompetitionDaoImp.queryAthleteScoreByCond(
                    competition.getCompetitionId(), competitionStage.getCompetitionStageId());

            // 初赛前5名进入决赛 生成决赛的运动员项目记录
            int i=0;
            for(AthleteCompetition athleteCompetitionRecord:athleteCompetitionList){
                AthleteCompetition athleteCompetition = new AthleteCompetition();
                athleteCompetition.setAthleteId(athleteCompetitionRecord.getAthleteId());
                athleteCompetition.setCompetitionId(competition.getCompetitionId());
                athleteCompetition.setCompetitionStageId(competition.getCompetitionStageId());
                athleteCompetition.setScore(0);
                // 插入数据
                athleteCompetitionDaoImp.insert(athleteCompetition, connection);
            }
            // 提交
            connection.commit();
            return 1;
        } catch (Exception e) {
            // 回滚
            connection.rollback();
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Competition> getAllCompetion() throws SQLException {
        return competitionDaoImp.getAllCompetion();
    }
}
