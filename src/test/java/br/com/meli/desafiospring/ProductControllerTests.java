package br.com.meli.desafiospring;

import br.com.meli.desafiospring.enums.UserType;
import br.com.meli.desafiospring.exceptions.InvalidValueException;
import br.com.meli.desafiospring.exceptions.PostNotFoundException;
import br.com.meli.desafiospring.models.User;
import br.com.meli.desafiospring.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ProductControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Test
    void test3_followWithError() throws Exception {
        User testUser = new User();
        testUser.setUsername("Rodrigo");
        testUser.setUserType(UserType.SELLER);

        testUser = userService.save(testUser);

        mockMvc.perform(post("/products/newpromopost")
                .content("{\n" +
                        "    \"userID\": "+testUser.getId()+",\n" +
                        "    \"date\": \"20-10-2021\",\n" +
                        "         \"detail\" :\n" +
                        "            { \n" +
                        "                \"productName\": \"Cadeira Gamer 3\",\n" +
                        "                \"type\": \"Gamer\",\n" +
                        "                \"brand\": \"Racer\",   \n" +
                        "                \"color\": \"Red & Black\",\n" +
                        "                \"notes\": \"Special Edition\"\n" +
                        "            },\n" +
                        "    \"category\": 100,\n" +
                        "    \"price\": 1500.50,\n" +
                        "    \"hasPromo\": true,\n" +
                        "    \"discount\": 0.0\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof InvalidValueException));
    }

    @Test
    void test7_followWithError() throws Exception {
        User testUser = new User();
        testUser.setUsername("Rodrigo");
        testUser.setUserType(UserType.SELLER);

        testUser = userService.save(testUser);

        mockMvc.perform(patch("/products/editpost/909999")
                .content("{\n" +
                        "    \"userID\": "+testUser.getId()+",\n" +
                        "    \"date\": \"20-10-2021\",\n" +
                        "         \"detail\" :\n" +
                        "            { \n" +
                        "                \"productName\": \"Cadeira Gamer 3\",\n" +
                        "                \"type\": \"Gamer\",\n" +
                        "                \"brand\": \"Racer\",   \n" +
                        "                \"color\": \"Red & Black\",\n" +
                        "                \"notes\": \"Special Edition\"\n" +
                        "            },\n" +
                        "    \"category\": 100,\n" +
                        "    \"price\": 1500.50,\n" +
                        "    \"hasPromo\": true,\n" +
                        "    \"discount\": 10.0\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof PostNotFoundException));
    }

    @Test
    void test6() throws Exception {
        User testUser = new User();
        testUser.setUsername("Yan");
        testUser.setUserType(UserType.SELLER);

        testUser = userService.save(testUser);

        mockMvc.perform(post("/products/newpost")
                .content("{\n" +
                        "    \"userID\": "+testUser.getId()+",\n" +
                        "    \"date\": \"20-10-2021\",\n" +
                        "         \"detail\" :\n" +
                        "            { \n" +
                        "                \"productName\": \"Cadeira Gamer 3\",\n" +
                        "                \"type\": \"Gamer\",\n" +
                        "                \"brand\": \"Racer\",   \n" +
                        "                \"color\": \"Red & Black\",\n" +
                        "                \"notes\": \"Special Edition\"\n" +
                        "            },\n" +
                        "    \"category\": 100,\n" +
                        "    \"price\": 1500.50,\n" +
                        "    \"hasPromo\": false,\n" +
                        "    \"discount\": 100.00\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void test7() throws Exception {
        User testUserBuyer = new User();
        testUserBuyer.setUsername("Buyer");
        testUserBuyer.setUserType(UserType.BUYER);

        testUserBuyer = userService.save(testUserBuyer);

        User testUserSeller = new User();
        testUserSeller.setUsername("Seller");
        testUserSeller.setUserType(UserType.SELLER);

        testUserSeller = userService.save(testUserSeller);

        mockMvc.perform(post("/users/{userId}/follow/{userIdToFollow}", testUserBuyer.getId(), testUserSeller.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/products/newpromopost")
                .content("{\n" +
                        "    \"userID\": "+testUserSeller.getId()+",\n" +
                        "    \"date\": \"20-10-2021\",\n" +
                        "         \"detail\" :\n" +
                        "            { \n" +
                        "                \"productName\": \"Cadeira Gamer 3\",\n" +
                        "                \"type\": \"Gamer\",\n" +
                        "                \"brand\": \"Racer\",   \n" +
                        "                \"color\": \"Red & Black\",\n" +
                        "                \"notes\": \"Special Edition\"\n" +
                        "            },\n" +
                        "    \"category\": 100,\n" +
                        "    \"price\": 1500.50,\n" +
                        "    \"hasPromo\": true,\n" +
                        "    \"discount\": 100.00\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/products/followed/{userId}/list", testUserSeller.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].hasPromo").value(true));
    }

    @Test
    void test9() throws Exception {
        User testUser = new User();
        testUser.setUsername("Yan");
        testUser.setUserType(UserType.SELLER);

        testUser = userService.save(testUser);

        mockMvc.perform(post("/products/newpromopost")
                .content("{\n" +
                        "    \"userID\": "+testUser.getId()+",\n" +
                        "    \"date\": \"20-10-2021\",\n" +
                        "         \"detail\" :\n" +
                        "            { \n" +
                        "                \"productName\": \"Cadeira Gamer 3\",\n" +
                        "                \"type\": \"Gamer\",\n" +
                        "                \"brand\": \"Racer\",   \n" +
                        "                \"color\": \"Red & Black\",\n" +
                        "                \"notes\": \"Special Edition\"\n" +
                        "            },\n" +
                        "    \"category\": 100,\n" +
                        "    \"price\": 1500.50,\n" +
                        "    \"hasPromo\": true,\n" +
                        "    \"discount\": 10.0\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void test10() throws Exception {
        User testUser = new User();
        testUser.setUsername("Yan");
        testUser.setUserType(UserType.SELLER);

        testUser = userService.save(testUser);

        mockMvc.perform(post("/products/newpromopost")
                .content("{\n" +
                        "    \"userID\": "+testUser.getId()+",\n" +
                        "    \"date\": \"20-10-2021\",\n" +
                        "         \"detail\" :\n" +
                        "            { \n" +
                        "                \"productName\": \"Cadeira Gamer 3\",\n" +
                        "                \"type\": \"Gamer\",\n" +
                        "                \"brand\": \"Racer\",   \n" +
                        "                \"color\": \"Red & Black\",\n" +
                        "                \"notes\": \"Special Edition\"\n" +
                        "            },\n" +
                        "    \"category\": 100,\n" +
                        "    \"price\": 1500.50,\n" +
                        "    \"hasPromo\": true,\n" +
                        "    \"discount\": 10.0\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/products/{userId}/countpromo", testUser.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("promoProductsCount").value(1));
    }

    @Test
    void test11() throws Exception {
        User testUser = new User();
        testUser.setUsername("Yan");
        testUser.setUserType(UserType.SELLER);

        testUser = userService.save(testUser);

        mockMvc.perform(post("/products/newpromopost")
                .content("{\n" +
                        "    \"userID\": "+testUser.getId()+",\n" +
                        "    \"date\": \"20-10-2021\",\n" +
                        "         \"detail\" :\n" +
                        "            { \n" +
                        "                \"productName\": \"Cadeira Gamer 2\",\n" +
                        "                \"type\": \"Gamer\",\n" +
                        "                \"brand\": \"Racer\",   \n" +
                        "                \"color\": \"Red\",\n" +
                        "                \"notes\": \"Special\"\n" +
                        "            },\n" +
                        "    \"category\": 100,\n" +
                        "    \"price\": 1500.1,\n" +
                        "    \"hasPromo\": true,\n" +
                        "    \"discount\": 10.0\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/products/{userId}/list", testUser.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("posts.[0].hasPromo").value(true));
    }

    @Test
    void test12() throws Exception {
        User testUser = new User();
        testUser.setUsername("Yan");
        testUser.setUserType(UserType.SELLER);

        testUser = userService.save(testUser);

        mockMvc.perform(patch("/products/editpost/{postId}", 1)
                .content("{\n" +
                        "    \"userID\": "+testUser.getId()+",\n" +
                        "    \"date\": \"20-10-2021\",\n" +
                        "         \"detail\" :\n" +
                        "            { \n" +
                        "                \"productName\": \"Cadeira Gamer 2\",\n" +
                        "                \"type\": \"Gamer\",\n" +
                        "                \"brand\": \"Racer\",   \n" +
                        "                \"color\": \"Red\",\n" +
                        "                \"notes\": \"Special\"\n" +
                        "            },\n" +
                        "    \"category\": 100,\n" +
                        "    \"price\": 2000.1,\n" +
                        "    \"hasPromo\": true,\n" +
                        "    \"discount\": 10.0\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/products/{userId}/list", testUser.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
