
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

import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.FabricMedidorDAO;
import br.com.waterbridge.dao.SituacaoDAO;
import br.com.waterbridge.modelo.FabricMedidor;
import br.com.waterbridge.modelo.Situacao;
import br.com.waterbridge.modelo.User;

public class FabricMedidorBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                   
		if (req.getParameter("acao") != null && req.getParameter("acao").equals("1")) {//ENTRA NA TELA DE CADASTRO
			
			Connection connection = null;
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				req.setAttribute("tituloTela", "Cadastro de Fabricante Medidor");
				req.setAttribute("acao", "FabricMedidorBO?acao=2");
				req.setAttribute("listSituacao", listSituacao);
				req.setAttribute("btNome", "Cadastrar");
				req.getRequestDispatcher("/jsp/fabricmedidor/cadaltfabricmedidor.jsp").forward(req, res);
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
			
			try {
			
				connection = ConnectionFactory.getConnection();

				FabricMedidorDAO fabricMedidorDAO = new FabricMedidorDAO(connection);
				FabricMedidor fabricMedidor = fabricMedidorDAO.buscarPorFabric(req.getParameter("fabricNome"));
				
				if(fabricMedidor == null ) {
					
					fabricMedidor = new FabricMedidor();
					fabricMedidor.setIdFabricMedidor(0l);
					fabricMedidor.setIdUser(user.getIdUser());
					fabricMedidor.setFabricante(req.getParameter("fabricNome").trim().toUpperCase());
					fabricMedidor.setSituacao(req.getParameter("situacao"));
					fabricMedidor.setDtInsert(null);
					
					fabricMedidorDAO.inserir(fabricMedidor);
					
					req.setAttribute("aviso", 
					"<div class='alert alert-success'>" +
					"    Cadastro realizado com sucesso!" +
					"    &emsp;&emsp;" +		
					"</div>"
					);
				}
				else {
					
					req.setAttribute("aviso", 
					"<div class='alert alert-danger'>" +
					"    Não foi possível cadastrar.! O cadastro já foi realizado em " + fabricMedidor.getDtInsert() +
					"    &emsp;&emsp;" +
					//"    <a href='FabricMedidorBO?acao=3&idFabricMedidor=" + fabricMedidor.getIdFabricMedidor() + "'>" + fabricMedidor.getFabricante() + "</a>" +
					"</div>"
					);
				}
				
				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				req.setAttribute("tituloTela", "Cadastro de Fabricante Medidor");
				req.setAttribute("acao", "FabricMedidorBO?acao=2");
				req.setAttribute("btNome", "Cadastrar");
				req.setAttribute("listSituacao", listSituacao);
				req.getRequestDispatcher("/jsp/fabricmedidor/cadaltfabricmedidor.jsp").forward(req, res);	
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("3")) {

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
			
			try {
			
				connection = ConnectionFactory.getConnection();
				
				FabricMedidorDAO fabricMedidorDAO = new FabricMedidorDAO(connection);
				FabricMedidor fabricMedidor = fabricMedidorDAO.buscar(Long.parseLong(req.getParameter("idFabricMedidor")));
				
				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				req.setAttribute("tituloTela", "Atera&ccedil;&atilde;o Fabricante Medidor");
				req.setAttribute("acao", "FabricMedidorBO?acao=4");
				req.setAttribute("btNome", "Alterar");
				req.setAttribute("listSituacao", listSituacao);
				req.setAttribute("fabricMedidor", fabricMedidor);
				req.getRequestDispatcher("/jsp/fabricmedidor/cadaltfabricmedidor.jsp").forward(req, res);
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("4")) {

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
			
			try {
			
				connection = ConnectionFactory.getConnection();

				FabricMedidor fabricMedidor = new FabricMedidor();
				fabricMedidor.setIdFabricMedidor(Long.parseLong(req.getParameter("idFabricMedidor")));
				fabricMedidor.setIdUser(user.getIdUser());
				fabricMedidor.setFabricante(req.getParameter("fabricNome").trim().toUpperCase());
				fabricMedidor.setSituacao(req.getParameter("situacao"));
				fabricMedidor.setDtInsert(null);

				FabricMedidorDAO fabricMedidorDAO = new FabricMedidorDAO(connection);
				fabricMedidorDAO.alterar(fabricMedidor);
				
				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				fabricMedidor = fabricMedidorDAO.buscar(Long.parseLong(req.getParameter("idFabricMedidor"))); 

				req.setAttribute("aviso", 
				"<div class='alert alert-success'>" +
				"    Alteração realizada com sucesso!" +		
				"</div>"
				);
				req.setAttribute("tituloTela", "Atera&ccedil;&atilde;o Bridge");
				req.setAttribute("acao", "FabricMedidorBO?acao=4");
				req.setAttribute("btNome", "Alterar");
				req.setAttribute("listSituacao", listSituacao);
				req.setAttribute("fabricMedidor", fabricMedidor);
				req.getRequestDispatcher("/jsp/fabricmedidor/cadaltfabricmedidor.jsp").forward(req, res);
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("5")) {//TELA CONSULTA / LISTA ENTRADA MENU

			Connection connection = null;
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				FabricMedidorDAO fabricMedidorDAO = new FabricMedidorDAO(connection);
				List<FabricMedidor> listFabricMedidor = fabricMedidorDAO.listar();
				
				req.setAttribute("tituloTela", "Consulta Fabricante Medidor");
				req.setAttribute("listFabricMedidor", listFabricMedidor);
				req.getRequestDispatcher("/jsp/fabricmedidor/listafabricmedidor.jsp").forward(req, res);
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("6")) {//TELA CONSULTA LISTAR FABRICANTE MEDIDOR

			Connection connection = null;
            String sql = "WHERE  ID_FABRICMEDIDOR > 0 ";
            
			try {
				
				if(req.getParameter("fabricNome") != null && !req.getParameter("fabricNome").trim().equals("")) {
					sql += "AND UPPER(FABRICANTE) LIKE '%" + req.getParameter("fabricNome").toUpperCase() + "%' "; 
				}
				sql += "ORDER BY FABRICANTE " ;
				
				connection = ConnectionFactory.getConnection();
				
				FabricMedidorDAO fabricMedidorDAO = new FabricMedidorDAO(connection);
				List<FabricMedidor> listFabricMedidor = fabricMedidorDAO.listar(sql);				
				if(listFabricMedidor.size() == 0) {
					req.setAttribute("aviso",
					"<div class='col-sm-12 text-center'>" +
					"	<div class='alert alert-danger'>" +
					"		NENHUM REGISTRO LOCALIZADO" +
					"	</div>" +
					"</div>"
					);
				}
				req.setAttribute("tituloTela", "Consulta Fabricante Medidor");
				req.setAttribute("listFabricMedidor", listFabricMedidor);
				req.getRequestDispatcher("/jsp/fabricmedidor/listafabricmedidor.jsp").forward(req, res);
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
}


