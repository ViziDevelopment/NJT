/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.mavenproject2.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import njt.mavenproject2.dto.Dto;

/**
 *
 * @author vanja
 */
public class RestaurantDto implements  Dto{
    private Long id;
    @NotEmpty(message = "name is required.")
    private String name;
    @NotBlank(message = "Address is required.")
    @Size(max = 200, message = "Address cannot be longer than 200 characters.")
    private String address;

    public RestaurantDto() {
    }

    
    public RestaurantDto(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

     
}
