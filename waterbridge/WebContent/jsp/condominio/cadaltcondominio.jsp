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
	    
	    <script src="./js/jquery.mask.min.js" type="text/javascript"></script>   
        <script>
            $(function () {
                $("#telefone").mask("(99) 99999-9999");
                $("#telefone").mask("(99) 99999-9999");
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
			    <li class="active">Cadastro</li>
			    <li class="active">Condomínio</li>
			</ul>
			
			<div class="col-sm-9" style="float: none; margin: 0 auto;">
				<h3>Cadastro de Condom&iacute;nio</h3>
				<div id="divAviso">${aviso}</div>
				<form role="form" id="formCadBridge" action="BridgeBO?acao=2" method="POST" class="form-horizontal" accept-charset="iso-8859-1,utf-8" style="margin-top: 30px;" onsubmit="return cadastrarBridge()">
					<div class="form-group">
						<div class="col-sm-6">
							<label class="control-label">Nome/Razão Social:</label>
							<input type="text" class="form-control" id="nome" name="nome" value="" />
						</div>
						<div class="col-sm-3">
							<label>Tipo</label> 
							<select class="form-control" id="cnpTp" name="cnpTp" onchange="mascaraCnp()">
								<option value="0" selected>Selecione...</option>
								<c:forEach var="cnpTp" items="${listCnpTp}">
	                         		<option value="${cnpTp.idCnpTp}">${cnpTp.descricao}</option>                         		
		                     	</c:forEach>
							</select>
						</div>
						<div class="col-sm-3">
							<label class="control-label">CPF / CNPJ:</label>
							<input type="text" class="form-control" id="cnp" name="cnp" value="" required/>
						</div>					
					</div>					
					<div class="form-group">
						<div class="col-sm-6">
							<label class="control-label">Nome do Responsável:</label>
							<input type="text" class="form-control" id="responsavel" name="responsavel" value=""/>
						</div>
						<div class="col-sm-3">
							<label class="control-label">Telefone Fixo:</label>
							<input type="text" class="form-control" id="telFixo" name="telFixo" placeholder="(XX) XXXX-XXXX" value=""/>
						</div>
						<div class="col-sm-3">
							<label class="control-label">Telefone Celular:</label>
							<input type="text" class="form-control" id="telCel" name="telCel" placeholder="(XX) XXXXX-XXXX" value=""/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-6">
							<label class="control-label">E-mail:</label>
							<input type="text" class="form-control" id="email" name="email" value=""/>
						</div>
						<div class="col-sm-3">
							<label class="control-label">Número do Contrato:</label>
							<input type="text" class="form-control" id="contratoNum" name="contratoNum" value="" />
						</div>
						<div class="col-sm-3">
							<label class="control-label">Ciclo da Conta:</label>
							<input type="text" class="form-control" id="contaCiclo" name="contaCiclo" value="" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-6">
	                        <label>Busca de Logradouro no Google</label>
	                        <input id="autocomplete" class="form-control" placeholder="Digite o Endereço ou CEP" onFocus="geolocate()" type="text" size="100"></input>
                        </div>
                        <div class="col-sm-6">
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
					<div class="form-group">
						<div class="col-sm-6">
	                        <label>Endere&ccedil;o</label>
	                        <input class="form-control" type="text" name="endereco" id="route" value="" maxlength="100" value="" required/>
                        </div>
                        <div class="col-sm-3">
                            <label>N&uacute;mero</label>
                            <input class="form-control" type="text" name="numero" id="street_number" value="" maxlength="6" value="" required/>
                        </div>
                        <div class="col-sm-3">
                            <label>Complemento</label>
                            <input class="form-control" type="text" name="compl" id="locality" value="" type="text" maxlength="50" value="" required/>
                        </div>
					</div>
					<div class="form-group">
						<div class="col-sm-6">
	                        <label>Município</label>
	                        <input class="form-control" type="text" name="municipio" id="locality" value="" maxlength="100" value="" required/>
                        </div>
                        <div class="col-sm-3">
	                        <label>Estado</label>
	                        <input class="form-control" type="text" name="estado" id="administrative_area_level_1" value="" maxlength="100" value="" required/>
                        </div>                            
                        <div class="col-sm-3">
	                        <label>CEP</label>
	                        <input class="form-control" type="text" name="cep" id="postal_code" value="" maxlength="9" value="" required/>
                        </div>
					</div>
					<div class="form-group">
						<div class="col-sm-6">
	                        <label>País</label>
	                        <input class="form-control" type="text" name="pais" id="country" value="${colaborador.municipio.pais}" maxlength="50"></input>
                        </div>
					</div>
					<div class="form-group">
						<div class="col-md-12 text-center">
							<button type="submit" class="btn btn-primary">Cadastrar</button>
						</div>
					</div>
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
