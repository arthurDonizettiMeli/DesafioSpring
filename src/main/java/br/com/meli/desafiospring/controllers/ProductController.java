package br.com.meli.desafiospring.controllers;

import br.com.meli.desafiospring.dtos.PostDTO;
import br.com.meli.desafiospring.dtos.ProductCountPromoDTO;
import br.com.meli.desafiospring.services.PostService;
import br.com.meli.desafiospring.services.ProductService;
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
    ProductService productService;

    @PostMapping(value = "/newpost")
    public ResponseEntity CreatePost(@RequestBody PostDTO postDTO) {
        try {
            postDTO.getDetail().setProduct_id(productService.Save(postDTO.getDetail()).getId());
            postService.createPost(postDTO, false);

            return ResponseEntity.ok().build();

        } catch (Exception exception) {
            return ResponseEntity.status(400).build();
        }

    }

    @GetMapping(value = "/followed/{userId}/list")
    public ResponseEntity<List<PostDTO>> GetPostsByUser(@PathVariable(value = "userId") int userId) {
        try {

            List<PostDTO> postList = postService.getById(userId);
            return ResponseEntity.ok(postList);

        } catch (Exception e){
            return ResponseEntity.status(400).build();
        }

    }

    @PostMapping(value = "/newpromopost")
    public ResponseEntity createPromoPost(@RequestBody PostDTO postDTO) {
        try {
            postService.createPost(postDTO, true);
            return ResponseEntity.ok().build();

        } catch (Exception exception) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping(value = "/{userId}/countPromo")
    public ResponseEntity<ProductCountPromoDTO> countPromo(@PathVariable(value = "userId") int userId) {
        ProductCountPromoDTO productCountPromoDTO = postService.countPromo(userId);
        if(productCountPromoDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productCountPromoDTO);
    }
}
