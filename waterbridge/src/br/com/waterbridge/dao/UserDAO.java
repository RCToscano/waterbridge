package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.Pass;
import br.com.waterbridge.modelo.Perfil;
import br.com.waterbridge.modelo.User;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {

        this.connection = connection;
    }

    public User login(String email, String senha) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        try {
            stmt = connection.prepareStatement(
            "SELECT    TB_USER.*, " +
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
            "WHERE     TB_USER.USUARIO = ? " +
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
                
                user = new User();
                user.setIdUser(rs.getLong("ID_USER"));
                user.setUsuario(rs.getString("USUARIO"));
                user.setPerfil(perfil);
                user.setNome(rs.getString("NOME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setSexo(rs.getString("SEXO"));
                user.setDtNasc(rs.getString("DTNASC"));
                user.setDtInsert(rs.getString("DTINSERT"));
                user.setSituacao(rs.getString("SITUACAO"));
                user.setDtNasc(formatoData.format(formatoBanco.parse(rs.getString("DTNASC"))));
                user.setCpf(rs.getString("CPF"));
        		user.setTelFixo(rs.getString("TELFIXO"));
        		user.setTelCel(rs.getString("TELCEL"));
        		user.setEndereco(rs.getString("ENDERECO"));
        		user.setNumero(rs.getLong("NUMERO"));
        		user.setCompl(rs.getString("COMPL"));
        		user.setMunicipio(rs.getString("MUNICIPIO"));
        		user.setUf(rs.getString("UF"));
        		user.setCep(rs.getString("CEP"));
                user.setListPermissao(new PermissaoDAO(connection).listar(rs.getLong("ID_USER")));
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
    
    public User buscarPorId(String idUser) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        try {
            stmt = connection.prepareStatement(
            "SELECT    TB_USER.*, " +
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
                user.setUsuario(rs.getString("USUARIO"));
                user.setCpf(rs.getString("CPF"));
        		user.setNome(rs.getString("NOME"));
        		user.setDtNasc(formatoData.format(formatoBanco.parse(rs.getString("DTNASC"))));
        		user.setSexo(rs.getString("SEXO"));
        		user.setTelFixo(rs.getString("TELFIXO"));
        		user.setTelCel(rs.getString("TELCEL"));
        		user.setEmail(rs.getString("EMAIL"));
        		user.setEndereco(rs.getString("ENDERECO"));
        		user.setNumero(rs.getLong("NUMERO"));
        		user.setCompl(rs.getString("COMPL"));
        		user.setMunicipio(rs.getString("MUNICIPIO"));
        		user.setUf(rs.getString("UF"));
        		user.setCep(rs.getString("CEP"));
        		user.setDtInsert(rs.getString("DTINSERT"));
        		user.setSituacao(rs.getString("SITUACAO"));
                
                user.setPerfil(perfil);
                user.setPass(pass);
            }
            return user;
        } 
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }
    
    public User buscarPorCpf(String cpf) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	User user = null;
    	SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT    TB_USER.*, " +
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
					"WHERE     TB_USER.CPF = ? " 
					//"      AND TB_PERFIL.PERFIL <> 'PROGRAMADOR' "
    				);
    		
    		stmt.setString(1, cpf);
    		
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
    			user.setUsuario(rs.getString("USUARIO"));
    			user.setCpf(rs.getString("CPF"));
    			user.setNome(rs.getString("NOME"));
    			user.setDtNasc(formatoData.format(formatoBanco.parse(rs.getString("DTNASC"))));
    			user.setSexo(rs.getString("SEXO"));
    			user.setTelFixo(rs.getString("TELFIXO"));
    			user.setTelCel(rs.getString("TELCEL"));
    			user.setEmail(rs.getString("EMAIL"));
    			user.setEndereco(rs.getString("ENDERECO"));
    			user.setNumero(rs.getLong("NUMERO"));
    			user.setCompl(rs.getString("COMPL"));
    			user.setMunicipio(rs.getString("MUNICIPIO"));
    			user.setUf(rs.getString("UF"));
    			user.setCep(rs.getString("CEP"));
    			user.setDtInsert(rs.getString("DTINSERT"));
    			user.setSituacao(rs.getString("SITUACAO"));
    			
    			user.setPerfil(perfil);
    			user.setPass(pass);
    		}
    		return user;
    	} 
    	finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }
    
    public List<User> buscarPorEndereco(String endereco) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	User user = null;
    	SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    	
    	List<User> lista = new ArrayList<>();
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT    TB_USER.*, " +
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
					"WHERE     TB_USER.ENDERECO LIKE '%"+endereco+"%' " + 
					"      AND TB_PERFIL.PERFIL <> 'PROGRAMADOR' "
    				);
    		
    		rs = stmt.executeQuery();
    		
    		while(rs.next()) {
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
    			user.setUsuario(rs.getString("USUARIO"));
    			user.setCpf(rs.getString("CPF"));
    			user.setNome(rs.getString("NOME"));
    			user.setDtNasc(formatoData.format(formatoBanco.parse(rs.getString("DTNASC"))));
    			user.setSexo(rs.getString("SEXO"));
    			user.setTelFixo(rs.getString("TELFIXO"));
    			user.setTelCel(rs.getString("TELCEL"));
    			user.setEmail(rs.getString("EMAIL"));
    			user.setEndereco(rs.getString("ENDERECO") + Auxiliar.isNull(rs.getString("NUMERO")));
    			user.setCompl(rs.getString("COMPL"));
    			user.setMunicipio(rs.getString("MUNICIPIO"));
    			user.setUf(rs.getString("UF"));
    			user.setCep(rs.getString("CEP"));
    			user.setDtInsert(rs.getString("DTINSERT"));
    			user.setSituacao(rs.getString("SITUACAO"));
    			
    			user.setPerfil(perfil);
    			user.setPass(pass);
    			lista.add(user);
    		}
    		return lista;
    	} 
    	finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }
    
    public List<User> buscarPorNome(String nome) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	User user = null;
    	SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    	
    	List<User> lista = new ArrayList<>();
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT    TB_USER.*, " +
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
					"WHERE     TB_USER.NOME LIKE '%"+nome+"%' " + 
					"      AND TB_PERFIL.PERFIL <> 'PROGRAMADOR' "
    				);
    		
    		rs = stmt.executeQuery();
    		
    		while(rs.next()) {
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
    			user.setUsuario(rs.getString("USUARIO"));
    			user.setCpf(rs.getString("CPF"));
    			user.setNome(rs.getString("NOME"));
    			user.setDtNasc(formatoData.format(formatoBanco.parse(rs.getString("DTNASC"))));
    			user.setSexo(rs.getString("SEXO"));
    			user.setTelFixo(rs.getString("TELFIXO"));
    			user.setTelCel(rs.getString("TELCEL"));
    			user.setEmail(rs.getString("EMAIL"));
    			user.setEndereco(rs.getString("ENDERECO") + Auxiliar.isNull(rs.getString("NUMERO")));
    			user.setCompl(rs.getString("COMPL"));
    			user.setMunicipio(rs.getString("MUNICIPIO"));
    			user.setUf(rs.getString("UF"));
    			user.setCep(rs.getString("CEP"));
    			user.setDtInsert(rs.getString("DTINSERT"));
    			user.setSituacao(rs.getString("SITUACAO"));
    			
    			user.setPerfil(perfil);
    			user.setPass(pass);
    			lista.add(user);
    		}
    		return lista;
    	} 
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }
    
    public List<User> buscarPorPerfil(String idPerfil) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	User user = null;
    	SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    	
    	List<User> lista = new ArrayList<>();
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT    TB_USER.*, " +
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
					"WHERE     TB_PERFIL.ID_PERFIL = "+idPerfil+" " + 
					"      AND TB_PERFIL.PERFIL <> 'PROGRAMADOR' "
    				);
    		
    		rs = stmt.executeQuery();
    		
    		while(rs.next()) {
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
    			user.setUsuario(rs.getString("USUARIO"));
    			user.setCpf(rs.getString("CPF"));
    			user.setNome(rs.getString("NOME"));
    			user.setDtNasc(formatoData.format(formatoBanco.parse(rs.getString("DTNASC"))));
    			user.setSexo(rs.getString("SEXO"));
    			user.setTelFixo(rs.getString("TELFIXO"));
    			user.setTelCel(rs.getString("TELCEL"));
    			user.setEmail(rs.getString("EMAIL"));
    			user.setEndereco(rs.getString("ENDERECO") + Auxiliar.isNull(rs.getString("NUMERO")));
    			user.setCompl(rs.getString("COMPL"));
    			user.setMunicipio(rs.getString("MUNICIPIO"));
    			user.setUf(rs.getString("UF"));
    			user.setCep(rs.getString("CEP"));
    			user.setCoordx(rs.getString("COORDX"));
    			user.setCoordy(rs.getString("COORDY"));
    			user.setDtInsert(rs.getString("DTINSERT"));
    			user.setSituacao(rs.getString("SITUACAO"));
    			
    			user.setPerfil(perfil);
    			user.setPass(pass);
    			lista.add(user);
    		}
    		return lista;
    	} 
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
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
        } 
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
        return list;
    }
    
    public void inserir(User user) throws Exception {
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	stmt = connection.prepareStatement(
    				" INSERT INTO TB_USER ( " +
					"ID_PERFIL, " +
					"NOME, " +
					"EMAIL, " +
					"SEXO, " +
					"DTNASC, " +
					"SITUACAO, " +
					"USUARIO, " +
					"CPF, " +
					"TELFIXO, " +
					"ENDERECO, " +
					"NUMERO, " +
					"COMPL, " +
					"MUNICIPIO, " +
					"UF, " +
					"CEP, " +
					"TELCEL, " +
					"COORDX, " +
					"COORDY, " +
					"DTINSERT " +
					")" +
    				" VALUES ( " +
            		"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " + 
					"?, ?, ?, ?, ?, ?, sysdate() ) " 
    				);
            		
            stmt.setObject(1, user.getPerfil().getIdPerfil());
            stmt.setObject(2, user.getNome());
            stmt.setObject(3, user.getEmail());
            stmt.setObject(4, user.getSexo());
            stmt.setObject(5, user.getDtNasc());
            stmt.setObject(6, user.getSituacao());
            stmt.setObject(7, user.getUsuario());
            stmt.setObject(8, user.getCpf());
            stmt.setObject(9, user.getTelFixo());
            stmt.setObject(10, user.getEndereco());
            stmt.setObject(11, user.getNumero());
            stmt.setObject(12, user.getCompl());
            stmt.setObject(13, user.getMunicipio());
            stmt.setObject(14, user.getUf());
            stmt.setObject(15, user.getCep());
            stmt.setObject(16, user.getTelCel());
            stmt.setObject(17, user.getCoordx());
            stmt.setObject(18, user.getCoordy());
            stmt.executeUpdate();
		} 
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }
    
    public void alterar(User user) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		
    		//LOGA REGISTRO ANTES DE ALTERAR
            logar(user.getIdUser());
    		
    		stmt = connection.prepareStatement(
    					" UPDATE TB_USER SET " +
						"ID_PERFIL = ?, " +
						"NOME = ?, " +
						"EMAIL = ?, " +
						"SEXO = ?, " +
						"DTNASC = ?, " +
						"SITUACAO = ?, " +
						"USUARIO = ?, " +
						"CPF = ?, " +
						"TELFIXO = ?, " +
						"ENDERECO = ?, " +
						"NUMERO = ?, " +
						"COMPL = ?, " +
						"MUNICIPIO = ?, " +
						"UF = ?, " +
						"CEP = ?, " +
						"TELCEL = ?, " +
						"COORDX = ?, " +
						"COORDY = ?, " +
						"DTINSERT = sysdate() " +
						"WHERE ID_USER = ? "
    				);
    		
    		stmt.setObject(1, user.getPerfil().getIdPerfil());
    		stmt.setObject(2, user.getNome());
    		stmt.setObject(3, user.getEmail());
    		stmt.setObject(4, user.getSexo());
    		stmt.setObject(5, user.getDtNasc());
    		stmt.setObject(6, user.getSituacao());
    		stmt.setObject(7, user.getUsuario());
    		stmt.setObject(8, user.getCpf());
    		stmt.setObject(9, user.getTelFixo());
    		stmt.setObject(10, user.getEndereco());
    		stmt.setObject(11, user.getNumero());
    		stmt.setObject(12, user.getCompl());
    		stmt.setObject(13, user.getMunicipio());
    		stmt.setObject(14, user.getUf());
    		stmt.setObject(15, user.getCep());
    		stmt.setObject(16, user.getTelCel());
    		stmt.setObject(17, user.getCoordx());
    		stmt.setObject(18, user.getCoordy());
    		stmt.setObject(19, user.getIdUser());
    		stmt.executeUpdate();
    	} 
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }
    
    public void logar(Long idUser) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
            "INSERT INTO TB_USERLOG " +
            "SELECT * " +
            "FROM   TB_USER " +
            "WHERE  ID_USER = ? "
            );

            stmt.setLong(1, idUser);
            stmt.executeUpdate();
        }
        finally {
            if(stmt != null)
                stmt.close();
        }
    }
    
    public User buscarUltimo() throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(
            "SELECT    MAX(TB_USER.ID_USER) AS ID_USER " +
            "FROM      TB_USER "
            );
            rs = stmt.executeQuery();

            User user = new User();
            if(rs.next()) {
            	user.setIdUser(rs.getLong("ID_USER"));
            }
            return user;
        }
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }
    
    public List<User> listar(String sql) throws Exception {
    	
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<>();
        
        try {
            
        	stmt = connection.prepareStatement(
			"SELECT ID_USER, " +
    		"       ID_PERFIL, " +
    		"       NOME, " +
    		"       EMAIL, " +
    		"       SEXO, " +
    		"       DTNASC, " +
    		"       DTINSERT, " +
    		"       SITUACAO, " +
    		"       USUARIO, " +
    		"       CPF, " +
    		"       TELFIXO, " +
    		"       ENDERECO, " +
    		"       NUMERO, " +
    		"       COMPL, " +
    		"       MUNICIPIO, " +
    		"       UF, " +
    		"       CEP, " +
    		"       COORDX, " +
    		"       COORDY, " +
    		"       TELCEL " +
    		"FROM   TB_USER " +		
    		sql
            );
            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	PerfilDAO perfilDAO = new PerfilDAO(connection);
            	Perfil perfil = perfilDAO.buscarPorId(rs.getLong("ID_PERFIL"));
            	
            	PassDAO passDAO = new PassDAO(connection);
            	Pass pass = passDAO.buscarPorIdUser(rs.getLong("ID_USER"));
            	
                User user = new User();
                user.setIdUser(rs.getLong("ID_USER"));
                user.setPerfil(perfil);
                user.setPass(pass);
                user.setUsuario(rs.getString("USUARIO"));
                user.setNome(rs.getString("NOME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setSexo(rs.getString("SEXO"));
                user.setDtNasc(Auxiliar.formataDtTela(rs.getString("DTNASC")));
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
                user.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
                user.setSituacao(rs.getString("SITUACAO"));
               
                list.add(user);
            }
            return list;
        } 
        finally {
            if (stmt != null)
                stmt.close();
            if (rs != null)
                rs.close();
        }
    }
    
    public List<User> listarTodos() throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(
                    "   SELECT TB_USER.ID_USER, " +
                    "          TB_USER.NOME " +
                    "     FROM TB_USER " +
                    "LEFT JOIN TB_PERFIL " +
					"       ON TB_USER.ID_PERFIL = TB_PERFIL.ID_PERFIL " +
                    "    WHERE TB_PERFIL.PERFIL <> 'PROGRAMADOR' " +
                    " ORDER BY TB_USER.NOME "
            );
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getLong("ID_USER"));
                user.setNome(rs.getString("NOME"));
                list.add(user);
            }
            return list;
        } 
        finally {
            if (stmt != null)
                stmt.close();
            if (rs != null)
                rs.close();
        }
    }
    
}
