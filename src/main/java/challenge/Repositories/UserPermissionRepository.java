package challenge.Repositories;


import challenge.Model.AlbumPermissions;
import challenge.Model.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission,Long> {
    List<UserPermission> findByUserIdAndAlbumId(Long userId, Long albumId);
    List<UserPermission> findByAlbumIdAndPermission(Long albumId, AlbumPermissions permission);
}
