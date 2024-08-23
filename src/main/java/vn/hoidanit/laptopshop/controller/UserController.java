package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        String test = this.userService.handleHello();
        model.addAttribute("test", test);  // 1. key: tien to muon truy cap ben view, 2. value: gia tri cua bien
        model.addAttribute("name", "Hoi Dan IT");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) { // Model la doi tuong de truyen du lieu tu controller sang view
        model.addAttribute("newUser", new User());
        return "admin/user/create";  // duong dan den file view
    }

    @RequestMapping(value = "/admin/user/create1", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User acd) { // Model la doi tuong de truyen du lieu tu controller sang view   form - kieu gia tri - ten bien
        System.out.println("run here" + acd);
        return "hello";  // duong dan den file view
    }
}

