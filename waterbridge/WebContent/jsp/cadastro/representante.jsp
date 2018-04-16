
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
            });
        </script>
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
					<form action="" method="post">
						<fieldset>
							<legend class="text-left">Cadastro de Representante</legend>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label" for="name">Nome/Razão Social:</label>
									<input type="text" class="form-control" id="name" name="name" value="" />
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">Telefone:</label>
									<input type="text" class="form-control" id="telefone" name="telefone" value="" />
								</div>
							</div>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label" for="name">Responsável:</label>
									<input type="text" class="form-control" id="responsavel" name="responsavel" value="" />
								</div>
							</div>
							
							<div class="col-sm-12"></div>
							
							<div class="col-sm-7">
								<div class="form-group">
	                                <label>Busca de Logradouro no Google</label>
	                                <input id="autocomplete" class="form-control" placeholder="Digite o Endereço ou CEP" onFocus="geolocate()" type="text" size="100"></input>
	                            </div>
                            </div>
	                            
                            <div class="col-sm-5">
                            	<div class="form-group">
	                                <label>Coordenadas</label>
	                                <div class="input-group">
                                        <input class="form-control" type="text" name="coordenadas" id="coordenadas" value="" readonly></input>
	                                    <input class="form-control" type="hidden" name="latitude" id="latitude" value=""></input>
	                                    <input class="form-control" type="hidden" name="longitude" id="longitude" value=""></input>
	                                    <span class="input-group-btn"><!-- Append button addon using class input-group-lg -->
	                                        <button class="btn btn-default" type="button" onclick="abrirMapa()">Mapa <span class="glyphicon glyphicon-map-marker text-danger"></span></button>
	                                    </span>
	                                </div>
                                </div>
							</div>
	                            
                            <div class="col-sm-7">
								<div class="form-group">
	                                <label>Endereço</label>
	                                <input class="form-control" type="text" name="endereco" id="route" value="" maxlength="100" value="" />
	                            </div>
                            </div>
	                            
                            <div class="col-sm-2">
                            	<div class="form-group">
	                                <label>Número</label>
	                                <input class="form-control" type="text" name="numero" id="street_number" value="" maxlength="6" value="" />
	                            </div>
                            </div>
	                            
                            <div class="col-sm-3">
                            	<div class="form-group">
	                                <label>Complemento</label>
	                                <input class="form-control" type="text" name="compl" id="locality" value="" type="text" maxlength="50" value="" />
	                            </div>
	                        </div>
	                        
                            <div class="col-sm-5">
	                        	<div class="form-group">
	                                <label>Município</label>
	                                <input class="form-control" type="text" name="municipio" id="municipio" value="" maxlength="100" value="" />
	                            </div>
                            </div>
	                        
                            <div class="col-sm-4">
	                        	<div class="form-group">
	                                <label>Estado</label>
	                                <input class="form-control" type="text" name="estado" id="administrative_area_level_1" value="" maxlength="100" value="" />
	                            </div>
                            </div>
	                            
	                        <div class="col-sm-3">
	                        	<div class="form-group">
	                                <label>CEP</label>
	                                <input class="form-control" type="text" name="cep" id="postal_code" value="" maxlength="9" value="" />
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
