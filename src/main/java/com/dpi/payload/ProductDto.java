package com.dpi.payload;

import java.time.LocalDateTime;

import com.dpi.status.ProductStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

	
	private Integer id;
	private Double price;
	private String name;
	private Integer quantity;
	private Double discount;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
	private String updatedBy;
	private String creatdBy;
	private String updatedReason;
	@Enumerated(EnumType.STRING)
	private ProductStatus status;
}
