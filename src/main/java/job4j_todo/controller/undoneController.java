package job4j_todo.controller;

import job4j_todo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class undoneController {

    private final TaskService taskService;

    @GetMapping("/undone")
    public String undone(Model model) {
        model.addAttribute("undone", taskService.findUndone());
        return "undone";
    }
}
