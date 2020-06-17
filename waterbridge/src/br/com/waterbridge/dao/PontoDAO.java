package br.com.waterbridge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.waterbridge.modelo.Ponto;

public class PontoDAO {
    
    private Connection connection;

    public PontoDAO(Connection connection) {
        
        this.connection = connection;
    }
    
    public void inserir(Ponto ponto) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
        
            stmt = connection.prepareStatement(
            "INSERT INTO TB_PONTO ( " +
    		//"       ID_PONTO, " +
    		"       ID_EMPRESA, " +
    		"       ID_PONTOTP, " +
    		"       DESCRICAO, " +
    		"       COORDX, " +
    		"       COORDY, " +
    		"       DTINSERT " +                    		
            ") VALUES ( " +
            "       ?,?,?,?,?,SYSDATE() " +
            ")");
            
            //stmt.setObject(1, ponto.getIdPonto());
            stmt.setObject(1, ponto.getIdEmpresa());
            stmt.setObject(2, ponto.getIdPontoTp());
            stmt.setObject(3, ponto.getDescricao());
            stmt.setObject(4, ponto.getCoordX());
            stmt.setObject(5, ponto.getCoordY());
            //stmt.setObject(1, ponto.getDtInsert();
            
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
    
    public void alterar(Ponto ponto) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
            
            //LOGA REGISTRO ANTES DE ALTERAR
            logar(ponto.getIdPonto());
        
            stmt = connection.prepareStatement(
            "UPDATE TB_PONTO SET " +            
			"       ID_EMPRESA = ?, " +
			"       ID_PONTOTP = ?, " +
			"       DESCRICAO = ?, " +
			"       COORDX = ?, " +
			"       COORDY = ?, " +			
    		"       DTINSERT = SYSDATE() " +
    		"WHERE  ID_PONTO = ? ");
            
            stmt.setObject(1, ponto.getIdEmpresa());
            stmt.setObject(2, ponto.getIdPontoTp());
            stmt.setObject(3, ponto.getDescricao());
            stmt.setObject(4, ponto.getCoordX());
            stmt.setObject(5, ponto.getCoordY());
            //stmt.setObject(1, ponto.getDtInsert();
            stmt.setObject(6, ponto.getIdPonto());
            
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
    
    public void excluir(Long idPonto) throws SQLException {

        PreparedStatement stmt = null;
                
        try {
            
            //LOGA REGISTRO ANTES DE EXCLUIR
            logar(idPonto);
        
            stmt = connection.prepareStatement(
            "DELETE FROM TB_PONTO " +            
    		"WHERE  ID_PONTO = ? ");
            
            stmt.setObject(1, idPonto);
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

    public void logar(Long idPonto) throws SQLException {

        PreparedStatement stmt = null;
        
        try {
            
            stmt = connection.prepareStatement(
            "INSERT INTO TB_PONTOLOG " +
            "SELECT * " +
            "FROM   TB_PONTO " +
            "WHERE  ID_PONTO = ? "
            );

            stmt.setLong(1, idPonto);
            
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
}
