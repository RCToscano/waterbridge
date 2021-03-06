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
        <div class="container-fluid" style="margin-top: 60px;">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Local</li>
			    <li class="active">Consulta</li>
			</ul>
			
			<div class="col-sm-12" style="float: none; margin: 0 auto;">
				<div class="form-group">
					<form role="form" id="formCadBridge" action="CondominioBO?acao=6" method="POST" accept-charset="iso-8859-1,utf-8">
						<fieldset>
						    <legend>${tituloTela}</legend>
					  	
							<div id="divAviso">${aviso}</div>
						
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">Nome/Razão Social:</label>
									<input type="text" class="form-control" id="nome" name="nome" value="" maxlength="100"/>
								</div>
							</div>
		
							<div class="col-sm-4">
								<div class="form-group">	
			                        <label class="control-label">Endere&ccedil;o</label>
			                        <input class="form-control" type="text" name="endereco" id="route" value="" maxlength="100"/>
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
				
				<c:if test = "${fn:length(listCondominio) > 0}">
					<div class="form-group">
						<div class="col-sm-12">
							<input class="form-control" id="myInput" type="text" placeholder="Utilize para procurar..."></input> <br />
							<div class="table-responsive" id="divTable">
								<table class="table table-hover table-striped">
									<thead>
										<tr>
											<th>Nº</th>
											<th>Nome/Razão Social</th>
											<th>CPF / CNPJ</th>
											<th>Situa&ccedil;&atilde;o</th>
											<th>Endere&ccedil;o</th>
											<th>Nome do Responsável</th>
											<th>Coordenadas</th>
											<th></th>
										</tr>
									</thead>
									<tbody id="myTable">
										<% int cont = 1;%>
										<c:forEach var="condominio" items="${listCondominio}">
											<tr>
												<td><small><%=cont%></small></td>
												<td><small>${condominio.nome}</small></td>
												<td><small>${condominio.cnp}</small></td>
												<td align="center"><small>${condominio.situacao}</small></td>
												<td><small>${condominio.endereco} ${condominio.numero} ${condominio.compl}</small></td>
												<td><small>${condominio.responsavel}</small></td>
												<td>
													<c:choose>
		                                                <c:when test="${condominio.coordX != null && condominio.coordX != '' && condominio.coordX != '0.0'}">
		                                                    <small><a href="https://www.google.com/maps?q=loc:${condominio.coordX}+${condominio.coordY}"  target="_blank">${condominio.coordX} ${condominio.coordY}</a></small>
		                                                </c:when>
		                                                <c:otherwise>
		                                                    <small>${condominio.coordX} ${condominio.coordY}</small>
		                                                </c:otherwise>
		                                            </c:choose>
												</td>
												<td>
													<a href="CondominioBO?acao=3&idCondominio=${condominio.idCondominio}">
														<button type="button" class="btn btn-info btn-sm" title="Clique para visualizar o detalhe do Condomínio">
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
