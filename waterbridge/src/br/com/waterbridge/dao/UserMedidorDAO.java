
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.CnpTp;
import br.com.waterbridge.modelo.UserMedidor;

public class UserMedidorDAO {
    
    private Connection connection;

    public UserMedidorDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(UserMedidor userMedidor) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_USERMEDIDOR ( " +
            //"		ID_USERMEDIDOR, " +
            "		ID_INSERT, " +
            "		ID_USER, " +
            "		ID_MEDIDOR, " +
            "       OBS, " +
            "		SITUACAO, " +
            "		DTINICIO, " +
            "		DTFIM " +
            ") VALUES ( " +
            "       ?,?,?,?,?,SYSDATE(),NULL " +
            ")");
            
            //stmt.setObject(1, userMedidor.getIdUserMedidor());
            stmt.setObject(1, userMedidor.getIdInsert());
            stmt.setObject(2, userMedidor.getIdUser());
            stmt.setObject(3, userMedidor.getIdMedidor());
            stmt.setObject(4, userMedidor.getObs());
            stmt.setObject(5, userMedidor.getSituacao());
            //stmt.setObject(6, userMedidor.getDtInicio());
            //stmt.setObject(7, userMedidor.getDtFim());
            
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
    
    public void inativar(UserMedidor userMedidor) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
            
            //LOGA REGISTRO ANTES DE ALTERAR
            logar(userMedidor.getIdUserMedidor());
        
            stmt = connection.prepareStatement(
            "UPDATE TB_USERMEDIDOR SET " +
            "       OBS = ?, " +
            "	    SITUACAO = 'I', " +
            "		DTFIM = SYSDATE() " +
            "WHERE  ID_USERMEDIDOR = ? ");
            
            stmt.setObject(1, userMedidor.getObs());
            stmt.setObject(2, userMedidor.getIdUserMedidor());

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
    
    public UserMedidor buscar(Long idUser, Long idMedidor, String situacao) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        UserMedidor userMedidor = null;
        
        try {
            
            stmt = connection.prepareStatement(
        	"SELECT ID_USERMEDIDOR, " +
        	"		ID_INSERT, " +
        	"		ID_USER, " +
        	"		ID_MEDIDOR, " +
        	"       OBS, " +
        	"		SITUACAO, " +
        	"		DTINICIO, " +
        	"		DTFIM " +
        	"FROM   TB_USERMEDIDOR " +
        	"WHERE  ID_USER = ? " +
        	"AND    ID_MEDIDOR = ? " +
        	"AND    SITUACAO = ? " 
            );

            stmt.setLong(1, idUser);
            stmt.setLong(2, idMedidor);
            stmt.setString(3, situacao);
            
            rs = stmt.executeQuery();

            if(rs.next()) {

            	userMedidor = new UserMedidor();
            	userMedidor.setIdUserMedidor(rs.getLong("ID_USERMEDIDOR"));
            	userMedidor.setIdInsert(rs.getLong("ID_INSERT"));
            	userMedidor.setIdUser(rs.getLong("ID_USER"));
            	userMedidor.setIdMedidor(rs.getLong("ID_MEDIDOR"));
            	userMedidor.setObs(rs.getString("OBS"));
            	userMedidor.setSituacao(rs.getString("SITUACAO"));
            	userMedidor.setDtInicio(Auxiliar.formataDtTelaHr(rs.getString("DTINICIO")));
            	userMedidor.setDtFim(Auxiliar.formataDtTelaHr(rs.getString("DTFIM")));
            }
            
            return userMedidor;
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

    public void logar(Long idUserMedidor) throws SQLException {

        PreparedStatement stmt = null;
        
        try {
            
            stmt = connection.prepareStatement(
            "INSERT INTO TB_USERMEDIDORLOG " +
            "SELECT * " +
            "FROM   TB_USERMEDIDOR " +
            "WHERE  ID_USERMEDIDOR = ? "
            );

            stmt.setLong(1, idUserMedidor);
            
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
}
