package br.com.meli.desafiospring.services;

import br.com.meli.desafiospring.dtos.PostDTO;
import br.com.meli.desafiospring.dtos.ProductDTO;
import br.com.meli.desafiospring.models.Product;
import br.com.meli.desafiospring.models.User;
import br.com.meli.desafiospring.repositories.PostRepository;
import br.com.meli.desafiospring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PostRepository postRepository;

    public Product Save(ProductDTO productDTO) {
        return productRepository.save(productDTO.toModel(productDTO));
    }
}
