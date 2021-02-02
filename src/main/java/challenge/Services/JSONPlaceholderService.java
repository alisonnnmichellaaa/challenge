package challenge.Services;

import challenge.Model.Album;
import challenge.Model.Comment;
import challenge.Model.Photo;
import challenge.Model.Post;
import challenge.Model.Users.User;
import challenge.Services.Exceptions.JsonPlaceHolderServiceException;
import challenge.Services.Exceptions.OnlyOneFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JSONPlaceholderService {

    RestTemplate restTemplate = new RestTemplate();
    private static String informationCouldNotBeRetrieved="The information could not be retrieved";

    private static final String JSONPLACEHOLDERSERVICE="https://jsonplaceholder.typicode.com/";

    public User[] allUsers(){
        ResponseEntity <User[]> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/users", User[].class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException(informationCouldNotBeRetrieved);
        return  response.getBody();
    }

    public User findUserById(Long userId){
        ResponseEntity <User> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/users/"+userId, User.class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException(informationCouldNotBeRetrieved);
        return  response.getBody();
    }

    public Photo[] allPhotos(){
        ResponseEntity <Photo[]> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/photos", Photo[].class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException(informationCouldNotBeRetrieved);
        return  response.getBody();
    }

    public Photo findPhotoByUserId(long id){
        ResponseEntity <Photo> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/photos/"+id, Photo.class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException(informationCouldNotBeRetrieved);
        return  response.getBody();
    }

    public Album[] allAlbums(){
        ResponseEntity <Album[]> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/users", Album[].class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException(informationCouldNotBeRetrieved);
        return  response.getBody();
    }
    public Album findAlbumById(long id){
        ResponseEntity <Album> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/albums/"+id, Album.class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException(informationCouldNotBeRetrieved);
        return  response.getBody();
    }
    public Album[] allAlbumsOfUser(long userId){
        ResponseEntity <Album[]> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/users/"+userId+"albums", Album[].class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException(informationCouldNotBeRetrieved);
        return  response.getBody();
    }

    public Photo[] allPhotosOfUser(long userId){
        ResponseEntity <Photo[]> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/photos", Photo[].class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException(informationCouldNotBeRetrieved);
        Album[] albums=this.allAlbumsOfUser(userId);
        List<Long> idsAlbums= Arrays.stream(albums).mapToLong(album->album.getId()).boxed().collect(Collectors.toList());
        return Arrays.stream(response.getBody()).filter(photo ->  idsAlbums.contains(photo.getAlbumId())).toArray(Photo[]::new);

    }

    public Post[] allPostsByUserId(Long userId){
        ResponseEntity <Post[]> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/posts?userId="+userId, Post[].class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException(informationCouldNotBeRetrieved);
        return  response.getBody();
    }

    public Comment[] allCommentsByUserId(String userIdString){
        Long userId=Long.valueOf(userIdString);
        ResponseEntity<Comment[]> response = restTemplate.getForEntity(JSONPLACEHOLDERSERVICE+"/comments", Comment[].class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException(informationCouldNotBeRetrieved);
        Comment[] comments=response.getBody();
        Post[] postArray=this.allPostsByUserId(userId);
        List<Long> postIds=Arrays.stream(postArray).mapToLong(post->post.getId()).boxed().collect(Collectors.toList());
        List<Comment> commentList= Arrays.stream(comments).filter(comment -> postIds.contains(comment.getPostId())).collect(Collectors.toList());
        return commentList.toArray(new Comment[commentList.size()]);
    }

    private void nameAndUserIdVerification(String name,String userId){
        if(name!=null && userId!=null)
            throw new OnlyOneFilter("The filter must be either name or userId. No both of them");
    }

    private Comment[] allComments(){
        ResponseEntity<Comment[]> response = restTemplate.getForEntity(JSONPLACEHOLDERSERVICE+"/comments", Comment[].class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException(informationCouldNotBeRetrieved);
        return response.getBody();
    }

    private Comment[] allCommentsByName(String name){
        ResponseEntity<Comment[]> response = restTemplate.getForEntity(JSONPLACEHOLDERSERVICE+"/comments?name="+name, Comment[].class);
        if(response.getStatusCodeValue()!=200)
            throw new JsonPlaceHolderServiceException(informationCouldNotBeRetrieved);
        return response.getBody();
    }

    public Comment[] allCommentsWithQueryParams(String name,String userId){
        nameAndUserIdVerification(name,userId);
        if(name==null && userId==null)
           return  allComments();
        else if (userId!=null)
           return allCommentsByUserId(userId);
        else
           return allCommentsByName(name);
    }
}
