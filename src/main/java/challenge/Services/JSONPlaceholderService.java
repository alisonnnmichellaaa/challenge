package challenge.Services;

import challenge.Dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JSONPlaceholderService {
    private static final String JSONPLACEHOLDERSERVICE="https://jsonplaceholder.typicode.com/";
    RestTemplate restTemplate = new RestTemplate();

    public UserDto[] allUsers(){
        ResponseEntity <UserDto[]> response
                = restTemplate.getForEntity( JSONPLACEHOLDERSERVICE+ "/users", UserDto[].class);
    return response.getBody();
    }

}
