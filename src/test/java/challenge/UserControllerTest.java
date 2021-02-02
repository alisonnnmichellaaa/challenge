package challenge;

import challenge.Controllers.UserController;
import challenge.Model.Users.User;
import challenge.Services.JSONPlaceholderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JSONPlaceholderService jsonPlaceholderService;

    @Test
    public void whenGetUsersIsPerformedThenStatusOkAndUsersAreReturned() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("./src/test/java/challenge/UserList.json")), StandardCharsets.UTF_8);
        User[] usersDto=objectMapper.readValue(content, User[].class);
        Mockito.when(jsonPlaceholderService.allUsers()).thenReturn(usersDto);
        mvc.perform(MockMvcRequestBuilders.get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasSize(2)))
                .andExpect(MockMvcResultMatchers.content().json(content));

    }



}
