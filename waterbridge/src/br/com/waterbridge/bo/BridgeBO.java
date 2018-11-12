
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

import br.com.andwaterbridge.dao.MetaPressaoDAO;
import br.com.andwaterbridge.modelo.MetaPressao;
import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.BridgeDAO;
import br.com.waterbridge.dao.BridgeTpAlimDAO;
import br.com.waterbridge.dao.BridgeTpDAO;
import br.com.waterbridge.dao.CondominioDAO;
import br.com.waterbridge.dao.SituacaoDAO;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.BridgeTp;
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
			String sql = "";
			
			try {
				
				connection = ConnectionFactory.getConnection();
				
				BridgeTpAlimDAO bridgeTpAlimDAO = new BridgeTpAlimDAO(connection);
				List<BridgeTpAlim> listBridgeTpAlim = bridgeTpAlimDAO.listar();
				
				BridgeTpDAO bridgeTpDAO = new BridgeTpDAO(connection);
				List<BridgeTp> listBridgeTp = bridgeTpDAO.listar();
				
				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = condominioDAO.listar();
				
				req.setAttribute("tituloTela", "Cadastro de Bridge");
				req.setAttribute("acao", "BridgeBO?acao=2");
				req.setAttribute("devicereadonly", "");
				req.setAttribute("btNome", "Cadastrar");
				req.setAttribute("listBridgeTpAlim", listBridgeTpAlim);
				req.setAttribute("listBridgeTp", listBridgeTp);
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("2")) {//CADASTRA BRIDGE

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
			
			try {
			
				connection = ConnectionFactory.getConnection();
				
				BridgeTpAlimDAO bridgeTpAlimDAO = new BridgeTpAlimDAO(connection);
				List<BridgeTpAlim> listBridgeTpAlim = bridgeTpAlimDAO.listar();
				
				BridgeTpDAO bridgeTpDAO = new BridgeTpDAO(connection);
				List<BridgeTp> listBridgeTp = bridgeTpDAO.listar();
				
				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = condominioDAO.listar();
				
				BridgeDAO bridgeDAO = new BridgeDAO(connection);
				Bridge bridge = bridgeDAO.buscarPorDeviceNum(req.getParameter("deviceNum").trim().toUpperCase(), "A"); 
				
				if(bridge == null) {
					
					BridgeTp bridgeTp = new BridgeTp();
					bridgeTp.setIdBridgeTp(Long.parseLong(req.getParameter("idBridgeTp")));
					
					BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
					bridgeTpAlim.setIdBridgeTpAlim(Long.parseLong(req.getParameter("tpAlimentacao")));
	
					bridge = new Bridge();
					bridge.setIdBridge(0l);
					bridge.setIdUser(user.getIdUser());
					bridge.setIdCondominio(Long.parseLong(req.getParameter("idCondominio")));
					bridge.setDeviceNum(req.getParameter("deviceNum").trim().toUpperCase());
					bridge.setDtAtivacao(req.getParameter("dtAtivacao"));
					bridge.setValidadeToken(req.getParameter("validadeToken"));
					bridge.setBridgeTp(bridgeTp);
					bridge.setBridgeTpAlim(bridgeTpAlim);
					bridge.setCustoMensal(Double.parseDouble(req.getParameter("custoMensal").replace(".", "").replace(",", ".")));
					bridge.setTaxaEnvio(Long.parseLong(req.getParameter("taxaEnvio")));
					bridge.setDescricao(Auxiliar.removerCaracteres(req.getParameter("descricao")).toUpperCase());
					bridge.setSituacao(req.getParameter("situacao"));
					bridge.setDtInsert(null);
					
					bridgeDAO.inserir(bridge);
					
					bridge = bridgeDAO.buscarPorDeviceNum(req.getParameter("deviceNum").trim().toUpperCase(), "A");
					
					//CADASTRA META PRESSAO
					if(bridgeTp.getIdBridgeTp().longValue() == 2) {
						
						Condominio condominio = condominioDAO.buscarPorId(bridge.getIdCondominio());

						MetaPressaoDAO metaPressaoDAO = new MetaPressaoDAO(connection);
						
						MetaPressao metaPressao = new MetaPressao();
						metaPressao.setIdMetaPressao(0l);
						metaPressao.setIdUser(user.getIdUser());
						metaPressao.setIdEmpresa(condominio.getIdEmpresa());
						metaPressao.setIdCondominio(condominio.getIdCondominio());
						metaPressao.setIdBridge(bridge.getIdBridge());
						metaPressao.setIdMedidor(null);
						metaPressao.setMeterPosition(null);
						metaPressao.setPressaoMinBaixa(Double.parseDouble(req.getParameter("minimoPressaoBaixa").replace(",", ".")));
						metaPressao.setPressaoMin(Double.parseDouble(req.getParameter("minimoPressaoNormal").replace(",", ".")));
						metaPressao.setPressaoMax(Double.parseDouble(req.getParameter("maximoPressaoNormal").replace(",", ".")));
						metaPressao.setPressaoMaxAlta(Double.parseDouble(req.getParameter("maximoPressaoAlta").replace(",", ".")));
						metaPressao.setDtInsert(null);
						
						metaPressaoDAO.inserir(metaPressao);		
					}
				
					req.setAttribute("aviso", 
					"<div class='alert alert-success'>" +
					"    Cadastro realizado com sucesso!" +
					"    &emsp;&emsp;" +
					"    <a href='BridgeBO?acao=3&idBridge=" + bridge.getIdBridge() + "'>" + bridge.getDeviceNum() + "</a>" +		
					"</div>"
					);
				}
				else {
					
					req.setAttribute("aviso", 
					"<div class='alert alert-danger'>" +
					"    Não foi possível cadastrar.! O cadastro já foi realizado em " + bridge.getDtInsert() +
					"    &emsp;&emsp;" +
					"    <a href='BridgeBO?acao=3&idBridge=" + bridge.getIdBridge() + "'>" + bridge.getDeviceNum() + "</a>" +
					"</div>"
					);
				}
				req.setAttribute("tituloTela", "Cadastro de Bridge");
				req.setAttribute("acao", "BridgeBO?acao=2");
				req.setAttribute("devicereadonly", "");
				req.setAttribute("btNome", "Cadastrar");
				req.setAttribute("listBridgeTpAlim", listBridgeTpAlim);
				req.setAttribute("listBridgeTp", listBridgeTp);
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("3")) {//ENTRA NA TELA DE ALTERACAO ATRAVES DA TELA DE CONSULTA

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
			
			try {
			
				connection = ConnectionFactory.getConnection();
				
				BridgeTpAlimDAO bridgeTpAlimDAO = new BridgeTpAlimDAO(connection);
				List<BridgeTpAlim> listBridgeTpAlim = bridgeTpAlimDAO.listar();
				
				BridgeTpDAO bridgeTpDAO = new BridgeTpDAO(connection);
				List<BridgeTp> listBridgeTp = bridgeTpDAO.listar();
				
				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = condominioDAO.listar();
				
				BridgeDAO bridgeDAO = new BridgeDAO(connection);
				Bridge bridge = bridgeDAO.buscarPorId(Long.parseLong(req.getParameter("idBridge"))); 
				
				MetaPressaoDAO metaPressaoDAO = new MetaPressaoDAO(connection);
				MetaPressao metaPressao = metaPressaoDAO.buscarPorIdBridge(bridge.getIdBridge());
				
				req.setAttribute("tituloTela", "Atera&ccedil;&atilde;o Bridge");
				req.setAttribute("acao", "BridgeBO?acao=4");
				req.setAttribute("devicereadonly", "readonly='readonly'");
				req.setAttribute("btNome", "Alterar");
				req.setAttribute("listBridgeTpAlim", listBridgeTpAlim);
				req.setAttribute("listBridgeTp", listBridgeTp);
				req.setAttribute("listSituacao", listSituacao);
				req.setAttribute("listCondominio", listCondominio);
				req.setAttribute("bridge", bridge);
				req.setAttribute("metaPressao", metaPressao);
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("4")) {//ALTERA BRIDGE

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
			
			try {
			
				connection = ConnectionFactory.getConnection();

				BridgeTp bridgeTp = new BridgeTp();
				bridgeTp.setIdBridgeTp(Long.parseLong(req.getParameter("idBridgeTp")));
				
				BridgeTpAlim bridgeTpAlim = new BridgeTpAlim();
				bridgeTpAlim.setIdBridgeTpAlim(Long.parseLong(req.getParameter("tpAlimentacao")));

				Bridge bridge = new Bridge();
				bridge.setIdBridge(Long.parseLong(req.getParameter("idBridge")));
				bridge.setIdUser(user.getIdUser());
				bridge.setIdCondominio(Long.parseLong(req.getParameter("idCondominio")));
				bridge.setDeviceNum(req.getParameter("deviceNum").trim().toUpperCase());
				bridge.setDtAtivacao(req.getParameter("dtAtivacao"));
				bridge.setValidadeToken(req.getParameter("validadeToken"));
				bridge.setBridgeTp(bridgeTp);
				bridge.setBridgeTpAlim(bridgeTpAlim);
				bridge.setCustoMensal(Double.parseDouble(req.getParameter("custoMensal").replace(".", "").replace(",", ".")));
				bridge.setTaxaEnvio(Long.parseLong(req.getParameter("taxaEnvio")));
				bridge.setDescricao(Auxiliar.removerCaracteres(req.getParameter("descricao")).toUpperCase());
				bridge.setSituacao(req.getParameter("situacao"));
				bridge.setDtInsert(null);
				
				BridgeDAO bridgeDAO = new BridgeDAO(connection);
				
				//VERIFICA SE EXISTE ALGUM BRIDGE ATIVO COM ID DIFERENTE
				Bridge bridgeAtivo = bridgeDAO.buscarPorDeviceNum(bridge.getDeviceNum(), "A");
				if(bridge.getSituacao().equals("A") 
						&& bridge.getIdBridge().longValue() != bridgeAtivo.getIdBridge()) {
					
				}
				
				bridgeDAO.alterar(bridge);

				BridgeTpAlimDAO bridgeTpAlimDAO = new BridgeTpAlimDAO(connection);
				List<BridgeTpAlim> listBridgeTpAlim = bridgeTpAlimDAO.listar();
				
				BridgeTpDAO bridgeTpDAO = new BridgeTpDAO(connection);
				List<BridgeTp> listBridgeTp = bridgeTpDAO.listar();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = condominioDAO.listar();
				
				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				bridge = bridgeDAO.buscarPorId(Long.parseLong(req.getParameter("idBridge")));
				
				//CADASTRA META PRESSAO
				MetaPressaoDAO metaPressaoDAO = new MetaPressaoDAO(connection);
				MetaPressao metaPressao = null;
				if(bridgeTp.getIdBridgeTp().longValue() == 2) {
					
					Condominio condominio = condominioDAO.buscarPorId(bridge.getIdCondominio());
					
					metaPressao = metaPressaoDAO.buscarPorIdBridge(Long.parseLong(req.getParameter("idBridge")));
					if(metaPressao ==  null) {
						
						metaPressao = new MetaPressao();
						metaPressao.setIdMetaPressao(0l);
						metaPressao.setIdUser(user.getIdUser());
						metaPressao.setIdEmpresa(condominio.getIdEmpresa());
						metaPressao.setIdCondominio(condominio.getIdCondominio());
						metaPressao.setIdBridge(bridge.getIdBridge());
						metaPressao.setIdMedidor(null);
						metaPressao.setMeterPosition(null);
						metaPressao.setPressaoMinBaixa(Double.parseDouble(req.getParameter("minimoPressaoBaixa").replace(",", ".")));
						metaPressao.setPressaoMin(Double.parseDouble(req.getParameter("minimoPressaoNormal").replace(",", ".")));
						metaPressao.setPressaoMax(Double.parseDouble(req.getParameter("maximoPressaoNormal").replace(",", ".")));
						metaPressao.setPressaoMaxAlta(Double.parseDouble(req.getParameter("maximoPressaoAlta").replace(",", ".")));
						metaPressao.setDtInsert(null);
						
						metaPressaoDAO.inserir(metaPressao);
					}
					else {
						
						//metaPressao = new MetaPressao();
						//metaPressao.setIdMetaPressao(0l);
						metaPressao.setIdUser(user.getIdUser());
						metaPressao.setIdEmpresa(condominio.getIdEmpresa());
						metaPressao.setIdCondominio(condominio.getIdCondominio());
						metaPressao.setIdBridge(bridge.getIdBridge());
						metaPressao.setIdMedidor(null);
						metaPressao.setMeterPosition(null);
						metaPressao.setPressaoMinBaixa(Double.parseDouble(req.getParameter("minimoPressaoBaixa").replace(",", ".")));
						metaPressao.setPressaoMin(Double.parseDouble(req.getParameter("minimoPressaoNormal").replace(",", ".")));
						metaPressao.setPressaoMax(Double.parseDouble(req.getParameter("maximoPressaoNormal").replace(",", ".")));
						metaPressao.setPressaoMaxAlta(Double.parseDouble(req.getParameter("maximoPressaoAlta").replace(",", ".")));
						metaPressao.setDtInsert(null);
						
						metaPressaoDAO.alterar(metaPressao);						
					}
					
					metaPressao = metaPressaoDAO.buscarPorIdBridge(Long.parseLong(req.getParameter("idBridge")));
				}
				else {
					
					//EXCLUIR META PRESSAO SE MUDAR TIPO BRIDGE
					metaPressao = metaPressaoDAO.buscarPorIdBridge(Long.parseLong(req.getParameter("idBridge")));
					if(metaPressao != null) {
						
						metaPressaoDAO.excluir(metaPressao.getIdMetaPressao());
					}					
				}

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
				req.setAttribute("listBridgeTp", listBridgeTp);
				req.setAttribute("listSituacao", listSituacao);
				req.setAttribute("listCondominio", listCondominio);
				req.setAttribute("bridge", bridge);
				req.setAttribute("metaPressao", metaPressao);
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

