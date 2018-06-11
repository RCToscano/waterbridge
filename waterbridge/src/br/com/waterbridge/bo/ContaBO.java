package br.com.waterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.auxiliar.Constantes;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.CondominioDAO;
import br.com.waterbridge.dao.ContaDAO;
import br.com.waterbridge.dao.ContaFotoDAO;
import br.com.waterbridge.dao.EmpresaDAO;
import br.com.waterbridge.dao.LogSqlDAO;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.Conta;
import br.com.waterbridge.modelo.ContaFoto;
import br.com.waterbridge.modelo.Empresa;
import br.com.waterbridge.modelo.User;
import br.com.waterbridge.reldao.RelContaDAO;
import br.com.waterbridge.relmodelo.RelConta;

public class ContaBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String relat = "";
		Connection connection = null;
		User user = (User) req.getSession().getAttribute("user");
        try {
        	connection = ConnectionFactory.getConnection();
        	
            if (req.getParameter("acao") != null) {
                relat = req.getParameter("acao");
            }
        	
        	
        	//Consulta
            if (relat.equals("consulta")) {
            	EmpresaDAO empresaDAO = new EmpresaDAO(connection);
				List<Empresa> listEmpresa = empresaDAO.listarPorUsuario(user.getIdUser());
				
				req.setAttribute("listEmpresa", listEmpresa);
        		req.setAttribute("display", "none");
        		req.getRequestDispatcher("/jsp/conta/consulta.jsp").forward(req, res);
            }
            
            //Pesquisar
            if (relat.equals("pesquisar")) {
            	try {
            		
					ContaDAO contaDAO = new ContaDAO(connection);
					
					List<Conta> listConta = contaDAO.listarPorEmpresaLocal(
							Long.parseLong(req.getParameter("idEmpresa")),
							Long.parseLong(req.getParameter("idCondominio")));
					
					RelContaDAO relContaDAO = new RelContaDAO(connection);
					
					List<RelConta> listRelConta = relContaDAO.listarPorIdCondominio(Long.parseLong(req.getParameter("idCondominio")));
							
					String json = new Gson().toJson(listRelConta);
    				
    				res.setContentType("application/json");
    				res.setCharacterEncoding("UTF-8");
    				res.getWriter().write(json);  
				} 
            	catch (Exception e) {
            		System.out.println(e);
					try {
						new LogSqlDAO(connection).inserir(user.getIdUser(), "", e.getMessage(), "ContaBO", relat);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
            		req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
            		req.setAttribute("display", "block");
				}
            }
            
            //Cadastro
            else if (relat.equals("cadastro")) {
            	EmpresaDAO empresaDAO = new EmpresaDAO(connection);
            	List<Empresa> listEmpresa = empresaDAO.listarPorUsuario(user.getIdUser());
            	
            	req.setAttribute("listEmpresa", listEmpresa);
            	req.setAttribute("display", "none");
            	req.setAttribute("titulo", "Cadastro");
            	req.setAttribute("botao", "Cadastrar");
            	req.getRequestDispatcher("/jsp/conta/conta.jsp").forward(req, res);
            }
            
            //Combo Local
            else if (relat.equals("1")) {
    			try {
    				CondominioDAO condominioDAO = new CondominioDAO(connection);
					List<Condominio> listCondominio = condominioDAO.listarPorUsuario(user.getIdUser(),
							Long.parseLong(req.getParameter("idEmpresa")));
    				
    				String json = new Gson().toJson(listCondominio);
    				
    				res.setContentType("application/json");
    				res.setCharacterEncoding("UTF-8");
    				res.getWriter().write(json);   
    			}
    	        catch (Exception e) {
    	        	System.out.println(e);
					try {
						new LogSqlDAO(connection).inserir(user.getIdUser(), "", e.getMessage(), "ContaBO", relat);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
            		req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
            		req.setAttribute("display", "block");
    	        }
            }
            
            //Cadastro e Alteracao
            else if (relat.equals("inserir")) {
            	Conta conta = new Conta();
            	
            	req.setAttribute("titulo", "Cadastro");
    			req.setAttribute("botao", "Cadastrar");

    			SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
    			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            	try {
            		
            		String pathTemp = Constantes.PATH_TEMP_LOGO_REMOTO;
            		String pathFinal = Constantes.PATH_FINAL_LOGO_REMOTO;
            		if(ConnectionFactory.getUsuario().equals("root")) {
            			pathTemp = Constantes.PATH_TEMP_LOGO_LOCAL;
            			pathFinal = Constantes.PATH_FINAL_LOGO_LOCAL;
            		}
            		
            		MultipartRequest mpr = new MultipartRequest(req, pathTemp, 200 * 1024 * 1024);
            		String arquivoTemp = "";
            		if(mpr.getFileNames() != null && mpr.getFileNames().hasMoreElements()) {
            			arquivoTemp = mpr.getFilesystemName((String) mpr.getFileNames().nextElement( ));
            		}
                    
                    conta.setIdUser(user.getIdUser());
                    conta.setIdEmpresa(Long.parseLong(mpr.getParameter("idEmpresa")));
                    conta.setIdCondominio(Long.parseLong(mpr.getParameter("idCondominio")));
                    conta.setConsumo(Double.parseDouble(mpr.getParameter("consumo").replace(".", "").replace(",", ".")));
                    conta.setValor(Double.parseDouble(mpr.getParameter("valor").replace(".", "").replace(",", ".")));
                    conta.setDtLeituraAnterior(formatoBanco.format(formatoData.parse(mpr.getParameter("dtLeituraAnterior"))));
                    conta.setDtLeituraAtual(formatoBanco.format(formatoData.parse(mpr.getParameter("dtLeituraAtual"))));
                    conta.setObs(Auxiliar.removerCaracteres(mpr.getParameter("obs").trim()));
                    
                    ContaDAO contaDAO = new ContaDAO(connection);
                    
                    //Alteracao
            		if(mpr.getParameter("id") != null && !mpr.getParameter("id").isEmpty()) {
            			req.setAttribute("titulo", "Alteração");
            			req.setAttribute("botao", "Alterar");
            			
            			conta.setIdConta(Long.valueOf(mpr.getParameter("id")));
            			contaDAO.alterar(conta);
            			
            			conta.setDtLeituraAnterior(formatoData.format(formatoBanco.parse(conta.getDtLeituraAnterior())));
                        conta.setDtLeituraAtual(formatoData.format(formatoBanco.parse(conta.getDtLeituraAtual())));
            			
            			req.setAttribute("conta", conta);
            			req.setAttribute("display", "none");
	            		req.setAttribute("sucesso", "Conta alterada com sucesso!");
            		}
            		
            		//Cadastro
            		else {
            			req.setAttribute("titulo", "Cadastro");
            			req.setAttribute("botao", "Cadastrar");
            			
            			contaDAO.inserir(conta);
            			
						req.setAttribute("display", "none");
						req.setAttribute("sucesso", "Conta cadastrada com sucesso!");
            		}
            		
            		//Logo
					if(arquivoTemp != null && !arquivoTemp.isEmpty()) {
						String nomeFinal = "IdEmpresa" + conta.getIdEmpresa() 
								+ "_IdCondominio" + conta.getIdCondominio() + "_" 
								+ Auxiliar.dataAtual() + Auxiliar.recuperaExtensao(arquivoTemp);
						Auxiliar.copiarArquivo(pathTemp + arquivoTemp, pathFinal + nomeFinal);
						
						ContaFotoDAO contaFotoDAO = new ContaFotoDAO(connection);
						ContaFoto contaFoto = new ContaFoto();
						
						if(mpr.getParameter("id") != null && !mpr.getParameter("id").isEmpty()) {
							contaFoto.setIdConta(conta.getIdConta());
						}
						else {
							contaFoto.setIdConta(contaDAO.buscarUltimo());
						}
						
						contaFoto.setIdUser(user.getIdUser());
						contaFoto.setDiretorio(pathFinal);
						contaFoto.setNome(nomeFinal);
						
						contaFotoDAO.inserir(contaFoto);
					}
            		
            	} 
            	catch (Exception e) {
            		System.out.println(e);
					try {
						new LogSqlDAO(connection).inserir(user.getIdUser(), "", e.getMessage(), "ContaBO", relat);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
            		
					conta.setDtLeituraAnterior(formatoData.format(formatoBanco.parse(conta.getDtLeituraAnterior())));
                    conta.setDtLeituraAtual(formatoData.format(formatoBanco.parse(conta.getDtLeituraAtual())));
					
            		req.setAttribute("conta", conta);
            		req.setAttribute("sucesso", "");
            		req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
            		req.setAttribute("display", "block");
				}
            	finally {
            		EmpresaDAO empresaDAO = new EmpresaDAO(connection);
    				List<Empresa> listEmpresa = empresaDAO.listarPorUsuario(user.getIdUser());
    				
    				CondominioDAO condominioDAO = new CondominioDAO(connection);
    				List<Condominio> listCondominio = condominioDAO.listarPorUsuario(user.getIdUser(),
    						conta.getIdEmpresa());
    				
    				req.setAttribute("listCondominio", listCondominio);
    				req.setAttribute("listEmpresa", listEmpresa);
            		req.getRequestDispatcher("/jsp/conta/conta.jsp").forward(req, res);
            	}
            }
            
            //Detalhe
            else if (relat.equals("detalhe")) {
            	EmpresaDAO empresaDAO = new EmpresaDAO(connection);
            	List<Empresa> listEmpresa = empresaDAO.listarPorUsuario(user.getIdUser());
            	
            	ContaDAO contaDAO = new ContaDAO(connection);
            	Conta conta = contaDAO.buscar(Long.parseLong(req.getParameter("idConta")));

            	CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = condominioDAO.listarPorUsuario(user.getIdUser(),
						conta.getIdEmpresa());
            	
            	req.setAttribute("conta", conta);
            	req.setAttribute("listEmpresa", listEmpresa);
            	req.setAttribute("listCondominio", listCondominio);
            	req.setAttribute("display", "none");
            	req.setAttribute("titulo", "Alteração");
            	req.setAttribute("botao", "Alterar");
            	req.getRequestDispatcher("/jsp/conta/conta.jsp").forward(req, res);
            }
            
         	//Fotos
            else if (relat.equals("foto")) {
        		Long idConta = Long.parseLong(req.getParameter("idConta"));
        		
        		ContaFotoDAO fotoDAO = new ContaFotoDAO(connection);
        		List<ContaFoto> listFotos = fotoDAO.listarPorConta(idConta);
        		
        		req.setAttribute("listaFotos", listFotos);
        		req.setAttribute("idConta", idConta);
        		req.getRequestDispatcher("/jsp/conta/fotos.jsp").forward(req, res);
            }
        	
        }
        catch (Exception e) {
        	try {
        		connection = ConnectionFactory.getConnection();
				new LogSqlDAO(connection).inserir(user.getIdUser(), "", e.getMessage(), "ContaBO", relat);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
