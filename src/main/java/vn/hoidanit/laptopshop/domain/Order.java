package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalPrice;

    // nhieu order thuoc ve 1 user
    @ManyToOne
    @JoinColumn(name = "user_id")  // khoa ngoai - foreign key
    private User user;

    // 1 order co nhieu order detail
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
