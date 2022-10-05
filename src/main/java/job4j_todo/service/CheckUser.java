package job4j_todo.service;

import job4j_todo.model.User;

import javax.servlet.http.HttpSession;

public final class CheckUser {

    private CheckUser() {
    }
    public static User userCheck(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        return user;
    }
}
