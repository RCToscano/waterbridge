
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
        <script src="./js/jquery.mask.min.js" type="text/javascript"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
		<script type="text/javascript" src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
        <script>
            $(function () {
                $("#telefone").mask("(99) 99999-9999");
            });
        </script>
    </head>
    <body>
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Medidor</li>
			    <li class="active">${titulo}</li>
			</ul>
			
			<div id="divAviso" name="divAviso" class="alert alert-danger" style="display:${display};">
				<strong><label id='aviso' name='aviso'/>${aviso}</strong>
			</div>
			
			<c:if test="${not empty sucesso}">
				<div class="alert alert-success">
					<strong><c:out value="${sucesso}"/></strong>
				</div>
			</c:if>
			
			<div class="row">
				<div class="col-md-8 col-md-offset-2">
					<form action="" method="post">
						<fieldset>
							<legend class="text-left">${titulo} de Medidor (Hidrômetro)</legend>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">Fabricante:</label>
									<input type="text" class="form-control" id="fabricante" name="fabricante" value="" required/>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">Modelo:</label>
									<input type="text" class="form-control" id="modelo" name="modelo" value="" required/>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">N&deg; S&eacute;rie:</label>
									<input type="text" class="form-control" id="serie" name="serie" value="" required/>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">Tipo:</label>
									<input type="text" class="form-control" id="tipo" name="tipo" value="" required/>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">Chave de Decriptografia:</label>
									<input type="text" class="form-control" id="chave" name="chave" value="" required/>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">Validade Bateria:</label>
									<input type="text" class="form-control" id="bateria" name="bateria" value="" required/>
								</div>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group">
									<label class="control-label" for="descricao">Informa&ccedil;&atilde;es Adicionais:</label>
									<textarea class="form-control" rows="3" name="descricao" id="descricao"></textarea>
								</div>
							</div>
							
	    					<div class="col-sm-12">
								<div class="form-group">
									<div class="col-md-12 text-center">
										<button type="submit" class="btn btn-primary">${botao}</button>
									</div>
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
