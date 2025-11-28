package com.dpi.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpi.entity.Product;
import com.dpi.exception.ProductNotFoundException;
import com.dpi.mapper.ProductMapper;
import com.dpi.payload.ProductDto;
import com.dpi.payload.ProductResponse;
import com.dpi.repo.ProductRepo;
import com.dpi.service.ProductService;
import com.dpi.status.ProductStatus;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductRepo productRepo;
	private final ProductMapper productMapper;

	public ProductServiceImpl(ProductRepo productRepo, ProductMapper productMapper) {
		this.productRepo = productRepo;
		this.productMapper = productMapper;
	}

	@Override
	public ProductResponse addProduct(ProductDto productDto) {
		log.info("Adding new product: {}", productDto.getName());
		Product product = productMapper.toEntity(productDto);
		product = productRepo.save(product);
		return createResponse(product, "Product saved successfully");
	}

	@Override
	public ProductResponse updateProduct(ProductDto productDto) {
		log.info("Updating product with ID: {}", productDto.getId());
		Product product = productRepo.findById(productDto.getId())
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productDto.getId()));

		productMapper.updateProductFromDto(productDto, product);
		product = productRepo.save(product);
		return createResponse(product, "Product updated successfully");
	}

	@Override
	public ProductResponse deleteProduct(Integer id) {
		log.info("Deleting product with ID: {}", id);
		Product product = productRepo.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product with id : '" + id + "' not found"));

		product.setStatus(ProductStatus.INACTIVE);
		productRepo.save(product);
		return createResponse(product, "Product deleted successfully");
	}

	@Override
	@Transactional(readOnly = true)
	public ProductResponse getProductByName(String name) {
		log.info("Fetching product by name: {}", name);
		List<Product> products = productRepo.findByName(name);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("Product with name '" + name + "' not found");
		}
		return createResponse(products, "Product retrieved successfully");
	}

	@Override
	@Transactional(readOnly = true)
	public ProductResponse getAllProducts() {
		log.info("Fetching all products");
		List<Product> products = (List<Product>) productRepo.findAll();
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No products available");
		}
		return createResponse(products, "All products retrieved successfully");
	}

	@Override
	@Transactional(readOnly = true)
	public ProductResponse searchByKeyword(String keyword) {
		log.info("Searching products with keyword: {}", keyword);
		List<Product> products = productRepo.findByNameContainingIgnoreCase(keyword);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No products available");
		}
		return createResponse(products, "Products retrieved successfully");
	}

	private ProductResponse createResponse(Product product, String message) {
		ProductResponse response = new ProductResponse();
		response.setData(List.of(product));
		response.setMessage(message);
		response.setTimestamp(LocalDateTime.now());
		return response;
	}

	private ProductResponse createResponse(List<Product> products, String message) {
		ProductResponse response = new ProductResponse();
		response.setData(products);
		response.setMessage(message);
		response.setTimestamp(LocalDateTime.now());
		return response;
	}

}
