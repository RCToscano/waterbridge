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
			    <li class="active">Fabricante Medidor</li>
			    <li class="active">Consulta</li>
			</ul>
			<div class="col-sm-8" style="float: none; margin: 0 auto;">
				<fieldset>
				    <legend>${tituloTela}</legend>
			  	</fieldset>
				<div id="divAviso">${aviso}</div>
				<form role="form" id="formConsultaFabricMedidor" action="FabricMedidorBO?acao=6" method="POST" class="form-horizontal" accept-charset="iso-8859-1,utf-8">
					<div class="form-group">
						<div class="col-sm-12">
							<label class="control-label">Digite o nome do fabricante</label>
							<input type="text" class="form-control" id="fabricNome" name="fabricNome" value="" maxlength="50" />			
						</div>
					</div>					
					<div class="form-group">
						<div class="col-md-12 text-center">
							<button type="submit" class="btn btn-primary">Buscar</button>
						</div>
					</div>
				</form>			
				<c:if test = "${fn:length(listFabricMedidor) > 0}">
					<div class="form-group">
						<div class="col-sm-12">
<!-- 							<input class="form-control" id="myInput" type="text" placeholder="Utilize para procurar..."></input> <br /> -->
							<fmt:setLocale value="pt-BR" />
							<div class="table-responsive" id="divTable">
								<table class="table table-hover table-striped">
									<thead>
										<tr>
											<th>Nº</th>
											<th>Fabricante</th>
											<th></th>
										</tr>
									</thead>
									<tbody id="myTable">
										<% int cont = 1;%>
										<c:forEach var="fabricMedidor" items="${listFabricMedidor}">
											<tr>
												<td><small><%=cont%></small></td>
												<td><small>${fabricMedidor.fabricante}</small></td>
												<td align="right">
													<a href="FabricMedidorBO?acao=3&idFabricMedidor=${fabricMedidor.idFabricMedidor}">
														<button type="button" class="btn btn-info btn-sm" title="Clique para visualizar o detalhe do Fabricante">
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
    </body>
</html>
