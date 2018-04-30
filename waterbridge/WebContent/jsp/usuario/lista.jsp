
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
			    <li class="active">Usuário</li>
			    <li class="active">Lista</li>
			</ul>
			
			<div id="divAviso" name="divAviso" class="alert alert-danger" style="display:${display};">
				<strong><label id='aviso' name='aviso'/>${aviso}</strong>
			</div>
			
			<input class="form-control" id="myInput" type="text" placeholder="Utilize para procurar..."></input> <br />
			<div class="table-responsive" id="divTable">
				<table class="table table-hover table-striped">
					<thead>
						<tr>
							<th style="width:0.5%;">Nº</th>
							<th style="width:20%;">Nome</th>
							<th style="width:11%;">CPF</th>
							<th style="width:11%;">Perfil</th>
							<th style="width:15%;">E-mail</th>
							<th style="width:10%;">Celular</th>
							<th style="width:30%;">Endere&ccedil;o</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="myTable">
						<% int cont = 1;%>
						<c:forEach items="${listaUsuarios}" var="total">
							<c:url value="UsuarioBO" var="link">
	                            <c:param name="acao" value="detalhe"/>
	                            <c:param name="id" value="${total.idUser}"/>
	                        </c:url>
							<tr>
								<td><%=cont%></td>
								<td><small>${total.nome}</small></td>
								<td><small>${total.cpf}</small></td>
								<td><small>${total.perfil.perfil}</small></td>
								<td><small>${total.email}</small></td>
								<td><small>${total.telCel}</small></td>
								<td><small>${total.endereco}</small></td>
								<td>
									<a href="${link}">
										<button type="button" class="btn btn-info btn-sm" title="Clique para visualizar o perfil do usuário">
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
        <footer class="footer">
			<div class="container text-center">
				<p class="text-muted">
					Todos os direitos reservados&emsp;&emsp;-&emsp;&emsp;<label>Desenvolvido por Desoltec Engenharia</label>&emsp;&emsp;<img src="./images/logo_desoltec_rodape.png" alt="" style="margin: 0px;">
				</p>
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