
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.Conta;

public class ContaDAO {
    
    Connection connection = null;

    public ContaDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public Conta buscar(Long idConta) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Conta conta = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_CONTA, " +
        	"       ID_EMPRESA, " +
        	"       ID_CONDOMINIO, " +
        	"       ID_USER, " +
        	"	    DTLEITURAATUAL, " +
        	"       DTLEITURAANTERIOR, " +
        	"       VALOR, " +
        	"       CONSUMO, " +
        	"       OBS, " +
        	"       DTINSERT " +
        	"FROM   TB_CONTA " +	
            "WHERE  ID_CONTA = ? "
            );

            stmt.setLong(1, idConta);
            
            rs = stmt.executeQuery();

            if(rs.next()) {

            	conta = new Conta();
            	conta.setIdConta(rs.getLong("ID_CONTA"));
            	conta.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	conta.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	conta.setIdUser(rs.getLong("ID_USER"));
            	conta.setDtLeituraAtual(Auxiliar.formataDtTela(rs.getString("DTLEITURAATUAL")));
            	conta.setDtLeituraAnterior(Auxiliar.formataDtTela(rs.getString("DTLEITURAANTERIOR")));
            	conta.setValor(rs.getDouble("VALOR"));
            	conta.setConsumo(rs.getDouble("CONSUMO"));
            	conta.setObs(rs.getString("OBS"));
            	conta.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            }
            
            return conta;
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
    
    public List<Conta> listarPorCondominio(Long idCondominio) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Conta> list = new ArrayList<Conta>();
        
        try {
            
        	stmt = connection.prepareStatement(
    		"SELECT ID_CONTA, " +
        	"       ID_EMPRESA, " +
        	"       ID_CONDOMINIO, " +
        	"       ID_USER, " +
        	"	    DTLEITURAATUAL, " +
        	"       DTLEITURAANTERIOR, " +
        	"       VALOR, " +
        	"       CONSUMO, " +
        	"       OBS, " +
        	"       DTINSERT " +
        	"FROM   TB_CONTA " +	
            "WHERE  ID_CONDOMINIO = ? "
            );

        	stmt.setLong(1, idCondominio);
        	
            rs = stmt.executeQuery();

            while(rs.next()) {

            	Conta conta = new Conta();
            	conta.setIdConta(rs.getLong("ID_CONTA"));
            	conta.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	conta.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	conta.setIdUser(rs.getLong("ID_USER"));
            	conta.setDtLeituraAtual(Auxiliar.formataDtTela(rs.getString("DTLEITURAATUAL")));
            	conta.setDtLeituraAnterior(Auxiliar.formataDtTela(rs.getString("DTLEITURAANTERIOR")));
            	conta.setValor(rs.getDouble("VALOR"));
            	conta.setConsumo(rs.getDouble("CONSUMO"));
            	conta.setObs(rs.getString("OBS"));
            	conta.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	
            	list.add(conta);
            }
            
            return list;
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

    public String addDiaData(String data, Long qtdeDias) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String dt = null;
        
        try {
            
            stmt = connection.prepareStatement(
            "SELECT ADDDATE('" + data + "', INTERVAL " + qtdeDias + " DAY) DATA "
            );
            
            rs = stmt.executeQuery();

            if(rs.next()) {

            	dt = rs.getString("DATA");            	
            }
            
            return dt;
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