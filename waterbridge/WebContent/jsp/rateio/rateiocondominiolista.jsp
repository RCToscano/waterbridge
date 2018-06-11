
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
	    <script src="./js/rateio/rateiocondominioexecutar.js" type="text/javascript"></script>
	    
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
        <div class="container-fluid">
        
        	<ul class="breadcrumb" style="margin-top: 60px;">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Rateio</li>
			    <li class="active">Rateio Conta</li>
			</ul>
			
			<div class="form-group">
				<div id="divAviso">${aviso}</div>
			</div>			
			
<!-- 			<fieldset> -->
<!-- 				<legend class="text-left">Rateio Conta</legend> -->
<!-- 			</fieldset> -->
			
			<div class="form-group">
				<fmt:setLocale value="pt-BR" />
				<div class="col-sm-6">
					<div class="form-group">	
						<label>Empresa</label><br/>
						${empresa.nome}
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label>Condomínio</label><br/>
						${condominio.nome}
					</div>
				</div>		
				<div class="col-sm-3">
					<div class="form-group">
						<label>Data Leitura Atual</label><br/>
						${conta.dtLeituraAtual}
					</div>		
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label>Data Leitura Anterior</label><br/>
						${conta.dtLeituraAnterior}
					</div>	
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label>Valor Total da Conta (R$)</label><br/>
						<fmt:formatNumber value="${conta.valor}" type="currency"/>
					</div>	
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label>Consumo Total da Conta (m³)</label><br/>
						<fmt:formatNumber type = "number" maxIntegerDigits = "3" value = "${conta.consumo}" />
						
					</div>	
				</div>
				
			</div>
			
			<div class="form-group" style="margin-top: 150px;">
				<p></p><hr>
				<div class="col-sm-12">
					<div class="table-responsive" id="divTable">
						<c:set var="cont" scope="page" value="1"/>
						
						<c:set var="consumoRealTotal" scope="page" value="0"/>
						<c:set var="consumoRateioTotal" scope="page" value="0"/>
						<c:set var="valorTotal" scope="page" value="0"/>
						<c:set var="percTotal" scope="page" value="0"/>
						
						
						<table class="table table-hover table-striped">	
							<thead>		
								<tr>				
									<th>Nº</th>			
									<th style="text-align: center">Medidor</th>			
									<th style="text-align: center">Endereço</th>			
									<th style="text-align: center">Consumidor</th>			
									<th style="text-align: center">Volume Inicial (m³)</th>			
									<th style="text-align: center">Volume Final (m³)</th>
									<th style="text-align: center">Consumo Real(m³)</th>
									<th style="text-align: center">Consumo Rateio(m³)</th>
									<th style="text-align: center">Valor (R$)</th>
									<th style="text-align: center">%</th>			
									<th style="text-align: center">Obs</th>		
								</tr>	
							</thead>	
							<tbody id="myTable">	
								<c:forEach var="relContaRateio" items="${listRelContaRateio}">
		               		        <tr>			
										<td><small>${cont}</small></td>			
										<td><small>${relContaRateio.numeroMedidor}</small></td>			
										<td><small>${relContaRateio.endereco} ${relContaRateio.numero} ${relContaRateio.compl}</small></td>			
										<td>
											<small>
												<c:forEach var="relUserMedidor" items="${relContaRateio.listRelUserMedidor}">
													${relUserMedidor.cpfUser} - ${relUserMedidor.nomeUser}
												</c:forEach>
											</small>
										</td>			
										<td align="right"><small><fmt:formatNumber type = "number" maxFractionDigits="3" value = "${relContaRateio.volumeInicial}" /></small></td>			
										<td align="right"><small><fmt:formatNumber type = "number" maxFractionDigits="3" value = "${relContaRateio.volumeFinal}" /></small></td>			
										<td align="right"><small><fmt:formatNumber type = "number" maxFractionDigits="3" value = "${relContaRateio.consumoReal}" /></small></td>
										<td align="right"><small><fmt:formatNumber type = "number" maxFractionDigits="3" value = "${relContaRateio.consumoRateio}" /></small></td>
										<td align="right"><small><fmt:formatNumber value="${relContaRateio.valorRateio}" type="currency" currencySymbol=""/></small></td>
										<td align="right"><small><fmt:formatNumber value="${relContaRateio.percRateio}" type="currency" currencySymbol=""/></small></td>		    
										<td><small>${relContaRateio.obs}</small></td>		
									</tr>
		               		        <c:set var="cont" scope="page" value="${cont + 1}"/>
		               		        <c:set var="consumoRealTotal" scope="page" value="${consumoRealTotal + relContaRateio.consumoReal}"/>
									<c:set var="consumoRateioTotal" scope="page" value="${consumoRateioTotal + relContaRateio.consumoRateio}"/>
									<c:set var="valorTotal" scope="page" value="${valorTotal + relContaRateio.valorRateio}"/>
									<c:set var="percTotal" scope="page" value="${percTotal + relContaRateio.percRateio}"/>
		                     	</c:forEach>
		                     	<tfoot>
			                     	<tr>				
										<td align="right"></td>			
										<td align="right"></td>			
										<td align="right"></td>			
										<td align="right"></td>			
										<td align="right"></td>			
										<td align="right"><small><label>Total</label></small></td>			
										<td align="right"><small><label><fmt:formatNumber type = "number" maxFractionDigits="3" value = "${consumoRealTotal}" /></label></small></td>
										<td align="right"><small><label><fmt:formatNumber type = "number" maxFractionDigits="3" value = "${consumoRateioTotal}" /></label></small></td>
										<td align="right"><small><label><fmt:formatNumber value="${valorTotal}" type="currency" currencySymbol=""/></label></small></td>
										<td align="right"><small><label><fmt:formatNumber value="${percTotal}" type="currency" currencySymbol=""/></label></small></td>			
										<td align="right"></td>		
									</tr>	
								</tfoot>
							</tbody>
						</table>
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