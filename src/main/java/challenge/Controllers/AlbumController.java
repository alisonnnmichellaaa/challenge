package challenge.Controllers;

import challenge.Dtos.UserPermissionDto;
import challenge.Model.Album;
import challenge.Model.UserPermission;
import challenge.Model.Users.User;
import challenge.Services.AlbumPermissionsService;
import challenge.Services.JSONPlaceholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlbumController {

    @Autowired
    JSONPlaceholderService jsonPlaceholderService;

    @Autowired
    AlbumPermissionsService albumPermissionsService;

    @GetMapping("/albums")
    public ResponseEntity<Album[]> getUsers() {
            return new ResponseEntity<>(this.jsonPlaceholderService.allAlbums(),HttpStatus.OK);
    }
    @PostMapping("albums/{albumId}/permission")
    public ResponseEntity<UserPermission> postAlbumPermission(@PathVariable String albumId, @RequestBody UserPermissionDto userPermissionDto) {
        return new ResponseEntity<>(this.albumPermissionsService.addPermission(userPermissionDto,Long.valueOf(albumId)), HttpStatus.OK);
    }
    @PutMapping("albums/{albumId}/permission")
    public ResponseEntity<UserPermission> putAlbumPermission(@PathVariable String albumId, @RequestBody UserPermissionDto userPermissionDto) {
        return new ResponseEntity<>(this.albumPermissionsService.editPermission(userPermissionDto,Long.valueOf(albumId)), HttpStatus.OK);
    }
    @GetMapping("albums/{albumId}/permission/{idPermission}/users")
    public ResponseEntity <List<User>> getAlbumPermission(@PathVariable String albumId, @PathVariable String idPermission) {
        return new ResponseEntity<>(this.albumPermissionsService.allUsers(Long.valueOf(albumId),Integer.valueOf(idPermission)), HttpStatus.OK);
    }
}
