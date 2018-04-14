
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
        <script>
            $(function () {
                $("#telefone").mask("(99) 99999-9999");
                $("#cpf").mask("999.999.999-99");
                $("#cnpj").mask("99.999.999/9999-99");
            });
        </script>
    </head>
    <body>
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Cadastro</li>
			    <li class="active">Condomínio</li>
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
					<form action="" method="post">
						<fieldset>
							<legend class="text-left">Cadastro de Condomínio</legend>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label">Nome/Razão Social:</label>
									<input type="text" class="form-control" id="name" name="name" value="" />
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">Telefone:</label>
									<input type="text" class="form-control" id="telefone" name="telefone" value="" />
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">CPF:</label>
									<input type="text" class="form-control" id="cpf" name="cpf" placeholder="999.999.999-99" value="" />
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">CNPJ:</label>
									<input type="text" class="form-control" id="cnpj" name="cnpj" placeholder="99.999.999/9999-99" value="" />
								</div>
							</div>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label">Responsável:</label>
									<input type="text" class="form-control" id="responsavel" name="responsavel" value="" />
								</div>
							</div>
							
							<div class="col-sm-12"></div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">Latitute:</label>
									<input type="text" class="form-control" id="latitute" name="latitute" value="" />
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">Longitude:</label>
									<input type="text" class="form-control" id="longitude" name="longitude" value="" />
								</div>
							</div>
							
							<div class="col-sm-12"></div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">Número do Contrato:</label>
									<input type="text" class="form-control" id="contrato" name="contrato" value="" />
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">Ciclo da Conta:</label>
									<input type="text" class="form-control" id="ciclo" name="ciclo" value="" />
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">Volume Macro Medidor:</label>
									<input type="text" class="form-control" id="volume" name="volume" value="" />
								</div>
							</div>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label">E-mail/Login:</label>
									<input type="email" class="form-control" id="email" name="email" value="" />
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">Senha:</label>
									<input type="password" class="form-control" id="senha" name="senha" value="" />
								</div>
							</div>
							
	    
	    					<div class="col-sm-12">
								<div class="form-group">
									<div class="col-md-12 text-center">
										<button type="submit" class="btn btn-primary">Cadastrar</button>
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
