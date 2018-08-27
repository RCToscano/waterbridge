package br.com.waterbridge.schedulers;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.waterbridge.auxiliar.Constantes;
import br.com.waterbridge.auxiliar.Email;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.BridgeDAO;
import br.com.waterbridge.dao.LogSqlDAO;
import br.com.waterbridge.modelo.Bridge;

public class ValidadeToken implements Runnable {

    @Override
    public void run() {
        System.out.println("rodou");
        
        Connection connection = null;
        try {
			
        	connection = ConnectionFactory.getConnection();
        	BridgeDAO bridgeDAO = new BridgeDAO(connection);
        	List<Bridge> lista = bridgeDAO.listarTodos();
        	List<Bridge> listaFinal = new ArrayList<>();
        	
        	Calendar dataFutura = Calendar.getInstance();
        	dataFutura.add(Calendar.DAY_OF_MONTH, 30);
        	dataFutura.set(Calendar.HOUR, 0);
        	dataFutura.set(Calendar.HOUR_OF_DAY, 0);
        	dataFutura.set(Calendar.MINUTE, 0);
        	dataFutura.set(Calendar.SECOND, 0);
        	dataFutura.set(Calendar.MILLISECOND, 0);
        	
        	for (Bridge bridge : lista) {
        		SimpleDateFormat formatoBanco = new SimpleDateFormat("dd/MM/yyyy");
        		Date dataValidade = formatoBanco.parse(bridge.getValidadeToken());
        		
				if(dataValidade.equals(dataFutura.getTime())) {
					listaFinal.add(bridge);
				}
			}
        	
        	if(!listaFinal.isEmpty()) {
        		String mensagem = Email.corpoEmailToken(listaFinal);
        		Email.enviarEmail("WaterBridge - Validade Token", mensagem, Constantes.EMAIL_DESOLTEC);
        	}
        	
		} 
        catch (Exception e) {
        	System.out.println(e);
        	try {
				new LogSqlDAO(connection).inserir(1l, "", e.getMessage(), "ValidadeToken", "automatico");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
        finally {
			if(connection != null) {
				try {connection.close();} catch (SQLException e) {}
			}
		}
    }

}
