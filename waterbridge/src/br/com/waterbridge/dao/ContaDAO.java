
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
    
    public void inserir(Conta conta) throws Exception {
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	stmt = connection.prepareStatement(
        			"INSERT INTO TB_CONTA ( " +
        			"ID_EMPRESA, " +
        			"ID_CONDOMINIO, " +
        			"ID_USER, " +
        			"DTLEITURAATUAL, " +
        			"DTLEITURAANTERIOR, " +
        			"VALOR, " +
        			"CONSUMO, " +
        			"OBS, " +
        			"DTINSERT " +
        			")" +
					" VALUES ( " +
            		"?, ?, ?, ?, ?, ?, ?, ?, sysdate() ) " 
    				);
        			
            		
            stmt.setObject(1, conta.getIdEmpresa());
            stmt.setObject(2, conta.getIdCondominio());
            stmt.setObject(3, conta.getIdUser());
            stmt.setObject(4, conta.getDtLeituraAnterior());
            stmt.setObject(5, conta.getDtLeituraAtual());
            stmt.setObject(6, conta.getValor());
            stmt.setObject(7, conta.getConsumo());
            stmt.setObject(8, conta.getObs());
            stmt.executeUpdate();
		} 
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }
    
    public void alterar(Conta conta) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		stmt = connection.prepareStatement(
    				"UPDATE TB_CONTA SET " +
					"ID_EMPRESA = ?, " +
					"ID_CONDOMINIO = ?, " +
					"ID_USER = ?, " +
					"DTLEITURAATUAL = ?, " +
					"DTLEITURAANTERIOR = ?, " +
					"VALOR = ?, " +
					"CONSUMO = ?, " +
					"OBS = ?, " +
					"DTINSERT = sysdate() " +
					"WHERE ID_CONTA = ? "
    				);
    		
    		
    		stmt.setObject(1, conta.getIdEmpresa());
    		stmt.setObject(2, conta.getIdCondominio());
    		stmt.setObject(3, conta.getIdUser());
    		stmt.setObject(4, conta.getDtLeituraAnterior());
    		stmt.setObject(5, conta.getDtLeituraAtual());
    		stmt.setObject(6, conta.getValor());
    		stmt.setObject(7, conta.getConsumo());
    		stmt.setObject(8, conta.getObs());
    		stmt.setObject(9, conta.getIdConta());
    		stmt.executeUpdate();
    	} 
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }
    
    public Long buscarUltimo() throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(
    		"SELECT MAX(ID_CONTA) as ID_CONTA " +
        	"  FROM TB_CONTA "	
            );

            rs = stmt.executeQuery();

            if(rs.next()) {
            	return rs.getLong("ID_CONTA");
            }
            return null;
        }
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null) 
                rs.close();
        }
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
    
    public List<Conta> listarContaRateio(Long idCondominio) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Conta> list = new ArrayList<Conta>();
        
        try {
            
        	stmt = connection.prepareStatement(
    		"SELECT    TB_CONTA.ID_CONTA, " +
        	"          TB_CONTA.ID_EMPRESA, " +
        	"          TB_CONTA.ID_CONDOMINIO, " +
        	"          TB_CONTA.ID_USER, " +
        	"	       TB_CONTA.DTLEITURAATUAL, " +
        	"          TB_CONTA.DTLEITURAANTERIOR, " +
        	"          TB_CONTA.VALOR, " +
        	"          TB_CONTA.CONSUMO, " +
        	"          TB_CONTA.OBS, " +
        	"          TB_CONTA.DTINSERT " +
        	"FROM      TB_CONTA " +	
        	"LEFT JOIN TB_CONTARATEIO " +
        	"ON        TB_CONTA.ID_CONTA = TB_CONTARATEIO.ID_CONTA " +
            "WHERE     TB_CONTA.ID_CONDOMINIO = ? " +
        	"AND       TB_CONTARATEIO.ID_CONTARATEIO IS NULL "
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
    
    public List<Conta> listarPorEmpresaLocal(Long idEmpresa, Long idCondominio) throws Exception {
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
					"  FROM TB_CONTA " +	
					" WHERE ID_CONDOMINIO = ? " +
					"   AND ID_EMPRESA = ? "
    				);
    		
    		stmt.setLong(1, idCondominio);
    		stmt.setLong(2, idEmpresa);
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
    			
//    			conta.setIdContaRateio(new Long(1));
    			
    			list.add(conta);
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