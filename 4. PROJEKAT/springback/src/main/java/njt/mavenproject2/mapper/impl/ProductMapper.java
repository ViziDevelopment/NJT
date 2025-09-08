/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.mavenproject2.mapper.impl;

import njt.mavenproject2.dto.impl.ProductDto;
import njt.mavenproject2.entity.impl.Product;
import njt.mavenproject2.entity.impl.Restaurant;
import njt.mavenproject2.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author vanja
 */
@Component
public class ProductMapper implements DtoEntityMapper<ProductDto,Product> {
    @Override
    public ProductDto toDto(Product e) { 
        Long restaurantId = e.getRestaurant() != null ? e.getRestaurant().getId() : null;
        return new ProductDto(
            e.getId(),
            e.getName(),
            e.getDescription(),
            e.getPrice(),
                e.getImageUrl(),
            restaurantId
        );
    }

    @Override
    public Product toEntity(ProductDto t) {
        Restaurant restaurant = t.getRestaurantId() != null ? new Restaurant(t.getRestaurantId()) : null;

        return new Product(
            t.getId(),
            t.getName(),
            t.getDescription(),
            t.getPrice(),
            t.getImageUrl(),
            restaurant
        );
    }

  
}
