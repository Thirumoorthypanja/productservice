package com.dpi.payload;

import java.time.LocalDateTime;
import java.util.List;

import com.dpi.entity.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {

	    private String message;
	    private List<Product> data;
	    private LocalDateTime timestamp;
}
