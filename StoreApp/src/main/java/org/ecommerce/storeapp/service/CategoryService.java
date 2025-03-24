package org.ecommerce.storeapp.service;

import jakarta.persistence.EntityNotFoundException;
import org.ecommerce.storeapp.model.Category;
import org.ecommerce.storeapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(int id,Category category) {
        Category toUpdate = getCategoryById(id);
        if (category.getName() != null && !category.getName().isEmpty()) {
            toUpdate.setName(category.getName());
        }
        return categoryRepository.save(toUpdate);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
