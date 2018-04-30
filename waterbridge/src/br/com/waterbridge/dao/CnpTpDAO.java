package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.modelo.CnpTp;

public class CnpTpDAO {
    
    Connection connection = null;

    public CnpTpDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public CnpTp buscar(Long idCnpTp) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        CnpTp cnpTp = null;
        
        try {
            
            stmt = connection.prepareStatement(
        	"SELECT ID_CNPTP, " +
        	"       DESCRICAO " +
        	"FROM   TB_CNPTP " +
        	"WHERE  ID_CNPTP = ? "
            );

            stmt.setLong(1, idCnpTp);
            
            rs = stmt.executeQuery();

            if(rs.next()) {

            	cnpTp = new CnpTp();
            	cnpTp.setIdCnpTp(rs.getLong("ID_CNPTP"));
            	cnpTp.setDescricao(rs.getString("DESCRICAO"));
            }
            
            return cnpTp;
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
    
    public List<CnpTp> listar() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<CnpTp> list = new ArrayList<CnpTp>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT   ID_CNPTP, " +
        	"         DESCRICAO " +
        	"FROM     TB_CNPTP " +
        	"ORDER BY DESCRICAO "		 
            );

            rs = stmt.executeQuery();

            while(rs.next()) {

            	CnpTp cnpTp = new CnpTp();
            	cnpTp.setIdCnpTp(rs.getLong("ID_CNPTP"));
            	cnpTp.setDescricao(rs.getString("DESCRICAO"));
            	
            	list.add(cnpTp);
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
}