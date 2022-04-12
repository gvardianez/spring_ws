package com.alov.spring.ws.services;

import com.alov.spring.ws.entities.CategoryEntity;
import com.alov.spring.ws.repositories.CategoryRepository;
import com.alov.spring.ws.soap.categories.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public static final Function<CategoryEntity, Category> functionEntityToSoap = categoryEntity -> {
        Category category = new Category();
        category.setTitle(categoryEntity.getTitle());
        categoryEntity.getProduct().stream().map(ProductService.functionEntityToSoap).forEach(product -> category.getProducts().add(product));
        return category;
    };

    public Category getByTitle(String title) {
        return categoryRepository.findByTitle(title).map(functionEntityToSoap).get();
    }

}
