package com.backend.EcommerceProjectBackEnd.controller;

import com.backend.EcommerceProjectBackEnd.exception.ProductException;
import com.backend.EcommerceProjectBackEnd.model.Product;
import com.backend.EcommerceProjectBackEnd.request.CreateProductRequest;
import com.backend.EcommerceProjectBackEnd.response.ApiResponse;
import com.backend.EcommerceProjectBackEnd.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {
    final private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req){
    Product product = productService.createProductRequest(req);

    return new ResponseEntity<>(product , HttpStatus.CREATED);
    }

    @DeleteMapping("{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer productId) throws ProductException{
        productService.deleteProduct(productId);
        ApiResponse res = new ApiResponse();
        res.setMessage("Product deleted successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res , HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Product>> findAllProduct(
            @RequestParam(defaultValue = "") String category,
            @RequestParam(defaultValue = "") List<String> colors,
            @RequestParam(defaultValue = "") List<String> sizes,
            @RequestParam(defaultValue = "0") Integer minPrice,
            @RequestParam(defaultValue = "100000") Integer maxPrice,
            @RequestParam(defaultValue = "0") Integer minDiscount,
            @RequestParam(defaultValue = "price_low") String sort,
            @RequestParam(defaultValue = "") String stock,
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {

        Page<Product> products = productService.getAllProducts(
                category, colors, sizes, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product
            , @PathVariable Integer productId) throws ProductException{
        Product product1 = productService.updateProduct(productId , product);

        return new ResponseEntity<Product>(product , HttpStatus.CREATED);

    }
}
