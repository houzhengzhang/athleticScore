package athletic.service.serviceImp;

import athletic.dao.daoImp.AthleteDaoImp;
import athletic.dao.daoImp.AthleteTeamDaoImp;
import athletic.dao.daoImp.RankingDaoImp;
import athletic.domain.Athlete;
import athletic.domain.AthleteTeam;
import athletic.domain.Ranking;
import athletic.service.RankingService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/8 13:58
 * @ Description:
 */
public class RankingServiceImp implements RankingService {
    private RankingDaoImp rankingDaoImp = new RankingDaoImp();

    @Override
    public List<Ranking> getRankingByCompetitionId(String competitionId, String competitionStageId) throws SQLException {
        return rankingDaoImp.getRankingByCompetitionId(competitionId, competitionStageId);
    }

    @Override
    public JSONArray getRankingById(String competitionId) throws SQLException {
        AthleteDaoImp athleteDaoImp = new AthleteDaoImp();
        JSONArray jsonArray = new JSONArray();
        // 获取所有运动队
        AthleteTeamDaoImp athleteTeamDaoImp = new AthleteTeamDaoImp();
        List<AthleteTeam> athleteTeamList = athleteTeamDaoImp.getAllAthleteTeam();
        for (AthleteTeam athleteTeam : athleteTeamList) {
            int gold = 0, sliver = 0, bronze = 0;
            JSONObject jsonObject = new JSONObject();
            // 获取该运动队的所有运动员
            List<Athlete> athleteList = athleteDaoImp.getAthleteByTeamId(athleteTeam.getAthleteTeamId());
            for (Athlete athlete : athleteList) {
                int rank = rankingDaoImp.getRankingById(athlete.getAthleteId(), competitionId);
                switch (rank) {
                    case 1:
                        gold++;
                        break;
                    case 2:
                        sliver++;
                        break;
                    case 3:
                        bronze++;
                        break;
                }
            }
            jsonObject.put("gold", gold);
            jsonObject.put("sliver", sliver);
            jsonObject.put("bronze", bronze);
            jsonObject.put("athleteTeamId", athleteTeam.getAthleteTeamId());
            jsonObject.put("name", athleteTeam.getName());
            jsonObject.put("school", athleteTeam.getSchool());
            jsonObject.put("totalPoint", athleteTeam.getTotalPoint());

            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }
}
