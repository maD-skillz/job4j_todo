package job4j_todo.service;

import job4j_todo.model.Priority;
import job4j_todo.store.PriorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PriorityService {

    private final PriorityRepository priorityRepository;


    public Optional<Priority> addPriority(Priority priority) {
        return priorityRepository.create(priority);
    }

    public List<Priority> getAllPriorities() {
           return priorityRepository.findAll();
       }

    public Optional<Priority> getPriorityById(int id) {
        return priorityRepository.findById(id);
    }

}
