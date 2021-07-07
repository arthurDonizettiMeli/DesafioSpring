package br.com.meli.desafiospring.services;

import br.com.meli.desafiospring.dtos.UserFollowersCountDTO;
import br.com.meli.desafiospring.enums.UserType;
import br.com.meli.desafiospring.dtos.UserFollowerDTO;
import br.com.meli.desafiospring.models.User;
import br.com.meli.desafiospring.models.UserFollowers;
import br.com.meli.desafiospring.repositories.UserFollowersRepository;
import br.com.meli.desafiospring.repositories.UserRepository;
import br.com.meli.desafiospring.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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

    public boolean follow(int userId, int userIdToFollow) {
        User user = findById(userId);

        if (user == null)
            return false;
        if (findById(userIdToFollow) == null)
            return false;
        if (userId == userIdToFollow)
            return false;
        if (user.getUserType().equals(UserType.SELLER))
            return false;
        if (user.getUserFollowers().stream().anyMatch(u -> u.getFollowedId() == userIdToFollow))
            return false;
        UserFollowers userFollowers = new UserFollowers(userIdToFollow, userId);
        userFollowersRepository.save(userFollowers);
        user.getUserFollowers().add(userFollowers);
        userRepository.save(user);
        return true;
    }

    public UserFollowersCountDTO followersCount(int userId) {
        List<UserFollowers> userFollowersList = userFollowersRepository.findAll();
        User user = findById(userId);
        return (new UserFollowersCountDTO(user.getId(), user.getUsername(),
                (int) userFollowersList.stream().filter(u -> u.getFollowedId() == userId).count()));
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

    public boolean unfollow(Integer followerId, Integer followedId) {
        Optional<User> optionalFollower = userRepository.findById(followerId);
        if (optionalFollower.isEmpty())
            return false;

        Optional<User> optionalFollowed = userRepository.findById(followedId);
        if (optionalFollowed.isEmpty())
            return false;

        Optional<UserFollowers> optionalFollow = userFollowersRepository.getUserFollowersByFollowerIdAndFollowedId(followerId, followedId);
        if (optionalFollow.isEmpty())
            return false;

        User follower = optionalFollower.get();
        follower.getUserFollowers().removeIf(e -> e.getFollowedId().equals(followedId));
        userRepository.save(follower);
        return true;
    }
}
