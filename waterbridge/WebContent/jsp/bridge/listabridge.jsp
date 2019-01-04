<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
	    <title>WaterBridge</title>	
		<link rel="icon" type="image/png" href="./images/favicon.ico"/>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<script src="./js/funcoes.auxiliares.js" type="text/javascript"></script>
	
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	    <script src="http://code.jquery.com/jquery-2.2.4.js" ></script>	
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script src="http://malsup.github.io/jquery.blockUI.js"></script>
	
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment.min.js"></script>
	    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/locale/pt-br.js"></script>
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
	    
	    <link href="./css/menucustomcolor.css" rel="stylesheet"/>
	    <link href="./css/footercustom.css" rel="stylesheet"/>
	    
	    <script src="./js/jquery.mask.min.js" type="text/javascript"></script>   
        <script>
            $(function () {
                $("#telFixo").mask("(99) 9999-9999");
            });
        </script>
        <script>
            $(document).ready(function () {
                $('input').keypress(function (e) {
                    var code = null;
                    code = (e.keyCode ? e.keyCode : e.which);
                    return (code == 13) ? false : true;
                });
            });
        </script>
    </head>
    <body>
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Bridge</li>
			    <li class="active">Consulta</li>
			</ul>
			<div class="col-sm-12" style="float: none; margin: 0 auto;">
				<div class="form-group">
					<form role="form" id="formConsultaBridge" action="BridgeBO?acao=6" method="POST" accept-charset="iso-8859-1,utf-8">
						<fieldset>
						    <legend>${tituloTela}</legend>
					  	
							<div id="divAviso">${aviso}</div>
						
							<div class="col-sm-2">
								<div class="form-group">
									<label class="control-label">N&deg; Device</label>
									<input type="text" class="form-control" id="deviceNum" name="deviceNum" value="" maxlength="20" />			
								</div>
							</div>
								
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label">Local</label> 
									<select class="form-control" id="idCondominio" name="idCondominio">
										<option value="" selected>Selecione...</option>
										<c:forEach var="condominio" items="${listCondominio}">
											<option value="${condominio.idCondominio}">${condominio.nome} - ${condominio.endereco} ${condominio.numero} ${condominio.compl}</option>
				                     	</c:forEach>
									</select>					
								</div>
							</div>
		                      
							<div class="col-sm-2">
								<div class="form-group">
									<label class="control-label">Início Cadastro</label>
				                    <div class='input-group date' id='dpDtInicio'>
					                    <input type='text' class="form-control input-sm" id='dtInicio' name='dtInicio' placeholder="dd/mm/aaaa" />
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
									<label class="control-label">Fim Cadastro</label>
									<div class='input-group date' id='dpDtFim'>
					                    <input type='text' class="form-control input-sm" id='dtFim' name='dtFim' placeholder="dd/mm/aaaa" />
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
							
							<div class="form-group">
								<div class="col-md-12 text-center">
									<button type="submit" class="btn btn-primary">Consultar</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				
				
				<c:if test = "${fn:length(listBridge) > 0}">
					<div class="form-group">
						<div class="col-sm-12">
							<input class="form-control" id="myInput" type="text" placeholder="Utilize para procurar..."></input> <br />
							<fmt:setLocale value="pt-BR" />
							<div class="table-responsive" id="divTable">
								<table class="table table-hover table-striped">
									<thead>
										<tr>
											<th>Nº</th>
											<th>N° Device</th>
											<th>Data de Ativação</th>											
											<th>Validade do Token</th>
											<th>Situação</th>
											<th>Tipo de Bridge</th>
											<th>Tipo de Alimentação</th>
											<th>Custo Mensal</th>
											<th>Taxa de Envio Diário</th>											
											<th></th>
										</tr>
									</thead>
									<tbody id="myTable">
										<% int cont = 1;%>
										<c:forEach var="bridge" items="${listBridge}">
											<tr>
												<td><small><%=cont%></small></td>
												<td><small>${bridge.deviceNum}</small></td>
												<td><small>${bridge.dtAtivacao}</small></td>
												<td><small>${bridge.validadeToken}</small></td>
												<td align="center"><small>${bridge.situacao}</small></td>
												<td><small>${bridge.bridgeTp.bridgeTp}</small></td>
												<td><small>${bridge.bridgeTpAlim.tpAlimentacao}</small></td>
												<td><small><fmt:formatNumber value="${bridge.custoMensal}" type="currency" currencySymbol=""/></small></td>
												<td><small>${bridge.taxaEnvio}</small></td>												
												<td>
													<a href="BridgeBO?acao=3&idBridge=${bridge.idBridge}">
														<button type="button" class="btn btn-info btn-sm" title="Clique para visualizar o detalhe do Bridge">
															<span class="glyphicon glyphicon-search"></span>
														</button>
													</a>
												</td>
											</tr>
											<%cont++;%>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
		      	</c:if>
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
