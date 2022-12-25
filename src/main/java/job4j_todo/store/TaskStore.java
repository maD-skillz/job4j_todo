package job4j_todo.store;

import job4j_todo.model.Priority;
import job4j_todo.model.Task;
import liquibase.pro.packaged.T;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskStore implements AutoCloseable {

    private final SessionFactory sf;

    private final CrudRepository crudRepository;

    public Optional<Task> addTask(Task task) {
            Session session = sf.openSession();
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
            session.close();
        return Optional.of(task);
    }

    public boolean replaceTask(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        int result = session.createQuery(
                "update Task t set t.description = :newDesc, t.done = :newDone, t.priority = :newPriority where t.id = :fId")
                .setParameter("newDesc", task.getDescription())
                .setParameter("newDone", task.isDone())
                .setParameter("newPriority", task.getPriority())
                .setParameter("fId", task.getId())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result > 0;
    }

    public boolean deleteTask(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        int result = session.createQuery(
                "delete from Task t where t.id = :fId")
                .setParameter("fId", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result > 0;
    }

    public List<Task> findTaskByDesc(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Task> result = session.createQuery(
                "from Task t where t.description like :fDesc")
                .setParameter("fDesc", key)
                .list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public boolean setTaskDone(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        int result = session.createQuery(
                "update Task t set t.done = true where t.id = :fId")
                .setParameter("fId", task.getId())
                .executeUpdate();
        session.getTransaction();
        session.close();
        return result > 0;
    }

    public List<Task> findTaskByDoneTrue() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Task> result = session.createQuery(
                        "from Task t where t.done is true")
                .list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Task> findTaskByDoneFalse() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Task> result = session.createQuery(
                        "from Task t where t.done is false")
                .list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public Optional<Task> findTaskById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Task result = (Task) session.createQuery(
                "from Task t where t.id = :fId")
                .setParameter("fId", id)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return Optional.of(result);
    }

    public List<Task> findAllTasks() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Task> result = session.createQuery(
                "from Task"
        ).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Task> findAllUsersAndTasks() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Task> result = session.createQuery(
                "from Task t left join User u on u.id = user_id"
        ).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Task> findAllPrioritiesAndTasks() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Task> result = session.createQuery(
                "from Task t join fetch Priority p on p.id = priority_id"
        ).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public Optional<Task> findTaskByUserId(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Task result = (Task) session.createQuery(
                        "select description, created, done from Task t where t.user.id = :fId")
                .setParameter("fId", id)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return Optional.of(result);
    }

    public Optional<Priority> findByPriorityId(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Priority result = (Priority) session.createQuery(
                        "from Priority where id = :fId")
                .setParameter("fId", id)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return Optional.of(result);
    }

    public List<Priority> findAllPriorities() {
        return crudRepository.query("from Priority", Priority.class);
    }

    @Override
    public void close() {
        sf.close();
    }
}
