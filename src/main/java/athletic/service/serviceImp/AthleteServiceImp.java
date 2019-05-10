package athletic.service.serviceImp;

import athletic.dao.daoImp.AthleteCompetitionDaoImp;
import athletic.dao.daoImp.AthleteDaoImp;
import athletic.dao.daoImp.CompetitionDaoImp;
import athletic.domain.Athlete;
import athletic.domain.AthleteCompetition;
import athletic.domain.Competition;
import athletic.service.AthleteService;
import athletic.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ Date: 2019/5/7 13:56
 * @ Description:
 */
public class AthleteServiceImp implements AthleteService {
    private AthleteDaoImp athleteDaoImp = new AthleteDaoImp();

    @Override
    public Athlete athleteLogin(Athlete athlete) throws SQLException {
        return athleteDaoImp.athleteLogin(athlete);
    }

    @Override
    public int insert(Athlete athlete, String[] competitionIdList) throws SQLException {
        CompetitionDaoImp competitionDaoImp = new CompetitionDaoImp();
        AthleteCompetitionDaoImp athleteCompetitionDaoImp = new AthleteCompetitionDaoImp();
        Connection connection = null;
        try {
            // 获取连接
            connection = JDBCUtils.getConnection();
            // 开启事务
            connection.setAutoCommit(false);
            // 插入运动员数据
            athleteDaoImp.insert(athlete, connection);

            for (String competitionId : competitionIdList) {
                // 查询项目信息
                Competition competition = competitionDaoImp.getCompetionById(competitionId);
                // 生成运动员-项目记录
                AthleteCompetition athleteCompetition = new AthleteCompetition();
                athleteCompetition.setCompetitonId(competitionId);
                athleteCompetition.setAthleteId(athlete.getAthleteId());
                athleteCompetition.setCompetitionStageId(competition.getCompetitionStageId());

                // 插入记录
                athleteCompetitionDaoImp.insert(athleteCompetition, connection);
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
