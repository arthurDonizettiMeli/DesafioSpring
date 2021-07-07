package br.com.meli.desafiospring.services;

import br.com.meli.desafiospring.dtos.PostDTO;
import br.com.meli.desafiospring.models.Post;
import br.com.meli.desafiospring.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public void createPost(PostDTO post) { postRepository.save(post.toModel(post)); }

    public List<PostDTO> getById(Integer userId) {

        List<Post> postList = postRepository.findAll().stream().filter(e -> {
          Long startDate = e.getDate().getTime();
          Long dateNow = System.currentTimeMillis();
          long difference = dateNow - startDate;
          return TimeUnit.MILLISECONDS.toDays(difference) < 15;
        }).collect(Collectors.toList());

        return postList.stream().filter(e -> e.getUserId().equals(userId)).map(e -> e.toDTO(e)).collect(Collectors.toList());

    }

}
