package org.ecommerce.storeapp.service;

import org.ecommerce.storeapp.model.Categories;
import org.ecommerce.storeapp.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }

    public Categories findById(int id) {
        return categoriesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category with id " + id + " not found"));
    }

    public Categories save(Categories category) {
        return categoriesRepository.save(category);
    }

    public void deleteById(int id) {
        if (!categoriesRepository.existsById(id)) {
            throw new NoSuchElementException("Category with id " + id + " not found");
        }
        categoriesRepository.deleteById(id);
    }


}
