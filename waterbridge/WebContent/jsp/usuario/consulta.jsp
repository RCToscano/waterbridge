
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
        <script>
            $(function () {
                $("#cpf").mask("999.999.999-99");
            });
        </script>
    </head>
    <body>
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Usuário</li>
			    <li class="active">Consulta</li>
			</ul>
			
			<div class="row">
				<div class="col-md-8 col-md-offset-2">
				
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
				
					<form action="UsuarioBO?acao=pesquisar" method="post" accept-charset="iso-8859-1,utf-8" onSubmit="return validaForm()">
						<fieldset>
							<legend class="text-left">Consulta de Usu&aacute;rio</legend>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label>Usuários:</label>
									<select class="form-control" name="usuario" id="usuario" onChange="verificaUsuario()">
				                        <option value="" selected>Selecione...</option>
				                        <c:forEach var="listaUsuarios" items="${listaUsuarios}">
	                             			<option value="${listaUsuarios.idUser}" >${listaUsuarios.nome}</option>
				                        </c:forEach>
				                    </select>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">CPF:</label>
									<input type="tel" class="form-control" id="cpf" name="cpf" placeholder="999.999.999-99" value="${cpf}"  onKeyPress="verificaUsuario()" onBlur="verificaUsuario()"/>
								</div>
							</div>
							
							<div class="col-sm-7">
								<div class="form-group">
	                                <label>Endere&ccedil;o</label>
	                                <input class="form-control" type="text" name="endereco" id="endereco" maxlength="100" value="${endereco}" onKeyPress="verificaUsuario()" onBlur="verificaUsuario()"/>
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