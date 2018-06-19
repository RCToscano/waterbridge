package br.com.waterbridge.bo;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
 
//import net.sf.jasperreports.engine.*;

@WebServlet(name = "GeraPdf", urlPatterns = {"/GeraPdf"})
public class GeraPdf extends HttpServlet {
    
//    private static String relat = "";
//    private long sis_user;
//    String nomeArquivoBase = "";
//    String nomeArquivo = "";
//    HttpServletRequest req;
//    HttpServletResponse res;
//    
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }
//    // </editor-fold>
//    
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException{
//        
//        this.req = request;
//        this.res = response;
//        res.setContentType("text/html;charset=UTF-8");
////        HttpSession session = req.getSession(true);
////        sis_user = (Long)session.getValue("sis_user");
//        try {
//
//            if(req.getParameter("r") != null){relat = req.getParameter("r");}
//
//            switch (relat) {
//                case "ContaRateioDemonstrativoLista.jasper":    contaRateioDemonstrativoLista(this);     break;                
//            }
//        }
//        catch(Exception e){
//        	System.out.println("erro gera pdf " + e.toString());
//        }
//        finally {
//            
//        }
//    }
//    
//    public void contaRateioDemonstrativoLista(HttpServlet hSvt)
//            throws IOException, JRException, SQLException, Exception{
//        
//        File relatorio = new File(hSvt.getServletConfig().getServletContext().getRealPath(relat));
//
//        Long idConta = Long.parseLong(req.getParameter("idConta"));
//       
//       //String path = ConnectionFactory.getPathTmp();
//        String path = "C:\\Temp\\";
//        
//        nomeArquivoBase = "030303";
//        nomeArquivo = "Demonstrativo.pdf";
//        //passar parametros para o .jasper se necessario
//        Map pars = new HashMap();
//        pars.put("idConta", req.getParameter("idConta"));
//
//        System.out.println("path " + relatorio.getPath());
//        
//        //Aqui ele cria o relatorio
//        JasperPrint impressao = JasperFillManager.fillReport(relatorio.getPath(), pars);
//        //Grava o relatorio em disco em pdf
//        JasperManager.printReportToPdfFile(impressao, path + nomeArquivoBase);
//
//        //chama metodo baixa aquivo
//        download();
//    }
//    
//    //metodo de download de qualquer arquivo
//    public void download() 
//            throws IOException{
//        
//        DataInputStream in;
//        ServletOutputStream op;
//        int length;
//        byte[] bbuf = new byte[2048];
//        
//        File f = new File("C:\\Temp\\"+nomeArquivoBase);
//        
//        op = res.getOutputStream();
//        res.setContentType("multipart/form-data");
//        res.setContentLength( (int)f.length() );
//        res.setHeader( "Content-Disposition", "attachment; filename="+nomeArquivo);
//        
//        in = new DataInputStream(new FileInputStream(f));
//        while (((length = in.read(bbuf)) != -1)){
//            op.write(bbuf,0,length);
//        }
//        op.flush();
//    }
}
