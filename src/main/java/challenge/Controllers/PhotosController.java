package challenge.Controllers;

import challenge.Model.Photo;
import challenge.Services.JSONPlaceholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class PhotosController {

    @Autowired
    JSONPlaceholderService jsonPlaceholderService;


    @GetMapping("/photos")
    public ResponseEntity<Photo[]> getUsers() {
        try {
            return new ResponseEntity<>( this.jsonPlaceholderService.allPhotos(),HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<Photo> getUsers(@PathVariable String id) {
        try {
            return new ResponseEntity<>( this.jsonPlaceholderService.findPhotoById(Long.valueOf(id)),HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
