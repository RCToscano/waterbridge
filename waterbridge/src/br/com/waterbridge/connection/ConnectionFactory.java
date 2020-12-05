
package br.com.waterbridge.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    //CONEXAO REMOTA
	//private static final String url = "jdbc:mysql://www.waterbridge.com.br:3306/waterbri_001";
    private static final String url = "jdbc:mysql://localhost:3306/waterbri_001?noAccessToProcedureBodies=true";//INTEGRATOR
    //private static final String url = "jdbc:mysql://200.147.61.72:3306/waterbri_001";//UOL
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String usuario = "waterbri_001";//INTEGRATOR//UOL
    private static final String senha = "wdahE&h083";//INTEGRATOR
    //private static final String senha = "wdahE@h083";//UOL
                                           
       
//    //CONEXAO LOCAL
//    private static final String url = "jdbc:mysql://localhost:3306/waterbri_001";
//    private static final String driver = "com.mysql.jdbc.Driver";
//    private static final String usuario = "root";
//    private static final String senha = "admin";

    public static Connection getConnection() throws SQLException {

        try {

            Class.forName(driver);
            return DriverManager.getConnection(url, usuario, senha);
        } 
        catch (ClassNotFoundException e) {

            throw new SQLException(e.getMessage());
        }
    }
    
    public static String getUsuario() {
		return usuario;
	}
}
