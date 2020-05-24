<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
    <head>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
        <meta charset="utf-8"/>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"></link>
        
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
        <div id='divscript' style='background-color: #fff; position: absolute; top: 50px; min-width: 50px; right: 10px; z-index: 1; width: 5%;' >
            <a href="#" id="hrfiltros"><span class="label label-primary">F I L T R O S</span></a>
            <script>
                $('#divColaborador').animate({width: 'toggle'});
                $('#hrfiltros').click(function(){
                    $('#divColaborador').animate({width: 'toggle'});
                });
            </script>
        </div>    
        <div id='divColaborador' style='background-color: #fff; position: absolute; top: 70px; min-width: 400px; right: 10px; z-index: 1; width: 20%;' >
            <div class="panel-group" id="accordion" style="margin: 0px;">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                          <a data-toggle="collapse" data-parent="#accordion" href="#collapse0">Filtros</a>
                        </h4>
                    </div>
                    <div id="collapse0" class="panel-collapse collapse">
                        <div class="panel-body" style="padding: 5px;">                            
                            Descarga <a href="#" onclick="buscarPontos(1);return false;"><span class='glyphicon glyphicon-map-marker text-danger' style="color:#6991fd"></span></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Estacao Bomba <a href="#" onclick="buscarPontos(2);return false;"><span class='glyphicon glyphicon-map-marker text-danger' style="color:#6991fd"></span></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Estacao Tratamento <a href="#" onclick="buscarPontos(3);return false;"><span class='glyphicon glyphicon-map-marker text-danger' style="color:#6991fd"></span></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Gaveta <a href="#" onclick="buscarPontos(4);return false;"><span class='glyphicon glyphicon-map-marker text-danger' style="color:#6991fd"></span></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Outros <a href="#" onclick="buscarPontos(5);return false;"><span class='glyphicon glyphicon-map-marker text-danger' style="color:#6991fd"></span></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Pocos <a href="#" onclick="buscarPontos(6);return false;"><span class='glyphicon glyphicon-map-marker text-danger' style="color:#6991fd"></span></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Redutora Pressao <a href="#" onclick="buscarPontos(7);return false;"><span class='glyphicon glyphicon-map-marker text-danger' style="color:#6991fd"></span></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Registro <a href="#" onclick="buscarPontos(8);return false;"><span class='glyphicon glyphicon-map-marker text-danger' style="color:#6991fd"></span></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Reservatorio <a href="#" onclick="buscarPontos(9);return false;"><span class='glyphicon glyphicon-map-marker text-danger' style="color:#6991fd"></span></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Ventosa <a href="#" onclick="buscarPontos(10);return false;"><span class='glyphicon glyphicon-map-marker text-danger' style="color:#6991fd"></span></a>
                        </div>                                                                                                                                                                                        
                    </div>
                </div>
            </div>            
        </div>
        <script>
            
            var listRelPonto = ${listRelPonto};            
            var map;
            function initMap() {
                var myLatLng = {lat: -22.901452, lng: -47.066742};
                map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 11,
                    center: myLatLng
                });                
              	//EXIBE PONTOS
                for (i = 0; i < listRelPonto.length; i++) {                	
                    var relPonto = listRelPonto[i];        
                    addPontoDispositivo(map,relPonto);
                }
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
                    title: String('')
                });
                
                var texto = '';
                texto = 	
               		'    <p><b>Id: </b> ' + relPonto.idPonto + ' </p>' +
                    '    <p><b>Tipo: </b> ' + relPonto.descricaoPontoTp + '</p>' +
                    '    <p><b>Descricao: </b> ' + relPonto.descricaoPonto + '</p>' ;
                /* if(relMapaConsumoPressao.idBridgeTp == 1) {
                	texto = 
                	'    <p><b>Tipo Medidor: </b> AGUA / PRESSAO </p>' +	
               		'    <p><b>Bridge: </b> ' + relMapaConsumoPressao.device + ' <b>Numero Medidor: </b> ' + relMapaConsumoPressao.numeroMedidor + ' </p>' +
                    '    <p><b>Pressao (MCA): </b> ' + relMapaConsumoPressao.pressure + '</p>' +
                    '    <p><b>Volume (M3): </b> ' + relMapaConsumoPressao.volume + '</p>' ;	
                }
                else if(relMapaConsumoPressao.idBridgeTp == 2 || relMapaConsumoPressao.idBridgeTp == 4) {
                	texto = 
                 	'    <p><b>Tipo Medidor: </b> PRESSAO </p>' +	
               		'    <p><b>Bridge: </b> ' + relMapaConsumoPressao.device + ' </p>' +
                    '    <p><b>Pressao (MCA): </b> ' + relMapaConsumoPressao.pressure + '</p>' ;
                }
                else if(relMapaConsumoPressao.idBridgeTp == 3) {
                	texto = 
                   	'    <p><b>Tipo Medidor: </b> AGUA </p>' +	
              		'    <p><b>Numero Medidor: </b> ' + relMapaConsumoPressao.numeroMedidor + ' </p>' +
                    '    <p><b>Volume (M3): </b> ' + relMapaConsumoPressao.volume + '</p>' ;
                } */

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
            }

            function clearMarkers() {                
                var myLatLng = {lat: -22.901452, lng: -47.066742};
                map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 10,
                    center: myLatLng
                });                
                //addPontoDesoltec(map);
            }            
            
            function buscarPontos(idPontoTp) {                	
           	   /*  $.blockUI({ 
           	    	message: '<img src="./images/busy.gif" />',
           	    	css: { 
           	    		padding:        5,
           	    		left:           '45%', 
           	            width:          '10%', 
           	            border:         '1px solid #aaa'
           	        }         		
           	    });  */           	    
           	    $.ajax({
           	        url: 'PontoBO?acao=2' +
           	             '&idPontoTp=' + idPontoTp
           	        ,
           	        type: "POST",
           	        dataType: 'json',
           	        success: function(result) {
           	        	clearMarkers();
           	        	var listRelPonto = result;
	           	        for (i = 0; i < listRelPonto.length; i++) {                		                      
	           	        	var relPonto = listRelPonto[i];        	                      
	           	        	addPontoDispositivo(map,relPonto);
	                    }           	        	            
           	            $.unblockUI();
           	        },
           	        error : function(){           	
           	            //$.unblockUI();
           	            alert('Falha ao buscar pontos');
           	        }
           	    });                	
            }
        </script>

        <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCQYeBkyboWkHixtUDY9NZ_crkhBDa8eFQ&signed_in=true&callback=initMap"></script>
    </body>
</html>
