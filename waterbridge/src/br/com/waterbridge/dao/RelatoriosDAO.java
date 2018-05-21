package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.FabricMedidor;
import br.com.waterbridge.modelo.Medidor;
import br.com.waterbridge.modelo.RelatorioCondominio;

public class RelatoriosDAO {
	
	private Connection connection;

    public RelatoriosDAO(Connection connection) {
        this.connection = connection;
    }
    
    
    public List<Medidor> listarTodos() throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medidor> list = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(
            		"   SELECT TB_MEDIDOR.*, " +
            		"          TB_FABRICMEDIDOR.FABRICANTE " +
		            "     FROM TB_MEDIDOR " +
            		"LEFT JOIN TB_FABRICMEDIDOR " +
		            "       ON TB_FABRICMEDIDOR.ID_FABRICMEDIDOR = TB_MEDIDOR.ID_FABRICMEDIDOR "
		            );
            
            rs = stmt.executeQuery();

            while (rs.next()) {
            	Medidor medidor = new Medidor();
            	medidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	medidor.setIdFabricMedidor(rs.getLong("ID_FABRICMEDIDOR"));
        		medidor.setModelo(rs.getString("MODELO"));
        		medidor.setSerie(rs.getString("SERIE"));
        		medidor.setTipo(rs.getString("TIPO"));
        		medidor.setChaveDeCripto(rs.getString("CHAVEDECRIPTO"));
        		medidor.setValidBateria(rs.getInt("VALIDBATERIA"));
        		medidor.setNumeroMedidor(rs.getString("METERID"));
        		medidor.setIdBridge(rs.getLong("ID_BRIDGE"));
        		medidor.setDeviceNum(rs.getString("DEVICENUM"));
        		medidor.setMeterPosition(rs.getInt("METERPOSITION"));
        		medidor.setObs(rs.getString("OBS"));
        		medidor.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
        		medidor.setEndereco(rs.getString("ENDERECO"));
        		medidor.setNumero(Auxiliar.converteLong(rs.getString("NUMERO")));
        		medidor.setCompl(rs.getString("COMPL"));
        		medidor.setMunicipio(rs.getString("MUNICIPIO"));
        		medidor.setUf(rs.getString("UF"));
        		medidor.setCep(rs.getString("CEP"));
        		medidor.setCoordX(rs.getString("COORDX"));
        		medidor.setCoordY(rs.getString("COORDY"));
        		medidor.setSituacao(rs.getString("SITUACAO"));
        		medidor.setDtInsert(rs.getString("DTINSERT"));
        		
        		FabricMedidor fabricMedidor = new FabricMedidor();
        		fabricMedidor.setIdFabricMedidor(rs.getLong("ID_FABRICMEDIDOR"));
        		fabricMedidor.setFabricante(rs.getString("FABRICANTE"));
        		medidor.setFabricMedidor(fabricMedidor);
        		
                list.add(medidor);
            }
            return list;
        } 
        finally {
            if (stmt != null)
                stmt.close();
            if (rs != null)
                rs.close();
        }
    }
    
    public List<RelatorioCondominio> consumoPorComunidade(Long idCondominio) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelatorioCondominio> list = new ArrayList<>();
        try {
            
            stmt = connection.prepareStatement(
        	"	SELECT SUM(TB_CONSUMO.VOLUME) AS VOLUME, " + 
            "          TB_USER.NOME " +
            "     FROM TB_CONDOMINIO " + 
            "LEFT JOIN TB_USERCONDOMINIO " + 
        	"       ON TB_USERCONDOMINIO.ID_CONDOMINIO = TB_CONDOMINIO.ID_CONDOMINIO " + 
            "LEFT JOIN TB_USER " +
        	"       ON TB_USER.ID_USER = TB_USERCONDOMINIO.ID_USER " + 
        	"LEFT JOIN TB_MEDIDOR " + 
        	"       ON TB_MEDIDOR.ID_CONDOMINIO = TB_CONDOMINIO.ID_CONDOMINIO " + 
        	"LEFT JOIN TB_BRIDGE " + 
        	"       ON TB_BRIDGE.ID_BRIDGE = TB_MEDIDOR.ID_BRIDGE " + 
        	"LEFT JOIN TB_CONSUMO " + 
        	"       ON TB_CONSUMO.DEVICE = TB_MEDIDOR.DEVICENUM " + 
            "    WHERE TB_CONDOMINIO.ID_CONDOMINIO = ? " + 
        	" GROUP BY TB_USER.ID_USER"
            );
            stmt.setObject(1, idCondominio);
            rs = stmt.executeQuery();

            while(rs.next()) {
            	if(rs.getBigDecimal("VOLUME") != null) {
	            	RelatorioCondominio condominio = new RelatorioCondominio();
	            	condominio.setIdCondominio(idCondominio);
	            	condominio.setCondominio(rs.getString("NOME"));
	            	condominio.setConsumo(rs.getBigDecimal("VOLUME"));
	                list.add(condominio);
            	}
            }
            return list;
        }
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }

}
