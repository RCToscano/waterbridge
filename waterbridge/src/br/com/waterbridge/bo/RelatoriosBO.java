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

import com.google.gson.Gson;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.auxiliar.Constantes;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.BridgeDAO;
import br.com.waterbridge.dao.CondominioDAO;
import br.com.waterbridge.dao.FabricMedidorDAO;
import br.com.waterbridge.dao.MedidorDAO;
import br.com.waterbridge.dao.RelatoriosDAO;
import br.com.waterbridge.dao.SituacaoDAO;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.FabricMedidor;
import br.com.waterbridge.modelo.Medidor;
import br.com.waterbridge.modelo.RelatorioCondominio;
import br.com.waterbridge.modelo.Situacao;
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
        try {
        	connection = ConnectionFactory.getConnection();
        	
            if (req.getParameter("acao") != null) {
                relat = req.getParameter("acao");
            }

            if (relat.equals("medidor")) {
        		req.setAttribute("display", "none");
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
