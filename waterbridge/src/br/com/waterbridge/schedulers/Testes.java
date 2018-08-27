package br.com.waterbridge.schedulers;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.waterbridge.auxiliar.Email;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.dao.AlarmDAO;
import br.com.waterbridge.dao.EmailAlarmDAO;
import br.com.waterbridge.dao.LogSqlDAO;
import br.com.waterbridge.modelo.Alarm;
import br.com.waterbridge.modelo.Consumo;
import br.com.waterbridge.modelo.EmailAlarm;

public class Testes implements Runnable {
	
	 @Override
	 public void run() {
		Connection connection = null;
		try {
			connection = ConnectionFactory.getConnection();
			Consumo consumo = new Consumo();
			consumo.setAlarm(1l);
			consumo.setDevice("4055F7");
			verificarAlarm(consumo, connection);
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			if(connection != null) {
				try {connection.close();} catch (SQLException ex) {}
			}
		}
	}
	
	public void verificarAlarm(Consumo consumo, Connection connection) {
		try {
			if(consumo.getAlarm() > 0) {
				
				EmailAlarmDAO emailAlarmDAO = new EmailAlarmDAO(connection);
				EmailAlarm emailAlarm = emailAlarmDAO.buscarPorAlarmDevice(consumo.getAlarm(), consumo.getDevice());
				if(emailAlarm == null) {
					emailAlarm = new EmailAlarm();
					emailAlarm.setDevice(consumo.getDevice());
					emailAlarm.setIdAlarm(consumo.getAlarm());
					
					enviarEmailAlarm(emailAlarm, connection);

					emailAlarmDAO.inserir(emailAlarm);
				}
			}
		} 
		catch (Exception e) {
			System.out.println(e);
			try {
				new LogSqlDAO(connection).inserir(1l, "", e.getMessage(), "MessageBO", "");
			} catch (SQLException e1) {
				System.out.println(e1);
			}
		}
	}
	
	public void enviarEmailAlarm(EmailAlarm emailAlarm, Connection connection) throws Exception {
		AlarmDAO alarmDAO = new AlarmDAO(connection);
		Alarm alarm = alarmDAO.buscarPorId(emailAlarm.getIdAlarm());
		if(alarm != null) {
			String mensagem = Email.corpoEmailAlarme(emailAlarm.getDevice(), emailAlarm.getIdAlarm(),
					alarm.getAlarm() + " - " + alarm.getDescricao());
//        		Email.enviarEmail("WaterBridge - Alarme Device "+emailAlarm.getDevice(), mensagem, Constantes.EMAIL_DESOLTEC);
    		Email.enviarEmail("WaterBridge - Alarme Device "+emailAlarm.getDevice(), mensagem, "rafael.ctoscano@gmail.com");
		}
		else {
			new LogSqlDAO(connection).inserir(1l, "", "Alarm nao encontrado na tabela", "MessageBO", "");
		}
	}

}
