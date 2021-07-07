package br.com.meli.desafiospring.controllers;

import br.com.meli.desafiospring.dtos.PostDTO;
import br.com.meli.desafiospring.dtos.PostsFromUserDTO;
import br.com.meli.desafiospring.models.Post;
import br.com.meli.desafiospring.models.User;
import br.com.meli.desafiospring.services.PostService;
import br.com.meli.desafiospring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping(value = "/newpost")
    public ResponseEntity CreatePost(@RequestBody PostDTO postDTO) {
        try {

            postService.createPost(postDTO);
             return ResponseEntity.ok().build();

        } catch (Exception exception) {
            return ResponseEntity.status(400).build();
        }

    }

    @GetMapping(value = "/followed/{userId}/list")
    public ResponseEntity<List<PostDTO>> GetPostsByUser(@PathVariable(value = "userId") int userId) {

        List<PostDTO> postList = postService.getById(userId);

        return ResponseEntity.ok(postList);
    }

    @GetMapping(value = "/{userId}/list")
    public ResponseEntity<PostsFromUserDTO> GetPromoProductsFromUser(@PathVariable(value = "userId") Integer userId){
        User user = userService.findById(userId);
        List<Post> postList = postService.getPromoPostsFromUserById(userId);

        return ResponseEntity.ok(new PostsFromUserDTO().toModel(user, postList));
    }
}
