
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
		<title>WaterBridge</title>
		<link rel="icon" type="image/png" href="./images/favicon.ico"/>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="./css/menucustomcolor.css" rel="stylesheet"/>
        <link href="./css/footercustom.css" rel="stylesheet"/>
        <script src="./js/jquery-1.11.3.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Cadastro</li>
			    <li class="active">Representante</li>
			</ul>
			
<%-- 			<c:if test="${aviso != ''}"> --%>
<!-- 				<div class="alert alert-danger"> -->
<%-- 					<strong><c:out value="${aviso}"/></strong> --%>
<!-- 				</div> -->
<%-- 			</c:if> --%>
			
<%-- 			<c:if test="${sucesso != ''}"> --%>
<!-- 				<div class="alert alert-success"> -->
<%-- 					<strong><c:out value="${sucesso}"/></strong> --%>
<!-- 				</div> -->
<%-- 			</c:if> --%>
			
			<div class="row">
				<div class="col-md-7 col-md-offset-2">
					<form class="form-horizontal" action="" method="post">
						<fieldset>
							<legend class="text-left">Cadastro de Representante</legend>
							<div class="form-group">
								<label class="col-md-4 control-label" for="name">Nome/Razão Social:</label>
								<div class="col-md-8">
									<input id="name" name="name" type="text" class="form-control"/>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="email">Telefone:</label>
								<div class="col-md-8">
									<input id="email" name="email" type="text" placeholder="(99) 9 9999-9999" class="form-control"/>
								</div>
							</div>
	    
							<div class="form-group">
								<label class="col-md-4 control-label" for="message">Enderço:</label>
								<div class="col-md-8">
									<input id="email" name="email" type="text" class="form-control"/>
								</div>
							</div>
	    
							<div class="form-group">
								<label class="col-md-4 control-label" for="message">E-mail:</label>
								<div class="col-md-8">
									<input id="email" name="email" type="email" class="form-control"/>
								</div>
							</div>
	    
							<div class="form-group">
								<label class="col-md-4 control-label" for="message">Responsável:</label>
								<div class="col-md-8">
									<input id="email" name="email" type="text" class="form-control"/>
								</div>
							</div>
	    
							<div class="form-group">
								<div class="col-md-12 text-center">
									<button type="submit" class="btn btn-primary">Cadastrar</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
        <footer class="footer">
			<div class="container text-center">
				<p class="text-muted">
					Todos os direitos reservados&emsp;&emsp;-&emsp;&emsp;<label>Desenvolvido por Desoltec Engenharia</label>&emsp;&emsp;<img src="./images/logo_desoltec_rodape.png" alt="" style="margin: 0px;">
				</p>
			</div>
		</footer>
    </body>
</html>
