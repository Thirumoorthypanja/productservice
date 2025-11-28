package com.dpi.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpi.entity.Product;
import com.dpi.exception.ProductNotFoundException;
import com.dpi.payload.ProductDto;
import com.dpi.payload.ProductResponse;
import com.dpi.repo.ProductRepo;
import com.dpi.service.ProductService;
import com.dpi.status.ProductStatus;
import com.dpi.util.ConvertData;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Override
	public ProductResponse addProduct(ProductDto productDto) {
		productDto.setCreateAt(LocalDateTime.now());
		return ConvertData.convertDaoToResponse(productRepo.save(ConvertData.convertToEntity(productDto)),
				"Product saved successfully");
	}

	@Override
	public ProductResponse updateProduct(ProductDto productDto) {

		Optional<Product> productRt = productRepo.findById(productDto.getId());
		if (productRt.isPresent()) {
			return ConvertData.convertDaoToResponse(productRepo.save(ConvertData.convertToEntity(productDto)),
					"Product updated successfully");
		} else {
			throw new ProductNotFoundException("Invalid Input");
		}
	}

	@Override
	public ProductResponse deletProduct(Integer id) {
		Optional<Product> product = productRepo.findById(id);
		if (!product.isPresent()) {
			throw new ProductNotFoundException("Product with id : '" + id + "' not found");
		}
		product.get().setStatus(ProductStatus.INACTIVE);
		productRepo.save(product.get());
		return ConvertData.convertDaoToResponse(product.get(), "Product deleted successfully");
	}

	@Override
	public ProductResponse getProductByName(String name) {

		List<Product> product = productRepo.findByName(name);
		if (product.size() > 1) {
			throw new ProductNotFoundException("Product with name '" + name + "' not found");
		}
		return ConvertData.convertDaoToResponse(product, "Product retrieved successfully");
	}

	@Override
	public ProductResponse getAllProducts() {
		List<Product> products = (List<Product>) productRepo.findAll();
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No products available");
		}
		return ConvertData.convertDaoToResponse(products, "All products retrieved successfully");
	}

	@Override
	public ProductResponse searchByKeyword(String keyword) {
		List<Product> nameContainProducts = productRepo.findByNameContainingIgnoreCase(keyword);
		if (nameContainProducts.isEmpty()) {
			throw new ProductNotFoundException("No products available");
		}
		return ConvertData.convertDaoToResponse(nameContainProducts, "All products retrieved successfully");

	}

}
