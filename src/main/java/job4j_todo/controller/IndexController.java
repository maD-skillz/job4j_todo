package job4j_todo.controller;

import job4j_todo.model.User;
import job4j_todo.service.TaskService;
import job4j_todo.service.UserService;
import job4j_todo.store.UserStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
@AllArgsConstructor
public class IndexController {

    private final TaskService taskService;

    private final UserService userService;

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("user", userService.checkUser(session));
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

}
