
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
	    <script src='./js/usuario/usuario.js'></script>
        <script>
            $(function () {
                $("#telefoneFixo").mask("(99) 9999-9999");
                $("#telefoneCelular").mask("(99) 99999-9999");
                $("#cpf").mask("999.999.999-99");
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
			    <li class="active">Usuário</li>
			    <li class="active">${titulo}</li>
			</ul>
			
			<div id="divAviso" name="divAviso" class="alert alert-danger" style="display:${display};">
				<strong><label id='aviso' name='aviso'/>${aviso}</strong>
			</div>
			
			<c:if test="${not empty sucesso}">
				<div class="alert alert-success">
					<strong><c:out value="${sucesso}"/></strong>
				</div>
			</c:if>
			
			<div class="row">
				<div class="col-md-8 col-md-offset-2">
					<form action="UsuarioBO?acao=inserir" method="post" accept-charset="iso-8859-1,utf-8" onSubmit="return validaForm()">
						<input type="hidden" id="id" name="id" value="${usuario.idUser}" />
						
						<fieldset>
							<legend class="text-left">${titulo} de Usu&aacute;rio</legend>
							
							<div class="col-sm-12"></div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">Perfil do Usu&aacute;rio</label>
									<select class="form-control" name="perfil" id="perfil" required>
										<option value="" selected>Selecione...</option>
										<c:forEach var="listaPerfil" items="${listaPerfil}">
	                                    	<c:choose>
		                                        <c:when test="${usuario.perfil.idPerfil == listaPerfil.idPerfil}">
		                                            <option value="${listaPerfil.idPerfil}" selected="true">${listaPerfil.perfil}</option>
		                                        </c:when>
		                                        <c:otherwise>
		                                        	<option value="${listaPerfil.idPerfil}" >${listaPerfil.perfil}</option>
		                                        </c:otherwise>
	                                        </c:choose>
	                                    </c:forEach>
									</select>
								</div>
							</div>

							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label">CPF</label>
									<input type="text" class="form-control" id="cpf" name="cpf" placeholder="999.999.999-99" value="${usuario.cpf}" required/>
								</div>
							</div>
							
							<div class="col-sm-12"></div>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label">Nome</label>
									<input type="text" class="form-control" id="name" name="name" value="${usuario.nome}" required/>
								</div>
							</div>							
							
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label">Data de Nascimento</label>
									<div class='input-group date' id='datetimepicker1'>
										<input type="text" class="form-control" id="dtNascimento" name="dtNascimento" data-date-format="DD/MM/YYYY" placeholder="dd/mm/aaaa" value="${usuario.dtNasc}"/>
										<span class="input-group-addon">
											<span class="glyphicon glyphicon-calendar"></span>
										</span>
										<script type="text/javascript">
										    $(function () {
										        $('#datetimepicker1').datetimepicker({
										            format: 'DD/MM/YYYY'
										        });
										    });
										</script>
									</div>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label">Sexo</label>
									<select class="form-control" name="sexo" id="sexo" required>
										<option value="" selected>Selecione...</option>
										<c:forEach items="${listaSexo}" var="total">
											<c:choose>
												<c:when test="${total.id == usuario.sexo}">
													<option value="${total.id}" selected>${total.descricao}</option>
												</c:when>
												<c:otherwise>
													<option value="${total.id}">${total.descricao}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label">Telefone Fixo</label>
									<input type="tel" class="form-control" id="telefoneFixo" name="telefoneFixo" placeholder="(XX) XXXX-XXXX" value="${usuario.telFixo}"/>
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label">Telefone Celular</label>
									<input type="tel" class="form-control" id="telefoneCelular" name="telefoneCelular" placeholder="(XX) XXXXX-XXXX" value="${usuario.telCel}"/>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label">Email:</label>
									<input type="text" class="form-control" id="email" name="email" value="${usuario.email}" required/>
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
	                                <label>Endere&ccedil;o</label>
	                                <input class="form-control" type="text" name="endereco" id="route" maxlength="100" value="${usuario.endereco}" required/>
	                            </div>
                            </div>
	                            
                            <div class="col-sm-2">
                            	<div class="form-group">
	                                <label>N&uacute;mero</label>
	                                <input class="form-control" type="number" name="numero" id="street_number" maxlength="6" value="${usuario.numero}" required/>
	                            </div>
                            </div>
	                            
                            <div class="col-sm-3">
                            	<div class="form-group">
	                                <label>Complemento</label>
	                                <input class="form-control" type="text" name="compl" id="locality" type="text" maxlength="50" value="${usuario.compl}"/>
	                            </div>
	                        </div>
	                        
                            <div class="col-sm-5">
	                        	<div class="form-group">
	                                <label>Munic&iacute;pio</label>
	                                <input class="form-control" type="text" name="municipio" id="municipio" maxlength="100" value="${usuario.municipio}"/>
	                            </div>
                            </div>
	                        
                            <div class="col-sm-4">
	                        	<div class="form-group">
	                                <label>Estado</label>
	                                <input class="form-control" type="text" name="estado" id="administrative_area_level_1" maxlength="2" value="${usuario.uf}" required/>
	                            </div>
                            </div>
	                            
	                        <div class="col-sm-3">
	                        	<div class="form-group">
	                                <label>CEP</label>
	                                <input class="form-control" type="text" name="cep" id="postal_code" maxlength="9" value="${usuario.cep}" required/>
	                            </div>
	                        </div>

							<c:if test="${botao == 'Alterar'}">
		                        <div class="col-sm-5">
		                        	<div class="form-group">
		                                <label>Senha</label>
		                                <input class="form-control" type="text" name="senha" id="senha" maxlength="50" value="${usuario.pass.pass}" required/>
		                            </div>
		                        </div>
	                        </c:if>
	                        
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
        <footer class="footer">
			<div class="container text-center">
				<p class="text-muted">
					Todos os direitos reservados&emsp;&emsp;-&emsp;&emsp;<label>Desenvolvido por Desoltec Engenharia</label>&emsp;&emsp;<img src="./images/logo_desoltec_rodape.png" alt="" style="margin: 0px;">
				</p>
			</div>
		</footer>
		
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
    </body>
</html>
