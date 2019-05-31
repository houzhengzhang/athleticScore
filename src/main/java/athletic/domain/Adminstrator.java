package athletic.domain;

import org.json.JSONObject;

/**
 * adminstrator 实体类
 */


public class Adminstrator {
    private String adminstratorId;
    private String email;
    private String password;
    private String name;
    private int sex;
    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAdminstratorId(String adminstratorId) {
        this.adminstratorId = adminstratorId;
    }

    public String getAdminstratorId() {
        return adminstratorId;
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

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

    // 外键
    private Role role;
}

