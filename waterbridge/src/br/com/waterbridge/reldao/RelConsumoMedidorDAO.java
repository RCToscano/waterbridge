
package br.com.waterbridge.reldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.relmodelo.RelConsumoMedidor;

public class RelConsumoMedidorDAO {
    
    private Connection connection;

    public RelConsumoMedidorDAO(Connection connection) {
        
        this.connection = connection;
    }

    public List<RelConsumoMedidor> listar(String sql) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelConsumoMedidor> listRelConsumoMedidor = new ArrayList<RelConsumoMedidor>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_EMPRESA, " +
            "		ID_CONDOMINIO, " +
            "		ID_BRIDGE, " +
            "		ID_BRIDGETP, " +
    		"       ID_CONSUMO, " +
    		"		ID_USER, " +
    		"		ID_MEDIDOR, " +
    		"		EMPRESA, " +
    		"		CONDOMINIO, " +
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
    		"		DTINSERT, " +
    		"		METERID, " +
    		"		ENDERECO, " +
    		"		NUMERO, " +
    		"		COMPL, " +
    		"		MUNICIPIO, " +
    		"		UF, " +
    		"		CEP, " +
    		"		COORDX, " +
    		"		COORDY, " +
    		"		CONSUMO " +
        	"FROM   VW_CONSUMOMEDIDOR " +		
            sql
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	RelConsumoMedidor relConsumoMedidor = new RelConsumoMedidor();
            	relConsumoMedidor.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relConsumoMedidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	relConsumoMedidor.setIdBridge(rs.getLong("ID_BRIDGE"));
            	relConsumoMedidor.setIdBridgeTp(rs.getLong("ID_BRIDGETP"));
            	relConsumoMedidor.setIdConsumo(rs.getLong("ID_CONSUMO"));
            	relConsumoMedidor.setIdUser(rs.getLong("ID_USER"));
            	relConsumoMedidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	relConsumoMedidor.setDevice(rs.getString("DEVICE"));
            	relConsumoMedidor.setEmpresa(rs.getString("EMPRESA"));
            	relConsumoMedidor.setCondominio(rs.getString("CONDOMINIO"));
            	relConsumoMedidor.setData(rs.getString("DATA"));
            	relConsumoMedidor.setVersion(rs.getString("VERSION"));
            	relConsumoMedidor.setMeterPosition(rs.getLong("METERPOSITION"));
            	relConsumoMedidor.setVolume(rs.getDouble("VOLUME"));
            	relConsumoMedidor.setPressure(rs.getDouble("PRESSURE"));
            	relConsumoMedidor.setFlow(rs.getLong("FLOW"));
            	relConsumoMedidor.setTemperature(rs.getLong("TEMPERATURE"));
            	relConsumoMedidor.setBattery(rs.getDouble("BATTERY"));
            	relConsumoMedidor.setAlarm(rs.getLong("ALARM"));
            	relConsumoMedidor.setAlarmDesc(rs.getString("ALARMDESC"));
            	relConsumoMedidor.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	relConsumoMedidor.setNumeroMedidor(rs.getString("METERID"));
            	relConsumoMedidor.setEndereco(rs.getString("ENDERECO"));
            	relConsumoMedidor.setNumero(rs.getLong("NUMERO"));
            	relConsumoMedidor.setCompl(rs.getString("COMPL"));
            	relConsumoMedidor.setMunicipio(rs.getString("MUNICIPIO"));
            	relConsumoMedidor.setUf(rs.getString("UF"));
            	relConsumoMedidor.setCep(rs.getString("CEP"));
            	relConsumoMedidor.setCoordX(rs.getString("COORDX"));
            	relConsumoMedidor.setCoordY(rs.getString("COORDY"));
            	relConsumoMedidor.setConsumo(rs.getDouble("CONSUMO"));
            	
            	listRelConsumoMedidor.add(relConsumoMedidor);
            }
            
            return listRelConsumoMedidor;
        }
        catch(SQLException e) {
            
            throw e;
        }
        finally {

            if(stmt != null) {
                
                stmt.close();
            }
            if(rs != null) {
                
                rs.close();
            }
        }
    }

}


