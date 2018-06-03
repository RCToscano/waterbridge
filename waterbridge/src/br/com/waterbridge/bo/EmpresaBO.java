package br.com.waterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.auxiliar.Constantes;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.EmpresaDAO;
import br.com.waterbridge.dao.LogSqlDAO;
import br.com.waterbridge.dao.SituacaoDAO;
import br.com.waterbridge.modelo.Empresa;
import br.com.waterbridge.modelo.Situacao;
import br.com.waterbridge.modelo.User;

public class EmpresaBO extends HttpServlet {

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
				
				req.setAttribute("listSituacao", listSituacao);
        		req.setAttribute("display", "none");
        		req.setAttribute("titulo", "Cadastro");
        		req.setAttribute("botao", "Cadastrar");
        		req.getRequestDispatcher("/jsp/empresa/empresa.jsp").forward(req, res);
            }
            
            //Cadastro e Alteracao
            else if (relat.equals("inserir")) {
            	Empresa empresa = new Empresa();
            	User user = (User) req.getSession().getAttribute("user");
            	try {
            		String pathTemp = Constantes.PATH_TEMP_LOGO_REMOTO;
            		String pathFinal = Constantes.PATH_FINAL_LOGO_REMOTO;
            		if(ConnectionFactory.getUsuario().equals("root")) {
            			pathTemp = Constantes.PATH_TEMP_LOGO_LOCAL;
            			pathFinal = Constantes.PATH_FINAL_LOGO_LOCAL;
            		}
            		
            		MultipartRequest mpr = new MultipartRequest(req, pathTemp, 200 * 1024 * 1024);
                    String arquivoTemp = mpr.getFilesystemName((String) mpr.getFileNames().nextElement( ));
            		
            		empresa.setIdUser(user.getIdUser());
            		empresa.setNome(Auxiliar.removerCaracteres(mpr.getParameter("nome").trim()).toUpperCase());
            		empresa.setCnp(mpr.getParameter("cnpj"));
            		empresa.setResponsavel(Auxiliar.removerCaracteres(mpr.getParameter("responsavel").trim()).toUpperCase());
            		empresa.setTelFixo(mpr.getParameter("telefoneFixo"));
            		empresa.setTelCel(mpr.getParameter("telefoneCelular"));
            		empresa.setEmail(mpr.getParameter("email").trim());
            		empresa.setSituacao(mpr.getParameter("situacao"));
            		empresa.setEndereco(Auxiliar.removerCaracteres(mpr.getParameter("endereco").trim()).toUpperCase());
            		empresa.setNumero(Long.parseLong(mpr.getParameter("numero")));
            		empresa.setCompl(Auxiliar.removerCaracteres(mpr.getParameter("compl").trim()).toUpperCase());
            		empresa.setMunicipio(Auxiliar.removerCaracteres(mpr.getParameter("municipio").trim()).toUpperCase());
            		empresa.setUf(mpr.getParameter("estado").trim().toUpperCase());
            		empresa.setCep(mpr.getParameter("cep").trim());
            		empresa.setObs(Auxiliar.removerCaracteres(mpr.getParameter("obs").trim()));
					if(mpr.getParameter("latitude").trim().equals("")) {
						empresa.setCoordX("0.0");
						empresa.setCoordY("0.0");
					}
					else {
						empresa.setCoordX(mpr.getParameter("latitude"));
						empresa.setCoordY(mpr.getParameter("longitude"));
					}
					

					EmpresaDAO empresaDAO = new EmpresaDAO(connection);
					
					//Logo
					if(!arquivoTemp.isEmpty()) {
						String nomeFinal = empresa.getNome().replace(" ", "_") + "_" + Auxiliar.dataAtual()
								+ Auxiliar.recuperaExtensao(arquivoTemp);
						Auxiliar.copiarArquivo(pathTemp + arquivoTemp, pathFinal + nomeFinal);

						empresa.setLogoPDir(pathFinal);
						empresa.setLogoPNome(nomeFinal);
					}
					
					
					//Alteracao
            		if(mpr.getParameter("id") != null && !mpr.getParameter("id").isEmpty()) {
            			req.setAttribute("titulo", "Alteração");
            			req.setAttribute("botao", "Alterar");
            			
            			empresa.setIdEmpresa(Long.valueOf(mpr.getParameter("id")));
            			empresaDAO.alterar(empresa);
            			
            			req.setAttribute("empresa", empresa);
            			req.setAttribute("display", "none");
	            		req.setAttribute("sucesso", "Empresa alterada com sucesso!");
            		}
            		//Cadastro
            		else {
            			req.setAttribute("titulo", "Cadastro");
            			req.setAttribute("botao", "Cadastrar");
            			
            			empresaDAO.inserir(empresa);
            			
						req.setAttribute("display", "none");
						req.setAttribute("sucesso", "Empresa cadastrada com sucesso!");
            		}
            	} 
            	catch (Exception e) {
            		System.out.println(e);
            		
					try {
						new LogSqlDAO(connection).inserir(user.getIdUser(), "", e.getMessage(), "EmpresaBO", relat);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
            		
            		req.setAttribute("empresa", empresa);
            		req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
            		req.setAttribute("display", "block");
				}
            	finally {
            		SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
    				List<Situacao> listSituacao = situacaoDAO.listar();
    				
    				req.setAttribute("listSituacao", listSituacao);
            		req.getRequestDispatcher("/jsp/empresa/empresa.jsp").forward(req, res);
            	}
            }
            
            //Consulta
            else if (relat.equals("consulta")) {
            	EmpresaDAO empresaDAO = new EmpresaDAO(connection);
            	List<Empresa> listaEmpresa = empresaDAO.listarTodos();
            	
            	req.setAttribute("listaEmpresa", listaEmpresa);
            	req.setAttribute("display", "none");
            	req.getRequestDispatcher("/jsp/empresa/consulta.jsp").forward(req, res);
            }
            
            //Lista de Empresa
            else if (relat.equals("pesquisar")) {
            	try {
            		EmpresaDAO empresaDAO = new EmpresaDAO(connection);
            		if(!req.getParameter("empresa").isEmpty()) {
            			Empresa empresa = empresaDAO.buscarPorId(Long.valueOf(req.getParameter("empresa")));
            			
            			SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
        				List<Situacao> listSituacao = situacaoDAO.listar();
        				
        				req.setAttribute("listSituacao", listSituacao);
        				req.setAttribute("empresa", empresa);
        				req.setAttribute("titulo", "Alteração");
            			req.setAttribute("botao", "Alterar");
            			req.setAttribute("display", "none");
            			req.getRequestDispatcher("/jsp/empresa/empresa.jsp").forward(req, res);
            		}
            		else if(!req.getParameter("cnpj").isEmpty()) {
            			Empresa empresa = empresaDAO.buscarPorCnp(req.getParameter("medidor"));
            			if(empresa != null) {
            				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
            				List<Situacao> listSituacao = situacaoDAO.listar();
            				
            				req.setAttribute("listSituacao", listSituacao);
            				req.setAttribute("empresa", empresa);
            				req.setAttribute("titulo", "Alteração");
                			req.setAttribute("botao", "Alterar");
                			req.setAttribute("display", "none");
                			req.getRequestDispatcher("/jsp/empresa/empresa.jsp").forward(req, res);
            			}
            			else {
            				List<Empresa> listaEmpresa = empresaDAO.listarTodos();

            				SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
            				List<Situacao> listSituacao = situacaoDAO.listar();
            				
            				req.setAttribute("listSituacao", listSituacao);
                        	req.setAttribute("listaEmpresa", listaEmpresa);
            				req.setAttribute("display", "none");
		            		req.setAttribute("informacao", "Nenhum resultado encontrado!");
		            		req.getRequestDispatcher("/jsp/empresa/consulta.jsp").forward(req, res);
            			}
            		}
            		else {
            			List<Empresa> listaEmpresa = empresaDAO.listarTodos();
                		
    	            	if(listaEmpresa.isEmpty()) {
    	            		req.setAttribute("display", "none");
		            		req.setAttribute("informacao", "Nenhum resultado encontrado!");
		            		req.getRequestDispatcher("/jsp/empresa/consulta.jsp").forward(req, res);
    	            	}
    	            	else {
    	            		req.setAttribute("listaEmpresa", listaEmpresa);
    	            		req.setAttribute("lista", listaEmpresa);
    	            		req.setAttribute("display", "none");
    	            		req.getRequestDispatcher("/jsp/empresa/consulta.jsp").forward(req, res);
    	            	}
            		}
            	} 
            	catch (Exception e) {
					System.out.println(e);

					EmpresaDAO empresaDAO = new EmpresaDAO(connection);
					List<Empresa> listaEmpresa = empresaDAO.listarTodos();
                	
					req.setAttribute("listaEmpresa", listaEmpresa);
	            	req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
            		req.setAttribute("display", "block");
	            	req.getRequestDispatcher("/jsp/empresa/consulta.jsp").forward(req, res);
				}
            } 
            
            //Detalhe da Empresa
            else if (relat.equals("detalhe")) {
            	EmpresaDAO empresaDAO = new EmpresaDAO(connection);
            	Empresa empresa = empresaDAO.buscarPorId(Long.valueOf(req.getParameter("id")));
    			
    			SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
				
				req.setAttribute("listSituacao", listSituacao);
				req.setAttribute("empresa", empresa);
				req.setAttribute("titulo", "Alteração");
    			req.setAttribute("botao", "Alterar");
    			req.setAttribute("display", "none");
    			req.getRequestDispatcher("/jsp/empresa/empresa.jsp").forward(req, res);
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
