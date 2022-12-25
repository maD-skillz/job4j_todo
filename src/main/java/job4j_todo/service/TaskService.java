package job4j_todo.service;

import job4j_todo.model.Priority;
import job4j_todo.model.Task;
import job4j_todo.store.TaskStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskStore store;

    public Optional<Task> findById(int id) {
        return store.findTaskById(id);
    }

    public Optional<Task> add(Task task) {
        return store.addTask(task);
    }

    public boolean update(Task task) {
        return store.replaceTask(task);
    }

    public boolean updateIfDone(Task task) {
        return store.setTaskDone(task);
    }

    public boolean delete(int id) {
        return store.deleteTask(id);
    }

    public List<Task> findByDesc(String key) {
        return store.findTaskByDesc(key);
    }

    public List<Task> findAll() {
        return store.findAllTasks();
    }

    public List<Task> findDone() {
        return store.findTaskByDoneTrue();
    }

    public List<Task> findUndone() {
        return store.findTaskByDoneFalse();
    }

    public Optional<Task> findByUserId(int id) {
        return store.findTaskByUserId(id);
    }

    public Optional<Priority> findByPriorityById(int id) {
        return store.findByPriorityId(id);
    }

    public List<Task> findAllUsers() {
        return store.findAllUsersAndTasks();
    }

    public List<Task> findAllPrioritiesByTask() {
        return store.findAllPrioritiesAndTasks();
    }

    public List<Priority> allPriorities() {
        return store.findAllPriorities();
    }

}
