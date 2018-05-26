
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
		<title>WaterBridge</title>
		<link rel="icon" type="image/png" href="./images/favicon.ico"/>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
	    <script src="http://code.jquery.com/jquery-2.2.4.js" ></script>	
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="./js/jquery.mask.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment.min.js"></script>
	    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/locale/pt-br.js"></script>
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
	    <link href="./css/menucustomcolor.css" rel="stylesheet"/>
	    <link href="./css/footercustom.css" rel="stylesheet"/>
	    <script src='./js/usuario/consulta.js'></script>
    </head>
    <body>
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Relatórios</li>
			    <li class="active">Consumo por Condomínio</li>
			</ul>
			
			<div id="divAviso" name="divAviso" class="alert alert-danger" style="display:${display};">
				<strong><label id='aviso' name='aviso'/>${aviso}</strong>
			</div>
			
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
				<form action="RelatoriosBO?acao=consumoCondominio" method="post" accept-charset="iso-8859-1,utf-8">
					<fieldset>
						<legend class="text-left">Consumo por Condomínio</legend>
						
						<div class="col-sm-7">
							<div class="form-group">
								<label>Condomínio</label> 
								<select class="form-control" id="idCondominio" name="idCondominio" required >
									<option value="" selected>Selecione...</option>
									<c:forEach var="condominio" items="${listCondominio}">
		                   		        <c:choose>
		                                  	<c:when test="${condominio.idCondominio == idCondominio}">
		                                 		<option value="${condominio.idCondominio}" selected="true">${condominio.nome} - ${condominio.endereco} ${condominio.numero} ${condominio.compl}</option>
		                                   	</c:when>
		                                   	<c:otherwise>
		                                   		<option value="${condominio.idCondominio}">${condominio.nome} - ${condominio.endereco} ${condominio.numero} ${condominio.compl}</option>
		                                   	</c:otherwise>
		                            	</c:choose>
			                     	</c:forEach>
								</select>	
							</div>					
						</div>
						
						<div class="col-sm-2">
							<div class="form-group">
								<label class="control-label">Período</label>
								<div class='input-group date' id='datetimepicker1'>
									<input type="text" class="form-control" id="dtInicio" name="dtInicio" data-date-format="DD/MM/YYYY" placeholder="dd/mm/aaaa" value="${usuario.dtNasc}" required/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
									<script type="text/javascript">
									    $(function () {
									        $('#datetimepicker1').datetimepicker({
									            format: 'DD/MM/YYYY'
									        });
									    });
									</script>
								</div>
							</div>
						</div>
						
						<div class="col-sm-2">
							<div class="form-group">
								<label class="control-label">&nbsp; </label>
								<div class='input-group date' id='datetimepicker2'>
									<input type="text" class="form-control" id="dtFim" name="dtFim" data-date-format="DD/MM/YYYY" placeholder="dd/mm/aaaa" value="${usuario.dtNasc}" required/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
									<script type="text/javascript">
									    $(function () {
									        $('#datetimepicker2').datetimepicker({
									            format: 'DD/MM/YYYY'
									        });
									    });
									</script>
								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12 text-center">
								<button class="btn btn-primary">Consultar</button>
							</div>
						</div>
						
					</fieldset>
				</form>
			</div>
		
			<c:if test="${not empty lista}">
				<div class="col-sm-8 col-md-offset-2">
					<div class="form-group">
						<input class="form-control" id="myInput" type="text" placeholder="Utilize para procurar..."></input> <br />
						<div class="table-responsive" id="divTable">
							<table class="table table-hover table-striped">
								<thead>
									<tr>
										<th style="width:1%;">Nº</th>
										<th style="width:30%;">Consumidor</th>
										<th style="width:15%;">Medidor</th>
										<th style="width:15%;">Consumo (m³)</th>
									</tr>
								</thead>
								<tbody id="myTable">
									<% int cont = 1;%>
									<c:forEach items="${lista}" var="total">
										<tr>
											<td><%=cont%></td>
											<td><small>${total.consumidor}</small></td>
											<td><small>${total.medidor}</small></td>
											<td><small>${total.consumo}</small></td>
										</tr>
										<%cont++;%>
									</c:forEach>
									<tr>
										<td colspan="4" style="text-align: center">
											<label>Consumo total no período em m³: ${totalConsumo}</label>
										</td>
									</tr>
									<tr>
										<td colspan="4" style="text-align: center">
											<form action="RelatoriosBO?acao=graficoCondominio" method="post" target="_blank">
												<input type="hidden" id="dtInicio" name="dtInicio" value="${dtInicio}" />
												<input type="hidden" id="dtFim" name="dtFim" value="${dtFim}" />
												<button type="submit" class="btn btn-warning">
											    	<i class="fa fa-bar-chart"></i> Gráfico
											    </button>
											</form>    
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</c:if>
					
				
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