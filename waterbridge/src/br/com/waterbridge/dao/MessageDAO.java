package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.waterbridge.connection.ConnectionFactory;
import br.com.waterbridge.modelo.Message;

public class MessageDAO {
    
    Connection connection = null;

    public MessageDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(Message message) throws SQLException {
        
        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_MESSAGE ( " +
            //"		ID_MESSAGE, " +
            "		TEXTO " +
            ") VALUES ( " +
            "       ? " +
            ") ");
            
            stmt.setString(1, message.getTexto());

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
//		
//    	Connection connection = ConnectionFactory.getConnection();
//    	
//    	Message message = new Message();
//    	message.setTexto("teste");
//    	
//    	MessageDAO messageDAO = new MessageDAO(connection);
//    	messageDAO.inserir(message);
//    	
//    	connection.close();
//	}
}


