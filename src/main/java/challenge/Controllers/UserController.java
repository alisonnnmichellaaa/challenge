package challenge.Controllers;

import challenge.Model.Album;
import challenge.Model.Photo;
import challenge.Model.Users.User;
import challenge.Services.JSONPlaceholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class UserController {

    @Autowired
    JSONPlaceholderService jsonPlaceholderService;

    @GetMapping("/users")
    public ResponseEntity<User[]> getUsers() {
            return new ResponseEntity(  this.jsonPlaceholderService.allUsers(), OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUsers(@PathVariable String id) {
        return new ResponseEntity(this.jsonPlaceholderService.findUserById(Long.valueOf(id)), OK);

    }

    @GetMapping("/users/{id}/albums")
    public ResponseEntity<Album[]> getAlbumsFromIdUser(@PathVariable String id) {
            return new ResponseEntity(this.jsonPlaceholderService.allAlbumsOfUser(Long.valueOf(id)), OK);
    }

    @GetMapping("/users/{id}/photos")
    public ResponseEntity<Photo[]> getPhotosFromIdUser(@PathVariable String id) {
         return new ResponseEntity<> (this.jsonPlaceholderService.allPhotosOfUser(Long.valueOf(id)), OK);
    }


}
