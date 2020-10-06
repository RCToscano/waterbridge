package br.com.waterbridge.auxiliar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.waterbridge.modelo.Bridge;
import br.com.waterbridge.modelo.Condominio;
import br.com.waterbridge.modelo.User;

public class Email {
	
	public static void enviarEmail(String titulo, String mensagem, String destino){
		
        final String username = "noreply.waterbridge@gmail.com";
        final String password = "waterbridge16831964";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                }
            });

        try {
            Message message = new MimeMessage(session);
            
            message.setHeader("MIME-Version", "1.0");  
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");  
            message.setHeader("Content-Transfer-Encoding", "quoted-printable");  
            message.setHeader("Content-Disposition", "inline");  

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
            
            message.setSubject(titulo);
            
            message.setContent(mensagem, "text/html; charset=UTF-8");

            Transport.send(message);
        } 
        catch(MessagingException e) {
            throw new RuntimeException(e);
        }
    }
	public static void main(String[] args) {
		Email.enviarEmailComAnexo(
				"LISTA PRESSAO 09/2020", 
				"Lista de pressões medidas no mês 09/2020", 
				"felipe@desoltec.com.br", 
				"C:\\Temp\\Teste.xlsx",
				"LISTA_PRESSAO_202009.xlsx");
	}
	public static void enviarEmailComAnexo(
			String titulo, String mensagem, String destino, String src, String nomeArquivo){
		
        final String username = "noreply.waterbridge@gmail.com";
        final String password = "waterbridge16831964";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                }
            });

        try {
            Message message = new MimeMessage(session);
            
            message.setHeader("MIME-Version", "1.0");  
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");  
            message.setHeader("Content-Transfer-Encoding", "quoted-printable");  
            message.setHeader("Content-Disposition", "inline");  

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
            
            message.setSubject(titulo);
            
            message.setContent(mensagem, "text/html; charset=UTF-8");
            
        	// Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
	
            // Now set the actual message
            messageBodyPart.setText(mensagem);   
	            
            //Create a multipar message
            Multipart multipart = new MimeMultipart();
	 
            // Set text message part
      		multipart.addBodyPart(messageBodyPart);
	 
      		// Part two is attachment
      		messageBodyPart = new MimeBodyPart();
          	//String filename = "C:\\Temp\\Teste.xlsx";
          	DataSource source = new FileDataSource(src);
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        messageBodyPart.setFileName(nomeArquivo);
          	multipart.addBodyPart(messageBodyPart);
	 
	        // Send the complete message parts
	        message.setContent(multipart);

            Transport.send(message);
        } 
        catch(MessagingException e) {
            throw new RuntimeException(e);
        }
    }
	
	public static String corpoEmailSenha(String nome, String usuario, String senha) {
		String usuarioQuebrado[] = nome.split("\\s+");
		String nomeNovo = usuarioQuebrado[0];
		
		return  "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"	<head>\r\n" + 
				"		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
				"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\r\n" + 
				"		<meta name=\"format-detection\" content=\"telephone=no\" /> <!-- disable auto telephone linking in iOS -->\r\n" + 
				"		<title>WaterBridge</title>\r\n" + 
				"		<style type=\"text/css\">\r\n" + 
				"			/* RESET STYLES */\r\n" + 
				"			html { background-color:#E1E1E1; margin:0; padding:0; }\r\n" + 
				"			body, #bodyTable, #bodyCell, #bodyCell{height:100% !important; margin:0; padding:0; width:100% !important;font-family:Helvetica, Arial, \"Lucida Grande\", sans-serif;}\r\n" + 
				"			table{border-collapse:collapse;}\r\n" + 
				"			table[id=bodyTable] {width:100%!important;margin:auto;max-width:500px!important;color:#7A7A7A;font-weight:normal;}\r\n" + 
				"			img, a img{border:0; outline:none; text-decoration:none;height:auto; line-height:100%;}\r\n" + 
				"			a {text-decoration:none !important;border-bottom: 1px solid;}\r\n" + 
				"			h1, h2, h3, h4, h5, h6{color:#5F5F5F; font-weight:normal; font-family:Helvetica; font-size:20px; line-height:125%; text-align:Left; letter-spacing:normal;margin-top:0;margin-right:0;margin-bottom:10px;margin-left:0;padding-top:0;padding-bottom:0;padding-left:0;padding-right:0;}\r\n" + 
				"			/* CLIENT-SPECIFIC STYLES */\r\n" + 
				"			.ReadMsgBody{width:100%;} .ExternalClass{width:100%;} /* Force Hotmail/Outlook.com to display emails at full width. */\r\n" + 
				"			.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div{line-height:100%;} /* Force Hotmail/Outlook.com to display line heights normally. */\r\n" + 
				"			table, td{mso-table-lspace:0pt; mso-table-rspace:0pt;} /* Remove spacing between tables in Outlook 2007 and up. */\r\n" + 
				"			#outlook a{padding:0;} /* Force Outlook 2007 and up to provide a \"view in browser\" message. */\r\n" + 
				"			img{-ms-interpolation-mode: bicubic;display:block;outline:none; text-decoration:none;} /* Force IE to smoothly render resized images. */\r\n" + 
				"			body, table, td, p, a, li, blockquote{-ms-text-size-adjust:100%; -webkit-text-size-adjust:100%; font-weight:normal!important;} /* Prevent Windows- and Webkit-based mobile platforms from changing declared text sizes. */\r\n" + 
				"			.ExternalClass td[class=\"ecxflexibleContainerBox\"] h3 {padding-top: 10px !important;} /* Force hotmail to push 2-grid sub headers down */\r\n" + 
				"			/* /\\/\\/\\/\\/\\/\\/\\/\\/ TEMPLATE STYLES /\\/\\/\\/\\/\\/\\/\\/\\/ */\r\n" + 
				"			/* ========== Page Styles ========== */\r\n" + 
				"			h1{display:block;font-size:26px;font-style:normal;font-weight:normal;line-height:100%;}\r\n" + 
				"			h2{display:block;font-size:20px;font-style:normal;font-weight:normal;line-height:120%;}\r\n" + 
				"			h3{display:block;font-size:17px;font-style:normal;font-weight:normal;line-height:110%;}\r\n" + 
				"			h4{display:block;font-size:18px;font-style:italic;font-weight:normal;line-height:100%;}\r\n" + 
				"			.flexibleImage{height:auto;}\r\n" + 
				"			.linkRemoveBorder{border-bottom:0 !important;}\r\n" + 
				"			table[class=flexibleContainerCellDivider] {padding-bottom:0 !important;padding-top:0 !important;}\r\n" + 
				"			body, #bodyTable{background-color:#E1E1E1;}\r\n" + 
				"			#emailHeader{background-color:#E1E1E1;}\r\n" + 
				"			#emailBody{background-color:#FFFFFF;}\r\n" + 
				"			#emailFooter{background-color:#E1E1E1;}\r\n" + 
				"			.nestedContainer{background-color:#F8F8F8; border:1px solid #CCCCCC;}\r\n" + 
				"			.emailButton{background-color:#205478; border-collapse:separate;}\r\n" + 
				"			.buttonContent{color:#FFFFFF; font-family:Helvetica; font-size:18px; font-weight:bold; line-height:100%; padding:15px; text-align:center;}\r\n" + 
				"			.buttonContent a{color:#FFFFFF; display:block; text-decoration:none!important; border:0!important;}\r\n" + 
				"			.emailCalendar{background-color:#FFFFFF; border:1px solid #CCCCCC;}\r\n" + 
				"			.emailCalendarMonth{background-color:#205478; color:#FFFFFF; font-family:Helvetica, Arial, sans-serif; font-size:16px; font-weight:bold; padding-top:10px; padding-bottom:10px; text-align:center;}\r\n" + 
				"			.emailCalendarDay{color:#205478; font-family:Helvetica, Arial, sans-serif; font-size:60px; font-weight:bold; line-height:100%; padding-top:20px; padding-bottom:20px; text-align:center;}\r\n" + 
				"			.imageContentText {margin-top: 10px;line-height:0;}\r\n" + 
				"			.imageContentText a {line-height:0;}\r\n" + 
				"			#invisibleIntroduction {display:none !important;} /* Removing the introduction text from the view */\r\n" + 
				"			/*FRAMEWORK HACKS & OVERRIDES */\r\n" + 
				"			span[class=ios-color-hack] a {color:#275100!important;text-decoration:none!important;} /* Remove all link colors in IOS (below are duplicates based on the color preference) */\r\n" + 
				"			span[class=ios-color-hack2] a {color:#205478!important;text-decoration:none!important;}\r\n" + 
				"			span[class=ios-color-hack3] a {color:#8B8B8B!important;text-decoration:none!important;}\r\n" + 
				"			/* A nice and clean way to target phone numbers you want clickable and avoid a mobile phone from linking other numbers that look like, but are not phone numbers.  Use these two blocks of code to \"unstyle\" any numbers that may be linked.  The second block gives you a class to apply with a span tag to the numbers you would like linked and styled.\r\n" + 
				"			Inspired by Campaign Monitor's article on using phone numbers in email: http://www.campaignmonitor.com/blog/post/3571/using-phone-numbers-in-html-email/.\r\n" + 
				"			*/\r\n" + 
				"			.a[href^=\"tel\"], a[href^=\"sms\"] {text-decoration:none!important;color:#606060!important;pointer-events:none!important;cursor:default!important;}\r\n" + 
				"			.mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {text-decoration:none!important;color:#606060!important;pointer-events:auto!important;cursor:default!important;}\r\n" + 
				"			/* MOBILE STYLES */\r\n" + 
				"			@media only screen and (max-width: 480px){\r\n" + 
				"				/*////// CLIENT-SPECIFIC STYLES //////*/\r\n" + 
				"				body{width:100% !important; min-width:100% !important;} /* Force iOS Mail to render the email at full width. */\r\n" + 
				"				/* FRAMEWORK STYLES */\r\n" + 
				"				/*\r\n" + 
				"				CSS selectors are written in attribute\r\n" + 
				"				selector format to prevent Yahoo Mail\r\n" + 
				"				from rendering media query styles on\r\n" + 
				"				desktop.\r\n" + 
				"				*/\r\n" + 
				"				/*td[class=\"textContent\"], td[class=\"flexibleContainerCell\"] { width: 100%; padding-left: 10px !important; padding-right: 10px !important; }*/\r\n" + 
				"				table[id=\"emailHeader\"],\r\n" + 
				"				table[id=\"emailBody\"],\r\n" + 
				"				table[id=\"emailFooter\"],\r\n" + 
				"				table[class=\"flexibleContainer\"],\r\n" + 
				"				td[class=\"flexibleContainerCell\"] {width:100% !important;}\r\n" + 
				"				td[class=\"flexibleContainerBox\"], td[class=\"flexibleContainerBox\"] table {display: block;width: 100%;text-align: left;}\r\n" + 
				"				/*\r\n" + 
				"				The following style rule makes any\r\n" + 
				"				image classed with 'flexibleImage'\r\n" + 
				"				fluid when the query activates.\r\n" + 
				"				Make sure you add an inline max-width\r\n" + 
				"				to those images to prevent them\r\n" + 
				"				from blowing out.\r\n" + 
				"				*/\r\n" + 
				"				td[class=\"imageContent\"] img {height:auto !important; width:100% !important; max-width:100% !important; }\r\n" + 
				"				img[class=\"flexibleImage\"]{height:auto !important; width:100% !important;max-width:100% !important;}\r\n" + 
				"				img[class=\"flexibleImageSmall\"]{height:auto !important; width:auto !important;}\r\n" + 
				"				/*\r\n" + 
				"				Create top space for every second element in a block\r\n" + 
				"				*/\r\n" + 
				"				table[class=\"flexibleContainerBoxNext\"]{padding-top: 10px !important;}\r\n" + 
				"				/*\r\n" + 
				"				Make buttons in the email span the\r\n" + 
				"				full width of their container, allowing\r\n" + 
				"				for left- or right-handed ease of use.\r\n" + 
				"				*/\r\n" + 
				"				table[class=\"emailButton\"]{width:100% !important;}\r\n" + 
				"				td[class=\"buttonContent\"]{padding:0 !important;}\r\n" + 
				"				td[class=\"buttonContent\"] a{padding:15px !important;}\r\n" + 
				"			}\r\n" + 
				"			/*  CONDITIONS FOR ANDROID DEVICES ONLY\r\n" + 
				"			*   http://developer.android.com/guide/webapps/targeting.html\r\n" + 
				"			*   http://pugetworks.com/2011/04/css-media-queries-for-targeting-different-mobile-devices/ ;\r\n" + 
				"			=====================================================*/\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:.75){\r\n" + 
				"				/* Put CSS for low density (ldpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:1){\r\n" + 
				"				/* Put CSS for medium density (mdpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:1.5){\r\n" + 
				"				/* Put CSS for high density (hdpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			/* end Android targeting */\r\n" + 
				"			/* CONDITIONS FOR IOS DEVICES ONLY\r\n" + 
				"			=====================================================*/\r\n" + 
				"			@media only screen and (min-device-width : 320px) and (max-device-width:568px) {\r\n" + 
				"			}\r\n" + 
				"			/* end IOS targeting */\r\n" + 
				"		</style>\r\n" + 
				"	</head>\r\n" + 
				"	<body bgcolor=\"#E1E1E1\" leftmargin=\"0\" marginwidth=\"0\" topmargin=\"0\" marginheight=\"0\" offset=\"0\">\r\n" + 
				"		<center style=\"background-color:#E1E1E1;\">\r\n" + 
				"			<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"bodyTable\" style=\"table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;\">\r\n" + 
				"				<tr>\r\n" + 
				"					<td align=\"center\" valign=\"top\" id=\"bodyCell\">\r\n" + 
				"						<table bgcolor=\"#FFFFFF\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailBody\">\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"color:#FFFFFF;\" bgcolor=\"#3498db\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\" class=\"textContent\">\r\n" + 
				"																		<h1 style=\"color:#FFFFFF;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:35px;font-weight:normal;margin-bottom:5px;text-align:center;\">\r\n" + 
				"																			WaterBridge\r\n" + 
				"																		</h1>\r\n" + 
				"																		<div style=\"text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;\">\r\n" + 
				"																			Qualquer tecnologia suficientemente avançada é indistinguível de magia. Arthur C. Clark.\r\n" + 
				"																		</div>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#F8F8F8\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\">\r\n" + 
				"																		<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																			<tr>\r\n" + 
				"																				<td valign=\"top\" class=\"textContent\">\r\n" + 
				"																					<h3 mc:edit=\"header\" style=\"color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;text-align:left;\">\r\n" + 
				"																						Olá "+nomeNovo+", você solicitou a recuperação de senha e uma nova foi gerada" +
				"																					</h3>\r\n" + 
				"																					<br>\r\n" +
				"																					<div mc:edit=\"body\" style=\"text-align:left;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;\">\r\n" + 
				"																						Usuário: "+usuario+"\r\n" + 
				"																						<br>\r\n" + 
				"																						Senha: "+senha+"\r\n" + 
				"																					</div>\r\n" + 
				"																				</td>\r\n" + 
				"																			</tr>\r\n" + 
				"																		</table>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"										<tr style=\"padding-top:0;\">\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td style=\"padding-top:0;\" align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"50%\" class=\"emailButton\" style=\"background-color: #3498DB;\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"middle\" class=\"buttonContent\" style=\"padding-top:15px;padding-bottom:15px;padding-right:15px;padding-left:15px;\">\r\n" + 
				"																		<a style=\"color:#FFFFFF;text-decoration:none;font-family:Helvetica,Arial,sans-serif;font-size:20px;line-height:135%;\" href=\"http://waterbridge.com.br\" target=\"_blank\">\r\n" + 
				"																			Acessar\r\n" + 
				"																		</a>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"						</table>\r\n" + 
				"						<table bgcolor=\"#E1E1E1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailFooter\">\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td valign=\"top\" bgcolor=\"#E1E1E1\">\r\n" + 
				"																		<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;\">\r\n" + 
				"																			<div>contato@desoltec.com.br - (19) 3645-2883 - <a href=\"http://www.waterbridge.com.br\" target=\"_blank\" style=\"text-decoration:none;color:#828282;\"><span style=\"color:#828282;\">www.waterbridge.com.br</span></a></div>\r\n" + 
				"																			<div>Copyright &#169;2018, Desoltec Engenharia. Todos os direitos reservados.</div>\r\n" + 
				"																		</div>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"						</table>\r\n" + 
				"					</td>\r\n" + 
				"				</tr>\r\n" + 
				"			</table>\r\n" + 
				"		</center>\r\n" + 
				"	</body>\r\n" + 
				"</html>";
	}
	
	public static String corpoAcessoUsuario(User user) {
		
		return  "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"	<head>\r\n" + 
				"		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
				"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\r\n" + 
				"		<meta name=\"format-detection\" content=\"telephone=no\" /> <!-- disable auto telephone linking in iOS -->\r\n" + 
				"		<title>Respmail is a response HTML email designed to work on all major email platforms and smartphones</title>\r\n" + 
				"		<style type=\"text/css\">\r\n" + 
				"			/* RESET STYLES */\r\n" + 
				"			html { background-color:#E1E1E1; margin:0; padding:0; }\r\n" + 
				"			body, #bodyTable, #bodyCell, #bodyCell{height:100% !important; margin:0; padding:0; width:100% !important;font-family:Helvetica, Arial, \"Lucida Grande\", sans-serif;}\r\n" + 
				"			table{border-collapse:collapse;}\r\n" + 
				"			table[id=bodyTable] {width:100%!important;margin:auto;max-width:500px!important;color:#7A7A7A;font-weight:normal;}\r\n" + 
				"			img, a img{border:0; outline:none; text-decoration:none;height:auto; line-height:100%;}\r\n" + 
				"			a {text-decoration:none !important;border-bottom: 1px solid;}\r\n" + 
				"			h1, h2, h3, h4, h5, h6{color:#5F5F5F; font-weight:normal; font-family:Helvetica; font-size:20px; line-height:125%; text-align:Left; letter-spacing:normal;margin-top:0;margin-right:0;margin-bottom:10px;margin-left:0;padding-top:0;padding-bottom:0;padding-left:0;padding-right:0;}\r\n" + 
				"			/* CLIENT-SPECIFIC STYLES */\r\n" + 
				"			.ReadMsgBody{width:100%;} .ExternalClass{width:100%;} /* Force Hotmail/Outlook.com to display emails at full width. */\r\n" + 
				"			.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div{line-height:100%;} /* Force Hotmail/Outlook.com to display line heights normally. */\r\n" + 
				"			table, td{mso-table-lspace:0pt; mso-table-rspace:0pt;} /* Remove spacing between tables in Outlook 2007 and up. */\r\n" + 
				"			#outlook a{padding:0;} /* Force Outlook 2007 and up to provide a \"view in browser\" message. */\r\n" + 
				"			img{-ms-interpolation-mode: bicubic;display:block;outline:none; text-decoration:none;} /* Force IE to smoothly render resized images. */\r\n" + 
				"			body, table, td, p, a, li, blockquote{-ms-text-size-adjust:100%; -webkit-text-size-adjust:100%; font-weight:normal!important;} /* Prevent Windows- and Webkit-based mobile platforms from changing declared text sizes. */\r\n" + 
				"			.ExternalClass td[class=\"ecxflexibleContainerBox\"] h3 {padding-top: 10px !important;} /* Force hotmail to push 2-grid sub headers down */\r\n" + 
				"			/* /\\/\\/\\/\\/\\/\\/\\/\\/ TEMPLATE STYLES /\\/\\/\\/\\/\\/\\/\\/\\/ */\r\n" + 
				"			/* ========== Page Styles ========== */\r\n" + 
				"			h1{display:block;font-size:26px;font-style:normal;font-weight:normal;line-height:100%;}\r\n" + 
				"			h2{display:block;font-size:20px;font-style:normal;font-weight:normal;line-height:120%;}\r\n" + 
				"			h3{display:block;font-size:17px;font-style:normal;font-weight:normal;line-height:110%;}\r\n" + 
				"			h4{display:block;font-size:18px;font-style:italic;font-weight:normal;line-height:100%;}\r\n" + 
				"			.flexibleImage{height:auto;}\r\n" + 
				"			.linkRemoveBorder{border-bottom:0 !important;}\r\n" + 
				"			table[class=flexibleContainerCellDivider] {padding-bottom:0 !important;padding-top:0 !important;}\r\n" + 
				"			body, #bodyTable{background-color:#E1E1E1;}\r\n" + 
				"			#emailHeader{background-color:#E1E1E1;}\r\n" + 
				"			#emailBody{background-color:#FFFFFF;}\r\n" + 
				"			#emailFooter{background-color:#E1E1E1;}\r\n" + 
				"			.nestedContainer{background-color:#F8F8F8; border:1px solid #CCCCCC;}\r\n" + 
				"			.emailButton{background-color:#205478; border-collapse:separate;}\r\n" + 
				"			.buttonContent{color:#FFFFFF; font-family:Helvetica; font-size:18px; font-weight:bold; line-height:100%; padding:15px; text-align:center;}\r\n" + 
				"			.buttonContent a{color:#FFFFFF; display:block; text-decoration:none!important; border:0!important;}\r\n" + 
				"			.emailCalendar{background-color:#FFFFFF; border:1px solid #CCCCCC;}\r\n" + 
				"			.emailCalendarMonth{background-color:#205478; color:#FFFFFF; font-family:Helvetica, Arial, sans-serif; font-size:16px; font-weight:bold; padding-top:10px; padding-bottom:10px; text-align:center;}\r\n" + 
				"			.emailCalendarDay{color:#205478; font-family:Helvetica, Arial, sans-serif; font-size:60px; font-weight:bold; line-height:100%; padding-top:20px; padding-bottom:20px; text-align:center;}\r\n" + 
				"			.imageContentText {margin-top: 10px;line-height:0;}\r\n" + 
				"			.imageContentText a {line-height:0;}\r\n" + 
				"			#invisibleIntroduction {display:none !important;} /* Removing the introduction text from the view */\r\n" + 
				"			/*FRAMEWORK HACKS & OVERRIDES */\r\n" + 
				"			span[class=ios-color-hack] a {color:#275100!important;text-decoration:none!important;} /* Remove all link colors in IOS (below are duplicates based on the color preference) */\r\n" + 
				"			span[class=ios-color-hack2] a {color:#205478!important;text-decoration:none!important;}\r\n" + 
				"			span[class=ios-color-hack3] a {color:#8B8B8B!important;text-decoration:none!important;}\r\n" + 
				"			/* A nice and clean way to target phone numbers you want clickable and avoid a mobile phone from linking other numbers that look like, but are not phone numbers.  Use these two blocks of code to \"unstyle\" any numbers that may be linked.  The second block gives you a class to apply with a span tag to the numbers you would like linked and styled.\r\n" + 
				"			Inspired by Campaign Monitor's article on using phone numbers in email: http://www.campaignmonitor.com/blog/post/3571/using-phone-numbers-in-html-email/.\r\n" + 
				"			*/\r\n" + 
				"			.a[href^=\"tel\"], a[href^=\"sms\"] {text-decoration:none!important;color:#606060!important;pointer-events:none!important;cursor:default!important;}\r\n" + 
				"			.mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {text-decoration:none!important;color:#606060!important;pointer-events:auto!important;cursor:default!important;}\r\n" + 
				"			/* MOBILE STYLES */\r\n" + 
				"			@media only screen and (max-width: 480px){\r\n" + 
				"				/*////// CLIENT-SPECIFIC STYLES //////*/\r\n" + 
				"				body{width:100% !important; min-width:100% !important;} /* Force iOS Mail to render the email at full width. */\r\n" + 
				"				/* FRAMEWORK STYLES */\r\n" + 
				"				/*\r\n" + 
				"				CSS selectors are written in attribute\r\n" + 
				"				selector format to prevent Yahoo Mail\r\n" + 
				"				from rendering media query styles on\r\n" + 
				"				desktop.\r\n" + 
				"				*/\r\n" + 
				"				/*td[class=\"textContent\"], td[class=\"flexibleContainerCell\"] { width: 100%; padding-left: 10px !important; padding-right: 10px !important; }*/\r\n" + 
				"				table[id=\"emailHeader\"],\r\n" + 
				"				table[id=\"emailBody\"],\r\n" + 
				"				table[id=\"emailFooter\"],\r\n" + 
				"				table[class=\"flexibleContainer\"],\r\n" + 
				"				td[class=\"flexibleContainerCell\"] {width:100% !important;}\r\n" + 
				"				td[class=\"flexibleContainerBox\"], td[class=\"flexibleContainerBox\"] table {display: block;width: 100%;text-align: left;}\r\n" + 
				"				/*\r\n" + 
				"				The following style rule makes any\r\n" + 
				"				image classed with 'flexibleImage'\r\n" + 
				"				fluid when the query activates.\r\n" + 
				"				Make sure you add an inline max-width\r\n" + 
				"				to those images to prevent them\r\n" + 
				"				from blowing out.\r\n" + 
				"				*/\r\n" + 
				"				td[class=\"imageContent\"] img {height:auto !important; width:100% !important; max-width:100% !important; }\r\n" + 
				"				img[class=\"flexibleImage\"]{height:auto !important; width:100% !important;max-width:100% !important;}\r\n" + 
				"				img[class=\"flexibleImageSmall\"]{height:auto !important; width:auto !important;}\r\n" + 
				"				/*\r\n" + 
				"				Create top space for every second element in a block\r\n" + 
				"				*/\r\n" + 
				"				table[class=\"flexibleContainerBoxNext\"]{padding-top: 10px !important;}\r\n" + 
				"				/*\r\n" + 
				"				Make buttons in the email span the\r\n" + 
				"				full width of their container, allowing\r\n" + 
				"				for left- or right-handed ease of use.\r\n" + 
				"				*/\r\n" + 
				"				table[class=\"emailButton\"]{width:100% !important;}\r\n" + 
				"				td[class=\"buttonContent\"]{padding:0 !important;}\r\n" + 
				"				td[class=\"buttonContent\"] a{padding:15px !important;}\r\n" + 
				"			}\r\n" + 
				"			/*  CONDITIONS FOR ANDROID DEVICES ONLY\r\n" + 
				"			*   http://developer.android.com/guide/webapps/targeting.html\r\n" + 
				"			*   http://pugetworks.com/2011/04/css-media-queries-for-targeting-different-mobile-devices/ ;\r\n" + 
				"			=====================================================*/\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:.75){\r\n" + 
				"				/* Put CSS for low density (ldpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:1){\r\n" + 
				"				/* Put CSS for medium density (mdpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:1.5){\r\n" + 
				"				/* Put CSS for high density (hdpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			/* end Android targeting */\r\n" + 
				"			/* CONDITIONS FOR IOS DEVICES ONLY\r\n" + 
				"			=====================================================*/\r\n" + 
				"			@media only screen and (min-device-width : 320px) and (max-device-width:568px) {\r\n" + 
				"			}\r\n" + 
				"			/* end IOS targeting */\r\n" + 
				"		</style>\r\n" + 
				"	</head>\r\n" + 
				"	<body bgcolor=\"#E1E1E1\" leftmargin=\"0\" marginwidth=\"0\" topmargin=\"0\" marginheight=\"0\" offset=\"0\">\r\n" + 
				"		<center style=\"background-color:#E1E1E1;\">\r\n" + 
				"			<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"bodyTable\" style=\"table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;\">\r\n" + 
				"				<tr>\r\n" + 
				"					<td align=\"center\" valign=\"top\" id=\"bodyCell\">\r\n" + 
				"						<table bgcolor=\"#FFFFFF\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailBody\">\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"color:#FFFFFF;\" bgcolor=\"#3498db\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\" class=\"textContent\">\r\n" + 
				"																		<h1 style=\"color:#FFFFFF;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:35px;font-weight:normal;margin-bottom:5px;text-align:center;\">\r\n" + 
				"																			WaterBridge\r\n" + 
				"																		</h1>\r\n" + 
				"																		<div style=\"text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;\">\r\n" + 
				"																			Qualquer tecnologia suficientemente avançada é indistinguível de magia. Arthur C. Clark.\r\n" + 
				"																		</div>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#F8F8F8\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\">\r\n" + 
				"																		<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																			<tr>\r\n" + 
				"																				<td valign=\"top\" class=\"textContent\">\r\n" + 
				"																					<h3 mc:edit=\"header\" style=\"color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;text-align:left;\">\r\n" + 
				"																						Ol&aacute; " + user.getNome().split("\\s+")[0] + ", seja bem-vindo ao nosso sistema</h3>\r\n" + 
				"																					<div mc:edit=\"body\" style=\"text-align:left;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;\">\r\n" + 
				"																						Informamos que seu cadastro foi realizado com sucesso\r\n" + 
				"																					</div>\r\n" + 
				"																				</td>\r\n" + 
				"																			</tr>\r\n" + 
				"																		</table>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table class=\"flexibleContainerCellDivider\" border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\" style=\"padding-top:0px;padding-bottom:0px;\">\r\n" + 
				"																		<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																			<tr>\r\n" + 
				"																				<td align=\"center\" valign=\"top\" style=\"border-top:1px solid #C8C8C8;\"></td>\r\n" + 
				"																			</tr>\r\n" + 
				"																		</table>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#F8F8F8\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\">\r\n" + 
				"																		<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																			<tr>\r\n" + 
				"																				<td valign=\"top\" class=\"textContent\">\r\n" + 
				"																					<h3 mc:edit=\"header\" style=\"color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;text-align:left;\">\r\n" + 
				"																						Seus dados para acesso\r\n" + 
				"																					<div mc:edit=\"body\" style=\"text-align:left;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;\">\r\n" + 
				"																						Usu&aacute;rio: " + user.getUsuario() + "\r\n" + 
				"																						<br>\r\n" + 
				"																						Senha: " + user.getPass().getPass() + "\r\n" +
				"																						<br><br>\r\n" +
				"																						* Se desejar, você pode alterar sua senha através do menu Perfil\r\n" +
				"																					</div>\r\n" +				
				"																				</td>\r\n" + 
				"																			</tr>\r\n" + 
				"																		</table>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"										<tr style=\"padding-top:0;\">\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td style=\"padding-top:0;\" align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"50%\" class=\"emailButton\" style=\"background-color: #3498DB;\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"middle\" class=\"buttonContent\" style=\"padding-top:15px;padding-bottom:15px;padding-right:15px;padding-left:15px;\">\r\n" + 
				"																		<a style=\"color:#FFFFFF;text-decoration:none;font-family:Helvetica,Arial,sans-serif;font-size:20px;line-height:135%;\" href=\"http://waterbridge.com.br\" target=\"_blank\">\r\n" + 
				"																			Acessar\r\n" + 
				"																		</a>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"						</table>\r\n" + 
				"						<table bgcolor=\"#E1E1E1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailFooter\">\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td valign=\"top\" bgcolor=\"#E1E1E1\">\r\n" + 
				"																		<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;\">\r\n" + 
				"																			<div>contato@desoltec.com.br - (19) 3645-2883 - <a href=\"http://www.waterbridge.com.br\" target=\"_blank\" style=\"text-decoration:none;color:#828282;\"><span style=\"color:#828282;\">www.waterbridge.com.br</span></a></div>\r\n" + 
				"																			<div>Copyright &#169;2018, Desoltec Engenharia. Todos os direitos reservados.</div>\r\n" + 
				"																		</div>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"						</table>\r\n" + 
				"					</td>\r\n" + 
				"				</tr>\r\n" + 
				"			</table>\r\n" + 
				"		</center>\r\n" + 
				"	</body>\r\n" + 
				"</html>";
	}

	public static String corpoEmailCadastro(String nome, String usuario, String senha) {
		String usuarioQuebrado[] = nome.split("\\s+");
		String nomeNovo = usuarioQuebrado[0];
		
		return  "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"	<head>\r\n" + 
				"		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
				"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\r\n" + 
				"		<meta name=\"format-detection\" content=\"telephone=no\" /> <!-- disable auto telephone linking in iOS -->\r\n" + 
				"		<title>Respmail is a response HTML email designed to work on all major email platforms and smartphones</title>\r\n" + 
				"		<style type=\"text/css\">\r\n" + 
				"			/* RESET STYLES */\r\n" + 
				"			html { background-color:#E1E1E1; margin:0; padding:0; }\r\n" + 
				"			body, #bodyTable, #bodyCell, #bodyCell{height:100% !important; margin:0; padding:0; width:100% !important;font-family:Helvetica, Arial, \"Lucida Grande\", sans-serif;}\r\n" + 
				"			table{border-collapse:collapse;}\r\n" + 
				"			table[id=bodyTable] {width:100%!important;margin:auto;max-width:500px!important;color:#7A7A7A;font-weight:normal;}\r\n" + 
				"			img, a img{border:0; outline:none; text-decoration:none;height:auto; line-height:100%;}\r\n" + 
				"			a {text-decoration:none !important;border-bottom: 1px solid;}\r\n" + 
				"			h1, h2, h3, h4, h5, h6{color:#5F5F5F; font-weight:normal; font-family:Helvetica; font-size:20px; line-height:125%; text-align:Left; letter-spacing:normal;margin-top:0;margin-right:0;margin-bottom:10px;margin-left:0;padding-top:0;padding-bottom:0;padding-left:0;padding-right:0;}\r\n" + 
				"			/* CLIENT-SPECIFIC STYLES */\r\n" + 
				"			.ReadMsgBody{width:100%;} .ExternalClass{width:100%;} /* Force Hotmail/Outlook.com to display emails at full width. */\r\n" + 
				"			.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div{line-height:100%;} /* Force Hotmail/Outlook.com to display line heights normally. */\r\n" + 
				"			table, td{mso-table-lspace:0pt; mso-table-rspace:0pt;} /* Remove spacing between tables in Outlook 2007 and up. */\r\n" + 
				"			#outlook a{padding:0;} /* Force Outlook 2007 and up to provide a \"view in browser\" message. */\r\n" + 
				"			img{-ms-interpolation-mode: bicubic;display:block;outline:none; text-decoration:none;} /* Force IE to smoothly render resized images. */\r\n" + 
				"			body, table, td, p, a, li, blockquote{-ms-text-size-adjust:100%; -webkit-text-size-adjust:100%; font-weight:normal!important;} /* Prevent Windows- and Webkit-based mobile platforms from changing declared text sizes. */\r\n" + 
				"			.ExternalClass td[class=\"ecxflexibleContainerBox\"] h3 {padding-top: 10px !important;} /* Force hotmail to push 2-grid sub headers down */\r\n" + 
				"			/* /\\/\\/\\/\\/\\/\\/\\/\\/ TEMPLATE STYLES /\\/\\/\\/\\/\\/\\/\\/\\/ */\r\n" + 
				"			/* ========== Page Styles ========== */\r\n" + 
				"			h1{display:block;font-size:26px;font-style:normal;font-weight:normal;line-height:100%;}\r\n" + 
				"			h2{display:block;font-size:20px;font-style:normal;font-weight:normal;line-height:120%;}\r\n" + 
				"			h3{display:block;font-size:17px;font-style:normal;font-weight:normal;line-height:110%;}\r\n" + 
				"			h4{display:block;font-size:18px;font-style:italic;font-weight:normal;line-height:100%;}\r\n" + 
				"			.flexibleImage{height:auto;}\r\n" + 
				"			.linkRemoveBorder{border-bottom:0 !important;}\r\n" + 
				"			table[class=flexibleContainerCellDivider] {padding-bottom:0 !important;padding-top:0 !important;}\r\n" + 
				"			body, #bodyTable{background-color:#E1E1E1;}\r\n" + 
				"			#emailHeader{background-color:#E1E1E1;}\r\n" + 
				"			#emailBody{background-color:#FFFFFF;}\r\n" + 
				"			#emailFooter{background-color:#E1E1E1;}\r\n" + 
				"			.nestedContainer{background-color:#F8F8F8; border:1px solid #CCCCCC;}\r\n" + 
				"			.emailButton{background-color:#205478; border-collapse:separate;}\r\n" + 
				"			.buttonContent{color:#FFFFFF; font-family:Helvetica; font-size:18px; font-weight:bold; line-height:100%; padding:15px; text-align:center;}\r\n" + 
				"			.buttonContent a{color:#FFFFFF; display:block; text-decoration:none!important; border:0!important;}\r\n" + 
				"			.emailCalendar{background-color:#FFFFFF; border:1px solid #CCCCCC;}\r\n" + 
				"			.emailCalendarMonth{background-color:#205478; color:#FFFFFF; font-family:Helvetica, Arial, sans-serif; font-size:16px; font-weight:bold; padding-top:10px; padding-bottom:10px; text-align:center;}\r\n" + 
				"			.emailCalendarDay{color:#205478; font-family:Helvetica, Arial, sans-serif; font-size:60px; font-weight:bold; line-height:100%; padding-top:20px; padding-bottom:20px; text-align:center;}\r\n" + 
				"			.imageContentText {margin-top: 10px;line-height:0;}\r\n" + 
				"			.imageContentText a {line-height:0;}\r\n" + 
				"			#invisibleIntroduction {display:none !important;} /* Removing the introduction text from the view */\r\n" + 
				"			/*FRAMEWORK HACKS & OVERRIDES */\r\n" + 
				"			span[class=ios-color-hack] a {color:#275100!important;text-decoration:none!important;} /* Remove all link colors in IOS (below are duplicates based on the color preference) */\r\n" + 
				"			span[class=ios-color-hack2] a {color:#205478!important;text-decoration:none!important;}\r\n" + 
				"			span[class=ios-color-hack3] a {color:#8B8B8B!important;text-decoration:none!important;}\r\n" + 
				"			/* A nice and clean way to target phone numbers you want clickable and avoid a mobile phone from linking other numbers that look like, but are not phone numbers.  Use these two blocks of code to \"unstyle\" any numbers that may be linked.  The second block gives you a class to apply with a span tag to the numbers you would like linked and styled.\r\n" + 
				"			Inspired by Campaign Monitor's article on using phone numbers in email: http://www.campaignmonitor.com/blog/post/3571/using-phone-numbers-in-html-email/.\r\n" + 
				"			*/\r\n" + 
				"			.a[href^=\"tel\"], a[href^=\"sms\"] {text-decoration:none!important;color:#606060!important;pointer-events:none!important;cursor:default!important;}\r\n" + 
				"			.mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {text-decoration:none!important;color:#606060!important;pointer-events:auto!important;cursor:default!important;}\r\n" + 
				"			/* MOBILE STYLES */\r\n" + 
				"			@media only screen and (max-width: 480px){\r\n" + 
				"				/*////// CLIENT-SPECIFIC STYLES //////*/\r\n" + 
				"				body{width:100% !important; min-width:100% !important;} /* Force iOS Mail to render the email at full width. */\r\n" + 
				"				/* FRAMEWORK STYLES */\r\n" + 
				"				/*\r\n" + 
				"				CSS selectors are written in attribute\r\n" + 
				"				selector format to prevent Yahoo Mail\r\n" + 
				"				from rendering media query styles on\r\n" + 
				"				desktop.\r\n" + 
				"				*/\r\n" + 
				"				/*td[class=\"textContent\"], td[class=\"flexibleContainerCell\"] { width: 100%; padding-left: 10px !important; padding-right: 10px !important; }*/\r\n" + 
				"				table[id=\"emailHeader\"],\r\n" + 
				"				table[id=\"emailBody\"],\r\n" + 
				"				table[id=\"emailFooter\"],\r\n" + 
				"				table[class=\"flexibleContainer\"],\r\n" + 
				"				td[class=\"flexibleContainerCell\"] {width:100% !important;}\r\n" + 
				"				td[class=\"flexibleContainerBox\"], td[class=\"flexibleContainerBox\"] table {display: block;width: 100%;text-align: left;}\r\n" + 
				"				/*\r\n" + 
				"				The following style rule makes any\r\n" + 
				"				image classed with 'flexibleImage'\r\n" + 
				"				fluid when the query activates.\r\n" + 
				"				Make sure you add an inline max-width\r\n" + 
				"				to those images to prevent them\r\n" + 
				"				from blowing out.\r\n" + 
				"				*/\r\n" + 
				"				td[class=\"imageContent\"] img {height:auto !important; width:100% !important; max-width:100% !important; }\r\n" + 
				"				img[class=\"flexibleImage\"]{height:auto !important; width:100% !important;max-width:100% !important;}\r\n" + 
				"				img[class=\"flexibleImageSmall\"]{height:auto !important; width:auto !important;}\r\n" + 
				"				/*\r\n" + 
				"				Create top space for every second element in a block\r\n" + 
				"				*/\r\n" + 
				"				table[class=\"flexibleContainerBoxNext\"]{padding-top: 10px !important;}\r\n" + 
				"				/*\r\n" + 
				"				Make buttons in the email span the\r\n" + 
				"				full width of their container, allowing\r\n" + 
				"				for left- or right-handed ease of use.\r\n" + 
				"				*/\r\n" + 
				"				table[class=\"emailButton\"]{width:100% !important;}\r\n" + 
				"				td[class=\"buttonContent\"]{padding:0 !important;}\r\n" + 
				"				td[class=\"buttonContent\"] a{padding:15px !important;}\r\n" + 
				"			}\r\n" + 
				"			/*  CONDITIONS FOR ANDROID DEVICES ONLY\r\n" + 
				"			*   http://developer.android.com/guide/webapps/targeting.html\r\n" + 
				"			*   http://pugetworks.com/2011/04/css-media-queries-for-targeting-different-mobile-devices/ ;\r\n" + 
				"			=====================================================*/\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:.75){\r\n" + 
				"				/* Put CSS for low density (ldpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:1){\r\n" + 
				"				/* Put CSS for medium density (mdpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:1.5){\r\n" + 
				"				/* Put CSS for high density (hdpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			/* end Android targeting */\r\n" + 
				"			/* CONDITIONS FOR IOS DEVICES ONLY\r\n" + 
				"			=====================================================*/\r\n" + 
				"			@media only screen and (min-device-width : 320px) and (max-device-width:568px) {\r\n" + 
				"			}\r\n" + 
				"			/* end IOS targeting */\r\n" + 
				"		</style>\r\n" + 
				"	</head>\r\n" + 
				"	<body bgcolor=\"#E1E1E1\" leftmargin=\"0\" marginwidth=\"0\" topmargin=\"0\" marginheight=\"0\" offset=\"0\">\r\n" + 
				"		<center style=\"background-color:#E1E1E1;\">\r\n" + 
				"			<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"bodyTable\" style=\"table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;\">\r\n" + 
				"				<tr>\r\n" + 
				"					<td align=\"center\" valign=\"top\" id=\"bodyCell\">\r\n" + 
				"						<table bgcolor=\"#FFFFFF\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailBody\">\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"color:#FFFFFF;\" bgcolor=\"#3498db\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\" class=\"textContent\">\r\n" + 
				"																		<h1 style=\"color:#FFFFFF;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:35px;font-weight:normal;margin-bottom:5px;text-align:center;\">\r\n" + 
				"																			WaterBridge\r\n" + 
				"																		</h1>\r\n" + 
				"																		<div style=\"text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;\">\r\n" + 
				"																			Qualquer tecnologia suficientemente avançada é indistinguível de magia. Arthur C. Clark.\r\n" + 
				"																		</div>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#F8F8F8\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\">\r\n" + 
				"																		<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																			<tr>\r\n" + 
				"																				<td valign=\"top\" class=\"textContent\">\r\n" + 
				"																					<h3 mc:edit=\"header\" style=\"color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;text-align:left;\">\r\n" + 
				"																						Ol&aacute; "+nomeNovo+", seja bem-vindo ao nosso sistema</h3>\r\n" + 
				"																					<div mc:edit=\"body\" style=\"text-align:left;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;\">\r\n" + 
				"																						Informamos que seu cadastro foi realizado com sucesso\r\n" + 
				"																					</div>\r\n" + 
				"																				</td>\r\n" + 
				"																			</tr>\r\n" + 
				"																		</table>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table class=\"flexibleContainerCellDivider\" border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\" style=\"padding-top:0px;padding-bottom:0px;\">\r\n" + 
				"																		<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																			<tr>\r\n" + 
				"																				<td align=\"center\" valign=\"top\" style=\"border-top:1px solid #C8C8C8;\"></td>\r\n" + 
				"																			</tr>\r\n" + 
				"																		</table>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#F8F8F8\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\">\r\n" + 
				"																		<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																			<tr>\r\n" + 
				"																				<td valign=\"top\" class=\"textContent\">\r\n" + 
				"																					<h3 mc:edit=\"header\" style=\"color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;text-align:left;\">\r\n" + 
				"																						Seus dados para acesso\r\n" + 
				"																					<div mc:edit=\"body\" style=\"text-align:left;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;\">\r\n" + 
				"																						Usu&aacute;rio: "+usuario+"\r\n" + 
				"																						<br>\r\n" + 
				"																						Senha: "+senha+"\r\n" + 
				"																					</div>\r\n" +				
				"																				</td>\r\n" + 
				"																			</tr>\r\n" + 
				"																		</table>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"										<tr style=\"padding-top:0;\">\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td style=\"padding-top:0;\" align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"50%\" class=\"emailButton\" style=\"background-color: #3498DB;\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"middle\" class=\"buttonContent\" style=\"padding-top:15px;padding-bottom:15px;padding-right:15px;padding-left:15px;\">\r\n" + 
				"																		<a style=\"color:#FFFFFF;text-decoration:none;font-family:Helvetica,Arial,sans-serif;font-size:20px;line-height:135%;\" href=\"http://waterbridge.com.br\" target=\"_blank\">\r\n" + 
				"																			Acessar\r\n" + 
				"																		</a>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"						</table>\r\n" + 
				"						<table bgcolor=\"#E1E1E1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailFooter\">\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td valign=\"top\" bgcolor=\"#E1E1E1\">\r\n" + 
				"																		<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;\">\r\n" + 
				"																			<div>contato@desoltec.com.br - (19) 3645-2883 - <a href=\"http://www.waterbridge.com.br\" target=\"_blank\" style=\"text-decoration:none;color:#828282;\"><span style=\"color:#828282;\">www.waterbridge.com.br</span></a></div>\r\n" + 
				"																			<div>Copyright &#169;2018, Desoltec Engenharia. Todos os direitos reservados.</div>\r\n" + 
				"																		</div>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"						</table>\r\n" + 
				"					</td>\r\n" + 
				"				</tr>\r\n" + 
				"			</table>\r\n" + 
				"		</center>\r\n" + 
				"	</body>\r\n" + 
				"</html>";
	}
	
	public static String corpoEmailToken(List<Bridge> lista) throws ParseException {
		String retorno = 
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"	<head>\r\n" + 
				"		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
				"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\r\n" + 
				"		<meta name=\"format-detection\" content=\"telephone=no\" /> <!-- disable auto telephone linking in iOS -->\r\n" + 
				"		<title>WaterBridge</title>\r\n" + 
				"		<style type=\"text/css\">\r\n" + 
				"			/* RESET STYLES */\r\n" + 
				"			html { background-color:#E1E1E1; margin:0; padding:0; }\r\n" + 
				"			body, #bodyTable, #bodyCell, #bodyCell{height:100% !important; margin:0; padding:0; width:100% !important;font-family:Helvetica, Arial, \"Lucida Grande\", sans-serif;}\r\n" + 
				"			table{border-collapse:collapse;}\r\n" + 
				"			table[id=bodyTable] {width:100%!important;margin:auto;max-width:500px!important;color:#7A7A7A;font-weight:normal;}\r\n" + 
				"			img, a img{border:0; outline:none; text-decoration:none;height:auto; line-height:100%;}\r\n" + 
				"			a {text-decoration:none !important;border-bottom: 1px solid;}\r\n" + 
				"			h1, h2, h3, h4, h5, h6{color:#5F5F5F; font-weight:normal; font-family:Helvetica; font-size:20px; line-height:125%; text-align:Left; letter-spacing:normal;margin-top:0;margin-right:0;margin-bottom:10px;margin-left:0;padding-top:0;padding-bottom:0;padding-left:0;padding-right:0;}\r\n" + 
				"			/* CLIENT-SPECIFIC STYLES */\r\n" + 
				"			.ReadMsgBody{width:100%;} .ExternalClass{width:100%;} /* Force Hotmail/Outlook.com to display emails at full width. */\r\n" + 
				"			.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div{line-height:100%;} /* Force Hotmail/Outlook.com to display line heights normally. */\r\n" + 
				"			table, td{mso-table-lspace:0pt; mso-table-rspace:0pt;} /* Remove spacing between tables in Outlook 2007 and up. */\r\n" + 
				"			#outlook a{padding:0;} /* Force Outlook 2007 and up to provide a \"view in browser\" message. */\r\n" + 
				"			img{-ms-interpolation-mode: bicubic;display:block;outline:none; text-decoration:none;} /* Force IE to smoothly render resized images. */\r\n" + 
				"			body, table, td, p, a, li, blockquote{-ms-text-size-adjust:100%; -webkit-text-size-adjust:100%; font-weight:normal!important;} /* Prevent Windows- and Webkit-based mobile platforms from changing declared text sizes. */\r\n" + 
				"			.ExternalClass td[class=\"ecxflexibleContainerBox\"] h3 {padding-top: 10px !important;} /* Force hotmail to push 2-grid sub headers down */\r\n" + 
				"			/* /\\/\\/\\/\\/\\/\\/\\/\\/ TEMPLATE STYLES /\\/\\/\\/\\/\\/\\/\\/\\/ */\r\n" + 
				"			/* ========== Page Styles ========== */\r\n" + 
				"			h1{display:block;font-size:26px;font-style:normal;font-weight:normal;line-height:100%;}\r\n" + 
				"			h2{display:block;font-size:20px;font-style:normal;font-weight:normal;line-height:120%;}\r\n" + 
				"			h3{display:block;font-size:17px;font-style:normal;font-weight:normal;line-height:110%;}\r\n" + 
				"			h4{display:block;font-size:18px;font-style:italic;font-weight:normal;line-height:100%;}\r\n" + 
				"			.flexibleImage{height:auto;}\r\n" + 
				"			.linkRemoveBorder{border-bottom:0 !important;}\r\n" + 
				"			table[class=flexibleContainerCellDivider] {padding-bottom:0 !important;padding-top:0 !important;}\r\n" + 
				"			body, #bodyTable{background-color:#E1E1E1;}\r\n" + 
				"			#emailHeader{background-color:#E1E1E1;}\r\n" + 
				"			#emailBody{background-color:#FFFFFF;}\r\n" + 
				"			#emailFooter{background-color:#E1E1E1;}\r\n" + 
				"			.nestedContainer{background-color:#F8F8F8; border:1px solid #CCCCCC;}\r\n" + 
				"			.emailButton{background-color:#205478; border-collapse:separate;}\r\n" + 
				"			.buttonContent{color:#FFFFFF; font-family:Helvetica; font-size:18px; font-weight:bold; line-height:100%; padding:15px; text-align:center;}\r\n" + 
				"			.buttonContent a{color:#FFFFFF; display:block; text-decoration:none!important; border:0!important;}\r\n" + 
				"			.emailCalendar{background-color:#FFFFFF; border:1px solid #CCCCCC;}\r\n" + 
				"			.emailCalendarMonth{background-color:#205478; color:#FFFFFF; font-family:Helvetica, Arial, sans-serif; font-size:16px; font-weight:bold; padding-top:10px; padding-bottom:10px; text-align:center;}\r\n" + 
				"			.emailCalendarDay{color:#205478; font-family:Helvetica, Arial, sans-serif; font-size:60px; font-weight:bold; line-height:100%; padding-top:20px; padding-bottom:20px; text-align:center;}\r\n" + 
				"			.imageContentText {margin-top: 10px;line-height:0;}\r\n" + 
				"			.imageContentText a {line-height:0;}\r\n" + 
				"			#invisibleIntroduction {display:none !important;} /* Removing the introduction text from the view */\r\n" + 
				"			/*FRAMEWORK HACKS & OVERRIDES */\r\n" + 
				"			span[class=ios-color-hack] a {color:#275100!important;text-decoration:none!important;} /* Remove all link colors in IOS (below are duplicates based on the color preference) */\r\n" + 
				"			span[class=ios-color-hack2] a {color:#205478!important;text-decoration:none!important;}\r\n" + 
				"			span[class=ios-color-hack3] a {color:#8B8B8B!important;text-decoration:none!important;}\r\n" + 
				"			/* A nice and clean way to target phone numbers you want clickable and avoid a mobile phone from linking other numbers that look like, but are not phone numbers.  Use these two blocks of code to \"unstyle\" any numbers that may be linked.  The second block gives you a class to apply with a span tag to the numbers you would like linked and styled.\r\n" + 
				"			Inspired by Campaign Monitor's article on using phone numbers in email: http://www.campaignmonitor.com/blog/post/3571/using-phone-numbers-in-html-email/.\r\n" + 
				"			*/\r\n" + 
				"			.a[href^=\"tel\"], a[href^=\"sms\"] {text-decoration:none!important;color:#606060!important;pointer-events:none!important;cursor:default!important;}\r\n" + 
				"			.mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {text-decoration:none!important;color:#606060!important;pointer-events:auto!important;cursor:default!important;}\r\n" + 
				"			/* MOBILE STYLES */\r\n" + 
				"			@media only screen and (max-width: 480px){\r\n" + 
				"				/*////// CLIENT-SPECIFIC STYLES //////*/\r\n" + 
				"				body{width:100% !important; min-width:100% !important;} /* Force iOS Mail to render the email at full width. */\r\n" + 
				"				/* FRAMEWORK STYLES */\r\n" + 
				"				/*\r\n" + 
				"				CSS selectors are written in attribute\r\n" + 
				"				selector format to prevent Yahoo Mail\r\n" + 
				"				from rendering media query styles on\r\n" + 
				"				desktop.\r\n" + 
				"				*/\r\n" + 
				"				/*td[class=\"textContent\"], td[class=\"flexibleContainerCell\"] { width: 100%; padding-left: 10px !important; padding-right: 10px !important; }*/\r\n" + 
				"				table[id=\"emailHeader\"],\r\n" + 
				"				table[id=\"emailBody\"],\r\n" + 
				"				table[id=\"emailFooter\"],\r\n" + 
				"				table[class=\"flexibleContainer\"],\r\n" + 
				"				td[class=\"flexibleContainerCell\"] {width:100% !important;}\r\n" + 
				"				td[class=\"flexibleContainerBox\"], td[class=\"flexibleContainerBox\"] table {display: block;width: 100%;text-align: left;}\r\n" + 
				"				/*\r\n" + 
				"				The following style rule makes any\r\n" + 
				"				image classed with 'flexibleImage'\r\n" + 
				"				fluid when the query activates.\r\n" + 
				"				Make sure you add an inline max-width\r\n" + 
				"				to those images to prevent them\r\n" + 
				"				from blowing out.\r\n" + 
				"				*/\r\n" + 
				"				td[class=\"imageContent\"] img {height:auto !important; width:100% !important; max-width:100% !important; }\r\n" + 
				"				img[class=\"flexibleImage\"]{height:auto !important; width:100% !important;max-width:100% !important;}\r\n" + 
				"				img[class=\"flexibleImageSmall\"]{height:auto !important; width:auto !important;}\r\n" + 
				"				/*\r\n" + 
				"				Create top space for every second element in a block\r\n" + 
				"				*/\r\n" + 
				"				table[class=\"flexibleContainerBoxNext\"]{padding-top: 10px !important;}\r\n" + 
				"				/*\r\n" + 
				"				Make buttons in the email span the\r\n" + 
				"				full width of their container, allowing\r\n" + 
				"				for left- or right-handed ease of use.\r\n" + 
				"				*/\r\n" + 
				"				table[class=\"emailButton\"]{width:100% !important;}\r\n" + 
				"				td[class=\"buttonContent\"]{padding:0 !important;}\r\n" + 
				"				td[class=\"buttonContent\"] a{padding:15px !important;}\r\n" + 
				"			}\r\n" + 
				"			/*  CONDITIONS FOR ANDROID DEVICES ONLY\r\n" + 
				"			*   http://developer.android.com/guide/webapps/targeting.html\r\n" + 
				"			*   http://pugetworks.com/2011/04/css-media-queries-for-targeting-different-mobile-devices/ ;\r\n" + 
				"			=====================================================*/\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:.75){\r\n" + 
				"				/* Put CSS for low density (ldpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:1){\r\n" + 
				"				/* Put CSS for medium density (mdpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:1.5){\r\n" + 
				"				/* Put CSS for high density (hdpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			/* end Android targeting */\r\n" + 
				"			/* CONDITIONS FOR IOS DEVICES ONLY\r\n" + 
				"			=====================================================*/\r\n" + 
				"			@media only screen and (min-device-width : 320px) and (max-device-width:568px) {\r\n" + 
				"			}\r\n" + 
				"			/* end IOS targeting */\r\n" + 
				"		</style>\r\n" + 
				"	</head>\r\n" + 
				"	<body bgcolor=\"#E1E1E1\" leftmargin=\"0\" marginwidth=\"0\" topmargin=\"0\" marginheight=\"0\" offset=\"0\">\r\n" + 
				"		<center style=\"background-color:#E1E1E1;\">\r\n" + 
				"			<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"bodyTable\" style=\"table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;\">\r\n" + 
				"				<tr>\r\n" + 
				"					<td align=\"center\" valign=\"top\" id=\"bodyCell\">\r\n" + 
				"						<table bgcolor=\"#FFFFFF\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailBody\">\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"color:#FFFFFF;\" bgcolor=\"#3498db\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\" class=\"textContent\">\r\n" + 
				"																		<h1 style=\"color:#FFFFFF;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:35px;font-weight:normal;margin-bottom:5px;text-align:center;\">\r\n" + 
				"																			WaterBridge\r\n" + 
				"																		</h1>\r\n" + 
				"																		<div style=\"text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;\">\r\n" + 
				"																			Qualquer tecnologia suficientemente avançada é indistinguível de magia. Arthur C. Clark.\r\n" + 
				"																		</div>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#F8F8F8\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\">\r\n" + 
				"																		<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																			<tr>\r\n" + 
				"																				<td valign=\"top\" class=\"textContent\">\r\n" + 
				"																					<h3 mc:edit=\"header\" style=\"color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;text-align:left;\">\r\n" + 
				"																						Prezado Gerente, abaixo os Devices que irão expirar dentro de 30 dias</h3>\r\n" + 
				"																						<br>\r\n" + 
				"																					<div mc:edit=\"body\" style=\"text-align:left;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;\">\r\n" + 
				"																						<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																							<thead>\r\n" + 
				"																								<tr>\r\n" + 
				"																									<th style=\"width:1%;\">Nº</th>\r\n" + 
				"																									<th style=\"width:10%;\">Device</th>\r\n" + 
				"																									<th style=\"width:10%;\">Data Ativação</th>\r\n" + 
				"																									<th style=\"width:10%;\">Validade Token</th>\r\n" + 
				"																								</tr>\r\n" + 
				"																							</thead>\r\n" + 
				"																							<tbody>\r\n" ;
				SimpleDateFormat formatBanco = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				SimpleDateFormat formatEmail = new SimpleDateFormat("dd/MM/yyyy");
				int i = 1;
				for (Bridge bridge : lista) {
					retorno += 	"<tr>\r\n" +
								"	<td>"+i+"</td>\r\n" + 
								"	<td>"+bridge.getDeviceNum()+"</td>\r\n" + 
								"	<td>"+formatEmail.format(formatBanco.parse(bridge.getDtAtivacao()))+"</td>\r\n" + 
								"	<td>"+bridge.getValidadeToken()+"</td>\r\n" +
								"</tr>\r\n" ;
					i++;
				}
				retorno +="																					</tbody>\r\n" + 
				"																						</table>\r\n" + 
				"																					</div>\r\n" + 
				"																				</td>\r\n" + 
				"																			</tr>\r\n" + 
				"																		</table>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"						</table>\r\n" + 
				"						<table bgcolor=\"#E1E1E1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailFooter\">\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td valign=\"top\" bgcolor=\"#E1E1E1\">\r\n" + 
				"																		<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;\">\r\n" + 
				"																			<div>contato@desoltec.com.br - (19) 3645-2883 - <a href=\"http://www.waterbridge.com.br\" target=\"_blank\" style=\"text-decoration:none;color:#828282;\"><span style=\"color:#828282;\">www.waterbridge.com.br</span></a></div>\r\n" + 
				"																			<div>Copyright &#169;2018, Desoltec Engenharia. Todos os direitos reservados.</div>\r\n" + 
				"																		</div>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"						</table>\r\n" + 
				"					</td>\r\n" + 
				"				</tr>\r\n" + 
				"			</table>\r\n" + 
				"		</center>\r\n" + 
				"	</body>\r\n" + 
				"</html>";
		return retorno;
	}
	
	public static String corpoEmailAlarme(String device, Long idAlarme, String descricaoAlarme) {
		return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"	<head>\r\n" + 
				"		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
				"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\r\n" + 
				"		<meta name=\"format-detection\" content=\"telephone=no\" /> <!-- disable auto telephone linking in iOS -->\r\n" + 
				"		<title>WaterBridge</title>\r\n" + 
				"		<style type=\"text/css\">\r\n" + 
				"			/* RESET STYLES */\r\n" + 
				"			html { background-color:#E1E1E1; margin:0; padding:0; }\r\n" + 
				"			body, #bodyTable, #bodyCell, #bodyCell{height:100% !important; margin:0; padding:0; width:100% !important;font-family:Helvetica, Arial, \"Lucida Grande\", sans-serif;}\r\n" + 
				"			table{border-collapse:collapse;}\r\n" + 
				"			table[id=bodyTable] {width:100%!important;margin:auto;max-width:500px!important;color:#7A7A7A;font-weight:normal;}\r\n" + 
				"			img, a img{border:0; outline:none; text-decoration:none;height:auto; line-height:100%;}\r\n" + 
				"			a {text-decoration:none !important;border-bottom: 1px solid;}\r\n" + 
				"			h1, h2, h3, h4, h5, h6{color:#5F5F5F; font-weight:normal; font-family:Helvetica; font-size:20px; line-height:125%; text-align:Left; letter-spacing:normal;margin-top:0;margin-right:0;margin-bottom:10px;margin-left:0;padding-top:0;padding-bottom:0;padding-left:0;padding-right:0;}\r\n" + 
				"			/* CLIENT-SPECIFIC STYLES */\r\n" + 
				"			.ReadMsgBody{width:100%;} .ExternalClass{width:100%;} /* Force Hotmail/Outlook.com to display emails at full width. */\r\n" + 
				"			.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div{line-height:100%;} /* Force Hotmail/Outlook.com to display line heights normally. */\r\n" + 
				"			table, td{mso-table-lspace:0pt; mso-table-rspace:0pt;} /* Remove spacing between tables in Outlook 2007 and up. */\r\n" + 
				"			#outlook a{padding:0;} /* Force Outlook 2007 and up to provide a \"view in browser\" message. */\r\n" + 
				"			img{-ms-interpolation-mode: bicubic;display:block;outline:none; text-decoration:none;} /* Force IE to smoothly render resized images. */\r\n" + 
				"			body, table, td, p, a, li, blockquote{-ms-text-size-adjust:100%; -webkit-text-size-adjust:100%; font-weight:normal!important;} /* Prevent Windows- and Webkit-based mobile platforms from changing declared text sizes. */\r\n" + 
				"			.ExternalClass td[class=\"ecxflexibleContainerBox\"] h3 {padding-top: 10px !important;} /* Force hotmail to push 2-grid sub headers down */\r\n" + 
				"			/* /\\\\/\\\\/\\\\/\\\\/\\\\/\\\\/\\\\/\\\\/ TEMPLATE STYLES /\\\\/\\\\/\\\\/\\\\/\\\\/\\\\/\\\\/\\\\/ */\r\n" + 
				"			/* ========== Page Styles ========== */\r\n" + 
				"			h1{display:block;font-size:26px;font-style:normal;font-weight:normal;line-height:100%;}\r\n" + 
				"			h2{display:block;font-size:20px;font-style:normal;font-weight:normal;line-height:120%;}\r\n" + 
				"			h3{display:block;font-size:17px;font-style:normal;font-weight:normal;line-height:110%;}\r\n" + 
				"			h4{display:block;font-size:18px;font-style:italic;font-weight:normal;line-height:100%;}\r\n" + 
				"			.flexibleImage{height:auto;}\r\n" + 
				"			.linkRemoveBorder{border-bottom:0 !important;}\r\n" + 
				"			table[class=flexibleContainerCellDivider] {padding-bottom:0 !important;padding-top:0 !important;}\r\n" + 
				"			body, #bodyTable{background-color:#E1E1E1;}\r\n" + 
				"			#emailHeader{background-color:#E1E1E1;}\r\n" + 
				"			#emailBody{background-color:#FFFFFF;}\r\n" + 
				"			#emailFooter{background-color:#E1E1E1;}\r\n" + 
				"			.nestedContainer{background-color:#F8F8F8; border:1px solid #CCCCCC;}\r\n" + 
				"			.emailButton{background-color:#205478; border-collapse:separate;}\r\n" + 
				"			.buttonContent{color:#FFFFFF; font-family:Helvetica; font-size:18px; font-weight:bold; line-height:100%; padding:15px; text-align:center;}\r\n" + 
				"			.buttonContent a{color:#FFFFFF; display:block; text-decoration:none!important; border:0!important;}\r\n" + 
				"			.emailCalendar{background-color:#FFFFFF; border:1px solid #CCCCCC;}\r\n" + 
				"			.emailCalendarMonth{background-color:#205478; color:#FFFFFF; font-family:Helvetica, Arial, sans-serif; font-size:16px; font-weight:bold; padding-top:10px; padding-bottom:10px; text-align:center;}\r\n" + 
				"			.emailCalendarDay{color:#205478; font-family:Helvetica, Arial, sans-serif; font-size:60px; font-weight:bold; line-height:100%; padding-top:20px; padding-bottom:20px; text-align:center;}\r\n" + 
				"			.imageContentText {margin-top: 10px;line-height:0;}\r\n" + 
				"			.imageContentText a {line-height:0;}\r\n" + 
				"			#invisibleIntroduction {display:none !important;} /* Removing the introduction text from the view */\r\n" + 
				"			/*FRAMEWORK HACKS & OVERRIDES */\r\n" + 
				"			span[class=ios-color-hack] a {color:#275100!important;text-decoration:none!important;} /* Remove all link colors in IOS (below are duplicates based on the color preference) */\r\n" + 
				"			span[class=ios-color-hack2] a {color:#205478!important;text-decoration:none!important;}\r\n" + 
				"			span[class=ios-color-hack3] a {color:#8B8B8B!important;text-decoration:none!important;}\r\n" + 
				"			/* A nice and clean way to target phone numbers you want clickable and avoid a mobile phone from linking other numbers that look like, but are not phone numbers.  Use these two blocks of code to \"unstyle\" any numbers that may be linked.  The second block gives you a class to apply with a span tag to the numbers you would like linked and styled.\r\n" + 
				"			Inspired by Campaign Monitor's article on using phone numbers in email: http://www.campaignmonitor.com/blog/post/3571/using-phone-numbers-in-html-email/.\r\n" + 
				"			*/\r\n" + 
				"			.a[href^=\"tel\"], a[href^=\"sms\"] {text-decoration:none!important;color:#606060!important;pointer-events:none!important;cursor:default!important;}\r\n" + 
				"			.mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {text-decoration:none!important;color:#606060!important;pointer-events:auto!important;cursor:default!important;}\r\n" + 
				"			/* MOBILE STYLES */\r\n" + 
				"			@media only screen and (max-width: 480px){\r\n" + 
				"				/*////// CLIENT-SPECIFIC STYLES //////*/\r\n" + 
				"				body{width:100% !important; min-width:100% !important;} /* Force iOS Mail to render the email at full width. */\r\n" + 
				"				/* FRAMEWORK STYLES */\r\n" + 
				"				/*\r\n" + 
				"				CSS selectors are written in attribute\r\n" + 
				"				selector format to prevent Yahoo Mail\r\n" + 
				"				from rendering media query styles on\r\n" + 
				"				desktop.\r\n" + 
				"				*/\r\n" + 
				"				/*td[class=\"textContent\"], td[class=\"flexibleContainerCell\"] { width: 100%; padding-left: 10px !important; padding-right: 10px !important; }*/\r\n" + 
				"				table[id=\"emailHeader\"],\r\n" + 
				"				table[id=\"emailBody\"],\r\n" + 
				"				table[id=\"emailFooter\"],\r\n" + 
				"				table[class=\"flexibleContainer\"],\r\n" + 
				"				td[class=\"flexibleContainerCell\"] {width:100% !important;}\r\n" + 
				"				td[class=\"flexibleContainerBox\"], td[class=\"flexibleContainerBox\"] table {display: block;width: 100%;text-align: left;}\r\n" + 
				"				/*\r\n" + 
				"				The following style rule makes any\r\n" + 
				"				image classed with 'flexibleImage'\r\n" + 
				"				fluid when the query activates.\r\n" + 
				"				Make sure you add an inline max-width\r\n" + 
				"				to those images to prevent them\r\n" + 
				"				from blowing out.\r\n" + 
				"				*/\r\n" + 
				"				td[class=\"imageContent\"] img {height:auto !important; width:100% !important; max-width:100% !important; }\r\n" + 
				"				img[class=\"flexibleImage\"]{height:auto !important; width:100% !important;max-width:100% !important;}\r\n" + 
				"				img[class=\"flexibleImageSmall\"]{height:auto !important; width:auto !important;}\r\n" + 
				"				/*\r\n" + 
				"				Create top space for every second element in a block\r\n" + 
				"				*/\r\n" + 
				"				table[class=\"flexibleContainerBoxNext\"]{padding-top: 10px !important;}\r\n" + 
				"				/*\r\n" + 
				"				Make buttons in the email span the\r\n" + 
				"				full width of their container, allowing\r\n" + 
				"				for left- or right-handed ease of use.\r\n" + 
				"				*/\r\n" + 
				"				table[class=\"emailButton\"]{width:100% !important;}\r\n" + 
				"				td[class=\"buttonContent\"]{padding:0 !important;}\r\n" + 
				"				td[class=\"buttonContent\"] a{padding:15px !important;}\r\n" + 
				"			}\r\n" + 
				"			/*  CONDITIONS FOR ANDROID DEVICES ONLY\r\n" + 
				"			*   http://developer.android.com/guide/webapps/targeting.html\r\n" + 
				"			*   http://pugetworks.com/2011/04/css-media-queries-for-targeting-different-mobile-devices/ ;\r\n" + 
				"			=====================================================*/\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:.75){\r\n" + 
				"				/* Put CSS for low density (ldpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:1){\r\n" + 
				"				/* Put CSS for medium density (mdpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:1.5){\r\n" + 
				"				/* Put CSS for high density (hdpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			/* end Android targeting */\r\n" + 
				"			/* CONDITIONS FOR IOS DEVICES ONLY\r\n" + 
				"			=====================================================*/\r\n" + 
				"			@media only screen and (min-device-width : 320px) and (max-device-width:568px) {\r\n" + 
				"			}\r\n" + 
				"			/* end IOS targeting */\r\n" + 
				"		</style>\r\n" + 
				"	</head>\r\n" + 
				"	<body bgcolor=\"#E1E1E1\" leftmargin=\"0\" marginwidth=\"0\" topmargin=\"0\" marginheight=\"0\" offset=\"0\">\r\n" + 
				"		<center style=\"background-color:#E1E1E1;\">\r\n" + 
				"			<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"bodyTable\" style=\"table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;\">\r\n" + 
				"				<tr>\r\n" + 
				"					<td align=\"center\" valign=\"top\" id=\"bodyCell\">\r\n" + 
				"						<table bgcolor=\"#FFFFFF\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailBody\">\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"color:#FFFFFF;\" bgcolor=\"#3498db\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\" class=\"textContent\">\r\n" + 
				"																		<h1 style=\"color:#FFFFFF;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:35px;font-weight:normal;margin-bottom:5px;text-align:center;\">\r\n" + 
				"																			WaterBridge\r\n" + 
				"																		</h1>\r\n" + 
				"																		<div style=\"text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;\">\r\n" + 
				"																			Qualquer tecnologia suficientemente avançada é indistinguível de magia. Arthur C. Clark.\r\n" + 
				"																		</div>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#F8F8F8\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\">\r\n" + 
				"																		<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																			<tr>\r\n" + 
				"																				<td valign=\"top\" class=\"textContent\">\r\n" + 
				"																					<h3 mc:edit=\"header\" style=\"color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;text-align:left;\">\r\n" + 
				"																						Prezado, abaixo o Device com a ocorrência de alarme na data de hoje "+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" </h3>\r\n" + 
				"																						<br>\r\n" + 
				"																					<div mc:edit=\"body\" style=\"text-align:left;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;\">\r\n" + 
				"																						Device: "+device+" \r\n" + 
				"																						<br>\r\n" + 
				"																						Alarme: "+idAlarme+" \r\n" + 
				"																						<br>\r\n" + 
				"																						Descrição: "+descricaoAlarme+" \r\n" + 
				"																					</div>\r\n" + 
				"																				</td>\r\n" + 
				"																			</tr>\r\n" + 
				"																		</table>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"						</table>\r\n" + 
				"						<table bgcolor=\"#E1E1E1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailFooter\">\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td valign=\"top\" bgcolor=\"#E1E1E1\">\r\n" + 
				"																		<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;\">\r\n" + 
				"																			<div>contato@desoltec.com.br - (19) 3645-2883 - <a href=\"http://www.waterbridge.com.br\" target=\"_blank\" style=\"text-decoration:none;color:#828282;\"><span style=\"color:#828282;\">www.waterbridge.com.br</span></a></div>\r\n" + 
				"																			<div>Copyright &#169;2018, Desoltec Engenharia. Todos os direitos reservados.</div>\r\n" + 
				"																		</div>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"						</table>\r\n" + 
				"					</td>\r\n" + 
				"				</tr>\r\n" + 
				"			</table>\r\n" + 
				"		</center>\r\n" + 
				"	</body>\r\n" + 
				"</html>";
	}
	
	public static String corpoEmailAlarmePressao(Condominio condominio, String device, String descricaoAlarme) {
		return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"	<head>\r\n" + 
				"		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
				"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\r\n" + 
				"		<meta name=\"format-detection\" content=\"telephone=no\" /> <!-- disable auto telephone linking in iOS -->\r\n" + 
				"		<title>WaterBridge</title>\r\n" + 
				"		<style type=\"text/css\">\r\n" + 
				"			/* RESET STYLES */\r\n" + 
				"			html { background-color:#E1E1E1; margin:0; padding:0; }\r\n" + 
				"			body, #bodyTable, #bodyCell, #bodyCell{height:100% !important; margin:0; padding:0; width:100% !important;font-family:Helvetica, Arial, \"Lucida Grande\", sans-serif;}\r\n" + 
				"			table{border-collapse:collapse;}\r\n" + 
				"			table[id=bodyTable] {width:100%!important;margin:auto;max-width:500px!important;color:#7A7A7A;font-weight:normal;}\r\n" + 
				"			img, a img{border:0; outline:none; text-decoration:none;height:auto; line-height:100%;}\r\n" + 
				"			a {text-decoration:none !important;border-bottom: 1px solid;}\r\n" + 
				"			h1, h2, h3, h4, h5, h6{color:#5F5F5F; font-weight:normal; font-family:Helvetica; font-size:20px; line-height:125%; text-align:Left; letter-spacing:normal;margin-top:0;margin-right:0;margin-bottom:10px;margin-left:0;padding-top:0;padding-bottom:0;padding-left:0;padding-right:0;}\r\n" + 
				"			/* CLIENT-SPECIFIC STYLES */\r\n" + 
				"			.ReadMsgBody{width:100%;} .ExternalClass{width:100%;} /* Force Hotmail/Outlook.com to display emails at full width. */\r\n" + 
				"			.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div{line-height:100%;} /* Force Hotmail/Outlook.com to display line heights normally. */\r\n" + 
				"			table, td{mso-table-lspace:0pt; mso-table-rspace:0pt;} /* Remove spacing between tables in Outlook 2007 and up. */\r\n" + 
				"			#outlook a{padding:0;} /* Force Outlook 2007 and up to provide a \"view in browser\" message. */\r\n" + 
				"			img{-ms-interpolation-mode: bicubic;display:block;outline:none; text-decoration:none;} /* Force IE to smoothly render resized images. */\r\n" + 
				"			body, table, td, p, a, li, blockquote{-ms-text-size-adjust:100%; -webkit-text-size-adjust:100%; font-weight:normal!important;} /* Prevent Windows- and Webkit-based mobile platforms from changing declared text sizes. */\r\n" + 
				"			.ExternalClass td[class=\"ecxflexibleContainerBox\"] h3 {padding-top: 10px !important;} /* Force hotmail to push 2-grid sub headers down */\r\n" + 
				"			/* /\\\\/\\\\/\\\\/\\\\/\\\\/\\\\/\\\\/\\\\/ TEMPLATE STYLES /\\\\/\\\\/\\\\/\\\\/\\\\/\\\\/\\\\/\\\\/ */\r\n" + 
				"			/* ========== Page Styles ========== */\r\n" + 
				"			h1{display:block;font-size:26px;font-style:normal;font-weight:normal;line-height:100%;}\r\n" + 
				"			h2{display:block;font-size:20px;font-style:normal;font-weight:normal;line-height:120%;}\r\n" + 
				"			h3{display:block;font-size:17px;font-style:normal;font-weight:normal;line-height:110%;}\r\n" + 
				"			h4{display:block;font-size:18px;font-style:italic;font-weight:normal;line-height:100%;}\r\n" + 
				"			.flexibleImage{height:auto;}\r\n" + 
				"			.linkRemoveBorder{border-bottom:0 !important;}\r\n" + 
				"			table[class=flexibleContainerCellDivider] {padding-bottom:0 !important;padding-top:0 !important;}\r\n" + 
				"			body, #bodyTable{background-color:#E1E1E1;}\r\n" + 
				"			#emailHeader{background-color:#E1E1E1;}\r\n" + 
				"			#emailBody{background-color:#FFFFFF;}\r\n" + 
				"			#emailFooter{background-color:#E1E1E1;}\r\n" + 
				"			.nestedContainer{background-color:#F8F8F8; border:1px solid #CCCCCC;}\r\n" + 
				"			.emailButton{background-color:#205478; border-collapse:separate;}\r\n" + 
				"			.buttonContent{color:#FFFFFF; font-family:Helvetica; font-size:18px; font-weight:bold; line-height:100%; padding:15px; text-align:center;}\r\n" + 
				"			.buttonContent a{color:#FFFFFF; display:block; text-decoration:none!important; border:0!important;}\r\n" + 
				"			.emailCalendar{background-color:#FFFFFF; border:1px solid #CCCCCC;}\r\n" + 
				"			.emailCalendarMonth{background-color:#205478; color:#FFFFFF; font-family:Helvetica, Arial, sans-serif; font-size:16px; font-weight:bold; padding-top:10px; padding-bottom:10px; text-align:center;}\r\n" + 
				"			.emailCalendarDay{color:#205478; font-family:Helvetica, Arial, sans-serif; font-size:60px; font-weight:bold; line-height:100%; padding-top:20px; padding-bottom:20px; text-align:center;}\r\n" + 
				"			.imageContentText {margin-top: 10px;line-height:0;}\r\n" + 
				"			.imageContentText a {line-height:0;}\r\n" + 
				"			#invisibleIntroduction {display:none !important;} /* Removing the introduction text from the view */\r\n" + 
				"			/*FRAMEWORK HACKS & OVERRIDES */\r\n" + 
				"			span[class=ios-color-hack] a {color:#275100!important;text-decoration:none!important;} /* Remove all link colors in IOS (below are duplicates based on the color preference) */\r\n" + 
				"			span[class=ios-color-hack2] a {color:#205478!important;text-decoration:none!important;}\r\n" + 
				"			span[class=ios-color-hack3] a {color:#8B8B8B!important;text-decoration:none!important;}\r\n" + 
				"			/* A nice and clean way to target phone numbers you want clickable and avoid a mobile phone from linking other numbers that look like, but are not phone numbers.  Use these two blocks of code to \"unstyle\" any numbers that may be linked.  The second block gives you a class to apply with a span tag to the numbers you would like linked and styled.\r\n" + 
				"			Inspired by Campaign Monitor's article on using phone numbers in email: http://www.campaignmonitor.com/blog/post/3571/using-phone-numbers-in-html-email/.\r\n" + 
				"			*/\r\n" + 
				"			.a[href^=\"tel\"], a[href^=\"sms\"] {text-decoration:none!important;color:#606060!important;pointer-events:none!important;cursor:default!important;}\r\n" + 
				"			.mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {text-decoration:none!important;color:#606060!important;pointer-events:auto!important;cursor:default!important;}\r\n" + 
				"			/* MOBILE STYLES */\r\n" + 
				"			@media only screen and (max-width: 480px){\r\n" + 
				"				/*////// CLIENT-SPECIFIC STYLES //////*/\r\n" + 
				"				body{width:100% !important; min-width:100% !important;} /* Force iOS Mail to render the email at full width. */\r\n" + 
				"				/* FRAMEWORK STYLES */\r\n" + 
				"				/*\r\n" + 
				"				CSS selectors are written in attribute\r\n" + 
				"				selector format to prevent Yahoo Mail\r\n" + 
				"				from rendering media query styles on\r\n" + 
				"				desktop.\r\n" + 
				"				*/\r\n" + 
				"				/*td[class=\"textContent\"], td[class=\"flexibleContainerCell\"] { width: 100%; padding-left: 10px !important; padding-right: 10px !important; }*/\r\n" + 
				"				table[id=\"emailHeader\"],\r\n" + 
				"				table[id=\"emailBody\"],\r\n" + 
				"				table[id=\"emailFooter\"],\r\n" + 
				"				table[class=\"flexibleContainer\"],\r\n" + 
				"				td[class=\"flexibleContainerCell\"] {width:100% !important;}\r\n" + 
				"				td[class=\"flexibleContainerBox\"], td[class=\"flexibleContainerBox\"] table {display: block;width: 100%;text-align: left;}\r\n" + 
				"				/*\r\n" + 
				"				The following style rule makes any\r\n" + 
				"				image classed with 'flexibleImage'\r\n" + 
				"				fluid when the query activates.\r\n" + 
				"				Make sure you add an inline max-width\r\n" + 
				"				to those images to prevent them\r\n" + 
				"				from blowing out.\r\n" + 
				"				*/\r\n" + 
				"				td[class=\"imageContent\"] img {height:auto !important; width:100% !important; max-width:100% !important; }\r\n" + 
				"				img[class=\"flexibleImage\"]{height:auto !important; width:100% !important;max-width:100% !important;}\r\n" + 
				"				img[class=\"flexibleImageSmall\"]{height:auto !important; width:auto !important;}\r\n" + 
				"				/*\r\n" + 
				"				Create top space for every second element in a block\r\n" + 
				"				*/\r\n" + 
				"				table[class=\"flexibleContainerBoxNext\"]{padding-top: 10px !important;}\r\n" + 
				"				/*\r\n" + 
				"				Make buttons in the email span the\r\n" + 
				"				full width of their container, allowing\r\n" + 
				"				for left- or right-handed ease of use.\r\n" + 
				"				*/\r\n" + 
				"				table[class=\"emailButton\"]{width:100% !important;}\r\n" + 
				"				td[class=\"buttonContent\"]{padding:0 !important;}\r\n" + 
				"				td[class=\"buttonContent\"] a{padding:15px !important;}\r\n" + 
				"			}\r\n" + 
				"			/*  CONDITIONS FOR ANDROID DEVICES ONLY\r\n" + 
				"			*   http://developer.android.com/guide/webapps/targeting.html\r\n" + 
				"			*   http://pugetworks.com/2011/04/css-media-queries-for-targeting-different-mobile-devices/ ;\r\n" + 
				"			=====================================================*/\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:.75){\r\n" + 
				"				/* Put CSS for low density (ldpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:1){\r\n" + 
				"				/* Put CSS for medium density (mdpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			@media only screen and (-webkit-device-pixel-ratio:1.5){\r\n" + 
				"				/* Put CSS for high density (hdpi) Android layouts in here */\r\n" + 
				"			}\r\n" + 
				"			/* end Android targeting */\r\n" + 
				"			/* CONDITIONS FOR IOS DEVICES ONLY\r\n" + 
				"			=====================================================*/\r\n" + 
				"			@media only screen and (min-device-width : 320px) and (max-device-width:568px) {\r\n" + 
				"			}\r\n" + 
				"			/* end IOS targeting */\r\n" + 
				"		</style>\r\n" + 
				"	</head>\r\n" + 
				"	<body bgcolor=\"#E1E1E1\" leftmargin=\"0\" marginwidth=\"0\" topmargin=\"0\" marginheight=\"0\" offset=\"0\">\r\n" + 
				"		<center style=\"background-color:#E1E1E1;\">\r\n" + 
				"			<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"bodyTable\" style=\"table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;\">\r\n" + 
				"				<tr>\r\n" + 
				"					<td align=\"center\" valign=\"top\" id=\"bodyCell\">\r\n" + 
				"						<table bgcolor=\"#FFFFFF\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailBody\">\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"color:#FFFFFF;\" bgcolor=\"#3498db\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\" class=\"textContent\">\r\n" + 
				"																		<h1 style=\"color:#FFFFFF;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:35px;font-weight:normal;margin-bottom:5px;text-align:center;\">\r\n" + 
				"																			WaterBridge\r\n" + 
				"																		</h1>\r\n" + 
				"																		<div style=\"text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;\">\r\n" + 
				"																			Qualquer tecnologia suficientemente avançada é indistinguível de magia. Arthur C. Clark.\r\n" + 
				"																		</div>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#F8F8F8\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td align=\"center\" valign=\"top\">\r\n" + 
				"																		<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																			<tr>\r\n" + 
				"																				<td valign=\"top\" class=\"textContent\">\r\n" + 
				"																					<h3 mc:edit=\"header\" style=\"color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;text-align:left;\">\r\n" + 
				"																						Prezado, abaixo o Device com a ocorrência de alarme na data de hoje "+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" </h3>\r\n" + 
				"																						<br>\r\n" + 
				"																					<div mc:edit=\"body\" style=\"text-align:left;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;\">\r\n" +
				"																						Local: "+condominio.getNome()+" \r\n" + 
				"																						<br>\r\n" + 
				"																						Endereço: "+condominio.getEndereco()+ " " +condominio.getNumero() + " "+Auxiliar.isNull(condominio.getCompl()) + " \r\n" + 
				"																						<br>\r\n" + 
				"																						Device: "+device+" \r\n" + 
				"																						<br>\r\n" + 			 
				"																						Descrição: "+descricaoAlarme+" \r\n" + 
				"																					</div>\r\n" + 
				"																				</td>\r\n" + 
				"																			</tr>\r\n" + 
				"																		</table>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"						</table>\r\n" + 
				"						<table bgcolor=\"#E1E1E1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailFooter\">\r\n" + 
				"							<tr>\r\n" + 
				"								<td align=\"center\" valign=\"top\">\r\n" + 
				"									<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"										<tr>\r\n" + 
				"											<td align=\"center\" valign=\"top\">\r\n" + 
				"												<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\r\n" + 
				"													<tr>\r\n" + 
				"														<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\r\n" + 
				"															<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\r\n" + 
				"																<tr>\r\n" + 
				"																	<td valign=\"top\" bgcolor=\"#E1E1E1\">\r\n" + 
				"																		<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;\">\r\n" + 
				"																			<div>contato@desoltec.com.br - (19) 3645-2883 - <a href=\"http://www.waterbridge.com.br\" target=\"_blank\" style=\"text-decoration:none;color:#828282;\"><span style=\"color:#828282;\">www.waterbridge.com.br</span></a></div>\r\n" + 
				"																			<div>Copyright &#169;2018, Desoltec Engenharia. Todos os direitos reservados.</div>\r\n" + 
				"																		</div>\r\n" + 
				"																	</td>\r\n" + 
				"																</tr>\r\n" + 
				"															</table>\r\n" + 
				"														</td>\r\n" + 
				"													</tr>\r\n" + 
				"												</table>\r\n" + 
				"											</td>\r\n" + 
				"										</tr>\r\n" + 
				"									</table>\r\n" + 
				"								</td>\r\n" + 
				"							</tr>\r\n" + 
				"						</table>\r\n" + 
				"					</td>\r\n" + 
				"				</tr>\r\n" + 
				"			</table>\r\n" + 
				"		</center>\r\n" + 
				"	</body>\r\n" + 
				"</html>";
	}
}
