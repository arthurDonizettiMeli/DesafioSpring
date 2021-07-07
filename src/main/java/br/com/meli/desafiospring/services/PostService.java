package br.com.meli.desafiospring.services;

import br.com.meli.desafiospring.dtos.PostDTO;
import br.com.meli.desafiospring.dtos.ProductCountPromoDTO;
import br.com.meli.desafiospring.models.Post;
import br.com.meli.desafiospring.models.User;
import br.com.meli.desafiospring.repositories.PostRepository;
import br.com.meli.desafiospring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public void createPost(PostDTO post, boolean isPromoPost) {
        if (isPromoPost) {
            post.setHasPromo(true);
            if (post.getDiscount() == 0) {
                throw new RuntimeException("Discount should be bigger than 0");
            }
        } else {
            post.setHasPromo(false);
            post.setDiscount(0.0);
        }
        postRepository.save(post.toModel(post));
    }

    public List<PostDTO> getById(Integer userId) {

        List<Post> postList = postRepository.findAll().stream().filter(e -> {
            Long startDate = e.getDate().getTime();
            Long dateNow = System.currentTimeMillis();
            long difference = dateNow - startDate;
            return TimeUnit.MILLISECONDS.toDays(difference) < 15;
        }).collect(Collectors.toList());

        return postList.stream().filter(e -> e.getUserId().equals(userId)).map(e -> e.toDTO(e)).collect(Collectors.toList());

    }


    public ProductCountPromoDTO countPromo(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            return null;
        }

        List<Post> posts = postRepository.findAll();

        List<Post> filteredPosts = posts.stream().filter(e -> e.getUserId().equals(userId) && e.getHasPromo()).collect(Collectors.toList());

        User user = optionalUser.get();

        return new ProductCountPromoDTO(userId, user.getUsername(), filteredPosts.size());

    }
}
