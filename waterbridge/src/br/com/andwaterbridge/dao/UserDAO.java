package br.com.andwaterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.andwaterbridge.modelo.User;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {

        this.connection = connection;
    }

    public User login(String usuario, String senha) throws Exception {
    	
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            stmt = connection.prepareStatement(
            "SELECT    TB_USER.ID_USER, " +
            "          TB_PERFIL.ID_PERFIL, " +
            "          TB_PERFIL.PERFIL, " +		
            "          TB_PASS.PASS, " +
            "          TB_USER.NOME, " +
            "          TB_USER.EMAIL, " +
            "          TB_USER.SEXO, " +
            "          TB_USER.DTNASC, " +
            "          TB_USER.DTINSERT, " +
            "          TB_USER.SITUACAO, " +
            "          TB_USER.USUARIO, " +
            "          TB_USER.CPF, " +
            "          TB_USER.TELFIXO, " +
            "          TB_USER.ENDERECO, " +
            "          TB_USER.NUMERO, " +
            "          TB_USER.COMPL, " +
            "          TB_USER.MUNICIPIO, " +
            "          TB_USER.UF, " +
            "          TB_USER.CEP, " +
            "          TB_USER.COORDX, " +
            "          TB_USER.COORDY, " +
            "          TB_USER.TELCEL " +
            "FROM      TB_USER " +
            "LEFT JOIN TB_PERFIL " +
            "ON        TB_USER.ID_PERFIL = TB_PERFIL.ID_PERFIL " +
            "LEFT JOIN TB_PASS " +
            "ON        TB_USER.ID_USER = TB_PASS.ID_USER " +            		
            "WHERE     TB_USER.USUARIO = ? " +
            "AND       TB_PASS.PASS = ? "
            );
            
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	user = new User();
                user.setIdUser(rs.getLong("ID_USER"));
                user.setIdPerfil(rs.getLong("ID_PERFIL"));
                user.setPerfil(rs.getString("PERFIL"));
                user.setPass(rs.getString("PASS"));
                user.setUsuario(rs.getString("USUARIO"));
                user.setNome(rs.getString("NOME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setSexo(rs.getString("SEXO"));
                user.setDtNasc(rs.getString("DTNASC"));
                user.setTelFixo(rs.getString("TELFIXO"));
                user.setTelCel(rs.getString("TELCEL"));
                user.setCpf(rs.getString("CPF"));
                user.setEndereco(rs.getString("ENDERECO"));
                user.setNumero(rs.getLong("NUMERO"));
                user.setCompl(rs.getString("COMPL"));
                user.setMunicipio(rs.getString("MUNICIPIO"));
                user.setUf(rs.getString("UF"));
                user.setCep(rs.getString("CEP"));
            	user.setCoordx(rs.getString("COORDX"));
                user.setCoordy(rs.getString("COORDY"));
                user.setSituacao(rs.getString("SITUACAO"));
                user.setDtInsert(rs.getString("DTINSERT"));
            }

            return user;
        } 
        finally {
            if (stmt != null) {
            	stmt.close();
            }
            if (rs != null) {
            	rs.close();
            }
        }
    }        
}
