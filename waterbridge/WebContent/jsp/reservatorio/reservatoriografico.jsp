<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>Bootstrap Example</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/highcharts-3d.js"></script>
	<script src="https://code.highcharts.com/modules/data.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>

</head>
<body>
	<div class="container-fluid">
		<div class="row" style="text-align: center; padding: 5px; background-color: #156fc3;">			
			<div class="col-sm-2 text-center"><img class="img-responsive center-block" src="./images/logo_waterbridge_menu.png" alt=""></div>
			<div class="col-sm-2 text-center"></div>
            <div class="col-sm-4 text-center"><p class="text-muted"></p></div>
            <div class="col-sm-2 text-center"></div>
            <div class="col-sm-2 text-right"><img class="img-responsive center-block" src="./images/logo_desoltec_branco_menu.png" alt=""></div>            
		</div>
		<div class="row" style="text-align: center; padding-top: 5px;">			
            <div class="col-sm-12 text-center"><p class="text-muted"><label style="font-size: 14pt;">Reservatórios</label></p></div>            
		</div>		
		<div class="row" id="divReservatorio">
			<c:forEach var = "i" begin = "1" end = "48">
		        <div class="col-sm-1" style="padding: 3px;">		        			    
	            	<div id="container${i}"></div>
	            	<div id="titulo${i}" style="width: 100%; height: 30px; text-align: center;"></div>
	            </div> 
	      	</c:forEach>
				
		</div>
	</div>
	
	<script type="text/javascript">
		function atualizarReservatorios() {
			
 			$('[data-toggle="popover"]').popover('hide');

			$.ajax({
		        url: 'ReservatorioBO?acao=2'
		        ,
		        type: "POST",
		        dataType: 'json',
		        success: function(result) {

		            var listRelPressaoLast = result;
		            
		            if(listRelPressaoLast != null && listRelPressaoLast.length > 0) {
			
		            	var cont = 1;
	            		for(i = 0; i < listRelPressaoLast.length; i++) {
	            					                	
		                	var relPressaoLast = listRelPressaoLast[i];
		                	
		                	$('#titulo' + cont).html('<label style="font-size: 8pt;">' + relPressaoLast.condominio + '</label>');
		                	
		                	var title = 
	            			'Local: ' + relPressaoLast.condominio + '<br>' +
	            			'Hora: ' + relPressaoLast.dtInsert + '<br>'
	            			;
	            			
	            			var data = relPressaoLast.pressao;			
	            			var altura = 140;
	            			var condonimio = relPressaoLast.condominio;
	            			var deviceNum = relPressaoLast.deviceNum;
	            			
	            			var pressao = relPressaoLast.pressao;	
	            			var pressaoMinBaixa = relPressaoLast.pressaoMinBaixa;
	            			var pressaoMin = relPressaoLast.pressaoMin;
	            			var pressaoMax = relPressaoLast.pressaoMax;
	            			var pressaoMaxAlta = relPressaoLast.pressaoMaxAlta;
	            			
	            			var pressaoPerc = pressao * 100 / pressaoMaxAlta;	
	            			var pressaoMinBaixaPerc = pressaoMinBaixa * 100 / pressaoMaxAlta;
	            			var pressaoMinPerc = pressaoMin * 100 / pressaoMaxAlta;
	            			var pressaoMaxPerc = pressaoMax * 100 / pressaoMaxAlta;
	            			var pressaoMaxAltaPerc = pressaoMaxAlta * 100 / pressaoMaxAlta;
	            			
	            			Highcharts.chart('container' + cont, {
	            				chart : {
	            					type : 'column',
	            					height : altura,
	            					options3d : {
	            						enabled : true,
	            						alpha : 0,
	            						beta : 30,
	            						depth : 30
	            					}
	            				},
	            				title : {
	            					text : ''
	            				},
	            				subtitle : {
	            					useHTML:true,		            
	            		            //text: '<div data-toggle="popover" title="" data-trigger="hover" data-content="' + title + '" data-original-title="" data-html="true">' + deviceNum + '</div>'
	            					text: ''
	            				},
	            				legend : {
	            					enabled : false
	            				},
	            				credits : {
	            					enabled : false
	            				},

	            				plotOptions : {
	            					bar : {
	            						colorByPoint : true
	            					},
	            					series : {
	            						zones : [ {
	            							color : '#FF5722',
	            							value : 0
	            						}, {
	            							color : '#FF5722',
	            							value : pressaoMinBaixaPerc
	            						}, {
	            							color : '#FF9800',
	            							value : pressaoMinPerc
	            						}, {
	            							color : '#4CAF50',
	            							value : pressaoMaxPerc
	            						}, {
	            							color : '#FF9800',
	            							value : pressaoMaxAltaPerc
	            						}, {
	            							color : '#FF5722',
	            							value : Number.MAX_VALUE
	            						} ],
	            						dataLabels : {
	            							enabled : true,
	            							format : '{point.y:.3f}%'
	            						}
	            					}
	            				},
	            				tooltip: { enabled: false },
//	             				tooltip : {
//	             					pointFormat: '<tr><td style="padding:0"><b>' + pressao + ' MCA</b></td></tr>',					
//	             					valueDecimals : 3,
//	             					outside: true
//	             				},
	            				xAxis : {
	            					type : 'category',
	            					categories : [ '' ],
	            					labels : {
	            						style : {
	            							fontSize : '10px'
	            						}
	            					}
	            				},
	            				yAxis : {
	            					labels: {
	            			            enabled: false
	            			        },			        
	            					max : 100,
	            					title : false,
	            					plotBands : [ {
	            						from : pressaoMinBaixaPerc,
	            						to : pressaoMinPerc,
	            						color : '#FFEBEE'
	            					}, {
	            						from : pressaoMinPerc,
	            						to : pressaoMaxPerc,
	            						color : '#cce6ff'
	            					}, {
	            						from : pressaoMaxPerc,
	            						to : pressaoMaxAltaPerc,
	            						color : "#FFEBEE"
	            					} ]
	            				},
	            				series : [ {
	            					//name: 'Sales',
	            					dataLabels: [{
	            			            format: ''+ pressao + ' MCA'
	            			        }],
	            					data : [ pressaoPerc ]
	            				} ],
	            				exporting : {
	            					enabled : false
	            				}
	            			});			
	            			
	            			$('[data-toggle="popover"]').popover({
	            			    container: 'body'
	            			});

		                	cont = cont + 1;
		                }
		            }		        
		        },
		        error : function() {		
		            alert('erro');
		            window.location = "http://www.waterbridge.com.br/";
		        }
		    });
			setTimeout(function() {
				atualizarReservatorios();
			}, 5000);
		}

// 		setTimeout(function() {
// 			atualizarReservatorios();
// 		}, 1000);
		atualizarReservatorios();
	</script>
<!-- 
	<script>

		function carregarDados() {
			
			for (i = 1; i < 49; i++) {

				var data = Math.floor((Math.random() * 100) + 1);
				var altura = 250;
				if(i > 15) {
					altura = 200;
				}

				Highcharts.chart('container' + i, {
					chart : {
						type : 'column',
						height : 250,
						options3d : {
							enabled : true,
							alpha : 0,
							beta : 30,
							depth : 30
						}
					},
					title : {
						text : ''
					},
					subtitle : {
						text : '' + i 
					},
					legend : {
						enabled : false
					},
					credits : {
						enabled : false
					},

					plotOptions : {
						bar : {
							colorByPoint : true
						},
						series : {
							zones : [ {
								color : '#F44336',
								value : 0
							}, {
								color : '#FF5722',
								value : 5
							}, {
								color : '#FF9800',
								value : 10
							}, {
								color : '#FFC107',
								value : 15
							}, {
								color : '#FFEB3B',
								value : 30
							}, {
								color : '#CDDC39',
								value : 40
							}, {
								color : '#8BC34A',
								value : 45
							}, {
								color : '#4CAF50',
								value : 50
							}, {
								color : '#8BC34A',
								value : 55
							}, {
								color : '#CDDC39',
								value : 60
							}, {
								color : '#FFEB3B',
								value : 65
							}, {
								color : '#FFC107',
								value : 80
							}, {
								color : '#FF9800',
								value : 85
							}, {
								color : '#FF5722',
								value : 90
							}, {
								color : '#FF5722',
								value : Number.MAX_VALUE
							} ],
							dataLabels : {
								enabled : true,
								format : '{point.y:.0f}%'
							}
						}
					},
					tooltip : {
						valueDecimals : 1,
						valueSuffix : '%'
					},
					xAxis : {
						type : 'category',
						categories : [ '' ],
						labels : {
							style : {
								fontSize : '10px'
							}
						}
					},
					yAxis : {
						max : 100,
						title : false,
						plotBands : [ {
							from : 0,
							to : 30,
							color : '#E8F5E9'
						}, {
							from : 30,
							to : 70,
							color : '#FFFDE7'
						}, {
							from : 70,
							to : 100,
							color : "#FFEBEE"
						} ]
					},
					series : [ {
						//name: 'Sales',
						data : [ data ]
					} ],
					exporting : {
						enabled : false
					}
				});

			}
			setTimeout(function() {
				carregarDados();
			}, 5000);
		}
		setTimeout(function() {
			carregarDados();
		}, 1000);
				
	</script>
-->
</body>
</html>
