package athletic.domain;

import org.json.JSONObject;

/**
 * role 实体类
 */


public class Role {
    private String roleId;
    private String roleName;
    private int authority;

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public int getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }
}

