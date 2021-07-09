package br.com.meli.desafiospring;

import br.com.meli.desafiospring.enums.UserType;
import br.com.meli.desafiospring.exceptions.*;
import br.com.meli.desafiospring.models.User;
import br.com.meli.desafiospring.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Test
    void test1_registerUsersError() throws Exception {
        mockMvc.perform(post("/users/register")
                .content("{\n" +
                        "    \"username\": \"Arthur\",\n" +
                        "    \"userType\": \"ERROR1\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof HttpMessageNotReadableException));
    }

    @Test
    void test2_followWithError() throws Exception {
        mockMvc.perform(post("/users/{userId}/follow/{userIdToFollow}", 1000, 10000)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof UserNotFoundException));
    }

    @Test
    void test4_unfollowWithError() throws Exception {
        User testUser = new User();
        testUser.setUsername("Rodrigo");
        testUser.setUserType(UserType.BUYER);

        testUser = userService.save(testUser);

        User testUser2 = new User();
        testUser2.setUsername("Rodrigo");
        testUser2.setUserType(UserType.BUYER);

        testUser2 = userService.save(testUser2);

        mockMvc.perform(post("/users/{userId}/unfollow/{userIdToUnfollow}", testUser.getId(), testUser2.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof UserDoesNotFollowException));
    }

    @Test
    void test5_refollowWithError() throws Exception {
        User testUser = new User();
        testUser.setUsername("Rodrigo");
        testUser.setUserType(UserType.BUYER);

        testUser = userService.save(testUser);

        User testUser2 = new User();
        testUser2.setUsername("Rodrigo");
        testUser2.setUserType(UserType.BUYER);

        testUser2 = userService.save(testUser2);

        mockMvc.perform(post("/users/{userId}/follow/{userIdToFollow}", testUser.getId(), testUser2.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/users/{userId}/follow/{userIdToUnfollow}", testUser.getId(), testUser2.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof RefollowException));
    }

    @Test
    void test4_followYourselfWithError() throws Exception {
        User testUser = new User();
        testUser.setUsername("Rodrigo");
        testUser.setUserType(UserType.BUYER);

        testUser = userService.save(testUser);

        mockMvc.perform(post("/users/{userId}/follow/{userIdToUnfollow}", testUser.getId(), testUser.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof SelfFollowException));
    }

    @Test
    void test8_followYourselfWithError() throws Exception {
        User testSeller = new User();
        testSeller.setUsername("Rodrigo");
        testSeller.setUserType(UserType.SELLER);

        testSeller = userService.save(testSeller);

        User testBuyer = new User();
        testBuyer.setUsername("Rodrigo");
        testBuyer.setUserType(UserType.BUYER);

        testBuyer = userService.save(testBuyer);

        mockMvc.perform(post("/users/{userId}/follow/{userIdToUnfollow}", testSeller.getId(), testBuyer.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof SellerFollowException));
    }

    @Test
    void test3_followWithError() throws Exception {
        mockMvc.perform(post("/users/{userId}/follow/{userIdToFollow}", "asdasd", "dasd")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof MethodArgumentTypeMismatchException));
    }

    @Test
    void test1Us0015_registerUsers() throws Exception {
        mockMvc.perform(post("/users/register")
                .content("{\n" +
                        "    \"username\": \"Arthur\",\n" +
                        "    \"userType\": \"SELLER\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/users/register")
                .content("{\n" +
                        "    \"username\": \"Rodrigo\",\n" +
                        "    \"userType\": \"BUYER\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void test2Us001_followUser() throws Exception {
        mockMvc.perform(post("/users/{userId}/follow/{userIdToFollow}", 2, 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void test3Us002_countFollowers() throws Exception {
        mockMvc.perform(get("/users/{userId}/followers/count", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void test4Us003_listFollowers() throws Exception {
        mockMvc.perform(get("/users/{userId}/followers/list", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("followers").isArray())
                .andExpect(jsonPath("followers", hasSize(1)));
    }

    @Test
    void test5Us004_listFollowed() throws Exception {
        mockMvc.perform(get("/users/{userId}/followed/list", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("followed").isArray())
                .andExpect(jsonPath("followed", hasSize(1)));
    }

    @Test
    void test7Us007_unfollow() throws Exception {
        mockMvc.perform(post("/users/{userId}/unfollow/{userIdToUnfollow}", 2, 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
