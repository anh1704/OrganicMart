package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_detail")
@Setter
@Getter
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;
    private Double totalPrice;

    // nhieu order detail thuoc ve 1 order
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // nhieu order detail thuoc ve 1 product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
