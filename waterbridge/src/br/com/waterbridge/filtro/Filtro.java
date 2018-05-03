package br.com.waterbridge.filtro;
 
import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.waterbridge.modelo.User;
 
@WebFilter(value={"/jsp/*"},
           dispatcherTypes={DispatcherType.REQUEST, DispatcherType.FORWARD})
public class Filtro implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {}
 
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");

        if(user == null) {
            
            req.setAttribute("loginErro", "Usuario não está logado no sistema");
            req.getRequestDispatcher("/index.jsp").forward(req, res);
        }
        else {
            
            chain.doFilter(request, response);
        }
    }

    public void destroy() {}
}