package br.com.meli.desafiospring.services;

import br.com.meli.desafiospring.dtos.PostDTO;
import br.com.meli.desafiospring.models.Post;
import br.com.meli.desafiospring.dtos.ProductCountPromoDTO;
import br.com.meli.desafiospring.models.User;
import br.com.meli.desafiospring.repositories.PostRepository;
import br.com.meli.desafiospring.repositories.ProductRepository;
import br.com.meli.desafiospring.repositories.UserRepository;
import br.com.meli.desafiospring.utils.DateUtils;
import br.com.meli.desafiospring.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

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
        postRepository.save(post.toModel());
    }


    public List<PostDTO> getById(Integer userId, String order) {
        List<Post> postList = postRepository.findAll().stream()
                .filter(e -> DateUtils.getDifferenceBetweenEpochsInDays(
                        e.getDate().getTime(), System.currentTimeMillis()) < 15)
                .collect(Collectors.toList());

        List<PostDTO> list = postList.stream().filter(e -> e.getUserId().equals(userId)).map(Post::toDTO).collect(Collectors.toList());

        SortUtils.sort(list, order);

        return list;
    }

    public List<Post> getPromoPostsFromUserById(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty())
            return null;


        List<Post> posts = postRepository.findAll();

        return posts.stream().filter(e -> e.getUserId().equals(userId) && e.getHasPromo()).collect(Collectors.toList());
    }

    public ProductCountPromoDTO countPromo(Integer userId) {
        List<Post> filteredPosts = getPromoPostsFromUserById(userId);

        if (filteredPosts == null)
            return null;

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty())
            return null;

        User user = optionalUser.get();

        return new ProductCountPromoDTO(userId, user.getUsername(), filteredPosts.size());
    }

    public PostDTO updatePost(Integer postId, PostDTO postDTO) {
        Optional<Post> postOptional = postRepository.findById(postId);

        if (postOptional.isEmpty()) {
            return null;
        }

        Post post = postOptional.get();

        post.setCategory(postDTO.getCategory());
        post.setPrice(postDTO.getPrice());
        post.setProduct(postDTO.getDetail().toModel());
        post.setDiscount(postDTO.getDiscount());
        post.setHasPromo(postDTO.getHasPromo());

        if (post.getDiscount() == 0) {
            throw new RuntimeException("Discount should be bigger than 0");
        }

        Post savedPost = postRepository.save(post);

        return savedPost.toDTO();
    }
}
