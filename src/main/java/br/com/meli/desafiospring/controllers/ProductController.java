package br.com.meli.desafiospring.controllers;

import br.com.meli.desafiospring.dtos.*;
import br.com.meli.desafiospring.models.Post;
import br.com.meli.desafiospring.models.User;
import br.com.meli.desafiospring.services.PostService;
import br.com.meli.desafiospring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<HttpStatus> createPost(@RequestBody PostDTO postDTO) {
        try {
            postService.createPost(postDTO, false);

            return ResponseEntity.ok().build();

        } catch (Exception exception) {
            return ResponseEntity.status(400).build();
        }

    }

    @GetMapping(value = "/followed/{userId}/list")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable(value = "userId") int userId,
                                                        @RequestParam(value = "order", defaultValue = "date_desc") String order) {
        try {

            List<PostDTO> postList = postService.getById(userId, order);
            return ResponseEntity.ok(postList);

        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping(value = "/{userId}/list")
    public ResponseEntity<PostsFromUserDTO> getPromoProductsFromUser(@PathVariable(value = "userId") Integer userId) {
        User user = userService.findById(userId);
        List<Post> postList = postService.getPromoPostsFromUserById(userId);

        return ResponseEntity.ok(new PostsFromUserDTO().toModel(user, postList));
    }

    @PostMapping(value = "/newpromopost")
    public ResponseEntity<HttpStatus> createPromoPost(@RequestBody PostDTO postDTO) {
        try {
            postService.createPost(postDTO, true);
            return ResponseEntity.ok().build();

        } catch (Exception exception) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping(value = "/{userId}/countpromo")
    public ResponseEntity<ProductCountPromoDTO> countPromo(@PathVariable(value = "userId") int userId) {
        ProductCountPromoDTO productCountPromoDTO = postService.countPromo(userId);
        if (productCountPromoDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productCountPromoDTO);
    }

    @PatchMapping("/editpost/{postId}")
    public ResponseEntity<PostDTO> changeToPromoPost(@PathVariable Integer postId, @RequestBody PostDTO postDTO) {
        try {
            PostDTO post = postService.updatePost(postId, postDTO);

            if(post == null) {
                return ResponseEntity.notFound().build();
            }
        } catch(Exception ignored) {}
        return ResponseEntity.status(400).build();
    }

}
