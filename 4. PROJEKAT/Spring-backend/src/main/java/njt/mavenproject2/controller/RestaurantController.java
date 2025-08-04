/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.mavenproject2.controller;

 
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import njt.mavenproject2.dto.impl.RestaurantDto;
import njt.mavenproject2.servis.RestaurantServis;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
/**
 *
 * @author vanja
 */
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    
    private final RestaurantServis restaurantService;

    public RestaurantController(RestaurantServis restaurantService) {
        this.restaurantService = restaurantService;
    } 
    @GetMapping
    @Operation(summary = "Retrieve all Restaurant entities.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = RestaurantDto.class), mediaType = "application/json")
    })
    public ResponseEntity<List<RestaurantDto>> getAll() {
        return new ResponseEntity<>(restaurantService.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getById(
            @NotNull(message = "Should not be null or empty.")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(restaurantService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RestaurantController exception: " + ex.getMessage());
        }
    }
    @PostMapping
    @Operation(summary = "Create a new Restaurant entity.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = RestaurantDto.class), mediaType = "application/json")
    })
    public ResponseEntity<RestaurantDto> addRestaurant(@Valid @RequestBody @NotNull RestaurantDto restaurantDto) {
        try {
         
            RestaurantDto saved = restaurantService.create(restaurantDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while saving restaurant: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        try {
            restaurantService.deleteById(id);
            return new ResponseEntity<>("Restaurant successfully deleted.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Restaurant does not exist: " + id, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Restaurant entity.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = RestaurantDto.class), mediaType = "application/json")
    })
    public ResponseEntity<RestaurantDto> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody RestaurantDto restaurantDto) {
        try {
            restaurantDto.setId(id); // Osiguravamo da se ažurira pravi entitet
            RestaurantDto updated = restaurantService.update(restaurantDto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while updating restaurant: " + ex.getMessage());
        }
    }
    
    
    
}
