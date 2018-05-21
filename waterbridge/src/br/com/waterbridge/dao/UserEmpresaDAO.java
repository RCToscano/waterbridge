
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.waterbridge.auxiliar.Auxiliar;
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
            "       ID_INSERT, " +
            "		ID_USER, " +
            "		ID_EMPRESA, " +
            " 		SITUACAO, " +
            "		DTINICIO, " +
            "		DTFIM " +
            ") VALUES ( " +
            "       ?,?,?,?,SYSDATE(),NULL " +
            ")");

            //stmt.setObject(1, userEmpresa.getIdUserEmpresa());
            stmt.setObject(1, userEmpresa.getIdInsert());
            stmt.setObject(2, userEmpresa.getIdUser());
            stmt.setObject(3, userEmpresa.getIdEmpresa());
            stmt.setObject(4, userEmpresa.getSituacao());
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
    
    public void inativar(Long idUserEmpresa) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
            
            //LOGA REGISTRO ANTES DE ALTERAR
            logar(idUserEmpresa);
        
            stmt = connection.prepareStatement(
            "UPDATE TB_USEREMPRESA SET " +
            "	    SITUACAO = 'I', " +
            "		DTFIM = SYSDATE() " +
            "WHERE  ID_USEREMPRESA = ? ");
            
            stmt.setObject(1, idUserEmpresa);

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

    public UserEmpresa buscar(Long idUser, Long idEmpresa, String situacao) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        UserEmpresa userEmpresa = null;
        
        try {
            
            stmt = connection.prepareStatement(
        	"SELECT ID_USEREMPRESA, " +
        	"		ID_INSERT, " +
        	"		ID_USER, " +
        	"		ID_EMPRESA, " +
        	"		SITUACAO, " +
        	"		DTINICIO, " +
        	"		DTFIM " +
        	"FROM   TB_USEREMPRESA " +
        	"WHERE  ID_USER = ? " +
        	"AND    ID_EMPRESA = ? " +
        	"AND    SITUACAO = ? " 
            );

            stmt.setLong(1, idUser);
            stmt.setLong(2, idEmpresa);
            stmt.setString(3, situacao);
            
            rs = stmt.executeQuery();

            if(rs.next()) {

            	userEmpresa = new UserEmpresa();
            	userEmpresa.setIdUserEmpresa(rs.getLong("ID_USEREMPRESA"));
            	userEmpresa.setIdInsert(rs.getLong("ID_INSERT"));
            	userEmpresa.setIdUser(rs.getLong("ID_USER"));
            	userEmpresa.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	userEmpresa.setSituacao(rs.getString("SITUACAO"));
            	userEmpresa.setDtInicio(Auxiliar.formataDtTelaHr(rs.getString("DTINICIO")));
            	userEmpresa.setDtFim(Auxiliar.formataDtTelaHr(rs.getString("DTFIM")));
            }
            
            return userEmpresa;
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
