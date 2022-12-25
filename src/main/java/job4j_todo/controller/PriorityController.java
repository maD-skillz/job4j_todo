package job4j_todo.controller;

import job4j_todo.service.PriorityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@AllArgsConstructor
public class PriorityController {

    private final PriorityService priorityService;

    @GetMapping("/addPriority")
    public String addPriority(Model model) {
        model.addAttribute(model.addAttribute("priorities", priorityService.getAllPriorities()));
        return "addPriority";
    }

    @GetMapping("/createPriority")
    public String createPriority(Model model) {
        model.addAttribute(model.addAttribute("priorities", priorityService.getAllPriorities()));
        return "addPriority";
    }

    @GetMapping("/formAddPriority")
    public String formAddPriority(Model model) {
        model.addAttribute(model.addAttribute("priorities", priorityService.getAllPriorities()));
        return "addPriority";
    }

}
