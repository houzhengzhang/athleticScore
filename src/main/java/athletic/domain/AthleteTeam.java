package athletic.domain;
import java.sql.*;

/**
 * athleteTeam 实体类
 */ 


public class AthleteTeam {
	private String athleteTeamId;
	private String name;
	private int totalPoint;

    public void setAthleteTeamId(String athleteTeamId) {
        this.athleteTeamId = athleteTeamId;
    }

    public String getAthleteTeamId() {
        return athleteTeamId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

}

