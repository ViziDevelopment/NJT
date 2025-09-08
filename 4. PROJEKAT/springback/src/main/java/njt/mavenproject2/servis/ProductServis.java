/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.mavenproject2.servis;

import java.util.List;
import java.util.stream.Collectors;
import njt.mavenproject2.dto.impl.ProductDto;
import njt.mavenproject2.dto.impl.RestaurantDto;
import njt.mavenproject2.entity.impl.Product;
import njt.mavenproject2.entity.impl.Restaurant;
import njt.mavenproject2.mapper.impl.ProductMapper;
import njt.mavenproject2.repository.impl.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vanja
 */
@Service
public class ProductServis {
    private final ProductRepository  productRepository;
    private final ProductMapper productMapper;
    @Autowired
    public ProductServis(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    public List<ProductDto> findAll() {
        return productRepository.findAll() 
                 .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public ProductDto create(ProductDto dto) {
        Product product = productMapper.toEntity(dto);
        productRepository.save(product);
        return productMapper.toDto(product);
    }
    
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
    public ProductDto update(ProductDto dto) {
        
        Product updated = productMapper.toEntity(dto);
        productRepository.save(updated);  

        return productMapper.toDto(updated);
    }
    
    
    
    
    
}
