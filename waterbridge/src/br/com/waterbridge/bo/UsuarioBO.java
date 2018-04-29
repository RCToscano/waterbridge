package br.com.waterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.PassDAO;
import br.com.waterbridge.dao.PerfilDAO;
import br.com.waterbridge.dao.UserDAO;
import br.com.waterbridge.enuns.SexoEnum;
import br.com.waterbridge.modelo.Pass;
import br.com.waterbridge.modelo.Perfil;
import br.com.waterbridge.modelo.User;

public class UsuarioBO extends HttpServlet {

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
            if (req.getParameter("acao") != null) {
                relat = req.getParameter("acao");
            }

            if (relat.equals("cadUsuario")) {
            	connection = ConnectionFactory.getConnection();
            	PerfilDAO perfilDAO = new PerfilDAO(connection);
            	List<Perfil> listaPerfil = perfilDAO.listarTodos();
        		List<SexoEnum> listaSexo = SexoEnum.listCodigos();
        		req.setAttribute("listaSexo", listaSexo);
        		req.setAttribute("listaPerfil", listaPerfil);
        		req.setAttribute("display", "none");
        		req.getRequestDispatcher("/jsp/usuario/usuario.jsp").forward(req, res);
            } 
            else if (relat.equals("inserir")) {
            	SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
        		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        		connection = ConnectionFactory.getConnection();
        		User user = new User();
            	try {
            		Perfil perfil = new Perfil();
            		perfil.setIdPerfil(Auxiliar.converteLong(req.getParameter("perfil")));
            		user.setPerfil(perfil);
            		
            		user.setCpf(req.getParameter("cpf"));
            		user.setNome(req.getParameter("name").trim().toUpperCase());
            		user.setDtNasc(formatoBanco.format(formatoData.parse(req.getParameter("dtNascimento"))));
            		user.setSexo(req.getParameter("sexo"));
            		user.setTelFixo(req.getParameter("telefoneFixo"));
            		user.setTelCel(req.getParameter("telefoneCelular"));
            		user.setEmail(req.getParameter("email"));
            		user.setEndereco(req.getParameter("endereco"));
            		user.setNumero(Auxiliar.converteLong(req.getParameter("numero")));
            		user.setCompl(req.getParameter("compl"));
            		user.setMunicipio(req.getParameter("municipio"));
            		user.setUf(req.getParameter("estado"));
            		user.setCep(req.getParameter("cep"));
            		
            		UserDAO userDAO = new UserDAO(connection);
            		userDAO.inserir(user);
            		
            		User user2 = userDAO.buscarUltimo();
            		user.setIdUser(user2.getIdUser());
            		
            		Pass pass = new Pass();
            		pass.setPass("waterBridge"+new Random().nextInt(50));
            		pass.setIdUser(user.getIdUser());
            		
            		PassDAO passDAO = new PassDAO(connection);
            		passDAO.inserir(pass);
            		
            		
            		PerfilDAO perfilDAO = new PerfilDAO(connection);
                	List<Perfil> listaPerfil = perfilDAO.listarTodos();
            		List<SexoEnum> listaSexo = SexoEnum.listCodigos();
            		req.setAttribute("listaSexo", listaSexo);
            		req.setAttribute("listaPerfil", listaPerfil);
            		req.setAttribute("sucesso", "Usuário "+user.getNome()+" cadastrado com sucesso!");
            		req.setAttribute("display", "none");
            		req.getRequestDispatcher("/jsp/usuario/usuario.jsp").forward(req, res);
				} 
            	catch (Exception e) {
            		System.out.println(e);
            		req.setAttribute("aviso", "Não foi possível realizar a operação, contate o suporte!");
            		req.setAttribute("display", "block");
            		req.getRequestDispatcher("/jsp/usuario/usuario.jsp").forward(req, res);
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
