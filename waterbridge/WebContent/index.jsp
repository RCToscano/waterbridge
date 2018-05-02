
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
			/* xs < 768 */
			@media screen and (max-width: 767px) {
			    #labelbody {
			        font-size: 1.9em;
			    }
			}
			/* sm */
			@media screen and (min-width: 768px) {
			    #labelbody {
			        font-size: 1em;
			    }
			}
			/* md */
			@media screen and (min-width: 992px) {
			    #labelbody {
			        font-size: 2.2em;
			    }
			}
			/* lg */
			@media screen and (min-width: 1200px) {
			    #labelbody {
			        font-size: 3.3em;
			    }
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
        <div class="container-fluid" style="margin-top: 60px; padding: 0px;">
   	       	<div class="form-group">
        		<div class="col-sm-12 text-right">
        			<label style='color: #595959; font-size: 10pt;'>Qualquer tecnologia suficientemente avan&ccedil;ada &eacute; indistingu&iacute;vel de magia.<br/>Arthur C. Clark</label>
        		</div>
        	</div>
        	<div class="form-group" style="margin-top: 100px">
        		<div class="col-sm-6">
        			<div class="form-group">
        				<img class="img-responsive center-block" src="./images/logo_waterbridge.png" alt="">
        			</div>
        		</div>
        	</div>
        </div>
        <footer class="footer" style="background-color: #fff">
            <div class="container-fluid text-center" style="background-color: #fff; padding: 10px">
            	<div class="col-sm-4 text-center"><p class="text-muted">Todos os direitos reservados</p></div>
            	<div class="col-sm-4 text-center"><p class="text-muted"><label>Desenvolvido por Desoltec Engenharia</label></p></div>
            	<div class="col-sm-4 text-center"><img class="img-responsive center-block" src="./images/logo_desoltec_rodape.png" alt=""></div>
            </div>
        </footer>
    </body>
</html>
