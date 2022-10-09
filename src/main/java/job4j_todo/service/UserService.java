package job4j_todo.service;

import job4j_todo.model.User;
import job4j_todo.store.UserRepository;
import job4j_todo.store.UserStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserStore userStore;

    private final UserRepository userRepository;

    public Optional<User> add(User user) {
       return userRepository.create(user);
    }

    public Optional<User> findUserByLoginAndPwd(String login, String password) {
        return userRepository.findByLikeLoginAndPwd(login, password);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

}
