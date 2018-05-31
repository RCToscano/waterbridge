package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.modelo.Perfil;
import br.com.waterbridge.modelo.User;

public class PerfilDAO {
	
	private Connection connection;

    public PerfilDAO(Connection connection) {
        this.connection = connection;
    }

	public Perfil buscarPorId(Long idPerfil) throws Exception {
		
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Perfil perfil = null;
        
        try {
            stmt = connection.prepareStatement(
            "SELECT ID_PERFIL, " +
            "       PERFIL, " +
            "       MENU " +
            "FROM   TB_PERFIL " +
            "WHERE  ID_PERFIL = ? "
            );
            
            stmt.setObject(1, idPerfil);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	perfil = new Perfil();
            	perfil.setIdPerfil(rs.getLong("ID_PERFIL"));
            	perfil.setPerfil(rs.getString("PERFIL"));
            	perfil.setMenu(rs.getString("MENU"));
            }
            return perfil;
        } 
        finally {
        	
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
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
	
	public List<Perfil> listarPorOrdemPermissao(User user) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Perfil> list = new ArrayList<>();
		try {
			stmt = connection.prepareStatement(
					"  SELECT TB_PERFIL.* " +
					"    FROM TB_PERFIL " +
					"    WHERE TB_PERFIL.ID_PERFIL > ? " +
					"ORDER BY TB_PERFIL.ID_PERFIL "
					);
			stmt.setObject(1, user.getPerfil().getIdPerfil());
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
