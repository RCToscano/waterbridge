package br.com.waterbridge.bo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

            //Cadastro
            if (relat.equals("cadastro")) {
        		req.setAttribute("display", "none");
        		req.setAttribute("titulo", "Cadastro");
        		req.setAttribute("botao", "Cadastrar");
        		req.getRequestDispatcher("/jsp/medidor/medidor.jsp").forward(req, res);
            } 
            
            //Cadastro e Alteracao
            else if (relat.equals("inserir")) {
            	Medidor medidor = new Medidor();
            	try {
            		medidor.setFabricante(req.getParameter("fabricante"));
            		medidor.setModelo(req.getParameter("modelo"));
            		medidor.setSerie(req.getParameter("serie"));
            		medidor.setTipo(req.getParameter("tipo"));
            		medidor.setChaveDeCripto(req.getParameter("chave"));
            		medidor.setValidBateria(Auxiliar.converteInteger(req.getParameter("bateria")));
            		medidor.setObs(req.getParameter("descricao"));
            		
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

            			medidorDAO.inserir(medidor);
            			
            			req.setAttribute("display", "none");
	            		req.setAttribute("sucesso", "Medidor cadastrado com sucesso!");
            		}

            		req.getRequestDispatcher("/jsp/medidor/medidor.jsp").forward(req, res);
				} 
            	catch (Exception e) {
            		System.out.println(e);
            		req.setAttribute("aviso", "Não foi possível realizar a operação, contate o suporte!");
            		req.setAttribute("display", "block");
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
            		
    	            	req.setAttribute("listaMedidor", listaMedidor);
            			req.setAttribute("display", "none");
            			req.getRequestDispatcher("/jsp/medidor/lista.jsp").forward(req, res);
            		}
            		else {
            			MedidorDAO medidorDAO = new MedidorDAO(connection);
            			Medidor medidor = medidorDAO.buscarPorId(req.getParameter("medidor"));
            			
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
	            	req.setAttribute("aviso", "Não foi possível realizar a operação, contate o suporte!");
            		req.setAttribute("display", "block");
	            	req.getRequestDispatcher("/jsp/medidor/consulta.jsp").forward(req, res);
				}
            } 
            
            //Detalhe do Medidor
            else if (relat.equals("detalhe")) {
            	MedidorDAO medidorDAO = new MedidorDAO(connection);
    			Medidor medidor = medidorDAO.buscarPorId(req.getParameter("id"));
    			
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
