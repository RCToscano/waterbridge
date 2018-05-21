package br.com.waterbridge.auxiliar;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Serializacao extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String imagem = null;
        String path = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        BufferedOutputStream output = null;
        
        try {
            
            path = request.getParameter("path");
            imagem = request.getParameter("imagem");
            
            fis = new FileInputStream(new File(path + imagem));
            bis = new BufferedInputStream(fis);             
            response.setContentType("image/jpeg");
            response.setHeader("Content-disposition", "attachment; filename=" + imagem);
            output = new BufferedOutputStream(response.getOutputStream());
            
            for (int data; (data = bis.read()) > -1;) {
            
                output.write(data);
            }             
        }
        catch(IOException e){

        }
        finally {

            if(fis != null) {fis.close();}
            if(bis != null) {bis.close();}
            if(output != null) {output.close();}
        }
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }
}