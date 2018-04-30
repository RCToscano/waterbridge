
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
			    <li class="active">Condomínio</li>
			    <li class="active">Cadastro</li>
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
				<div class="col-md-8 col-md-offset-2">
					<form action="" method="post">
						<fieldset>
							<legend class="text-left">Cadastro de Condomínio</legend>
							
							<div class="col-sm-8">
								<div class="form-group">
									<label class="control-label">Nome/Razão Social:</label>
									<input type="text" class="form-control" id="name" name="name" value="" />
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">CNPJ:</label>
									<input type="text" class="form-control" id="cnpj" name="cnpj" placeholder="99.999.999/9999-99" value="" required/>
								</div>
							</div>
							
							
<!-- 							<div class="col-sm-6"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label">Representante Responsável:</label> -->
<!-- 									<select class="form-control" name="representante" id="representante" required> -->
<!-- 										<option value="" selected>Selecione...</option> -->
<!-- 										<option value="1">Representante 1</option> -->
<!-- 										<option value="2">Representante 2</option> -->
<!-- 										<option value="3">Representante 3</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
							
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label">Nome do Responsável:</label>
									<input type="text" class="form-control" id="responsavel" name="responsavel" value=""/>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label">Telefone Fixo:</label>
									<input type="text" class="form-control" id="telefoneResidencial" name="telefoneResidencial" placeholder="(XX) XXXX-XXXX" value=""/>
								</div>
							</div>
							
<!-- 							<div class="col-sm-4"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label">Telefone Comercial:</label> -->
<!-- 									<input type="text" class="form-control" id="telefoneComercial" name="telefoneComercial" placeholder="(XX) XXXX-XXXX" value=""/> -->
<!-- 								</div> -->
<!-- 							</div> -->
							
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label">Telefone Celular:</label>
									<input type="text" class="form-control" id="telefoneCelular" name="telefoneCelular" placeholder="(XX) XXXXX-XXXX" value=""/>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label">E-mail:</label>
									<input type="text" class="form-control" id="email" name="email" value=""/>
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
	                                <input class="form-control" type="text" name="endereco" id="route" value="" maxlength="100" value="" required/>
	                            </div>
                            </div>
	                            
                            <div class="col-sm-2">
                            	<div class="form-group">
	                                <label>Número</label>
	                                <input class="form-control" type="text" name="numero" id="street_number" value="" maxlength="6" value="" required/>
	                            </div>
                            </div>
	                            
                            <div class="col-sm-3">
                            	<div class="form-group">
	                                <label>Complemento</label>
	                                <input class="form-control" type="text" name="compl" id="locality" value="" type="text" maxlength="50" value="" required/>
	                            </div>
	                        </div>
	                        
                            <div class="col-sm-5">
	                        	<div class="form-group">
	                                <label>Município</label>
	                                <input class="form-control" type="text" name="municipio" id="municipio" value="" maxlength="100" value="" required/>
	                            </div>
                            </div>
	                        
                            <div class="col-sm-4">
	                        	<div class="form-group">
	                                <label>Estado</label>
	                                <input class="form-control" type="text" name="estado" id="administrative_area_level_1" value="" maxlength="100" value="" required/>
	                            </div>
                            </div>
	                            
	                        <div class="col-sm-3">
	                        	<div class="form-group">
	                                <label>CEP</label>
	                                <input class="form-control" type="text" name="cep" id="postal_code" value="" maxlength="9" value="" required/>
	                            </div>
	                        </div>
	                        <div class="col-sm-3">
	                        	<div class="form-group">
	                                <label>País</label>
	                                <input class="form-control" type="text" name="pais" id="country" value="${colaborador.municipio.pais}" maxlength="50"></input>
                               	</div>
	                        </div>
							
<!-- 							<div class="col-sm-4"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label">Latitute:</label> -->
<!-- 									<input type="text" class="form-control" id="latitute" name="latitute" value="" required/> -->
<!-- 								</div> -->
<!-- 							</div> -->
							
<!-- 							<div class="col-sm-4"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label">Longitude:</label> -->
<!-- 									<input type="text" class="form-control" id="longitude" name="longitude" value="" required/> -->
<!-- 								</div> -->
<!-- 							</div> -->
							
							<div class="col-sm-12"></div>
							
							
							
<!-- 							<div class="col-sm-6"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label">E-mail/Login:</label> -->
<!-- 									<input type="email" class="form-control" id="email" name="email" value="" /> -->
<!-- 								</div> -->
<!-- 							</div> -->
							
<!-- 							<div class="col-sm-4"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label">Senha:</label> -->
<!-- 									<input type="password" class="form-control" id="senha" name="senha" value="" /> -->
<!-- 								</div> -->
<!-- 							</div> -->
							
	    
	    					<div class="col-sm-12">
								<div class="form-group">
									<div class="col-md-12 text-center">
										<button type="submit" class="btn btn-primary">Cadastrar</button>
									</div>
								</div>
							</div>
						</fieldset>
					</form>
					
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
                    country: 'long_name',
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
        <footer class="footer">
			<div class="container text-center">
				<p class="text-muted">
					Todos os direitos reservados&emsp;&emsp;-&emsp;&emsp;<label>Desenvolvido por Desoltec Engenharia</label>&emsp;&emsp;<img src="./images/logo_desoltec_rodape.png" alt="" style="margin: 0px;">
				</p>
			</div>
		</footer>
    </body>
</html>
