package job4j_todo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


@Component
public class AuthFilter implements Filter {

    private final Set<String> allowedPages = Set.of(
            "localhost:8080/loginPage",
            "localhost:8080/login",
            "localhost:8080/createUser",
            "localhost:8080/users",
            "localhost:8080/formAddUser",
            "localhost:8080/addUser"
    );

    private boolean checkAllowedPages(String page) {
        return allowedPages.stream().anyMatch(s -> s.contains(page));
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (checkAllowedPages(uri)) {
            chain.doFilter(req, res);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/loginPage");
            return;
        }
        chain.doFilter(req, res);
    }
}
