package gdut.ff.domain;

import java.io.Serializable;

/**
 * @Author bluesnail95
 * @Date 2020/10/6 11:20
 * @Description
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String roleId;

    private String roleName;

    private String roleNameZh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleNameZh() {
        return roleNameZh;
    }

    public void setRoleNameZh(String roleNameZh) {
        this.roleNameZh = roleNameZh;
    }
}
