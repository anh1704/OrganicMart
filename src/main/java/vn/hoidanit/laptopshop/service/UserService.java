package vn.hoidanit.laptopshop.service;

import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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


}
