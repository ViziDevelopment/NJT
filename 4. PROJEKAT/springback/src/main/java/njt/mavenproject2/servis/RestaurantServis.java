/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.mavenproject2.servis;

import java.util.List;
import java.util.stream.Collectors;
import njt.mavenproject2.dto.impl.RestaurantDto;
import njt.mavenproject2.entity.impl.Restaurant;
import njt.mavenproject2.mapper.impl.RestaurantMapper;
import njt.mavenproject2.repository.impl.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vanja
 */
@Service
public class RestaurantServis {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    @Autowired
    public RestaurantServis(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }
    
    public List<RestaurantDto> findAll() {
        return restaurantRepository.findAll()
                .stream()
                .map(restaurantMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public RestaurantDto findById(Long id) throws Exception {
        return restaurantMapper.toDto(restaurantRepository.findById(id));
    }
    public RestaurantDto create(RestaurantDto dto) {
        Restaurant restaurant = restaurantMapper.toEntity(dto);
        restaurantRepository.save(restaurant);
        return restaurantMapper.toDto(restaurant);
    }
    public void deleteById(Long id) {
        restaurantRepository.deleteById(id);
    }
    public RestaurantDto update(RestaurantDto dto) {
        
        Restaurant updated = restaurantMapper.toEntity(dto);
        restaurantRepository.save(updated);  

        return restaurantMapper.toDto(updated);
    }

}
