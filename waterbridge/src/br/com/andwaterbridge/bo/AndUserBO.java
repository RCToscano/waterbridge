package br.com.andwaterbridge.bo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.andwaterbridge.dao.UserDAO;
import br.com.andwaterbridge.modelo.User;
import br.com.waterbridge.connection.ConnectionFactory;

public class AndUserBO extends HttpServlet {

	private static final long serialVersionUID = 1L;


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    
    	if(req.getParameter("acao") != null && req.getParameter("acao").equals("1")) {
    	
    		Connection connection = null;
    		
    		try {
         		
	    		connection = ConnectionFactory.getConnection();
	    	
            	String login = req.getParameter("login");
                String senha = req.getParameter("senha");
	            
            	UserDAO userDAO = new UserDAO(connection);
            	User user = userDAO.login(login, senha);
            		
                if (user == null) {//USUARIO NAO ENCONTRADO
                	write(res, "1");
                } 
                else if (user.getSituacao().equals("I")) {//USUARIO INATIVO
                	write(res, "3");
                } 
                else {//USUARIO ATIVO ENCONTRADO
                	write(res, new Gson().toJson(user));
                }                
	        }
	        catch (Exception e) {
	        	write(res, "erro " + e.toString());
	        }
	        finally {
	            if(connection != null) {
	                try {connection.close();} catch (SQLException ex) {}
	            }
	        }
    	}
    }
    
    private void write(HttpServletResponse response, String resposta) throws IOException {

        response.setContentType("text/html");
        //response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.print(resposta);
        writer.flush();
        writer.close();
    }
}
