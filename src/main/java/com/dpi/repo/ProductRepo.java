package com.dpi.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dpi.entity.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer>{

	List<Product> findByName(String productName);

	List<Product> findByNameContainingIgnoreCase(String keyword);

}
