
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.modelo.Permissao;

public class PermissaoDAO {
    
    Connection connection = null;

    public PermissaoDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public List<Permissao> listar(Long idUser) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Permissao> list = new ArrayList<Permissao>();
        
        try {
            
            stmt = connection.prepareStatement(
            "SELECT    TB_PERMISSAO.ID_PERMISSAO, " +
            "          TB_PERMISSAO.TELA, " +
            "          TB_PERMISSAO.ACAO, " +
            "          TB_PERMISSAO.CODIGO " +
            "FROM      TB_USERPERM " +
            "LEFT JOIN TB_PERMISSAO " +
            "ON        TB_USERPERM.ID_PERMISSAO = TB_PERMISSAO.ID_PERMISSAO " +
            "WHERE     TB_USERPERM.ID_USER = ? " +
            "ORDER BY  TB_PERMISSAO.TELA, " +
            "          TB_PERMISSAO.ACAO " 
            );
            
            stmt.setLong(1, idUser);

            rs = stmt.executeQuery();

            while(rs.next()) {

                Permissao permissao = new Permissao();
                permissao.setIdPermissao(rs.getLong("ID_PERMISSAO"));
                permissao.setTela(rs.getString("TELA"));
                permissao.setAcao(rs.getString("ACAO"));
                permissao.setCodigo(rs.getString("CODIGO"));

                list.add(permissao);
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

