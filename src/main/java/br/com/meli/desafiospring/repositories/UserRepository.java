package br.com.meli.desafiospring.repositories;

import br.com.meli.desafiospring.enums.UserType;
import br.com.meli.desafiospring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<List<User>> getAllByUserType(UserType userType);
}
