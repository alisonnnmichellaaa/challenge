package challenge.Dtos;

public class UserPermissionDto {
    private Integer codePermission;
    private Long userId;

    public UserPermissionDto() {
    }

    public int getCodePermission() {
        return codePermission;
    }

    public void setCodePermission(int codePermission) {
        this.codePermission = codePermission;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
