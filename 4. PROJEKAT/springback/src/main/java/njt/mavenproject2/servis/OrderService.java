// njt.mavenproject2.servis.OrderService.java
package njt.mavenproject2.servis;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import njt.mavenproject2.dto.impl.OrderDto;
import njt.mavenproject2.dto.impl.OrderItemDto;
import njt.mavenproject2.entity.impl.*;
import njt.mavenproject2.mapper.impl.OrderMapper;
import njt.mavenproject2.repository.impl.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orders;
    private final OrderMapper mapper;

    @PersistenceContext
    private EntityManager em;

    public OrderService(OrderRepository orders, OrderMapper mapper) {
        this.orders = orders;
        this.mapper = mapper;
    }

    public List<OrderDto> findAll() {
        return orders.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public OrderDto findById(Long id) throws Exception {
        return mapper.toDto(orders.findById(id));
    }

    /** Kreira narudžbinu i SVE njene stavke u JEDNOJ transakciji */
    @Transactional
    public OrderDto create(OrderDto dto) throws Exception {
        // 1) Napravi Order
        Order order = new Order();
        order.setStatus(dto.getStatus() != null ? dto.getStatus() : OrderStatus.CREATED);
        order.setNapomena(dto.getNapomena());

        // 2) Poveži korisnika (LAZY referenca, bez dodatnog SELECT-a)
        if (dto.getUserId() == null) {
            throw new Exception("userId is required");
        }
        order.setUser(em.getReference(User.class, dto.getUserId()));

        // 3) Napravi stavke i uveži ih
        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new Exception("Order must contain at least one item");
        }

        for (OrderItemDto it : dto.getItems()) {
            OrderItem oi = new OrderItem();
            // referenca na Product
            Product p = em.getReference(Product.class, it.getProductId());
            oi.setProduct(p);
            oi.setQuantity(it.getQuantity());

            // cena – ako nije poslata iz fronta, uzmi trenutnu iz proizvoda (pretpostavka da postoji getPrice())
            double price = it.getUnitPrice() > 0 ? it.getUnitPrice() : p.getPrice();
            oi.setUnitPrice(price);

            order.addItem(oi); // sinhronizuje owner stranu i listu
        }

        // 4) Sačuvaj – zbog cascade=ALL biće upisane i stavke
        orders.save(order);

        // 5) Vrati DTO
        return mapper.toDto(order);
    }

    /** Promena statusa (npr. CONFIRMED/CANCELED/COMPLETED) */
    @Transactional
    public OrderDto updateStatus(Long id, OrderStatus status) throws Exception {
        Order o = orders.findById(id);
        o.setStatus(status);
        orders.save(o);
        return mapper.toDto(o);
    }

    /** Brisanje narudžbine + stavki u jednoj transakciji (orphanRemoval) */
    @Transactional
    public void deleteById(Long id) {
        orders.deleteById(id);
    }
}
