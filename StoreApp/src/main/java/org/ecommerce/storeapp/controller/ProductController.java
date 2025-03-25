package org.ecommerce.storeapp.controller;

import org.ecommerce.storeapp.model.Product;
import org.ecommerce.storeapp.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> productList = productService.getAllProducts();

        return productList.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(productList);
    }

    @GetMapping("/get-by-category-id/{categoryId}")
    public ResponseEntity<List<Product>> getByCategoryId(@PathVariable int categoryId) {
        List<Product> productList = productService.getProductsByCategoryId(categoryId);

        return productList.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(productList);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        return ResponseEntity.ok(productService.GetProductById(id));
    }

    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> create(@RequestPart("product") Product product,
                                          @RequestPart("images") List<MultipartFile> images,
                                          @RequestParam("mainImageIndex") Integer  mainImageIndex) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProductWithImages(product, images, mainImageIndex));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable int id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

}
