// njt.mavenproject2.mapper.impl.OrderItemMapper.java
package njt.mavenproject2.mapper.impl;

import njt.mavenproject2.dto.impl.OrderItemDto;
import njt.mavenproject2.entity.impl.OrderItem;
import njt.mavenproject2.entity.impl.Product;
import njt.mavenproject2.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper implements DtoEntityMapper<OrderItemDto, OrderItem> {

    @Override
    public OrderItemDto toDto(OrderItem e) {
        return new OrderItemDto(
            e.getId(),
            e.getProduct().getId(),
            e.getQuantity(),
            e.getUnitPrice()
        );
    }

    @Override
    public OrderItem toEntity(OrderItemDto t) {
        OrderItem oi = new OrderItem();
        oi.setId(t.getId());
        // referenca na postojeći Product samo sa ID-em (bez dodatnog SELECT-a ako koristiš em.getReference u servisu)
        oi.setProduct(new Product(t.getProductId()));
        oi.setQuantity(t.getQuantity());
        oi.setUnitPrice(t.getUnitPrice());
        return oi;
    }
}
