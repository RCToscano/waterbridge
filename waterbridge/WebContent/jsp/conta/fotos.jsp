
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
		<title>WaterBridge</title>
		<link rel="icon" type="image/png" href="./images/favicon.ico"/>
        
        <script src="./js/funcoes.auxiliares.js" type="text/javascript"></script>
	    <script src="./js/conta/consulta.js" type="text/javascript"></script>
	    
   		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
	    <script src="http://code.jquery.com/jquery-2.2.4.js" ></script>	
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script src="http://malsup.github.io/jquery.blockUI.js"></script>

		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
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
    	<div class="container">
	    	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Conta</li>
			    <li class="active">Fotos</li>
			</ul>
			
			<h2>Fotos da Conta ${idConta}</h2>

			<div class="panel-group">
				
				<div class="panel panel-primary">
					<div class="panel-heading"><label>Informações da Conta</label></div>
					<div class="panel-body">
					
						<div class="col-sm-2">
							<div class="form-group">
								<label for="id">ID Conta: </label> 
								<input type="text" class="form-control input-sm" name="id" id="id" value="${idConta}" disabled />
							</div>
						</div>
					
						
						
					</div> <!-- Fim Painel Body -->
				</div> <!-- Fim Painel -->
				
				<c:choose>
					<c:when test="${empty listaFotos}">
						<div class="panel panel-primary">
							<div class="panel-body">
								<div class="col-sm-12">
									<div class="alert alert-warning">
										<strong>Nenhuma foto encontrada para a Conta</strong>
									</div>
								</div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach var="foto" items="${listaFotos}">
							<div class="panel panel-primary">
								<div class="panel-body">
									<div class="col-sm-12 text-center">
										<img src="Serializacao?path=${foto.diretorio}&imagem=${foto.nome}" alt="" style="max-width: 100%; margin: 0px;"></img>
									</div>
								</div>
							</div>
		                </c:forEach>
					</c:otherwise>
				</c:choose>
			</div> <!-- Fim Painel -->
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