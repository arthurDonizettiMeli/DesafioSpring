package br.com.meli.desafiospring.controllers;

import br.com.meli.desafiospring.dtos.UserFollowersCountDTO;
import br.com.meli.desafiospring.enums.UserType;
import br.com.meli.desafiospring.models.User;
import br.com.meli.desafiospring.models.UserFollowers;
import br.com.meli.desafiospring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/{userId}/follow/{userIdToFollow}")
    public ResponseEntity follow(@PathVariable(value = "userId") int userId, @PathVariable(value = "userIdToFollow") int userIdToFollow) {
        if (userService.follow(userId, userIdToFollow))
            return ResponseEntity.status(200).build();
        return ResponseEntity.status(400).build();
    }

    @GetMapping(value = "/{userId}/followers/count/")
    public ResponseEntity<UserFollowersCountDTO> followersCount(@PathVariable(value = "userId") int userId) {
        return ResponseEntity.ok(userService.followersCount(userId));
    }

    @GetMapping(value = "/test")
    public ResponseEntity<List<User>> test() {
        User user1 = new User();
        user1.setUsername("comprador1");
        user1.setUserType(UserType.BUYER);
        userService.save(user1);
        User user2 = new User();
        user2.setUsername("vendedor1");
        user2.setUserType(UserType.SELLER);
        userService.save(user2);
        List<User> all = userService.findAll();
        return ResponseEntity.ok(all);
    }
}
