
package br.com.waterbridge.reldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.relmodelo.RelPressao;

public class RelPressaoDAO {
    
    private Connection connection;

    public RelPressaoDAO(Connection connection) {
        
        this.connection = connection;
    }

    public List<RelPressao> listar(String sql) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelPressao> listRelPressao = new ArrayList<RelPressao>();
        try {
            stmt = connection.prepareStatement(
    		"SELECT ID_EMPRESA, " +
            "		ID_CONDOMINIO, " +
            "		ID_BRIDGE, " +
    		"       ID_CONSUMO, " +
    		"		ID_USER, " +
    		"		DEVICE, " +
    		"		DATA, " +
    		"		VERSION, " +
    		"		METERPOSITION, " +
    		"		VOLUME, " +
    		"		PRESSURE, " +
    		"		FLOW, " +
    		"		TEMPERATURE, " +
    		"		BATTERY, " +
    		"		ALARM, " +
    		"		ALARMDESC, " +
    		"		DTINSERT " +
        	"FROM   VW_RELATORIOPRESSAO " +		
            sql
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	RelPressao relPressao = new RelPressao();
            	relPressao.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relPressao.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	relPressao.setIdBridge(rs.getLong("ID_BRIDGE"));
            	relPressao.setIdConsumo(rs.getLong("ID_CONSUMO"));
            	relPressao.setIdUser(rs.getLong("ID_USER"));
            	relPressao.setDevice(rs.getString("DEVICE"));
            	relPressao.setData(rs.getString("DATA"));
            	relPressao.setVersion(rs.getString("VERSION"));
            	relPressao.setMeterPosition(rs.getLong("METERPOSITION"));
            	relPressao.setVolume(rs.getDouble("VOLUME"));
            	relPressao.setPressure(rs.getDouble("PRESSURE"));
            	relPressao.setFlow(rs.getLong("FLOW"));
            	relPressao.setTemperature(rs.getLong("TEMPERATURE"));
            	relPressao.setBattery(rs.getDouble("BATTERY"));
            	relPressao.setAlarm(rs.getLong("ALARM"));
            	relPressao.setAlarmDesc(rs.getString("ALARMDESC"));
            	relPressao.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	listRelPressao.add(relPressao);
            }
            
            return listRelPressao;
        }
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }

}


