package job4j_todo.controller;


import job4j_todo.service.CheckUser;
import job4j_todo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
@AllArgsConstructor
public class IndexController {

    private final TaskService taskService;

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("user", CheckUser.userCheck(session));
        model.addAttribute("users", taskService.findAllUsers());
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

}
