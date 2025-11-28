package com.dpi.entity;

import java.time.LocalDateTime;

import com.dpi.status.ProductStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "products")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(unique = true, nullable = false)
	private String sku;

	private String name;

	@Column(length = 1000)
	private String description;

	private Double price;

	@Column(length = 3)
	private String currency;

	private Integer quantity;
	private Double discount;
	private Double weight;
	private String category;
	private String brand;
	private String imageUrl;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	private String updatedBy;
	private String createdBy;
	private String updateReason;

	@Enumerated(EnumType.STRING)
	private ProductStatus status;

}
