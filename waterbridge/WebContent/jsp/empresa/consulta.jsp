
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
	    <script src='./js/empresa/consulta.js'></script>
	    
	    <script>
            $(function () {
                $("#telefoneFixo").mask("(99) 9999-9999");
                $("#telefoneCelular").mask("(99) 99999-9999");
                $("#cnpj").mask("99.999.999/9999-99");	
                $("#postal_code").mask("99999-999");
            });
        </script>
    </head>
    <body>
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Empresa</li>
			    <li class="active">Consulta</li>
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
				<form action="EmpresaBO?acao=pesquisar" method="post" accept-charset="iso-8859-1,utf-8" onSubmit="return validaForm()">
					<fieldset>
						<legend class="text-left">Consulta de Empresa</legend>
						
						<div class="col-sm-6">
							<div class="form-group">
								<label>Empresa:</label>
								<select class="form-control" name="empresa" id="empresa" onChange="validaForm()">
			                        <option value="" selected>Selecione...</option>
			                        <option value="todos">TODOS</option>
			                        <c:forEach var="listaEmpresa" items="${listaEmpresa}">
                             			<option value="${listaEmpresa.idEmpresa}" >${listaEmpresa.nome} - ${listaEmpresa.cnp}</option>
			                        </c:forEach>
			                    </select>
							</div>
						</div>
						
						<div class="col-sm-4">
							<div class="form-group">
								<label class="control-label">CNPJ</label>
								<input type="tel" class="form-control" id="cnpj" name="cnpj" placeholder="99.999.999/9999-99" value="${empresa.cnp}" maxlength="20"/>
							</div>
						</div>
						
						<div class="col-sm-12">
							<div class="form-group">
								<div class="col-md-12 text-center">
									<button type="submit" class="btn btn-primary">Consultar</button>
								</div>
							</div>
						</div>
						
					</fieldset>
				</form>
			</div>
			
			<c:if test="${not empty lista}">
				<div class="col-sm-12">
					<div class="form-group">
						<input class="form-control" id="myInput" type="text" placeholder="Utilize para procurar..."></input> <br />
						<div class="table-responsive" id="divTable">
							<table class="table table-hover table-striped">
								<thead>
									<tr>
										<th style="width:0.5%;">Nº</th>
										<th style="width:20%;">Nome</th>
										<th style="width:15%;">CNPJ</th>
										<th style="width:15%;">Responsável</th>
										<th style="width:30%;">Endereço</th>
										<th>Coordenadas</th>
										<th></th>
									</tr>
								</thead>
								<tbody id="myTable">
									<% int cont = 1;%>
									<c:forEach items="${lista}" var="total">
										<c:url value="EmpresaBO" var="link">
				                            <c:param name="acao" value="detalhe"/>
				                            <c:param name="id" value="${total.idEmpresa}"/>
				                        </c:url>
										<tr>
											<td><%=cont%></td>
											<td><small>${total.nome}</small></td>
											<td><small>${total.cnp}</small></td>
											<td><small>${total.responsavel}</small></td>
											<td><small>${total.endereco} ${total.numero}</small></td>
											<td>
												<c:choose>
	                                                <c:when test="${total.coordX != null && total.coordX != '' && total.coordX != '0.0'}">
	                                                    <small><a href="https://www.google.com/maps?q=loc:${total.coordX}+${total.coordY}"  target="_blank">${total.coordX} ${total.coordY}</a></small>
	                                                </c:when>
	                                                <c:otherwise>
	                                                    <small>${total.coordX} ${total.coordY}</small>
	                                                </c:otherwise>
	                                            </c:choose>
											</td>
											<td>
												<a href="${link}">
													<button type="button" class="btn btn-info btn-sm" title="Clique para visualizar o detalhe da Empresa">
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
        <footer class="footer" style="background-color: #fff">
            <div class="container-fluid text-center" style="background-color: #fff; padding: 10px">
            	<div class="col-sm-4 text-center"><p class="text-muted">Todos os direitos reservados</p></div>
            	<div class="col-sm-4 text-center"><p class="text-muted"><label>Desenvolvido por Desoltec Engenharia</label></p></div>
            	<div class="col-sm-4 text-center"><img class="img-responsive center-block" src="./images/logo_desoltec_rodape.png" alt=""></div>
            </div>
        </footer>
    </body>
</html>