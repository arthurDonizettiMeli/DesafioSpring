package br.com.meli.desafiospring.services;

import br.com.meli.desafiospring.models.User;
import br.com.meli.desafiospring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getTestUsers() {
        User userTest1 = new User();
        userTest1.setUsername("Usuario teste 1");

        User userTest2 = new User();
        userTest2.setUsername("Usuario teste 2");

        userRepository.save(userTest1);
        userRepository.save(userTest2);

        return userRepository.findAll();
    }

}
