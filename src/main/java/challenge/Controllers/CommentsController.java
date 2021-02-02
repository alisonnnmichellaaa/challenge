package challenge.Controllers;

import challenge.Model.Comment;
import challenge.Services.JSONPlaceholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentsController {

    @Autowired
    JSONPlaceholderService jsonPlaceholderService;

    @GetMapping("/comments")
    public ResponseEntity<Comment[]> getUsers(@RequestParam(required = false) String name,@RequestParam(required = false) String userId) {
        return new ResponseEntity<>(this.jsonPlaceholderService.allCommentsWithQueryParams(name,userId), HttpStatus.OK);
    }
}
