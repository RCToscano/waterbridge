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
	
	<script src="./js/bridge/cadastrobridge.js" type="text/javascript"></script>
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
    
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"/>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <style>
        .ui-autocomplete-loading {
            background: white url("images/ui-anim_basic_16x16.gif") right center no-repeat;
        }
    </style>
    <script>
            $(function () {
                $("#deviceNum").autocomplete({                	
                    source: function (request, response) {
                        $.ajax({
                            url: "BridgeBO?acao=5",
                            dataType: "json",
                            data: {
                            	deviceNum: $("#deviceNum").val()
                            },
                            success: function (data, textStatus, jqXHR) {

                                listBridge = data;                               
                                var texto = '';
                                if(listBridge != null && listBridge.length > 0) {
                                	
                                	for(i = 0; i < listBridge.length; i++){
                                        
                                        var bridge = listBridge[i];                    
                                        texto += '"' + String(bridge.idBridge) + '":"' + String(bridge.deviceNum) + '"';
                                        if(i + 1 < listBridge.length) {
                                        	texto += ', '
                                        }
                                    }
                                }
                                var obj = JSON.parse("{" + texto + "}");
                                response(obj);

                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                console.log(textStatus);
                            }
                        });
                    },
                    //minLength: 2,
                    select: function (event, ui) {
                    	
                    }
                });
            });
    </script>
    
    
</head>
<body>
	<jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
	<div class="container">
		<div class="col-sm-8" style="float: none; margin: 0 auto;">
			<h3>Cadastro de Bridge</h3>
			<div id="divAviso">${aviso}</div>
			<form role="form" id="formCadBridge" action="BridgeBO?acao=2" method="POST" class="form-horizontal" accept-charset="iso-8859-1,utf-8" style="margin-top: 30px;" onsubmit="return cadastrarBridge()">
				<div class="form-group">
				    <div class="col-sm-4">
						<label>N&deg; Device</label>
<!-- 						<input type="text" class="form-control" id="deviceNum" name="deviceNum" value="" maxlength="20"/> -->
						<input type='text' class="form-control" name='deviceNum' id='deviceNum' value=""></input>
					</div>
					<div class="col-sm-4">
						<label>Data de Ativa&ccedil;&atilde;o</label>
						<input type='text' class="form-control" id='dtAtivacao' name='dtAtivacao' value="" />
	                    <script type="text/javascript">
	                        $(function () {
	                            $('#dtAtivacao').datetimepicker({
	                                //inline: true,
	                                sideBySide: true
	                            });
	                        });
	                    </script>
					</div>
					<div class="col-sm-4">
		            	<label>Validade do Token</label>
	                    <input type='text' class="form-control" id='validadeToken' name='validadeToken' value="" />
	                    <script type="text/javascript">
	                        $(function () {
	                            $('#validadeToken').datetimepicker({
	                                //inline: true,
	                                //sideBySide: true
	                                format: 'DD/MM/YYYY'
	                            });
	                        });
	                    </script>
					</div>				
				</div>
				<div class="form-group">
					<div class="col-sm-4">
						<label>Tipo de Alimenta&ccedil;&atilde;o</label> 
						<select class="form-control" id="tpAlimentacao" name="tpAlimentacao">
							<option value="0" selected>Selecione...</option>
							<c:forEach var="bridgeTpAlim" items="${listBridgeTpAlim}">
                         		<option value="${bridgeTpAlim.idBridgeTpAlim}">${bridgeTpAlim.tpAlimentacao}</option>                         		
	                     	</c:forEach>
						</select>
					</div>
					<div class="col-sm-4">						
						<label>Custo Mensal</label>
						<fmt:setLocale value="pt-BR" />
						<input type="text" class="form-control" id="custoMensal" name="custoMensal" value="" maxlength="15"/>
					</div>
					<div class="col-sm-4">
						<label>Taxa de Envio Di&aacute;rio</label>
						<input type="text" class="form-control" id="taxaEnvio" name="taxaEnvio" value="" maxlength="4"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-4">
						<label>Situa&ccedil;&atilde;o</label> 
						<select class="form-control" id="situacao" name="situacao">
							<option value="0">Selecione...</option>
							<option value="A">Ativo</option>
							<option value="I">Inativo</option>							
						</select>
					</div>					
				</div>
				<div class="form-group">
					<div class="col-sm-12">
						<label>Descri&ccedil;&atilde;o</label>
						<textarea class="form-control" rows="3" id="descricao" name="descricao" ></textarea>
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
						<button type="submit" class="btn btn-primary">Cadastrar</button>
					</div>
				</div>
			</form>
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