package job4j_todo.store;

import job4j_todo.model.Priority;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PriorityRepository {

    private final CrudRepository crudRepository;

    public Optional<Priority> create(Priority priority) {
        crudRepository.run(session -> session.persist(priority));
        return Optional.of(priority);
    }

    public Optional<Priority> findById(int priorityId) {
        return crudRepository.optional(
                "from Priority where id = :fId", Priority.class,
                Map.of("fId", priorityId)
        );
    }

    public List<Priority> findAll() {
        return crudRepository.query("from Priority", Priority.class);
    }

}
