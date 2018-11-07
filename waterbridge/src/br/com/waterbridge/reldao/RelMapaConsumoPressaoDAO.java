
package br.com.waterbridge.reldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.relmodelo.RelMapaConsumoPressao;

public class RelMapaConsumoPressaoDAO {
    
    private Connection connection;

    public RelMapaConsumoPressaoDAO(Connection connection) {
        
        this.connection = connection;
    }

    public List<RelMapaConsumoPressao> listar(String sql) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelMapaConsumoPressao> listRelMapaConsumoPressao = new ArrayList<RelMapaConsumoPressao>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT    VW_MAPACONSUMOPRESSAO.ID_CONSUMO, " +
            "          VW_MAPACONSUMOPRESSAO.ID_EMPRESA, " +
            "          VW_MAPACONSUMOPRESSAO.EMPRESA, " +
            "          VW_MAPACONSUMOPRESSAO.ID_CONDOMINIO, " +
            "          VW_MAPACONSUMOPRESSAO.CONDOMINIO, " +
            "          VW_MAPACONSUMOPRESSAO.ENDERECO, " +
            "          VW_MAPACONSUMOPRESSAO.NUMERO, " +
            "          VW_MAPACONSUMOPRESSAO.COMPL, " +
            "          VW_MAPACONSUMOPRESSAO.COORDX, " +
            "          VW_MAPACONSUMOPRESSAO.COORDY, " +
            "          VW_MAPACONSUMOPRESSAO.ID_BRIDGETP, " +
            "          VW_MAPACONSUMOPRESSAO.ID_MEDIDOR, " +
            "          VW_MAPACONSUMOPRESSAO.METERID, " +
            "          VW_MAPACONSUMOPRESSAO.DEVICE, " +
            "          VW_MAPACONSUMOPRESSAO.VOLUME, " +
            "          VW_MAPACONSUMOPRESSAO.PRESSURE, " +
            "          VW_MAPACONSUMOPRESSAO.CONSUMO, " +
            "          VW_MAPACONSUMOPRESSAO.TEMPERATURE, " +
            "          VW_MAPACONSUMOPRESSAO.BATTERY, " +
            "          VW_MAPACONSUMOPRESSAO.ID_ALARM, " +
            "          VW_MAPACONSUMOPRESSAO.ALARM, " +
            "          VW_MAPACONSUMOPRESSAO.DTINSERT " +
            "FROM      VW_MAPACONSUMOPRESSAO " +            	
            sql
            );
            
            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	RelMapaConsumoPressao relMapaConsumoPressao = new RelMapaConsumoPressao();
            	relMapaConsumoPressao.setIdConsumo(rs.getLong("ID_CONSUMO"));
            	relMapaConsumoPressao.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relMapaConsumoPressao.setEmpresa(rs.getString("EMPRESA"));
            	relMapaConsumoPressao.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	relMapaConsumoPressao.setCondominio(rs.getString("CONDOMINIO"));
            	relMapaConsumoPressao.setEndereco(rs.getString("ENDERECO"));
            	relMapaConsumoPressao.setNumero(rs.getLong("NUMERO"));
            	relMapaConsumoPressao.setCompl(rs.getString("COMPL"));
            	relMapaConsumoPressao.setCoordX(rs.getString("COORDX"));
            	relMapaConsumoPressao.setCoordY(rs.getString("COORDY"));
            	relMapaConsumoPressao.setIdBridgeTp(rs.getLong("ID_BRIDGETP"));            	
            	relMapaConsumoPressao.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	relMapaConsumoPressao.setNumeroMedidor(rs.getString("METERID"));
            	relMapaConsumoPressao.setDevice(rs.getString("DEVICE"));
            	relMapaConsumoPressao.setVolume(rs.getDouble("VOLUME"));
            	relMapaConsumoPressao.setPressure(rs.getDouble("PRESSURE"));
            	relMapaConsumoPressao.setConsumo(rs.getDouble("CONSUMO"));
            	relMapaConsumoPressao.setTemperature(rs.getDouble("TEMPERATURE"));
            	relMapaConsumoPressao.setBattery(rs.getDouble("BATTERY"));
            	relMapaConsumoPressao.setIdAlarm(rs.getLong("ID_ALARM"));
            	relMapaConsumoPressao.setAlarm(rs.getString("ALARM"));
            	relMapaConsumoPressao.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	
            	listRelMapaConsumoPressao.add(relMapaConsumoPressao);
            }
            
            return listRelMapaConsumoPressao;
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