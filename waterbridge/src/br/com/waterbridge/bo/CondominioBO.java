
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
import br.com.waterbridge.dao.CnpTpDAO;
import br.com.waterbridge.dao.CondominioDAO;
import br.com.waterbridge.dao.SituacaoDAO;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.CnpTp;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.Situacao;
import br.com.waterbridge.modelo.User;

public class CondominioBO extends HttpServlet {

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
				
				CnpTpDAO cnpTpDAO = new CnpTpDAO(connection);
				List<CnpTp> listCnpTp = cnpTpDAO.listar();
				
				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();

				req.setAttribute("tituloTela", "Cadastro de Condom&iacute;nio");
				req.setAttribute("acao", "CondominioBO?acao=2");
				req.setAttribute("btNome", "Cadastrar");
				req.setAttribute("listCnpTp", listCnpTp);
				req.setAttribute("listSituacao", listSituacao);
				req.getRequestDispatcher("/jsp/condominio/cadaltcondominio.jsp").forward(req, res);
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
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				Condominio condominio = condominioDAO.buscarPorCnp(req.getParameter("cnp"));
				
				if(condominio == null) {
					
					CnpTp cnpTp = new CnpTp();
					cnpTp.setIdCnpTp(Long.parseLong(req.getParameter("cnpTp")));
				
					condominio = new Condominio();		
					condominio.setIdCondominio(0l);
					condominio.setIdUser(user.getIdUser());
					condominio.setCnpTp(cnpTp);
					condominio.setNome(Auxiliar.removerCaracteres(req.getParameter("nome").trim().toUpperCase()));
					condominio.setCnp(req.getParameter("cnp"));
					condominio.setTelFixo(req.getParameter("telFixo"));
					condominio.setTelCel(req.getParameter("telCel"));
					condominio.setEmail(req.getParameter("email"));
					condominio.setEndereco(Auxiliar.removerCaracteres(req.getParameter("endereco").trim().toUpperCase()));
					condominio.setNumero(Long.parseLong(req.getParameter("numero")));
					condominio.setCompl(Auxiliar.removerCaracteres(req.getParameter("compl").trim().toUpperCase()));
					condominio.setMunicipio(Auxiliar.removerCaracteres(req.getParameter("municipio").trim().toUpperCase()));
					condominio.setUf(req.getParameter("estado").trim().toUpperCase());
					condominio.setCep(req.getParameter("cep").trim().toUpperCase());
					if(req.getParameter("latitude").trim().equals("")) {
						condominio.setCoordX("0.0");
						condominio.setCoordY("0.0");
					}
					else {
						condominio.setCoordX(req.getParameter("latitude"));
						condominio.setCoordY(req.getParameter("longitude"));
					}
					condominio.setResponsavel(Auxiliar.removerCaracteres(req.getParameter("responsavel").trim().toUpperCase()));
					condominio.setContratoNum(req.getParameter("contratoNum"));
					condominio.setContaCiclo(Long.parseLong(req.getParameter("contaCiclo")));
					condominio.setSituacao(req.getParameter("situacao"));
					condominio.setDtInsert(null);
					
					condominioDAO.inserir(condominio);
					
					req.setAttribute("aviso", 
					"<div class='alert alert-success'>" +
					"    Cadastro realizado com sucesso!" +
					"    &emsp;&emsp;" +
					"    <a href='CondominioBO?acao=3&idCondominio=" + condominio.getIdCondominio() + "'>" + condominio.getNome() + "</a>" +		
					"</div>"
					);
				}
				else {

					req.setAttribute("aviso",
					"<div class='alert alert-danger'>" +
					"    Não foi possível cadastrar.! O cadastro já foi realizado em " + condominio.getDtInsert() +
					"    &emsp;&emsp;" +
					"    <a href='CondominioBO?acao=3&idCondominio=" + condominio.getIdCondominio() + "'>" + condominio.getNome() + "</a>" +
					"</div>"
					);
				}

				CnpTpDAO cnpTpDAO = new CnpTpDAO(connection);
				List<CnpTp> listCnpTp = cnpTpDAO.listar();

				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();

				req.setAttribute("tituloTela", "Cadastro de Condom&iacute;nio");
				req.setAttribute("acao", "CondominioBO?acao=2");
				req.setAttribute("btNome", "Cadastrar");
				req.setAttribute("listCnpTp", listCnpTp);
				req.setAttribute("listSituacao", listSituacao);
				req.getRequestDispatcher("/jsp/condominio/cadaltcondominio.jsp").forward(req, res);
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
				
				CnpTpDAO cnpTpDAO = new CnpTpDAO(connection);
				List<CnpTp> listCnpTp = cnpTpDAO.listar();

				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				Condominio condominio = condominioDAO.buscarPorId(Long.parseLong(req.getParameter("idCondominio")));
				
				req.setAttribute("tituloTela", "Altera&ccedil;&atilde;o de Condom&iacute;nio");
				req.setAttribute("acao", "CondominioBO?acao=4");
				req.setAttribute("btNome", "Alterar");
				req.setAttribute("listCnpTp", listCnpTp);
				req.setAttribute("listSituacao", listSituacao);
				req.setAttribute("condominio", condominio);
				req.getRequestDispatcher("/jsp/condominio/cadaltcondominio.jsp").forward(req, res);
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
				
				CnpTp cnpTp = new CnpTp();
				cnpTp.setIdCnpTp(Long.parseLong(req.getParameter("cnpTp")));
			
				Condominio condominio = new Condominio();		
				condominio.setIdCondominio(Long.parseLong(req.getParameter("idCondominio")));
				condominio.setIdUser(user.getIdUser());
				condominio.setCnpTp(cnpTp);
				condominio.setNome(Auxiliar.removerCaracteres(req.getParameter("nome").trim().toUpperCase()));
				condominio.setCnp(req.getParameter("cnp"));
				condominio.setTelFixo(req.getParameter("telFixo"));
				condominio.setTelCel(req.getParameter("telCel"));
				condominio.setEmail(req.getParameter("email"));
				condominio.setEndereco(Auxiliar.removerCaracteres(req.getParameter("endereco").trim().toUpperCase()));
				condominio.setNumero(Long.parseLong(req.getParameter("numero")));
				condominio.setCompl(Auxiliar.removerCaracteres(req.getParameter("compl").trim().toUpperCase()));
				condominio.setMunicipio(Auxiliar.removerCaracteres(req.getParameter("municipio").trim().toUpperCase()));
				condominio.setUf(req.getParameter("estado").trim().toUpperCase());
				condominio.setCep(req.getParameter("cep").trim().toUpperCase());
				if(req.getParameter("latitude").trim().equals("")) {
					condominio.setCoordX("0.0");
					condominio.setCoordY("0.0");
				}
				else {
					condominio.setCoordX(req.getParameter("latitude"));
					condominio.setCoordY(req.getParameter("longitude"));
				}
				condominio.setResponsavel(Auxiliar.removerCaracteres(req.getParameter("responsavel").trim().toUpperCase()));
				condominio.setContratoNum(req.getParameter("contratoNum"));
				condominio.setContaCiclo(Long.parseLong(req.getParameter("contaCiclo")));
				condominio.setSituacao(req.getParameter("situacao"));
				condominio.setDtInsert(null);
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				condominioDAO.alterar(condominio);

				CnpTpDAO cnpTpDAO = new CnpTpDAO(connection);
				List<CnpTp> listCnpTp = cnpTpDAO.listar();

				condominio = condominioDAO.buscarPorId(Long.parseLong(req.getParameter("idCondominio")));
				
				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();

				req.setAttribute("aviso", 
				"<div class='alert alert-success'>" +
				"    Altera&ccedil;&atilde;o realizada com sucesso!" +		
				"</div>"
				);
				req.setAttribute("tituloTela", "Altera&ccedil;&atilde;o de Condom&iacute;nio");
				req.setAttribute("acao", "CondominioBO?acao=4");
				req.setAttribute("btNome", "Alterar");
				req.setAttribute("listCnpTp", listCnpTp);
				req.setAttribute("listSituacao", listSituacao);
				req.setAttribute("condominio", condominio);
				req.getRequestDispatcher("/jsp/condominio/cadaltcondominio.jsp").forward(req, res);
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
				
				req.getRequestDispatcher("/jsp/condominio/listacondominio.jsp").forward(req, res);
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
		else if (req.getParameter("acao") != null && req.getParameter("acao").equals("6")) {//TELA CONSULTA LISTAR CONDOMINIOS

			Connection connection = null;
			HttpSession session = req.getSession(true);
            User user = (User) session.getValue("user");
			
			try {
			
				connection = ConnectionFactory.getConnection();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				
				List<Condominio> listCondominio = condominioDAO.listar();
				
				req.setAttribute("listCondominio", listCondominio);
				req.getRequestDispatcher("/jsp/condominio/listacondominio.jsp").forward(req, res);
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


