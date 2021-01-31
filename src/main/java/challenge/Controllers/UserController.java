package challenge.Controllers;

import challenge.Dtos.UserDto;
import challenge.Services.JSONPlaceholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    JSONPlaceholderService jsonPlaceholderService;

    public JSONPlaceholderService getJsonPlaceholderService() {
        return jsonPlaceholderService;
    }

    public void setJsonPlaceholderService(JSONPlaceholderService jsonPlaceholderService) {
        this.jsonPlaceholderService = jsonPlaceholderService;
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return Arrays.asList(this.getJsonPlaceholderService().allUsers());
    }

}
