<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
	    <title>WaterBridge</title>	
		<link rel="icon" type="image/png" href="./images/favicon.ico"/>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<script src="./js/funcoes.auxiliares.js" type="text/javascript"></script>
	    <script src="./js/usuarioempresa/listausuarioempresa.js" type="text/javascript"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	    <script src="http://code.jquery.com/jquery-2.2.4.js" ></script>	
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script src="http://malsup.github.io/jquery.blockUI.js"></script>

		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- 		<script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
			
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment.min.js"></script>
	    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/locale/pt-br.js"></script>
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
	    
	    <link href="./css/menucustomcolor.css" rel="stylesheet"/>
	    <link href="./css/footercustom.css" rel="stylesheet"/>
	    
	    <script src="./js/jquery.mask.min.js" type="text/javascript"></script>   
        <script>
            $(function () {
            	 //$("#cpf").mask("999.999.999-99");
            });
        </script>
        <script>
            $(document).ready(function () {
                $('input').keypress(function (e) {
                    var code = null;
                    code = (e.keyCode ? e.keyCode : e.which);
                    return (code == 13) ? false : true;
                });
            });
        </script>
        <style>
            .ui-autocomplete-loading {
                background: white url("images/ui-anim_basic_16x16.gif") right center no-repeat;
            }
        </style>
        <script>
	        $(function () {
	            $("#cpf").autocomplete({
	                source: function (request, response) {
	                    $.ajax({
	                        url: "UsuarioEmpresaBO?acao=4",
	                        dataType: "json",
	                        data: {
	                        	cpf: $("#cpf").val()
	                        },
	                        success: function (data, textStatus, jqXHR) {
	
	                        	var texto = '';
                        	 	var listUser = data;       
                        	 	
                                if(listUser != null && listUser.length > 0) {
                                	for(i = 0; i < listUser.length; i++){
                                        
                                        var user = listUser[i];                    
                                        texto += '"' + String(user.cpf) + '":"' + String(user.cpf) + ' - ' + String(user.nome) + '"';
                                        if(i + 1 < listUser.length) {
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
	                appendTo: "#divUsuarioEmpresa",
	                select: function (event, ui) {

	//                    var values = ui.item.value.split('-');
	//                    $("#codclientebusca").attr("value", values[0].trim());
	//                    $("#btbuscarassit").prop("disabled", false);
	//                    alert("Codigo: " + values[0] + " Nome: " + values[1]);
	                }
	            });
	        });
        </script>
    </head>
    <body id="corpo">
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Vínculo</li>
			    <li class="active">Usuário x Empresa</li>
			</ul>
			<div class="col-sm-12" style="float: none; margin: 0 auto;">
				<fieldset>
				    <legend>${tituloTela}</legend>
			  	</fieldset>
				<div id="divAviso">${aviso}</div>
				<form role="form" id="formUsuarioEmpresa" action="#" method="POST" class="form-horizontal" accept-charset="iso-8859-1,utf-8">
					<div class="form-group">
						<div class="col-sm-5">
							<label>Empresa</label> 
							<select class="form-control" id="idEmpresa" name="idEmpresa" required">
								<option value="" selected>Selecione...</option>
								<c:forEach var="empresa" items="${listEmpresa}">
                      		        <c:choose>
                                    	<c:when test="${empresa.idEmpresa eq condominio.idEmpresa}">
                                    		<option value="${empresa.idEmpresa}" selected="true">${empresa.nome}</option> 
                                      	</c:when>
                                      	<c:otherwise>
                                      		<option value="${empresa.idEmpresa}">${empresa.nome}</option>
                                      	</c:otherwise>
                                     </c:choose>
		                     	</c:forEach>
							</select>
						</div>
						<div class="col-sm-4" style="padding-top: 25px;">
							<button type="button" class="btn btn-primary" onclick="listarUsuarioEmpresa()">Buscar</button>
						</div>
					</div>					
				</form>			
				<div class="form-group">
					<div class="col-sm-12">
						<div class="table-responsive" id="divTable"></div>
					</div>
				</div>
			</div>
			<div id="divUsuarioEmpresa" title="Vínculo de Usuários Empresa" style="display: none;">
				<div class="form-group"><div class="col-sm-12"><label id="avisoDivUsuarioEmpresa" style="color: red;"></label></div></div>
				<div class="form-group">
					<div class="col-sm-5">
						<label class="control-label">Digite o CPF ou Nome do Usuário</label>
						<input type="text" class="form-control" id="cpf" name="cpf" value="" />
					</div>
					<div class="col-sm-1" style="padding-top: 25px;">
						<button type="button" class="btn btn-primary" onclick="inserirUsuarioEmpresa()">Inserir</button>
					</div>
				</div>
				<div class="form-group" style="margin-top: 100px;">
					<div class="col-sm-12" id="divUsuarioEmpresaLista"></div>
				</div>			
			</div>
			<style>
                .ui-dialog-titlebar {background-color: #156fc3; color: #fff;}
            </style>
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
