
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
            "          VW_MAPACONSUMOPRESSAO.PRESSAOMIN, " +
            "          VW_MAPACONSUMOPRESSAO.PRESSAOMAX, " +
            "          VW_MAPACONSUMOPRESSAO.PRESSAOMINBAIXA, " +
            "          VW_MAPACONSUMOPRESSAO.PRESSAOMAXALTA, " +
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
            	relMapaConsumoPressao.setPressaoMinBaixa(rs.getDouble("PRESSAOMINBAIXA"));
            	relMapaConsumoPressao.setPressaoMin(rs.getDouble("PRESSAOMIN"));
            	relMapaConsumoPressao.setPressaoMax(rs.getDouble("PRESSAOMAX"));
            	relMapaConsumoPressao.setPressaoMaxAlta(rs.getDouble("PRESSAOMAXALTA"));            	
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
    
    public RelMapaConsumoPressao buscar(Long idEmpresa, Long idCondominio, Long idBridge) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        RelMapaConsumoPressao relMapaConsumoPressao = null;
        
        try {
        	
            stmt = connection.prepareStatement(
		    "SELECT    TB_CONSUMO.ID_CONSUMO AS ID_CONSUMO, " +
		    "          TB_EMPRESA.ID_EMPRESA AS ID_EMPRESA, " +
		    "          TB_EMPRESA.NOME AS EMPRESA, " +
		    "          TB_CONDOMINIO.ID_CONDOMINIO AS ID_CONDOMINIO, " +
		    "          TB_CONDOMINIO.NOME AS CONDOMINIO, " +
		    "          TB_CONDOMINIO.ENDERECO AS ENDERECO, " +
		    "          TB_CONDOMINIO.NUMERO AS NUMERO, " +
		    "          TB_CONDOMINIO.COMPL AS COMPL, " +
		    "          TB_CONDOMINIO.COORDX AS COORDX, " +
		    "          TB_CONDOMINIO.COORDY AS COORDY, " +
		    "          TB_BRIDGE.ID_BRIDGETP AS ID_BRIDGETP, " +
		    "          TB_BRIDGE.SITUACAO AS SITUACAO_BRIDGE, " +
		    "          NULL AS ID_MEDIDOR, " +
		    "          NULL AS METERID, " +
		    "          TB_CONSUMO.DEVICE AS DEVICE, " +
		    "          TB_CONSUMO.VOLUME AS VOLUME, " +
		    "          TB_CONSUMO.PRESSURE AS PRESSURE, " +
		    "          0 AS CONSUMO, " +
		    "          TB_CONSUMO.TEMPERATURE AS TEMPERATURE, " +
		    "          TB_CONSUMO.BATTERY AS BATTERY, " +
		    "          TB_ALARM.ID_ALARM AS ID_ALARM, " +
		    "          TB_ALARM.ALARM AS ALARM, " +
		    "          TB_CONSUMO.DTINSERT AS DTINSERT, " +
		    "          TB_METAPRESSAO.PRESSAOMIN AS PRESSAOMIN, " +
		    "          TB_METAPRESSAO.PRESSAOMAX AS PRESSAOMAX, " +
		    "          TB_METAPRESSAO.PRESSAOMINBAIXA AS PRESSAOMINBAIXA, " +
		    "          TB_METAPRESSAO.PRESSAOMAXALTA AS PRESSAOMAXALTA " +
		    "FROM      TB_CONSUMO " +
		    "LEFT JOIN TB_BRIDGE " +
		    "ON        TB_CONSUMO.DEVICE = TB_BRIDGE.DEVICENUM " +
		    "LEFT JOIN TB_CONDOMINIO " +
		    "ON        TB_BRIDGE.ID_CONDOMINIO = TB_CONDOMINIO.ID_CONDOMINIO " +
		    "LEFT JOIN TB_EMPRESA " +
		    "ON        TB_CONDOMINIO.ID_EMPRESA = TB_EMPRESA.ID_EMPRESA " +
		    "LEFT JOIN TB_ALARM " +
		    "ON        TB_CONSUMO.ALARM = TB_ALARM.ID_ALARM " +
		    "LEFT JOIN TB_METAPRESSAO " +
		    "ON        TB_CONSUMO.ID_EMPRESA = TB_METAPRESSAO.ID_EMPRESA " +
		    "AND       TB_CONSUMO.ID_CONDOMINIO = TB_METAPRESSAO.ID_CONDOMINIO " +
		    "AND       TB_CONSUMO.ID_BRIDGE = TB_METAPRESSAO.ID_BRIDGE " +
		    "WHERE     TB_CONSUMO.ID_CONSUMO = ( " +
		   	"  SELECT MAX(ID_CONSUMO) ID_CONSUMO " +
		  	"  FROM   TB_CONSUMO " +
		  	"  WHERE  ID_EMPRESA = " + idEmpresa + " " +
		  	"  AND    ID_CONDOMINIO = " + idCondominio + " " +
		  	"  AND    ID_BRIDGE = " + idBridge + " " +
		    ") " 
            );
                        
            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	relMapaConsumoPressao = new RelMapaConsumoPressao();
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
            	relMapaConsumoPressao.setPressaoMinBaixa(rs.getDouble("PRESSAOMINBAIXA"));
            	relMapaConsumoPressao.setPressaoMin(rs.getDouble("PRESSAOMIN"));
            	relMapaConsumoPressao.setPressaoMax(rs.getDouble("PRESSAOMAX"));
            	relMapaConsumoPressao.setPressaoMaxAlta(rs.getDouble("PRESSAOMAXALTA"));            	
            	relMapaConsumoPressao.setAlarm(rs.getString("ALARM"));
            	
            	relMapaConsumoPressao.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));            	
            }
            
            return relMapaConsumoPressao;
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
    
    public List<RelMapaConsumoPressao> listarPressureBridgeEmpresa(Long idEmpresa) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelMapaConsumoPressao> listRelMapaConsumoPressao = new ArrayList<RelMapaConsumoPressao>();
        
        try {
        	
            stmt = connection.prepareStatement(
		    "SELECT    TB_EMPRESA.ID_EMPRESA, " +
            "          TB_CONDOMINIO.ID_CONDOMINIO, " +
            "          TB_BRIDGE.ID_BRIDGE " +
            "FROM      TB_EMPRESA " +
            "LEFT JOIN TB_CONDOMINIO " +
            "ON        TB_EMPRESA.ID_EMPRESA = TB_CONDOMINIO.ID_EMPRESA " +
            "LEFT JOIN TB_BRIDGE " +
            "ON        TB_CONDOMINIO.ID_CONDOMINIO = TB_BRIDGE.ID_CONDOMINIO " +
            "WHERE     TB_EMPRESA.ID_EMPRESA = " + idEmpresa + " " +
            "AND       TB_EMPRESA.SITUACAO = 'A' " +
            "AND       TB_CONDOMINIO.SITUACAO = 'A' " +
            "AND       TB_BRIDGE.SITUACAO = 'A' " +
            "AND       ( TB_BRIDGE.ID_BRIDGETP = 2 OR TB_BRIDGE.ID_BRIDGETP = 4 ) "            
            );
                        
            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	RelMapaConsumoPressao relMapaConsumoPressao = new RelMapaConsumoPressao();
            	relMapaConsumoPressao.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relMapaConsumoPressao.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	relMapaConsumoPressao.setIdBridge(rs.getLong("ID_BRIDGE"));            	
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