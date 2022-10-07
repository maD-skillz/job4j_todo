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
            "/loginPage",
            "/login",
            "/createUser",
            "/users",
            "/formAddUser",
            "/addUser",
            "/loginPage?fail=true",
            "addUser?fail=true"
    );

    private boolean checkAllowedPages(String page) {
        return allowedPages.stream().anyMatch(s -> s.endsWith(page));
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
