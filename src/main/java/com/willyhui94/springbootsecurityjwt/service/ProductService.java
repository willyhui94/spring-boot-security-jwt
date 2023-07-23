package com.willyhui94.springbootsecurityjwt.service;

import com.willyhui94.springbootsecurityjwt.entity.bo.ProductBO;
import com.willyhui94.springbootsecurityjwt.entity.dto.ProductDTO;

import java.util.List;

public interface ProductService {

  List<ProductBO> getAllProducts();

  ProductBO findProductById(Integer id);

  ProductBO createProduct(ProductDTO productDTO);

  List<ProductBO> createBatchProducts(List<ProductDTO> productDTOList);
}
