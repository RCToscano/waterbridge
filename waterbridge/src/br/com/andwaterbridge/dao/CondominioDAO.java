package br.com.andwaterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.andwaterbridge.modelo.Condominio;
import br.com.waterbridge.auxiliar.Auxiliar;

public class CondominioDAO {
    
    private Connection connection;

    public CondominioDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public List<Condominio> listarParaProgramador() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Condominio> listCondominio = new ArrayList<Condominio>();
        
        try {
            
            stmt = connection.prepareStatement(
	    	"SELECT   TB_CONDOMINIO.ID_CONDOMINIO, " +
			"		  TB_CONDOMINIO.ID_EMPRESA, " +
	    	"         TB_CONDOMINIO.ID_USER, " +
	    	"         TB_CONDOMINIO.NOME, " +
	    	"         TB_CONDOMINIO.ENDERECO, " +
	    	"         TB_CONDOMINIO.NUMERO, " +
	    	"         TB_CONDOMINIO.COMPL, " +
	    	"         TB_CONDOMINIO.MUNICIPIO, " +
	        "         TB_CONDOMINIO.UF, " +
	    	"         TB_CONDOMINIO.CEP, " +
	    	"         TB_CONDOMINIO.COORDX, " +
	    	"         TB_CONDOMINIO.COORDY, " +
	    	"         TB_CONDOMINIO.SITUACAO, " +
	    	"         TB_CONDOMINIO.DTINSERT " +
	    	"FROM     TB_CONDOMINIO " +		
	    	"ORDER BY NOME, " +
	    	"         ENDERECO, " + 
	    	"         NUMERO, " +
	    	"         COMPL "   	
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	Condominio condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	condominio.setIdUser(rs.getLong("ID_USER"));
            	condominio.setNome(rs.getString("NOME"));
            	condominio.setEndereco(rs.getString("ENDERECO"));
            	condominio.setNumero(rs.getLong("NUMERO"));
            	condominio.setCompl(rs.getString("COMPL"));
            	condominio.setMunicipio(rs.getString("MUNICIPIO"));
            	condominio.setUf(rs.getString("UF"));
            	condominio.setCep(rs.getString("CEP"));
            	condominio.setCoordX(rs.getString("COORDX"));
            	condominio.setCoordY(rs.getString("COORDY"));
            	condominio.setSituacao(rs.getString("SITUACAO"));
            	condominio.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));     
            	
            	listCondominio.add(condominio);
            }
            
            return listCondominio;
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
    
    public List<Condominio> listarParaGerente() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Condominio> listCondominio = new ArrayList<Condominio>();
        
        try {
            
            stmt = connection.prepareStatement(
	    	"SELECT   TB_CONDOMINIO.ID_CONDOMINIO, " +
			"		  TB_CONDOMINIO.ID_EMPRESA, " +
	    	"         TB_CONDOMINIO.ID_USER, " +
	    	"         TB_CONDOMINIO.NOME, " +
	    	"         TB_CONDOMINIO.ENDERECO, " +
	    	"         TB_CONDOMINIO.NUMERO, " +
	    	"         TB_CONDOMINIO.COMPL, " +
	    	"         TB_CONDOMINIO.MUNICIPIO, " +
	        "         TB_CONDOMINIO.UF, " +
	    	"         TB_CONDOMINIO.CEP, " +
	    	"         TB_CONDOMINIO.COORDX, " +
	    	"         TB_CONDOMINIO.COORDY, " +
	    	"         TB_CONDOMINIO.SITUACAO, " +
	    	"         TB_CONDOMINIO.DTINSERT " +
	    	"FROM     TB_CONDOMINIO " +		
	    	"ORDER BY NOME, " +
	    	"         ENDERECO, " + 
	    	"         NUMERO, " +
	    	"         COMPL "   	
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	Condominio condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	condominio.setIdUser(rs.getLong("ID_USER"));
            	condominio.setNome(rs.getString("NOME"));
            	condominio.setEndereco(rs.getString("ENDERECO"));
            	condominio.setNumero(rs.getLong("NUMERO"));
            	condominio.setCompl(rs.getString("COMPL"));
            	condominio.setMunicipio(rs.getString("MUNICIPIO"));
            	condominio.setUf(rs.getString("UF"));
            	condominio.setCep(rs.getString("CEP"));
            	condominio.setCoordX(rs.getString("COORDX"));
            	condominio.setCoordY(rs.getString("COORDY"));
            	condominio.setSituacao(rs.getString("SITUACAO"));
            	condominio.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));     
            	
            	listCondominio.add(condominio);
            }
            
            return listCondominio;
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
    
    public List<Condominio> listarParaRepresentante(Long idUser) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Condominio> listCondominio = new ArrayList<Condominio>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT    TB_CONDOMINIO.ID_CONDOMINIO, " +
			"		   TB_CONDOMINIO.ID_EMPRESA, " +
	    	"          TB_CONDOMINIO.ID_USER, " +
	    	"          TB_CONDOMINIO.NOME, " +
	    	"          TB_CONDOMINIO.ENDERECO, " +
	    	"          TB_CONDOMINIO.NUMERO, " +
	    	"          TB_CONDOMINIO.COMPL, " +
	    	"          TB_CONDOMINIO.MUNICIPIO, " +
	        "          TB_CONDOMINIO.UF, " +
	    	"          TB_CONDOMINIO.CEP, " +
	    	"          TB_CONDOMINIO.COORDX, " +
	    	"          TB_CONDOMINIO.COORDY, " +
	    	"          TB_CONDOMINIO.SITUACAO, " +
	    	"          TB_CONDOMINIO.DTINSERT " +
    		"FROM      TB_USEREMPRESA " +
    		"LEFT JOIN TB_CONDOMINIO " +
    		"ON        TB_USEREMPRESA.ID_EMPRESA = TB_CONDOMINIO.ID_EMPRESA " +
    		"WHERE     TB_USEREMPRESA.ID_USER = ? " +
    		"AND       TB_CONDOMINIO.ID_CONDOMINIO IS NOT NULL " +
    		"GROUP BY  ID_CONDOMINIO, " +
			"		   ID_EMPRESA, " +
	    	"          ID_USER, " +
	    	"          NOME, " +
	    	"          ENDERECO, " +
	    	"          NUMERO, " +
	    	"          COMPL, " +
	    	"          MUNICIPIO, " +
	        "          UF, " +
	    	"          CEP, " +
	    	"          COORDX, " +
	    	"          COORDY, " +
	    	"          SITUACAO, " +
	    	"          DTINSERT " +
	    	"ORDER BY  NOME, " +
	    	"          ENDERECO, " + 
	    	"          NUMERO, " +
	    	"          COMPL "   	
            );
            
            stmt.setObject(1, idUser);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	Condominio condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	condominio.setIdUser(rs.getLong("ID_USER"));
            	condominio.setNome(rs.getString("NOME"));
            	condominio.setEndereco(rs.getString("ENDERECO"));
            	condominio.setNumero(rs.getLong("NUMERO"));
            	condominio.setCompl(rs.getString("COMPL"));
            	condominio.setMunicipio(rs.getString("MUNICIPIO"));
            	condominio.setUf(rs.getString("UF"));
            	condominio.setCep(rs.getString("CEP"));
            	condominio.setCoordX(rs.getString("COORDX"));
            	condominio.setCoordY(rs.getString("COORDY"));
            	condominio.setSituacao(rs.getString("SITUACAO"));
            	condominio.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));     
            	
            	listCondominio.add(condominio);
            }
            
            return listCondominio;
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
    
    public List<Condominio> listarParaAdministradorLocal(Long idUser) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Condominio> listCondominio = new ArrayList<Condominio>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT    TB_CONDOMINIO.ID_CONDOMINIO, " +
			"		   TB_CONDOMINIO.ID_EMPRESA, " +
	    	"          TB_CONDOMINIO.ID_USER, " +
	    	"          TB_CONDOMINIO.NOME, " +
	    	"          TB_CONDOMINIO.ENDERECO, " +
	    	"          TB_CONDOMINIO.NUMERO, " +
	    	"          TB_CONDOMINIO.COMPL, " +
	    	"          TB_CONDOMINIO.MUNICIPIO, " +
	        "          TB_CONDOMINIO.UF, " +
	    	"          TB_CONDOMINIO.CEP, " +
	    	"          TB_CONDOMINIO.COORDX, " +
	    	"          TB_CONDOMINIO.COORDY, " +
	    	"          TB_CONDOMINIO.SITUACAO, " +
	    	"          TB_CONDOMINIO.DTINSERT " +
	    	"FROM      TB_USERCONDOMINIO " +          
	    	"LEFT JOIN TB_CONDOMINIO " +
	    	"ON        TB_USERCONDOMINIO.ID_CONDOMINIO = TB_CONDOMINIO.ID_CONDOMINIO " +
	    	"WHERE     TB_USERCONDOMINIO.ID_USER = ? " +
	    	"AND       TB_CONDOMINIO.ID_CONDOMINIO IS NOT NULL " +
    		"GROUP BY  ID_CONDOMINIO, " +
			"		   ID_EMPRESA, " +
	    	"          ID_USER, " +
	    	"          NOME, " +
	    	"          ENDERECO, " +
	    	"          NUMERO, " +
	    	"          COMPL, " +
	    	"          MUNICIPIO, " +
	        "          UF, " +
	    	"          CEP, " +
	    	"          COORDX, " +
	    	"          COORDY, " +
	    	"          SITUACAO, " +
	    	"          DTINSERT " +
	    	"ORDER BY  NOME, " +
	    	"          ENDERECO, " + 
	    	"          NUMERO, " +
	    	"          COMPL "   	
            );
            
            stmt.setObject(1, idUser);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	Condominio condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	condominio.setIdUser(rs.getLong("ID_USER"));
            	condominio.setNome(rs.getString("NOME"));
            	condominio.setEndereco(rs.getString("ENDERECO"));
            	condominio.setNumero(rs.getLong("NUMERO"));
            	condominio.setCompl(rs.getString("COMPL"));
            	condominio.setMunicipio(rs.getString("MUNICIPIO"));
            	condominio.setUf(rs.getString("UF"));
            	condominio.setCep(rs.getString("CEP"));
            	condominio.setCoordX(rs.getString("COORDX"));
            	condominio.setCoordY(rs.getString("COORDY"));
            	condominio.setSituacao(rs.getString("SITUACAO"));
            	condominio.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));     
            	
            	listCondominio.add(condominio);
            }
            
            return listCondominio;
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
    
    public List<Condominio> listarParaConsumidor(Long idUser) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Condominio> listCondominio = new ArrayList<Condominio>();
        
        try {
            
            stmt = connection.prepareStatement(
    		"SELECT    TB_CONDOMINIO.ID_CONDOMINIO, " +
			"		   TB_CONDOMINIO.ID_EMPRESA, " +
	    	"          TB_CONDOMINIO.ID_USER, " +
	    	"          TB_CONDOMINIO.NOME, " +
	    	"          TB_CONDOMINIO.ENDERECO, " +
	    	"          TB_CONDOMINIO.NUMERO, " +
	    	"          TB_CONDOMINIO.COMPL, " +
	    	"          TB_CONDOMINIO.MUNICIPIO, " +
	        "          TB_CONDOMINIO.UF, " +
	    	"          TB_CONDOMINIO.CEP, " +
	    	"          TB_CONDOMINIO.COORDX, " +
	    	"          TB_CONDOMINIO.COORDY, " +
	    	"          TB_CONDOMINIO.SITUACAO, " +
	    	"          TB_CONDOMINIO.DTINSERT " +
	    	"FROM      TB_USERMEDIDOR " +          
	    	"LEFT JOIN TB_MEDIDOR " +
	    	"ON        TB_USERMEDIDOR.ID_MEDIDOR = TB_MEDIDOR.ID_MEDIDOR " +
	    	"LEFT JOIN TB_BRIDGE " +
	    	"ON        TB_MEDIDOR.ID_BRIDGE = TB_BRIDGE.ID_BRIDGE " +
	    	"LEFT JOIN TB_CONDOMINIO " +
	    	"ON        TB_BRIDGE.ID_CONDOMINIO = TB_CONDOMINIO.ID_CONDOMINIO " +
	    	"WHERE     TB_USERMEDIDOR.ID_USER = ? " +
	    	"AND       TB_CONDOMINIO.ID_CONDOMINIO IS NOT NULL " +
    		"GROUP BY  ID_CONDOMINIO, " +
			"		   ID_EMPRESA, " +
	    	"          ID_USER, " +
	    	"          NOME, " +
	    	"          ENDERECO, " +
	    	"          NUMERO, " +
	    	"          COMPL, " +
	    	"          MUNICIPIO, " +
	        "          UF, " +
	    	"          CEP, " +
	    	"          COORDX, " +
	    	"          COORDY, " +
	    	"          SITUACAO, " +
	    	"          DTINSERT " +
	    	"ORDER BY  NOME, " +
	    	"          ENDERECO, " + 
	    	"          NUMERO, " +
	    	"          COMPL "   	
            );
            
            stmt.setObject(1, idUser);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	Condominio condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	condominio.setIdUser(rs.getLong("ID_USER"));
            	condominio.setNome(rs.getString("NOME"));
            	condominio.setEndereco(rs.getString("ENDERECO"));
            	condominio.setNumero(rs.getLong("NUMERO"));
            	condominio.setCompl(rs.getString("COMPL"));
            	condominio.setMunicipio(rs.getString("MUNICIPIO"));
            	condominio.setUf(rs.getString("UF"));
            	condominio.setCep(rs.getString("CEP"));
            	condominio.setCoordX(rs.getString("COORDX"));
            	condominio.setCoordY(rs.getString("COORDY"));
            	condominio.setSituacao(rs.getString("SITUACAO"));
            	condominio.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));     
            	
            	listCondominio.add(condominio);
            }
            
            return listCondominio;
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

