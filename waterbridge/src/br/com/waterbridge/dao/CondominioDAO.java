package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.CnpTp;
import br.com.waterbridge.modelo.Condominio;

public class CondominioDAO {
    
    private Connection connection;

    public CondominioDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(Condominio condominio) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_CONDOMINIO ( " +
    		//"       ID_CONDOMINIO, " +
    		"       ID_USER, " +
    		"       ID_CNPTP, " +
    		"       NOME, " +
    		"       CNP, " +
    		"       TELFIXO, " +
    		"       TELCEL, " +
    		"       EMAIL, " +
    		"       ENDERECO, " +
    		"       NUMERO, " +
    		"       COMPL, " +
    		"       MUNICIPIO, " +
    		"       UF, " +
    		"       CEP, " +
    		"       COORDX, " +
    		"       COORDY, " +
    		"       RESPONSAVEL, " +
    		"       CONTRNUM, " +
    		"       CONTACICLO, " +
    		"       SITUACAO, " +
    		"       DTINSERT " +
            ") VALUES ( " +
            "       ?,?,?,?,?,?,?,?,?,?, " +
            "       ?,?,?,?,?,?,?,?,?,SYSDATE() " +
            ")");
            
            //stmt.setObject(1, condominio.getIdCondominio());
            stmt.setObject(1, condominio.getIdUser());
            stmt.setObject(2, condominio.getCnpTp().getIdCnpTp());
            stmt.setObject(3, condominio.getNome());
            stmt.setObject(4, condominio.getCnp());
            stmt.setObject(5, condominio.getTelFixo());
            stmt.setObject(6, condominio.getTelCel());
            stmt.setObject(7, condominio.getEmail());
            stmt.setObject(8, condominio.getEndereco());
            stmt.setObject(9, condominio.getNumero());
            stmt.setObject(10, condominio.getCompl());
            stmt.setObject(11, condominio.getMunicipio());
            stmt.setObject(12, condominio.getUf());
            stmt.setObject(13, condominio.getCep());
            stmt.setObject(14, condominio.getCoordX());
            stmt.setObject(15, condominio.getCoordY());
            stmt.setObject(16, condominio.getResponsavel());
            stmt.setObject(17, condominio.getContratoNum());
            stmt.setObject(18, condominio.getContaCiclo());
            stmt.setObject(19, condominio.getSituacao());
            //stmt.setObject(20, condominio.getDtInsert());
            
            stmt.execute();
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
    
    public void alterar(Condominio condominio) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
            
            //LOGA REGISTRO ANTES DE ALTERAR
            logar(condominio.getIdCondominio());
        
            stmt = connection.prepareStatement(
            "UPDATE TB_CONDOMINIO SET " +    		
    		"       ID_USER = ?, " +
    		"       ID_CNPTP = ?, " +
    		"       NOME = ?, " +
    		"       CNP = ?, " +
    		"       TELFIXO = ?, " +
    		"       TELCEL = ?, " +
    		"       EMAIL = ?, " +
    		"       ENDERECO = ?, " +
    		"       NUMERO = ?, " +
    		"       COMPL = ?, " +
    		"       MUNICIPIO = ?, " +
    		"       UF = ?, " +
    		"       CEP = ?, " +
    		"       COORDX = ?, " +
    		"       COORDY = ?, " +
    		"       RESPONSAVEL = ?, " +
    		"       CONTRNUM = ?, " +
    		"       CONTACICLO = ?, " +
    		"       SITUACAO = ?, " +
    		"       DTINSERT = SYSDATE() " +
    		"WHERE  ID_CONDOMINIO = ? ");
          
            stmt.setObject(1, condominio.getIdUser());
            stmt.setObject(2, condominio.getCnpTp().getIdCnpTp());
            stmt.setObject(3, condominio.getNome());
            stmt.setObject(4, condominio.getCnp());
            stmt.setObject(5, condominio.getTelFixo());
            stmt.setObject(6, condominio.getTelCel());
            stmt.setObject(7, condominio.getEmail());
            stmt.setObject(8, condominio.getEndereco());
            stmt.setObject(9, condominio.getNumero());
            stmt.setObject(10, condominio.getCompl());
            stmt.setObject(11, condominio.getMunicipio());
            stmt.setObject(12, condominio.getUf());
            stmt.setObject(13, condominio.getCep());
            stmt.setObject(14, condominio.getCoordX());
            stmt.setObject(15, condominio.getCoordY());
            stmt.setObject(16, condominio.getResponsavel());
            stmt.setObject(17, condominio.getContratoNum());
            stmt.setObject(18, condominio.getContaCiclo());
            stmt.setObject(19, condominio.getSituacao());
            //stmt.setObject(20, condominio.getDtInsert());
            stmt.setObject(20, condominio.getIdCondominio());

            stmt.execute();
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

    public void logar(Long idCondominio) throws SQLException {

        PreparedStatement stmt = null;
        
        try {
            
            stmt = connection.prepareStatement(
            "INSERT INTO TB_CONDOMINIOLOG " +
            "SELECT * " +
            "FROM   TB_CONDOMINIO " +
            "WHERE  ID_CONDOMINIO = ? "
            );

            stmt.setLong(1, idCondominio);
            
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
    
    public Condominio buscar(Long idCondominio) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Condominio condominio = null;
        
        try {
            
            stmt = connection.prepareStatement(
	    	"SELECT ID_CONDOMINIO, " +
	    	"       ID_USER, " +
	    	"       ID_CNPTP, " +
	    	"       NOME, " +
	    	"       CNP, " +
	    	"       TELFIXO, " +
	    	"       TELCEL, " +
	    	"       EMAIL, " +
	    	"       ENDERECO, " +
	    	"       NUMERO, " +
	    	"       COMPL, " +
	    	"       MUNICIPIO, " +
	        "       UF, " +
	    	"       CEP, " +
	    	"       COORDX, " +
	    	"       COORDY, " +
	    	"       RESPONSAVEL, " +
	    	"       CONTRNUM, " +
	    	"       CONTACICLO, " +
	    	"       SITUACAO, " +
	    	"       DTINSERT " +
	    	"FROM   TB_CONDOMINIO " +		
            "WHERE  ID_CONDOMINIO = ? "		            		
            );

            stmt.setLong(1, idCondominio);

            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	CnpTp cnpTp = new CnpTp();
            	cnpTp = new CnpTpDAO(connection).buscar(rs.getLong("ID_CNPTP"));
            	
            	condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdUser(rs.getLong("ID_USER"));
            	condominio.setCnpTp(cnpTp);
            	condominio.setNome(rs.getString("NOME"));
            	condominio.setCnp(rs.getString("CNP"));
            	condominio.setTelFixo(rs.getString("TELFIXO"));
            	condominio.setTelCel(rs.getString("TELCEL"));
            	condominio.setEmail(rs.getString("EMAIL"));
            	condominio.setEndereco(rs.getString("ENDERECO"));
            	condominio.setNumero(rs.getLong("NUMERO"));
            	condominio.setCompl(rs.getString("COMPL"));
            	condominio.setMunicipio(rs.getString("MUNICIPIO"));
            	condominio.setUf(rs.getString("UF"));
            	condominio.setCep(rs.getString("CEP"));
            	condominio.setCoordX(rs.getString("COORDX"));
            	condominio.setCoordY(rs.getString("COORDY"));
            	condominio.setResponsavel(rs.getString("RESPONSAVEL"));
            	condominio.setContratoNum(rs.getString("CONTRNUM"));
            	condominio.setContaCiclo(rs.getLong("CONTACICLO"));
            	condominio.setSituacao(rs.getString("SITUACAO"));
            	condominio.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));            	                
            }
            
            return condominio;
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
