package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    		"       ID_EMPRESA, " +
    		"       DTINSERT " +
            ") VALUES ( " +
            "       ?,?,?,?,?,?,?,?,?,?, " +
            "       ?,?,?,?,?,?,?,?,?,?,SYSDATE() " +
            ")");
            
            //stmt.setObject(1, condominio.getIdCondominio());
            stmt.setObject(1, condominio.getIdUser());
            if(condominio.getCnpTp() != null) {
            	stmt.setObject(2, condominio.getCnpTp().getIdCnpTp());
            }
            else {
            	stmt.setObject(2, null);
            }            
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
            stmt.setObject(20, condominio.getIdEmpresa());
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
    		"       ID_EMPRESA = ?, " +
    		"       DTINSERT = SYSDATE() " +
    		"WHERE  ID_CONDOMINIO = ? ");
          
            stmt.setObject(1, condominio.getIdUser());            
            if(condominio.getCnpTp() != null) {
            	stmt.setObject(2, condominio.getCnpTp().getIdCnpTp());
            }
            else {
            	stmt.setObject(2, null);
            }
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
            stmt.setObject(20, condominio.getIdEmpresa());
            //stmt.setObject(20, condominio.getDtInsert());
            stmt.setObject(21, condominio.getIdCondominio());

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
    
    public Condominio buscarPorId(Long idCondominio) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Condominio condominio = null;
        
        try {
            
            stmt = connection.prepareStatement(
	    	"SELECT ID_CONDOMINIO, " +
	    	"		ID_EMPRESA, " +
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
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	condominio.setIdUser(rs.getLong("ID_USER"));
            	condominio.setCnpTp(cnpTp);
            	condominio.setNome(rs.getString("NOME"));
            	condominio.setCnp(rs.getString("CNP"));
            	condominio.setTelFixo(rs.getString("TELFIXO"));
            	condominio.setTelCel(rs.getString("TELCEL"));
            	condominio.setEmail(rs.getString("EMAIL"));
            	condominio.setEndereco(rs.getString("ENDERECO"));
            	condominio.setNumero(Auxiliar.converteLong(rs.getString("NUMERO")));
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
    
    public Condominio buscarPorCnp(String cnp) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Condominio condominio = null;
        
        try {
            
            stmt = connection.prepareStatement(
	    	"SELECT ID_CONDOMINIO, " +
			"		ID_EMPRESA, " +
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
            "WHERE  CNP = ? "		            		
            );

            stmt.setString(1, cnp);

            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	CnpTp cnpTp = new CnpTp();
            	cnpTp = new CnpTpDAO(connection).buscar(rs.getLong("ID_CNPTP"));
            	
            	condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
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
    
    public Condominio buscarPorNome(String nome) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Condominio condominio = null;
        
        try {
            
            stmt = connection.prepareStatement(
	    	"SELECT ID_CONDOMINIO, " +
			"		ID_EMPRESA, " +
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
            "WHERE  UPPER(NOME) = ? "		            		
            );

            stmt.setString(1, nome);

            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	CnpTp cnpTp = new CnpTp();
            	cnpTp = new CnpTpDAO(connection).buscar(rs.getLong("ID_CNPTP"));
            	
            	condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
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
    
    public List<Condominio> listar() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Condominio> listCondominio = new ArrayList<Condominio>();
        
        try {
            
            stmt = connection.prepareStatement(
	    	"SELECT   ID_CONDOMINIO, " +
			"		  ID_EMPRESA, " +
	    	"         ID_USER, " +
	    	"         ID_CNPTP, " +
	    	"         NOME, " +
	    	"         CNP, " +
	    	"         TELFIXO, " +
	    	"         TELCEL, " +
	    	"         EMAIL, " +
	    	"         ENDERECO, " +
	    	"         NUMERO, " +
	    	"         COMPL, " +
	    	"         MUNICIPIO, " +
	        "         UF, " +
	    	"         CEP, " +
	    	"         COORDX, " +
	    	"         COORDY, " +
	    	"         RESPONSAVEL, " +
	    	"         CONTRNUM, " +
	    	"         CONTACICLO, " +
	    	"         SITUACAO, " +
	    	"         DTINSERT " +
	    	"FROM     TB_CONDOMINIO " +		
	    	"ORDER BY NOME, " +
	    	"         ENDERECO, " + 
	    	"         NUMERO, " +
	    	"         COMPL "   	
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	CnpTp cnpTp = new CnpTp();
            	cnpTp = new CnpTpDAO(connection).buscar(rs.getLong("ID_CNPTP"));
            	
            	Condominio condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	condominio.setIdUser(rs.getLong("ID_USER"));
            	condominio.setCnpTp(cnpTp);
            	condominio.setNome(rs.getString("NOME"));
            	condominio.setCnp(rs.getString("CNP"));
            	condominio.setTelFixo(rs.getString("TELFIXO"));
            	condominio.setTelCel(rs.getString("TELCEL"));
            	condominio.setEmail(rs.getString("EMAIL"));
            	condominio.setEndereco(rs.getString("ENDERECO"));
            	condominio.setNumero(Auxiliar.converteLong(rs.getString("NUMERO")));
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
    
    public List<Condominio> listar(String sql) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Condominio> listCondominio = new ArrayList<Condominio>();
        
        try {
            
            stmt = connection.prepareStatement(
	    	"SELECT   ID_CONDOMINIO, " +
			"		  ID_EMPRESA, " +
	    	"         ID_USER, " +
	    	"         ID_CNPTP, " +
	    	"         NOME, " +
	    	"         CNP, " +
	    	"         TELFIXO, " +
	    	"         TELCEL, " +
	    	"         EMAIL, " +
	    	"         ENDERECO, " +
	    	"         NUMERO, " +
	    	"         COMPL, " +
	    	"         MUNICIPIO, " +
	        "         UF, " +
	    	"         CEP, " +
	    	"         COORDX, " +
	    	"         COORDY, " +
	    	"         RESPONSAVEL, " +
	    	"         CONTRNUM, " +
	    	"         CONTACICLO, " +
	    	"         SITUACAO, " +
	    	"         DTINSERT " +
	    	"FROM     TB_CONDOMINIO " +		
	    	sql
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	CnpTp cnpTp = new CnpTp();
            	cnpTp = new CnpTpDAO(connection).buscar(rs.getLong("ID_CNPTP"));
            	
            	Condominio condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	condominio.setIdUser(rs.getLong("ID_USER"));
            	condominio.setCnpTp(cnpTp);
            	condominio.setNome(rs.getString("NOME"));
            	condominio.setCnp(rs.getString("CNP"));
            	condominio.setTelFixo(rs.getString("TELFIXO"));
            	condominio.setTelCel(rs.getString("TELCEL"));
            	condominio.setEmail(rs.getString("EMAIL"));
            	condominio.setEndereco(rs.getString("ENDERECO"));
            	condominio.setNumero(Auxiliar.converteLong(rs.getString("NUMERO")));
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
    
    public List<Condominio> listarPorUsuario(Long idUser, Long idEmpresa) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Condominio> listCondominio = new ArrayList<Condominio>();
        
        try {
            
            stmt = connection.prepareStatement(
	    	"SELECT    TB_CONDOMINIO.ID_CONDOMINIO, " +
			"		   TB_CONDOMINIO.ID_EMPRESA, " +
	    	"          TB_CONDOMINIO.ID_USER, " +
	    	"          TB_CONDOMINIO.ID_CNPTP, " +
	    	"          TB_CONDOMINIO.NOME, " +
	    	"          TB_CONDOMINIO.CNP, " +
	    	"          TB_CONDOMINIO.TELFIXO, " +
	    	"          TB_CONDOMINIO.TELCEL, " +
	    	"          TB_CONDOMINIO.EMAIL, " +
	    	"          TB_CONDOMINIO.ENDERECO, " +
	    	"          TB_CONDOMINIO.NUMERO, " +
	    	"          TB_CONDOMINIO.COMPL, " +
	    	"          TB_CONDOMINIO.MUNICIPIO, " +
	        "          TB_CONDOMINIO.UF, " +
	    	"          TB_CONDOMINIO.CEP, " +
	    	"          TB_CONDOMINIO.COORDX, " +
	    	"          TB_CONDOMINIO.COORDY, " +
	    	"          TB_CONDOMINIO.RESPONSAVEL, " +
	    	"          TB_CONDOMINIO.CONTRNUM, " +
	    	"          TB_CONDOMINIO.CONTACICLO, " +
	    	"          TB_CONDOMINIO.SITUACAO, " +
	    	"          TB_CONDOMINIO.DTINSERT " +
	    	"FROM      TB_CONDOMINIO " +		
	    	"LEFT JOIN VW_USERCONDOMINIOID " +
		    "ON        TB_CONDOMINIO.ID_CONDOMINIO = VW_USERCONDOMINIOID.ID_CONDOMINIO " +
		    "WHERE     VW_USERCONDOMINIOID.ID_USER = ? " +
		    "AND       TB_CONDOMINIO.ID_EMPRESA = ? "
		    );
            
            stmt.setObject(1, idUser);
            stmt.setObject(2, idEmpresa);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	CnpTp cnpTp = new CnpTp();
            	cnpTp = new CnpTpDAO(connection).buscar(rs.getLong("ID_CNPTP"));
            	
            	Condominio condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
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
    //LISTA CONDOMINIOS QUE TENHAM PRESSURE BRIDGE OU WATER PRESSURE BRIDGE
    public List<Condominio> listarPorUsuarioPB(Long idUser, Long idEmpresa) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Condominio> listCondominio = new ArrayList<Condominio>();
        
        try {
            
            stmt = connection.prepareStatement(
	    	"SELECT    TB_CONDOMINIO.ID_CONDOMINIO, " +
			"		   TB_CONDOMINIO.ID_EMPRESA, " +
	    	"          TB_CONDOMINIO.ID_USER, " +
	    	"          TB_CONDOMINIO.ID_CNPTP, " +
	    	"          TB_CONDOMINIO.NOME, " +
	    	"          TB_CONDOMINIO.CNP, " +
	    	"          TB_CONDOMINIO.TELFIXO, " +
	    	"          TB_CONDOMINIO.TELCEL, " +
	    	"          TB_CONDOMINIO.EMAIL, " +
	    	"          TB_CONDOMINIO.ENDERECO, " +
	    	"          TB_CONDOMINIO.NUMERO, " +
	    	"          TB_CONDOMINIO.COMPL, " +
	    	"          TB_CONDOMINIO.MUNICIPIO, " +
	        "          TB_CONDOMINIO.UF, " +
	    	"          TB_CONDOMINIO.CEP, " +
	    	"          TB_CONDOMINIO.COORDX, " +
	    	"          TB_CONDOMINIO.COORDY, " +
	    	"          TB_CONDOMINIO.RESPONSAVEL, " +
	    	"          TB_CONDOMINIO.CONTRNUM, " +
	    	"          TB_CONDOMINIO.CONTACICLO, " +
	    	"          TB_CONDOMINIO.SITUACAO, " +
	    	"          TB_CONDOMINIO.DTINSERT " +
	    	"FROM      TB_CONDOMINIO " +		
	    	"LEFT JOIN VW_USERCONDOMINIOID " +
		    "ON        TB_CONDOMINIO.ID_CONDOMINIO = VW_USERCONDOMINIOID.ID_CONDOMINIO " +
	    	"LEFT JOIN VW_ID_CONDOMINIOPB " +
	    	"ON        TB_CONDOMINIO.ID_CONDOMINIO = VW_ID_CONDOMINIOPB.ID_CONDOMINIO " +
		    "WHERE     VW_USERCONDOMINIOID.ID_USER = ? " +
		    "AND       TB_CONDOMINIO.ID_EMPRESA = ? " +
		    "AND       VW_ID_CONDOMINIOPB.ID_CONDOMINIO IS NOT NULL "
		    );
            
            stmt.setObject(1, idUser);
            stmt.setObject(2, idEmpresa);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	CnpTp cnpTp = new CnpTp();
            	cnpTp = new CnpTpDAO(connection).buscar(rs.getLong("ID_CNPTP"));
            	
            	Condominio condominio = new Condominio();
            	condominio.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	condominio.setIdEmpresa(rs.getLong("ID_EMPRESA"));
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
