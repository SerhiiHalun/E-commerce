package org.ecommerce.storeapp.controller.web;

import org.ecommerce.storeapp.model.Category;
import org.ecommerce.storeapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/create")
    public String showCategoryForm(Model model){
        model.addAttribute("category",new Category());
        return "category/create";
    }
    @PostMapping("/create")
    public String createCategory(@ModelAttribute("category") Category category){
        categoryService.createCategory(category);
        return "redirect:/product/home";
    }

}
