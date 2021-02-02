package challenge.Controllers;

import challenge.Model.Photo;
import challenge.Services.JSONPlaceholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotosController {

    @Autowired
    JSONPlaceholderService jsonPlaceholderService;


    @GetMapping("/photos")
    public ResponseEntity<Photo[]> getUsers() {
            return new ResponseEntity<>( this.jsonPlaceholderService.allPhotos(),HttpStatus.OK);
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<Photo> getUsers(@PathVariable String id) {
        return new ResponseEntity<>( this.jsonPlaceholderService.findPhotoByUserId(Long.valueOf(id)),HttpStatus.OK);

    }

}
