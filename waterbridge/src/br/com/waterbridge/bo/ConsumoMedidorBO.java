
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

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.BridgeDAO;
import br.com.waterbridge.dao.CondominioDAO;
import br.com.waterbridge.dao.EmpresaDAO;
import br.com.waterbridge.dao.MedidorDAO;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.Empresa;
import br.com.waterbridge.modelo.Medidor;
import br.com.waterbridge.modelo.User;
import br.com.waterbridge.reldao.RelConsumoMedidorDAO;
import br.com.waterbridge.relmodelo.RelConsumoMedidor;

public class ConsumoMedidorBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                   
		if (req.getParameter("acao") != null && req.getParameter("acao").equals("1")) {//ENTRA NA TELA LISTA USUARIO X MEDIDOR
			
			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				EmpresaDAO empresaDAO = new EmpresaDAO(connection);
				List<Empresa> listEmpresa = empresaDAO.listarPorUsuario(user.getIdUser());
				
				req.setAttribute("listEmpresa", listEmpresa);
        		req.getRequestDispatcher("/jsp/relatorios/consumomedidordia.jsp").forward(req, res);
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("2")) {//POPULA COMBO CONDOMINIO

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String sql = "";
            String json = "";
			
			try {
			
				connection = ConnectionFactory.getConnection();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = new ArrayList<Condominio>();		
				listCondominio = condominioDAO.listarPorUsuario(user.getIdUser(), Long.parseLong(req.getParameter("idEmpresa")));
				
				json = new Gson().toJson(listCondominio);
				
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("3")) { //POPULA COMBO BRIDGE

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String sql = "";
            String json = "";
			
			try {
			
				connection = ConnectionFactory.getConnection();
				
				BridgeDAO bridgeDAO = new BridgeDAO(connection);
				List<Bridge> listBridge = new ArrayList<Bridge>();	
				listBridge = bridgeDAO.listarWaterBridgePorUsuario(user.getIdUser(), Long.parseLong(req.getParameter("idCondominio")));

				json = new Gson().toJson(listBridge);
				
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("4")) { //POPULA COMBO MEDIDOR

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String sql = "";
            String json = "";
			
			try {
			
				connection = ConnectionFactory.getConnection();
				
				MedidorDAO medidorDAO = new MedidorDAO(connection);
				List<Medidor> listMedidor = new ArrayList<Medidor>();		
				listMedidor = medidorDAO.listarPorUsuario(user.getIdUser(), Long.parseLong(req.getParameter("idBridge")));

				json = new Gson().toJson(listMedidor);
				
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("5")) { //LISTAR CONSUMO MEDIDOR DIA

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String sql = "";
            String json = "";
			
			try {

				sql += "WHERE ID_CONSUMO > 0 " ;
				if(req.getParameter("idEmpresa") != null && !req.getParameter("idEmpresa").equals("")) {
					sql += "AND   ID_EMPRESA = " + req.getParameter("idEmpresa") + " ";
				}
				if(req.getParameter("idCondominio") != null && !req.getParameter("idCondominio").equals("")) {
					sql += "AND   ID_CONDOMINIO = " + req.getParameter("idCondominio") + " ";
				}
				if(req.getParameter("idBridge") != null && !req.getParameter("idBridge").equals("")) {
					sql += "AND   ID_BRIDGE = " + req.getParameter("idBridge") + " ";
				}
				if(req.getParameter("idMedidor") != null && !req.getParameter("idMedidor").equals("")) {
					sql += "AND   ID_MEDIDOR = " + req.getParameter("idMedidor") + " ";
				}
				if(req.getParameter("dtInicio") != null && !req.getParameter("dtInicio").equals("")) {
					
					sql += "AND   DTINSERT >= '" + Auxiliar.formataDtBanco(req.getParameter("dtInicio")) + " 00:00' " +
						   "AND   DTINSERT <= '" + Auxiliar.formataDtBanco(req.getParameter("dtFim")) + " 23:59' " ;
				}
				sql += "ORDER BY DTINSERT ";

				connection = ConnectionFactory.getConnection();
				
				RelConsumoMedidorDAO relConsumoMedidorDAO = new RelConsumoMedidorDAO(connection);
				List<RelConsumoMedidor> listRelConsumoMedidor = relConsumoMedidorDAO.listar(sql);
				
				json = new Gson().toJson(listRelConsumoMedidor);
				
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);   
			}
	        catch (Exception e) {
	        	System.out.println("erro: " + e.toString());
	            req.setAttribute("erro", e.toString());
	            req.getRequestDispatcher("/jsp/erro.jsp").forward(req, res);
	        }
			finally {
				if(connection != null) {
					try {connection.close();} catch (SQLException e) {}
				}
			}	
        }
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("6")) { //LISTAR CONSUMO MEDIDOR DIA GRAFICO

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String sql = "";
			
			try {

				sql += "WHERE ID_CONSUMO > 0 " ;
				if(req.getParameter("idEmpresa") != null && !req.getParameter("idEmpresa").equals("")) {
					sql += "AND   ID_EMPRESA = " + req.getParameter("idEmpresa") + " ";
				}
				if(req.getParameter("idCondominio") != null && !req.getParameter("idCondominio").equals("")) {
					sql += "AND   ID_CONDOMINIO = " + req.getParameter("idCondominio") + " ";
				}
				if(req.getParameter("idBridge") != null && !req.getParameter("idBridge").equals("")) {
					sql += "AND   ID_BRIDGE = " + req.getParameter("idBridge") + " ";
				}
				if(req.getParameter("idMedidor") != null && !req.getParameter("idMedidor").equals("")) {
					sql += "AND   ID_MEDIDOR = " + req.getParameter("idMedidor") + " ";
				}
				if(req.getParameter("dtInicio") != null && !req.getParameter("dtInicio").equals("")) {
					
					sql += "AND   DTINSERT >= '" + Auxiliar.formataDtBanco(req.getParameter("dtInicio")) + " 00:00' " +
						   "AND   DTINSERT <= '" + Auxiliar.formataDtBanco(req.getParameter("dtFim")) + " 23:59' " ;
				}
				sql += "ORDER BY DTINSERT ";

				connection = ConnectionFactory.getConnection();
				
				BridgeDAO bridgeDAO = new BridgeDAO(connection);
				Bridge bridge = bridgeDAO.buscarPorId(Long.parseLong(req.getParameter("idBridge")));
				System.out.println("bridge " + bridge);
				
				MedidorDAO medidorDAO = new MedidorDAO(connection);
				Medidor medidor = medidorDAO.buscarPorId(req.getParameter("idMedidor"));
				
				RelConsumoMedidorDAO relConsumoMedidorDAO = new RelConsumoMedidorDAO(connection);
				List<RelConsumoMedidor> listRelConsumoMedidor = relConsumoMedidorDAO.listar(sql);
				
				req.setAttribute("bridge", bridge);
				req.setAttribute("medidor", medidor);
				req.setAttribute("dtInicio", req.getParameter("dtInicio"));
				req.setAttribute("dtFim", req.getParameter("dtFim"));
				req.setAttribute("listRelConsumoMedidor", listRelConsumoMedidor);
        		req.getRequestDispatcher("/jsp/relatorios/consumomedidordiagrafico.jsp").forward(req, res);   
			}
	        catch (Exception e) {
	        	System.out.println("erro: " + e.toString());
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

