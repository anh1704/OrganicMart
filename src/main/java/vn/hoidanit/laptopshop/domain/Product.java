package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotEmpty(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotNull
    @NotEmpty(message = "Mô tả không được để trống")
    @Column(name = "description", columnDefinition = "VARCHAR(1000)")
    private String description;

    @NotNull
    @DecimalMin(value = "0", message = "Giá phải lớn hơn 0")
    private double price;

    @NotNull
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private int quantity;

    @NotNull
    private String category;

    @Column(name = "image_path")
    private String imageProduct;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", imagePath='" + imageProduct + '\'' +
                '}';
    }
}
