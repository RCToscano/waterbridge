
package br.com.waterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.CondominioDAO;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.User;
import br.com.waterbridge.reldao.RelPressaoLastDAO;
import br.com.waterbridge.relmodelo.RelPressaoLast;

public class ReservatorioBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                   
		if (req.getParameter("acao") != null && req.getParameter("acao").equals("1")) {//ENTRA NA GRAFICO RESERVATORIOS
			
			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String sql = "";
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				RelPressaoLastDAO relPressaoLastDAO = new RelPressaoLastDAO(connection);
				List<RelPressaoLast> listRelPressaoLast = relPressaoLastDAO.listar(sql);
			
				req.setAttribute("listRelPressaoLast", listRelPressaoLast);
        		req.getRequestDispatcher("/jsp/reservatorio/reservatoriografico.jsp").forward(req, res);
			}
	        catch (Exception e) {
	            req.setAttribute("erro", e.toString());
	            req.getRequestDispatcher("/jsp/erro.jsp").forward(req, res);
	        }
			finally {
				if(connection != null) {
					try {connection.close();} catch (SQLException e) {}
				}
			}
        }
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("2")) {

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String sql = "";
			
			try {
			
				connection = ConnectionFactory.getConnection();
				
				RelPressaoLastDAO relPressaoLastDAO = new RelPressaoLastDAO(connection);
				List<RelPressaoLast> listRelPressaoLast = relPressaoLastDAO.listar(sql);
				
				String json = new Gson().toJson(listRelPressaoLast);
				
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);   
			}
	        catch (Exception e) {
	        	System.out.println("erro " + e.toString());
	            req.setAttribute("erro", e.toString());
	            req.getRequestDispatcher("/jsp/erro.jsp").forward(req, res);
	        }
			finally {
				if(connection != null) {
					try {connection.close();} catch (SQLException e) {}
				}
			}	
        }
    }
}



