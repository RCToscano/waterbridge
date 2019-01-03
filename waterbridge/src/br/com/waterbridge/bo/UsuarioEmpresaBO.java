
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
import br.com.waterbridge.dao.EmpresaDAO;
import br.com.waterbridge.dao.UserDAO;
import br.com.waterbridge.dao.UserEmpresaDAO;
import br.com.waterbridge.modelo.Empresa;
import br.com.waterbridge.modelo.User;
import br.com.waterbridge.modelo.UserEmpresa;
import br.com.waterbridge.reldao.RelEmpresaDAO;
import br.com.waterbridge.reldao.RelUserEmpresaDAO;
import br.com.waterbridge.relmodelo.RelEmpresa;
import br.com.waterbridge.relmodelo.RelUserEmpresa;

public class UsuarioEmpresaBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                   
		if (req.getParameter("acao") != null && req.getParameter("acao").equals("1")) {//ENTRA NA TELA LISTA USUARIO X EMPRESA
			
			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				EmpresaDAO empresaDAO = new EmpresaDAO(connection);
				List<Empresa> listEmpresa = empresaDAO.listar();
				
				req.setAttribute("tituloTela", "Víncular Usuário Empresa");
				req.setAttribute("acao", "UsuarioEmpresaBO?acao=2");
				req.setAttribute("btNome", "Buscar");
				req.setAttribute("listEmpresa", listEmpresa);
				req.getRequestDispatcher("/jsp/usuarioempresa/listausuarioempresa.jsp").forward(req, res);
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("2")) { //LISTA USUARIO EMPRESA

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String sql = "";
            String json = "";
			
			try {

				if(req.getParameter("idEmpresa") != null && !req.getParameter("idEmpresa").equals("")) {
					sql += "WHERE   ID_EMPRESA = " + req.getParameter("idEmpresa") + " ";
				}

				connection = ConnectionFactory.getConnection();
				
				RelEmpresaDAO relEmpresaDAO = new RelEmpresaDAO(connection);
				List<RelEmpresa> listRelEmpresa = relEmpresaDAO.listar(sql);
				
				json = new Gson().toJson(listRelEmpresa);
				
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("3")) { //LISTAR USUARIO MEDIDOR

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String json = "";
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				RelUserEmpresaDAO relUserEmpresaDAO = new RelUserEmpresaDAO(connection);
				List<RelUserEmpresa> listRelUserEmpresa = relUserEmpresaDAO.listar(Long.parseLong(req.getParameter("idEmpresa")));
				
				json = new Gson().toJson(listRelUserEmpresa);
			
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("4")) { //AUTOCOMPLETE CPF VINCULO USUARIO EMPRESA

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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("5")) { //INSERIR VINCULO USUARIO EMPRESA

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
				else if(user1.getPerfil().getIdPerfil().longValue() != 3) {//PERFIL DO USUARIO NAO COMPATIVEL COM VINCULO
					
					aviso = "O usuário informado não possui perfil compatível com vínculo";
				}
				else {

					UserEmpresa userEmpresa = new UserEmpresa();
					
					UserEmpresaDAO userEmpresaDAO = new UserEmpresaDAO(connection);
					userEmpresa = userEmpresaDAO.buscar(user1.getIdUser(), Long.parseLong(req.getParameter("idEmpresa")), "A");

					if(userEmpresa != null) {
						
						aviso = "O usuário informado já está vinculado a empresa";
					}
					else {
					
						userEmpresa = new UserEmpresa();
						userEmpresa.setIdUserEmpresa(0l);
						userEmpresa.setIdInsert(user.getIdUser());
						userEmpresa.setIdUser(user1.getIdUser());
						userEmpresa.setIdEmpresa(Long.parseLong(req.getParameter("idEmpresa")));
						userEmpresa.setSituacao("A");
						userEmpresa.setDtInicio(null);
						userEmpresa.setDtFim(null);
						
						userEmpresaDAO.inserir(userEmpresa);
					}
				}
				
				RelUserEmpresaDAO relUserEmpresaDAO = new RelUserEmpresaDAO(connection);
				List<RelUserEmpresa> listRelUserEmpresa = relUserEmpresaDAO.listar(Long.parseLong(req.getParameter("idEmpresa")));

				List<Object> listObject = new ArrayList<Object>();
				listObject.add(aviso);
				listObject.add(listRelUserEmpresa);
				
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("6")) { //INATIVAR VINCULO USUARIO EMPRESA

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String json = "";
			
			try {

				UserEmpresa userEmpresa = new UserEmpresa();
				userEmpresa.setIdUserEmpresa(Long.parseLong(req.getParameter("idUserEmpresa")));
				userEmpresa.setObs(req.getParameter("obs").toUpperCase());
				
				connection = ConnectionFactory.getConnection();

				UserEmpresaDAO userEmpresaDAO = new UserEmpresaDAO(connection);

				userEmpresaDAO.inativar(userEmpresa);

				RelUserEmpresaDAO relUserEmpresaDAO = new RelUserEmpresaDAO(connection);
				List<RelUserEmpresa> listRelUserEmpresa = relUserEmpresaDAO.listar(Long.parseLong(req.getParameter("idEmpresa")));

				json = new Gson().toJson(listRelUserEmpresa);

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

