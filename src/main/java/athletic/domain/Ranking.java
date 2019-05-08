package athletic.domain;

import org.json.JSONObject;

/**
 * ranking 实体类
 */


public class Ranking {
    private String rankingId;
    private String competitonId;
    private String athleteId;

    // 外键
    private Competition competition;
    private Athlete athlete;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

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

