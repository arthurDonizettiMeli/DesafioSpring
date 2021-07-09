package br.com.meli.desafiospring.controllers;

import br.com.meli.desafiospring.dtos.*;
import br.com.meli.desafiospring.models.User;
import br.com.meli.desafiospring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping(value = "/{userId}/follow/{userIdToFollow}")
  public ResponseEntity<HttpStatus> follow(@PathVariable(value = "userId") int userId, @PathVariable(value = "userIdToFollow") int userIdToFollow) {
    userService.follow(userId, userIdToFollow);
    return ResponseEntity.status(200).build();
  }

  @GetMapping(value = "/{userId}/followers/count")
  public ResponseEntity<UserFollowersCountDTO> followersCount(@PathVariable(value = "userId") int userId) {
    return ResponseEntity.ok(userService.followersCount(userId));
  }

  @PostMapping(value = "/{userId}/unfollow/{userIdToUnfollow}")
  public ResponseEntity<HttpStatus> unfollow(@PathVariable(value = "userId") int userId,
                                             @PathVariable(value = "userIdToUnfollow") int userIdToUnfollow) {
    userService.unfollow(userId, userIdToUnfollow);
    return ResponseEntity.status(200).build();
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
  public ResponseEntity<List<UserFollowersCountDTO>> getRankedSellers(@RequestParam(value = "size", defaultValue = "10") int size) {
    return ResponseEntity.ok(userService.getRankedSellers(size));
  }

  @PostMapping(value = "/register")
  public ResponseEntity<User> registerNewUser(@Valid @RequestBody User user) {
    return ResponseEntity.ok(userService.save(user));
  }
}
