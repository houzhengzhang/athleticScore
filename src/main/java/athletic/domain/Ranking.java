package athletic.domain;

import org.json.JSONObject;

/**
 * ranking 实体类
 */


public class Ranking {
    private String rankingId;
    private String competitonId;
    private String athleteId;

    public void setRankingId(String rankingId) {
        this.rankingId = rankingId;
    }

    public String getRankingId() {
        return rankingId;
    }

    public void setCompetitonId(String competitonId) {
        this.competitonId = competitonId;
    }

    public String getCompetitonId() {
        return competitonId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public String getAthleteId() {
        return athleteId;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }
}

