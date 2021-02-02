package challenge;

import challenge.Dtos.UserPermissionDto;
import challenge.Repositories.UserPermissionRepository;
import challenge.Services.AlbumPermissionsService;
import challenge.Services.Exceptions.UserPermissionValidationException;
import challenge.Services.JSONPlaceholderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlbumControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private JSONPlaceholderService jsonPlaceholderService;
    @MockBean
    private UserPermissionRepository userPermissionRepository;
    @MockBean
    private AlbumPermissionsService albumPermissionsService;

    @Test
    public void whenPostPermissionIsPerformedWithIdUserNullThen400CodeIsReturned() throws Exception {
        Mockito.when(albumPermissionsService.addPermission(ArgumentMatchers.any(UserPermissionDto.class),anyLong())).thenThrow(new UserPermissionValidationException("The user is null"));
        MockMvc mvc= MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        String json = "{\"codePermission\":1}";
        mvc.perform(MockMvcRequestBuilders.post("/albums/1/permission")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPostPermissionIsPerformedWithEmptyJsonObjectThen400CodeIsReturned() throws Exception {
        Mockito.when(albumPermissionsService.addPermission(ArgumentMatchers.any(UserPermissionDto.class),anyLong())).thenThrow(new UserPermissionValidationException("The user does not exist"));
        MockMvc mvc= MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        String json = "{}";
        mvc.perform(MockMvcRequestBuilders.post("/albums/1/permission")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("The user does not exist"));
    }


}
