package job4j_todo.controller;

import job4j_todo.model.Task;
import job4j_todo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/addTask")
    public String addTask(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "addTask";
    }

    @GetMapping("/createTask")
    public String createTask(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "addTask";
    }

    @GetMapping("/formAddTask")
    public String formAddTask(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "addTask";
    }

    @GetMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task) {
        taskService.update(task);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateTask/{taskId}")
    public String formUpdateTask(Model model, @PathVariable("taskId") int id) {
        model.addAttribute("task", taskService.findById(id));
        return "updateTask";
    }

    @PostMapping("/deleteTask")
    public String deleteTask(@ModelAttribute Task task) {
        taskService.delete(task.getId());
        return "redirect:/index";
    }

    @GetMapping("/formDeleteTask/{taskId}")
    public String formDeleteTask(Model model, @PathVariable("taskId") int id) {
        model.addAttribute("task", taskService.delete(id));
        return "updateTask";
    }

    @PostMapping("/viewTask")
    public String viewTask(@ModelAttribute Task task) {
        taskService.findById(task.getId());
        return "redirect:/updateTask";
    }

    @GetMapping("/formViewTask/{taskId}")
    public String formViewTask(Model model, @PathVariable("taskId") int id) {
        model.addAttribute("task", taskService.findById(id));
        return "viewTask";
    }

    @PostMapping("/setDoneTask")
    public String setDoneTask(@ModelAttribute Task task) {
        task.setDone(true);
        taskService.update(task);
        return "redirect:/index";
    }

    @GetMapping("/formSetDoneTask/{taskId}")
    public String formSetDoneTask(Model model, @PathVariable("taskId") int id) {
        model.addAttribute("task", taskService.findById(id));
        return "setDoneTask";
    }

    @PostMapping("/createTask")
    public String createTask(@ModelAttribute Task task) {
        task.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        taskService.add(task);
        return "redirect:/index";
    }
}
