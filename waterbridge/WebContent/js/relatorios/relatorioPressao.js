var colorRed = '#e52213';

function listarCondominio() {

    var idEmpresa = document.getElementById('idEmpresa');

    if(idEmpresa.value == '') {
    	
    	$('#idCondominio option').remove();
        $('#idCondominio').append('<option value="" selected="selected">Selecione...</option>');
        
        $('#idBridge option').remove();
        $('#idBridge').append('<option value="" selected="selected">Selecione...</option>');
        
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
	        url: 'RelatorioPressaoBO?acao=2' +
	             '&idEmpresa=' + idEmpresa.value
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	
	        	$('#idCondominio option').remove();
                $('#idCondominio').append('<option value="" selected="selected">Selecione...</option>');
                
                $('#idBridge option').remove();
                $('#idBridge').append('<option value="" selected="selected">Selecione...</option>');
                
	            var listCondominio = result;
	            if(listCondominio != null && listCondominio.length > 0) {
	                for(i = 0; i < listCondominio.length; i++) {
	                	var condominio = listCondominio[i];
	                    $('#idCondominio').append('<option value=' + condominio.idCondominio + '>' + condominio.nome + ' - ' + condominio.endereco + ' ' + condominio.numero + ' ' + condominio.compl + '</option>');
	                }
	            }
	            $.unblockUI();
	        },
	        error : function(){
	
	            $.unblockUI();
	            alert('erro');
	        }
	    });    
	}
}

function listarBridge() {

    var idCondominio = document.getElementById('idCondominio');

    if(idCondominio.value == '') {
    	
    	$('#idBridge option').remove();
        $('#idBridge').append('<option value="" selected="selected">Selecione...</option>');
        
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
	        url: 'RelatorioPressaoBO?acao=3' +
	             '&idCondominio=' + idCondominio.value
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	
	        	$('#idBridge option').remove();
                $('#idBridge').append('<option value="" selected="selected">Selecione...</option>');
                
	            var listBridge = result;
	            if(listBridge != null && listBridge.length > 0) {
	                for(i = 0; i < listBridge.length; i++) {
	                	var bridge = listBridge[i];
	                    $('#idBridge').append('<option value=' + bridge.idBridge + '>' + bridge.deviceNum + '</option>');
	                }
	            }
	            $.unblockUI();
	        },
	        error : function(){
	
	            $.unblockUI();
	            alert('erro');
	        }
	    });    
	}
}

function listarConsumoMedidor() {
	
	var divAviso = document.getElementById('divAviso');
	var idEmpresa = document.getElementById('idEmpresa');
	var idCondominio = document.getElementById('idCondominio');
	var idBridge = document.getElementById('idBridge');
	var dtInicio = document.getElementById('dtInicio');
	var dtFim = document.getElementById('dtFim');
	var divTable = document.getElementById('divTable');
	var divGrafico = document.getElementById('graficopressaodiaria');

	divAviso.innerHTML = '';
	divTable.innerHTML = '';
	divGrafico.innerHTML = '';
	
	idEmpresa.style.removeProperty('border');
	idCondominio.style.removeProperty('border');
	idBridge.style.removeProperty('border');
	dtInicio.style.removeProperty('border');
	dtFim.style.removeProperty('border');
	
    if(idEmpresa.value == '') {
    	
    	idEmpresa.style.borderColor = colorRed;
    }
    else if(idCondominio.value == '') {
    	
    	idCondominio.style.borderColor = colorRed;
    }
    else if(idBridge.value == '') {
    	
    	idBridge.style.borderColor = colorRed;
    }
    else if(dtInicio.value == '') {
    	
    	dtInicio.style.borderColor = colorRed;
    }
    else if(dtFim.value == '') {
    	
    	dtFim.style.borderColor = colorRed;
    }
    else {
    	var numeroDtInicio = dtInicio.value.split('/');
    	var numeroDtFim = dtFim.value.split('/');
    	var dataInicio = new Date(numeroDtInicio[2], (numeroDtInicio[1]-1), numeroDtInicio[0]);
    	var dataFim = new Date(numeroDtFim[2], (numeroDtFim[1]-1), numeroDtFim[0]);
    	
    	if(dataFim < dataInicio) {
    		dtInicio.style.borderColor = colorRed;
    		dtFim.style.borderColor = colorRed;
    		divAviso.innerHTML ="<div class='alert alert-danger'>" +
				 				"  <strong><label id='aviso' name='aviso'/>Data Fim não pode ser menor que Data Início</strong>" +
				 				"</div>";
    		return false;
    	}
    	
    	dataInicio.setDate(dataInicio.getDate() + 30);

    	if(dataFim > dataInicio) {
    		dtInicio.style.borderColor = colorRed;
    		dtFim.style.borderColor = colorRed;
    		divAviso.innerHTML ="<div class='alert alert-danger'>" +
				 				"  <strong><label id='aviso' name='aviso'/>O período não deve ultrapassar 30 dias</strong>" +
				 				"</div>";
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
	        url: 'RelatorioPressaoBO?acao=5' +
	             '&idEmpresa=' + idEmpresa.value +
	             '&idCondominio=' + idCondominio.value +
	             '&idBridge=' + idBridge.value +
	             '&dtInicio=' + dtInicio.value +
	             '&dtFim=' + dtFim.value
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	        	
	        	var texto = '';
	        	var consumo = 0;
	            var relPressao = result;
	            if(relPressao != null && relPressao.listRelPressao.length > 0) {
	            		            	
	            	var tootipAlarm = 
	                "data-toggle='popover' title='Limites Pressão' data-trigger='hover' " +
	                "data-content='Baixa: " + formatarTresDecimais(relPressao.metaPressao.pressaoMinBaixa) + "<br>" +
	                "Normal: " + formatarTresDecimais(relPressao.metaPressao.pressaoMin) + " ~ " + formatarTresDecimais(relPressao.metaPressao.pressaoMax) + "<br>" +
	                "Alta: " + formatarTresDecimais(relPressao.metaPressao.pressaoMaxAlta) + "'";
	            		
	            	texto +=	            	
	            	"<table class='table table-hover table-striped'>" +
	            	"	<thead>" +
	            	"		<tr>" +
	            	"			<th>" +
	            	"		         <form action='RelatorioPressaoBO?acao=6' method='post' target='_blank'>" +
					"                    <input type='hidden' name='idEmpresa' value='" + idEmpresa.value + "'>" +
					"                    <input type='hidden' name='idCondominio' value='" + idCondominio.value + "'>" +
					"                    <input type='hidden' name='idBridge' value='" + idBridge.value + "'>" +
					"                    <input type='hidden' name='dtInicio' value='" + dtInicio.value + "'>" +
					"                    <input type='hidden' name='dtFim' value='" + dtFim.value + "'>" +
					"			         <button type='submit' class='btn btn-warning' title='Clique para visualizar o gráfico'>" +
					"		                <i class='fa fa-bar-chart'></i>" +
					"		             </button>" +
					"		         </form>" +
					"			</th>" +
					"			<th colspan='7'>" +
					"		         <form action='RelatorioPressaoBO?acao=excel' method='post' target='_blank'>" +
					"                    <input type='hidden' name='idEmpresa' value='" + idEmpresa.value + "'>" +
					"                    <input type='hidden' name='idCondominio' value='" + idCondominio.value + "'>" +
					"                    <input type='hidden' name='idBridge' value='" + idBridge.value + "'>" +
					"                    <input type='hidden' name='dtInicio' value='" + dtInicio.value + "'>" +
					"                    <input type='hidden' name='dtFim' value='" + dtFim.value + "'>" +
					"			         <button type='submit' class='btn btn-success' title='Clique para fazer o download em excel'>" +
					"		                 <i class='fa fa-file-excel'></i>" +
					"		             </button>" +
					"		         </form>" +
	            	"			</th>" +        	
	            	"		</tr>" +
	            	"	</thead>" +
		            "	<thead>" +
		            "		<tr>" +
		            "			<th>Nº</th>" +
		            "			<th>Data</th>" +
		            "			<th>Hora</th>" +
		            "			<th>Pressão (MCA)</th>" +
		            "			<th>Alarme</th>" +
		            "			<th>Bateria (V)</th>" +
		            "			<th>Temperatura (ºC)</th>" +
		            "			<th></th>" +
		            "		</tr>" +
		            "	</thead>" +
		            "	<tbody id='myTable'>" ;
            		for(i = 0; i < relPressao.listRelPressao.length; i++) {
	                	
	                	var relatPressao = relPressao.listRelPressao[i];
	                	
	                	var bgTd = '';
	                	var alarmPressao = '';
	                	if(relPressao.metaPressao != null && relPressao.metaPressao.pressaoMinBaixa > relatPressao.pressure) {
	                		bgTd = "bgcolor='#f2dede'";
	                		alarmPressao = 'Pressão Baixa (Nível Crítico)';
	                	}
	                	else if(relPressao.metaPressao != null && relPressao.metaPressao.pressaoMin > relatPressao.pressure) {
	                		bgTd = "bgcolor='#f2dede'";
	                		alarmPressao = 'Pressão Baixa';
	                	}
	                	else if(relPressao.metaPressao != null && relPressao.metaPressao.pressaoMaxAlta < relatPressao.pressure) {
	                		bgTd = "bgcolor='#f2dede'";
	                		alarmPressao = 'Pressão Alta (Nível Crítico)';
	                	}
	                	else if(relPressao.metaPressao != null && relPressao.metaPressao.pressaoMax < relatPressao.pressure) {
	                		bgTd = "bgcolor='#f2dede'";
	                		alarmPressao = 'Pressão Alta';
	                	}
	                	
	                	var alarmPadrao = '';
	                	if(relatPressao.alarmDesc != null && relatPressao.alarmDesc != 'NO ALARM') {
	                		bgTd = "bgcolor='#f2dede'";
	                		alarmPadrao = relatPressao.alarmDesc;
	                	}
	                	else if(alarmPressao == '') {
	                		alarmPadrao = 'Sem Alarme';
	                	}
	                	
	                	texto +=
    		            "		<tr>" +
    		            "			<td " + bgTd + "><small>" + (i + 1) + "</small></td>" +
    		            "			<td " + bgTd + "><small>" + relatPressao.dtInsert + "</small></td>" +
    		            "			<td " + bgTd + "><small>" + relatPressao.horaInsert + "</small></td>" +
    		            "			<td " + bgTd + "><small " + tootipAlarm + ">" + formatarTresDecimais(relatPressao.pressure) + "</small></td>" +
    		            "			<td " + bgTd + "><small>" + alarmPadrao + " " + alarmPressao + "</small></td>" +
    		            "			<td " + bgTd + "><small>" + substituirPonto(relatPressao.battery) + "</small></td>" +
    		            "			<td " + bgTd + "><small>" + relatPressao.temperature + "</small></td>" +
    		            "		    <td align='right' " + bgTd + "></td>" +
    		            "		</tr>" ;
	                }
		            texto +=
					"        <tr>" +
					"	         <td>" +
					"		         <form action='RelatorioPressaoBO?acao=6' method='post' target='_blank'>" +
					"                    <input type='hidden' name='idEmpresa' value='" + idEmpresa.value + "'>" +
					"                    <input type='hidden' name='idCondominio' value='" + idCondominio.value + "'>" +
					"                    <input type='hidden' name='idBridge' value='" + idBridge.value + "'>" +
					"                    <input type='hidden' name='dtInicio' value='" + dtInicio.value + "'>" +
					"                    <input type='hidden' name='dtFim' value='" + dtFim.value + "'>" +
					"			         <button type='submit' class='btn btn-warning' title='Clique para visualizar o gráfico'>" +
					"		                 <i class='fa fa-bar-chart'></i>" +
					"		             </button>" +
					"		         </form>" +
					"	         </td>" +
					"	         <td colspan='7' style='text-align: left'>" +
					"		         <form action='RelatorioPressaoBO?acao=excel' method='post' target='_blank'>" +
					"                    <input type='hidden' name='idEmpresa' value='" + idEmpresa.value + "'>" +
					"                    <input type='hidden' name='idCondominio' value='" + idCondominio.value + "'>" +
					"                    <input type='hidden' name='idBridge' value='" + idBridge.value + "'>" +
					"                    <input type='hidden' name='dtInicio' value='" + dtInicio.value + "'>" +
					"                    <input type='hidden' name='dtFim' value='" + dtFim.value + "'>" +
					"			         <button type='submit' class='btn btn-success' title='Clique para fazer o download em excel'>" +
					"		                 <i class='fa fa-file-excel'></i>" +
					"		             </button>" +
					"		         </form>" +
					"	         </td>" +
					"        </tr>" +
		            "    </tbody>" +
		            "</table>" ;
		            
					Highcharts.chart('graficopressaodiaria', {
					    chart: {  
					    	type: 'line',  
				            panning: true  
					    },  
					    mapNavigation: {  
			                enabled: true,  
			                enableButtons: false  
			            },  
					    title: {  
					        text: 'Gráfico de Pressão<br/><label>Bridge ' + relPressao.bridge +' </label>'  
					    },  
					    subtitle: {  
					        text: 'Período '+ relPressao.dtInicio +' a '+ relPressao.dtFim  
					    },
					    xAxis: {
					    	crosshair: true,
					    	categories: (function () {
					            // generate an array of random data
					            var data = [];

					            for(i = 0; i < relPressao.listData.length; i++) {
					                data.push([
					                	relPressao.listData[i]
					                ]);
					            }
					            return data;
					        }())
					    },
					    yAxis: {
					    	min: 0,  
					        title: {  
					            text: 'MCA'  
					        }  							    
					       	,
					      	//minorGridLineWidth: 0,
					        //gridLineWidth: 0,
					        //alternateGridColor: null,
					        plotBands: [{ // limite pressao baixa
					            from: relPressao.metaPressao.pressaoMinBaixa,
					            to: relPressao.metaPressao.pressaoMin,
					            color: 'rgba(255, 153, 153, 0.1)',
					            label: {
					                text: ' ',
					                style: {
					                    color: '#606060'
					                }
					            }
					        },
					        { // limite pressao normal
					            from: relPressao.metaPressao.pressaoMin,
					            to: relPressao.metaPressao.pressaoMax,
					            color: 'rgba(68, 170, 213, 0.1)',
					            label: {
					                text: ' ',
					                style: {
					                    color: '#606060'
					                }
					            }
					        },
					        { //limite maximo pressao alta
					            from: relPressao.metaPressao.pressaoMax,
					            to: relPressao.metaPressao.pressaoMaxAlta,
					            color: 'rgba(255, 153, 153, 0.1)',
					            label: {
					                text: ' ',
					                style: {
					                    color: '#606060'
					                }
					            }
					        }]
					    },  
					    tooltip: {  
					    	headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
					        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' + '<td style="padding:0"><b>{point.y:.3f}</b></td></tr>',
					        footerFormat: '</table>',
					        shared: true,
					        useHTML: true 
					    },  
					    plotOptions: {  
					        column: {  
					            pointPadding: 0.3,  
					            borderWidth: 0  
					        }  
					    },  
					    series: [{  
					        name: 'Bridge ' + relPressao.bridge,  
					        data: (function () {
					            // generate an array of random data
					            var data = [];

					            for(i = 0; i < relPressao.listPressao.length; i++) {
					                data.push([
					                	relPressao.listData[i],
					                    relPressao.listPressao[i]
					                ]);
					            }
					            return data;
					        }())  
					    }]  
					});

		            setTimeout(listarConsumoMedidor, 600000);
	            }
	            else {
	            	divAviso.innerHTML ="<div class='alert alert-danger'>" +
	 				"  <strong><label id='aviso' name='aviso'/>Nenhum resultado encontrado!</strong>" +
	 				"</div>";
	            }
	            divTable.innerHTML = texto;
	            $('[data-toggle="popover"]').popover({html: true}); 
	            $.unblockUI();
	        },
	        error : function(){
	            $.unblockUI();
	            alert('erro');
	        }
	    });    
	}
}

function autoRefresh(interval) {
	setTimeout("listarConsumoMedidor();", interval);
}