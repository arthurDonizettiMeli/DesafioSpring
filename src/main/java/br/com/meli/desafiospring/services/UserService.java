package br.com.meli.desafiospring.services;

import br.com.meli.desafiospring.models.User;
import br.com.meli.desafiospring.models.UserFollowers;
import br.com.meli.desafiospring.repositories.UserFollowersRepository;
import br.com.meli.desafiospring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserFollowers follow(UserFollowers userFollowers) {
        return userFollowersRepository.save(userFollowers);
    }
}
