<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<title>WaterBridge</title>	
	<link rel="icon" type="image/png" href="./images/favicon.ico"/>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<script src="./js/bridge/cadaltbridge.js" type="text/javascript"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-2.2.4.js" ></script>	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<script src="http://malsup.github.io/jquery.blockUI.js"></script>

	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/locale/pt-br.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
    
    <link href="./css/menucustomcolor.css" rel="stylesheet"/>
    <link href="./css/footercustom.css" rel="stylesheet"/>   
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js"></script>
    <script type="text/javascript">
		$(function() {
			$("#custoMensal").maskMoney({prefix:'R$ ', allowNegative: true, thousands:'.', decimal:',', affixesStay: false});
		});
    </script>
</head>
<body>
	<jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
	<div class="container">
		<ul class="breadcrumb">
		    <li><a href="HomeBO?acao=home">Home</a></li>
		    <li class="active">Bridge</li>
		    <li class="active">Cadastro</li>
		</ul>
		<div class="col-sm-8" style="float: none; margin: 0 auto;">
			<fieldset>
			    <legend>${tituloTela}</legend>
		  	</fieldset>
			<div id="divAviso">${aviso}</div>
			<form role="form" id="formCadBridge" action="${acao}" method="POST" class="form-horizontal" accept-charset="iso-8859-1,utf-8" onsubmit="return validarForm()">
				<input type="hidden" id="idBridge" name="idBridge" value="${bridge.idBridge}" />
				<div class="form-group">
				    <div class="col-sm-4">
						<label>N&deg; Device</label>
						<input type="text" class="form-control" id="deviceNum" name="deviceNum" value="${bridge.deviceNum}" maxlength="20" ${devicereadonly} required />			
					</div>
					<div class="col-sm-4">
						<label>Data de Ativa&ccedil;&atilde;o</label>
	                    <div class='input-group date' id='dpDtAtivacao'>
		                    <input type='text' class="form-control" id='dtAtivacao' name='dtAtivacao' value="${bridge.dtAtivacao}" required />
	            			<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
		                    <script type="text/javascript">
			                    $(function () {
		                            $('#dpDtAtivacao').datetimepicker({
		                                //inline: true,
		                                sideBySide: true
		                            });
		                        });
		                    </script>
		                </div> 
					</div>
					<div class="col-sm-4">
		            	<label>Validade do Token</label>
		            	<div class='input-group date' id='dpValidadeToken'>
		                    <input type='text' class="form-control" id='validadeToken' name='validadeToken' value="${bridge.validadeToken}" required/>
	            			<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
		                    <script type="text/javascript">
		                        $(function () {
		                            $('#dpValidadeToken').datetimepicker({
		                                //inline: true,
		                                //sideBySide: true
		                                format: 'DD/MM/YYYY'
		                            });
		                        });
		                    </script>
		                </div>    
					</div>				
				</div>
				<div class="form-group">
					<div class="col-sm-4">
						<label>Tipo de Alimenta&ccedil;&atilde;o</label> 
						<select class="form-control" id="tpAlimentacao" name="tpAlimentacao" required>
							<option value="" selected>Selecione...</option>
							<c:forEach var="bridgeTpAlim" items="${listBridgeTpAlim}">
                         		<c:choose>
									<c:when test="${bridgeTpAlim.idBridgeTpAlim == bridge.bridgeTpAlim.idBridgeTpAlim}">
										<option value="${bridgeTpAlim.idBridgeTpAlim}" selected="selected">${bridgeTpAlim.tpAlimentacao}</option>
									</c:when>
									<c:otherwise>
										<option value="${bridgeTpAlim.idBridgeTpAlim}">${bridgeTpAlim.tpAlimentacao}</option>
									</c:otherwise>
								</c:choose>
	                     	</c:forEach>
						</select>
					</div>
					<div class="col-sm-4">						
						<label>Custo Mensal (R$)</label>
						<fmt:setLocale value="pt-BR" />
						<input type="text" class="form-control" id="custoMensal" name="custoMensal" value="<fmt:formatNumber value="${bridge.custoMensal}" type="currency" currencySymbol=""/>" maxlength="15" required/>
                        <script>
                            $(function() {
                                $("#custoMensal").maskMoney({prefix:'R$ ', allowNegative: true, thousands:'.', decimal:',', affixesStay: false});
                            });
                        </script>
					</div>
					<div class="col-sm-4">
						<label>Taxa de Envio Di&aacute;rio (minutos)</label>
						<input type="text" class="form-control" id="taxaEnvio" name="taxaEnvio" value="${bridge.taxaEnvio}" maxlength="4" required/>
					</div>
				</div>
				<div class="form-group">
				    <div class="col-sm-4">
						<label>Tipo</label> 
						<select class="form-control" id="idBridgeTp" name="idBridgeTp" required>
							<option value="" selected>Selecione...</option>
							<c:forEach var="bridgeTp" items="${listBridgeTp}">
                         		<c:choose>
									<c:when test="${bridgeTp.idBridgeTp == bridge.bridgeTp.idBridgeTp}">
										<option value="${bridgeTp.idBridgeTp}" selected="selected">${bridgeTp.bridgeTp}</option>
									</c:when>
									<c:otherwise>
										<option value="${bridgeTp.idBridgeTp}">${bridgeTp.bridgeTp}</option>
									</c:otherwise>
								</c:choose>
	                     	</c:forEach>
						</select>
					</div>
					<div class="col-sm-8">
						<label>Local</label> 
						<select class="form-control" id="idCondominio" name="idCondominio" required >
							<option value="" selected>Selecione...</option>
							<c:forEach var="condominio" items="${listCondominio}">
                   		        <c:choose>
                                  	<c:when test="${condominio.idCondominio == bridge.idCondominio}">
                                 		<option value="${condominio.idCondominio}" selected="true">${condominio.nome} - ${condominio.endereco} ${condominio.numero} ${condominio.compl}</option> 
                                   	</c:when>
                                   	<c:otherwise>
                                   		<option value="${condominio.idCondominio}">${condominio.nome} - ${condominio.endereco} ${condominio.numero} ${condominio.compl}</option>
                                   	</c:otherwise>
                            	</c:choose>
	                     	</c:forEach>
						</select>					
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-4">
						<label>Situa&ccedil;&atilde;o</label> 
						<select class="form-control" id="situacao" name="situacao" required >
							<option value="" selected>Selecione...</option>
							<c:forEach var="situacao" items="${listSituacao}">
                     		        <c:choose>
                                   	<c:when test="${situacao.situacao eq bridge.situacao}">
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
				<div class="form-group">
					<div class="col-sm-12">
						<label>Descri&ccedil;&atilde;o</label>
						<textarea class="form-control" rows="3" id="descricao" name="descricao" required>${bridge.descricao}</textarea>
						<script type="text/javascript">
							$('#descricao').keyup(function () {
								var maxLength = 140;
					        	var text = $(this).val();
					           	var textLength = text.length;
					           	if (text.length > maxLength) {
					            	$(this).val(text.substring(0, (maxLength)));
					           	}
					       	});												
						</script>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-12 text-center">
						<button type="submit" class="btn btn-primary">${btNome}</button>
					</div>
				</div>
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