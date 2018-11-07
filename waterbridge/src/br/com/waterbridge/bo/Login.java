package br.com.waterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.auxiliar.Constantes;
import br.com.waterbridge.auxiliar.Email;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.LogSqlDAO;
import br.com.waterbridge.dao.PassDAO;
import br.com.waterbridge.dao.SessaoDAO;
import br.com.waterbridge.dao.UserDAO;
import br.com.waterbridge.modelo.Pass;
import br.com.waterbridge.modelo.Sessao;
import br.com.waterbridge.modelo.User;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String relat = "";
		Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection();

            if (req.getParameter("r") != null) {
                relat = req.getParameter("r");
            }

            if (relat.equals("login")) {
            	String email = req.getParameter("usuario");
                String senha = req.getParameter("password");
                
            	UserDAO userDAO = new UserDAO(connection);
            	User user = userDAO.login(email, senha);
            	
                if (user == null) {
                    //usuario inexistente
                    req.setAttribute("loginErro", "Usuario/Senha Incorreta. Tente Novamente!");
                    req.getRequestDispatcher("/index.jsp").forward(req, res);
                } 
                else if (user.getSituacao().equals("I")) {
                    //verificando se o usuario esta ativo
                    req.setAttribute("loginErro", "Usuario Inativo no Sistema!");
                    req.getRequestDispatcher("/index.jsp").forward(req, res);
                } 
                else {
                    //vai para home page
                	User userSessao = (User) req.getSession().getAttribute("user");
                	if(userSessao == null || (userSessao != null && userSessao.getIdSessao() == null)) {
                		SessaoDAO sessaoDAO = new SessaoDAO(connection);
                		sessaoDAO.inserir(user.getIdUser());
                		
                		Sessao sessao = sessaoDAO.buscarUltimo();
                		user.setIdSessao(sessao.getIdSessao());
                	}
                	else {
                		user.setIdSessao(userSessao.getIdSessao());
                	}
                	
                	String usuarioQuebrado[] = user.getNome().split("\\s+");
        			String nome = usuarioQuebrado[0].trim();
        			user.setNome(nome);
                    req.getSession().setAttribute("user", user);
                    req.getRequestDispatcher("/jsp/home.jsp").forward(req, res);
                }                
            } 
            else if (relat.equals("logout")) {
            	User userSessao = (User) req.getSession().getAttribute("user");
    			if(userSessao != null) {
    				connection = ConnectionFactory.getConnection();
    				SessaoDAO sessaoDAO = new SessaoDAO(connection);
					sessaoDAO.alterar(userSessao.getIdSessao());
    			}
                req.getSession().invalidate();
                req.getRequestDispatcher("/index.jsp").forward(req, res);
            } 
            else if (relat.equals("index")) {
            	req.getRequestDispatcher("/index.jsp").forward(req, res);
            }
            else if (relat.equals("senha")) {
            	req.setAttribute("displayContainer", "true");
            	req.setAttribute("display", "none");
                req.getRequestDispatcher("/index.jsp").forward(req, res);
            }
            else if (relat.equals("recuperar")) {
            	try {
					UserDAO userDAO = new UserDAO(connection);
            		User usuario = userDAO.buscarPorUsuario(req.getParameter("cpf"));
            		
            		if(usuario != null) {
            			String novaSenha = Auxiliar.getRandomPass();
            			Pass pass = new Pass();
            			pass.setIdUser(usuario.getIdUser());
            			pass.setPass(novaSenha);
            			
            			PassDAO passDAO = new PassDAO(connection);
            			passDAO.alterar(pass);
            			
						String mensagem = Email.corpoEmailSenha(usuario.getNome(), usuario.getUsuario(), novaSenha);
						Email.enviarEmail("WaterBridge - Esqueci Minha Senha", mensagem, usuario.getEmail());

						String emailQuebrado[] = usuario.getEmail().split("@");
						String email = emailQuebrado[0].substring(0, 3) + "...@" + emailQuebrado[1];
            		
						req.setAttribute("display", "none");
						req.setAttribute("sucesso", "Um e-mail com instruções foi enviado para: "+email);
            		}
            		else {
            			req.setAttribute("display", "block");
            			req.setAttribute("aviso", "Nenhum usuário encontrado!");
            		}
				} 
            	catch (Exception e) {
					System.out.println(e);
					try {
						new LogSqlDAO(connection).inserir(1L, "", e.getMessage(), "Login", relat);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					req.setAttribute("aviso", Constantes.CONTATO_SUPORTE);
            		req.setAttribute("display", "block");
				}
            	finally {
            		req.setAttribute("displayContainer", "true");
            		req.getRequestDispatcher("/index.jsp").forward(req, res);
            	}
            }
            else if (relat.equals("sessao")) {
            	User userSessao = (User) req.getSession().getAttribute("user");
    			if(userSessao != null) {
    				connection = ConnectionFactory.getConnection();
    				SessaoDAO sessaoDAO = new SessaoDAO(connection);
					sessaoDAO.alterar(userSessao.getIdSessao());
    			}
            } 
        }
        catch (Exception e) {
        	System.out.println("erro " + e.toString());
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
