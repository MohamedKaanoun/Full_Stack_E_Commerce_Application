package com.backend.EcommerceProjectBackEnd.controller;

import com.backend.EcommerceProjectBackEnd.exception.ProductException;
import com.backend.EcommerceProjectBackEnd.model.Product;
import com.backend.EcommerceProjectBackEnd.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) List<String> color,
            @RequestParam(required = false) List<String> size,
            @RequestParam(required = false, defaultValue = "0") Integer minPrice,
            @RequestParam(required = false, defaultValue = "1000") Integer maxPrice,
            @RequestParam(required = false, defaultValue = "0") Integer minDiscount,
            @RequestParam(required = false, defaultValue = "price_high") String sort,
            @RequestParam(required = false) String stock,
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        Page<Product> res = productService.getAllProducts(category, color, size, minPrice,
                maxPrice, minDiscount, sort, stock, pageNumber, pageSize);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findProductByIdHandler(@PathVariable Integer productId) throws ProductException {
        Product product = productService.findProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    }

}
