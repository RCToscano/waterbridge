
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.waterbridge.modelo.UserEmpresa;

public class UserEmpresaDAO {
    
    private Connection connection;

    public UserEmpresaDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(UserEmpresa userEmpresa) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_USEREMPRESA ( " +
            //"		ID_USEREMPRESA, " +
            "		ID_USER, " +
            "		ID_EMPRESA, " +
            " 		SITUACAO " +
            "		DTINICIO, " +
            "		DTFIM " +
            ") VALUES ( " +
            "       ?,?,?,SYSDATE(),NULL " +
            ")");
            
            //stmt.setObject(1, userEmpresa.getIdUserEmpresa());
            stmt.setObject(1, userEmpresa.getIdUser());
            stmt.setObject(2, userEmpresa.getIdEmpresa());
            stmt.setObject(3, userEmpresa.getSituacao());
            //stmt.setObject(4, userEmpresa.getDtInicio());
            //stmt.setObject(5, userEmpresa.getDtFim());
            
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
    
    public void inativar(UserEmpresa userEmpresa) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
            
            //LOGA REGISTRO ANTES DE ALTERAR
            logar(userEmpresa.getIdUserEmpresa());
        
            stmt = connection.prepareStatement(
            "UPDATE TB_USEREMPRESA SET " +
            "		ID_USER = ?, " +
            "		ID_EMPRESA = ?, " +
            "	    SITUACAO = ?, " +
            "		DTINICIO = ?, " +
            "		DTFIM = SYSDATE() " +
            "WHERE  ID_USEREMPRESA = ? ");
            
            stmt.setObject(1, userEmpresa.getIdUser());
            stmt.setObject(2, userEmpresa.getIdEmpresa());
            stmt.setObject(3, userEmpresa.getSituacao());
            stmt.setObject(4, userEmpresa.getDtInicio());
            //stmt.setObject(5, userEmpresa.getDtFim());
            stmt.setObject(5, userEmpresa.getIdUserEmpresa());

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

    public void logar(Long idUserEmpresa) throws SQLException {

        PreparedStatement stmt = null;
        
        try {
            
            stmt = connection.prepareStatement(
            "INSERT INTO TB_USEREMPRESALOG " +
            "SELECT * " +
            "FROM   TB_USEREMPRESA " +
            "WHERE  ID_USEREMPRESA = ? "
            );

            stmt.setLong(1, idUserEmpresa);
            
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
