package br.com.waterbridge.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GraficoConsumoBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			
            if (req.getParameter("acao").equals("1")) {
                
            	List<String> listConsumo = new ArrayList<String>();
            	for(int i = 0; i < 28; i++) {
            		listConsumo.add("" + (i + 3));
            	}
            	
             	req.setAttribute("listConsumo", listConsumo);
            	req.getRequestDispatcher("/jsp/grafico/graficoconsumomes.jsp").forward(req, res);
            }  
        }
        catch (Exception e) {
            req.setAttribute("erro", e.toString());
            req.getRequestDispatcher("/jsp/erro.jsp").forward(req, res);
        }
    }
}
