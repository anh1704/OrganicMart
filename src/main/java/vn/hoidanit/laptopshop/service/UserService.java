package vn.hoidanit.laptopshop.service;

import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.repository.OrderRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import vn.hoidanit.laptopshop.repository.RoleRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public User handleSaveUser(User user) {
       User userData = this.userRepository.save(user);
         return userData;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email){
        return this.userRepository.findAllByEmail(email);
    }

    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User updateUser(User user) {
        if (user.getId() != null) {
            User existingUser = this.userRepository.findById(user.getId()).orElse(null);
            if (existingUser != null) {
                existingUser.setFullName(user.getFullName());
                existingUser.setAddress(user.getAddress());
                existingUser.setPhone(user.getPhone());
                return this.userRepository.save(existingUser);
            }
        }
        return this.userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name){
        return this.roleRepository.findByName(name);
    }

    public User registerDTOtoUser(RegisterDTO registerDTO){
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    public boolean checkEmailExist(String email){
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public long countUsers() {
        return this.userRepository.count();
    }

    public long countProducts() {
        return this.productRepository.count();
    }

    public long countOrders() {
        return this.orderRepository.count();
    }
}
