<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
    <head>
    	<title>WaterBridge</title>	
		<link rel="icon" type="image/png" href="./images/favicon.ico"/>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
        <meta charset="utf-8"/>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"></link>        
        <script src="http://malsup.github.io/jquery.blockUI.js"></script>        
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        
        <title>WaterBridge</title>
        <style>
            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
            }
            #map {
                height: 100%;
            }
            .collapse.in, .collapse{
                height: 320px;
                overflow-y: scroll;
            }
        </style>
    </head>
    <body>
    
    	<div class="row" style="text-align: center; padding: 5px; background-color: #156fc3;">			
			<div class="col-sm-2 text-center"><img class="img-responsive center-block" src="./images/logo_waterbridge_menu.png" alt=""></div>
			<div class="col-sm-2 text-center"></div>
            <div class="col-sm-4 text-center"><p class="text-muted"></p></div>
            <div class="col-sm-2 text-center"></div>
            <div class="col-sm-2 text-right"><img class="img-responsive center-block" src="./images/logo_desoltec_branco_menu.png" alt=""></div>            
		</div>
        <div id="map"></div>
        <div id='divscript' style='position: absolute; top: 50px; min-width: 50px; right: 10px; z-index: 1; width: 5%;' >
            <a href="#" id="hrfiltros"><span class="glyphicon glyphicon-fullscreen"></span></a>            
            <script>
                $('#divColaborador').animate({width: 'toggle'});
                $('#hrfiltros').click(function(){
                    $('#divColaborador').animate({width: 'toggle'});
                });
            </script>
        </div>    
			    
	    <div class="modal fade" id="modalPonto" role="dialog">
	        <div class="modal-dialog modal-lg">
	            <!-- Modal content-->
	            <div class="modal-content">
	                <div class="modal-header">
	                    <button type="button" class="close" data-dismiss="modal">&times;</button>
	                    <h4 class="modal-title">Alterar Acessório</h4>
	                </div>
	                <div class="modal-body">
	                    <p>
	                    	<div id='divAviso'></div>
	                        <form role="form" class="form-horizontal" action="#" method="post">
	                        	<div class="form-group">
	                        		<input type="hidden" class="form-control" id="idPonto" name="idPonto" value="">
	           			     	 	<div class="col-sm-4">
										<label class="control-label">Tipo</label><label class="text-danger">*</label>
										<select class="form-control" id="idPontoTp" name="idPontoTp"></select>
									</div>										
									<div class="col-sm-4">
										<label class="control-label">Latitude</label><label class="text-danger">*</label>
										<input type="text" class="form-control" id="coordX" name="coordX" value="" maxlength="20" required="">
									</div>
									<div class="col-sm-4">
										<label class="control-label">Longitude</label><label class="text-danger">*</label>
										<input type="text" class="form-control" id="coordY" name="coordY" value="" maxlength="20" required="">
									</div>									
								</div>
								<div class="form-group">
									<div class="col-sm-12">
										<label class="control-label">Descrição</label>
										<textarea class="form-control" rows="3" id="descricao" name="descricao"></textarea>
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
								
	                        </form>
	                    </p>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
	                    <button type="button" class="btn btn-danger" id="btExcluirPonto" onclick="excluirPonto()">Excluir</button>
        				<button type="button" class="btn btn-primary" id="btCadastrarAlterarPonto" onclick="alterarPonto()">Alterar</button>        				
	                </div>
	            </div>
	        </div>
	    </div>

        <div id="divAlertaNivel" class="map-cnt" style='display: none;'></div>  		
		<style>
			.map-alert {
			   position: absolute; 
			   left: 2%;
			   top: 90%;
			    
			}
		</style>
                
        <div id='divColaborador' style='background-color: #fff; position: absolute; top: 70px; min-width: 400px; right: 10px; z-index: 1; width: 20%;' >
            <div class="panel-group" id="accordion" style="margin: 0px;">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                          	<a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Painel de Exibição</a>
                       	</h4>                        
                    </div>
                    <div id="collapse1" class="panel-collapse collapse">
                        <div class="panel-body" style="padding: 5px;">                                                    
                        	<button type="button" class="btn btn-primary btn-sm" onclick="listarPontos(0)">
					          <span class="glyphicon glyphicon-refresh"></span> Atualizar Mapa
					        </button>
					        <button type="button" class="btn btn-danger btn-sm" onclick="clearMarkers()">
					          <span class="glyphicon glyphicon-erase"></span> Limpar Mapa
					        </button>
					        <button type="button" class="btn btn-success btn-sm" onclick="exibirModalCadastrarPonto()">
					          <span class="glyphicon glyphicon-plus"></span> Acessório
					        </button>
                        </div>
                        <div class="panel-body" style="padding: 5px;">      
                            <label>Setores</label><br/>                      
	                        <select class="js-example-basic-multiple" name="idEmpresa" id="idEmpresa" multiple="multiple" title="Selecione Setor">
	                            <c:forEach var="relPontoFiltroSetor" items="${listRelPontoFiltroSetor}">
	                                <option value="${relPontoFiltroSetor.idEmpresa}-${relPontoFiltroSetor.compl}">${relPontoFiltroSetor.compl}</option>
	                            </c:forEach>
	                        </select>	                        
	                        <br/><br/>
	                        <script>
	                            $(document).ready(function() {
	                                $('.js-example-basic-multiple').select2({
	                                    placeholder: "Selecione",
	                                    width: '100%'
	                                });
	                            });
	                        </script>
                        </div>
                        <div class="panel-body" style="padding: 5px;">
                            <label>Acessórios</label>
                        </div>
                        <div class="panel-body" style="padding: 5px;">
                            <input type="checkbox" id="acessorio1" name="acessorio1" value="1">
                            Descarga <a href="#"><img src='./images/ic_descarga.png' height="20"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">
                            <input type="checkbox" id="acessorio2" name="acessorio2" value="2">
                            Estação Bomba <a href="#"><img src='http://maps.google.com/mapfiles/ms/icons/blue-dot.png' height="20"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">
                            <input type="checkbox" id="acessorio3" name="acessorio3" value="3">
                            Estação Tratamento <a href="#"><img src='http://maps.google.com/mapfiles/ms/icons/green-dot.png' height="20"/></span></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">
                        	<input type="checkbox" id="acessorio4" name="acessorio4" value="4">
                            Gaveta <a href="#"><img src='http://maps.google.com/mapfiles/ms/icons/ltblue-dot.png' height="20"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                       
                            <input type="checkbox" id="acessorio5" name="acessorio5" value="5">
                            Outros <a href="#"><img src='http://maps.google.com/mapfiles/ms/icons/orange-dot.png' height="20"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                        
                            <input type="checkbox" id="acessorio6" name="acessorio6" value="6">
                            Poços <a href="#"><img src='./images/ic_poco.png' height="17"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">
                            <input type="checkbox" id="acessorio7" name="acessorio7" value="7">
                            Redutora Pressão <a href="#"><img src='http://maps.google.com/mapfiles/ms/icons/purple-dot.png' height="20"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">
                            <input type="checkbox" id="acessorio8" name="acessorio8" value="8">
                            Registro <a href="#"><img src='./images/ic_registro.png'/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">
                            <input type="checkbox" id="acessorio9" name="acessorio9" value="9">
                            Reservatório <a href="#"><img src='./images/ic_reservatorio.png' height="20"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">
                            <input type="checkbox" id="acessorio10" name="acessorio10" value="10">
                            Ventosa <a href="#"><img src='./images/ic_ventosa.png' height="20"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            <strong>Legenda</strong><br/>
                            - pressão crítica <img src='./images/ic_dispositivo_001.png' height="20"/><br/>
                            - pressão baixa <img src='./images/ic_dispositivo_002.png' height="20"/><br/>
                            - pressão aceitável <img src='./images/ic_dispositivo_003.png' height="20"/><br/>
                            - pressão normal <img src='./images/ic_dispositivo_004.png' height="20"/><br/>
                            - pressão alta (crítico) <img src='./images/ic_dispositivo_005.png' height="20"/>                          
                        </div>                                                                                                                                                                                        
                    </div>
                </div>                 
            </div>            
        </div>
        <div id="question" style="display:none; cursor: default"> 
			<h4>Tem certeza que deseja excluir o ponto?</h4> 			
			<button type="button" id="sim" class="btn btn-default">Sim</button>
			<button type="button" id="nao" class="btn btn-primary">Nao</button> 
		</div> 
        <script>
        
        	var timerAtualizaMarkers;
            var listRelPonto = ${listRelPonto};
            var listRelMapaConsumoPressao = ${listRelMapaConsumoPressao};
            var markers = [];
            var qtdeSetor1 = 0;
            var qtdeSetor2 = 0;
            var qtdeAbaixoMinSetor1 = 0;
            var qtdeAbaixoMinSetor2 = 0;
            
            var map;
            function initMap() {
                var myLatLng = {lat: -22.821545, lng: -47.224802};
                map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 12,
                    center: myLatLng
                });                
              	//EXIBE PONTOS
                for (i = 0; i < listRelPonto.length; i++) {                	
                    var relPonto = listRelPonto[i];        
                    addPontoDispositivo(map,relPonto);
                }
              	
                //EXIBE MEDIDORES E PRESSURE BRIDGE
                for (i = 0; i < listRelMapaConsumoPressao.length; i++) {                              
                    var relMapaConsumoPressao = listRelMapaConsumoPressao[i];                         
                    addPontoBridge(map,relMapaConsumoPressao);
                }
            }
            
            function addPoligon() {            	            	            	
            	if($("#idEmpresa").val() != null && $("#idEmpresa").val() != '') {
            		var listIdEmpresa = $("#idEmpresa").val();
                    for (i = 0; i < listIdEmpresa.length; i++) {                                        		                    	
                        var idEmpresa = listIdEmpresa[i];
                        var array = idEmpresa.split("-");                        
                        var src = buscarUrl(array);
                        if(src != '') {
                			var kmlLayer = new google.maps.KmlLayer(src, {                  
                            	suppressInfoWindows: true,
                              	preserveViewport: false,
                             	map: map
                            });	
                		}                        
                    }
            	}
            }
            
            function buscarUrl(array) {
            	var src = '';
        		if(array[1] == 'BOOSTER ACLIMACAO (AREA CURA)') {
        			src = 'http://waterbridge.com.br/kml/aclimacao.kml';		
        		}
        		else if(array[1] == 'BOOSTER ACLIMACAO (AREA CURA)') {
        			src = 'http://waterbridge.com.br/kml/aclimacao.kml';
        		}
				else if(array[1] == 'CALEGARI (AREA CURA)') {
					src = 'http://waterbridge.com.br/kml/calegari.kml';		
        		}
				else if(array[1] == 'CARLOTA') {
					src = 'http://waterbridge.com.br/kml/carlota.kml';		
				}
				else if(array[1] == 'CENTRO') {
					src = 'http://waterbridge.com.br/kml/centro.kml';		
				}
				else if(array[1] == 'CENTRO (FRANCESCHINI)') {
					src = 'http://waterbridge.com.br/kml/centro.kml';
				}
				else if(array[1] == 'CENTRO (PLANALTO)') {
					src = 'http://waterbridge.com.br/kml/centro.kml';
				}
				else if(array[1] == 'CENTRO (VECCON)') {
					src = 'http://waterbridge.com.br/kml/centro.kml';
				}
				else if(array[1] == 'CENTRO (VILA MENUZZO)') {
					src = 'http://waterbridge.com.br/kml/centro.kml';
				}
				else if(array[1] == 'JARDIM DULCE') {
					src = '';	
				}
				else if(array[1] == 'JOAO PAULO') {
					src = 'http://waterbridge.com.br/kml/joao_paulo.kml';		
				}
				else if(array[1] == 'MARIA ANTONIA') {
					src = 'http://waterbridge.com.br/kml/maria_antonia.kml';
				}
				else if(array[1] == 'MATAO PARTE ALTA') {
					src = 'http://waterbridge.com.br/kml/matao_parte_alta.kml';				
				}
				else if(array[1] == 'MATAO PARTE BAIXA') {
					src = 'http://waterbridge.com.br/kml/matao_parte_baixa.kml';		
				}
				else if(array[1] == 'NOVA VENEZA') {
					src = 'http://waterbridge.com.br/kml/nova_veneza.kml';	
				}
				else if(array[1] == 'NOVA VENEZA (APOIADO ETA II)') {
					src = '';
				}
				else if(array[1] == 'PARQUE DAS NACOES') {
					src = '';
				}
				else if(array[1] == 'PICERNO') {
					src = 'http://waterbridge.com.br/kml/picerno.kml';
				}
				else if(array[1] == 'POCOS (CRUZEIRO DO SUL)') {
					src = 'http://waterbridge.com.br/kml/pocos.kml';	
				}
				else if(array[1] == 'POCOS (DANTE MARMIROLLI)') {
					src = 'http://waterbridge.com.br/kml/pocos.kml';
				}
				else if(array[1] == 'POCOS (ESTRELA D ALVA)') {
					src = 'http://waterbridge.com.br/kml/pocos.kml';
				}
				else if(array[1] == 'POCOS (SAO BENTO)') {
					src = 'http://waterbridge.com.br/kml/pocos.kml';
				}
				else if(array[1] == 'RAVAGNANI') {
					src = '';
				}
				else if(array[1] == 'SANTA TEREZINHA') {
					src = '';
				}        		
        		return src;
            }
            
            function addPontoDesoltec(map) {

                var myLatLng = {lat: -22.7593783, lng: -47.331158500000015};
                
                var contentString =
                        '<div id="content">' +
                        '  <div id="siteNotice"></div>' +
                        '  <h4 id="firstHeading" class="firstHeading">Desoltec Engenharia</h4>' +
                        '  <div id="bodyContent">' +
                        //'    <img src="http://www.w3schools.com/images/w3schools_green.jpg" alt="W3Schools.com">'+
                        //'    <p><b>Uluru</b>, also referred to as <b>Ayers Rock</b>, is a large </p>'+
                        //'    <p>Attribution: Uluru, <a href="https://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">'+
                        //'    https://en.wikipedia.org/w/index.php?title=Uluru</a> </p>'+
                        //'    <a href="#" onclick="guardarCoordenadas(\'Escritório\', \'RB\', -23.614233, -46.662033); return false;">Rota</a></p>' +
                        '  </div>' +
                        '</div>';

                var infowindow = new google.maps.InfoWindow({
                    content: contentString
                });

                var image = './images/ic_desotec_mapa.png';
                var marker = new google.maps.Marker({
                    position: myLatLng,
                    map: map,
                    icon: image,
                    title: 'Desoltec Engenharia'
                });

                marker.addListener('click', function () {
                    infowindow.open(map, marker);
                });
                markers.push(marker);
            }
            
            function addPontoDispositivo(map, relPonto) {    
                
                var image;                
                if (Number(relPonto.idPontoTp) == 1) {                    
                	image = './images/ic_descarga.png';
                }
                else if (Number(relPonto.idPontoTp) == 2) {
                	image = 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png';
                }
                else if (Number(relPonto.idPontoTp) == 3) {
                	image = 'http://maps.google.com/mapfiles/ms/icons/green-dot.png';
                }
                else if (Number(relPonto.idPontoTp) == 4) {
                	image = 'http://maps.google.com/mapfiles/ms/icons/ltblue-dot.png';
                }
                else if (Number(relPonto.idPontoTp) == 5) {
                	image = 'http://maps.google.com/mapfiles/ms/icons/orange-dot.png';
                }
                else if (Number(relPonto.idPontoTp) == 6) {
                	image = './images/ic_poco.png';
                }
                else if (Number(relPonto.idPontoTp) == 7) {
                	image = 'http://maps.google.com/mapfiles/ms/icons/purple-dot.png';
                }
                else if (Number(relPonto.idPontoTp) == 8) {
                	image = './images/ic_registro.png';
                }
                else if (Number(relPonto.idPontoTp) == 9) {
                	image = './images/ic_reservatorio.png';
                }
                else if (Number(relPonto.idPontoTp) == 10) {
                	image = './images/ic_ventosa.png';
                }
                
                var myLatLng1 = {lat: Number(relPonto.coordX), lng: Number(relPonto.coordY)};
                var marker1 = new google.maps.Marker({
                    position: myLatLng1,
                    map: map,
                    icon: image,
                    title: String(''),
                    id: '' + relPonto.idPonto + ''
                });
                
                var texto = '';
                texto = 	
               		'    <p><b>Id: </b> ' + relPonto.idPonto + ' </p>' +
                    '    <p><b>Tipo: </b> ' + relPonto.descricaoPontoTp + '</p>' +
                    '    <p><b>Descricao: </b> ' + relPonto.descricaoPonto + '</p>' +
                    '    <a href="#" onclick="buscarPonto(' + relPonto.idPonto + '); return false;">Alterar</a></p>' +
                    '    <a href="#" onclick="deleteMarker(' + relPonto.idPonto + '); return false;">excluir marker</a></p>' ;
               
                var contentString1 =
                '<div id="content">' +
                '  <div id="siteNotice"></div>' +
                '  <div id="bodyContent">' ;                
                contentString1 += texto ;                
                contentString1 +=                
                '  </div>' +
                '</div>';

                var infowindow1 = new google.maps.InfoWindow({
                    content: contentString1
                });
                marker1.addListener('click', function () {
                    infowindow1.open(map, marker1);
                });
                markers.push(marker1);
            }
            
			function addPontoBridge(map, relMapaConsumoPressao) {

                var image = './images/ic_dispositivo_mapa.png';
               	if(relMapaConsumoPressao.idBridgeTp == 2 || relMapaConsumoPressao.idBridgeTp == 4) {
               		if(relMapaConsumoPressao.pressure < relMapaConsumoPressao.pressaoMinBaixa ) {
               			image = './images/ic_dispositivo_001.png';
               		}
               		else if(relMapaConsumoPressao.pressure > relMapaConsumoPressao.pressaoMaxAlta ) {
               			image = './images/ic_dispositivo_004.png';
               		}
					else if(relMapaConsumoPressao.pressure < relMapaConsumoPressao.pressaoMin ) {
						image = './images/ic_dispositivo_002.png';						
               		}
					else if(relMapaConsumoPressao.pressure > relMapaConsumoPressao.pressaoMax ) {
						image = './images/ic_dispositivo_004.png';
               		}
					else if(relMapaConsumoPressao.pressure >= relMapaConsumoPressao.pressaoMin
							|| relMapaConsumoPressao.pressure <= relMapaConsumoPressao.pressaoMax) {
						image = './images/ic_dispositivo_003.png';
               		}
               		
               		contarDispositivosSetor(relMapaConsumoPressao);
               	}
               	
                var myLatLng1 = {lat: Number(relMapaConsumoPressao.coordX), lng: Number(relMapaConsumoPressao.coordY)};
                var marker1 = new google.maps.Marker({
                    position: myLatLng1,
                    map: map,
                    icon: image,
                    title: String('')
                });

                var contentString1 =
                '<div id="content">' +
                '  <div id="siteNotice"></div>' +
                '  <div id="bodyContent">' ;                        
                contentString1 +=                	
                '    ' + relMapaConsumoPressao.device + '<br/>' + relMapaConsumoPressao.pressure + ' (MCA)<br/>' + relMapaConsumoPressao.dtInsert.substring(0, 6) + relMapaConsumoPressao.dtInsert.substring(8) + '<br/>' +
                '    ' + relMapaConsumoPressao.condominio + '<br/>' + relMapaConsumoPressao.endereco + ' ' + relMapaConsumoPressao.numero + '' + 
                '  </div>' +
                '</div>';

                var infowindow1 = new google.maps.InfoWindow({
                    content: contentString1
                });
                marker1.addListener('click', function () {
                    infowindow1.open(map, marker1);
                });
                markers.push(marker1);
            }

            function clearMarkers() {                
            	var myLatLng = {lat: -22.821545, lng: -47.224802};
                map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 12,
                    center: myLatLng
                });                
                //addPontoDesoltec(map);
                $("#divAlertaNivel").css("display","none");
            }            
            
            timerMarkers = setTimeout(function() {
       	 		listarPontos('0');
       		//}, 600000);
       	 	}, 10000);
            
       	 	function contarDispositivosSetor(relMapaConsumoPressao) {
	       	 	if(relMapaConsumoPressao.idBridgeTp == 2 || relMapaConsumoPressao.idBridgeTp == 4) {
	       	 		
		       	 	if(relMapaConsumoPressao.compl != null && (relMapaConsumoPressao.compl == 'BOOSTER ACLIMACAO (AREA CURA)' || relMapaConsumoPressao.compl == 'CALEGARI (AREA CURA)' || 
		       	 		relMapaConsumoPressao.compl == 'CALEGARI (JARDIM DULCE)' || relMapaConsumoPressao.compl == 'CALEGARI (PARQUE DAS NACOES)' ||
		       	 		relMapaConsumoPressao.compl == 'MARIA ANTONIA' || relMapaConsumoPressao.compl == 'MATAO PARTE ALTA' ||
		       	 		relMapaConsumoPressao.compl == 'MATAO PARTE BAIXA' || relMapaConsumoPressao.compl == 'NOVA VENEZA' || 
		       	 		relMapaConsumoPressao.compl == 'NOVA VENEZA (APOIADO ETA II)')){
		       	 		qtdeSetor2 = qtdeSetor2 + 1;
			       	 	if(relMapaConsumoPressao.pressure < relMapaConsumoPressao.pressaoMin) {
			       	 		qtdeAbaixoMinSetor2 = qtdeAbaixoMinSetor2 + 1;
				       	}
		       	 	}
		       	 	else if(relMapaConsumoPressao.compl != null && (relMapaConsumoPressao.compl == 'CARLOTA'|| relMapaConsumoPressao.compl == 'CENTRO' ||
		       	 			relMapaConsumoPressao.compl == 'CENTRO (FRANCESCHINI)' || relMapaConsumoPressao.compl == 'CENTRO (PLANALTO)' ||
		       	 			relMapaConsumoPressao.compl == 'CENTRO (RAVAGNANI)' || relMapaConsumoPressao.compl == 'CENTRO (SANTA TEREZINHA)' ||
		       	 			relMapaConsumoPressao.compl == 'CENTRO (VECCON)' || relMapaConsumoPressao.compl == 'CENTRO (VILA MENUZZO)' || 
		       	 			relMapaConsumoPressao.compl == 'JOAO PAULO' || relMapaConsumoPressao.compl == 'PICERNO' ||
		       	 			relMapaConsumoPressao.compl == 'POCOS (CRUZEIRO DO SUL)' || relMapaConsumoPressao.compl == 'POCOS (DANTE MARMIROLLI)' || 
		       	 			relMapaConsumoPressao.compl == 'POCOS (ESTRELA D ALVA)' || relMapaConsumoPressao.compl == 'POCOS (SAO BENTO)')){
		       	 		qtdeSetor1 = qtdeSetor1 + 1;
			       	 	if(relMapaConsumoPressao.pressure < relMapaConsumoPressao.pressaoMin) {
			       	 		qtdeAbaixoMinSetor1 = qtdeAbaixoMinSetor1 + 1;
				       	}
		       	 	}
	       	 	}	       	 		
       	 	}
       	 	
            function listarPontos(idPontoTp) {
            	
                qtdeSetor1 = 0;
                qtdeSetor2 = 0;
                qtdeAbaixoMinSetor1 = 0;
                qtdeAbaixoMinSetor2 = 0;
                
            	if(timerAtualizaMarkers != null) {
            		clearTimeout(timerAtualizaMarkers);
            	}
            	
            	var acessorios = '';
            	for (i = 1; i < 11; i++) {                		                      
            		if( $("#acessorio" + i).is(":checked") == true) {
            			acessorios += '&acessorio' + i + '=' + i;
            		}
                }     
            	
            	$.blockUI({ 
           	    	message: '<img src="./images/busy.gif" />',
           	    	css: { 
           	    		padding:        5,
           	    		left:           '45%', 
           	            width:          '10%', 
           	            border:         '1px solid #aaa'
           	        }         		
           	    });     	    
           	    $.ajax({
           	        url: 'PontoBO?acao=2' +
           	             '&idPontoTp=' + idPontoTp +
           	             '&idEmpresa=' + $("#idEmpresa").val() +
           	             acessorios,
           	        type: "POST",
           	        dataType: 'json',
           	        success: function(result) {
           	        	clearMarkers();           	                	        	
           	        	var listObject = result;
           	        	if(listObject != null && listObject.length > 0) {           	        	
           	        		var listRelPonto = listObject[0];
    	           	        for (i = 0; i < listRelPonto.length; i++) {                		                      
    	           	        	var relPonto = listRelPonto[i];        	                      
    	           	        	addPontoDispositivo(map,relPonto);
    	                    }       	           	     
    	           	        var listRelMapaConsumoPressao = listObject[1];    	           	        
    	                    for (i = 0; i < listRelMapaConsumoPressao.length; i++) {                              
    	                        var relMapaConsumoPressao = listRelMapaConsumoPressao[i];                         
    	                        addPontoBridge(map,relMapaConsumoPressao);
    	                    } 
    	                    addPoligon();
    	                    criarAlertaNivel();
           	        	}           	        	 
           	            $.unblockUI();
           	        },
           	        error : function(){           	
           	            $.unblockUI();
           	            alert('Falha ao listar pontos');
           	        }
           	    });
           	 	timerAtualizaMarkers = setTimeout(function() {
           	 		listarPontos('0');
           		}, 600000);
                //}, 60000);
            }

            function criarAlertaNivel() {
            	if(qtdeAbaixoMinSetor1 > 0 || qtdeAbaixoMinSetor2 > 0) {                  		
           
            		var percentualSetor1 = 0;
            		if(qtdeAbaixoMinSetor1 > 0) {
            			percentualSetor1 = (parseFloat(qtdeAbaixoMinSetor1) * 100) / parseFloat(qtdeSetor1);
            		}
            		var percentualSetor2 = 0;
					if(qtdeAbaixoMinSetor2 > 0) {
						percentualSetor2 = (parseFloat(qtdeAbaixoMinSetor2) * 100) / parseFloat(qtdeSetor2);
            		}
            		
            		var txt = 
                   	"        <div id='divAlertaNivel' class='map-cnt'>" +  
       	  		    "            <div class='alert alert-danger map-alert alert-dismissible fade in' style='color: black;'>" + 
       	  		    "                <a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
       	     		"                <strong>ETA 1</strong> - " + qtdeAbaixoMinSetor1 + " / " + qtdeSetor1 + " estão com pressão baixa (" + percentualSetor1.toFixed(2) + "%)</br>" +
       	     		"                <strong>ETA 2</strong> - " + qtdeAbaixoMinSetor2 + " / " + qtdeSetor2 + " estão com pressão baixa (" + percentualSetor2.toFixed(2) + "%)" +
       	  		    "            </div>" +
       		        "        </div> ";
                   	$("#divAlertaNivel").html(txt);
                   	$("#divAlertaNivel").css("display","block");
            	}
            	else {
            		$("#divAlertaNivel").css("display","none");
            	}
            }
            
            function buscarPonto(idPonto) {
            	resetModal();
            	$.blockUI({ 
	       	    	message: '<img src="./images/busy.gif" />',
	       	    	css: { 
	       	    		padding:        5,
	       	    		left:           '45%', 
	       	            width:          '10%', 
	       	            border:         '1px solid #aaa'
	       	        }         		
	       	    });          	    
	       	    $.ajax({
	       	        url: 'PontoBO?acao=3' +
	       	             '&idPonto=' + idPonto
	       	        ,
	       	        type: "POST",
	       	        dataType: 'json',
	       	        success: function(result) {	       	        	
	       	        	var listObject = result;	       	        	
	       	        	if(listObject.length != null) {	
	       	        		
	       	        		var relPonto = listObject[1];
	       	        		$("#coordX").val(relPonto.coordX);
	       	        		$("#coordY").val(relPonto.coordY);
	       	        		$("#descricao").val(relPonto.descricaoPonto);	       	        		
	       	        		$("#idPonto").val(idPonto);
	       	        		
	       	        		var listPontoTp = listObject[0];
	       	        		$('#idPontoTp').append('<option value="0">Selecione</option>');
	       	        		for (i = 0; i < listPontoTp.length; i++) {
	       	        			var pontoTp = listPontoTp[i];
	       	        			if(Number(relPonto.idPontoTp) == Number(pontoTp.idPontoTp)) {
	       	        				$('#idPontoTp').append('<option value="' + pontoTp.idPontoTp + '" selected="selected">' + pontoTp.descricao + '</option>');	
	       	        			}
	       	        			else {
	       	        				$('#idPontoTp').append('<option value="' + pontoTp.idPontoTp + '">' + pontoTp.descricao + '</option>');
	       	        			}
	       	        		}	       	        		
	       	        	} 	       	       
	       	        	$("#btCadastrarAlterarPonto").html("Alterar");
	       	        	$("#modalPonto").find('.modal-title').text("Alterar Acessório")
	                    $("#modalPonto").modal();	           	        
	       	            $.unblockUI();
	       	        },
	       	        error : function(){           	
	       	            $.unblockUI();
	       	            alert('Falha ao buscar pontos');
	       	        }
	       	    });        
            } 
            
            function alterarPonto() {
            	
	       		document.getElementById("idPontoTp").style.removeProperty('border');
	       		document.getElementById("coordX").style.removeProperty('border');
	       		document.getElementById("coordY").style.removeProperty('border');
	       	    
	       	    if($('#idPontoTp').val() == '0') {
	       	    	$('#idPontoTp').css('border-color', '#e52213');
	       	    	return false;
	       	    }
	       	    else if($('#coordX').val() == '') {
	       	    	$('#coordX').css('border-color', '#e52213');
	       	    	return false;
	       	    }	       	    
	       	 	else if($('#coordY').val() == '') {
	       	 		$('#coordY').css('border-color', '#e52213');
	       	 		return false;
	       	    }
	       	    
            	$.blockUI({ 
	       	    	message: '<img src="./images/busy.gif" />',
	       	    	css: { 
	       	    		padding:        5,
	       	    		left:           '45%', 
	       	            width:          '10%', 
	       	            border:         '1px solid #aaa'
	       	        }         		
	       	    }); 
	       	 	
	       	    $.ajax({
	       	        url: 'PontoBO?acao=4' +
	       	             '&idPonto=' + $('#idPonto').val() +
	       	             '&idPontoTp=' + $('#idPontoTp').val() +
			       	     '&coordX=' + $('#coordX').val() +
			       	     '&coordY=' + $('#coordY').val() +
			       	     '&descricao=' + $('#descricao').val()
	       	        ,
	       	        type: "POST",
	       	        dataType: 'json',
	       	        success: function(result) {	       	        	
	       	        	if(result == 'ok' && $('#idPonto').val() == '') {
	       	        		$("#divAviso").html(	       	        		
           	        			'<div class="alert alert-success">' +
    	       	        		  '<strong>Cadastro realizado com sucesso!</strong>' +
    	       	        		'</div>'
        	        		);	
	       	        	}
	       	        	else if(result == 'ok' && $('#idPonto').val() != '') {
	       	        		$("#divAviso").html(	       	        		
           	        			'<div class="alert alert-success">' +
    	       	        		  '<strong>Alteração realizada com sucesso!</strong>' +
    	       	        		'</div>'
        	        		);	
	       	        	}
	       	        	else {
	       	        		$("#divAviso").html(	       	        		
           	        			'<div class="alert alert-danger">' +
    	       	        		  '<strong>Ocorreu falha na tentativa de alteracao!</strong>' +
    	       	        		'</div>'
        	        		);
	       	        	}
	       	        	listarPontos("0");
	       	       		$.unblockUI();
	       	        },
	       	        error : function(){           	
	       	            $.unblockUI();
	       	        }
	       	    });            	
            }
            
            function exibirModalCadastrarPonto() {
            	resetModal();
            	$.blockUI({ 
	       	    	message: '<img src="./images/busy.gif" />',
	       	    	css: { 
	       	    		padding:        5,
	       	    		left:           '45%', 
	       	            width:          '10%', 
	       	            border:         '1px solid #aaa'
	       	        }         		
	       	    });          	    
	       	    $.ajax({
	       	        url: 'PontoBO?acao=5',
	       	        type: "POST",
	       	        dataType: 'json',
	       	        success: function(result) {	       	        	
	       	        	var listPontoTp = result;   	        		
       	        		$('#idPontoTp').append('<option value="0" selected="selected">Selecione</option>');
       	        		for (i = 0; i < listPontoTp.length; i++) {
       	        			var pontoTp = listPontoTp[i];
       	        			$('#idPontoTp').append('<option value="' + pontoTp.idPontoTp + '">' + pontoTp.descricao + '</option>');       	        			
       	        		}
       	        		$("#btCadastrarAlterarPonto").html("Cadastrar");
       	        		$("#modalPonto").find('.modal-title').text("Cadastrar Acessório")
	                    $("#modalPonto").modal();	           	        
	       	            $.unblockUI();
	       	        },
	       	        error : function(){           	
	       	            $.unblockUI();
	       	            alert('Falha ao buscar pontos');
	       	        }
	       	    });     
            }
            
			function excluirPonto() {				
				$('#modalPonto').modal('toggle'); 
            	$.blockUI({ message: $('#question'), css: { width: '400px' } }); 		        
                $('#sim').click(function() { 
                    $.blockUI({ message: "<h4>Excluindo ponto...</h4>" });          
    	       	    $.ajax({
    	       	        url: 'PontoBO?acao=6' +
    	       	             '&idPonto=' + $('#idPonto').val(),
    	       	        type: "POST",
    	       	        dataType: 'json',
    	       	        success: function(result) {	       	        	
    	       	        	if(result == 'ok' && $('#idPonto').val() == '') {
    	       	        		$("#divAviso").html(	       	        		
               	        			'<div class="alert alert-success">' +
        	       	        		  '<strong>Exclusao realizada com sucesso!</strong>' +
        	       	        		'</div>'
            	        		);	
    	       	        	}
    	       	        	else {
    	       	        		$("#divAviso").html(	       	        		
               	        			'<div class="alert alert-danger">' +
        	       	        		  '<strong>Ocorreu falha na tentativa de exclusao!</strong>' +
        	       	        		'</div>'
            	        		);
    	       	        	}
    	       	        	listarPontos("0");
    	       	       		$.unblockUI();
    	       	        },
    	       	        error : function(){           	
    	       	            $.unblockUI();
    	       	        }
    	       	    });    	       	 	
                });          
                $('#nao').click(function() { 
                    $.unblockUI(); 
                    return false; 
                });                
            }
                        
			function deleteMarker(id) {				
		        //Find and remove the marker from the Array		     
		        for (var i = 0; i < markers.length; i++) {
		            if (markers[i].id == id) {
		                //Remove the marker from Map                  
		                markers[i].setMap(null);		 
		                //Remove the marker from array.
		                markers.splice(i, 1);
		                return;
		            }
		        }
		    };
			
            function resetModal() {         
            	$('#divAviso').html('');
            	$('#idPonto').val("");
  	          	$('#idPontoTp').find('option').remove();
	       	    $('#coordX').val("");
	       	    $('#coordY').val("");
	       	    $('#descricao').val("");
            }
            
            function buscarPorIdEmpresa() {            	
            	alert('idEmpresa ' + $('#idEmpresa').val())
            }
        </script>

        <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCQYeBkyboWkHixtUDY9NZ_crkhBDa8eFQ&signed_in=true&callback=initMap"></script>
    </body>
</html>
