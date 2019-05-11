package athletic.service.serviceImp;

import athletic.dao.RankingDao;
import athletic.dao.daoImp.AthleteCompetitionDaoImp;
import athletic.dao.daoImp.CompetitionStageDaoImp;
import athletic.dao.daoImp.RankingDaoImp;
import athletic.domain.AthleteCompetition;
import athletic.domain.CompetitionStage;
import athletic.domain.Ranking;
import athletic.service.AthleteCompetitionService;
import athletic.utils.JDBCUtils;
import athletic.utils.UUIDUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 19:23
 * @ Description:
 */
public class AthleteCompetitionServiceImp implements AthleteCompetitionService {
    private AthleteCompetitionDaoImp athleteCompetitionDaoImp = new AthleteCompetitionDaoImp();

    @Override
    public List<AthleteCompetition> queryAthleteScore(String athleteId) throws SQLException {
        return athleteCompetitionDaoImp.queryAthleteScoreById(athleteId);
    }

    @Override
    public List<AthleteCompetition> getAllAthleteScore(String copetitionId) throws SQLException {
        return athleteCompetitionDaoImp.getAllAthleteScore(copetitionId);
    }

    @Override
    public int insert(AthleteCompetition athleteCompetition, Connection connection) throws SQLException {
        return athleteCompetitionDaoImp.insert(athleteCompetition, connection);
    }

    @Override
    public int insert(AthleteCompetition athleteCompetition) throws SQLException {
        return athleteCompetitionDaoImp.insert(athleteCompetition);
    }

    /**
     * 计分员录入分数
     * @param athleteCompetition
     * @return
     * @throws SQLException
     */
    @Override
    public int updateAthleteScore(AthleteCompetition athleteCompetition) throws SQLException {
        AthleteCompetitionDaoImp athleteCompetitionDaoImp = new AthleteCompetitionDaoImp();
        CompetitionStageDaoImp competitionStageDaoImp = new CompetitionStageDaoImp();
        RankingDaoImp rankingDaoImp = new RankingDaoImp();
        Connection connection = null;

        try {
            // 获取连接
            connection = JDBCUtils.getConnection();
            // 开启事务
            connection.setAutoCommit(false);

            // 更新运动员-项目表 分数
            athleteCompetitionDaoImp.update(athleteCompetition, connection);

            String competitionId = athleteCompetition.getCompetitionId();
            // 查询更新的比赛阶段信息
            CompetitionStage competitionStage = competitionStageDaoImp.getCompetitionStageById(competitionId);

            // 如果更新决赛成绩且所有运动员的分数都已给出，则生成排名表信息
            boolean flag = athleteCompetitionDaoImp.isAthleteScoredById(competitionId,
                    competitionStage.getCompetitionStageId());
            if ("决赛".equals(competitionStage.getState()) && flag) {
                // 按照成绩降序查询该项目所有运动员成绩
                List<AthleteCompetition> rankAthleteCompetitionList = athleteCompetitionDaoImp.queryAthleteScoreByCond(
                        competitionId, competitionStage.getCompetitionStageId());
                int i = 1;
                // 生成排名表数据
                for (AthleteCompetition rankAthleteCompetition : rankAthleteCompetitionList) {
                    Ranking ranking = new Ranking();
                    ranking.setRankingId(UUIDUtils.getId());
                    ranking.setAthleteId(rankAthleteCompetition.getAthleteId());
                    ranking.setCompetitonId(rankAthleteCompetition.getCompetitionId());
                    ranking.setRanking(i++);
                    // 插入数据
                    rankingDaoImp.insert(ranking, connection);
                }

                // TODO 更新运动队total Point
            }
            // 提交事务
            connection.commit();
            return 1;
        } catch (Exception e) {
            // 回滚
            connection.rollback();
            e.printStackTrace();
            return 0;
        }
    }
}
