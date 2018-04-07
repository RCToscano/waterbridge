
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Deep</title>
        <link rel="icon" type="image/png" sizes="32x32" href="./images/favicon-32x32.png">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="./css/menucustomcolor.css" rel="stylesheet"/>
        <link href="./css/footercustom.css" rel="stylesheet"/>
        <script src="./js/jquery-1.11.3.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>
    <body>
    	<jsp:include page="../../menu/${sessionScope.user.perfil.menu}" ></jsp:include>
    	<div class="container">
	    	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li><a href="ServicoBO?acao=consultar">Servi&ccedil;o</a></li>
			    <li><a href="ServicoBO?acao=consultar">Consulta</a></li>
			    <li class="active">Lista</li>
			</ul>
			
			<c:if test="${aviso != ''}">
				<div class="alert alert-danger">
					<strong><c:out value="${aviso}"/></strong>
				</div>
			</c:if>
		
			<h2>Lista de Execu&ccedil;&otilde;es</h2>

			<form class="form-horizontal" action="ServicoBO?acao=detalhe"	method="post">
				<input class="form-control" id="myInput" type="text" placeholder="Utilize para procurar..."></input> <br />
				<div class="table-responsive">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th style="width:05%;">Id</th>
								<th style="width:11%;">Data Execu&ccedil;&atilde;o</th>
								<th style="width:11%;">Sit. Im&oacute;vel</th>
								<th style="width:15%;">Cliente</th>
								<th style="width:11%;">CPF</th>
								<th style="width:25%;">Endere&ccedil;o</th>
								<th style="width:11%;">Liga&ccedil;&atilde;o &Aacute;gua</th>
								<th style="width:11%;">Esgoto</th>
							</tr>
						</thead>
						<tbody id="myTable">
							<c:forEach items="${lista}" var="total">
								<tr>
									<c:url value="ServicoBO" var="link">
			                            <c:param name="acao" value="detalhe"/>
			                            <c:param name="id" value="${total.idDiagnostico}"/>
			                            <c:param name="dtInicio" value="${dtInicio}"/>
			                            <c:param name="dtFim" value="${dtFim}"/>
			                        </c:url>
									<td><a href="${link}">${total.idDiagnostico}</a></td>
									<td>${total.data}</td>
									<c:choose>
										<c:when test="${total.idSitImovel != ''}">
											<c:forEach items="${listaSituacaoImovel}" var="lista">
												<c:if test="${lista.id == total.idSitImovel}">
													<td>${lista.descricao}</td>
												</c:if>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>
									<td>${total.nome}</td>
									<td>${total.cpf}</td>
									<td>${total.endereco} ${total.numAtual}</td>
									<c:choose>
										<c:when test="${total.abastAguaIrreg != ''}">
											<c:forEach items="${listaAbastecimentoAgua}" var="lista">
												<c:if test="${lista.id == total.abastAguaIrreg}">
													<td>${lista.descricao}</td>
												</c:if>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>
									
									<c:choose>
										<c:when test="${total.idDestEsgoto != ''}">
											<c:forEach items="${listaDestinoEsgoto}" var="lista">
												<c:if test="${lista.id == total.idDestEsgoto}">
													<td>${lista.descricao}</td>
												</c:if>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</form>
<!-- 			<div style="text-align: center;"> -->
<!-- 				<ul class="pagination"> -->
<!-- 					<li><a href="#">1</a></li> -->
<!-- 				    <li class="active"><a href="#">2</a></li> -->
<!-- 				    <li><a href="#">3</a></li> -->
<!-- 				    <li><a href="#">4</a></li> -->
<!-- 				    <li><a href="#">5</a></li> -->
<!-- 				</ul> -->
<!-- 			</div> -->



		</div>
		<footer class="footer">
			<div class="container text-center">
				<p class="text-muted">©Copyright 2018</p>
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
			</script>
    </body>
</html>
