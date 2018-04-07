package br.com.waterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.UserDAO;
import br.com.waterbridge.modelo.User;

public class Login extends HttpServlet {

    private static String relat = "";

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Connection connection = null;

        relat = "";

        String email = req.getParameter("email");
        String senha = req.getParameter("password");

        User user = new User();

        try {

            connection = ConnectionFactory.getConnection();

            UserDAO userDAO = new UserDAO(connection);

            user = userDAO.login(email, senha);
            
            if (req.getParameter("r") != null) {

                relat = req.getParameter("r");
            }

            if (relat.equals("login")) {

                if (userDAO.login(email, senha) == null) {

                    //usuario inexistente
                    req.setAttribute("loginErro", "Usuario/Senha Incorreta. Tente Novamente!");
                    req.getRequestDispatcher("/index.jsp").forward(req, res);
                } 
                else if (user.getSituacao().equals("I")) {

                    //verificando se o usuario esta ativo
                    req.setAttribute("loginErro", "Usuario Inativo no Sistema!");
                    req.getRequestDispatcher("/index.jsp").forward(req, res);
                } 
                else {

                    //vai para home page
                    req.getSession().setAttribute("user", user);
                    req.getRequestDispatcher("/jsp/home.jsp").forward(req, res);
                }                
            } 
            else if (relat.equals("logout")) {
               
                //invalidar a sessao
                HttpSession session = req.getSession(true);
                session.putValue("user", null);
                req.getSession().invalidate();
                req.getRequestDispatcher("/index.jsp").forward(req, res);
            } 
        }
        catch (Exception e) {
        	System.out.println("erro " + e.toString());
            req.setAttribute("erro", e.toString());
            req.getRequestDispatcher("/jsp/erro.jsp").forward(req, res);
        }
        finally {

            if(connection != null) {

                try {connection.close();} catch (SQLException ex) {}
            }
        }
    }
}
