package athletic.dao.daoImp;

import athletic.domain.AthleteCompetition;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2019/5/7 19:48
 * @ Description:
 */
public class AthleteCompetitionDaoImpTest {

    @Test
    public void queryAthleteScore() throws SQLException {
        AthleteCompetitionDaoImp athleteCompetitionDaoImp= new AthleteCompetitionDaoImp();
        List<AthleteCompetition> athleteCompetitionList=athleteCompetitionDaoImp.queryAthleteScoreById("");
        System.out.println(athleteCompetitionList);
    }
}