package challenge.Controllers;

import challenge.Model.Album;
import challenge.Services.JSONPlaceholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlbumsController {

    @Autowired
    JSONPlaceholderService jsonPlaceholderService;

    @GetMapping("/albums")
    public ResponseEntity<Album[]> getUsers() {
        try {
            return new ResponseEntity<>(this.jsonPlaceholderService.allAlbums(),HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
