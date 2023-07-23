package com.willyhui94.springbootsecurityjwt.service.impl;

import com.willyhui94.springbootsecurityjwt.constant.BusinessCodeEnum;
import com.willyhui94.springbootsecurityjwt.entity.Product;
import com.willyhui94.springbootsecurityjwt.entity.bo.ProductBO;
import com.willyhui94.springbootsecurityjwt.entity.dto.ProductDTO;
import com.willyhui94.springbootsecurityjwt.exception.ServiceException;
import com.willyhui94.springbootsecurityjwt.repository.ProductRepository;
import com.willyhui94.springbootsecurityjwt.service.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @PostConstruct
  public void initDB() {
    List<Product> products = new ArrayList<>();
    Product product1 = Product.builder().id(10001).name("Product 10001").price(10.99).build();
    Product product2 = Product.builder().id(10002).name("Product 10002").price(20.99).build();
    Product product3 = Product.builder().id(10003).name("Product 10003").price(30.99).build();
    products.add(product1);
    products.add(product2);
    products.add(product3);
    this.productRepository.saveAll(products);
  }

  @Override
  public List<ProductBO> getAllProducts() {
    List<Product> productList = this.productRepository.findAll();

    List<ProductBO> productBOList =
        productList.stream().map(product -> this.buildProductBO(product)).toList();
    return productBOList;
  }

  @Override
  public ProductBO findProductById(Integer id) {
    Product product =
        this.productRepository
            .findById(id)
            .orElseThrow(() -> new ServiceException(BusinessCodeEnum.BUSINESS_CODE_90002));

    return buildProductBO(product);
  }

  @Override
  public ProductBO createProduct(ProductDTO productDTO) {
    Product product =
        Product.builder().name(productDTO.getName()).price(productDTO.getPrice()).build();
    this.productRepository.save(product);

    return buildProductBO(product);
  }

  @Override
  public List<ProductBO> createBatchProducts(List<ProductDTO> productDTOList) {
    List<Product> productList =
        productDTOList.stream()
            .map(
                productDTO ->
                    Product.builder()
                        .name(productDTO.getName())
                        .price(productDTO.getPrice())
                        .build())
            .toList();
    this.productRepository.saveAll(productList);

    List<ProductBO> productBOList =
        productList.stream().map(product -> this.buildProductBO(product)).toList();
    return productBOList;
  }

  private ProductBO buildProductBO(Product product) {
    return ProductBO.builder()
        .id(product.getId())
        .name(product.getName())
        .price(product.getPrice())
        .build();
  }
}
