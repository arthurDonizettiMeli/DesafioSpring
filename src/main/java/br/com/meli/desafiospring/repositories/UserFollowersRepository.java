package br.com.meli.desafiospring.repositories;

import br.com.meli.desafiospring.models.UserFollowers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFollowersRepository extends JpaRepository<UserFollowers, Integer> {
  Optional<UserFollowers> getUserFollowersByFollowerIdAndFollowedId(Integer followerId, Integer followedId);

  List<UserFollowers> findAllByFollowedId(Integer followedId);
}
