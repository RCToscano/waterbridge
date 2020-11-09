package br.com.waterbridge.bo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.andwaterbridge.modelo.MetaPressao;
import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.BridgeTp;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.Consumo;

class MessageBOTest {

	void test() throws SQLException {
		MessageBO messageBO = new MessageBO();
		Bridge bridge = new Bridge();
		bridge.setIdBridge(62L);
		bridge.setDeviceNum("TESTE");
		
		BridgeTp bridgeTp = new BridgeTp();
		bridgeTp.setIdBridgeTp(4L);
		bridge.setBridgeTp(bridgeTp);
		
		Consumo consumo = new Consumo();
		consumo.setPressure(10.0);
		consumo.setAlarm(0L);
		
		MetaPressao metaPressao = new MetaPressao();
		metaPressao.setPressaoMaxAlta(10.0);
		metaPressao.setPressaoMax(9.0);
		metaPressao.setPressaoMinBaixa(4.0);
		metaPressao.setPressaoMin(5.0);
		
		
		Connection connection = ConnectionFactory.getConnection();
		
		messageBO.verificarAlarm(consumo, new Condominio(), bridge, metaPressao, connection);
	}

}
