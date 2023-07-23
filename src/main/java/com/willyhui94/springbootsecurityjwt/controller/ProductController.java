package com.willyhui94.springbootsecurityjwt.controller;

import com.willyhui94.springbootsecurityjwt.entity.ResponseResult;
import com.willyhui94.springbootsecurityjwt.entity.bo.ProductBO;
import com.willyhui94.springbootsecurityjwt.entity.dto.ProductDTO;
import com.willyhui94.springbootsecurityjwt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping("")
  public ResponseResult<List<ProductBO>> getAllProducts() {
    return ResponseResult.ok(productService.getAllProducts());
  }

  @GetMapping("/{productId}")
  public ResponseResult<ProductBO> getProductById(
      @PathVariable(name = "productId") Integer productId) {
    return ResponseResult.ok(productService.findProductById(productId));
  }

  @PostMapping("")
  public ResponseResult<ProductBO> createProduct(@RequestBody ProductDTO productDTO) {
    return ResponseResult.ok(productService.createProduct(productDTO));
  }

  @PostMapping("/batch-creation")
  public ResponseResult<List<ProductBO>> createBatchProducts(
      @RequestBody List<ProductDTO> productDTOList) {
    return ResponseResult.ok(productService.createBatchProducts(productDTOList));
  }
}
