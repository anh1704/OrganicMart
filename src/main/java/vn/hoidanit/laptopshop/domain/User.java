package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String address;
    private String phone;
    private String avatar;

    // User many -> to one -> Role (nhieu user thuoc ve 1 role)
    @ManyToOne
    @JoinColumn(name = "role_id")   // khoa ngoai - foreign key
    private Role role;

    // 1 user co nhieu order
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
