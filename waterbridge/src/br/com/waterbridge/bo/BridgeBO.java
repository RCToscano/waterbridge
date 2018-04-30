
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
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.BridgeTpAlim;
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

				req.setAttribute("listBridgeTpAlim", listBridgeTpAlim);				
				req.getRequestDispatcher("/jsp/bridge/cadastrobridge.jsp").forward(req, res);
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
				
				BridgeDAO bridgeDAO = new BridgeDAO(connection);
				Bridge bridge = bridgeDAO.buscarPorDeviceNum(req.getParameter("deviceNum").trim().toUpperCase()); 
				
				if(bridge == null) {
					
					BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
					bridgeTpAlim.setIdBridgeTpAlim(Long.parseLong(req.getParameter("tpAlimentacao")));
	
					bridge = new Bridge();
					bridge.setIdBridge(0l);
					bridge.setIdUser(user.getIdUser());
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
					req.setAttribute("listBridgeTpAlim", listBridgeTpAlim);
					req.getRequestDispatcher("/jsp/bridge/cadastrobridge.jsp").forward(req, res);
				}
				else {
					
					req.setAttribute("aviso", 
					"<div class='alert alert-danger'>" +
					"    Não foi possível cadastrar.! O cadastro já foi realizado em " + bridge.getDtInsert() +
					"    &emsp;&emsp;" +
					"    <a href='BridgeBO?acao=3&deviceNum=" + bridge.getDeviceNum() + "'>" + bridge.getDeviceNum() + "</a>" +
					"</div>"
					);
					req.setAttribute("acao", "2");
					req.setAttribute("btsubmit", "Cadastrar");
					req.setAttribute("listBridgeTpAlim", listBridgeTpAlim);
					req.setAttribute("idBridge", bridge.getIdBridge().toString());
					req.getRequestDispatcher("/jsp/bridge/cadastrobridge.jsp").forward(req, res);
				}
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
				
				BridgeDAO bridgeDAO = new BridgeDAO(connection);
				Bridge bridge = bridgeDAO.buscarPorDeviceNum(req.getParameter("deviceNum").trim().toUpperCase()); 
				
				req.setAttribute("listBridgeTpAlim", listBridgeTpAlim);
				req.setAttribute("bridge", bridge);
				req.getRequestDispatcher("/jsp/bridge/alteracaobridge.jsp").forward(req, res);
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
				
				bridge = bridgeDAO.buscarPorDeviceNum(req.getParameter("deviceNum").trim().toUpperCase()); 

				req.setAttribute("aviso", 
				"<div class='alert alert-success'>" +
				"    Alteração realizada com sucesso!" +
				"    &emsp;&emsp;" +
				"    <a href='BridgeBO?acao=3&deviceNum=" + bridge.getDeviceNum() + "'>" + bridge.getDeviceNum() + "</a>" +		
				"</div>"
				);
				req.setAttribute("listBridgeTpAlim", listBridgeTpAlim);
				req.setAttribute("bridge", bridge);
				req.getRequestDispatcher("/jsp/bridge/alteracaobridge.jsp").forward(req, res);
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("5")) {

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
			
			try {
			
				connection = ConnectionFactory.getConnection();
				BridgeDAO bridgeDAO = new BridgeDAO(connection);
				List<Bridge> listBridge = bridgeDAO.listarPorDeviceNum(req.getParameter("deviceNum"));
				
				String json = new Gson().toJson(listBridge);
				
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
    }
}

