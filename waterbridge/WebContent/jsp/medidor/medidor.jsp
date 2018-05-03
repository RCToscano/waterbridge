
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
		<title>WaterBridge</title>
		<link rel="icon" type="image/png" href="./images/favicon.ico"/>
		<script src="./js/medidor/medidor.js" type="text/javascript"></script>
        <script src='./js/funcoes.auxiliares.js'></script>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="./css/menucustomcolor.css" rel="stylesheet"/>
        <link href="./css/footercustom.css" rel="stylesheet"/>
<!--         <script src="./js/jquery-1.11.3.min.js"></script> -->
		<script src="http://code.jquery.com/jquery-2.2.4.js" ></script>	
        <script src="./js/jquery.mask.min.js" type="text/javascript"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
		<script type="text/javascript" src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
		
		<script src="http://malsup.github.io/jquery.blockUI.js"></script>

    </head>
    <body>
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Medidor</li>
			    <li class="active">${titulo}</li>
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
				
					<form action="MedidorBO?acao=inserir" method="post" accept-charset="iso-8859-1,utf-8" onsubmit="return validaForm()">
						<input type="hidden" id="id" name="id" value="${medidor.idMedidor}" />
						
						<fieldset>
							<legend class="text-left">${titulo} de Medidor (Hidrômetro)</legend>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">Fabricante:</label>
									<input type="text" class="form-control" id="fabricante" name="fabricante" maxlength="100" value="${medidor.fabricante}" required/>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">Modelo:</label>
									<input type="text" class="form-control" id="modelo" name="modelo" maxlength="100" value="${medidor.modelo}" required/>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">N&deg; S&eacute;rie:</label>
									<input type="text" class="form-control" id="serie" name="serie" maxlength="100" value="${medidor.serie}" required/>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">Tipo:</label>
									<input type="text" class="form-control" id="tipo" name="tipo" maxlength="100" value="${medidor.tipo}" required/>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">Chave de Decriptografia:</label>
									<input type="text" class="form-control" id="chave" name="chave" maxlength="100" value="${medidor.chaveDeCripto}" required/>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">Validade Bateria:</label>
									<input type="number" class="form-control" id="bateria" name="bateria" maxlength="2" value="${medidor.validBateria}" onKeyPress="validaTamanho(this,2)" required/>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">Número:</label>
									<input type="text" class="form-control" id="numero" name="numero" maxlength="15" value="${medidor.numero}" required/>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">Situação</label>
									<select class="form-control" name="situacao" id="situacao" required>
										<option value="" selected>Selecione...</option>
										<c:forEach var="situacao" items="${listSituacao}">
		                      		        <c:choose>
		                                    	<c:when test="${situacao.situacao eq medidor.situacao}">
		                                    		<option value="${situacao.situacao}" selected>${situacao.descricao}</option> 
		                                      	</c:when>
		                                      	<c:otherwise>
		                                      		<option value="${situacao.situacao}">${situacao.descricao}</option>
		                                      	</c:otherwise>
		                                     </c:choose>
				                     	</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group">
									<label>Condomínio</label> 
									<select class="form-control" id="idCondominio" name="idCondominio" onchange="listarBridgeCadastro()" required >
										<option value="" selected>Selecione...</option>
										<c:forEach var="condominio" items="${listCondominio}">
			                   		        <c:choose>
			                                  	<c:when test="${condominio.idCondominio == medidor.idCondominio}">
			                                 		<option value="${condominio.idCondominio}" selected="true">${condominio.nome} - ${condominio.endereco} ${condominio.numero} ${condominio.compl}</option>
			                                 		<script>
			                                 			listarBridgeAlteracao(${medidor.idBridge});
			                                 		</script> 
			                                   	</c:when>
			                                   	<c:otherwise>
			                                   		<option value="${condominio.idCondominio}">${condominio.nome} - ${condominio.endereco} ${condominio.numero} ${condominio.compl}</option>
			                                   	</c:otherwise>
			                            	</c:choose>
				                     	</c:forEach>
									</select>	
								</div>					
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">Bridge</label>
									<select class="form-control" name="bridge" id="bridge" required>
										<option value="" selected>Selecione...</option>
									</select>																		
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">Posição:</label>
									<input type="number" class="form-control" id="posicao" name="posicao" maxlength="2" value="${medidor.meterPosition}" onKeyPress="validaTamanho(this,2)" required/>
								</div>
							</div>

							<div class="col-sm-12">
								<div class="form-group">
									<label class="control-label" for="descricao">Informa&ccedil;&atilde;es Adicionais:</label>
									<textarea class="form-control" rows="3" name="descricao" id="descricao" style="resize:none;" onKeyPress="validaTamanho(this,150)">${medidor.obs}</textarea>
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
        <footer class="footer" style="background-color: #fff">
            <div class="container-fluid text-center" style="background-color: #fff; padding: 10px">
            	<div class="col-sm-4 text-center"><p class="text-muted">Todos os direitos reservados</p></div>
            	<div class="col-sm-4 text-center"><p class="text-muted"><label>Desenvolvido por Desoltec Engenharia</label></p></div>
            	<div class="col-sm-4 text-center"><img class="img-responsive center-block" src="./images/logo_desoltec_rodape.png" alt=""></div>
            </div>
        </footer>
    </body>
</html>
