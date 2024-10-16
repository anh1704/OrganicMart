package vn.hoidanit.laptopshop.controller.admin;

import jakarta.validation.Valid;
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

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> allUsers = this.userService.getAllUsersByEmail("hiiii@gmail.com");
        System.out.println(allUsers);
        model.addAttribute("test", "test");  // 1. key: tien to muon truy cap ben view, 2. value: gia tri cua bien
        model.addAttribute("name", "Hoi Dan IT");
        return "hello";
    }

    @GetMapping("/admin/user")
    public String getUserPage(Model model) { // Model là đối tượng để truyền dữ liệu từ controller sang view
        List<User> allUsers = this.userService.getAllUsers();
        model.addAttribute("users", allUsers);  // users: giá trị truyền sang view  allUsers: giá trị cần truyền (gan vao key)
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

    // den trang xoa nguoi dung
    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    // xoa nguoi dung
    @PostMapping("/admin/user/delete")
    public String handleDeleteUser(@ModelAttribute("newUser") User user) {
        this.userService.deleteUserById(user.getId());
        return "redirect:/admin/user";
    }
}

