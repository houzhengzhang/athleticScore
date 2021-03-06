package athletic.domain;

import org.json.JSONObject;

/**
 * competitionStage 实体类
 */


public class CompetitionStage {
    private String competitionStageId;
    private String state;

    public void setCompetitionStageId(String competitionStageId) {
        this.competitionStageId = competitionStageId;
    }

    public String getCompetitionStageId() {
        return competitionStageId;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }
}

