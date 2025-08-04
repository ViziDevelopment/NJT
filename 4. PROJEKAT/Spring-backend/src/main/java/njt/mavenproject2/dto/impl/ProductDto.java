/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.mavenproject2.dto.impl;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import njt.mavenproject2.dto.Dto;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author vanja
 */
public class ProductDto implements Dto {
    private Long id;
    @NotEmpty(message = "name is required.")
     @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters.")
    private String name;
     @Size(max = 500, message = "Description can be at most 500 characters.")
    private String description;
    @NotNull(message = "Price is required.")
    @Positive(message = "Price must be greater than zero.")
    private Double price;
    
    @URL(message = "Image URL must be valid.")
    private String imageUrl;
    private Long restaurantId;
    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description, Double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductDto(Long id, String name, String description, Double price, String imageUrl, Long restaurantId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.restaurantId = restaurantId;
    }
 
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    
    
    
    
    
    
    
    
}
