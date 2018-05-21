
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.UserCondominio;
import br.com.waterbridge.modelo.UserEmpresa;

public class UserCondominioDAO {
    
    private Connection connection;

    public UserCondominioDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(UserCondominio userCondominio) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_USERCONDOMINIO ( " +
            //"		ID_USERCONDOMINIO, " +
            "       ID_INSERT, " +
            "		ID_USER, " +
            "		ID_CONDOMINIO, " +
            " 		SITUACAO, " +
            "		DTINICIO, " +
            "		DTFIM " +
            ") VALUES ( " +
            "       ?,?,?,?,SYSDATE(),NULL " +
            ")");
            
            //stmt.setObject(1, userCondominio.getIdUserCondominio());
            stmt.setObject(1, userCondominio.getIdInsert());
            stmt.setObject(2, userCondominio.getIdUser());
            stmt.setObject(3, userCondominio.getIdCondominio());
            stmt.setObject(4, userCondominio.getSituacao());
            //stmt.setObject(5, userCondominio.getDtInicio());
            //stmt.setObject(6, userCondominio.getDtFim());
            
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
    
    public void inativar(Long idUserCondominio) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
            
            //LOGA REGISTRO ANTES DE ALTERAR
            logar(idUserCondominio);
        
            stmt = connection.prepareStatement(
            "UPDATE TB_USERCONDOMINIO SET " +
            "	    SITUACAO = 'I', " +
            "		DTFIM = SYSDATE() " +
            "WHERE  ID_USERCONDOMINIO = ? ");
            
            stmt.setObject(1, idUserCondominio);

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

    public UserCondominio buscar(Long idUser, Long idCondominio, String situacao) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        UserCondominio userCondominio = null;
        
        try {
            
            stmt = connection.prepareStatement(
        	"SELECT ID_USERCONDOMINIO, " +
        	"		ID_INSERT, " +
        	"		ID_USER, " +
        	"		ID_CONDOMINIO, " +
        	"		SITUACAO, " +
        	"		DTINICIO, " +
        	"		DTFIM " +
        	"FROM   TB_USERCONDOMINIO " +
        	"WHERE  ID_USER = ? " +
        	"AND    ID_CONDOMINIO = ? " +
        	"AND    SITUACAO = ? " 
            );

            stmt.setLong(1, idUser);
            stmt.setLong(2, idCondominio);
            stmt.setString(3, situacao);
            
            rs = stmt.executeQuery();

            if(rs.next()) {

            	userCondominio = new UserCondominio();
            	userCondominio.setIdUserCondominio(rs.getLong("ID_USERCONDOMINIO"));
            	userCondominio.setIdInsert(rs.getLong("ID_INSERT"));
            	userCondominio.setIdUser(rs.getLong("ID_USER"));
            	userCondominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	userCondominio.setSituacao(rs.getString("SITUACAO"));
            	userCondominio.setDtInicio(Auxiliar.formataDtTelaHr(rs.getString("DTINICIO")));
            	userCondominio.setDtFim(Auxiliar.formataDtTelaHr(rs.getString("DTFIM")));
            }
            
            return userCondominio;
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
    
    public void logar(Long idUserCondominio) throws SQLException {

        PreparedStatement stmt = null;
        
        try {
            
            stmt = connection.prepareStatement(
            "INSERT INTO TB_USERCONDOMINIOLOG " +
            "SELECT * " +
            "FROM   TB_USERCONDOMINIO " +
            "WHERE  ID_USERCONDOMINIO = ? "
            );

            stmt.setLong(1, idUserCondominio);
            
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
