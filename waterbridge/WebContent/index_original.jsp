
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>WaterBridge</title>
        <link rel="icon" type="image/png" href="./images/favicon.ico">
        <script src="./js/jquery-1.11.3.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="./css/formValidation.css"/>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="./css/menucustomcolor.css" rel="stylesheet"/>
        <link href="./css/footercustom.css" rel="stylesheet"/>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <style type="text/css">
			#bgimg {
			    background-image: url('./images/bg_login.jpg');
			}
		</style>
    </head>
    <body background="./images/bg_login.jpg">
        <%@include file="./menu/menu_login.jsp" %>
        <c:choose>
            <c:when test="${!empty loginErro}">
                <script>
                    $(document).ready(function () {
                        $("[data-toggle='tooltip']").tooltip('show');
                        $('.tooltip-inner').css('background-color', 'red');
                    });
                </script>
            </c:when>                
            <c:otherwise>
                <script>
                    $(document).ready(function () {
                        $("[data-toggle='tooltip']").tooltip('destroy');
                    });
                </script>
            </c:otherwise>
        </c:choose>   
        <div class="container-fluid" style="margin-top: 60px;">
<!--             <img class="img-responsive" src="./images/bg_login.jpg" alt=""> -->
        	<div class="form-group">
        		<div class="col-sm-12 text-right">
        			<label style='color: #595959; font-size: 10pt;'>Qualquer tecnologia suficientemente avan&ccedil;ada &eacute; indistingu&iacute;vel de magia.<br/>Arthur C. Clark</label>
        		</div>
        	</div>
        	<br/><br/><br/><br/>
        	<div class="form-group">
        		<div class="col-sm-6">
        			<img src="./images/logo_waterbridge.png" alt="">
        		</div>
        		<div class="col-sm-6">
        			<label style="font-size: 32pt;">Sistema de medi&ccedil;&atilde;o<br/>individualizada de &aacute;gua</label>
        		</div>
        	</div>
        </div>
        <footer class="footer" style="background: #fff;">
            <div class="container text-center" style="background: #fff; width: 100%">
                <p class="text-muted">
					Todos os direitos reservados&emsp;&emsp;-&emsp;&emsp;<label>Desenvolvido por Desoltec Engenharia</label>&emsp;&emsp;<img src="./images/logo_desoltec_rodape.png" alt="" style="margin: 0px;">
				</p> 
            </div>
        </footer>
    </body>
</html>
