/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.mavenproject2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import njt.mavenproject2.dto.impl.ProductDto;
import njt.mavenproject2.dto.impl.RestaurantDto;
import njt.mavenproject2.servis.ProductServis;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author vanja
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/product")
public class ProductController {
    
    private final ProductServis productService;

    public ProductController(ProductServis productService) {
        this.productService = productService;
    }
    
    @GetMapping()
    @Operation(summary = "Retrieve all Product entities.") 
    public ResponseEntity<List<ProductDto>> getAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
    
    @PostMapping
    @Operation(summary = "Create a new Product.")
    public ResponseEntity<ProductDto> addProduct(
            @Valid @RequestBody ProductDto productDto) {
        try {
            ProductDto saved = productService.create(productDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greška prilikom čuvanja proizvoda: " + ex.getMessage());
        }
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        try {
            productService.deleteById(id);
            return new ResponseEntity<>("Product successfully deleted.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Product does not exist: " + id, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Restaurant entity.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = RestaurantDto.class), mediaType = "application/json")
    })
    public ResponseEntity<ProductDto> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody ProductDto productDto) {
        try {
            productDto.setId(id); // Osiguravamo da se ažurira pravi entitet
            ProductDto updated = productService.update(productDto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while updating restaurant: " + ex.getMessage());
        }
    }
    
    @GetMapping("/restaurant/{restaurantId}")
    @Operation(summary = "Retrieve all products for a given restaurant.")
    public ResponseEntity<List<ProductDto>> getByRestaurant(@PathVariable Long restaurantId) {
        List<ProductDto> products = productService.findByRestaurant(restaurantId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
