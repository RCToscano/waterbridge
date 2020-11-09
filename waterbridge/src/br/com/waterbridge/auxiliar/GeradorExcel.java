package br.com.waterbridge.auxiliar;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.waterbridge.modelo.bo.RelConsumoPressaoExcelBO;

public class GeradorExcel {
	
	private GeradorExcel() { }
	
	public static void gerar2Abas(HttpServletResponse res, String nomeArquivo, List<String> abas,
			List<List<String>> colunas, List<List<List<String>>> valores) throws IOException {
		// Create a Workbook .xsl
		Workbook workbook = new XSSFWorkbook();

		for(int i = 0; i < abas.size(); i++) {
			
			// Create a Sheet
			Sheet sheet = workbook.createSheet(abas.get(i));
	
			// Create a Font for styling header cells
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 12);
			headerFont.setColor(IndexedColors.RED.getIndex());
	
			// Create a CellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
	
			// Create a Row
			Row headerRow = sheet.createRow(0);
			
			for(int j = 0; j < colunas.get(i).size(); j++) {
				Cell cell = headerRow.createCell(j);
				cell.setCellValue(colunas.get(i).get(j));
				cell.setCellStyle(headerCellStyle);
			}
	
	
			// Create Other rows and cells with employees data
			int rowNum = 1;
			for(int j = 0; j < valores.get(i).size(); j++) {
				Row row = sheet.createRow(rowNum++);
				
				for (int j2 = 0; j2 < valores.get(i).get(j).size(); j2++) {
					row.createCell(j2).setCellValue(valores.get(i).get(j).get(j2));
				}
			}
	
			// Resize all columns to fit the content size
			for (int j = 0; j < colunas.get(i).size(); j++) {
				sheet.autoSizeColumn(j);
			}
		}
		
		//Gera o download do arquivo
		res.setContentType("application/vnd.ms-excel");
		res.setHeader("Content-Disposition", "attachment; filename="+nomeArquivo);
		OutputStream out = res.getOutputStream();
		workbook.write(out);
		out.close();

		//Gera o arquivo fisico
//		FileOutputStream fileOut = new FileOutputStream(nomeArquivo);
//		workbook.write(fileOut);
//		fileOut.close();

		// Closing the workbook
		workbook.close();
	}
	
	public static void gerarExcel(HttpServletResponse res, String nomeArquivo, String nomeAba, List<String> colunas,
			List<List<String>> valores) throws IOException {
		// Create a Workbook .xsl
		Workbook workbook = new XSSFWorkbook();
		
		
		// Create a Sheet
		Sheet sheet = workbook.createSheet(nomeAba);
		
		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 12);
		headerFont.setColor(IndexedColors.RED.getIndex());
		
		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		
		// Create a Row
		Row headerRow = sheet.createRow(0);
		
		// Creating cells
		for (int i = 0; i < colunas.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(colunas.get(i));
			cell.setCellStyle(headerCellStyle);
		}
		
		
		// Create Other rows and cells with employees data
		int rowNum = 1;
		for(int i = 0; i < valores.size(); i++) {
			Row row = sheet.createRow(rowNum++);
			
			for (int j = 0; j < valores.get(i).size(); j++) {
				row.createCell(j).setCellValue(valores.get(i).get(j));
			}
		}
		
		// Resize all columns to fit the content size
		for (int i = 0; i < colunas.size(); i++) {
			sheet.autoSizeColumn(i);
		}
		
		//Gera o download do arquivo
		res.setContentType("application/vnd.ms-excel");
		res.setHeader("Content-Disposition", "attachment; filename="+nomeArquivo);
		OutputStream out = res.getOutputStream();
		workbook.write(out);
		out.close();
		
		//Gera o arquivo fisico
//		FileOutputStream fileOut = new FileOutputStream(nomeArquivo);
//		workbook.write(fileOut);
//		fileOut.close();
		
		// Closing the workbook
		workbook.close();
	}
	
	public static void gerarExcelConsumo(String nomeArquivo, List<String> colunas, List<String> colunasEmp,
			RelConsumoPressaoExcelBO resultado) {
		try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fileOut = new FileOutputStream(nomeArquivo)) {
			
			int empresas = 0;
			
			/** Abas */
			for(int i = 0; i < resultado.getAbas().size(); i++) {
				Sheet sheet = workbook.createSheet(resultado.getAbas().get(i));
		
				/** Fonte da celula */
				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) 12);
				headerFont.setColor(IndexedColors.RED.getIndex());
				
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFont(headerFont);
		
				
				/** Registros - Demais linhas */
				if(i < resultado.getMap().size()) {
					/** Nova linha */
					int posicaoLinha = 0;
					Row headerRow = sheet.createRow(posicaoLinha++);
					
					/** Cabecalho - 1ª linha */
					for(int j = 0; j < colunas.size(); j++) {
						Cell cell = headerRow.createCell(j);
						cell.setCellValue(colunas.get(j));
						cell.setCellStyle(headerCellStyle);
					}
					
					
					for (int j = 0; j < resultado.getMap().get(i).size(); j++) {
						Row row = sheet.createRow(posicaoLinha++);
						int celula = 0;
						
						row.createCell(celula++).setCellValue(j+1);
						row.createCell(celula++).setCellValue(resultado.getMap().get(i).get(j).getNomeEmpresa());
						row.createCell(celula++).setCellValue(resultado.getMap().get(i).get(j).getNomeCondominio());
						row.createCell(celula++).setCellValue(resultado.getMap().get(i).get(j).getDtInsert());
						row.createCell(celula++).setCellValue(resultado.getMap().get(i).get(j).getHoraInsert());
						row.createCell(celula++).setCellValue(resultado.getMap().get(i).get(j).getPressure());
						row.createCell(celula++).setCellValue(resultado.getMap().get(i).get(j).getBattery());
						row.createCell(celula++).setCellValue(resultado.getMap().get(i).get(j).getAlarmDesc());
						row.createCell(celula++).setCellValue(resultado.getMap().get(i).get(j).getTemperature());
					}
				}
				else {
					/** Nova linha */
					int posicaoLinha = 0;
					Row headerRow = sheet.createRow(posicaoLinha++);
					
					/** Cabecalho - 1ª linha */
					for(int j = 0; j < colunasEmp.size(); j++) {
						Cell cell = headerRow.createCell(j);
						cell.setCellValue(colunasEmp.get(j));
						cell.setCellStyle(headerCellStyle);
					}
					
					for (int j = 0; j < resultado.getMapEmp().get(empresas).size(); j++) {
						Row row = sheet.createRow(posicaoLinha++);
						int celula = 0;
						
						row.createCell(celula++).setCellValue(j+1);
						row.createCell(celula++).setCellValue(resultado.getMapEmp().get(empresas).get(j).getNomeEmpresa());
						row.createCell(celula++).setCellValue(resultado.getMapEmp().get(empresas).get(j).getDevice());
					}
					empresas++;
				}
				
			}
			
			/** Gera o arquivo fisico */
			workbook.write(fileOut);
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static String nomeArquivo(String nomeArquivo) {
		if(nomeArquivo.split("-")[0] != null) {
			nomeArquivo = nomeArquivo.split("-")[0].replaceAll("-", "").trim();
		}
		
		if(nomeArquivo.length() > 30) {
			nomeArquivo = nomeArquivo.substring(0, 30);
		}
		return nomeArquivo.replaceAll("\\s", "_").trim();
	}

}
