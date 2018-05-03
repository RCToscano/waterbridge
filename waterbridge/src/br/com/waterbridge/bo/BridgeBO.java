
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

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.BridgeDAO;
import br.com.waterbridge.dao.BridgeTpAlimDAO;
import br.com.waterbridge.dao.CondominioDAO;
import br.com.waterbridge.dao.SituacaoDAO;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.BridgeTpAlim;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.Situacao;
import br.com.waterbridge.modelo.User;

public class BridgeBO extends HttpServlet {

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
				
				BridgeTpAlimDAO bridgeTpAlimDAO = new BridgeTpAlimDAO(connection);
				List<BridgeTpAlim> listBridgeTpAlim = bridgeTpAlimDAO.listar();
				
				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = condominioDAO.listar();
				
				req.setAttribute("tituloTela", "Cadastro de Bridge");
				req.setAttribute("acao", "BridgeBO?acao=2");
				req.setAttribute("devicereadonly", "");
				req.setAttribute("btNome", "Cadastrar");
				req.setAttribute("listBridgeTpAlim", listBridgeTpAlim);
				req.setAttribute("listSituacao", listSituacao);
				req.setAttribute("listCondominio", listCondominio);
				req.getRequestDispatcher("/jsp/bridge/cadaltbridge.jsp").forward(req, res);
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
				
				BridgeTpAlimDAO bridgeTpAlimDAO = new BridgeTpAlimDAO(connection);
				List<BridgeTpAlim> listBridgeTpAlim = bridgeTpAlimDAO.listar();
				
				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = condominioDAO.listar();
				
				BridgeDAO bridgeDAO = new BridgeDAO(connection);
				Bridge bridge = bridgeDAO.buscarPorDeviceNum(req.getParameter("deviceNum").trim().toUpperCase()); 
				
				if(bridge == null) {
					
					BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
					bridgeTpAlim.setIdBridgeTpAlim(Long.parseLong(req.getParameter("tpAlimentacao")));
	
					bridge = new Bridge();
					bridge.setIdBridge(0l);
					bridge.setIdUser(user.getIdUser());
					bridge.setIdCondominio(Long.parseLong(req.getParameter("idCondominio")));
					bridge.setDeviceNum(req.getParameter("deviceNum").trim().toUpperCase());
					bridge.setDtAtivacao(req.getParameter("dtAtivacao"));
					bridge.setValidadeToken(req.getParameter("validadeToken"));
					bridge.setBridgeTpAlim(bridgeTpAlim);
					bridge.setCustoMensal(Double.parseDouble(req.getParameter("custoMensal").replace(".", "").replace(",", ".")));
					bridge.setTaxaEnvio(Long.parseLong(req.getParameter("taxaEnvio")));
					bridge.setDescricao(Auxiliar.removerCaracteres(req.getParameter("descricao")).toUpperCase());
					bridge.setSituacao(req.getParameter("situacao"));
					bridge.setDtInsert(null);
					
					bridgeDAO.inserir(bridge);
				
					req.setAttribute("aviso", 
					"<div class='alert alert-success'>" +
					"    Cadastro realizado com sucesso!" +
					"    &emsp;&emsp;" +
					"    <a href='BridgeBO?acao=3&deviceNum=" + bridge.getDeviceNum() + "'>" + bridge.getDeviceNum() + "</a>" +		
					"</div>"
					);
				}
				else {
					
					req.setAttribute("aviso", 
					"<div class='alert alert-danger'>" +
					"    Não foi possível cadastrar.! O cadastro já foi realizado em " + bridge.getDtInsert() +
					"    &emsp;&emsp;" +
					"    <a href='BridgeBO?acao=3&deviceNum=" + bridge.getDeviceNum() + "'>" + bridge.getDeviceNum() + "</a>" +
					"</div>"
					);
				}
				req.setAttribute("tituloTela", "Cadastro de Bridge");
				req.setAttribute("acao", "BridgeBO?acao=2");
				req.setAttribute("devicereadonly", "");
				req.setAttribute("btNome", "Cadastrar");
				req.setAttribute("listBridgeTpAlim", listBridgeTpAlim);
				req.setAttribute("listSituacao", listSituacao);
				req.setAttribute("listCondominio", listCondominio);
				req.getRequestDispatcher("/jsp/bridge/cadaltbridge.jsp").forward(req, res);				
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
				
				BridgeTpAlimDAO bridgeTpAlimDAO = new BridgeTpAlimDAO(connection);
				List<BridgeTpAlim> listBridgeTpAlim = bridgeTpAlimDAO.listar();
				
				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = condominioDAO.listar();
				
				BridgeDAO bridgeDAO = new BridgeDAO(connection);
				Bridge bridge = bridgeDAO.buscarPorDeviceNum(req.getParameter("deviceNum").trim().toUpperCase()); 
				
				req.setAttribute("tituloTela", "Atera&ccedil;&atilde;o Bridge");
				req.setAttribute("acao", "BridgeBO?acao=4");
				req.setAttribute("devicereadonly", "readonly='readonly'");
				req.setAttribute("btNome", "Alterar");
				req.setAttribute("listBridgeTpAlim", listBridgeTpAlim);
				req.setAttribute("listSituacao", listSituacao);
				req.setAttribute("listCondominio", listCondominio);
				req.setAttribute("bridge", bridge);
				req.getRequestDispatcher("/jsp/bridge/cadaltbridge.jsp").forward(req, res);
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

				BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
				bridgeTpAlim.setIdBridgeTpAlim(Long.parseLong(req.getParameter("tpAlimentacao")));

				Bridge bridge = new Bridge();
				bridge.setIdBridge(Long.parseLong(req.getParameter("idBridge")));
				bridge.setIdUser(user.getIdUser());
				bridge.setIdCondominio(Long.parseLong(req.getParameter("idCondominio")));
				bridge.setDeviceNum(req.getParameter("deviceNum").trim().toUpperCase());
				bridge.setDtAtivacao(req.getParameter("dtAtivacao"));
				bridge.setValidadeToken(req.getParameter("validadeToken"));
				bridge.setBridgeTpAlim(bridgeTpAlim);
				bridge.setCustoMensal(Double.parseDouble(req.getParameter("custoMensal").replace(".", "").replace(",", ".")));
				bridge.setTaxaEnvio(Long.parseLong(req.getParameter("taxaEnvio")));
				bridge.setDescricao(Auxiliar.removerCaracteres(req.getParameter("descricao")).toUpperCase());
				bridge.setSituacao(req.getParameter("situacao"));
				bridge.setDtInsert(null);
				
				BridgeDAO bridgeDAO = new BridgeDAO(connection);
				bridgeDAO.alterar(bridge);

				BridgeTpAlimDAO bridgeTpAlimDAO = new BridgeTpAlimDAO(connection);
				List<BridgeTpAlim> listBridgeTpAlim = bridgeTpAlimDAO.listar();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = condominioDAO.listar();
				
				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				bridge = bridgeDAO.buscarPorDeviceNum(req.getParameter("deviceNum").trim().toUpperCase()); 

				req.setAttribute("aviso", 
				"<div class='alert alert-success'>" +
				"    Alteração realizada com sucesso!" +		
				"</div>"
				);
				req.setAttribute("tituloTela", "Atera&ccedil;&atilde;o Bridge");
				req.setAttribute("acao", "BridgeBO?acao=4");
				req.setAttribute("devicereadonly", "readonly='readonly'");
				req.setAttribute("btNome", "Alterar");
				req.setAttribute("listBridgeTpAlim", listBridgeTpAlim);
				req.setAttribute("listSituacao", listSituacao);
				req.setAttribute("listCondominio", listCondominio);
				req.setAttribute("bridge", bridge);
				req.getRequestDispatcher("/jsp/bridge/cadaltbridge.jsp").forward(req, res);
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
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = condominioDAO.listar();
				
				req.setAttribute("tituloTela", "Consulta Bridge");
				req.setAttribute("listCondominio", listCondominio);
				req.getRequestDispatcher("/jsp/bridge/listabridge.jsp").forward(req, res);
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("6")) {//TELA CONSULTA LISTAR BRIDGE

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
            String sql = "WHERE  ID_BRIDGE > 0 ";
            
			try {
				
				if(req.getParameter("deviceNum") != null && !req.getParameter("deviceNum").trim().equals("")) {
					sql += "AND UPPER(DEVICENUM) = '" + req.getParameter("deviceNum").toUpperCase() + "' "; 
				}
				if(req.getParameter("idCondominio") != null && !req.getParameter("idCondominio").trim().equals("")) {
					sql += "AND ID_CONDOMINIO = '" + req.getParameter("idCondominio") + "' ";
				}				
				if(req.getParameter("dtInicio") != null 
						&& req.getParameter("dtInicio").trim().length() == 10
						&& req.getParameter("dtFim") != null 
						&& req.getParameter("dtFim").trim().length() == 10) {
					sql += "AND      DTINSERT >= '" + Auxiliar.formataDtBanco(req.getParameter("dtInicio")) + " 00:00:00' AND    DTINSERT <= '" + Auxiliar.formataDtBanco(req.getParameter("dtFim")) + " 23:59:00' ";
				}
				sql += "ORDER BY DEVICENUM, " +
					   "         ATIVATIONDATE " ;
				
				connection = ConnectionFactory.getConnection();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = condominioDAO.listar();
				
				BridgeDAO bridgeDAO = new BridgeDAO(connection);
				List<Bridge> listBridge = bridgeDAO.listar(sql);				
				if(listBridge.size() == 0) {
					req.setAttribute("aviso",
					"<div class='col-sm-12 text-center'>" +
					"	<div class='alert alert-danger'>" +
					"		NENHUM REGISTRO LOCALIZADO" +
					"	</div>" +
					"</div>"
					);
				}
				req.setAttribute("tituloTela", "Consulta Bridge");
				req.setAttribute("listCondominio", listCondominio);
				req.setAttribute("listBridge", listBridge);
				req.getRequestDispatcher("/jsp/bridge/listabridge.jsp").forward(req, res);
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

