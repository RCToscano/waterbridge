
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
	    <script src="./js/relatorios/consumocondominiodia.js" type="text/javascript"></script>
	    
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
	    
	    <link href="./css/menucustomcolor.css" rel="stylesheet"/>
	    <link href="./css/footercustom.css" rel="stylesheet"/>
	    
	    <script src='./js/validator.min.js'></script>
    </head>
    <body>
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Relatórios</li>
			    <li class="active">Consumo por Medidor</li>
			</ul>
			
			<div id="divAviso" name="divAviso">${aviso}</div>
			
			<div class="form-group">
				<form data-toggle="validator" role="form" id="formUsuarioMedidor" action="#" method="POST" accept-charset="iso-8859-1,utf-8">
					<fieldset>
						<legend class="text-left">Consumo por Local</legend>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="control-label">Empresa</label><label class="text-danger">*</label>
								<select class="form-control input-sm" id="idEmpresa" name="idEmpresa" required onchange="listarCondominio()">
									<option value="" selected>Selecione...</option>
									<c:forEach var="empresa" items="${listEmpresa}">
			               		        <c:choose>
			                            	<c:when test="${empresa.idEmpresa eq condominio.idEmpresa}">
			                            		<option value="${empresa.idEmpresa}" selected="true">${empresa.nome}</option> 
			                                </c:when>
			                                <c:otherwise>
			                                  	<option value="${empresa.idEmpresa}">${empresa.nome}</option>
			                                </c:otherwise>
			                     		</c:choose>
			                     	</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">	
								<label class="control-label">Local</label><label class="text-danger">*</label>
								<select class="form-control input-sm" id="idCondominio" name="idCondominio" required onchange="listarBridge()">
									<option value="" selected>Selecione...</option>
								</select>					
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label class="control-label">Bridge</label>
								<select class="form-control input-sm" id="idBridge" name="idBridge" onchange="listarMedidor()">
									<option value="" selected>Selecione...</option>
								</select>					
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label class="control-label">Medidor</label>
								<select class="form-control input-sm" id="idMedidor" name="idMedidor">
									<option value="" selected>Selecione...</option>
								</select>					
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label class="control-label">Data Início</label><label class="text-danger">*</label>
			                    <div class='input-group date' id='dpDtInicio'>
				                    <input type='text' class="form-control input-sm" id='dtInicio' name='dtInicio' placeholder="dd/mm/aaaa" required />
			            			<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
				                    <script type="text/javascript">
					                    $(function () {
				                            $('#dpDtInicio').datetimepicker({
				                            	format: 'DD/MM/YYYY'
				                            });
				                        });
				                    </script>
				                </div> 
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">	
								<label class="control-label">Data Fim</label><label class="text-danger">*</label>
			                    <div class='input-group date' id='dpDtFim'>
				                    <input type='text' class="form-control input-sm" id='dtFim' name='dtFim' placeholder="dd/mm/aaaa" required />
			            			<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
				                    <script type="text/javascript">
					                    $(function () {
				                            $('#dpDtFim').datetimepicker({
				                            	format: 'DD/MM/YYYY'
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
			<div class="form-group">
				<div class="col-sm-12">
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