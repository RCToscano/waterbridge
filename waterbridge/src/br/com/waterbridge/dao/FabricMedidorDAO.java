
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.FabricMedidor;

public class FabricMedidorDAO {
    
    Connection connection = null;

    public FabricMedidorDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(FabricMedidor fabricMedidor) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_FABRICMEDIDOR ( " +
            //"		ID_FABRICMEDIDOR, " +
            "		ID_USER, " +
            "		FABRICANTE, " +
            "		SITUACAO, " +
            "		DTINSERT " +
            ") VALUES ( " +
            "       ?,?,?,SYSDATE() " +
            ")");
            
            //stmt.setObject(1, fabricMedidor.getIdFabricMedidor());
            stmt.setObject(1, fabricMedidor.getIdUser());
            stmt.setObject(2, fabricMedidor.getFabricante());
            stmt.setObject(3, fabricMedidor.getSituacao());
            //stmt.setObject(5, fabricMedidor.getDtInsert());
            
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
    
    public void alterar(FabricMedidor fabricMedidor) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
            
            //LOGA REGISTRO ANTES DE ALTERAR
            logar(fabricMedidor.getIdFabricMedidor());
        
            stmt = connection.prepareStatement(
            "UPDATE TB_FABRICMEDIDOR SET " +
            "		ID_USER = ?, " +
            "		FABRICANTE = ?, " +
            "		SITUACAO = ?, " +
            "		DTINSERT = SYSDATE() " +
            "WHERE  ID_FABRICMEDIDOR = ? ");
            
            stmt.setObject(1, fabricMedidor.getIdUser());
            stmt.setObject(2, fabricMedidor.getFabricante());
            stmt.setObject(3, fabricMedidor.getSituacao());
            //stmt.setObject(4, fabricMedidor.getDtInsert());
            stmt.setObject(4, fabricMedidor.getIdFabricMedidor());

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

    public void logar(Long idFabricMedidor) throws SQLException {

        PreparedStatement stmt = null;
        
        try {
            
            stmt = connection.prepareStatement(
            "INSERT INTO TB_FABRICMEDIDORLOG " +
            "SELECT * " +
            "FROM   TB_FABRICMEDIDOR " +
            "WHERE  ID_FABRICMEDIDOR = ? "
            );

            stmt.setLong(1, idFabricMedidor);
            
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
    
    public FabricMedidor buscar(Long idFabricMedidor) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        FabricMedidor fabricMedidor = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_FABRICMEDIDOR, " +
        	"       ID_USER, " +
        	"       FABRICANTE, " +
        	"       SITUACAO, " +
        	"       DTINSERT " +
        	"FROM   TB_FABRICMEDIDOR " +
        	"WHERE  ID_FABRICMEDIDOR = ? "
            );

            stmt.setLong(1, idFabricMedidor);
            
            rs = stmt.executeQuery();

            if(rs.next()) {

            	fabricMedidor = new FabricMedidor();
            	fabricMedidor.setIdFabricMedidor(rs.getLong("ID_FABRICMEDIDOR"));
            	fabricMedidor.setIdUser(rs.getLong("ID_USER"));
            	fabricMedidor.setFabricante(rs.getString("FABRICANTE"));
            	fabricMedidor.setSituacao(rs.getString("SITUACAO"));
            	fabricMedidor.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            }
            
            return fabricMedidor;
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
    
    public FabricMedidor buscarPorFabric(String fabricante) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        FabricMedidor fabricMedidor = null;
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT ID_FABRICMEDIDOR, " +
        	"       ID_USER, " +
        	"       FABRICANTE, " +
        	"       SITUACAO, " +
        	"       DTINSERT " +
        	"FROM   TB_FABRICMEDIDOR " +
        	"WHERE  UPPER(FABRICANTE) = ? "
            );

            stmt.setString(1, fabricante.toUpperCase());
            
            rs = stmt.executeQuery();

            if(rs.next()) {

            	fabricMedidor = new FabricMedidor();
            	fabricMedidor.setIdFabricMedidor(rs.getLong("ID_FABRICMEDIDOR"));
            	fabricMedidor.setIdUser(rs.getLong("ID_USER"));
            	fabricMedidor.setFabricante(rs.getString("FABRICANTE"));
            	fabricMedidor.setSituacao(rs.getString("SITUACAO"));
            	fabricMedidor.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            }
            
            return fabricMedidor;
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
    
    public List<FabricMedidor> listar() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<FabricMedidor> list = new ArrayList<FabricMedidor>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT   ID_FABRICMEDIDOR, " +
        	"         ID_USER, " +
        	"         FABRICANTE, " +
        	"         SITUACAO, " +
        	"         DTINSERT " +
        	"FROM     TB_FABRICMEDIDOR " +
        	"ORDER BY FABRICANTE "		 
            );

            rs = stmt.executeQuery();

            while(rs.next()) {

            	FabricMedidor fabricMedidor = new FabricMedidor();
            	fabricMedidor.setIdFabricMedidor(rs.getLong("ID_FABRICMEDIDOR"));
            	fabricMedidor.setIdUser(rs.getLong("ID_USER"));
            	fabricMedidor.setFabricante(rs.getString("FABRICANTE"));
            	fabricMedidor.setSituacao(rs.getString("SITUACAO"));
            	fabricMedidor.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	
            	list.add(fabricMedidor);
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
    
    public List<FabricMedidor> listar(String sql) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<FabricMedidor> list = new ArrayList<FabricMedidor>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT   ID_FABRICMEDIDOR, " +
        	"         ID_USER, " +
        	"         FABRICANTE, " +
        	"         SITUACAO, " +
        	"         DTINSERT " +
        	"FROM     TB_FABRICMEDIDOR " +
        	sql
            );

            rs = stmt.executeQuery();

            while(rs.next()) {

            	FabricMedidor fabricMedidor = new FabricMedidor();
            	fabricMedidor.setIdFabricMedidor(rs.getLong("ID_FABRICMEDIDOR"));
            	fabricMedidor.setIdUser(rs.getLong("ID_USER"));
            	fabricMedidor.setFabricante(rs.getString("FABRICANTE"));
            	fabricMedidor.setSituacao(rs.getString("SITUACAO"));
            	fabricMedidor.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	
            	list.add(fabricMedidor);
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