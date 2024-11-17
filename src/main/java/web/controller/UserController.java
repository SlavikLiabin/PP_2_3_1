package web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/user-create")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

//    @GetMapping("/deleteUser/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return "redirect:/";
//    }
//
//    @GetMapping("/updateUser/{id}")
//    public String updateUser(@PathVariable Long id, Model model) {
//        model.addAttribute("update", userService.getUserById(id));
//        return "user-update";
//    }
//
//    @PostMapping("/updateUser/{getId}")
//    public String saveUpdateUser(@PathVariable Long getId, @ModelAttribute("user") User user) {
//        user.setId(getId);
//        userService.updateUser(user);
//        return "redirect:/";
//    }

    // Заменяем @PathVariable на @RequestParam
    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    // Заменяем @PathVariable на @RequestParam
    @GetMapping("/updateUser")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("update", userService.getUserById(id));
        return "user-update";
    }

    // Заменяем @PathVariable на @RequestParam
    @PostMapping("/updateUser")
    public String saveUpdateUser(@RequestParam("id") Long id, @ModelAttribute("user") User user) {
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/";
    }
}

