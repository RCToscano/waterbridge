
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
		<title>WaterBridge</title>
		<link rel="icon" type="image/png" href="./images/favicon.ico"/>

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
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
	    <script src='./js/funcoes.auxiliares.js'></script>
		<script src="./js/conta/conta.js" type="text/javascript"></script>

		<script src="./js/jquery.maskMoney.min.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Conta</li>
			    <li class="active">${titulo}</li>
			</ul>
			
			<div class="row">
				<div class="col-md-8 col-md-offset-2">
					
					<div id="divAviso" name="divAviso" class="alert alert-danger" style="display:${display};">
						<strong><label id='aviso' name='aviso'/>${aviso}</strong>
					</div>
					
					<c:if test="${not empty sucesso}">
						<div class="alert alert-success alert-dismissible fade in">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<strong><c:out value="${sucesso}"/></strong>
						</div>
					</c:if>
				
					<form data-toggle="validator" role="form" action="ContaBO?acao=inserir" method="post" enctype="multipart/form-data" accept-charset="iso-8859-1,utf-8" onsubmit="return validaForm()">
						<input type="hidden" id="id" name="id" value="${conta.idConta}" />
						
						<fieldset>
							<legend class="text-left">${titulo} de Conta</legend>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label">Empresa</label><label class="text-danger">*</label>
									<select class="form-control input-sm" id="idEmpresa" name="idEmpresa" required onchange="listarCondominio()">
										<option value="" selected>Selecione...</option>
										<c:forEach var="empresa" items="${listEmpresa}">
				               		        <c:choose>
				                            	<c:when test="${empresa.idEmpresa eq conta.idEmpresa}">
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
							
							<div class="col-sm-6">
								<div class="form-group">	
									<label class="control-label">Local</label><label class="text-danger">*</label>
									<select class="form-control input-sm" id="idCondominio" name="idCondominio" required>
										<option value="" selected>Selecione...</option>
										<c:forEach var="condominio" items="${listCondominio}">
			                   		        <c:choose>
			                                  	<c:when test="${condominio.idCondominio == conta.idCondominio}">
			                                 		<option value="${condominio.idCondominio}" selected>${condominio.nome} - ${condominio.endereco} ${condominio.numero} ${condominio.compl}</option> 
			                                   	</c:when>
			                                   	<c:otherwise>
			                                   		<option value="${condominio.idCondominio}">${condominio.nome} - ${condominio.endereco} ${condominio.numero} ${condominio.compl}</option>
			                                   	</c:otherwise>
			                            	</c:choose>
				                     	</c:forEach>
									</select>					
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label">Data Leitura Atual</label><label class="text-danger">*</label>
				                    <div class='input-group date' id='dpDtLeituraAtual'>
					                    <input type='text' class="form-control input-sm" id='dtLeituraAtual' name='dtLeituraAtual' placeholder="dd/mm/aaaa" value="${conta.dtLeituraAtual}" required />
				            			<span class="input-group-addon">
											<span class="glyphicon glyphicon-calendar"></span>
										</span>
					                    <script type="text/javascript">
						                    $(function () {
					                            $('#dpDtLeituraAtual').datetimepicker({
					                            	format: 'DD/MM/YYYY'
					                            });
					                        });
					                    </script>
					                </div> 
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label">Data Leitura Anterior</label><label class="text-danger">*</label>
				                    <div class='input-group date' id='dpDtLeituraAnterior'>
					                    <input type='text' class="form-control input-sm" id='dtLeituraAnterior' name='dtLeituraAnterior' placeholder="dd/mm/aaaa" value="${conta.dtLeituraAnterior}" required />
				            			<span class="input-group-addon">
											<span class="glyphicon glyphicon-calendar"></span>
										</span>
					                    <script type="text/javascript">
						                    $(function () {
					                            $('#dpDtLeituraAnterior').datetimepicker({
					                            	format: 'DD/MM/YYYY'
					                            });
					                        });
					                    </script>
					                </div> 
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label">Valo Total (R$)</label><label class="text-danger">*</label>
									<fmt:setLocale value="pt-BR" />
									<input type="numeric" class="form-control" id="valor" name="valor" value="<fmt:formatNumber value="${conta.valor}" type="currency" currencySymbol=""/>" maxlength="19" required/>
			                        <script>
			                            $(function() {
			                                $("#valor").maskMoney({prefix:'R$ ', allowNegative: false, thousands:'.', decimal:',', affixesStay: false, precision:2});
			                            });
			                        </script>
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label">Connsumo Total (m³):</label><label class="text-danger">*</label>
									<input type="numeric" class="form-control" id="consumo" name="consumo" value="<fmt:formatNumber value="${conta.consumo}" type="currency" currencySymbol=""/>" maxlength="16" required/>
 									<script>
 			                            $(function() {
 			                                $("#consumo").maskMoney({allowNegative: false, thousands:'.', decimal:',', affixesStay: false, precision:3});
 			                            });
 			                        </script>
								</div>
							</div>
							
							<c:if test="${not empty conta.idConta}">
								<c:url value="ContaBO" var="link">
									<c:param name="acao" value="foto"/>
									<c:param name="idConta" value="${conta.idConta}"/>
								</c:url>
								
								<div class="col-sm-2">
									<div class="form-group">
										<a href="${link}" target="_blank">
											<button type="button" class="btn btn-warning"> 
												<i class="fa fa-bar-chart"></i> Visualizar Fotos 
											</button>
										</a>
									</div>
								</div>
							</c:if>
							
	                        <div class="col-sm-12">
								<div class="form-group">
									<label class="control-label">Foto da Conta</label>
									<div class="input-group input-file" name="Fichier1">
										<span class="input-group-btn">
							        		<button class="btn btn-choose btn-primary" type="button">Selecione</button>
							    		</span>
							    		<input type="text" class="form-control" accept=".jpg,.png,.bmp" placeholder="Selecione a imagem da conta..." />
							    		<span class="input-group-btn">
							       			 <button class="btn btn-warning btn-reset" type="button">Excluir</button>
							    		</span>
									</div>
								</div>
								<div id="divArquivo" name="divArquivo" class="alert alert-danger" style="display:none;">
									<strong><label id='feedback' name='feedback'/></strong>
								</div>
								<div id="feedback" style="color: red;"></div>
							</div>

							<div class="col-sm-12">
								<div class="form-group">
									<label class="control-label">Obsevações:</label>
									<textarea class="form-control" rows="3" name="obs" id="obs" style="resize:none;" onKeyPress="validaTamanho(this,150)">${empresa.obs}</textarea>
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
										<button type="submit" class="btn btn-primary" id="botao">${botao}</button>
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
