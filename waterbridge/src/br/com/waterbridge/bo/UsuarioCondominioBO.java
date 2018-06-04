
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

import br.com.waterbridge.auxiliar.Email;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.CondominioDAO;
import br.com.waterbridge.dao.EmpresaDAO;
import br.com.waterbridge.dao.UserCondominioDAO;
import br.com.waterbridge.dao.UserDAO;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.Empresa;
import br.com.waterbridge.modelo.User;
import br.com.waterbridge.modelo.UserCondominio;
import br.com.waterbridge.reldao.RelCondominioDAO;
import br.com.waterbridge.reldao.RelUserCondominioDAO;
import br.com.waterbridge.relmodelo.RelCondominio;
import br.com.waterbridge.relmodelo.RelUserCondominio;

public class UsuarioCondominioBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                   
		if (req.getParameter("acao") != null && req.getParameter("acao").equals("1")) {//ENTRA NA TELA LISTA USUARIO X CONDOMINIO
			
			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				EmpresaDAO empresaDAO = new EmpresaDAO(connection);
				List<Empresa> listEmpresa = empresaDAO.listarPorUsuario(user.getIdUser());
				
				req.setAttribute("tituloTela", "Víncular Usuário Local");
				req.setAttribute("acao", "UsuarioCondominioBO?acao=2");
				req.setAttribute("btNome", "Buscar");
				req.setAttribute("listEmpresa", listEmpresa);
				req.getRequestDispatcher("/jsp/usuariocondominio/listausuariocondominio.jsp").forward(req, res);
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("3")) { //LISTA USUARIO CONDOMINIO

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String sql = "";
            String json = "";
			
			try {

				if(req.getParameter("idCondominio") != null && !req.getParameter("idCondominio").equals("")) {
					sql += "WHERE   ID_CONDOMINIO = " + req.getParameter("idCondominio") + " ";
				}

				connection = ConnectionFactory.getConnection();
				
				RelCondominioDAO relCondominioDAO = new RelCondominioDAO(connection);
				List<RelCondominio> listRelCondominio = relCondominioDAO.listar(sql);
				
				json = new Gson().toJson(listRelCondominio);
				
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("4")) { //LISTAR USUARIO CONDOMINIO

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String json = "";
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				RelUserCondominioDAO relUserCondominioDAO = new RelUserCondominioDAO(connection);
				List<RelUserCondominio> listRelUserCondominio = relUserCondominioDAO.listar(Long.parseLong(req.getParameter("idCondominio")));
				
				json = new Gson().toJson(listRelUserCondominio);
				
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("5")) { //AUTOCOMPLETE CPF VINCULO USUARIO CONDOMINIO

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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("6")) { //INSERIR VINCULO USUARIO CONDOMINIO

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String aviso = "";
            String json = "";
            final User user1;
			
			try {

				connection = ConnectionFactory.getConnection();
				
				String cpf = req.getParameter("cpf").split(" - ")[0];
				
				UserDAO userDAO = new UserDAO(connection);
				user1 = userDAO.buscarPorCpf(cpf.trim());
				
				if(user1 == null) {//USUARIO NAO ENCONTRADO
					
					aviso = "O cpf informado não foi localizado";
				}
				else if(user1.getPerfil().getIdPerfil().longValue() != 4) {//PERFIL DO USUARIO NAO COMPATIVEL COM VINCULO
					
					aviso = "O usuário informado não possui perfil compatível com vínculo";
				}
				else {

					UserCondominio userCondominio = new UserCondominio();
					
					UserCondominioDAO userCondominioDAO = new UserCondominioDAO(connection);
					userCondominio = userCondominioDAO.buscar(user1.getIdUser(), Long.parseLong(req.getParameter("idCondominio")), "A");

					if(userCondominio != null) {
						
						aviso = "O usuário informado já está vinculado ao medidor";
					}
					else {
					
						userCondominio = new UserCondominio();
						userCondominio.setIdUserCondominio(0l);
						userCondominio.setIdInsert(user.getIdUser());
						userCondominio.setIdUser(user1.getIdUser());
						userCondominio.setIdCondominio(Long.parseLong(req.getParameter("idCondominio")));
						userCondominio.setSituacao("A");
						userCondominio.setDtInicio(null);
						userCondominio.setDtFim(null);
						
						userCondominioDAO.inserir(userCondominio);
						
						new Thread() {	       
					        @Override
					        public void run() {
					        	Email.enviarEmail("WaterBridge - Acesso", Email.corpoAcessoUsuario(user1), user1.getEmail());
					        }
					    }.start();	
					}
				}
				
				RelUserCondominioDAO relUserCondominioDAO = new RelUserCondominioDAO(connection);
				List<RelUserCondominio> listRelUserCondominio = relUserCondominioDAO.listar(Long.parseLong(req.getParameter("idCondominio")));

				List<Object> listObject = new ArrayList<Object>();
				listObject.add(aviso);
				listObject.add(listRelUserCondominio);
				
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("7")) { //INATIVAR VINCULO USUARIO CONDOMINIO

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String json = "";
			
			try {

				UserCondominio userCondominio = new UserCondominio();
				userCondominio.setIdUserCondominio(Long.parseLong(req.getParameter("idUserCondominio")));
				userCondominio.setObs(req.getParameter("obs").toUpperCase());
				
				connection = ConnectionFactory.getConnection();

				UserCondominioDAO userCondominioDAO = new UserCondominioDAO(connection);

				userCondominioDAO.inativar(userCondominio);

				RelUserCondominioDAO relUserCondominioDAO = new RelUserCondominioDAO(connection);
				List<RelUserCondominio> listRelUserCondominio = relUserCondominioDAO.listar(Long.parseLong(req.getParameter("idCondominio")));

				json = new Gson().toJson(listRelUserCondominio);

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

