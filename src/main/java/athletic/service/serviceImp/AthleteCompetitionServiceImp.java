package athletic.service.serviceImp;

import athletic.dao.daoImp.AthleteCompetitionDaoImp;
import athletic.dao.daoImp.AthleteDaoImp;
import athletic.dao.daoImp.AthleteTeamDaoImp;
import athletic.dao.daoImp.CompetitionDaoImp;
import athletic.dao.daoImp.RankingDaoImp;
import athletic.domain.Athlete;
import athletic.domain.AthleteCompetition;
import athletic.domain.Competition;
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
    public int getAthleteRanking(String athleteId, String competitionId, String competitionStageId) throws SQLException {
        return athleteCompetitionDaoImp.getAthleteRanking(athleteId, competitionId, competitionStageId);
    }

    @Override
    public int insert(AthleteCompetition athleteCompetition) throws SQLException {
        return athleteCompetitionDaoImp.insert(athleteCompetition);
    }

    /**
     * 计分员录入分数
     *
     * @param athleteCompetition
     * @return
     * @throws SQLException
     */
    @Override
    public int updateAthleteScore(AthleteCompetition athleteCompetition) throws SQLException {
        AthleteTeamDaoImp athleteTeamDaoImp = new AthleteTeamDaoImp();
        AthleteCompetitionDaoImp athleteCompetitionDaoImp = new AthleteCompetitionDaoImp();
        CompetitionDaoImp competitionDaoImp = new CompetitionDaoImp();
        RankingDaoImp rankingDaoImp = new RankingDaoImp();
        AthleteDaoImp athleteDaoImp = new AthleteDaoImp();
        Connection connection = null;
        // 更新运动员-项目表 分数  放在事务内无法查询到
        athleteCompetitionDaoImp.update(athleteCompetition);

        try {
            // 获取连接
            connection = JDBCUtils.getConnection();
            // 开启事务
            connection.setAutoCommit(false);

            Competition competition = competitionDaoImp.getCompetionById(athleteCompetition.getCompetitionId());
            // 获取比赛阶段信息
            CompetitionStage competitionStage = competition.getCompetitionStage();
            // 如果更新决赛成绩且所有运动员的分数都已给出，则生成排名表信息
            boolean flag = athleteCompetitionDaoImp.isAthleteScoredById(competition.getCompetitionId(),
                    competitionStage.getCompetitionStageId());
            if ("决赛".equals(competitionStage.getState()) && flag) {
                // 按照成绩降序查询该项目所有运动员成绩
                List<AthleteCompetition> rankAthleteCompetitionList = athleteCompetitionDaoImp.queryAthleteScoreByCond(
                        competition.getCompetitionId(), competitionStage.getCompetitionStageId());
                // TODO 修改名次生成
                int i = 1;
                // 生成排名表数据
                for (AthleteCompetition rankAthleteCompetition : rankAthleteCompetitionList) {
                    Ranking ranking = new Ranking();
                    ranking.setRankingId(UUIDUtils.getId());
                    ranking.setAthleteId(rankAthleteCompetition.getAthleteId());
                    ranking.setCompetitonId(rankAthleteCompetition.getCompetitionId());
                    ranking.setRanking(i);
                    // 插入数据
                    rankingDaoImp.insert(ranking, connection);

                    // 只记录前三名到运动队总分榜
                    if (i < 4) {
                        // 查询该运动员信息，按照名次更新运动队总分
                        Athlete athlete = athleteDaoImp.getAthleteById(rankAthleteCompetition.getAthleteId());
                        // TODO 添加事务-更新运动队总分
                        athleteTeamDaoImp.updateTotalPoint(athlete.getAthleteTeamId(), 4 - i);
                    }
                    i++;
                }
            }
            //提交事务
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
    public List<AthleteCompetition> getRankingByCompetitionId(String competitionId, String competitionStageId) throws SQLException {
        return athleteCompetitionDaoImp.getRankingByCompetitionId(competitionId, competitionStageId);
    }
}
