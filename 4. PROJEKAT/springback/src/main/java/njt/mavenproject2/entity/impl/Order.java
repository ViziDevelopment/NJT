/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.mavenproject2.entity.impl;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vanja
 */

@Entity
@Table(name = "orders")
public class Order {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
     @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status = OrderStatus.CREATED;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = true)
    private String napomena;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();
    
    public Order() {}
    public Order(Long id) { this.id = id; }
    public Order(Long id, String napomena, User user) {
        this.id = id; this.napomena = napomena; this.user = user;
    }
    
    public void addItem(OrderItem item){
        item.setOrder(this);
        this.items.add(item);
    }
    public void removeItem(OrderItem item){
        item.setOrder(null);
        this.items.remove(item);
    }
     public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getNapomena() { return napomena; }
    public void setNapomena(String napomena) { this.napomena = napomena; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    
}
