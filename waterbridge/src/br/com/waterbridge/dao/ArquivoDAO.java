
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.waterbridge.modelo.Arquivo;

public class ArquivoDAO {
    
    Connection connection = null;

    public ArquivoDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(Arquivo arquivo) throws Exception {
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	stmt = connection.prepareStatement(
			"INSERT INTO TB_ARQUIVO ( " +
			//"       ID_ARQUIVO, " +
			"       REMESSA, " +
			"       NOME_ARQUIVO, " +
			"       QTDE, " +
			"       DTINSERT " +
			")" +
			" VALUES ( " +
    		"       ?,?,?,?) " 
			);
        			
            stmt.setObject(1, arquivo.getRemessa());
            stmt.setObject(2, arquivo.getNomeArquivo());
            stmt.setObject(3, arquivo.getQtde());
            stmt.setObject(4, arquivo.getDtInsert());
            stmt.executeUpdate();
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
    
    public void alterar(Arquivo arquivo) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		stmt = connection.prepareStatement(
			"UPDATE TB_CONTA SET " +
			"       REMESSA = ?, " +
			"       NOME_ARQUIVO = ?, " +
			"       QTDE = ?, " +
			"       DTINSERT = ? " +
			"WHERE  ID_ARQUIVO = ? "
			);
    		
    		stmt.setObject(1, arquivo.getRemessa());
    		stmt.setObject(2, arquivo.getNomeArquivo());
    		stmt.setObject(3, arquivo.getQtde());
    		stmt.setObject(4, arquivo.getDtInsert());
    		stmt.setObject(5, arquivo.getIdArquivo());
    		stmt.executeUpdate();
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
    
    public String buscarDataAtual() throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = connection.prepareStatement(
            "SELECT DATE_FORMAT(SYSDATE(), '%Y-%m-%d %H:%i:%s')  DATA_ATUAL "	
            );

            rs = stmt.executeQuery();

            if(rs.next()) {
            	return rs.getString("DATA_ATUAL");
            }
            return null;
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
    
    public Integer buscarUltimaRemessa(String dataAtual) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer remessa = null;
        try {
            stmt = connection.prepareStatement(
    		"SELECT MAX(REMESSA) REMESSA " + 
    		"FROM   TB_ARQUIVO " +
    		"WHERE  DATE_FORMAT(DTINSERT, '%Y-%m-%d') = '" + dataAtual + "'"	
            );

            rs = stmt.executeQuery();

            if(rs.next()) {
            	remessa = rs.getInt("REMESSA");
            }
            if(remessa == null) {
            	return 0;
            }
            else {
            	return remessa;
            }
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
    
    public Arquivo buscarPorRemessaData(Integer remessa, String dtInsert) throws SQLException {
    	
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	Arquivo arquivo = null;
    	
    	try {
    		
    		stmt = connection.prepareStatement(
			"SELECT ID_ARQUIVO, " +
			"       REMESSA, " +
			"       NOME_ARQUIVO, " +
			"       QTDE, " +
			"       DTINSERT " +    						
			"FROM   TB_ARQUIVO " +	
			"WHERE  REMESSA = ? " +
			"AND    DTINSERT = ? "
			);
    		
    		stmt.setLong(1, remessa);
    		stmt.setString(2, dtInsert);
    		
    		rs = stmt.executeQuery();
    		
    		if(rs.next()) {    			
    			arquivo = new Arquivo();    		
    			arquivo.setIdArquivo(rs.getLong("ID_ARQUIVO"));
    			arquivo.setNomeArquivo(rs.getString("NOME_ARQUIVO"));
    			arquivo.setRemessa(rs.getInt("REMESSA"));
    			arquivo.setQtde(rs.getInt("QTDE"));
    			arquivo.setDtInsert(rs.getString("DTINSERT"));
    		}    		
    		return arquivo;
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