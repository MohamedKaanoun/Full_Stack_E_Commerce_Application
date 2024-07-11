package com.backend.EcommerceProjectBackEnd.service;

import com.backend.EcommerceProjectBackEnd.exception.ProductException;
import com.backend.EcommerceProjectBackEnd.model.Product;
import com.backend.EcommerceProjectBackEnd.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    public List<Product> findAllProducts();
    public Product createProductRequest(CreateProductRequest req);

    public void deleteProduct(Integer id) throws ProductException;

    public Product updateProduct(Integer id , Product product) throws ProductException;

    public Product findProductById(Integer id) throws ProductException;

    public List<Product> findProductByCategory(String category);

    Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes
            , Integer minPrice, Integer maxPrice, Integer minDiscount
            , String sort, String stock, Integer pageNumber, Integer pageSize);

}
