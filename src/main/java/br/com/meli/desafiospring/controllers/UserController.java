package br.com.meli.desafiospring.controllers;

import br.com.meli.desafiospring.dtos.*;
import br.com.meli.desafiospring.enums.UserType;
import br.com.meli.desafiospring.models.User;
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
    User user3 = new User();
    user3.setUsername("comprador2");
    user3.setUserType(UserType.BUYER);
    userService.save(user3);
    User user4 = new User();
    user4.setUsername("vendedor2");
    user4.setUserType(UserType.SELLER);
    userService.save(user4);
    User user5 = new User();
    user5.setUsername("comprador3");
    user5.setUserType(UserType.BUYER);
    userService.save(user5);
    List<User> all = userService.findAll();
    return ResponseEntity.ok(all);
  }

  @PostMapping(value = "/{userId}/unfollow/{userIdToUnfollow}")
  public ResponseEntity unfollow(@PathVariable(value = "userId") int userId,
                                 @PathVariable(value = "userIdToUnfollow") int userIdToUnfollow) {
    if (userService.unfollow(userId, userIdToUnfollow)) {
      return ResponseEntity.status(200).build();
    } else {
      return ResponseEntity.status(400).build();
    }
  }

  @GetMapping(value = "/{userId}/followers/list")
  public ResponseEntity<UserFollowersListDTO> getFollowers(@PathVariable("userId") Integer userId,
                                                            @RequestParam(value = "order", defaultValue = "name_asc") String order) {
    return ResponseEntity.ok(userService.getFollowers(userId, order));
  }

  @GetMapping(value = "/{userId}/followed/list")
  public ResponseEntity<UserFollowedListDTO> getFollowed(@PathVariable("userId") Integer userId,
                                                         @RequestParam(value = "order", defaultValue = "name_asc") String order) {
    return ResponseEntity.ok(userService.getFollowed(userId, order));
  }

  @GetMapping(value = "/popular-sellers/ranking")
  public ResponseEntity<List<UserFollowersCountDTO>> getRankedSellers(@RequestParam(value = "size", defaultValue = "10") int size){
    return ResponseEntity.ok(userService.getRankedSellers(size));
  }

  @PostMapping(value = "/register")
  public ResponseEntity<User> registerNewUser(@RequestBody User user) {
    User saveUser = userService.save(user);

    if(saveUser == null) {
      return ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok(saveUser);
  }
}
