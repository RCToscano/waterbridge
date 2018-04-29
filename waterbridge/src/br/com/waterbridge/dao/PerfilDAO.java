package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.modelo.Perfil;

public class PerfilDAO {
	
	private Connection connection;

    public PerfilDAO(Connection connection) {
        this.connection = connection;
    }

	
	public List<Perfil> listarTodos() throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Perfil> list = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(
                    "  SELECT TB_PERFIL.* " +
                    "    FROM TB_PERFIL " +
                    "ORDER BY TB_PERFIL.ID_PERFIL "
            );
            rs = stmt.executeQuery();

            while (rs.next()) {
            	Perfil perfil = new Perfil();
            	perfil.setIdPerfil(rs.getLong("ID_PERFIL"));
            	perfil.setPerfil(rs.getString("PERFIL"));
            	perfil.setMenu(rs.getString("MENU"));
            	
            	if(!perfil.getPerfil().equals("PROGRAMADOR"))
            		list.add(perfil);
            }
            return list;
        } 
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }

}
