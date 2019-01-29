
function listarCondominio() {
	
    var idEmpresa = document.getElementById('idEmpresa');

    if(idEmpresa.value == '') {
    	
    	$('#idCondominio option').remove();
        $('#idCondominio').append('<option value="" selected="selected">Selecione...</option>');
    }
    else {
    	
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
	        url: 'ConsumoCondominioBO?acao=2' +
	             '&idEmpresa=' + idEmpresa.value
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	
	        	$('#idCondominio option').remove();
                //$('#idCondominio').append('<option value="" selected="selected">Selecione...</option>');
	            var listCondominio = result;
	            if(listCondominio != null && listCondominio.length > 0) {
	                for(i = 0; i < listCondominio.length; i++) {
	                	var condominio = listCondominio[i];
	                	var situacao = '';
	                	var color = ''
	                	if(condominio.situacao == 'I') {
	                		situacao = ' - ( INATIVO )';
	                		color = 'style="color: #c9302c;"';
	                	}
	                	$('#idCondominio').append('<option value=' + condominio.idCondominio + ' ' + color + '>' + condominio.nome + ' - ' + condominio.endereco + ' ' + condominio.numero + ' ' + condominio.compl + '' + situacao + '</option>');
	                }
	            }
	            $('#idCondominio').selectpicker('refresh');	            
	            $.unblockUI();
	        },
	        error : function(){
	
	            $.unblockUI();
	            alert('erro');
	        }
	    });    
	}
}

function abrirMapaReservatorios() {
	window.open('MapaConsumoPressaoBO?acao=1', '_blank');
}

function lPad(number, length) {   
    var str = '' + number;
    while (str.length < length) {
        str = '0' + str;
    }
    return str;
}

function limitarTexto(texto, length) {   	
    var str = '';    
  	for (j = 0; j < texto.length; j++) {     
		str = str + texto.charAt(j);    
  		if(length == (j + 1)) {
        	break;
        }
	}
    return str;
}

var idEmpresaValue;
var idCondominioValue; 
var colorRed = '#e52213';
function carregarReservatorios() {	
	
	var aviso = document.getElementById('aviso');
	var idEmpresa = document.getElementById('idEmpresa');	
	var idCondominio = document.getElementById('idCondominio');
	
	$("#aviso").html("");
	idEmpresa.style.removeProperty('border');
	
	if(idEmpresa.value == '') {
		idEmpresa.style.borderColor = colorRed;
		$("#aviso").html("Selecione a Empresa");
	}
	else {
	
		for (i = 1; i < 49; i++) {
			$("#container" + i).html("");
			$("#titulo" + i).html("");
		}
		idEmpresaValue = $('#idEmpresa').val();
		idCondominioValue = $('#idCondominio').val();
		atualizarReservatorios();
	}
}

var timeAtualizarRelatorios;
function atualizarReservatorios() {
	
	$('[data-toggle="popover"]').popover('hide');
	if(timeAtualizarRelatorios != null) {
		clearTimeout(timeAtualizarRelatorios);
	}
	$.ajax({
        url: 'ReservatorioBO?acao=2' +
        '&idEmpresa=' + idEmpresaValue +
        '&idCondominio=' + idCondominioValue,
        //data: $('#form1').serialize(),
        type: "POST",
        dataType: 'json',
        success: function(result) {

            var listRelPressaoLast = result;
            
            if(listRelPressaoLast != null && listRelPressaoLast.length > 0) {
	
            	var cont = 1;
        		for(i = 0; i < listRelPressaoLast.length; i++) {
        					                	
                	var relPressaoLast = listRelPressaoLast[i];
                	
                	var data = relPressaoLast.pressao;			
        			var altura = 140;
        			var condonimio = relPressaoLast.condominio;
        			var deviceNum = relPressaoLast.deviceNum;		                	        			
        			
        			$('#divHoraAtualizacao').html('<p class="text-muted"><label style="font-size: 8pt;">ATUALIZA&Ccedil;&Atilde;O ' + relPressaoLast.dtBusca + '</label></p>');
        			
                	var info = 
                	'' + relPressaoLast.condominio + '<br>' +
                	'Bridge: ' + relPressaoLast.deviceNum + '<br>' +
        			'Limite Baixa: ' + formatarTresDecimais(relPressaoLast.pressaoMinBaixa) + '<br>' +
        			'Normal: ' + formatarTresDecimais(relPressaoLast.pressaoMin) + ' ~ ' + formatarTresDecimais(relPressaoLast.pressaoMax) + '<br>' +
        			'Limite Alta: ' + formatarTresDecimais(relPressaoLast.pressaoMaxAlta) + '<br>' +
        			'Hora: ' + relPressaoLast.dtInsert + '<br>' 
        			;
                	$('#titulo' + cont).html(
        			'<label style="font-size: 7pt; margin: 0px;" data-toggle="popover" title="" data-trigger="hover" data-content="' + info + '" data-original-title="" data-html="true">' + limitarTexto(relPressaoLast.condominio, 15) + '</label><br>' +
        			'<label style="font-size: 7pt; margin: 0px;">' + relPressaoLast.dtInsert.substring(0, 5) + ' ' + relPressaoLast.dtInsert.substring(11, 16) + '</label>'
        			);
                	
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
        							color : '#17a2b8',
        							value : pressaoMinPerc
        						}, {
        							color : '#17a2b8',
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
//         				tooltip : {
//         					pointFormat: '<tr><td style="padding:0"><b>' + pressao + ' MCA</b></td></tr>',					
//         					valueDecimals : 3,
//         					outside: true
//         				},
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
        			            format: ''+ pressao + ' MCA',
        			            color: 'black'
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
            else {
            	$("#aviso").html("Nenhum registro encontrado!");
            }
        },
        error : function() {		
            alert('erro');
            window.location = "http://www.waterbridge.com.br/";
        }
    });
	
	timeAtualizarRelatorios = setTimeout(function() {
		atualizarReservatorios();
	}, 300000);
}