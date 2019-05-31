package athletic.domain;

import org.json.JSONObject;

import java.util.Date;

/**
 * competition 实体类
 */


public class Competition {
    private String competitionId;
    private String competitionStageId;
    private String name;
    private String fieldId;
    private Date startTime;
    private Date endTime;


    public CompetitionField getCompetitionField() {
        return competitionField;
    }

    public void setCompetitionField(CompetitionField competitionField) {
        this.competitionField = competitionField;
    }

    public CompetitionStage getCompetitionStage() {
        return competitionStage;
    }

    public void setCompetitionStage(CompetitionStage competitionStage) {
        this.competitionStage = competitionStage;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionStageId(String competitionStageId) {
        this.competitionStageId = competitionStageId;
    }

    public String getCompetitionStageId() {
        return competitionStageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }
    // 外键对象
    private CompetitionField competitionField;
    private CompetitionStage competitionStage;

}

