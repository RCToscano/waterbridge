
package br.com.waterbridge.auxiliar;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import javaxt.io.Image;

public class Auxiliar {
 
    public static String preparaCampo (String campo, int tamanho){

        StringBuilder texto = new StringBuilder();
        
        if(campo == null) {
            
            texto.append(" ");
            
            while(texto.length() < tamanho){
             
                texto.append(" ");
            }    
        }
        else {
            
            texto.append(campo);
            
            while(texto.length() < tamanho) {
             
                texto.append(" ");
            }
        }
        
        return texto.toString();
    }
    
    public static boolean gerarArquivoTxt(String nome, String texto) {
        
        String webTempPath = "/var/tmp/";
    	//String webTempPath = "C:/temp/";
        
        try {
            
            FileWriter fw = new FileWriter(webTempPath + nome);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.print(texto);
            pw.close();
            
            return true;
        } 
        catch (IOException ex) {
            
            return false;
        }
    }
    
    public static String isNull(String entrada) {
        
        if(entrada == null) {
            
            entrada = "";
        }
        
        return entrada;
    }
    
    public static Long isNullZero(Long entrada) {
        
        if(entrada == null) {
            
            entrada = 0l;
        }
        
        return entrada;
    }
    
    public static String isEmpty(String entrada) {
        
        if(entrada != null && entrada.trim().equals("")) {
            
            entrada = null;
        }
        
        return entrada;
    }
    
    public static String isEmptyZero(String entrada) {
        
        if(entrada != null && entrada.trim().equals("")) {
            
            entrada = "0";
        }
        
        return entrada;
    }    
    
    public static String isZeroNull(String entrada) {
        
        if(entrada != null && entrada.trim().equals("")) {
            
            entrada = "0";
        }
        
        return entrada;
    }    
    
    public static String isZero(Long entrada) {
        
        if(entrada != null && entrada == 0) {
            
            return "";
        }
        
        return String.valueOf(entrada);
    }
    
    public static String isZero(Integer entrada) {
        
        if(entrada != null && entrada == 0) {
            
            return "";
        }
        
        return String.valueOf(entrada);
    }
    
    public static String substPontoPorVirgula(String valor) {
        
        if(valor == null || valor.trim().equals("")) {
            
            valor = null;
        }
        else {
            
            valor = valor.replace(".", ",");
        }
        
        return valor;
    }

    public static String dataAtual() {
        //recupera data e hora atual do sistema
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        String mes = String.valueOf(cal.get(Calendar.MONTH) + 1);
        String dia = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String ano = String.valueOf(cal.get(Calendar.YEAR));
        String horas = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        String minutos = String.valueOf(cal.get(Calendar.MINUTE));
        String segundos = String.valueOf(cal.get(Calendar.SECOND));
        //
        cal = null;
        date = null;
        //
        //formata a data de modo que o tamanho do resultado seja sempre fixo
        //dia
        if (dia.length() < 2) { dia = "0" + dia; } //mes
        if (mes.length() < 2) { mes = "0" + mes; } //horas
        if (horas.length() < 2) { horas = "0" + horas; } //minutos
        if (minutos.length() < 2) { minutos = "0" + minutos; } //segundos
        if (segundos.length() < 2) { segundos = "0" + segundos; } //
        
        //return dia + "/" + mes + "/" + ano + " " + horas + ":" + minutos + ":" + segundos;
        return ano + mes + dia + horas + minutos + segundos;
//        return ano + mes + dia ;
    }

    public static String horaAtual() {
        //recupera data e hora atual do sistema
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        String mes = String.valueOf(cal.get(Calendar.MONTH) + 1);
        String dia = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String ano = String.valueOf(cal.get(Calendar.YEAR));
        String horas = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        String minutos = String.valueOf(cal.get(Calendar.MINUTE));
        String segundos = String.valueOf(cal.get(Calendar.SECOND));
        //
        cal = null;
        date = null;
        //
        //formata a data de modo que o tamanho do resultado seja sempre fixo
        //dia
        if (dia.length() < 2) { dia = "0" + dia; } //mes
        if (mes.length() < 2) { mes = "0" + mes; } //horas
        if (horas.length() < 2) { horas = "0" + horas; } //minutos
        if (minutos.length() < 2) { minutos = "0" + minutos; } //segundos
        if (segundos.length() < 2) { segundos = "0" + segundos; } //
        
        //return dia + "/" + mes + "/" + ano + " " + horas + ":" + minutos + ":" + segundos;
        return horas + ":" + minutos ;
    }
    
    public static String leftPad(String texto, int tamanho, String caractere) {

       texto = texto.trim();

       while(texto.length() < tamanho) {
               
           texto = caractere + texto;
       }

       return texto;
   }    
    
    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {   
   
       double earthRadius = 3958.75;
       double dLat = Math.toRadians(lat2-lat1);
       double dLng = Math.toRadians(lng2-lng1);
       double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                  Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                  Math.sin(dLng/2) * Math.sin(dLng/2);
       double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
       double dist = earthRadius * c;
 
       //return dist; MILHAS   
       return dist * 1609;//METROS
   }
    
    public static String removerCaracteres(String text) {   

        if(text != null) {

                return text.replaceAll("[ãâàáä]", "a")     
                   .replaceAll("[êèéë]", "e")     
                   .replaceAll("[îìíï]", "i")     
                   .replaceAll("[õôòóö]", "o")     
                   .replaceAll("[ûúùü]", "u")     
                   .replaceAll("[ÃÂÀÁÄ]", "A")     
                   .replaceAll("[ÊÈÉË]", "E")     
                   .replaceAll("[ÎÌÍÏ]", "I")     
                   .replaceAll("[ÕÔÒÓÖ]", "O")     
                   .replaceAll("[ÛÙÚÜ]", "U")     
                   .replace('ç', 'c')     
                   .replace('Ç', 'C')     
                   .replace('ñ', 'n')     
                   .replace('Ñ', 'N')  
                   .replaceAll("!", "")                  
                   .replaceAll ("\\[\\´\\`\\?!\\@\\#\\$\\%\\¨\\*"," ")  
                   .replaceAll("\\(\\)\\=\\{\\}\\[\\]\\~\\^\\]"," ")  
                   .replaceAll("[\\.\\;\\-\\_\\+\\'\\ª\\º\\:\\;\\/]"," ")
                   .replaceAll("  ", " ")
                   .trim();
        }
        else {

                return text;
        }
    }
    
    public static String formataDtTela(String dt) {   
        
        if(dt != null && dt.length() >= 10) {

            //2015-10-29 22:08:15 --> 21/04/1979
            dt = dt.substring(8, 10) + "/" + dt.substring(5, 7) + "/" + dt.substring(0, 4); 
        }
        
        return dt;
    }
    
    public static String formataDtTelaHr(String dt) {   
        
        if(dt != null && dt.length() >= 10) {
            //2015-10-29 22:08:15 --> 21/04/1979
            dt = dt.substring(8, 10) + "/" + dt.substring(5, 7) + "/" + dt.substring(0, 4) + " " + dt.substring(11, 13) + ":" + dt.substring(14, 16); 
        }
        
        return dt;
    }
    
    public static String formataDtBanco(String dt) {   
        
        if(dt != null && dt.length() == 10) {

            //21/04/1979 --> 2015-10-29 22:08:15
            dt = dt.substring(6, 10) + "-" + dt.substring(3, 5) + "-" + dt.substring(0, 2); 
        }
        
        return dt;
    }

    public static String formataDtBancoHr(String dt) {   
        
        if(dt != null && dt.length() == 16) {

            //21/04/1979 --> 2015-10-29 22:08:15
            dt = dt.substring(6, 10) + "-" + dt.substring(3, 5) + "-" + dt.substring(0, 2) + " " + dt.substring(11, 16); 
        }
        
        return dt;
    }
    
    public static boolean isNumeric (String s) {  
        try {  
        
            Long.parseLong (s);   
            return true;  
        
        } catch (NumberFormatException ex) { 
        
            return false;  
        }  
    }    
    
    public static void ajustarImagem(String path) {
        
        Image image = new Image(new File(path));
        
        if(image.getWidth() > 800 || image.getHeight() > 800) {
         
            int dim1 = 600;
            int dim2 = 800;

            if(image.getWidth() > image.getHeight()) {

                image.resize(dim2, dim1);
            }
            else {

                image.resize(dim1, dim2);
            }
        }
        
        image.rotate(); 

        image.saveAs(new File(path));
    }
    
    public static void download(HttpServletResponse res, String path, String arquivo) throws IOException{

        DataInputStream in;
        ServletOutputStream op;
        int length;
        byte[] bbuf = new byte[2048];

        File f = new File(path);
        
        op = res.getOutputStream();
        res.setContentType("multipart/form-data");
        res.setContentLength( (int)f.length() );
        res.setHeader( "Content-Disposition", "attachment; filename="+arquivo);

        in = new DataInputStream(new FileInputStream(f));
        while (((length = in.read(bbuf)) != -1)){
            op.write(bbuf,0,length);
        }
        op.flush();
        op.close();
        in.close();
    }
    
    public static void copiarArquivo(String srFile, String dtFile) throws Exception {
		
        File f1 = new File(srFile);
        File f2 = new File(dtFile);

        InputStream in = new FileInputStream(f1);

        // For Append the file.
        // OutputStream out = new FileOutputStream(f2,true);

        // For Overwrite the file.
        OutputStream out = new FileOutputStream(f2);

        byte[] buf = new byte[1024];
        int len;

        while ((len = in.read(buf)) > 0) {
        	out.write(buf, 0, len);
        }

        f1.delete();
        in.close();
        out.close();
    }
    
    public static String getPathTemp(){
        return "C:\\temp\\teste\\";
//        return "/home/rbcuid/rbcuid_001_tmp/";
    }

    public static String formatarMinutoParaHora(Long minutos) {
        
        String hh = "00";
        String mm = "00";

        if(minutos != null && minutos.longValue() > 0) {
            
            hh = String.valueOf((minutos - (minutos % 60)) / 60);
            mm = String.valueOf(minutos % 60);

            if(hh.length() < 2) {
                hh = "0" + hh;
            }
            if(mm.length() < 2) {
                mm = "0" + mm;
            }
        }
        
        return hh + ":" + mm;
    }

    public static Long converteLong(String parametro) {
    	try {
			return Long.parseLong(parametro);
		} 
    	catch (Exception e) {
			return null;
		}
    }
    
    public static Integer converteInteger(String parametro) {
    	try {
    		return Integer.parseInt(parametro);
    	} 
    	catch (Exception e) {
    		return null;
    	}
    }
    
    public static BigDecimal converteBigDecimal(String parametro) {
    	try {
    		if(parametro != null && !parametro.isEmpty()) {
    			parametro = parametro.replace(".", "").replace(",", ".");
    			return new BigDecimal(parametro);
    		}
    		else {
    			return null;
    		}
		} 
    	catch (Exception e) {
			return null;
		}
    }
    
    public static Integer converteCheckBox(String parametro) {
    	try {
    		if(parametro != null && !parametro.isEmpty())
    			return 1;
    		
    		return null;
    	} 
    	catch (Exception e) {
    		return null;
    	}
    }
    
    public static String recuperaExtensao(String texto) {
    	return texto.substring(texto.lastIndexOf("."));
    }
    
	public static String getRandomPass() {
		char[] chart = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
				'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
				'Z' };
		char[] senha = new char[12];
		int chartLenght = chart.length;
		Random rdm = new Random();
		for (int x = 0; x < 12; x++)
			senha[x] = chart[rdm.nextInt(chartLenght)];
		return new String(senha);
	}
}
