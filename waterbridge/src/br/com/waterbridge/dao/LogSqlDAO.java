package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogSqlDAO {
    
    Connection connection = null;

    public LogSqlDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(Long idUser, String logSql, String texto, String tela, String acao) throws SQLException {
        
        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_LOGSQL ( " +
			"       ID_USER, " +
			"       LOGSQL, " +
			"       TEXTO, " +
			"       TELA, " +
			"       ACAO, " +
			"       DTINSERT " +
            ") VALUES ( " +
            "       ?,?,?,?,?,sysdate() " +
            ") ");
            
            stmt.setObject(1, idUser);
            stmt.setObject(2, logSql);
            stmt.setObject(3, texto);
            stmt.setObject(4, tela);
            stmt.setObject(5, acao);

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
    
//    public static void main(String[] args) throws SQLException {
//    	Connection connection = ConnectionFactory.getConnection();
//    	LogSql logSql = new LogSql();
//    	//logSql.setIdLogSql(null);
//    	logSql.setIdUser(4l);
//    	logSql.setLogSql("teste");
//    	logSql.setTexto("texto");
//    	logSql.setDtInsert(null);
//    	LogSqlDAO logSqlDAO = new LogSqlDAO(connection);
//    	logSqlDAO.inserir(logSql);
//    	connection.close();
//	}
}


