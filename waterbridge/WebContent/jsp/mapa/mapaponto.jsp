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
        <script src="http://malsup.github.io/jquery.blockUI.js"></script>
        
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
        
        <div id='divColaborador' style='background-color: #fff; position: absolute; top: 70px; min-width: 400px; right: 10px; z-index: 1; width: 20%;' >
            <div class="panel-group" id="accordion" style="margin: 0px;">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                          	<a data-toggle="collapse" data-parent="#accordion" href="#collapse0">Acessórios</a>
                       	</h4>                        
                    </div>
                    <div id="collapse0" class="panel-collapse collapse">
                    	<div class="panel-body" style="padding: 5px;">                            
                            Incluir <a href="#" onclick="exibirModalCadastrarPonto();return false;"><span class='glyphicon glyphicon-plus' style="color:#6991fd"></span></a>
                        </div>                    
                    	<div class="panel-body" style="padding: 5px;">                            
                            Exibir Todos <a href="#" onclick="listarPontos(0);return false;"><span class='glyphicon glyphicon-asterisk text-danger' style="color:#6991fd"></span></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Descarga <a href="#" onclick="listarPontos(1);return false;"><img src='./images/ic_descarga.png' height="20"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Estacao Bomba <a href="#" onclick="listarPontos(2);return false;"><img src='http://maps.google.com/mapfiles/ms/icons/blue-dot.png' height="20"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Estacao Tratamento <a href="#" onclick="listarPontos(3);return false;"><img src='http://maps.google.com/mapfiles/ms/icons/green-dot.png' height="20"/></span></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Gaveta <a href="#" onclick="listarPontos(4);return false;"><img src='http://maps.google.com/mapfiles/ms/icons/ltblue-dot.png' height="20"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Outros <a href="#" onclick="listarPontos(5);return false;"><img src='http://maps.google.com/mapfiles/ms/icons/orange-dot.png' height="20"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Pocos <a href="#" onclick="listarPontos(6);return false;"><img src='./images/ic_poco.png' height="17"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Redutora Pressao <a href="#" onclick="listarPontos(7);return false;"><img src='http://maps.google.com/mapfiles/ms/icons/purple-dot.png' height="20"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Registro <a href="#" onclick="listarPontos(8);return false;"><img src='./images/ic_registro.png'/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Reservatorio <a href="#" onclick="listarPontos(9);return false;"><img src='./images/ic_reservatorio.png' height="20"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            Ventosa <a href="#" onclick="listarPontos(10);return false;"><img src='./images/ic_ventosa.png' height="20"/></a>
                        </div>
                        <div class="panel-body" style="padding: 5px;">                            
                            <strong>Legenda</strong><br/>
                            - limite crítico <img src='./images/ic_dispositivo_mapa_danger.png' height="20"/><br/>
                            - limite intermediário <img src='./images/ic_dispositivo_mapa_warning.png' height="20"/><br/>
                            - normal <img src='./images/ic_dispositivo_mapa.png' height="20"/>                          
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
        
            var listRelPonto = ${listRelPonto};
            var listRelMapaConsumoPressao = ${listRelMapaConsumoPressao};
            
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
                    '    <p><b>Descricao: </b> ' + relPonto.descricaoPonto + '</p>' +
                    '    <a href="#" onclick="buscarPonto(' + relPonto.idPonto + '); return false;">Alterar</a></p>' ;
                
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
            
			function addPontoBridge(map, relMapaConsumoPressao) {    
                
                var image = './images/ic_dispositivo_mapa.png';
               	if(relMapaConsumoPressao.idBridgeTp == 2 || relMapaConsumoPressao.idBridgeTp == 4) {
               		if(relMapaConsumoPressao.pressure < relMapaConsumoPressao.pressaoMinBaixa ) {
               			image = './images/ic_dispositivo_mapa_danger.png';		
               		}
               		else if(relMapaConsumoPressao.pressure > relMapaConsumoPressao.pressaoMaxAlta ) {
               			image = './images/ic_dispositivo_mapa_danger.png';
               		}
					else if(relMapaConsumoPressao.pressure < relMapaConsumoPressao.pressaoMin ) {
						image = './images/ic_dispositivo_mapa_warning.png';
               		}
					else if(relMapaConsumoPressao.pressure > relMapaConsumoPressao.pressaoMax ) {
						image = './images/ic_dispositivo_mapa_warning.png';
               		}
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
                '    <p>' + relMapaConsumoPressao.device + '<br/><b>(MCA): </b>' + relMapaConsumoPressao.pressure + '<br/>' + relMapaConsumoPressao.dtInsert + '</p>' +
                '  </div>' +
                '</div>';

                var infowindow1 = new google.maps.InfoWindow({
                    content: contentString1
                });
                marker1.addListener('click', function () {
                    infowindow1.open(map, marker1);
                });
                infowindow1.open(map,marker1);
            }

            function clearMarkers() {                
            	var myLatLng = {lat: -22.821545, lng: -47.224802};
                map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 12,
                    center: myLatLng
                });                
                //addPontoDesoltec(map);
            }            
            
            function listarPontos(idPontoTp) {                	           	    
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
           	             '&idPontoTp=' + idPontoTp,
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
    	           	        var relMapaConsumoPressao = listRelMapaConsumoPressao[1];
    	                    for (i = 0; i < listRelMapaConsumoPressao.length; i++) {                              
    	                        var relMapaConsumoPressao = listRelMapaConsumoPressao[i];                         
    	                        addPontoBridge(map,relMapaConsumoPressao);
    	                    } 
           	        	}           	        	 
           	            $.unblockUI();
           	        },
           	        error : function(){           	
           	            $.unblockUI();
           	            alert('Falha ao listar pontos');
           	        }
           	    });                	
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
                        
            function resetModal() {         
            	$('#divAviso').html('');
            	$('#idPonto').val("");
  	          	$('#idPontoTp').find('option').remove();
	       	    $('#coordX').val("");
	       	    $('#coordY').val("");
	       	    $('#descricao').val("");
            }
        </script>

        <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCQYeBkyboWkHixtUDY9NZ_crkhBDa8eFQ&signed_in=true&callback=initMap"></script>
    </body>
</html>
