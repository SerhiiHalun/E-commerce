package org.ecommerce.storeapp.service;

import org.ecommerce.storeapp.model.Category;
import org.ecommerce.storeapp.model.Image;
import org.ecommerce.storeapp.model.Product;
import org.ecommerce.storeapp.repository.CategoryRepository;
import org.ecommerce.storeapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ImageService imageService;
    @Autowired
    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository, ImageService imageService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.imageService = imageService;
    }
    @Transactional
    public Product createProductWithImages(Product product, List<MultipartFile> files, int mainIndex) {

        product.setImages(new ArrayList<>());
        Product savedProduct = productRepository.save(product);


        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (!file.isEmpty()) {
                boolean isMain = (i == mainIndex);

                Image image = imageService.addImageToProduct(file, savedProduct, isMain);

                savedProduct.getImages().add(image);
            }
        }

        return savedProduct;
    }


    @Transactional(readOnly = true)
    @Cacheable("product")
    public Product GetProductById(int id){
        return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product with id " + id + " not found"));
    };
    @Transactional(readOnly = true)
    @Cacheable("allProducts")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<Product> getProductsByCategoryId(int categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category with ID=" + categoryId + " not found"));

        return productRepository.findAllByCategoryId(category.getId());

    }
    @Transactional
    public Product updateProduct(int id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());
                    product.setAvailAmount(updatedProduct.getAvailAmount());
                    product.setDiscount(updatedProduct.getDiscount());
                    product.setCreatedDate(updatedProduct.getCreatedDate());
                    product.setCategory(updatedProduct.getCategory());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new NoSuchElementException("Product with id " + id + " not found"));
    }


    @Transactional
    public void deleteProduct(int id) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
    }
}
