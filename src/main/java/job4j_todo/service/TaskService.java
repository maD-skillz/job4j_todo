package job4j_todo.service;

import job4j_todo.model.Task;
import job4j_todo.store.TaskStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskStore store;

    public Task findById(int id) {
        return store.findTaskById(id);
    }

    public Task add(Task task) {
        return store.addTask(task);
    }

    public boolean update(Task task) {
        return store.replaceTask(task);
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
}
