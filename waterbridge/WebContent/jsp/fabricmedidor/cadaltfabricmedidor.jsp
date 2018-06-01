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
		
		<script src="./js/fabricmedidor/cadaltfabricmedidor.js" type="text/javascript"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	    <script src="http://code.jquery.com/jquery-2.2.4.js" ></script>	
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script src="http://malsup.github.io/jquery.blockUI.js"></script>
	
	    <link href="./css/menucustomcolor.css" rel="stylesheet"/>
	    <link href="./css/footercustom.css" rel="stylesheet"/>
	    <script src='./js/validator.min.js'></script>
	</head>
	<body>
		<jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
		<div class="container">
			<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Fabricante Medidor</li>
			    <li class="active">Cadastro</li>
			</ul>
			<div class="col-sm-8" style="float: none; margin: 0 auto;">
				
				<form data-toggle="validator" role="form" id="formCadFabricMedidor" action="${acao}" method="POST" accept-charset="iso-8859-1,utf-8" onsubmit="return validarForm()">
					<input type="hidden" id="idFabricMedidor" name="idFabricMedidor" value="${fabricMedidor.idFabricMedidor}" />
					<fieldset>
					    <legend>${tituloTela}</legend>
				  	
						<div id="divAviso">${aviso}</div>
					
						<div class="col-sm-8">
							<div class="form-group">
								<label class="control-label">Nome do Fabricante</label><label class="text-danger">*</label>
								<input type="text" class="form-control" id="fabricNome" name="fabricNome" value="${fabricMedidor.fabricante}" maxlength="50" required />			
							</div>	
						</div>	

						<div class="col-sm-4">
							<div class="form-group">
								<label class="control-label">Situa&ccedil;&atilde;o</label><label class="text-danger">*</label>
								<select class="form-control" id="situacao" name="situacao" required >
									<option value="" selected>Selecione...</option>
									<c:forEach var="situacao" items="${listSituacao}">
		                     		        <c:choose>
		                                   	<c:when test="${situacao.situacao eq fabricMedidor.situacao}">
		                                   		<option value="${situacao.situacao}" selected="true">${situacao.descricao}</option> 
		                                     	</c:when>
		                                     	<c:otherwise>
		                                     		<option value="${situacao.situacao}">${situacao.descricao}</option>
		                                     	</c:otherwise>
		                                    </c:choose>
			                     	</c:forEach>
								</select>					
							</div>			
						</div>
						
						<div class="col-sm-3">
                        	<div class="form-group">
                        		<label class="control-label"></label><label class="text-danger">* Campos Obrigatórios</label>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-12 text-center">
								<button type="submit" class="btn btn-primary">${btNome}</button>
							</div>
						</div>
					</fieldset>
				</form>
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