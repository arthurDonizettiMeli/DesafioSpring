package br.com.meli.desafiospring.services;

import br.com.meli.desafiospring.enums.UserType;
import br.com.meli.desafiospring.models.User;
import br.com.meli.desafiospring.models.UserFollowers;
import br.com.meli.desafiospring.repositories.UserFollowersRepository;
import br.com.meli.desafiospring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}
