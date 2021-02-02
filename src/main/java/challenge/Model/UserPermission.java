package challenge.Model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name="ALBUM_PERMISSIONS",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"userId","albumId"}, name = "uniqueUserAndAlbumConstraint")}
        )
public class UserPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private AlbumPermissions permission;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long albumId;

    public UserPermission() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AlbumPermissions getPermission() {
        return permission;
    }

    public void setPermission(AlbumPermissions permission) {
        this.permission = permission;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }
}
