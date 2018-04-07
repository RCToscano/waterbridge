
package br.com.waterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.MessageDAO;
import br.com.waterbridge.modelo.Message;

public class MessageBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Connection connection;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			
//            if (req.getParameter("acao").equals("1")) {
//            	req.getRequestDispatcher("/jsp/grafico/graficoconsumomes.jsp").forward(req, res);
//            }  
            
			connection = ConnectionFactory.getConnection();
			
	    	Message message = new Message();
	    	//message.setTexto(req.getQueryString());
	    	message.setTexto(req.getReader().readLine());
	    	
	    	
	    	MessageDAO messageDAO = new MessageDAO(connection);
	    	messageDAO.inserir(message);
			
            String json = "ok";
            
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write(json); 
        }
        catch (Exception e) {
        	
        	Message message = new Message();
	    	message.setTexto(e.toString());
	    	
	    	MessageDAO messageDAO = new MessageDAO(connection);
	    	try {messageDAO.inserir(message);} catch (SQLException e1) {}
        	
        	res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write("erro");
        }
		finally {
			if(connection != null) {
				try {connection.close();} catch (SQLException ex) {}
			}
		}
    }
}


