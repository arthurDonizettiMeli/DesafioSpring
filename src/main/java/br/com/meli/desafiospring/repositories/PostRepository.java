package br.com.meli.desafiospring.repositories;

import br.com.meli.desafiospring.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
  //Optional<List<Post>> getPostByUserIdAAndHasPromoIsTrue(Integer userId);
  Optional<List<Post>> getAllByUserIdAndHasPromo(Integer userId, boolean hasPromo);
}
