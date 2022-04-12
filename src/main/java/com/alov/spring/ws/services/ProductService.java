package com.alov.spring.ws.services;

import com.alov.spring.ws.entities.ProductEntity;
import com.alov.spring.ws.repositories.ProductRepository;
import com.alov.spring.ws.soap.products.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public static final Function<ProductEntity, Product> functionEntityToSoap = productEntity -> {
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setTitle(productEntity.getTitle());
        product.setPrice(productEntity.getPrice());
        product.setCategoryTitle(productEntity.getCategory().getTitle());
        return product;
    };

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public Product getByTitle(String title) {
        return productRepository.findByTitle(title).map(functionEntityToSoap).get();
    }


}
