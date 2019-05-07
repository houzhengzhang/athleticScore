package athletic.domain;

import org.json.JSONObject;

/**
 * athleteCompetition 实体类
 */


public class AthleteCompetition {
    private String athleteId;
    private String competitonId;
    private String competitionStageId;
    private double score;
    private Competition competition;
    private CompetitionStage competitionStage;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public CompetitionStage getCompetitionStage() {
        return competitionStage;
    }

    public void setCompetitionStage(CompetitionStage competitionStage) {
        this.competitionStage = competitionStage;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setCompetitonId(String competitonId) {
        this.competitonId = competitonId;
    }

    public String getCompetitonId() {
        return competitonId;
    }

    public void setCompetitionStageId(String competitionStageId) {
        this.competitionStageId = competitionStageId;
    }

    public String getCompetitionStageId() {
        return competitionStageId;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}

