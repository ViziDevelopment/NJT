// njt.mavenproject2.mapper.impl.OrderMapper.java
package njt.mavenproject2.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;
import njt.mavenproject2.dto.impl.OrderDto;
import njt.mavenproject2.entity.impl.Order;
import njt.mavenproject2.entity.impl.User;
import njt.mavenproject2.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements DtoEntityMapper<OrderDto, Order> {

    private final OrderItemMapper itemMapper;

    public OrderMapper(OrderItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public OrderDto toDto(Order e) {
        List items = e.getItems()
                .stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());

        return new OrderDto(
                e.getId(),
                e.getStatus(),
                e.getCreatedAt(),
                e.getNapomena(),
                e.getUser().getId(),
                items
        );
    }

    @Override
    public Order toEntity(OrderDto t) {
        Order o = new Order();
        o.setId(t.getId());
        if (t.getStatus() != null) o.setStatus(t.getStatus());
        o.setNapomena(t.getNapomena());

        if (t.getUserId() != null) {
            o.setUser(new User(t.getUserId())); // referenca na user-a samo sa ID-em
        }

        if (t.getItems() != null) {
            t.getItems().forEach(d -> o.addItem(itemMapper.toEntity(d)));
        }
        return o;
    }
}
