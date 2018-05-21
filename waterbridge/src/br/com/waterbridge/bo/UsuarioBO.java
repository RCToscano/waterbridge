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

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.auxiliar.Constantes;
import br.com.waterbridge.auxiliar.Email;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.PassDAO;
import br.com.waterbridge.dao.PerfilDAO;
import br.com.waterbridge.dao.SituacaoDAO;
import br.com.waterbridge.dao.UserDAO;
import br.com.waterbridge.enuns.SexoEnum;
import br.com.waterbridge.modelo.Pass;
import br.com.waterbridge.modelo.Perfil;
import br.com.waterbridge.modelo.Situacao;
import br.com.waterbridge.modelo.User;

public class UsuarioBO extends HttpServlet {
//alterado
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

            if (relat.equals("cadUsuario")) {
            	PerfilDAO perfilDAO = new PerfilDAO(connection);
            	List<Perfil> listaPerfil = perfilDAO.listarTodos();
        		List<SexoEnum> listaSexo = SexoEnum.listCodigos();
        		
        		SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
        		
        		req.setAttribute("listSituacao", listSituacao);
        		req.setAttribute("listaSexo", listaSexo);
        		req.setAttribute("listaPerfil", listaPerfil);
        		req.setAttribute("display", "none");
        		req.setAttribute("titulo", "Cadastro");
        		req.setAttribute("botao", "Cadastrar");
        		req.getRequestDispatcher("/jsp/usuario/usuario.jsp").forward(req, res);
            } 
            else if (relat.equals("inserir")) {
            	SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
        		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        		User user = new User();
            	try {
            		Perfil perfil = new Perfil();
            		perfil.setIdPerfil(Auxiliar.converteLong(req.getParameter("perfil")));
            		user.setPerfil(perfil);
            		
            		user.setCpf(req.getParameter("cpf"));
            		user.setUsuario(req.getParameter("cpf"));
            		user.setNome(Auxiliar.removerCaracteres(req.getParameter("name").trim()).toUpperCase());
            		user.setDtNasc(formatoBanco.format(formatoData.parse(req.getParameter("dtNascimento"))));
            		user.setSexo(req.getParameter("sexo"));
            		user.setTelFixo(req.getParameter("telefoneFixo"));
            		user.setTelCel(req.getParameter("telefoneCelular"));
            		user.setEmail(req.getParameter("email"));
            		user.setEndereco(Auxiliar.removerCaracteres(req.getParameter("endereco").trim()).toUpperCase());
            		user.setNumero(Auxiliar.converteLong(req.getParameter("numero")));
            		user.setCompl(Auxiliar.removerCaracteres(req.getParameter("compl").trim()).toUpperCase());
            		user.setMunicipio(Auxiliar.removerCaracteres(req.getParameter("municipio").trim()).toUpperCase());
            		user.setUf(Auxiliar.removerCaracteres(req.getParameter("estado").trim()).toUpperCase());
            		user.setCep(req.getParameter("cep"));
            		if(req.getParameter("latitude").trim().equals("")) {
            			user.setCoordx("0.0");
            			user.setCoordy("0.0");
					}
					else {
						user.setCoordx(req.getParameter("latitude"));
						user.setCoordy(req.getParameter("longitude"));
					}
            		user.setSituacao(req.getParameter("situacao"));
            		
            		//Alteracao
            		if(req.getParameter("id") != null && !req.getParameter("id").isEmpty()) {
            			req.setAttribute("titulo", "Alteração");
	            		req.setAttribute("botao", "Alterar");
            			
            			user.setIdUser(Long.valueOf(req.getParameter("id")));
            			UserDAO userDAO = new UserDAO(connection);
            			userDAO.alterar(user);
	            		
	            		user.setDtNasc(formatoData.format(formatoBanco.parse(user.getDtNasc())));
	            		
	            		req.setAttribute("usuario", user);
	            		req.setAttribute("display", "none");
	            		req.setAttribute("sucesso", "Usuário "+user.getNome()+" alterado com sucesso!");
            		}
            		//Cadastro
            		else {
            			req.setAttribute("titulo", "Cadastro");
	            		req.setAttribute("botao", "Cadastrar");
            			
	            		UserDAO userDAO = new UserDAO(connection);
	            		
	            		//Ja cadastrado
	            		if(userDAO.buscarPorCpf(user.getCpf()) != null) {
	            			user.setDtNasc(formatoData.format(formatoBanco.parse(user.getDtNasc())));
	            			req.setAttribute("usuario", user);
	            			req.setAttribute("display", "block");
	            			req.setAttribute("aviso", "Usuário com CPF "+user.getCpf()+" já cadastrado!");
	            		}
	            		else {
	            			userDAO.inserir(user);
	            			
	            			User user2 = userDAO.buscarUltimo();
	            			user.setIdUser(user2.getIdUser());
	            			
	            			Pass pass = new Pass();
	            			String senha = Auxiliar.getRandomPass();
	            			pass.setPass(senha);
	            			pass.setIdUser(user.getIdUser());
	            			
	            			PassDAO passDAO = new PassDAO(connection);
	            			passDAO.inserir(pass);
	            			
	            			try {
	            				String mensagem = Email.corpoEmailCadastro(user.getNome(), user.getUsuario(), senha);
	    						Email.enviarEmail("WaterBridge - Cadastro", mensagem, user.getEmail());
	    						req.setAttribute("display", "none");
	    						req.setAttribute("sucesso", "Usuário "+user.getNome()+" cadastrado com sucesso! "
	    								+ "Um e-mail foi enviado para "+user.getEmail());
							} 
	            			catch (Exception e) {
								System.out.println(e);
								req.setAttribute("display", "none");
	    						req.setAttribute("sucesso", "Usuário "+user.getNome()+" cadastrado com sucesso! "
	    								+ "Não foi possível enviar o e-mail para "+user.getEmail());
							}
	            		}
	            		
            		}
				} 
            	catch (Exception e) {
            		System.out.println(e);
            		req.setAttribute("usuario", user);
            		req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
            		req.setAttribute("display", "block");
				}
            	finally {
            		PerfilDAO perfilDAO = new PerfilDAO(connection);
                	List<Perfil> listaPerfil = perfilDAO.listarTodos();
            		List<SexoEnum> listaSexo = SexoEnum.listCodigos();
            		
            		SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
    				List<Situacao> listSituacao = situacaoDAO.listar();
            		
            		req.setAttribute("listSituacao", listSituacao);
            		req.setAttribute("listaSexo", listaSexo);
            		req.setAttribute("listaPerfil", listaPerfil);
            		req.getRequestDispatcher("/jsp/usuario/usuario.jsp").forward(req, res);
            	}
            }
            
            //Consulta
            else if (relat.equals("consulta")) {
            	PerfilDAO perfilDAO = new PerfilDAO(connection);
            	List<Perfil> listaPerfil = perfilDAO.listarTodos();
        		
            	req.setAttribute("listaPerfil", listaPerfil);
            	req.setAttribute("display", "none");
            	req.getRequestDispatcher("/jsp/usuario/consulta.jsp").forward(req, res);
            }
            else if (relat.equals("pesquisar")) {
            	try {
	            	if(req.getParameter("usuario") != null && !req.getParameter("usuario").isEmpty()) {
	            		PerfilDAO perfilDAO = new PerfilDAO(connection);
	                	List<Perfil> listaPerfil = perfilDAO.listarTodos();
	                	req.setAttribute("listaPerfil", listaPerfil);

	                	UserDAO userDAO = new UserDAO(connection);
	                	List<User> listaUser = userDAO.buscarPorNome(req.getParameter("usuario"));
	            		
	            		if(listaUser.isEmpty()) {
		            		req.setAttribute("display", "none");
		            		req.setAttribute("informacao", "Nenhum resultado encontrado!");
		            		req.getRequestDispatcher("/jsp/usuario/consulta.jsp").forward(req, res);
	            		}
	            		else {
	            			req.setAttribute("lista", listaUser);
	            			req.setAttribute("display", "none");
	            			req.getRequestDispatcher("/jsp/usuario/consulta.jsp").forward(req, res);
	            		}
	            		
	            	}
	            	else if(req.getParameter("cpf") != null && !req.getParameter("cpf").isEmpty()) {
	            		PerfilDAO perfilDAO = new PerfilDAO(connection);
	                	List<Perfil> listaPerfil = perfilDAO.listarTodos();
	                	req.setAttribute("listaPerfil", listaPerfil);
	            		
	            		UserDAO userDAO = new UserDAO(connection);
	            		User user = userDAO.buscarPorCpf(req.getParameter("cpf"));
	            		
	            		if(user == null) {
		            		req.setAttribute("display", "none");
		            		req.setAttribute("informacao", "Nenhum resultado encontrado!");
		            		req.getRequestDispatcher("/jsp/usuario/consulta.jsp").forward(req, res);
	            		}
	            		else {
	            			List<SexoEnum> listaSexo = SexoEnum.listCodigos();
	            			SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
	        				List<Situacao> listSituacao = situacaoDAO.listar();
	                		req.setAttribute("listSituacao", listSituacao);
	            			req.setAttribute("listaSexo", listaSexo);
	            			req.setAttribute("usuario", user);
	            			req.setAttribute("display", "none");
	            			req.setAttribute("titulo", "Alteração");
	            			req.setAttribute("botao", "Alterar");
	            			req.getRequestDispatcher("/jsp/usuario/usuario.jsp").forward(req, res);
	            		}
	            	}
	            	else if(req.getParameter("endereco") != null && !req.getParameter("endereco").isEmpty()) {
	            		PerfilDAO perfilDAO = new PerfilDAO(connection);
	                	List<Perfil> listaPerfil = perfilDAO.listarTodos();
	                	req.setAttribute("listaPerfil", listaPerfil);
	            		
	            		UserDAO userDAO = new UserDAO(connection);
	            		List<User> listaUser = userDAO.buscarPorEndereco(req.getParameter("endereco"));
	            		
	            		if(listaUser.isEmpty()) {
		            		req.setAttribute("display", "none");
		            		req.setAttribute("informacao", "Nenhum resultado encontrado!");
		            		req.getRequestDispatcher("/jsp/usuario/consulta.jsp").forward(req, res);
	            		}
	            		else {
	            			req.setAttribute("lista", listaUser);
	            			req.setAttribute("display", "none");
	            			req.getRequestDispatcher("/jsp/usuario/consulta.jsp").forward(req, res);
	            		}
	            	}
	            	else {
	            		PerfilDAO perfilDAO = new PerfilDAO(connection);
	                	List<Perfil> listaPerfil = perfilDAO.listarTodos();
	                	req.setAttribute("listaPerfil", listaPerfil);
	            		
	            		UserDAO userDAO = new UserDAO(connection);
	            		List<User> listaUser = userDAO.buscarPorPerfil(req.getParameter("perfil"));
	            		
	            		if(listaUser.isEmpty()) {
	            			req.setAttribute("display", "none");
	            			req.setAttribute("informacao", "Nenhum resultado encontrado!");
	            			req.getRequestDispatcher("/jsp/usuario/consulta.jsp").forward(req, res);
	            		}
	            		else {
	            			req.setAttribute("lista", listaUser);
	            			req.setAttribute("display", "none");
	            			req.getRequestDispatcher("/jsp/usuario/consulta.jsp").forward(req, res);
	            		}
	            	}
            	} 
            	catch (Exception e) {
            		System.out.println(e);
            		
            		UserDAO userDAO = new UserDAO(ConnectionFactory.getConnection());
                	List<User> listaUsuarios = userDAO.listarTodos();
                	
                	req.setAttribute("listaUsuarios", listaUsuarios);
                	req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
                	req.setAttribute("display", "block");
                	req.getRequestDispatcher("/jsp/usuario/consulta.jsp").forward(req, res);
            	}
            }
            else if (relat.equals("detalhe")) {
            	UserDAO userDAO = new UserDAO(connection);
        		User user = userDAO.buscarPorId(req.getParameter("id"));
        		
        		PerfilDAO perfilDAO = new PerfilDAO(connection);
            	List<Perfil> listaPerfil = perfilDAO.listarTodos();
        		List<SexoEnum> listaSexo = SexoEnum.listCodigos();
        		SituacaoDAO situacaoDAO = new SituacaoDAO(connection);
				List<Situacao> listSituacao = situacaoDAO.listar();
        		req.setAttribute("listSituacao", listSituacao);
        		req.setAttribute("listaSexo", listaSexo);
        		req.setAttribute("listaPerfil", listaPerfil);
        		req.setAttribute("usuario", user);
        		req.setAttribute("display", "none");
        		req.setAttribute("titulo", "Alteração");
        		req.setAttribute("botao", "Alterar");
        		req.getRequestDispatcher("/jsp/usuario/usuario.jsp").forward(req, res);
            }
            
            //Perfil
            else if (relat.equals("perfil")) {
            	User user = (User) req.getSession().getAttribute("user");
            	
            	List<SexoEnum> listaSexo = SexoEnum.listCodigos();
            	req.setAttribute("usuario", user);
            	req.setAttribute("listaSexo", listaSexo);
            	req.setAttribute("display", "none");
            	req.getRequestDispatcher("/jsp/usuario/perfil.jsp").forward(req, res);
            }
            
            //Alterar Perfil
            else if (relat.equals("alterarPerfil")) {
            	SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
        		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        		User usuario = (User) req.getSession().getAttribute("user");
        		User user = new User();
            	try {
            		user.setCpf(req.getParameter("cpf"));
            		user.setNome(Auxiliar.removerCaracteres(req.getParameter("name").trim()).toUpperCase());
            		user.setDtNasc(formatoBanco.format(formatoData.parse(req.getParameter("dtNascimento"))));
            		user.setSexo(req.getParameter("sexo"));
            		user.setTelFixo(req.getParameter("telefoneFixo"));
            		user.setTelCel(req.getParameter("telefoneCelular"));
            		user.setEmail(req.getParameter("email"));
            		user.setEndereco(Auxiliar.removerCaracteres(req.getParameter("endereco").trim()).toUpperCase());
            		user.setNumero(Auxiliar.converteLong(req.getParameter("numero")));
            		user.setCompl(Auxiliar.removerCaracteres(req.getParameter("compl").trim()).toUpperCase());
            		user.setMunicipio(Auxiliar.removerCaracteres(req.getParameter("municipio").trim()).toUpperCase());
            		user.setUf(Auxiliar.removerCaracteres(req.getParameter("estado").trim()).toUpperCase());
            		user.setCep(req.getParameter("cep"));
            		if(req.getParameter("latitude").trim().equals("")) {
            			user.setCoordx("0.0");
            			user.setCoordy("0.0");
					}
					else {
						user.setCoordx(req.getParameter("latitude"));
						user.setCoordy(req.getParameter("longitude"));
					}
            		user.setSituacao(usuario.getSituacao());
            		user.setUsuario(usuario.getUsuario());
            		user.setPerfil(usuario.getPerfil());
        			user.setIdUser(usuario.getIdUser());
        			
        			UserDAO userDAO = new UserDAO(connection);
        			userDAO.alterar(user);
        			
            		user.setDtNasc(formatoData.format(formatoBanco.parse(user.getDtNasc())));
            		
            		List<SexoEnum> listaSexo = SexoEnum.listCodigos();
            		req.setAttribute("listaSexo", listaSexo);
            		req.setAttribute("usuario", user);
            		req.setAttribute("display", "none");
            		req.setAttribute("sucesso", "Alterações realizadas com sucesso!");
            		
            		req.getRequestDispatcher("/jsp/usuario/perfil.jsp").forward(req, res);
				} 
            	catch (Exception e) {
            		System.out.println(e);
            		List<SexoEnum> listaSexo = SexoEnum.listCodigos();
                	req.setAttribute("listaSexo", listaSexo);
            		req.setAttribute("usuario", user);
            		req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
            		req.setAttribute("display", "block");
            		req.getRequestDispatcher("/jsp/usuario/perfil.jsp").forward(req, res);
				}
            }
            
            
            //Alterar senha
            else if (relat.equals("alterarSenha")) {
            	User user = (User) req.getSession().getAttribute("user");
            	try {
            		connection = ConnectionFactory.getConnection();
            		PassDAO passDAO = new PassDAO(connection);
            		
            		Pass pass = passDAO.buscarSenha(user.getIdUser());
            		String senha = req.getParameter("senha");
            		if(senha.equals(pass.getPass())) {
            			pass.setPass(req.getParameter("novaSenha"));
            			passDAO.alterar(pass);
            			
            			req.setAttribute("display", "none");
            			req.setAttribute("sucesso", "Senha alterada com sucesso!");
            		}
            		else {
            			req.setAttribute("aviso", "Senha atual informada incorreta!");
                    	req.setAttribute("display", "block");
            		}
            	} 
            	catch (Exception e) {
            		System.out.println(e);
            		req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
                	req.setAttribute("display", "block");
            	}
            	finally {
            		List<SexoEnum> listaSexo = SexoEnum.listCodigos();
                	req.setAttribute("listaSexo", listaSexo);
            		req.setAttribute("usuario", user);
            		req.getRequestDispatcher("/jsp/usuario/perfil.jsp").forward(req, res);
            	}
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
