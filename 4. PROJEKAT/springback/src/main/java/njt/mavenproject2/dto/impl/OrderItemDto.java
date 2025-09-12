// njt.mavenproject2.dto.impl.OrderItemDto.java
package njt.mavenproject2.dto.impl;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import njt.mavenproject2.dto.Dto;

public class OrderItemDto implements Dto {
    private Long id;

    @NotNull(message = "productId is required")
    private Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    // Čuvamo cenu u trenutku poručivanja (read/write u DTO-u)
    private double unitPrice;

    public OrderItemDto() {}

    public OrderItemDto(Long id, Long productId, int quantity, double unitPrice) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
}
