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
		<script src="./js/condominio/cadaltcondominio.js" type="text/javascript"></script>
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
	    
	    <script src='./js/validator.min.js'></script>
	    <script src="./js/jquery.mask.min.js" type="text/javascript"></script>   
        <script>
            $(function () {
                $("#telFixo").mask("(99) 9999-9999");
                $("#telCel").mask("(99) 99999-9999");
                $("#postal_code").mask("99999-999");
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
    </head>
    <body>
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Local</li>
			    <li class="active">Cadastro</li>
			</ul>
			
			<div class="col-sm-9" style="float: none; margin: 0 auto;">
				<form data-toggle="validator" role="form" id="formCadBridge" action="${acao}" method="POST" accept-charset="iso-8859-1,utf-8" onsubmit="return validarForm()">
					<fieldset>
					    <legend>${tituloTela}</legend>
				  	
						<div id="divAviso">${aviso}</div>
						
						<input type="hidden" id="idCondominio" name="idCondominio" value="${condominio.idCondominio}"/>
						
						<div class="col-sm-6">
							<div class="form-group">
								<label class="control-label">Nome/Razão Social:</label><label class="text-danger">*</label>
								<input type="text" class="form-control" id="nome" name="nome" value="${condominio.nome}" maxlength="100" required/>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group">	
								<label class="control-label">Tipo</label>
								<select class="form-control" id="cnpTp" name="cnpTp" onchange="mascaraCnp()">
									<option value="" selected>Selecione...</option>
									<c:forEach var="cnpTp" items="${listCnpTp}">
	               			            <c:choose>
	                                    	<c:when test="${cnpTp.idCnpTp == condominio.cnpTp.idCnpTp}">
	                                    		<option value="${cnpTp.idCnpTp}" selected="true">${cnpTp.descricao}</option> 
	                                      	</c:when>
	                                      	<c:otherwise>
	                                      		<option value="${cnpTp.idCnpTp}">${cnpTp.descricao}</option>
	                                      	</c:otherwise>
	                                     </c:choose>
			                     	</c:forEach>
								</select>
							</div>
						</div>

						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label">CPF / CNPJ:
								<input type="tel" class="form-control" id="cnp" name="cnp" value="${condominio.cnp}" maxlength="20"/>
							</div>					
						</div>

						<div class="col-sm-6">
							<div class="form-group">
								<label class="control-label">Nome do Responsável:</label><label class="text-danger">*</label>
								<input type="text" class="form-control" id="responsavel" name="responsavel" value="${condominio.responsavel}" maxlength="100" required/>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label">Telefone Fixo:</label>
								<input type="tel" class="form-control" id="telFixo" name="telFixo" placeholder="(XX) XXXX-XXXX" value="${condominio.telFixo}" maxlength="20" />
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label">Telefone Celular:</label>
								<input type="tel" class="form-control" id="telCel" name="telCel" placeholder="(XX) XXXXX-XXXX" value="${condominio.telCel}"  maxlength="20" />
							</div>
						</div>
						
						<div class="col-sm-6">
							<div class="form-group">
								<label class="control-label">E-mail:</label><label class="text-danger">*</label>
								<input type="email" class="form-control" id="email" name="email" value="${condominio.email}" maxlength="100" required />
							</div>
						</div>

						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label">Número do Contrato:</label><label class="text-danger">*</label>
								<input type="text" class="form-control" id="contratoNum" name="contratoNum" value="${condominio.contratoNum}" maxlength="100" required />
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label">Ciclo da Conta:</label><label class="text-danger">*</label>
								<input type="text" class="form-control" id="contaCiclo" name="contaCiclo" value="${condominio.contaCiclo}" maxlength="2" required />
							</div>
						</div>
						
						<div class="col-sm-6">
							<div class="form-group">
								<label class="control-label">Empresa</label><label class="text-danger">*</label>
								<select class="form-control" id="idEmpresa" name="idEmpresa" required >
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
						</div>
							
						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label">Situa&ccedil;&atilde;o</label><label class="text-danger">*</label>
								<select class="form-control" id="situacao" name="situacao" required >
									<option value="" selected>Selecione...</option>
									<c:forEach var="situacao" items="${listSituacao}">
	                      		        <c:choose>
	                                    	<c:when test="${situacao.situacao eq condominio.situacao}">
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
						
						<div class="col-sm-6">
							<div class="form-group">
		                        <label>Busca de Logradouro no Google</label>
		                        <input id="autocomplete" class="form-control" placeholder="Digite o Endereço ou CEP" onFocus="geolocate()" type="text" size="100"></input>
	                        </div>
						</div>

						<div class="col-sm-6">
							<div class="form-group">
		                        <label>Coordenadas</label>
		                        <div class="input-group">
		                            <input class="form-control" type="text" name="coordenadas" id="coordenadas" value="" readonly></input>
		                            <span class="input-group-btn"><!-- Append button addon using class input-group-lg -->
		                                <button class="btn btn-default" type="button" onclick="abrirMapa()">Mapa <span class="glyphicon glyphicon-map-marker text-danger"></span></button>
		                            </span>
		                        </div>
							</div>
						</div>
						
						<div class="col-sm-6">
							<div class="form-group">
		                        <label class="control-label">Endere&ccedil;o</label>
		                        <input class="form-control" type="text" name="endereco" id="route" value="${condominio.endereco}" maxlength="100"/>
	                        </div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group">
		                        <label class="control-label">Latitude</label>
		                        <input class="form-control" type="text" name="latitude" id="latitude" value="${condominio.coordX}"></input>
	                        </div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group">
		                        <label class="control-label">Longitude</label>
		                        <input class="form-control" type="text" name="longitude" id="longitude" value="${condominio.coordY}"></input>
	                        </div>
						</div>

						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label">N&uacute;mero</label>
								<input class="form-control" type="number" name="numero" id="street_number" value="${condominio.numero}" max="9999" min="0" data-error="N&deg; inválido"/>
								<div class="help-block with-errors"></div>
							</div>
						</div>
	                        
						<div class="col-sm-3">
	                    	<div class="form-group">    
	                            <label class="control-label">Complemento</label>
	                            <input class="form-control" type="text" name="compl" id="compl" type="text" value="${condominio.compl}" maxlength="50"/>
	                        </div>
						</div>
						
						<div class="col-sm-6">
							<div class="form-group">
		                        <label class="control-label">Município</label><label class="text-danger">*</label>
		                        <input class="form-control" type="text" name="municipio" id="locality" value="${condominio.municipio}" maxlength="100" required/>
	                        </div>
						</div>

						<div class="col-sm-3">
	                    	<div class="form-group">    
		                        <label class="control-label">Estado</label><label class="text-danger">*</label>
		                        <input class="form-control" type="text" name="estado" id="administrative_area_level_1" value="${condominio.uf}" maxlength="2" required/>
	                        </div>          
						</div>          

						<div class="col-sm-3">
							<div class="form-group">
		                        <label class="control-label">CEP</label>
		                        <input class="form-control" type="tel" name="cep" id="postal_code" value="${condominio.cep}" maxlength="9"/>
	                        </div>
						</div>
						
						<div class="col-sm-12"></div>
						
						<div class="col-sm-3">
	                       	<div class="form-group">
	                       		<label class="control-label"></label><label class="text-danger">* Campos Obrigatórios</label>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12 text-center">
								<button type="submit" class="btn btn-primary">${btNome}</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
					
			<!--BUSCA ENDERECO GOOGLE FUNCAO DIEGO-->
            <script>
                // This example displays an address form, using the autocomplete feature
                // of the Google Places API to help users fill in the information.
                var placeSearch, autocomplete;
                var componentForm = {
                    street_number: 'short_name',
                    route: 'long_name',
                    locality: 'long_name',
                    administrative_area_level_1: 'short_name',
                    //country: 'long_name',
                    postal_code: 'short_name'
                };

                function initAutocomplete() {
                    // Create the autocomplete object, restricting the search to geographical
                    // location types.
                    autocomplete = new google.maps.places.Autocomplete(
                            /** @type {!HTMLInputElement} */(document.getElementById('autocomplete')),
                            {types: ['geocode']});

                    // When the user selects an address from the dropdown, populate the address
                    // fields in the form.
                    autocomplete.addListener('place_changed', fillInAddress);
                }

                // [START region_fillform]
                function fillInAddress() {
                    // Get the place details from the autocomplete object.
                    var place = autocomplete.getPlace();

                    for (var component in componentForm) {
                        document.getElementById(component).value = '';
                        document.getElementById(component).disabled = false;
                        document.getElementById('latitude').value = '';
                        document.getElementById('longitude').value = '';

                        document.getElementById('coordenadas').value = '';
                    }

                    // Get each component of the address from the place details
                    // and fill the corresponding field on the form.
                    for (var i = 0; i < place.address_components.length; i++) {
                        var addressType = place.address_components[i].types[0];
                        if (componentForm[addressType]) {
                            var val = place.address_components[i][componentForm[addressType]];
                            document.getElementById(addressType).value = val;
                            document.getElementById('latitude').value = place.geometry.location.lat();
                            document.getElementById('longitude').value = place.geometry.location.lng();

                            document.getElementById('coordenadas').value = place.geometry.location.lat() + ', ' + place.geometry.location.lng();

                            //alert("val: " + val);
                        }
                    }
                }
                // [END region_fillform]

                // [START region_geolocation]
                // Bias the autocomplete object to the user's geographical location,
                // as supplied by the browser's 'navigator.geolocation' object.
                function geolocate() {

                    if (navigator.geolocation) {
                        navigator.geolocation.getCurrentPosition(function (position) {
                            var geolocation = {
                                lat: position.coords.latitude,
                                lng: position.coords.longitude
                            };
                            var circle = new google.maps.Circle({
                                center: geolocation,
                                radius: position.coords.accuracy
                            });
                            autocomplete.setBounds(circle.getBounds());
                        });
                    }
                }
                // [END region_geolocation]
            </script>                                
            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDbC6CZeFkfrPPWPI5CLqIZ5tcz5rVRgqY&signed_in=true&libraries=places&callback=initAutocomplete"
            async defer></script>
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
