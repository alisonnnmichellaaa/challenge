package challenge.Services;

import challenge.Model.Album;
import challenge.Model.Photo;
import challenge.Model.Users.User;
import challenge.Services.Exceptions.JsonPlaceHolderServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JSONPlaceholderService {

    RestTemplate restTemplate = new RestTemplate();


    private static final String JSONPLACEHOLDERSERVICE="https://jsonplaceholder.typicode.com/";

    public User[] allUsers(){
        ResponseEntity <User[]> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/users", User[].class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException("The information could not be retrieved");
        return  response.getBody();
    }

    public User findUserById(Long userId){
        ResponseEntity <User> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/users/"+userId, User.class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException("The information could not be retrieved");
        return  response.getBody();
    }

    public Photo[] allPhotos(){
        ResponseEntity <Photo[]> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/photos", Photo[].class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException("The information could not be retrieved");
        return  response.getBody();
    }

    public Photo findPhotoByUserId(long id){
        ResponseEntity <Photo> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/photos/"+id, Photo.class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException("The information could not be retrieved");
        return  response.getBody();
    }

    public Album[] allAlbums(){
        ResponseEntity <Album[]> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/users", Album[].class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException("The information could not be retrieved");
        return  response.getBody();
    }
    public Album findAlbumById(long id){
        ResponseEntity <Album> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/albums/"+id, Album.class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException("The information could not be retrieved");
        return  response.getBody();
    }
    public Album[] allAlbumsOfUser(long userId){
        ResponseEntity <Album[]> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/users/"+userId+"albums", Album[].class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException("The information could not be retrieved");
        return  response.getBody();
    }

    public Photo[] allPhotosOfUser(long userId){
        ResponseEntity <Photo[]> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/photos", Photo[].class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException("The information could not be retrieved");
        Album[] albums=this.allAlbumsOfUser(userId);
        List<Long> idsAlbums= Arrays.stream(albums).mapToLong(album->album.getId()).boxed().collect(Collectors.toList());
        return Arrays.stream(response.getBody()).filter(photo ->  idsAlbums.contains(photo.getAlbumId())).toArray(Photo[]::new);

    }
}
