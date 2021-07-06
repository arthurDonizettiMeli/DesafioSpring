package br.com.meli.desafiospring.repositories;

import br.com.meli.desafiospring.models.UserFollowers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowersRepository extends JpaRepository<UserFollowers, Integer> {
}
