/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.mavenproject2.mapper.impl;

import njt.mavenproject2.dto.impl.RestaurantDto;
import njt.mavenproject2.entity.impl.Restaurant;
import njt.mavenproject2.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author vanja
 */
@Component
public class RestaurantMapper implements DtoEntityMapper<RestaurantDto, Restaurant> {

    @Override
    public RestaurantDto toDto(Restaurant e) {
        return  new RestaurantDto(e.getId(), e.getName(), e.getAddress());
        
    }

    @Override
    public Restaurant toEntity(RestaurantDto t) {
        return new Restaurant(t.getId(), t.getName(), t.getAddress(), null);
    }
    
}
