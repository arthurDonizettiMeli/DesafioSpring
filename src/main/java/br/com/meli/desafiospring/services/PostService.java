package br.com.meli.desafiospring.services;

import br.com.meli.desafiospring.dtos.PostDTO;
import br.com.meli.desafiospring.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public void createPost(PostDTO post) { postRepository.save(post.toModel(post)); }

    public List<PostDTO> getById(Integer userId) {
        return null;
    }

    public void save(PostDTO post) { postRepository.save(post.toModel(post)); }

}
