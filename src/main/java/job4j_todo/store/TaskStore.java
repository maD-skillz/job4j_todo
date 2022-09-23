package job4j_todo.store;

import job4j_todo.model.Task;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskStore implements AutoCloseable {

    private final SessionFactory sf;

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
                "update job4j_todo.model.Task t set t.description = :newDesc, t.done = :newDone where t.id = :fId")
                .setParameter("newDesc", task.getDescription())
                .setParameter("newDone", task.isDone())
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
                "delete from job4j_todo.model.Task t where t.id = :fId")
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
                "from job4j_todo.model.Task t where t.description like :fDesc")
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
                "update job4j_todo.model.Task t set t.done = true where t.id = :fId")
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
                        "from job4j_todo.model.Task t where t.done is true")
                .list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Task> findTaskByDoneFalse() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Task> result = session.createQuery(
                        "from job4j_todo.model.Task t where t.done is false")
                .list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public Optional<Task> findTaskById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Task result = (Task) session.createQuery(
                "from job4j_todo.model.Task t where t.id = :fId")
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
                "from job4j_todo.model.Task"
        ).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void close() {
        sf.close();
    }
}
