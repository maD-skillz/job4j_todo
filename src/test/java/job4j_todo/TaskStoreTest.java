package job4j_todo;

import job4j_todo.model.Task;
import job4j_todo.store.TaskStore;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class TaskStoreTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Test
    void findByIdStoreTest() throws Exception {
        try (var taskStore = new TaskStore(sf)) {
            Task task = new Task();
            task.setDescription("New Task");
            task.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            task.setDone(false);
            taskStore.addTask(task);
            Optional<Task> result = taskStore.findTaskById(task.getId());
            assertThat(result.get().getDescription(), is(task.getDescription()));
        }
    }

}
