package com.willyhui94.springbootsecurityjwt.repository;

import com.willyhui94.springbootsecurityjwt.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  Optional<Product> findById(Integer id);
}
