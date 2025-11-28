package com.dpi.entity;


import java.time.LocalDateTime;

import com.dpi.status.ProductStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Double price;
	private String name;
	private Integer quantity;
	private Double discount;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String updatedBy;
	private String creatdBy;
	private String updatedReason;
	@Enumerated(EnumType.STRING) 
	private ProductStatus status;
	
	

}
