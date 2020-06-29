
package br.com.waterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.EmpresaDAO;
import br.com.waterbridge.modelo.Empresa;
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
				
//				RelPressaoLastDAO relPressaoLastDAO = new RelPressaoLastDAO(connection);
//				List<RelPressaoLast> listRelPressaoLast = relPressaoLastDAO.listar(sql);
				
				EmpresaDAO empresaDAO = new EmpresaDAO(connection);
				List<Empresa> listEmpresa = empresaDAO.listarPorUsuario(user.getIdUser());
			
				//req.setAttribute("listRelPressaoLast", listRelPressaoLast);
				req.setAttribute("listEmpresa", listEmpresa);
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
				
				sql = "WHERE     VW_PRESSAOLAST.ID_EMPRESA = " + req.getParameter("idEmpresa") + " " ;
				if(req.getParameter("idCondominio") != null && req.getParameter("idCondominio").toString().trim().length() > 0) {
					sql += "AND       VW_PRESSAOLAST.ID_CONDOMINIO IN(" + req.getParameter("idCondominio") + ") ";
				}
				sql += "ORDER BY  VW_PRESSAOLAST.CONDOMINIO ";

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



