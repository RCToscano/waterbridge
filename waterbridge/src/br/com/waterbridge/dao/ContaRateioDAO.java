
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.CnpTp;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.ContaRateio;

public class ContaRateioDAO {
    
    private Connection connection;

    public ContaRateioDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(ContaRateio contaRateio) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_CONTARATEIO ( " +
            //"		ID_CONTARATEIO, " +
            "		ID_CONTA, " +
            "		ID_EMPRESA, " +
            "		ID_CONDOMINIO, " +
            "		ID_MEDIDOR, " +
            "		ID_USER, " +
            "		VOLUMEINICIAL, " +
            "		VOLUMEFINAL, " +
            "		CONSUMOREAL, " +
            "		CONSUMORATEIO, " +
            "		VALORRATEIO, " +
            "		PERCRATEIO, " +
            "		OBS, " +
            "		DTINSERT " +
            ") VALUES ( " +
            "       ?,?,?,?,?,?,?,?,?,?, " +
            "       ?,?,SYSDATE() " +
            ")");
            
            //stmt.setObject(1, contaRateio.getIdContaRateio());
            stmt.setObject(1, contaRateio.getIdConta());
            stmt.setObject(2, contaRateio.getIdEmpresa());
            stmt.setObject(3, contaRateio.getIdCondominio());
            stmt.setObject(4, contaRateio.getIdMedidor());
            stmt.setObject(5, contaRateio.getIdUser());
            stmt.setObject(6, contaRateio.getVolumeInicial());
            stmt.setObject(7, contaRateio.getVolumeFinal());
            stmt.setObject(8, contaRateio.getConsumoReal());
            stmt.setObject(9, contaRateio.getConsumoRateio());
            stmt.setObject(10, contaRateio.getValorRateio());
            stmt.setObject(11, contaRateio.getPercRateio());
            stmt.setObject(12, contaRateio.getObs());
            //stmt.setObject(13, contaRateio.getDtInsert());
            
            stmt.execute();
        }
        catch(SQLException e) {
            
            throw e;
        }
        finally {
            
            if(stmt != null) {
                
                stmt.close();
            }
        }
    }
    
    public void alterar(ContaRateio contaRateio) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
            
            //LOGA REGISTRO ANTES DE ALTERAR
            logar(contaRateio.getIdContaRateio());
        
            stmt = connection.prepareStatement(
            "UPDATE TB_CONTARATEIO SET " +    
            "		ID_CONTA = ?, " +
            "		ID_EMPRESA = ?, " +
            "		ID_CONDOMINIO = ?, " +
            "		ID_MEDIDOR = ?, " +
            "		ID_USER = ?, " +
            "		VOLUMEINICIAL = ?, " +
            "		VOLUMEFINAL = ?, " +
            "		CONSUMOREAL = ?, " +
            "		CONSUMORATEIO = ?, " +
            "		VALORRATEIO = ?, " +
            "		PERCRATEIO = ?, " +
            "		OBS = ?, " +
            "		DTINSERT = SYSDATE() " +
    		"WHERE  ID_CONTARATEIO = ? ");		
          
            stmt.setObject(1, contaRateio.getIdConta());
            stmt.setObject(2, contaRateio.getIdEmpresa());
            stmt.setObject(3, contaRateio.getIdCondominio());
            stmt.setObject(4, contaRateio.getIdMedidor());
            stmt.setObject(5, contaRateio.getIdUser());
            stmt.setObject(6, contaRateio.getVolumeInicial());
            stmt.setObject(7, contaRateio.getVolumeFinal());
            stmt.setObject(8, contaRateio.getConsumoReal());
            stmt.setObject(9, contaRateio.getConsumoRateio());
            stmt.setObject(10, contaRateio.getValorRateio());
            stmt.setObject(11, contaRateio.getPercRateio());
            stmt.setObject(12, contaRateio.getObs());
            //stmt.setObject(13, contaRateio.getDtInsert());
            stmt.setObject(13, contaRateio.getIdContaRateio());

            stmt.execute();
        }
        catch(SQLException e) {
            
            throw e;
        }
        finally {
            
            if(stmt != null) {
                
                stmt.close();
            }
        }
    }    

    public void logar(Long idContaRateio) throws SQLException {

        PreparedStatement stmt = null;
        
        try {
            
            stmt = connection.prepareStatement(
            "INSERT INTO TB_CONTARATEIOLOG " +
            "SELECT * " +
            "FROM   TB_CONTARATEIO " +
            "WHERE  ID_CONTARATEIO = ? "
            );

            stmt.setLong(1, idContaRateio);
            
            stmt.executeUpdate();
        }
        catch(SQLException e) {
            
            throw e;
        }
        finally {

            if(stmt != null) {
                
                stmt.close();
            }
        }
    }
    
    public ContaRateio buscarPorId(Long idContaRateio) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        ContaRateio contaRateio = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_CONTARATEIO, " +
        	"       ID_CONTA, " +
        	"       ID_EMPRESA, " +
        	"       ID_CONDOMINIO, " +
        	"       ID_MEDIDOR, " +
        	"       ID_USER, " +
        	"	    VOLUMEINICIAL, " +
        	"       VOLUMEFINAL, " +
        	"       CONSUMOREAL, " +
        	"       CONSUMORATEIO, " +
        	"       VALORRATEIO, " +
        	"       PERCRATEIO, " +
        	"       OBS, " +
        	"       DTINSERT " +
        	"FROM   TB_CONTARATEIO " + 		
            "WHERE  ID_CONTARATEIO = ? "		            		
            );

            stmt.setLong(1, idContaRateio);

            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	contaRateio = new ContaRateio();
            	contaRateio.setIdContaRateio(rs.getLong("ID_CONTARATEIO"));
            	contaRateio.setIdConta(rs.getLong("ID_CONTA"));
            	contaRateio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	contaRateio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	contaRateio.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	contaRateio.setIdUser(rs.getLong("ID_USER"));
            	contaRateio.setVolumeInicial(rs.getDouble("VOLUMEINICIAL"));
            	contaRateio.setVolumeFinal(rs.getDouble("VOLUMEFINAL"));
            	contaRateio.setConsumoReal(rs.getDouble("CONSUMOREAL"));
            	contaRateio.setConsumoRateio(rs.getDouble("CONSUMORATEIO"));
            	contaRateio.setValorRateio(rs.getDouble("VALORRATEIO"));
            	contaRateio.setPercRateio(rs.getDouble("PERCRATEIO"));
            	contaRateio.setObs(rs.getString("OBS"));
            	contaRateio.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            }
            
            return contaRateio;
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
