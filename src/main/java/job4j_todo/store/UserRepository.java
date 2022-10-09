package job4j_todo.store;

import job4j_todo.model.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public Optional<User> create(User user) {
        crudRepository.run(session -> session.persist(user));
        return Optional.of(user);
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        crudRepository.run(session -> session.merge(user));
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public void delete(int userId) {
        crudRepository.run(
                "delete from User where id = :fId",
                Map.of("fId", userId)
        );
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        return crudRepository.query("from User", User.class);
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int userId) {
        return crudRepository.optional(
                "from User where id = :fId", User.class,
                Map.of("fId", userId)
        );
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        return crudRepository.query(
                "from User where login like :fKey", User.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    /**
     * Найти пользователя по login и password.
     * @param login login, password pwd
     * @return Optional or user.
     */
    public Optional<User> findByLikeLoginAndPwd(String login, String pwd) {
        for (User user : findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(pwd)) {
                return Optional.of(user);

            }
        }
        return Optional.empty();
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        return crudRepository.optional(
                "from User where login = :fLogin", User.class,
                Map.of("fLogin", login)
        );
    }

    /**
     * Найти пользователя по password.
     * @param password password.
     * @return Optional or user.
     */
    public Optional<User> findByPassword(String password) {
        return crudRepository.optional(
                "from User where password = :fPass", User.class,
                Map.of("fPass", password)
        );
    }
    /**
     * Найти всех пользователей.
     * @return список пользователей.
     */
    public List<User> findAll() {
        return crudRepository.query("from User", User.class);
    }
}