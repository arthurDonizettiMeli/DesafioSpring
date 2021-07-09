package br.com.meli.desafiospring.services;

import br.com.meli.desafiospring.dtos.*;
import br.com.meli.desafiospring.enums.UserType;
import br.com.meli.desafiospring.exceptions.RefollowException;
import br.com.meli.desafiospring.exceptions.SelfFollowException;
import br.com.meli.desafiospring.exceptions.SellerFollowException;
import br.com.meli.desafiospring.exceptions.UserNotFoundException;
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

    public User save(User user) {
        if(user.getUserType() == null || (!user.getUserType().equals(UserType.SELLER) && !user.getUserType().equals(UserType.BUYER))) {
            return null;
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setUserType(user.getUserType());

        return (userRepository.save(newUser));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean follow(int userId, int userIdToFollow) {
        User user = findById(userId);

        if (user == null)
            throw new UserNotFoundException(userId);
        if (findById(userIdToFollow) == null)
            throw new UserNotFoundException(userIdToFollow);
        if (userId == userIdToFollow)
            throw new SelfFollowException();
        if (user.getUserType().equals(UserType.SELLER))
            throw new SellerFollowException();
        if (user.getUserFollowers().stream().anyMatch(u -> u.getFollowedId() == userIdToFollow))
            throw new RefollowException();
        UserFollowers userFollowers = new UserFollowers(userIdToFollow, userId);
        userFollowersRepository.save(userFollowers);
        user.getUserFollowers().add(userFollowers);
        userRepository.save(user);
        return true;
    }

    public UserFollowersCountDTO followersCount(int userId) {
        List<UserFollowers> userFollowersList = userFollowersRepository.findAllByFollowedId(userId);
        User user = findById(userId);
        return (new UserFollowersCountDTO(user.getId(), user.getUsername(), userFollowersList.size()));
    }

    public UserFollowersListDTO getFollowers(Integer userId, String order) {
        List<UserFollowers> userFollowers = userFollowersRepository.findAllByFollowedId(userId);

        List<UserFollowerDTO> followers = userFollowers.stream().map(e -> {
            Optional<User> userOptional = userRepository.findById(e.getFollowerId());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return new UserFollowerDTO(user.getId(), user.getUsername());
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        SortUtils.sort(followers, order);
        return (new UserFollowersListDTO(userId, findById(userId).getUsername(), followers));
    }

    public UserFollowedListDTO getFollowed(Integer userId, String order) {
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
        return (new UserFollowedListDTO(userId, findById(userId).getUsername(), followers));
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

        UserFollowers follow = optionalFollow.get();
        userFollowersRepository.delete(follow);
        return true;
    }

    public List<UserFollowersCountDTO> getRankedSellers(int size) {
        size = size > 100 ? 100 : Math.max(size, 1);

        List<User> sellers = userRepository.getAllByUserType(UserType.SELLER);
        List<UserFollowersCountDTO> followersCountDTOS = sellers.stream().map(s -> followersCount(s.getId())).collect(Collectors.toList());

        SortUtils.sort(followersCountDTOS, "desc");

        size = Math.min(size, followersCountDTOS.size());

        return followersCountDTOS.subList(0, size);
    }
}
