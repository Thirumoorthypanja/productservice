package com.dpi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.dpi.entity.Product;
import com.dpi.payload.ProductDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "createdAt", source = "createAt")
    @Mapping(target = "updatedAt", source = "updateAt")
    Product toEntity(ProductDto productDto);

    @Mapping(target = "createAt", source = "createdAt")
    @Mapping(target = "updateAt", source = "updatedAt")
    ProductDto toDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateProductFromDto(ProductDto productDto, @MappingTarget Product product);
}
