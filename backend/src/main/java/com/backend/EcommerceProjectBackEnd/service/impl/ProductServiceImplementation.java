package com.backend.EcommerceProjectBackEnd.service.impl;

import com.backend.EcommerceProjectBackEnd.exception.ProductException;
import com.backend.EcommerceProjectBackEnd.model.Category;
import com.backend.EcommerceProjectBackEnd.model.Product;
import com.backend.EcommerceProjectBackEnd.repository.CategoryRepository;
import com.backend.EcommerceProjectBackEnd.repository.ProductRepository;
import com.backend.EcommerceProjectBackEnd.request.CreateProductRequest;
import com.backend.EcommerceProjectBackEnd.service.ProductService;
import com.backend.EcommerceProjectBackEnd.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImplementation.class);

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProductRequest(CreateProductRequest req) {
        Category topLevel = categoryRepository.findByName(req.getTopLevelCategory());
        if (topLevel == null) {
            topLevel = new Category();
            topLevel.setName(req.getTopLevelCategory());
            topLevel.setLevel(1);
            categoryRepository.save(topLevel);
        }

        Category secondLevel = categoryRepository.findByNameAndParent(req.getSecondLevelCategory(), topLevel.getName());
        if (secondLevel == null) {
            secondLevel = new Category();
            secondLevel.setName(req.getSecondLevelCategory());
            secondLevel.setLevel(2);
            secondLevel.setParentCategory(topLevel);
            categoryRepository.save(secondLevel);
        }

        Category thirdLevel = categoryRepository.findByNameAndParent(req.getThirdLevelCategory(), secondLevel.getName());
        if (thirdLevel == null) {
            thirdLevel = new Category();
            thirdLevel.setName(req.getThirdLevelCategory());
            thirdLevel.setLevel(3);
            thirdLevel.setParentCategory(secondLevel);
            categoryRepository.save(thirdLevel);
        }

        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setBrand(req.getBrand());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setDiscountPercent(req.getDiscountPercent());
        product.setDiscountPrice(req.getDiscountPrice());
        product.setImageUrl(req.getImageUrl());
        product.setSizes(req.getSize());
        product.setQuantity(req.getQuantity());
        product.setCategory(thirdLevel);
        product.setPrice(req.getPrice());
        product.setCreatedDate(LocalDateTime.now());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) throws ProductException {
        Product product = findProductById(id);
        product.getSizes().clear();
        productRepository.delete(product);
    }

    @Override
    public Product updateProduct(Integer id, Product p) throws ProductException {
        Product product = findProductById(id);

        if (p.getQuantity() != 0) {
            product.setQuantity(p.getQuantity());
        }

        productRepository.save(product);

        return product;
    }

    @Override
    public Product findProductById(Integer id) throws ProductException {
        Optional<Product> opt = productRepository.findById(id);

        if (opt.isPresent())
            return opt.get();
        else
            throw new ProductException("Product not found with id: " + id);
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        logger.debug("Fetching products with filters: category={}, colors={}, sizes={}, minPrice={}, maxPrice={}, minDiscount={}, sort={}, stock={}, pageNumber={}, pageSize={}",
                category, colors, sizes, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize); // Adjusted for zero-based page index

        List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);

        if (colors != null && !colors.isEmpty()) {
            products = products.stream()
                    .filter(product -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(product.getColor())))
                    .collect(Collectors.toList());
        }

        if (sizes != null && !sizes.isEmpty()) {
            products = products.stream()
                    .filter(product -> sizes.stream().anyMatch(s -> product.getSizes().stream().anyMatch(size -> size.getName().equalsIgnoreCase(s))))
                    .collect(Collectors.toList());
        }

        if (stock != null) {
            if (stock.equals("in_stock")) {
                products = products.stream()
                        .filter(product -> product.getQuantity() > 0)
                        .collect(Collectors.toList());
            } else if (stock.equals("out_of_stock")) {
                products = products.stream()
                        .filter(product -> product.getQuantity() < 1)
                        .collect(Collectors.toList());
            }
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), products.size());
        List<Product> pageContent = products.subList(start, end);

        logger.debug("Total products after filtering: {}", products.size());
        logger.debug("Products in current page: {}", pageContent.size());

        return new PageImpl<>(pageContent, pageable, products.size());
    }
}
