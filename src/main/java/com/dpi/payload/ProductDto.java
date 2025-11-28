package com.dpi.payload;

import java.time.LocalDateTime;

import com.dpi.status.ProductStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

	private Integer id;

	@NotBlank(message = "SKU is required")
	@Size(max = 100, message = "SKU must not exceed 100 characters")
	private String sku;

	@NotBlank(message = "Name is required")
	private String name;

	@Size(max = 1000, message = "Description must not exceed 1000 characters")
	private String description;

	@NotNull(message = "Price is required")
	@Positive(message = "Price must be positive")
	private Double price;

	@NotBlank(message = "Currency is required")
	@Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a valid ISO 4217 code (e.g., USD, EUR)")
	private String currency;

	@NotNull(message = "Quantity is required")
	@Min(value = 0, message = "Quantity cannot be negative")
	private Integer quantity;

	private Double discount;

	@Positive(message = "Weight must be positive")
	private Double weight;

	private String category;
	private String brand;
	private String imageUrl;

	private LocalDateTime createAt;
	private LocalDateTime updateAt;
	private String updatedBy;
	private String createdBy;
	private String updateReason;
	@Enumerated(EnumType.STRING)
	private ProductStatus status;
}
