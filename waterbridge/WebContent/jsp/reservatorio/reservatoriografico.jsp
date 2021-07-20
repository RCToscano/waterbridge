<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>WaterBridge</title>	
	<link rel="icon" type="image/png" href="./images/favicon.ico"/>	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="utf-8">
	
	<script src="./js/funcoes.auxiliares.js" type="text/javascript"></script>
	<script src="./js/reservatorio/reservatoriografico.js" type="text/javascript"></script>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<script src="http://malsup.github.io/jquery.blockUI.js"></script>

<!-- 	<script src="https://code.highcharts.com/highcharts.js"></script> -->
<!--     <script src="https://code.highcharts.com/highcharts-3d.js"></script> -->
<!-- 	<script src="https://code.highcharts.com/modules/data.js"></script> -->
<!-- 	<script src="https://code.highcharts.com/modules/exporting.js"></script> -->
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/highcharts/6.2.0/highcharts.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/highcharts/6.2.0/highcharts-3d.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/highcharts/6.2.0/js/modules/data.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/highcharts/6.2.0/js/modules/exporting.js"></script>
	
	<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/i18n/defaults-pt_BR.js"></script>

</head>
<body>
	<div class="container-fluid">
		<div class="row" style="text-align: center; padding: 5px; background-color: #156fc3;">			
			<div class="col-sm-2 text-center"><img class="img-responsive center-block" src="./images/logo_waterbridge_menu.png" alt=""></div>
			<div class="col-sm-2 text-center"></div>
            <div class="col-sm-4 text-center"><p class="text-muted"></p></div>
            <div class="col-sm-2 text-center"></div>
            <div class="col-sm-2 text-right"><img class="img-responsive center-block" src="./images/logo_desoltec_branco_menu.png" alt=""></div>            
		</div>		
		<div class="row" style="text-align: center; padding-top: 5px;">									           
            <div class="col-sm-5 text-center"></div>
            <div class="col-sm-2 text-center"></div>
            <div class="col-sm-1 text-center">
            	<button type="button" class="btn btn-info btn-xs" onclick="abrirMapaReservatorios()">
            		<span class="glyphicon glyphicon-map-marker"></span> Mapa
            	</button>
            </div>
            <div class="col-sm-1 text-center">
            	<button type="button" class="btn btn-info btn-xs" id="btfiltro">
            		<span class="glyphicon glyphicon-filter"></span> Filtros
            	</button>
            	<script>
                    $( "#btfiltro" ).click(function() {
                        $( "#divFiltro" ).slideToggle(300);
                    });
                </script>
            </div>                        
            <div class="col-sm-3 text-right" id="divHoraAtualizacao"></div>                        
		</div>
		<div class="row">
			<div class="col-md-12 text-left">
				<label class="text-danger" id="aviso"></label>
			</div>
		</div>	
		<div class="row" style="text-align: center; padding-top: 5px;" id="divFiltro">
			<form action="#" id="form1">
				<div class="col-sm-4">
					<div class="form-group">
						<select class="form-control" id="idEmpresa" name="idEmpresa" required onchange="listarCondominio()">
							<option value="">Selecione a Empresa</option>
							<c:forEach var="empresa" items="${listEmpresa}">
								<option value="${empresa.idEmpresa}">${empresa.nome}</option>
	                     	</c:forEach>
						</select>
					</div>
				</div>			
	            <div class="col-sm-6">
	            	<div class="form-group">            
		            	<select class="selectpicker form-control" multiple name="idCondominio" id="idCondominio" title="Selecione o Local"></select>
	                </div>
	            </div>
	            <div class="col-sm-2">
					<div class="form-group">
						<div class="col-md-12 text-center">
							<button type="button" class="btn btn-primary" onclick="carregarReservatorios()">Consultar</button>
						</div>
					</div>
				</div>
			</form>	
		</div>	
		<div class="row" id="divReservatorio">
			<c:forEach var = "i" begin = "1" end = "120">
		        <div class="col-sm-1" style="padding: 3px;">		        			    
	            	<div id="container${i}"></div>
	            	<div id="titulo${i}" style="width: 100%; height: 30px; text-align: center;"></div>
	            </div> 
	      	</c:forEach>				
		</div>
	</div>
	
	<script type="text/javascript">		
		//atualizarReservatorios();
	</script>
</body>
</html>
