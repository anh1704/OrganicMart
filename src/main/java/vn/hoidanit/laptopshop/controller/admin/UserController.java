package vn.hoidanit.laptopshop.controller.admin;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

import java.io.*;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin/user")
    public String getUserPage(Model model, @RequestParam(value = "page")Optional<String> pageOptional) { // Model là đối tượng để truyền dữ liệu từ controller sang view
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Pageable pageable = PageRequest.of(page - 1, 3);
        Page<User> userPage = this.userService.getAllUsers(pageable);
        List<User> allUsers = userPage.getContent();

        model.addAttribute("users", allUsers);  // users: giá trị truyền sang view  allUsers: giá trị cần truyền (gan vao key)
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        return "admin/user/show";  // đường dẫn đến file view
    }

    // đến trang tạo mới người dùng (hiển thị form)
    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());  // newUser: giá trị truyền sang view,  new User(): giá trị cần truyền (gan vao key)
        return "admin/user/create";  // duong dan den file view
    }

    // tạo mới người dùng (xử lý form, lưu vào database)
    @PostMapping( "/admin/user/create")
    public String handleCreateUser(
            @ModelAttribute("newUser")
            @Valid User user,
            BindingResult newUserBindingResult,
            @RequestParam("imageFile") MultipartFile file
    ) { // form - kieu gia tri - ten bien

        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>>> " + error.getField() + " - " + error.getDefaultMessage());
        }

        // validate
        if (newUserBindingResult.hasErrors()) {
            return "admin/user/create";
        }

        String avatar = this.uploadService.handleUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(user.getPassword());

        user.setAvatar(avatar);
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName(user.getRole().getName()));

        // save
        this.userService.handleSaveUser(user); // lưu người dùng vào database
        return "redirect:/admin/user";  // mapping vao duong dan /admin/user
    }

    // lay thong tin va den trang chi tiet nguoi dung
    @GetMapping("/admin/user/{id}")
    public String getUserDetail(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", this.userService.getUserById(id));
//        model.addAttribute("userID", id);
        return "admin/user/detail";
    }

    // get page update user
    @GetMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("updateUser", this.userService.getUserById(id));  // updateUser: giá trị truyền sang view,  this.userService.getUserById(id): current user
        return "admin/user/update";
    }

    // update user
    @PostMapping("/admin/user/update")
    public String handleUpdateUser(Model model, @ModelAttribute("updateUser") User user) {  // @ModelAttribute("updateUser"): lay gia tri tu form
        this.userService.updateUser(user);
        return "redirect:/admin/user";
    }

    // delete user
    @GetMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteUserById(id);
        return "redirect:/admin/user";
    }

}

