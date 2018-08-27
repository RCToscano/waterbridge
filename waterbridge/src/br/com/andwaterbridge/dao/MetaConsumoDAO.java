package br.com.andwaterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.andwaterbridge.modelo.MetaConsumo;

public class MetaConsumoDAO {
    
    private Connection connection;

    public MetaConsumoDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(MetaConsumo metaConsumo) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_METACONSUMO ( " +
    		//"       ID_METACONSUMO, " +
    		"       ID_USER, " +
    		"       ID_MEDIDOR, " +
    		"       META, " +
    		"       DTINSERT " +
            ") VALUES ( " +
            "       ?,?,?,SYSDATE() " +
            ")");
            
            //stmt.setObject(1, metaConsumo.getIdMetaConsumo());
            stmt.setObject(1, metaConsumo.getIdUser());
            stmt.setObject(2, metaConsumo.getIdMedidor());
            stmt.setObject(3, metaConsumo.getMeta());
            //stmt.setObject(4, metaConsumo.getDtInsert());
            
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
    
    public void alterar(MetaConsumo metaConsumo) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
            
            //LOGA REGISTRO ANTES DE ALTERAR
            logar(metaConsumo.getIdMetaConsumo());
        
            stmt = connection.prepareStatement(
            "UPDATE TB_METACONSUMO SET " +
    		"       ID_USER = ?, " +
    		"       ID_MEDIDOR = ?, " +
    		"       META = ?, " +
    		"       DTINSERT = SYSDATE() " +
    		"WHERE  ID_METACONSUMO = ? ");
          
            stmt.setObject(1, metaConsumo.getIdUser());
            stmt.setObject(2, metaConsumo.getIdMedidor());
            stmt.setObject(3, metaConsumo.getMeta());
            //stmt.setObject(4, metaConsumo.getDtInsert());
            stmt.setObject(4, metaConsumo.getIdMetaConsumo());

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

    public void logar(Long idMetaConsumo) throws SQLException {

        PreparedStatement stmt = null;
        
        try {
            
            stmt = connection.prepareStatement(
            "INSERT INTO TB_METACONSUMOLOG " +
            "SELECT * " +
            "FROM   TB_METACONSUMO " +
            "WHERE  ID_METACONSUMO = ? "
            );

            stmt.setLong(1, idMetaConsumo);
            
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
    
    public MetaConsumo buscarPorIdMedidor(Long idMedidor) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        MetaConsumo metaConsumo = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_METACONSUMO, " +
        	"       ID_USER, " +
        	"       ID_MEDIDOR, " +
        	"       META, " +
        	"       DTINSERT " +
        	"FROM   TB_METACONSUMO " +
        	"WHERE  ID_MEDIDOR = ? "
            );

            stmt.setLong(1, idMedidor);

            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	metaConsumo = new MetaConsumo();
            	metaConsumo.setIdMetaConsumo(rs.getLong("ID_METACONSUMO"));
            	metaConsumo.setIdUser(rs.getLong("ID_USER"));
            	metaConsumo.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	metaConsumo.setMeta(rs.getDouble("META"));
            	metaConsumo.setDtInsert(rs.getString("DTINSERT"));
            }
            
            return metaConsumo;
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
