package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;


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

    @NotNull
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$") // kiem tra email hop le
    private String email;

    @NotEmpty(message = "Password can't be empty")
    private String password;

    @NotEmpty(message = "Full name can't be empty")
    private String fullName;

//    @NotEmpty(message = "Address can't be empty")
    private String address;

//    @NotEmpty(message = "Number phone can't be empty")
    private String phone;

    private String avatar;

    // User many -> to one -> Role (nhieu user thuoc ve 1 role)
    @ManyToOne
    @JoinColumn(name = "role_id")   // khoa ngoai - foreign key
    private Role role;

    // 1 user co nhieu order
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    // 1 user co 1 cart
    @OneToOne(mappedBy = "user")
    private Cart carts;

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
