package com.dpi.service;

import com.dpi.payload.ProductDto;
import com.dpi.payload.ProductResponse;

public interface ProductService {

	ProductResponse addProduct(ProductDto productDto);

	ProductResponse updateProduct(ProductDto productDto);

	ProductResponse deleteProduct(Integer id);

	ProductResponse getProductByName(String name);

	ProductResponse getAllProducts();

	ProductResponse searchByKeyword(String keyword);

}
