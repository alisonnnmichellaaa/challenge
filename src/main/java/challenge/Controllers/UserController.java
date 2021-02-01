package challenge.Controllers;

import challenge.Model.Users.User;
import challenge.Services.JSONPlaceholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    JSONPlaceholderService jsonPlaceholderService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        try {
            return new ResponseEntity<>(Arrays.asList(this.jsonPlaceholderService.allUsers()), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUsers(@PathVariable String id) {
            try {
                return new ResponseEntity<>(this.jsonPlaceholderService.findUserById(Long.valueOf(id)), HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }



}
