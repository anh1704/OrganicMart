package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> allUsers = this.userService.getAllUsersByEmail("hiiii@gmail.com");
        System.out.println(allUsers);
        model.addAttribute("test", "test");  // 1. key: tien to muon truy cap ben view, 2. value: gia tri cua bien
        model.addAttribute("name", "Hoi Dan IT");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) { // Model là đối tượng để truyền dữ liệu từ controller sang view
        return "admin/user/table-user";  // đường dẫn đến file view
    }

    // đến trang tạo mới người dùng (hiển thị form)
    @RequestMapping("/admin/user/create")  // method: GET
    public String getCreateUserPage(Model model) {
        return "admin/user/create";  // duong dan den file view
    }

    // tạo mới người dùng (xử lý form, lưu vào database)
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String handleCreateUser(Model model, @ModelAttribute("newUser") User user) { // form - kieu gia tri - ten bien
        this.userService.handleSaveUser(user);
        return "hello";
    }
}

