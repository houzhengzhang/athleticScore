package athletic.domain;

import org.json.JSONObject;

/**
 * athlete 实体类
 */


public class Athlete {
    private String athleteId;
    private String email;
    private String password;
    private String name;
    private int sex;
    private String roleId;
    private String athleteTeamId;

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSex() {
        return sex;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setAthleteTeamId(String athleteTeamId) {
        this.athleteTeamId = athleteTeamId;
    }

    public String getAthleteTeamId() {
        return athleteTeamId;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }
}

