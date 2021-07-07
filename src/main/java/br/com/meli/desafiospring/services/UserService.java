package br.com.meli.desafiospring.services;

import br.com.meli.desafiospring.dtos.UserFollowerDTO;
import br.com.meli.desafiospring.models.User;
import br.com.meli.desafiospring.models.UserFollowers;
import br.com.meli.desafiospring.repositories.UserFollowersRepository;
import br.com.meli.desafiospring.repositories.UserRepository;
import br.com.meli.desafiospring.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserFollowersRepository userFollowersRepository;

    public User findById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserFollowers follow(UserFollowers userFollowers) {
        return userFollowersRepository.save(userFollowers);
    }

    public List<UserFollowerDTO> getFollowers(Integer userId, String order) {
        List<UserFollowers> userFollowers = userFollowersRepository.findAll();

        List<UserFollowerDTO> followers = userFollowers.stream().filter(e -> e.getFollowedId()
                .equals(userId)).map(e -> {
            Optional<User> userOptional = userRepository.findById(e.getFollowerId());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return new UserFollowerDTO(user.getId(), user.getUsername());
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());

        SortUtils.sort(followers, order);

        return followers;
    }

    public List<UserFollowerDTO> getFollowed(Integer userId, String order) {
        List<UserFollowers> userFollowers = userFollowersRepository.findAll();

        List<UserFollowerDTO> followers = userFollowers.stream().filter(e -> e.getFollowerId()
                .equals(userId)).map(e -> {
            Optional<User> userOptional = userRepository.findById(e.getFollowedId());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return new UserFollowerDTO(user.getId(), user.getUsername());
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());

        SortUtils.sort(followers, order);

        return followers;
    }
}
