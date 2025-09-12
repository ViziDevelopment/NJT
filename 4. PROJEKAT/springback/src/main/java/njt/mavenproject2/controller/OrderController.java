// njt.mavenproject2.controller.OrderController.java
package njt.mavenproject2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

import njt.mavenproject2.dto.impl.OrderDto;
import njt.mavenproject2.entity.impl.OrderStatus;
import njt.mavenproject2.servis.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/order")
@Tag(name = "Orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> all() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> byId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Create order with items in a single transaction")
    public ResponseEntity<OrderDto> create(@Valid @RequestBody @NotNull OrderDto dto) {
        try {
            OrderDto saved = service.create(dto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Create order failed: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderDto> updateStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        try {
            return new ResponseEntity<>(service.updateStatus(id, status), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("Order deleted", HttpStatus.OK);
    }
}
