package job4j_todo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@Component
public class AuthFilter implements Filter {

    private final Set<String> allowedPages = new HashSet<String>();

    private boolean checkAllowedPages(String page) {
        if (page.endsWith("loginPage")) {
            allowedPages.add(page);
        }
        if (page.endsWith("login")) {
            allowedPages.add(page);
        }
        if (page.endsWith("createUser")) {
            allowedPages.add(page);
        }

        if (page.endsWith("users")) {
            allowedPages.add(page);
        }
        if (page.endsWith("formAddUser")) {
            allowedPages.add(page);
        }
        if (page.endsWith("addUser")) {
            allowedPages.add(page);
        }
        return allowedPages.contains(page);
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
