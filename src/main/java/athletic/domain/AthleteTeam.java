package athletic.domain;

import org.json.JSONObject;

/**
 * athleteTeam 实体类
 */


public class AthleteTeam {
    private String athleteTeamId;
    private String name;
    private String school;
    private int totalPoint;

    public void setAthleteTeamId(String athleteTeamId) {
        this.athleteTeamId = athleteTeamId;
    }

    public String getAthleteTeamId() {
        return athleteTeamId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
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

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }
}

