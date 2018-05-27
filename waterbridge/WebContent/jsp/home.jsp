
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<title>WaterBridge</title>
	<link rel="icon" type="image/png" href="./images/favicon.ico"/>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="./css/menucustomcolor.css" rel="stylesheet"/>
	<link href="./css/footercustom.css" rel="stylesheet"/>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" />
	
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
	<script type="text/javascript" src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
	</head>
	<body>
		<jsp:include page="/menu/${sessionScope.user.perfil.menu}"></jsp:include>
		<div class="container">
			<div class="row">
				<c:if test="${sessionScope.user.perfil.perfil != 'CONSUMIDOR' or sessionScope.user.perfil.perfil == 'CONDOMINIO'}">
					<div class="col-sm-4 col-md-3">
						<div class="thumbnail">
							<div class="caption">
								<h3>Cadastro de Usuários</h3>
								<p>Cadastre os usuários de acordo com o perfil.</p>
								<p><a href="UsuarioBO?acao=cadUsuario" class="btn btn-primary" role="button">Acessar</a></p>
							</div>
						</div>
					</div>
				</c:if>
				<div class="col-sm-5 col-md-3">
					<div class="thumbnail">
						<div class="caption">
							<h3>Consumo por Medidor</h3>
							<p>Veja o consumo do Medidor em um período.</p>
							<p><a href="ConsumoMedidorBO?acao=1" class="btn btn-primary" role="button">Acessar</a></p>
						</div>
					</div>
				</div>
				<c:if test="${sessionScope.user.perfil.perfil != 'CONSUMIDOR'}">
					<div class="col-sm-5 col-md-3">
						<div class="thumbnail">
							<div class="caption">
								<h3>Consumo por Local</h3>
								<p>Veja o consumo do Local em um período.</p>
								<p><a href="ConsumoCondominioBO?acao=1" class="btn btn-primary" role="button">Acessar</a></p>
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
