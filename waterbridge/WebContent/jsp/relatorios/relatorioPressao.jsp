
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
		<title>WaterBridge</title>
		<link rel="icon" type="image/png" href="./images/favicon.ico"/>
        
        <script src="./js/funcoes.auxiliares.js" type="text/javascript"></script>
	    <script src="./js/relatorios/relatorioPressao.js" type="text/javascript"></script>
	    
   		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	    <script src="http://code.jquery.com/jquery-2.2.4.js" ></script>	
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script src="http://malsup.github.io/jquery.blockUI.js"></script>

		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment.min.js"></script>
	    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/locale/pt-br.js"></script>
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
	    
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous"/>
	    
	    <link href="./css/menucustomcolor.css" rel="stylesheet"/>
	    <link href="./css/footercustom.css" rel="stylesheet"/>
		
		<script src="https://code.highcharts.com/stock/highstock.js"></script>
		<script src="https://code.highcharts.com/modules/series-label.js"></script>
		<script src="https://code.highcharts.com/maps/modules/map.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
	    
	    <script src='./js/validator.min.js'></script>

		<script>
			$(document).ready(function(){
			    $('[data-toggle="popover"]').popover({html: true});   
			});
		</script>
	    
    </head>
    <body onload="autoRefresh(6000);">
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Consulta</li>
			    <li class="active">Pressure Bridge</li>
			</ul>
			
			<div id="divAviso" name="divAviso">${aviso}</div>
			
			<c:if test="${not empty sucesso}">
				<div class="alert alert-success">
					<strong><c:out value="${sucesso}"/></strong>
				</div>
			</c:if>
			
			<c:if test="${not empty informacao}">
				<div class="alert alert-warning">
					<strong><c:out value="${informacao}"/></strong>
				</div>
			</c:if>
			
			<div class="form-group">
				<form data-toggle="validator" role="form" id="formUsuarioMedidor" action="#" method="POST" accept-charset="iso-8859-1,utf-8">
					<fieldset>
						<legend class="text-left">Pressure Bridge</legend>
					
						<div class="col-sm-5">
							<div class="form-group">
								<label class="control-label">Empresa</label><label class="text-danger">*</label>
								<select class="form-control input-sm" id="idEmpresa" name="idEmpresa" required onchange="listarCondominio()">
									<option value="" selected>Selecione...</option>
									<c:forEach var="empresa" items="${listEmpresa}">
			               		        <c:choose>
			                            	<c:when test="${empresa.idEmpresa eq condominio.idEmpresa}">
			                            		<option value="${empresa.idEmpresa}" selected>${empresa.nome}</option> 
			                                </c:when>
			                                <c:otherwise>
			                                  	<option value="${empresa.idEmpresa}">${empresa.nome}</option>
			                                </c:otherwise>
			                     		</c:choose>
			                     	</c:forEach>
								</select>
							</div>
						</div>
							
						<div class="col-sm-5">
							<div class="form-group">
								<label class="control-label">Local</label><label class="text-danger">*</label>
								<select class="form-control input-sm" id="idCondominio" name="idCondominio" required onchange="listarBridge()">
									<option value="" selected>Selecione...</option>
								</select>					
							</div>
						</div>
						
						<div class="col-sm-2">
							<div class="form-group">
								<label class="control-label">Bridge</label><label class="text-danger">*</label>
								<select class="form-control input-sm" id="idBridge" name="idBridge" required>
									<option value="" selected>Selecione...</option>
								</select>					
							</div>
						</div>
							
						<div class="col-sm-2">
							<div class="form-group">
								<label class="control-label">Data Início</label><label class="text-danger">*</label>
								<div class='input-group date' id='datetimepicker1'>
									<input type="text" class="form-control input-sm" id="dtInicio" name="dtInicio" data-date-format="DD/MM/YYYY" placeholder="dd/mm/aaaa" value="${usuario.dtNasc}" required/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
									<script type="text/javascript">
									    $(function () {
									        $('#datetimepicker1').datetimepicker({
									            format: 'DD/MM/YYYY',
									            date: new Date()
									        });
									    });
									</script>
								</div>
							</div>
						</div>
						
						<div class="col-sm-2">
							<div class="form-group">
								<label class="control-label">Data Fim</label><label class="text-danger">*</label>
								<div class='input-group date' id='datetimepicker2'>
									<input type="text" class="form-control input-sm" id="dtFim" name="dtFim" data-date-format="DD/MM/YYYY" placeholder="dd/mm/aaaa" value="${usuario.dtNasc}" required/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
									<script type="text/javascript">
									    $(function () {
									        $('#datetimepicker2').datetimepicker({
									            format: 'DD/MM/YYYY',
									            date: new Date()
									        });
									    });
									</script>
								</div>
							</div>
						</div>
						
						<div class="col-sm-12">
							<div class="form-group">
								<label class="control-label"></label><label class="text-danger">* Campos Obrigatórios</label>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-md-12 text-center">
								<button type="button" class="btn btn-primary" onclick="listarConsumoMedidor()">Consultar</button>
							</div>
						</div>
					</fieldset>	
				</form>	
			</div>
			
			<div class="col-sm-12" style="float: none; margin: 0 auto;">
				<div class="form-group">
					<div class="col-sm-12">
						<div id="graficopressaodiaria" style="margin-top: 30px;"></div>
					</div>
				</div>
			</div>

			<div class="col-sm-8 col-md-offset-2">
				<div class="form-group">
					<div class="table-responsive" id="divTable"></div>
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
		<script>
        	$(document).ready(function(){
			  $("#myInput").on("keyup", function() {
			    var value = $(this).val().toLowerCase();
			    $("#myTable tr").filter(function() {
			      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			    });
			  });
			});
        
	        jQuery(document).ready(function($) {
	            $(".clickable-row").click(function() {
	                window.location = $(this).data("href");
	            });
	        });
	        
	        $(document).ready(function(){
	            $('[data-toggle="tooltip"]').tooltip(); 
	        });
		</script>
    </body>
</html>