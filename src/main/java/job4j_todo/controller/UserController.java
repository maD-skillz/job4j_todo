package job4j_todo.controller;

import job4j_todo.model.User;
import job4j_todo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/createUser")
    public String createUser(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "addUser";
    }

    @GetMapping("/formAddUser")
    public String formAddUser(Model model, @RequestParam(name = "fail", required = false)
    Boolean fail, HttpSession session) {
        model.addAttribute("user", userService.checkUser(session));
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("fail", fail != null);
        return "addUser";
    }

    @GetMapping("/users")
    public String users(Model model, HttpSession session) {
        model.addAttribute("user", userService.checkUser(session));
        model.addAttribute("users", userService.findAllUsers());
        return "users";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute User user) {
        Optional<User> regUser = userService.add(user);
        if (regUser.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        return "redirect:/loginPage";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> userDb = userService.findUserByLoginAndPwd(
                user.getLogin(), user.getPassword()
        );
        if (userDb.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", userDb.get());
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginPage";
    }

}
