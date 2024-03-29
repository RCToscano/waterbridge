
package br.com.waterbridge.reldao;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import br.com.waterbridge.auxiliar.Auxiliar;
import br.com.waterbridge.modelo.bo.RelConsumoPressaoExcelBO;
import br.com.waterbridge.relmodelo.RelConsumoPressaoExcel;
import br.com.waterbridge.relmodelo.RelPressao;

public class RelPressaoDAO {
    
    private Connection connection;

    public RelPressaoDAO(Connection connection) {
        
        this.connection = connection;
    }

    public List<RelPressao> listar(String sql) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RelPressao> listRelPressao = new ArrayList<RelPressao>();
        
        SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        
        try {
            stmt = connection.prepareStatement(
    		"SELECT ID_EMPRESA, " +
            "		ID_CONDOMINIO, " +
            "		ID_BRIDGE, " +
            "       ID_BRIDGETP, " +
    		"       ID_CONSUMO, " +
    		"		ID_USER, " +
    		"		DEVICE, " +
    		"		DATA, " +
    		"		VERSION, " +
    		"		METERPOSITION, " +
    		"		VOLUME, " +
    		"		PRESSURE, " +
    		"		FLOW, " +
    		"		TEMPERATURE, " +
    		"		BATTERY, " +
    		"		ALARM, " +
    		"		ALARMDESC, " +
    		"		DTINSERT " +
        	"FROM   VW_RELATORIOPRESSAO " +		
            sql
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
            	RelPressao relPressao = new RelPressao();
            	relPressao.setIdEmpresa(rs.getLong("ID_EMPRESA"));
            	relPressao.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
            	relPressao.setIdBridge(rs.getLong("ID_BRIDGE"));
            	relPressao.setIdBridgeTp(rs.getLong("ID_BRIDGETP"));
            	relPressao.setIdConsumo(rs.getLong("ID_CONSUMO"));
            	relPressao.setIdUser(rs.getLong("ID_USER"));
            	relPressao.setDevice(rs.getString("DEVICE"));
            	relPressao.setData(rs.getString("DATA"));
            	relPressao.setVersion(rs.getString("VERSION"));
            	relPressao.setMeterPosition(rs.getLong("METERPOSITION"));
            	relPressao.setVolume(rs.getDouble("VOLUME"));
            	relPressao.setPressure(rs.getDouble("PRESSURE"));
            	relPressao.setFlow(rs.getDouble("FLOW"));
            	relPressao.setTemperature(rs.getLong("TEMPERATURE"));
            	relPressao.setBattery(rs.getDouble("BATTERY"));
            	relPressao.setAlarm(rs.getLong("ALARM"));
            	relPressao.setAlarmDesc(rs.getString("ALARMDESC"));
            	relPressao.setDtHoraInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
            	relPressao.setDtInsert(formatoData.format(formatoBanco.parse(rs.getString("DTINSERT"))));
            	relPressao.setHoraInsert(formatoHora.format(formatoBanco.parse(rs.getString("DTINSERT"))));
            	listRelPressao.add(relPressao);
            }
            
            return listRelPressao;
        }
        finally {
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }
    }
    
    
    public RelPressao buscarUmaLinha(String sql) throws SQLException {
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		stmt = connection.prepareStatement("SELECT * FROM VW_RELATORIOPRESSAO " + sql);
    		rs = stmt.executeQuery();
    		
    		RelPressao relPressao = new RelPressao();
    		if(rs.next()) {
    			relPressao.setIdEmpresa(rs.getLong("ID_EMPRESA"));
    			relPressao.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
    			relPressao.setIdBridge(rs.getLong("ID_BRIDGE"));
    			relPressao.setIdBridgeTp(rs.getLong("ID_BRIDGETP"));
    			relPressao.setIdConsumo(rs.getLong("ID_CONSUMO"));
    			relPressao.setIdUser(rs.getLong("ID_USER"));
    			relPressao.setNomeEmpresa(rs.getString("NOME_EMPRESA"));
    			relPressao.setNomeCondominio(rs.getString("NOME_CONDOMINIO"));
    			relPressao.setIdUser(rs.getLong("ID_USER"));
    			relPressao.setDevice(rs.getString("DEVICE"));
    			relPressao.setData(rs.getString("DATA"));
    			relPressao.setVersion(rs.getString("VERSION"));
    			relPressao.setMeterPosition(rs.getLong("METERPOSITION"));
    			relPressao.setVolume(rs.getDouble("VOLUME"));
    			relPressao.setPressure(rs.getDouble("PRESSURE"));
    			relPressao.setFlow(rs.getDouble("FLOW"));
    			relPressao.setTemperature(rs.getLong("TEMPERATURE"));
    			relPressao.setBattery(rs.getDouble("BATTERY"));
    			relPressao.setAlarm(rs.getLong("ALARM"));
    			relPressao.setAlarmDesc(rs.getString("ALARMDESC"));
    			relPressao.setDtHoraInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
    		}
    		
    		return relPressao;
    	}
    	finally {
    		if(stmt != null)
    			stmt.close();
    		if(rs != null)
    			rs.close();
    	}
    }
    
	public RelConsumoPressaoExcelBO buscarConsumoUserEmpresa(Long idUser, Long idEmpresa, String dtInicio, String dtFim)
			throws Exception {
		
		RelConsumoPressaoExcelBO resultado = new RelConsumoPressaoExcelBO();
		List<String> abas = new ArrayList<>();
		HashMap<Integer, List<RelConsumoPressaoExcel>> map = new HashMap<>();
		
		SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
		
		String select = 
				"   select TB_EMPRESA.ID_EMPRESA AS ID_EMPRESA, " + 
				"          TB_EMPRESA.NOME AS NOME_EMPRESA, " + 
				"          TB_CONDOMINIO.ID_CONDOMINIO AS ID_CONDOMINIO, " + 
				"          concat(TB_CONDOMINIO.NOME,' - ',TB_CONDOMINIO.ENDERECO,' ',TB_CONDOMINIO.NUMERO,' ',TB_CONDOMINIO.COMPL) AS NOME_CONDOMINIO, " + 
				"          TB_BRIDGE.ID_BRIDGE AS ID_BRIDGE, " + 
				"          TB_BRIDGE.ID_BRIDGETP AS ID_BRIDGETP, " + 
				"          TB_CONSUMO.ID_CONSUMO AS ID_CONSUMO, " + 
				"          TB_CONSUMO.ID_USER AS ID_USER, " + 
				"          TB_CONSUMO.DEVICE AS DEVICE, " + 
				"          TB_CONSUMO.DATA AS DATA, " + 
				"          TB_CONSUMO.VERSION AS VERSION, " + 
				"          TB_CONSUMO.METERPOSITION AS METERPOSITION, " + 
				"          TB_CONSUMO.VOLUME AS VOLUME, " + 
				"          TB_CONSUMO.PRESSURE AS PRESSURE, " + 
				"          TB_CONSUMO.FLOW AS FLOW, " + 
				"          TB_CONSUMO.TEMPERATURE AS TEMPERATURE, " + 
				"          TB_CONSUMO.BATTERY AS BATTERY, " + 
				"          TB_CONSUMO.ALARM AS ALARM, " + 
				"          TB_ALARM.ALARM AS ALARMDESC, " + 
				"          TB_CONSUMO.DTINSERT AS DTINSERT, " +
				"          TB_METAPRESSAO.ID_METAPRESSAO, " + 
				"		   TB_METAPRESSAO.PRESSAOMIN, " + 
				"		   TB_METAPRESSAO.PRESSAOMAX, " + 
				"		   TB_METAPRESSAO.PRESSAOMINBAIXA, " + 
				"		   TB_METAPRESSAO.PRESSAOMAXALTA " + 
				"     FROM TB_CONSUMO  " + 
				"LEFT JOIN TB_BRIDGE  " + 
				"       ON TB_CONSUMO.ID_BRIDGE = TB_BRIDGE.ID_BRIDGE  " + 
				"LEFT JOIN TB_CONDOMINIO  " + 
				"       ON TB_CONSUMO.ID_CONDOMINIO = TB_CONDOMINIO.ID_CONDOMINIO  " + 
				"LEFT JOIN TB_EMPRESA  " + 
				"       ON TB_CONSUMO.ID_EMPRESA = TB_EMPRESA.ID_EMPRESA  " + 
				"LEFT JOIN TB_ALARM  " + 
				"       ON TB_CONSUMO.ALARM = TB_ALARM.ID_ALARM " + 
				"LEFT JOIN TB_METAPRESSAO " + 
				"	    ON TB_CONSUMO.ID_BRIDGE = TB_METAPRESSAO.ID_BRIDGE " + 
				"    WHERE TB_CONSUMO.DTINSERT BETWEEN ? AND ? " + 
			    //"      AND TB_CONSUMO.ID_EMPRESA IN(4,6,7,8,9,10) " +
			    "      AND TB_CONSUMO.ID_EMPRESA IN(10) " +
			    "      AND TB_CONSUMO.ID_USER = ? " + 
	    		" ORDER BY TB_CONSUMO.DEVICE, TB_CONSUMO.ID_CONSUMO DESC";
        
		try (PreparedStatement statement = connection.prepareStatement(select)) {

			statement.setString(1, dtInicio);
			statement.setString(2, dtFim);
			//statement.setLong(3, idEmpresa);
			statement.setLong(3, idUser);
			
			try (ResultSet rs = statement.executeQuery()) {
				
				List<RelConsumoPressaoExcel> valores = new ArrayList<>();
				
				int posicao = 0;
				while (rs.next()) {
					String bridge = rs.getString("DEVICE");

					if (abas.isEmpty()) {
						abas.add(bridge);
					} 
					else if (!abas.get(posicao).equals(bridge)) {
						/** Adiciona os valores antes de mudar aba */
						map.put(posicao, valores);
						valores = new ArrayList<>();

						/** Muda aba */
						abas.add(bridge);
						posicao++;
					}

					RelConsumoPressaoExcel relPressao = new RelConsumoPressaoExcel();
					relPressao.setIdEmpresa(rs.getLong("ID_EMPRESA"));
					relPressao.setIdCondominio(rs.getLong("ID_CONDOMINIO"));
					relPressao.setIdBridge(rs.getLong("ID_BRIDGE"));
					relPressao.setIdConsumo(rs.getLong("ID_CONSUMO"));
					relPressao.setIdUser(rs.getLong("ID_USER"));
					relPressao.setNomeEmpresa(rs.getString("NOME_EMPRESA"));
					relPressao.setNomeCondominio(rs.getString("NOME_CONDOMINIO"));
					relPressao.setDevice(bridge);
					relPressao.setData(rs.getString("DATA"));
					relPressao.setVersion(rs.getString("VERSION"));
					relPressao.setMeterPosition(rs.getLong("METERPOSITION"));
					relPressao.setVolume(rs.getDouble("VOLUME"));
					relPressao.setPressure(rs.getDouble("PRESSURE"));
					relPressao.setFlow(rs.getLong("FLOW"));
					relPressao.setTemperature(rs.getLong("TEMPERATURE"));
					relPressao.setBattery(rs.getDouble("BATTERY"));
					relPressao.setAlarm(rs.getLong("ALARM"));
					relPressao.setAlarmDesc(rs.getString("ALARMDESC"));
					relPressao.setPressaoMin(rs.getDouble("PRESSAOMIN"));
					relPressao.setPressaoMax(rs.getDouble("PRESSAOMAX"));
					relPressao.setPressaoMinBaixa(rs.getDouble("PRESSAOMINBAIXA"));
					relPressao.setPressaoMaxAlta(rs.getDouble("PRESSAOMAXALTA"));
					relPressao.setDtHoraInsert(Auxiliar.formataDtTelaHr(rs.getString("DTINSERT")));
					relPressao.setDtInsert(formatoData.format(formatoBanco.parse(rs.getString("DTINSERT"))));
					relPressao.setHoraInsert(formatoHora.format(formatoBanco.parse(rs.getString("DTINSERT"))));
					relPressao.setAlarmDesc(defineAlarme(relPressao));
					valores.add(relPressao);
				}
				map.put(posicao, valores);
			}
			resultado.setAbas(abas);
			resultado.setMap(map);
            return resultado;
        }
    }
	
	public void buscarConsumoUserEmpresa1(Long idUser, Long idEmpresa, String dtInicio, String dtFim,
			String nomeArquivo, List<String> colunas) throws Exception {
		
		SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
		
		long start = System.nanoTime();
		
		String select = 
				"   select TB_EMPRESA.ID_EMPRESA AS ID_EMPRESA, " + 
				"          TB_EMPRESA.NOME AS NOME_EMPRESA, " + 
				"          TB_CONDOMINIO.ID_CONDOMINIO AS ID_CONDOMINIO, " + 
				"          concat(TB_CONDOMINIO.NOME,' - ',TB_CONDOMINIO.ENDERECO,' ',TB_CONDOMINIO.NUMERO,' ',TB_CONDOMINIO.COMPL) AS NOME_CONDOMINIO, " + 
				"          TB_BRIDGE.ID_BRIDGE AS ID_BRIDGE, " + 
				"          TB_BRIDGE.ID_BRIDGETP AS ID_BRIDGETP, " + 
				"          TB_CONSUMO.ID_CONSUMO AS ID_CONSUMO, " + 
				"          TB_CONSUMO.ID_USER AS ID_USER, " + 
				"          TB_CONSUMO.DEVICE AS DEVICE, " + 
				"          TB_CONSUMO.DATA AS DATA, " + 
				"          TB_CONSUMO.VERSION AS VERSION, " + 
				"          TB_CONSUMO.METERPOSITION AS METERPOSITION, " + 
				"          TB_CONSUMO.VOLUME AS VOLUME, " + 
				"          TB_CONSUMO.PRESSURE AS PRESSURE, " + 
				"          TB_CONSUMO.FLOW AS FLOW, " + 
				"          TB_CONSUMO.TEMPERATURE AS TEMPERATURE, " + 
				"          TB_CONSUMO.BATTERY AS BATTERY, " + 
				"          TB_CONSUMO.ALARM AS ALARM, " + 
				"          TB_ALARM.ALARM AS ALARMDESC, " + 
				"          TB_CONSUMO.DTINSERT AS DTINSERT, " +
				"          TB_METAPRESSAO.ID_METAPRESSAO, " + 
				"		   TB_METAPRESSAO.PRESSAOMIN, " + 
				"		   TB_METAPRESSAO.PRESSAOMAX, " + 
				"		   TB_METAPRESSAO.PRESSAOMINBAIXA, " + 
				"		   TB_METAPRESSAO.PRESSAOMAXALTA " + 
				"     FROM TB_CONSUMO  " + 
				"LEFT JOIN TB_BRIDGE  " + 
				"       ON TB_CONSUMO.ID_BRIDGE = TB_BRIDGE.ID_BRIDGE  " + 
				"LEFT JOIN TB_CONDOMINIO  " + 
				"       ON TB_CONSUMO.ID_CONDOMINIO = TB_CONDOMINIO.ID_CONDOMINIO  " + 
				"LEFT JOIN TB_EMPRESA  " + 
				"       ON TB_CONSUMO.ID_EMPRESA = TB_EMPRESA.ID_EMPRESA  " + 
				"LEFT JOIN TB_ALARM  " + 
				"       ON TB_CONSUMO.ALARM = TB_ALARM.ID_ALARM " + 
				"LEFT JOIN TB_METAPRESSAO " + 
				"	    ON TB_CONSUMO.ID_BRIDGE = TB_METAPRESSAO.ID_BRIDGE " + 
				"    WHERE TB_CONSUMO.DTINSERT BETWEEN ? AND ? " + 
			    "      AND TB_CONSUMO.ID_EMPRESA = ? " +
			    "      AND TB_CONSUMO.ID_USER = ? " + 
	    		" ORDER BY TB_CONSUMO.DEVICE, TB_CONSUMO.ID_CONSUMO DESC";
        
		try (PreparedStatement statement = connection.prepareStatement(select)) {

			statement.setString(1, dtInicio);
			statement.setString(2, dtFim);
			statement.setLong(3, idEmpresa);
			statement.setLong(4, idUser);
			statement.setFetchSize(1000);
			
			try (ResultSet rs = statement.executeQuery(); 
					SXSSFWorkbook workbook = new SXSSFWorkbook();
					FileOutputStream out = new FileOutputStream(nomeArquivo);) {
				
		        workbook.setCompressTempFiles(true);
		        
		        int posicaoLinha = 0;
		        String lastBridge = "";
		        SXSSFSheet sheet = null;
		        List<String> listBridges = new ArrayList<>();
		        
		        while (rs.next()) {
					String bridge = rs.getString("DEVICE");
					
					if (!lastBridge.equals(bridge)) {
						lastBridge = bridge;
						listBridges.add(bridge);

						//Abas
				        sheet = workbook.createSheet(bridge);
				        sheet.setRandomAccessWindowSize(100);// keep 100 rows in memory, exceeding rows will be flushed to disk

				        
				        /** Fonte da celula */
						Font headerFont = workbook.createFont();
						headerFont.setBold(true);
						headerFont.setFontHeightInPoints((short) 12);
						headerFont.setColor(IndexedColors.RED.getIndex());
						
						CellStyle headerCellStyle = workbook.createCellStyle();
						headerCellStyle.setFont(headerFont);
				
						/** Nova linha */
						posicaoLinha = 0;
						Row headerRow = sheet.createRow(posicaoLinha++);
						
						/** Cabecalho */
						for(int j = 0; j < colunas.size(); j++) {
							Cell cell = headerRow.createCell(j);
							cell.setCellValue(colunas.get(j));
							cell.setCellStyle(headerCellStyle);
						}
					}
					int celula = 0;
					Row row = sheet.createRow(posicaoLinha);
					row.createCell(celula++).setCellValue(posicaoLinha++);
					row.createCell(celula++).setCellValue(rs.getString("NOME_EMPRESA"));
					row.createCell(celula++).setCellValue(rs.getString("NOME_CONDOMINIO"));
					row.createCell(celula++).setCellValue(formatoData.format(formatoBanco.parse(rs.getString("DTINSERT"))));
					row.createCell(celula++).setCellValue(formatoHora.format(formatoBanco.parse(rs.getString("DTINSERT"))));
					row.createCell(celula++).setCellValue(rs.getDouble("PRESSURE"));
					row.createCell(celula++).setCellValue(rs.getDouble("BATTERY"));
					row.createCell(celula++).setCellValue(defineAlarme(rs.getString("ALARMDESC"), rs.getDouble("PRESSAOMINBAIXA"), rs.getDouble("PRESSAOMIN"), rs.getDouble("PRESSURE"), rs.getDouble("PRESSAOMAX"), rs.getDouble("PRESSAOMAXALTA")));
					row.createCell(celula++).setCellValue(rs.getLong("TEMPERATURE"));
					
				}
		        
		        //Ultima aba
		        sheet = workbook.createSheet("BRIDGES");
		        sheet.setRandomAccessWindowSize(100);
		        posicaoLinha = 0;
		        for (int i = 0; i < listBridges.size(); i++) {
					Row row = sheet.createRow(posicaoLinha++);
					row.createCell(0).setCellValue(listBridges.get(i));
				}
		        
		        workbook.write(out);
			}
        }
		
		long end = System.nanoTime();
		long durationInMs = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Finalizado em :" + durationInMs + " ms");
		
		Runtime runtime = Runtime.getRuntime();
		long memory = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Memoria utilizada em bytes: " + memory);
		System.out.println("Memoria utilizada em megabytes: " + (memory / (1024L * 1024L)));
    }
	
	private String defineAlarme(String alarmDesc, Double pressaoMinBaixa, Double pressaoMin, Double pressure,
			Double pressaoMax, Double pressaoMaxAlta) {
		String alarmPressao = "";
    	if(pressaoMinBaixa != null && pressaoMinBaixa > pressure) {
    		alarmPressao = "Press�o Baixa (N�vel Cr�tico)";
    	}
    	else if(pressaoMin != null && pressaoMin > pressure) {
    		alarmPressao = "Press�o Baixa";
    	}
    	else if(pressaoMaxAlta != null && pressaoMaxAlta < pressure) {
    		alarmPressao = "Press�o Alta (N�vel Cr�tico)";
    	}
    	else if(pressaoMax != null && pressaoMax < pressure) {
    		alarmPressao = "Press�o Alta";
    	}
    	
    	String alarmPadrao = "";
    	if(alarmDesc != null && !alarmDesc.equals("NO ALARM")) {
    		alarmPadrao = alarmDesc;
    	}
    	else if(alarmPressao.equals("")) {
    		alarmPadrao = "Sem Alarme";
    	}
    	
    	String separador = "";
    	if(!alarmPadrao.equals("Sem Alarme") && !alarmPadrao.equals("") && !alarmPressao.equals("")) {
    		separador = "/";
    	}
		return (alarmPadrao + separador + alarmPressao);
	}
	
	private static String defineAlarme(RelConsumoPressaoExcel relPressao) {
		String alarmPressao = "";
    	if(relPressao.getPressaoMinBaixa() != null && relPressao.getPressaoMinBaixa() > relPressao.getPressure()) {
    		alarmPressao = "Press�o Baixa (N�vel Cr�tico)";
    	}
    	else if(relPressao.getPressaoMin() != null && relPressao.getPressaoMin() > relPressao.getPressure()) {
    		alarmPressao = "Press�o Baixa";
    	}
    	else if(relPressao.getPressaoMaxAlta() != null && relPressao.getPressaoMaxAlta() < relPressao.getPressure()) {
    		alarmPressao = "Press�o Alta (N�vel Cr�tico)";
    	}
    	else if(relPressao.getPressaoMax() != null && relPressao.getPressaoMax() < relPressao.getPressure()) {
    		alarmPressao = "Press�o Alta";
    	}
    	
    	String alarmPadrao = "";
    	if(relPressao.getAlarmDesc() != null && !relPressao.getAlarmDesc().equals("NO ALARM")) {

    		alarmPadrao = relPressao.getAlarmDesc();
    	}
    	else if(alarmPressao.equals("")) {
    		alarmPadrao = "Sem Alarme";
    	}
    	
    	String separador = "";
    	if(!alarmPadrao.equals("Sem Alarme") && !alarmPadrao.equals("") && !alarmPressao.equals("")) {
    		separador = "/";
    	}
		return (alarmPadrao + separador + alarmPressao);
	}
    
}