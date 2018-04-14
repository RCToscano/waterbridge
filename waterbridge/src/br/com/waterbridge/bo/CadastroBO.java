package br.com.waterbridge.bo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastroBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String relat = "";
        try {
            if (req.getParameter("acao") != null) {
                relat = req.getParameter("acao");
            }

            if (relat.equals("home")) {
                req.getRequestDispatcher("/jsp/home.jsp").forward(req, res);
            } 
            else if (relat.equals("representante")) {
            	req.getRequestDispatcher("/jsp/cadastro/representante.jsp").forward(req, res);
            } 
            else if (relat.equals("bridge")) {
            	req.getRequestDispatcher("/jsp/cadastro/bridge.jsp").forward(req, res);
            } 
            else if (relat.equals("condominio")) {
            	req.getRequestDispatcher("/jsp/cadastro/condominio.jsp").forward(req, res);
            } 
            else if (relat.equals("consumidor")) {
            	req.getRequestDispatcher("/jsp/cadastro/consumidor.jsp").forward(req, res);
            } 
            else if (relat.equals("medidor")) {
            	req.getRequestDispatcher("/jsp/cadastro/medidor.jsp").forward(req, res);
            } 
        }
        catch (Exception e) {
            req.setAttribute("erro", e.toString());
            req.getRequestDispatcher("/jsp/erro.jsp").forward(req, res);
        }
    }
}
