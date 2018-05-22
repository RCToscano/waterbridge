package br.com.waterbridge.bo;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.CondominioDAO;
import br.com.waterbridge.dao.EmpresaDAO;
import br.com.waterbridge.dao.RelatoriosDAO;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.Empresa;
import br.com.waterbridge.modelo.RelatorioCondominio;
import br.com.waterbridge.modelo.User;

public class RelatoriosBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String relat = "";
		Connection connection = null;
		HttpSession session = req.getSession(true);
        User user = (User) session.getValue("user");
        
        try {
        	connection = ConnectionFactory.getConnection();
        	
            if (req.getParameter("acao") != null) {
                relat = req.getParameter("acao");
            }

            if (relat.equals("medidor")) {
            	
            	EmpresaDAO empresaDAO = new EmpresaDAO(connection);
            	List<Empresa> listEmpresa = empresaDAO.listarPorUsuario(user.getIdUser());
            	
        		req.setAttribute("display", "none");
        		req.setAttribute("listEmpresa", listEmpresa);
        		req.getRequestDispatcher("/jsp/relatorios/consumoMedidor.jsp").forward(req, res);
            } 
            
            else if (relat.equals("condominio")) {
            	CondominioDAO condominioDAO = new CondominioDAO(connection);
            	List<Condominio> listCondominio = condominioDAO.listar();
            	
            	req.setAttribute("listCondominio", listCondominio);
            	req.setAttribute("display", "none");
            	req.getRequestDispatcher("/jsp/relatorios/consumoCondominio.jsp").forward(req, res);
            } 
            
            else if (relat.equals("consumoCondominio")) {
            	CondominioDAO condominioDAO = new CondominioDAO(connection);
				List<Condominio> listCondominio = condominioDAO.listar();
				
				RelatoriosDAO relatoriosDAO = new RelatoriosDAO(connection);
				List<RelatorioCondominio> lista = relatoriosDAO
						.consumoPorComunidade(Long.valueOf(req.getParameter("idCondominio")));
				
				if(!lista.isEmpty()) {
					BigDecimal total = BigDecimal.ZERO;
					for(RelatorioCondominio relatorio : lista) {
						total.add(relatorio.getConsumo());
					}
					req.setAttribute("listCondominio", listCondominio);
					req.setAttribute("lista", lista);
					req.setAttribute("totalConsumo", total);
				}
				else {
					req.setAttribute("informacao", "Nenhum resultado encontrado!");
				}
				
        		req.setAttribute("display", "none");
        		req.getRequestDispatcher("/jsp/relatorios/consumoCondominio.jsp").forward(req, res);
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
