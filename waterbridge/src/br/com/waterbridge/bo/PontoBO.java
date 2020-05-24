package br.com.waterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.reldao.RelPontoDAO;
import br.com.waterbridge.relmodelo.RelPonto;

@WebServlet("/PontoBO")
public class PontoBO extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
		if (req.getParameter("acao") != null && req.getParameter("acao").equals("1")) {//LISTAR PONTOS
			listarPontos(req, res);
		}
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("2")) {//LISTAR PONTOS POR ID
			listarPontosPorId(req, res);
		}
	}
		
	public void listarPontos(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		
		Connection connection = null;		
		
		try {
			
			connection = ConnectionFactory.getConnection();
			
			RelPontoDAO relPontoDAO = new RelPontoDAO(connection);			
			List<RelPonto> listRelPonto = relPontoDAO.listar("");
			
			req.setAttribute("listRelPonto", new Gson().toJson(listRelPonto));			
			req.getRequestDispatcher("/jsp/mapa/mapaponto.jsp").forward(req, res);
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
	public void listarPontosPorId(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		
		Connection connection = null;
		
		try {
			
			connection = ConnectionFactory.getConnection();
			
			RelPontoDAO relPontoDAO = new RelPontoDAO(connection);
			
			String sql = "";
			Long idPontoTp = 1l;
			if(req.getParameter("idPontoTp") != null) {
				idPontoTp = Long.parseLong(req.getParameter("idPontoTp"));				
			}
			sql = "WHERE  ID_PONTOTP = " + idPontoTp ;
			List<RelPonto> listRelPonto = relPontoDAO.listar(sql);
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new Gson().toJson(listRelPonto));
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
}
