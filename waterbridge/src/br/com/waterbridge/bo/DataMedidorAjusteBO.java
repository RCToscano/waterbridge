package br.com.waterbridge.bo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

public class DataMedidorAjusteBO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
		try {
			
			StringBuilder sb = null;
			sb = new StringBuilder();
	    	
	    	String linha = null;
	    	while((linha = req.getReader().readLine()) != null) {	    
	    		sb.append(linha);
	    	}
			
			Calendar dataAtual = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
			Long dataAtualMiliSegundos = dataAtual.getTimeInMillis();
		
			JSONObject obj = new JSONObject(sb.toString());
	    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = sdf.parse(obj.getString("time"));
			Calendar dataDispositivo = Calendar.getInstance();
			dataDispositivo.setTime(date);
			Long dataDispositivoMiliSegundos = dataDispositivo.getTimeInMillis();
						
			Long diferenca = dataAtualMiliSegundos - dataDispositivoMiliSegundos;
			diferenca = diferenca / 1000 / 60;
	
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new Gson().toJson(diferenca));		
		} 
		catch (Exception e) {			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write("Falha " + e.toString());
		}
    }
}

