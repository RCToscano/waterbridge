
package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.Empresa;

public class EmpresaDAO {
    
    private Connection connection;

    public EmpresaDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    
    public void inserir(Empresa empresa) throws Exception {
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	stmt = connection.prepareStatement(
    				"INSERT INTO TB_EMPRESA ( " +
					"ID_USER, " +
				    "NOME, " +
				    "CNP, " +
				    "TELFIXO, " +
				    "TELCEL, " +
				    "RESPONSAVEL, " +
				    "EMAIL, " +
				    "ENDERECO, " +
				    "NUMERO, " +
				    "COMPL, " +
				    "MUNICIPIO, " +
				    "UF, " +
				    "CEP, " +
				    "COORDX, " +
				    "COORDY, " +
				    "LOGOPDIR, " +
				    "LOGOPNOME, " +
				    "SITUACAO, " +
				    "OBS, " +
					"DTINSERT " +
					")" +
    				" VALUES ( " +
            		"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            		"?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate() ) " 
    				);
            		
            stmt.setObject(1, empresa.getIdUser());
            stmt.setObject(2, empresa.getNome());
            stmt.setObject(3, empresa.getCnp());
            stmt.setObject(4, empresa.getTelFixo());
            stmt.setObject(5, empresa.getTelCel());
            stmt.setObject(6, empresa.getResponsavel());
            stmt.setObject(7, empresa.getEmail());
            stmt.setObject(8, empresa.getEndereco());
            stmt.setObject(9, empresa.getNumero());
            stmt.setObject(10, empresa.getCompl());
            stmt.setObject(11, empresa.getMunicipio());
            stmt.setObject(12, empresa.getUf());
            stmt.setObject(13, empresa.getCep());
            stmt.setObject(14, empresa.getCoordX());
            stmt.setObject(15, empresa.getCoordY());
            stmt.setObject(16, empresa.getLogoPDir());
            stmt.setObject(17, empresa.getLogoPNome());
            stmt.setObject(18, empresa.getSituacao());
            stmt.setObject(19, empresa.getObs());
            stmt.executeUpdate();
		} 
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }
    
    public void alterar(Empresa empresa) throws Exception {
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	
        	//LOGA REGISTRO ANTES DE ALTERAR
            logar(empresa.getIdEmpresa());
        	
        	stmt = connection.prepareStatement(
    				"UPDATE TB_EMPRESA SET " +
					"ID_USER = ?, " +
				    "NOME = ?, " +
				    "CNP = ?, " +
				    "TELFIXO = ?, " +
				    "TELCEL = ?, " +
				    "RESPONSAVEL = ?, " +
				    "EMAIL = ?, " +
				    "ENDERECO = ?, " +
				    "NUMERO = ?, " +
				    "COMPL = ?, " +
				    "MUNICIPIO = ?, " +
				    "UF = ?, " +
				    "CEP = ?, " +
				    "COORDX = ?, " +
				    "COORDY = ?, " +
				    "LOGOPDIR = ?, " +
				    "LOGOPNOME = ?, " +
				    "SITUACAO = ?, " +
				    "OBS = ?, " +
					"DTINSERT = sysdate() " +
					"WHERE ID_EMPRESA = ? "
    				);
            		
            stmt.setObject(1, empresa.getIdUser());
            stmt.setObject(2, empresa.getNome());
            stmt.setObject(3, empresa.getCnp());
            stmt.setObject(4, empresa.getTelFixo());
            stmt.setObject(5, empresa.getTelCel());
            stmt.setObject(6, empresa.getResponsavel());
            stmt.setObject(7, empresa.getEmail());
            stmt.setObject(8, empresa.getEndereco());
            stmt.setObject(9, empresa.getNumero());
            stmt.setObject(10, empresa.getCompl());
            stmt.setObject(11, empresa.getMunicipio());
            stmt.setObject(12, empresa.getUf());
            stmt.setObject(13, empresa.getCep());
            stmt.setObject(14, empresa.getCoordX());
            stmt.setObject(15, empresa.getCoordY());
            stmt.setObject(16, empresa.getLogoPDir());
            stmt.setObject(17, empresa.getLogoPNome());
            stmt.setObject(18, empresa.getSituacao());
            stmt.setObject(19, empresa.getObs());
            stmt.setObject(20, empresa.getIdEmpresa());
            stmt.executeUpdate();
		} 
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }
    
    public void logar(Long idEmpresa) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
            "INSERT INTO TB_EMPRESALOG " +
            "SELECT * " +
            "FROM   TB_EMPRESA " +
            "WHERE  ID_EMPRESA = ? "
            );

            stmt.setLong(1, idEmpresa);
            stmt.executeUpdate();
        }
        finally {
            if(stmt != null)
                stmt.close();
        }
    }

    public List<Empresa> listar() throws SQLException {
        
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Empresa> listEmpresa = new ArrayList<Empresa>();
        
        try {
            stmt = connection.prepareStatement(
		    "SELECT    TB_EMPRESA.ID_EMPRESA, " +
		    "          TB_EMPRESA.ID_USER, " +
		    "          TB_EMPRESA.NOME, " +
		    "          TB_EMPRESA.CNP, " +
		    "          TB_EMPRESA.TELFIXO, " +
		    "          TB_EMPRESA.TELCEL, " +
		    "          TB_EMPRESA.RESPONSAVEL, " +
		    "          TB_EMPRESA.EMAIL, " +
		    "          TB_EMPRESA.ENDERECO, " +
		    "          TB_EMPRESA.NUMERO, " +
		    "          TB_EMPRESA.COMPL, " +
		    "          TB_EMPRESA.MUNICIPIO, " +
		    "          TB_EMPRESA.UF, " +
		    "          TB_EMPRESA.CEP, " +
		    "          TB_EMPRESA.COORDX, " +
		    "          TB_EMPRESA.COORDY, " +
		    "          TB_EMPRESA.LOGOPDIR, " +
		    "          TB_EMPRESA.LOGOPNOME, " +
		    "          TB_EMPRESA.SITUACAO, " +
		    "          TB_EMPRESA.DTINSERT " +
		    "FROM      TB_EMPRESA " +
		    "ORDER BY  TB_EMPRESA.NOME ");
            
            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	Empresa empresa = new Empresa();
            	empresa.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	empresa.setIdUser(rs.getLong("ID_USER"));
            	empresa.setNome(rs.getString("NOME"));
            	empresa.setCnp(rs.getString("CNP"));
            	empresa.setTelFixo(rs.getString("TELFIXO"));
            	empresa.setTelCel(rs.getString("TELCEL"));
            	empresa.setEmail(rs.getString("EMAIL"));
            	empresa.setResponsavel(rs.getString("RESPONSAVEL"));
            	empresa.setEndereco(rs.getString("ENDERECO"));
            	empresa.setNumero(Auxiliar.converteLong(rs.getString("NUMERO")));
            	empresa.setCompl(rs.getString("COMPL"));
            	empresa.setMunicipio(rs.getString("MUNICIPIO"));
            	empresa.setUf(rs.getString("UF"));
            	empresa.setCep(rs.getString("CEP"));
            	empresa.setCoordX(rs.getString("COORDX"));
            	empresa.setCoordY(rs.getString("COORDY"));
            	empresa.setLogoPDir(rs.getString("LOGOPDIR"));;
            	empresa.setLogoPNome(rs.getString("LOGOPNOME"));;
            	empresa.setSituacao(rs.getString("SITUACAO"));
            	empresa.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	
            	listEmpresa.add(empresa);
            }
            return listEmpresa;
        }
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }
    
    public List<Empresa> listarPorUsuario(Long idUser) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Empresa> listEmpresa = new ArrayList<Empresa>();
        try {
            stmt = connection.prepareStatement(
		    "SELECT    TB_EMPRESA.ID_EMPRESA, " +
		    "          TB_EMPRESA.ID_USER, " +
		    "          TB_EMPRESA.NOME, " +
		    "          TB_EMPRESA.CNP, " +
		    "          TB_EMPRESA.TELFIXO, " +
		    "          TB_EMPRESA.TELCEL, " +
		    "          TB_EMPRESA.RESPONSAVEL, " +
		    "          TB_EMPRESA.EMAIL, " +
		    "          TB_EMPRESA.ENDERECO, " +
		    "          TB_EMPRESA.NUMERO, " +
		    "          TB_EMPRESA.COMPL, " +
		    "          TB_EMPRESA.MUNICIPIO, " +
		    "          TB_EMPRESA.UF, " +
		    "          TB_EMPRESA.CEP, " +
		    "          TB_EMPRESA.COORDX, " +
		    "          TB_EMPRESA.COORDY, " +
		    "          TB_EMPRESA.LOGOPDIR, " +
		    "          TB_EMPRESA.LOGOPNOME, " +
		    "          TB_EMPRESA.SITUACAO, " +
		    "          TB_EMPRESA.DTINSERT " +
		    "FROM      TB_EMPRESA " +
		    "LEFT JOIN VW_USEREMPRESAID " +
		    "ON        TB_EMPRESA.ID_EMPRESA = VW_USEREMPRESAID.ID_EMPRESA " +
		    "WHERE     VW_USEREMPRESAID.ID_USER = ? " 
		    );
            
            stmt.setObject(1, idUser);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	Empresa empresa = new Empresa();
            	empresa.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	empresa.setIdUser(rs.getLong("ID_USER"));
            	empresa.setNome(rs.getString("NOME"));
            	empresa.setCnp(rs.getString("CNP"));
            	empresa.setTelFixo(rs.getString("TELFIXO"));
            	empresa.setTelCel(rs.getString("TELCEL"));
            	empresa.setEmail(rs.getString("EMAIL"));
            	empresa.setResponsavel(rs.getString("RESPONSAVEL"));
            	empresa.setEndereco(rs.getString("ENDERECO"));
            	empresa.setNumero(Auxiliar.converteLong(rs.getString("NUMERO")));
            	empresa.setCompl(rs.getString("COMPL"));
            	empresa.setMunicipio(rs.getString("MUNICIPIO"));
            	empresa.setUf(rs.getString("UF"));
            	empresa.setCep(rs.getString("CEP"));
            	empresa.setCoordX(rs.getString("COORDX"));
            	empresa.setCoordY(rs.getString("COORDY"));
            	empresa.setLogoPDir(rs.getString("LOGOPDIR"));;
            	empresa.setLogoPNome(rs.getString("LOGOPNOME"));;
            	empresa.setSituacao(rs.getString("SITUACAO"));
            	empresa.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	
            	listEmpresa.add(empresa);
            }
            return listEmpresa;
        }
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }
    
    public List<Empresa> listarPorUsuarioLogin(Long idUser) throws SQLException {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	List<Empresa> listEmpresa = new ArrayList<Empresa>();
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT    TB_EMPRESA.ID_EMPRESA, " +
					"          TB_EMPRESA.ID_USER, " +
					"          TB_EMPRESA.NOME, " +
					"          TB_EMPRESA.CNP, " +
					"          TB_EMPRESA.TELFIXO, " +
					"          TB_EMPRESA.TELCEL, " +
					"          TB_EMPRESA.RESPONSAVEL, " +
					"          TB_EMPRESA.EMAIL, " +
					"          TB_EMPRESA.ENDERECO, " +
					"          TB_EMPRESA.NUMERO, " +
					"          TB_EMPRESA.COMPL, " +
					"          TB_EMPRESA.MUNICIPIO, " +
					"          TB_EMPRESA.UF, " +
					"          TB_EMPRESA.CEP, " +
					"          TB_EMPRESA.COORDX, " +
					"          TB_EMPRESA.COORDY, " +
					"          TB_EMPRESA.LOGOPDIR, " +
					"          TB_EMPRESA.LOGOPNOME, " +
					"          TB_EMPRESA.SITUACAO, " +
					"          TB_EMPRESA.DTINSERT " +
					"FROM      TB_EMPRESA " +
					"LEFT JOIN VW_USEREMPRESA " +
					"ON        TB_EMPRESA.ID_EMPRESA = VW_USEREMPRESA.ID_EMPRESA " +
					"WHERE     VW_USEREMPRESA.ID_USER = ? " 
    				);
    		
    		stmt.setObject(1, idUser);
    		
    		rs = stmt.executeQuery();
    		
    		while (rs.next()) {
    			Empresa empresa = new Empresa();
    			empresa.setIdEmpresa(rs.getLong("ID_EMPRESA"));
    			empresa.setIdUser(rs.getLong("ID_USER"));
    			empresa.setNome(rs.getString("NOME"));
    			empresa.setCnp(rs.getString("CNP"));
    			empresa.setTelFixo(rs.getString("TELFIXO"));
    			empresa.setTelCel(rs.getString("TELCEL"));
    			empresa.setEmail(rs.getString("EMAIL"));
    			empresa.setResponsavel(rs.getString("RESPONSAVEL"));
    			empresa.setEndereco(rs.getString("ENDERECO"));
    			empresa.setNumero(Auxiliar.converteLong(rs.getString("NUMERO")));
    			empresa.setCompl(rs.getString("COMPL"));
    			empresa.setMunicipio(rs.getString("MUNICIPIO"));
    			empresa.setUf(rs.getString("UF"));
    			empresa.setCep(rs.getString("CEP"));
    			empresa.setCoordX(rs.getString("COORDX"));
    			empresa.setCoordY(rs.getString("COORDY"));
    			empresa.setLogoPDir(rs.getString("LOGOPDIR"));;
    			empresa.setLogoPNome(rs.getString("LOGOPNOME"));;
    			empresa.setSituacao(rs.getString("SITUACAO"));
    			empresa.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
    			
    			listEmpresa.add(empresa);
    		}
    		return listEmpresa;
    	}
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }
    
    public List<Empresa> listarTodos() throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Empresa> list = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(
            		"   SELECT TB_EMPRESA.* " +
		            "     FROM TB_EMPRESA "
		            );
            
            rs = stmt.executeQuery();

            while (rs.next()) {
            	Empresa empresa = new Empresa();
            	empresa.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	empresa.setIdUser(rs.getLong("ID_USER"));
            	empresa.setNome(rs.getString("NOME"));
            	empresa.setCnp(rs.getString("CNP"));
            	empresa.setTelFixo(rs.getString("TELFIXO"));
            	empresa.setTelCel(rs.getString("TELCEL"));
            	empresa.setEmail(rs.getString("EMAIL"));
            	empresa.setResponsavel(rs.getString("RESPONSAVEL"));
            	empresa.setEndereco(rs.getString("ENDERECO"));
            	empresa.setNumero(Auxiliar.converteLong(rs.getString("NUMERO")));
            	empresa.setCompl(rs.getString("COMPL"));
            	empresa.setMunicipio(rs.getString("MUNICIPIO"));
            	empresa.setUf(rs.getString("UF"));
            	empresa.setCep(rs.getString("CEP"));
            	empresa.setCoordX(rs.getString("COORDX"));
            	empresa.setCoordY(rs.getString("COORDY"));
            	empresa.setLogoPDir(rs.getString("LOGOPDIR"));;
            	empresa.setLogoPNome(rs.getString("LOGOPNOME"));;
            	empresa.setSituacao(rs.getString("SITUACAO"));
            	empresa.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	
            	list.add(empresa);
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
    
    public Empresa buscarPorId(Long idEmpresa) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT TB_EMPRESA.* " +
					"  FROM TB_EMPRESA " +
					" WHERE TB_EMPRESA.ID_EMPRESA = ? "
    				);
    		
    		stmt.setObject(1, idEmpresa);
    		rs = stmt.executeQuery();
    		
    		Empresa empresa = null;
    		while (rs.next()) {
    			empresa = new Empresa();
    			empresa.setIdEmpresa(rs.getLong("ID_EMPRESA"));
    			empresa.setIdUser(rs.getLong("ID_USER"));
    			empresa.setNome(rs.getString("NOME"));
    			empresa.setCnp(rs.getString("CNP"));
    			empresa.setTelFixo(rs.getString("TELFIXO"));
    			empresa.setTelCel(rs.getString("TELCEL"));
    			empresa.setEmail(rs.getString("EMAIL"));
    			empresa.setResponsavel(rs.getString("RESPONSAVEL"));
    			empresa.setEndereco(rs.getString("ENDERECO"));
    			empresa.setNumero(Auxiliar.converteLong(rs.getString("NUMERO")));
    			empresa.setCompl(rs.getString("COMPL"));
    			empresa.setMunicipio(rs.getString("MUNICIPIO"));
    			empresa.setUf(rs.getString("UF"));
    			empresa.setCep(rs.getString("CEP"));
    			empresa.setCoordX(rs.getString("COORDX"));
    			empresa.setCoordY(rs.getString("COORDY"));
    			empresa.setLogoPDir(rs.getString("LOGOPDIR"));;
    			empresa.setLogoPNome(rs.getString("LOGOPNOME"));;
    			empresa.setSituacao(rs.getString("SITUACAO"));
    			empresa.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
    		}
    		return empresa;
    	} 
    	finally {
    		if (stmt != null)
    			stmt.close();
    		if (rs != null)
    			rs.close();
    	}
    }
    
    public Empresa buscarPorCnp(String cnp) throws Exception {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		stmt = connection.prepareStatement(
    				"SELECT TB_EMPRESA.* " +
					"  FROM TB_EMPRESA " +
					" WHERE TB_EMPRESA.CNP = ? "
    				);
    		
    		stmt.setObject(1, cnp);
    		rs = stmt.executeQuery();
    		
    		Empresa empresa = null;
    		while (rs.next()) {
    			empresa = new Empresa();
    			empresa.setIdEmpresa(rs.getLong("ID_EMPRESA"));
    			empresa.setIdUser(rs.getLong("ID_USER"));
    			empresa.setNome(rs.getString("NOME"));
    			empresa.setCnp(rs.getString("CNP"));
    			empresa.setTelFixo(rs.getString("TELFIXO"));
    			empresa.setTelCel(rs.getString("TELCEL"));
    			empresa.setEmail(rs.getString("EMAIL"));
    			empresa.setResponsavel(rs.getString("RESPONSAVEL"));
    			empresa.setEndereco(rs.getString("ENDERECO"));
    			empresa.setNumero(rs.getLong("NUMERO"));
    			empresa.setCompl(rs.getString("COMPL"));
    			empresa.setMunicipio(rs.getString("MUNICIPIO"));
    			empresa.setUf(rs.getString("UF"));
    			empresa.setCep(rs.getString("CEP"));
    			empresa.setCoordX(rs.getString("COORDX"));
    			empresa.setCoordY(rs.getString("COORDY"));
    			empresa.setLogoPDir(rs.getString("LOGOPDIR"));;
    			empresa.setLogoPNome(rs.getString("LOGOPNOME"));;
    			empresa.setSituacao(rs.getString("SITUACAO"));
    			empresa.setDtInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
    		}
    		return empresa;
    	} 
    	finally {
    		if (stmt != null)
    			stmt.close();
    		if (rs != null)
    			rs.close();
    	}
    }
    
    
}
