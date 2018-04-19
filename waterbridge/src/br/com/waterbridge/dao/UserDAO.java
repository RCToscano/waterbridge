package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.modelo.Pass;
import br.com.waterbridge.modelo.Perfil;
import br.com.waterbridge.modelo.User;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {

        this.connection = connection;
    }

    public User login(String email, String senha) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        User user = null;

        try {
            
            stmt = connection.prepareStatement(
            "SELECT    TB_USER.ID_USER, " +
            "          TB_USER.NOME, " +
            "          TB_USER.EMAIL, " +
            "          TB_USER.SEXO, " +
            "          CHAR(TB_USER.DTNASC,'DD/MM/YYYY') AS DTNASC, " +        
            "          CHAR(TB_USER.DTINSERT,'DD/MM/YYYY') AS DTINSERT, " +
            "          TB_USER.SITUACAO, " +
            "          TB_PERFIL.ID_PERFIL, " +
            "          TB_PERFIL.PERFIL, " +
            "          TB_PERFIL.MENU, " +
            "          TB_PASS.ID_PASS, " +
            "          TB_PASS.PASS " +
            "FROM      TB_USER " +
            "LEFT JOIN TB_PERFIL " +
            "ON        TB_USER.ID_PERFIL = TB_PERFIL.ID_PERFIL " +
            "LEFT JOIN TB_PASS " +
            "ON        TB_USER.ID_USER = TB_PASS.ID_USER " +
            "WHERE     TB_USER.EMAIL = ? " +
            "AND       TB_PASS.PASS = ? "
            );
            
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();

            if (rs.next()) {
                
                Perfil perfil = new Perfil();
                perfil.setIdPerfil(rs.getLong("ID_PERFIL"));
                perfil.setPerfil(rs.getString("PERFIL"));
                perfil.setMenu(rs.getString("MENU"));
                
                Pass pass = new Pass();
                pass.setIdPass(rs.getLong("ID_PASS"));
                pass.setIdUser(rs.getLong("ID_USER"));
                pass.setPass(rs.getString("PASS"));
                
                user = new User();
                user.setIdUser(rs.getLong("ID_USER"));
                user.setPerfil(perfil);
                user.setPass(pass);
                user.setNome(rs.getString("NOME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setSexo(rs.getString("SEXO"));
                user.setDtNasc(rs.getString("DTNASC"));
                user.setDtInsert(rs.getString("DTINSERT"));
                user.setSituacao(rs.getString("SITUACAO"));
                user.setListPermissao(new PermissaoDAO(connection).listar(rs.getLong("ID_USER")));
            }

            return user;
        } 
        catch (Exception e) {
            
            throw e;
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
    
    public User buscarPorId(String idUser) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        User user = null;

        try {
            
            stmt = connection.prepareStatement(
            "SELECT    TB_USER.ID_USER, " +
            "          TB_USER.NOME, " +
            "          TB_USER.EMAIL, " +
            "          TB_USER.SEXO, " +
            "          CHAR(TB_USER.DTNASC,'DD/MM/YYYY') AS DTNASC, " +        
            "          CHAR(TB_USER.DTINSERT,'DD/MM/YYYY') AS DTINSERT, " +
            "          TB_USER.SITUACAO, " +
            "          TB_PERFIL.ID_PERFIL, " +
            "          TB_PERFIL.PERFIL, " +
            "          TB_PERFIL.MENU, " +
            "          TB_PASS.ID_PASS, " +
            "          TB_PASS.PASS " +
            "FROM      TB_USER " +
            "LEFT JOIN TB_PERFIL " +
            "ON        TB_USER.ID_PERFIL = TB_PERFIL.ID_PERFIL " +
            "LEFT JOIN TB_PASS " +
            "ON        TB_USER.ID_USER = TB_PASS.ID_USER " +
            "WHERE     TB_USER.ID_USER = ? "
            );
            
            stmt.setString(1, idUser);
            
            rs = stmt.executeQuery();

            if (rs.next()) {
                
                Perfil perfil = new Perfil();
                perfil.setIdPerfil(rs.getLong("ID_PERFIL"));
                perfil.setPerfil(rs.getString("PERFIL"));
                perfil.setMenu(rs.getString("MENU"));
                
                Pass pass = new Pass();
                pass.setIdPass(rs.getLong("ID_PASS"));
                pass.setIdUser(rs.getLong("ID_USER"));
                pass.setPass(rs.getString("PASS"));

                user = new User();
                user.setIdUser(rs.getLong("ID_USER"));
                user.setPerfil(perfil);
                user.setPass(pass);
                user.setNome(rs.getString("NOME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setSexo(rs.getString("SEXO"));
                user.setDtNasc(rs.getString("DTNASC"));
                user.setDtInsert(rs.getString("DTINSERT"));
                user.setSituacao(rs.getString("SITUACAO"));
            }

            return user;
        } 
        catch (Exception e) {
            
            throw e;
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
    
    public List<User> listarNome(String q) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<User> list = new ArrayList<User>();

        try {

            stmt = connection.prepareStatement(
                    "  SELECT TB_USER.ID_USER, TB_USER.NOME "
                    + "    FROM TB_USER "
                    + "   WHERE TB_USER.NOME LIKE '%"+q+"%' " 
                    + "ORDER BY TB_USER.NOME "
            );
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                User user = new User();

                user.setIdUser(rs.getLong("ID_USER"));
                user.setNome(rs.getString("NOME"));

                list.add(user);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

//    public static void main(String[] args) throws SQLException {
// 
//        Connection conn = ConnectionFactory.getConnection();
//        
//        UserDAO userDAO = new UserDAO(conn);
//        //User user = userDAO.buscarPorId("2");
//        User user = userDAO.login("wagnersoarescamargo@gmail.com", "cama");
//        System.out.println("usuario: " + user.getNome());
//        System.out.println("perfil: " + user.getPerfil().getPerfil());
//        System.out.println("pass: " + user.getPass().getPass());
//        
//        conn.close();
//    }
}