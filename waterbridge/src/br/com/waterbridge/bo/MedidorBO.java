package br.com.waterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.MedidorDAO;
import br.com.waterbridge.modelo.Medidor;

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

            if (relat.equals("cadastro")) {
        		req.setAttribute("display", "none");
        		req.setAttribute("titulo", "Cadastro");
        		req.setAttribute("botao", "Cadastrar");
        		req.getRequestDispatcher("/jsp/medidor/medidor.jsp").forward(req, res);
            } 
            if (relat.equals("inserir")) {
            	Medidor medidor = new Medidor();
            	try {
            		medidor.setFabricante(req.getParameter("fabricante"));
            		medidor.setModelo(req.getParameter("modelo"));
            		medidor.setSerie(req.getParameter("serie"));
            		medidor.setTipo(req.getParameter("tipo"));
            		medidor.setChaveDeCripto(req.getParameter("chave"));
            		medidor.setValidBateria(Auxiliar.converteInteger(req.getParameter("bateria")));
            		medidor.setObs(req.getParameter("descricao"));
            		
            		if(req.getParameter("id") != null && !req.getParameter("id").isEmpty()) {
            			//Alteracao
            		}
            		else {
            			req.setAttribute("titulo", "Cadastro");
            			req.setAttribute("botao", "Cadastrar");

            			MedidorDAO medidorDAO = new MedidorDAO(connection);
            			medidorDAO.inserir(medidor);
            			req.setAttribute("display", "none");
	            		req.setAttribute("sucesso", "Medidor cadastrado com sucesso!");
	            		req.getRequestDispatcher("/jsp/medidor/medidor.jsp").forward(req, res);
            		}
				} 
            	catch (Exception e) {
            		System.out.println(e);
            		req.setAttribute("aviso", "Não foi possível realizar a operação, contate o suporte!");
            		req.setAttribute("display", "block");
            		req.getRequestDispatcher("/jsp/medidor/medidor.jsp").forward(req, res);
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
