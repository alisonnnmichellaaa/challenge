package challenge.Services;

import challenge.Dtos.UserPermissionDto;
import challenge.Model.Album;
import challenge.Model.AlbumPermissions;
import challenge.Model.UserPermission;
import challenge.Model.Users.User;
import challenge.Repositories.UserPermissionRepository;
import challenge.Services.Exceptions.UserPermissionValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumPermissionsService {
    @Autowired
    private JSONPlaceholderService jsonPlaceholderService;

    @Autowired
    private UserPermissionRepository userPermissionRepository;


    private boolean userExists(Long userId){
        if(userId==null)
            return false;
        User user=this.jsonPlaceholderService.findUserById(userId);
        return user!=null;
    }

    private boolean albumIdExists(Long albumId){
        if(albumId==null)
            return false;
       Album album= this.jsonPlaceholderService.findAlbumById(albumId);
       return album!=null;
    }

    private void albumValidation(Long albumId){
        if(!this.albumIdExists(albumId))
            throw new UserPermissionValidationException("The album does not exists");
    }

    private void userValidation(Long userId){
        if(!this.userExists(userId))
            throw new UserPermissionValidationException("The user does not exist");
    }

    private void permissionCodeValidation(Integer permissionCode){
        if(permissionCode==null)
            throw new UserPermissionValidationException("The permission does not exist");
      boolean permissionCodeExist=Arrays.stream(AlbumPermissions.values()).anyMatch(albumPermissions -> albumPermissions.getCodePermission()==permissionCode);
        if(!permissionCodeExist)
            throw new UserPermissionValidationException("The permission does not exist");
    }

    private void userPermissionDtoVerification(UserPermissionDto userPermissionDto){
        userValidation(userPermissionDto.getUserId());
        permissionCodeValidation(userPermissionDto.getCodePermission());
    }

    private void distinctPermissionValidation(UserPermission userPermission, UserPermissionDto userPermissionDto){
        if(userPermission.getPermission().getCodePermission()==userPermissionDto.getCodePermission())
            throw new UserPermissionValidationException("The user already has those permissions");
    }

    private AlbumPermissions toAlbumPermission(int codePermission){
        Optional<AlbumPermissions> albumPermissionsOptional= Arrays.stream(AlbumPermissions.values()).filter(albumPermissions -> albumPermissions.getCodePermission()==codePermission).findFirst();
        return albumPermissionsOptional.get();
    }

    private UserPermission toUserPermission(UserPermissionDto userPermissionDto,Long albumId){
          UserPermission userPermission= new UserPermission();
          userPermission.setAlbumId(albumId);
          userPermission.setPermission(toAlbumPermission(userPermissionDto.getCodePermission()));
          userPermission.setUserId(userPermissionDto.getUserId());
          return userPermission;
    }

    public UserPermission addPermission( UserPermissionDto userPermissionDto,Long albumId) throws UserPermissionValidationException {
        userPermissionDtoVerification( userPermissionDto);
        this.albumValidation(albumId);
        UserPermission userPermission=this.toUserPermission(userPermissionDto,albumId);
        return this.userPermissionRepository.save(userPermission);
    }
    private UserPermission getUserPermissionByIdAndAlbumId(Long userId,Long albumId){
        List<UserPermission> userPermissionList=this.userPermissionRepository.findByUserIdAndAlbumId(userId,albumId);
        if(userPermissionList.isEmpty())
            throw new UserPermissionValidationException("The user does not have permissions registered");
        return userPermissionList.get(0);
    }
    public UserPermission editPermission( UserPermissionDto userPermissionDto,Long albumId) throws UserPermissionValidationException {
        userPermissionDtoVerification( userPermissionDto);
        UserPermission userPermission= getUserPermissionByIdAndAlbumId(userPermissionDto.getUserId(),albumId);
        distinctPermissionValidation(userPermission,userPermissionDto);
        userPermission.setPermission(toAlbumPermission(userPermissionDto.getCodePermission()));
        return this.userPermissionRepository.save(userPermission);
    }

    public List<User> allUsers( Long albumId,int codePermission ) throws UserPermissionValidationException {
        albumValidation(albumId);
        AlbumPermissions albumPermissions=toAlbumPermission(codePermission);
        List<User> users= Arrays.asList(this.jsonPlaceholderService.allUsers());
        List<UserPermission> userPermissionList=this.userPermissionRepository.findByAlbumIdAndPermission(albumId,albumPermissions);
        List<Long> userPermissionIds= userPermissionList.stream().mapToLong(userPermission1->userPermission1.getUserId()).boxed().collect(Collectors.toList());
        return users.stream().filter(user ->userPermissionIds.contains(user.getId())).collect(Collectors.toList());
    }
}
