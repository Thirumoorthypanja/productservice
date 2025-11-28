package com.dpi.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dpi.entity.Product;
import com.dpi.payload.ProductDto;
import com.dpi.payload.ProductResponse;
import com.dpi.status.ProductStatus;

public class ConvertData {

	public static ProductResponse convertDaoToResponse(Product product, String msg) {

		ProductResponse response = new ProductResponse();
		List<Product> productData = new ArrayList<>();
		productData.add(product);
		response.setData(productData);
		response.setMessage(msg);
		response.setTimestamp(LocalDateTime.now());

		return response;

	}

	public static ProductResponse convertDaoToResponse(List<Product> product, String msg) {

		ProductResponse response = new ProductResponse();
		response.setData(product);
		response.setMessage(msg);
		response.setTimestamp(LocalDateTime.now());
		response.setData(product);
		return response;
	}

	public static Product convertToEntity(ProductDto productDto) {

		Product product = new Product();
		product.setCreatdBy(productDto.getCreatdBy());
		product.setDiscount(productDto.getDiscount());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setStatus(ProductStatus.ACTIVE);
		product.setUpdatedBy(productDto.getUpdatedBy());
		product.setUpdatedAt(LocalDateTime.now());
		product.setUpdatedReason(productDto.getUpdatedReason());
		product.setId(productDto.getId());
		return product;
	}
}
