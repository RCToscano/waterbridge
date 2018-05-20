
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
import br.com.waterbridge.dao.BridgeDAO;
import br.com.waterbridge.dao.CondominioDAO;
import br.com.waterbridge.dao.EmpresaDAO;
import br.com.waterbridge.dao.MedidorDAO;
import br.com.waterbridge.dao.UserDAO;
import br.com.waterbridge.dao.UserMedidorDAO;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.Empresa;
import br.com.waterbridge.modelo.Medidor;
import br.com.waterbridge.modelo.User;
import br.com.waterbridge.modelo.UserMedidor;
import br.com.waterbridge.reldao.RelMedidorDAO;
import br.com.waterbridge.reldao.RelUserMedidorDAO;
import br.com.waterbridge.relmodelo.RelMedidor;
import br.com.waterbridge.relmodelo.RelUserMedidor;

public class UsuarioMedidorBO extends HttpServlet {

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
				
				req.setAttribute("tituloTela", "Víncular Usuário Medidor");
				req.setAttribute("acao", "VinculoConsumidorBO?acao=2");
				req.setAttribute("btNome", "Buscar");
				req.setAttribute("listEmpresa", listEmpresa);
				req.getRequestDispatcher("/jsp/usuariomedidor/listausuariomedidor.jsp").forward(req, res);
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
				if(req.getParameter("idEmpresa") != null && !req.getParameter("idEmpresa").equals("")) {
					sql += "WHERE ID_CONDOMINIO > 0 " +
						   "AND   ID_EMPRESA = " + req.getParameter("idEmpresa");
					listCondominio = condominioDAO.listar(sql);
				}

				json = new Gson().toJson(listCondominio);
				
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);   
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
				if(req.getParameter("idCondominio") != null && !req.getParameter("idCondominio").equals("")) {
					sql += "WHERE ID_BRIDGE > 0 " +
						   "AND   ID_CONDOMINIO = " + req.getParameter("idCondominio");
					listBridge = bridgeDAO.listar(sql);
				}

				json = new Gson().toJson(listBridge);
				
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);   
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
				if(req.getParameter("idBridge") != null && !req.getParameter("idBridge").equals("")) {
					sql += "WHERE ID_MEDIDOR > 0 " +
						   "AND   ID_BRIDGE = " + req.getParameter("idBridge");
					listMedidor = medidorDAO.listar(sql);
				}

				json = new Gson().toJson(listMedidor);
				
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);   
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("5")) { //LISTA USUARIO MEDIDOR

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String sql = "";
            String json = "";
			
			try {

				sql += "WHERE ID_CONDOMINIO > 0 " ;
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

				connection = ConnectionFactory.getConnection();
				
				RelMedidorDAO relUsuarioMedidorDAO = new RelMedidorDAO(connection);
				List<RelMedidor> listRelUsuarioMedidor = relUsuarioMedidorDAO.listar(sql);
				
				json = new Gson().toJson(listRelUsuarioMedidor);
				
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("6")) { //LISTAR USUARIO MEDIDOR

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String json = "";
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				RelUserMedidorDAO relUserMedidorDAO = new RelUserMedidorDAO(connection);
				List<RelUserMedidor> listRelUserMedidor = relUserMedidorDAO.listar(Long.parseLong(req.getParameter("idMedidor")));
				
				json = new Gson().toJson(listRelUserMedidor);
				
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("7")) { //AUTOCOMPLETE CPF VINCULO USUARIO MEDIDOR

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String json = "";
            String sql = "";
			
			try {

				sql += "WHERE   ID_USER > 0 " + 
					   "AND     ( REPLACE(CPF, '.', '') LIKE '" + req.getParameter("cpf").replace(".", "") + "%' OR UPPER(NOME) LIKE '" + req.getParameter("cpf").toUpperCase() + "%' ) " ;
				
				connection = ConnectionFactory.getConnection();
				
				UserDAO userDAO = new UserDAO(connection);
				List<User> listUser = userDAO.listar(sql);
				
				json = new Gson().toJson(listUser);
				
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("8")) { //INSERIR VINCULO USUARIO MEDIDOR

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String aviso = "";
            String json = "";
			
			try {

				connection = ConnectionFactory.getConnection();
				
				String cpf = req.getParameter("cpf").split(" - ")[0];
				
				UserDAO userDAO = new UserDAO(connection);
				User user1 = userDAO.buscarPorCpf(cpf.trim());
				
				if(user1 == null) {//USUARIO NAO ENCONTRADO
					
					aviso = "O cpf informado não foi localizado";
				}
				else {

					UserMedidor userMedidor = new UserMedidor();
					
					UserMedidorDAO userMedidorDAO = new UserMedidorDAO(connection);
					userMedidor = userMedidorDAO.buscar(user1.getIdUser(), Long.parseLong(req.getParameter("idMedidor")), "A");

					if(userMedidor != null) {
						
						aviso = "O usuário informado já está vinculado ao medidor";
					}
					else {
					
						userMedidor = new UserMedidor();
						userMedidor.setIdUserMedidor(0l);
						userMedidor.setIdInsert(user.getIdUser());
						userMedidor.setIdUser(user1.getIdUser());
						userMedidor.setIdMedidor(Long.parseLong(req.getParameter("idMedidor")));
						userMedidor.setDtInicio(null);
						userMedidor.setDtFim(null);
						userMedidor.setSituacao("A");
						userMedidorDAO.inserir(userMedidor);
					}
				}
				
				RelUserMedidorDAO relUserMedidorDAO = new RelUserMedidorDAO(connection);
				List<RelUserMedidor> listRelUserMedidor = relUserMedidorDAO.listar(Long.parseLong(req.getParameter("idMedidor")));

				List<Object> listObject = new ArrayList<Object>();
				listObject.add(aviso);
				listObject.add(listRelUserMedidor);
				
				json = new Gson().toJson(listObject);

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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("9")) { //INATIVAR VINCULO USUARIO MEDIDOR

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String json = "";
			
			try {

				connection = ConnectionFactory.getConnection();

				UserMedidorDAO userMedidorDAO = new UserMedidorDAO(connection);

				userMedidorDAO.inativar(Long.parseLong(req.getParameter("idUserMedidor")));

				RelUserMedidorDAO relUserMedidorDAO = new RelUserMedidorDAO(connection);
				List<RelUserMedidor> listRelUserMedidor = relUserMedidorDAO.listar(Long.parseLong(req.getParameter("idMedidor")));

				json = new Gson().toJson(listRelUserMedidor);

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
    }
}
