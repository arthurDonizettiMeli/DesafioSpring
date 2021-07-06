package br.com.meli.desafiospring.controllers;

import br.com.meli.desafiospring.dtos.PostDTO;
import br.com.meli.desafiospring.dtos.ProductDTO;
import br.com.meli.desafiospring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

//    @Autowired
//    ProductService productService;

    @PostMapping(value = "/newpost")
    public ResponseEntity<ProductDTO> CreatePost(@RequestBody PostDTO postDTO) {
        try {

            PostDTO post = postService.createPost;

            return ResponseEntity.ok(post.getDetail());

        } catch (Exception exception) {
            return ResponseEntity.status(400).build();
        }

    }

    @GetMapping(value = "/followed/{userId}/list")
    public ResponseEntity<List<PostDTO>> GetPostsByUser(@PathVariable(value = "userId") int userId) {

        List<PostDTO> postList = postService.getById(userId);

        return ResponseEntity.ok(postList);
    }

    @GetMapping(value = "/followed/{userId}/list")
    public ResponseEntity<List<PostDTO>> GetPostsByUser(@PathVariable(value = "userId") int userId) {

        List<PostDTO> postList = postService.getById(userId);

        return ResponseEntity.ok(postList);
    }

    @GetMapping(value = "/followed/{userId}/list")
    public ResponseEntity<List<PostDTO>> GetPostsByUser(@PathVariable(value = "userId") int userId) {

        List<PostDTO> postList = postService.getById(userId);

        return ResponseEntity.ok(postList);
    }




}
