package athletic.domain;

import org.json.JSONObject;

/**
 * scoringStaff 实体类
 */


public class ScoringStaff {
    private String sId;
    private String email;
    private String password;
    private String name;
    private int sex;
    private String roleId;

    public void setSId(String sId) {
        this.sId = sId;
    }

    public String getSId() {
        return sId;
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

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }
}

