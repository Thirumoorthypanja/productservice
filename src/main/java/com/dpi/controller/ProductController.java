package com.dpi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dpi.payload.ProductDto;
import com.dpi.payload.ProductResponse;
import com.dpi.service.ProductService;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductDto productDto) {
		return new ResponseEntity<ProductResponse>(productService.addProduct(productDto), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductDto productDto) {
		return ResponseEntity.ok(productService.updateProduct(productDto));
	}

	@GetMapping
	public ResponseEntity<ProductResponse> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductResponse> deleteProduct(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(productService.deletProduct(id));
	}

	@GetMapping("/{name}")
	public ResponseEntity<ProductResponse> getProductByName(@PathVariable("name") String name) {
		return ResponseEntity.ok(productService.getProductByName(name));
	}
	
    @GetMapping("/{keyword}")
    public ResponseEntity<ProductResponse> searchByKeyword(@PathVariable("keyword") String keyword) {
        return ResponseEntity.ok(productService.searchByKeyword(keyword));
    }


}
