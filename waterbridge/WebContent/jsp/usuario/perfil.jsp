
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

		<script src="http://code.jquery.com/jquery-2.2.4.js" ></script>	
        <script src="./js/jquery.mask.min.js" type="text/javascript"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
		<script type="text/javascript" src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
		
		<script src="http://malsup.github.io/jquery.blockUI.js"></script>
	    
        <script src='./js/funcoes.auxiliares.js'></script>
	    <script src='./js/usuario/usuario.js'></script>
	    <script src='./js/validator.min.js'></script>
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
			    <li class="active">Perfil</li>
			</ul>
			
			<div class="row">
				<div class="col-md-8 col-md-offset-2">
				
					<div id="divAviso" name="divAviso" class="alert alert-danger alert-dismissible fade in" style="display:${display};">
						<strong><label id='aviso' name='aviso'/>${aviso}</strong>
					</div>
					
					<c:if test="${not empty sucesso}">
						<div class="alert alert-success alert-dismissible fade in">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<strong><c:out value="${sucesso}"/></strong>
						</div>
					</c:if>
				
					<ul class="nav nav-tabs">
				      <li class="active"><a href="#perfil" data-toggle="tab">Perfil</a></li>
				      <li><a href="#senha" data-toggle="tab">Senha</a></li>
				    </ul>
					<div id="myTabContent" class="tab-content">
			      		<div class="tab-pane active in" id="perfil">
					      	<form data-toggle="validator" role="form" action="UsuarioBO?acao=alterarPerfil" method="post" accept-charset="iso-8859-1,utf-8" onSubmit="return validaPerfil()">
								<input type="hidden" id="id" name="id" value="${usuario.idUser}" />
								
								<div class="col-sm-12"></div>
								
								<div class="col-sm-6" style="padding-top: 2%;">
									<div class="form-group">
										<label class="control-label">Nome</label><label class="text-danger">*</label>
										<input type="text" class="form-control" id="name" name="name" value="${usuario.nome}" required/>
									</div>
								</div>							

								<div class="col-sm-4" style="padding-top: 2%;">
									<div class="form-group">
										<label class="control-label">CPF</label><label class="text-danger">*</label>
										<input type="tel" class="form-control" id="cpf" name="cpf" placeholder="999.999.999-99" value="${usuario.cpf}" required/>
									</div>
								</div>
								
								<div class="col-sm-3">
									<div class="form-group">
										<label class="control-label">Data de Nascimento</label><label class="text-danger">*</label>
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
										<label class="control-label">Sexo</label><label class="text-danger">*</label>
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
										<label class="control-label">E-mail:</label><label class="text-danger">*</label>
										<input type="email" class="form-control" id="email" name="email" value="${usuario.email}" required/>
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
		                                    <input class="form-control" type="hidden" name="latitude" id="latitude" value="${usuario.coordx}"></input>
		                                    <input class="form-control" type="hidden" name="longitude" id="longitude" value="${usuario.coordy}"></input>
		                                    <span class="input-group-btn"><!-- Append button addon using class input-group-lg -->
		                                        <button class="btn btn-default" type="button" onclick="abrirMapa()">Mapa <span class="glyphicon glyphicon-map-marker text-danger"></span></button>
		                                    </span>
		                                </div>
	                                </div>
								</div>
		                            
	                            <div class="col-sm-7">
									<div class="form-group">
		                                <label class="control-label">Endere&ccedil;o</label><label class="text-danger">*</label>
		                                <input class="form-control" type="text" name="endereco" id="route" maxlength="100" value="${usuario.endereco}" required/>
		                            </div>
	                            </div>
		                            
	                            <div class="col-sm-2">
	                            	<div class="form-group">
		                                <label class="control-label">N&uacute;mero</label><label class="text-danger">*</label>
		                                <input class="form-control" type="number" name="numero" id="street_number" max="9999" min="0" value="${usuario.numero}" data-error="N&deg; inválido" required/>
		                            	<div class="help-block with-errors"></div>
		                            </div>
	                            </div>
		                            
	                            <div class="col-sm-3">
	                            	<div class="form-group">
		                                <label class="control-label">Complemento</label>
		                                <input class="form-control" type="text" name="compl" id="locality" type="text" maxlength="50" value="${usuario.compl}"/>
		                            </div>
		                        </div>
		                        
	                            <div class="col-sm-5">
		                        	<div class="form-group">
		                                <label class="control-label">Munic&iacute;pio</label>
		                                <input class="form-control" type="text" name="municipio" id="municipio" maxlength="100" value="${usuario.municipio}"/>
		                            </div>
	                            </div>
		                        
	                            <div class="col-sm-4">
		                        	<div class="form-group">
		                                <label class="control-label">Estado</label><label class="text-danger">*</label>
		                                <input class="form-control" type="text" name="estado" id="administrative_area_level_1" maxlength="2" value="${usuario.uf}" required/>
		                            </div>
	                            </div>
		                            
		                        <div class="col-sm-3">
		                        	<div class="form-group">
		                                <label class="control-label">CEP</label><label class="text-danger">*</label>
		                                <input class="form-control" type="tel" name="cep" id="postal_code" maxlength="9" value="${usuario.cep}" required/>
		                            </div>
		                        </div>
		                        
		                        <div class="col-sm-3">
		                        	<div class="form-group">
		                        		<label class="control-label"></label><label class="text-danger">* Campos Obrigatórios</label>
									</div>
								</div>
		                        
		    					<div class="col-sm-12">
									<div class="form-group">
										<div class="col-md-12 text-center">
											<button type="submit" class="btn btn-primary">Alterar</button>
										</div>
									</div>
								</div>
							</form>
						</div>
						<div class="tab-pane fade" id="senha">
					    	<form data-toggle="validator" role="form" action="UsuarioBO?acao=alterarSenha" method="post" accept-charset="iso-8859-1,utf-8" onSubmit="return validaSenha()">
					        	<div class="col-sm-4" style="padding-top: 2%;">
									<div class="form-group">
					        			<label class="control-label">Senha Atual</label><label class="text-danger">*</label>
										<input type="password" class="form-control input-sm" name="senha" id="senha" maxlength="50" required/>
									</div>
								</div>
								<div class="col-sm-12"></div>
					        	<div class="col-sm-4">
									<div class="form-group">
					        			<label class="control-label">Nova Senha</label><label class="text-danger">*</label>
										<input type="password" class="form-control input-sm" name="novaSenha" id="novaSenha" maxlength="50" required/>
									</div>
								</div>
								<div class="col-sm-12"></div>
					        	<div class="col-sm-4">
									<div class="form-group">
					        			<label class="control-label">Confirmação de Senha</label><label class="text-danger">*</label>
										<input type="password" class="form-control input-sm" name="confSenha" id="confSenha" maxlength="50" required/>
									</div>
								</div>
								<div class="col-sm-12"></div>
								<div class="col-sm-3">
		                        	<div class="form-group">
		                        		<label class="control-label"></label><label class="text-danger">* Campos Obrigatórios</label>
									</div>
								</div>
								<div class="col-sm-12"></div>
								<div class="col-sm-12">
									<div class="form-group">
										<div class="col-md-12 text-center">
											<button type="submit" class="btn btn-primary">Alterar</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
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
