package br.com.waterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.auxiliar.Constantes;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.BridgeDAO;
import br.com.waterbridge.dao.CondominioDAO;
import br.com.waterbridge.dao.FabricMedidorDAO;
import br.com.waterbridge.dao.MedidorDAO;
import br.com.waterbridge.dao.SituacaoDAO;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.FabricMedidor;
import br.com.waterbridge.modelo.Medidor;
import br.com.waterbridge.modelo.Situacao;
import br.com.waterbridge.modelo.User;

public class MedidorBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String relat = "";
		Connection connection = null;
        try {
        	connection = ConnectionFactory.getConnection();
        	
            if (req.getParameter("acao") != null) {
                relat = req.getParameter("acao");
            }

            //Cadastro
            if (relat.equals("cadastro")) {
            	SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = condominioDAO.listar();
				
				FabricMedidorDAO fabricMedidorDAO = new FabricMedidorDAO(connection);
				List<FabricMedidor> listFabricante = fabricMedidorDAO.listar();
            	
				req.setAttribute("listSituacao", listSituacao);
				req.setAttribute("listCondominio", listCondominio);
				req.setAttribute("listFabricante", listFabricante);
        		req.setAttribute("display", "none");
        		req.setAttribute("titulo", "Cadastro");
        		req.setAttribute("botao", "Cadastrar");
        		req.getRequestDispatcher("/jsp/medidor/medidor.jsp").forward(req, res);
            } 
            
            //Listar Bridge
            if (relat.equals("listarbridge")) {
            	
            	try {

            		BridgeDAO bridgeDAO = new BridgeDAO(connection);
                	List<Bridge> listBridge = bridgeDAO.listarPorIdCondominio(Long.parseLong(req.getParameter("idCondominio")));
                	
                	String json = new Gson().toJson(listBridge);
                	
                	res.setContentType("application/json");
                	res.setCharacterEncoding("UTF-8");
                	res.getWriter().write(json); 
            	}
                catch (Exception e) {

                	String json = new Gson().toJson("");
                	
                	res.setContentType("application/json");
                	res.setCharacterEncoding("UTF-8");
                	res.getWriter().write(json);   
                }
                finally {
                    if(connection != null) {
                        try {connection.close();} catch (SQLException ex) {}
                    }
                }
            } 
            
            //Cadastro e Alteracao
            else if (relat.equals("inserir")) {
            	Medidor medidor = new Medidor();
            	try {
            		User user = (User) req.getSession().getAttribute("user");
            		
                    medidor.setIdUser(user.getIdUser());
            		medidor.setIdFabricMedidor(Long.parseLong(req.getParameter("fabricante").trim().toUpperCase()));
            		medidor.setModelo(Auxiliar.removerCaracteres(req.getParameter("modelo").trim().toUpperCase()));
            		medidor.setSerie(Auxiliar.removerCaracteres(req.getParameter("serie").trim().toUpperCase()));
            		medidor.setTipo(Auxiliar.removerCaracteres(req.getParameter("tipo").trim().toUpperCase()));
            		medidor.setChaveDeCripto(req.getParameter("chave"));
            		medidor.setValidBateria(Auxiliar.converteInteger(req.getParameter("bateria")));
            		medidor.setNumeroMedidor(Auxiliar.removerCaracteres(req.getParameter("numeroMedidor").trim().toUpperCase()));
            		medidor.setEndereco(Auxiliar.removerCaracteres(req.getParameter("endereco").trim().toUpperCase()));
            		medidor.setNumero(Long.parseLong(req.getParameter("numero")));
            		medidor.setCompl(Auxiliar.removerCaracteres(req.getParameter("compl").trim().toUpperCase()));
            		medidor.setMunicipio(Auxiliar.removerCaracteres(req.getParameter("municipio").trim().toUpperCase()));
            		medidor.setUf(req.getParameter("estado").trim().toUpperCase());
            		medidor.setCep(req.getParameter("cep").trim().toUpperCase());
					if(req.getParameter("latitude").trim().equals("")) {
						medidor.setCoordX("0.0");
						medidor.setCoordY("0.0");
					}
					else {
						medidor.setCoordX(req.getParameter("latitude"));
						medidor.setCoordY(req.getParameter("longitude"));
					}
            		
            		
            		String split[] = req.getParameter("bridge").split(";");
            		Long idBridge = Long.valueOf(split[0]);
					String deviceNum = split[1];
            		
            		medidor.setIdBridge(idBridge);
            		medidor.setDeviceNum(deviceNum);
            		medidor.setIdCondominio(Long.parseLong(req.getParameter("idCondominio")));
            		medidor.setMeterPosition(Auxiliar.converteInteger(req.getParameter("posicao")));
            		
            		medidor.setSituacao(req.getParameter("situacao"));
            		medidor.setObs(Auxiliar.removerCaracteres(req.getParameter("descricao").trim().toUpperCase()));
            		
            		MedidorDAO medidorDAO = new MedidorDAO(connection);

            		//Alteracao
            		if(req.getParameter("id") != null && !req.getParameter("id").isEmpty()) {
            			req.setAttribute("titulo", "Alteração");
            			req.setAttribute("botao", "Alterar");
            			
            			medidor.setIdMedidor(Long.valueOf(req.getParameter("id")));
            			medidorDAO.alterar(medidor);
            			
            			req.setAttribute("medidor", medidor);
            			req.setAttribute("display", "none");
	            		req.setAttribute("sucesso", "Medidor alterado com sucesso!");
            		}
            		//Cadastro
            		else {
            			req.setAttribute("titulo", "Cadastro");
            			req.setAttribute("botao", "Cadastrar");
            			
						if (medidorDAO.buscarPorBridgePosicao(medidor.getIdBridge(),
								medidor.getMeterPosition()) != null) {
							req.setAttribute("medidor", medidor);
							req.setAttribute("display", "block");
							req.setAttribute("aviso", "Medidor já cadastrado para o Bridge " + medidor.getDeviceNum()
									+ " na posição " + medidor.getMeterPosition() + "!");
						}
						else if (medidorDAO.buscarPorFabricanteNumero(medidor.getIdFabricMedidor(),
								medidor.getNumeroMedidor()) != null) {
							
							FabricMedidorDAO fabricMedidorDAO = new FabricMedidorDAO(connection);
							FabricMedidor fabricMedidor = fabricMedidorDAO.buscar(medidor.getIdFabricMedidor());
							
							req.setAttribute("medidor", medidor);
							req.setAttribute("display", "block");
							req.setAttribute("aviso", "Medidor Número: " + medidor.getNumero() + ", Fabricante: "
									+ fabricMedidor.getFabricante() + " já cadastrado!");
						}
						else {
							medidorDAO.inserir(medidor);
							
							req.setAttribute("display", "none");
							req.setAttribute("sucesso", "Medidor cadastrado com sucesso!");
						}
            		}
            		
				} 
            	catch (Exception e) {
            		System.out.println(e);
            		req.setAttribute("medidor", medidor);
            		req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
            		req.setAttribute("display", "block");
				}
            	finally {
            		SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
    				List<Situacao> listSituacao = situacaoDAO.listar();
    				
    				CondominioDAO condominioDAO = new CondominioDAO(connection);
    				List<Condominio> listCondominio = condominioDAO.listar();
    				
    				FabricMedidorDAO fabricMedidorDAO = new FabricMedidorDAO(connection);
    				List<FabricMedidor> listFabricante = fabricMedidorDAO.listar();
                	
    				req.setAttribute("listSituacao", listSituacao);
    				req.setAttribute("listCondominio", listCondominio);
    				req.setAttribute("listFabricante", listFabricante);
    				req.getRequestDispatcher("/jsp/medidor/medidor.jsp").forward(req, res);
            	}
            }
            
            //Consulta
            else if (relat.equals("consulta")) {
            	MedidorDAO medidorDAO = new MedidorDAO(connection);
            	List<Medidor> listaMedidor = medidorDAO.listarTodos();
            	
            	req.setAttribute("listaMedidor", listaMedidor);
            	req.setAttribute("display", "none");
            	req.getRequestDispatcher("/jsp/medidor/consulta.jsp").forward(req, res);
            }
            
            //Lista de Medidor
            else if (relat.equals("pesquisar")) {
            	try {
            		if(req.getParameter("medidor").equals("todos")) {
            			MedidorDAO medidorDAO = new MedidorDAO(connection);
    	            	List<Medidor> listaMedidor = medidorDAO.listarTodos();
            		
    	            	if(listaMedidor.isEmpty()) {
    	            		req.setAttribute("display", "none");
		            		req.setAttribute("informacao", "Nenhum resultado encontrado!");
		            		req.getRequestDispatcher("/jsp/medidor/consulta.jsp").forward(req, res);
    	            	}
    	            	else {
    	            		req.setAttribute("listaMedidor", listaMedidor);
    	            		req.setAttribute("lista", listaMedidor);
    	            		req.setAttribute("display", "none");
    	            		req.getRequestDispatcher("/jsp/medidor/consulta.jsp").forward(req, res);
    	            	}
            		}
            		else {
            			MedidorDAO medidorDAO = new MedidorDAO(connection);
            			Medidor medidor = medidorDAO.buscarPorId(req.getParameter("medidor"));
            			
            			SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
        				List<Situacao> listSituacao = situacaoDAO.listar();
        				
        				CondominioDAO condominioDAO = new CondominioDAO(connection);
        				List<Condominio> listCondominio = condominioDAO.listar();
        				
        				FabricMedidorDAO fabricMedidorDAO = new FabricMedidorDAO(connection);
        				List<FabricMedidor> listFabricante = fabricMedidorDAO.listar();
                    	
        				req.setAttribute("listSituacao", listSituacao);
        				req.setAttribute("listCondominio", listCondominio);
        				req.setAttribute("listFabricante", listFabricante);
            			req.setAttribute("medidor", medidor);
            			req.setAttribute("display", "none");
            			req.setAttribute("titulo", "Alteração");
            			req.setAttribute("botao", "Alterar");
            			req.getRequestDispatcher("/jsp/medidor/medidor.jsp").forward(req, res);
            		}
            	} 
            	catch (Exception e) {
					System.out.println(e);
					
					MedidorDAO medidorDAO = new MedidorDAO(connection);
	            	List<Medidor> listaMedidor = medidorDAO.listarTodos();
	            	
	            	req.setAttribute("listaMedidor", listaMedidor);
	            	req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
            		req.setAttribute("display", "block");
	            	req.getRequestDispatcher("/jsp/medidor/consulta.jsp").forward(req, res);
				}
            }
            
            //Detalhe do Medidor
            else if (relat.equals("detalhe")) {
            	MedidorDAO medidorDAO = new MedidorDAO(connection);
    			Medidor medidor = medidorDAO.buscarPorId(req.getParameter("id"));
    			
    			SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = condominioDAO.listar();
				
				FabricMedidorDAO fabricMedidorDAO = new FabricMedidorDAO(connection);
				List<FabricMedidor> listFabricante = fabricMedidorDAO.listar();
            	
				req.setAttribute("listSituacao", listSituacao);
				req.setAttribute("listCondominio", listCondominio);
				req.setAttribute("listFabricante", listFabricante);
				req.setAttribute("medidor", medidor);
    			req.setAttribute("display", "none");
    			req.setAttribute("titulo", "Alteração");
    			req.setAttribute("botao", "Alterar");
    			req.getRequestDispatcher("/jsp/medidor/medidor.jsp").forward(req, res);
            }
            
            
        }
        catch (Exception e) {
            req.setAttribute("erro", e.toString());
            req.getRequestDispatcher("/jsp/erro.jsp").forward(req, res);
        }
        finally {
            if(connection != null) {
                try {connection.close();} catch (SQLException ex) {}
            }
        }
    }
}
