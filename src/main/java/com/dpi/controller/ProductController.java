package com.dpi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dpi.payload.ProductDto;
import com.dpi.payload.ProductResponse;
import com.dpi.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/product")
@Slf4j
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<ProductResponse> addProduct(@jakarta.validation.Valid @RequestBody ProductDto productDto) {
		log.info("Received request to add product: {}", productDto.getName());
		return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ProductResponse> updateProduct(@jakarta.validation.Valid @RequestBody ProductDto productDto) {
		log.info("Received request to update product: {}", productDto.getId());
		return ResponseEntity.ok(productService.updateProduct(productDto));
	}

	@GetMapping
	public ResponseEntity<ProductResponse> getAllProducts() {
		log.info("Received request to fetch all products");
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductResponse> deleteProduct(@PathVariable("id") Integer id) {
		log.info("Received request to delete product: {}", id);
		return ResponseEntity.ok(productService.deleteProduct(id));
	}

	@GetMapping("/{name}")
	public ResponseEntity<ProductResponse> getProductByName(@PathVariable("name") String name) {
		log.info("Received request to fetch product by name: {}", name);
		return ResponseEntity.ok(productService.getProductByName(name));
	}

	@GetMapping("/search")
	public ResponseEntity<ProductResponse> searchByKeyword(@RequestParam("keyword") String keyword) {
		log.info("Received request to search products by keyword: {}", keyword);
		return ResponseEntity.ok(productService.searchByKeyword(keyword));
	}

}
