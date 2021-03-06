var colorRed = '#e52213';

function listarCondominio() {

    var idEmpresa = document.getElementById('idEmpresa');

    if(idEmpresa.value == '') {
    	
    	$('#idCondominio option').remove();
        $('#idCondominio').append('<option value="" selected="selected">Selecione...</option>');
        
        $('#idBridge option').remove();
        $('#idBridge').append('<option value="" selected="selected">Selecione...</option>');
        
        $('#idMedidor option').remove();
        $('#idMedidor').append('<option value="" selected="selected">Selecione...</option>');
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
                $('#idCondominio').append('<option value="" selected="selected">Selecione...</option>');
                
                $('#idBridge option').remove();
                $('#idBridge').append('<option value="" selected="selected">Selecione...</option>');
                
                $('#idMedidor option').remove();
                $('#idMedidor').append('<option value="" selected="selected">Selecione...</option>');
                
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
        
        $('#idMedidor option').remove();
        $('#idMedidor').append('<option value="" selected="selected">Selecione...</option>');
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
	        url: 'ConsumoCondominioBO?acao=3' +
	             '&idCondominio=' + idCondominio.value
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	
	        	$('#idBridge option').remove();
                $('#idBridge').append('<option value="" selected="selected">Selecione...</option>');
                
                $('#idMedidor option').remove();
                $('#idMedidor').append('<option value="" selected="selected">Selecione...</option>');
                
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

function listarMedidor() {

    var idBridge = document.getElementById('idBridge');

    if(idBridge.value == '') {
    	
    	$('#idMedidor option').remove();
        $('#idMedidor').append('<option value="" selected="selected">Selecione...</option>');
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
	        url: 'ConsumoCondominioBO?acao=4' +
	             '&idBridge=' + idBridge.value
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	
	        	$('#idMedidor option').remove();
                $('#idMedidor').append('<option value="" selected="selected">Selecione...</option>');
	            var listMedidor = result;
	            if(listMedidor != null && listMedidor.length > 0) {
	                for(i = 0; i < listMedidor.length; i++) {
	                	var medidor = listMedidor[i];
	                    $('#idMedidor').append('<option value=' + medidor.idMedidor + '>' + medidor.numeroMedidor + '</option>');
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
	var idMedidor = document.getElementById('idMedidor');
	var dtInicio = document.getElementById('dtInicio');
	var dtFim = document.getElementById('dtFim');
	var divTable = document.getElementById('divTable');

	divAviso.innerHTML = '';
	idEmpresa.style.removeProperty('border');
	idCondominio.style.removeProperty('border');
	idBridge.style.removeProperty('border');
	idMedidor.style.removeProperty('border');
	dtInicio.style.removeProperty('border');
	dtFim.style.removeProperty('border');
	
    if(idEmpresa.value == '') {
    	
    	idEmpresa.style.borderColor = colorRed;
    }
    else if(idCondominio.value == '') {
    	
    	idCondominio.style.borderColor = colorRed;
    }
//    else if(idBridge.value == '') {
//    	
//    	idBridge.style.borderColor = colorRed;
//    }
//    else if(idMedidor.value == '') {
//    	
//    	idMedidor.style.borderColor = colorRed;
//    }
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
	        url: 'ConsumoCondominioBO?acao=5' +
	             '&idEmpresa=' + idEmpresa.value +
	             '&idCondominio=' + idCondominio.value +
	             '&idBridge=' + idBridge.value +
	             '&idMedidor=' + idMedidor.value +
	             '&dtInicio=' + dtInicio.value +
	             '&dtFim=' + dtFim.value
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	        	
	        	var texto = '';
	        	var consumo = 0;
	            var consumoCondominio = result;
	            if (consumoCondominio != null
						&& consumoCondominio.listRelConsumoCondominio != null
						&& consumoCondominio.listRelConsumoCondominio.length > 0) {
	            	
	            	texto +=
	            	"<table class='table table-hover table-striped'>" +
	            	"	<thead>" +
	            	"		<tr>" +
	            	"			<th>" +
	            	"		         <form action='ConsumoCondominioBO?acao=6' method='post' target='_blank'>" +
					"                    <input type='hidden' name='idEmpresa' value='" + idEmpresa.value + "'>" +
					"                    <input type='hidden' name='idCondominio' value='" + idCondominio.value + "'>" +
					"                    <input type='hidden' name='idBridge' value='" + idBridge.value + "'>" +
					"                    <input type='hidden' name='idMedidor' value='" + idMedidor.value + "'>" +
					"                    <input type='hidden' name='dtInicio' value='" + dtInicio.value + "'>" +
					"                    <input type='hidden' name='dtFim' value='" + dtFim.value + "'>" +
					"			         <button type='submit' class='btn btn-warning' title='Clique para visualizar o gráfico'>" +
					"		                 <i class='fa fa-bar-chart'></i>" +
					"		             </button>" +
					"		         </form>" +
	            	"			</th>" +
	            	"			<th>" +
	            	"		         <form action='ConsumoCondominioBO?acao=excel' method='post' target='_blank'>" +
	            	"                    <input type='hidden' name='idEmpresa' value='" + idEmpresa.value + "'>" +
	            	"                    <input type='hidden' name='idCondominio' value='" + idCondominio.value + "'>" +
	            	"                    <input type='hidden' name='idBridge' value='" + idBridge.value + "'>" +
	            	"                    <input type='hidden' name='idMedidor' value='" + idMedidor.value + "'>" +
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
		            "			<th>Medidor</th>" +
		            "			<th>Endereço</th>" +
		            "			<th>Volume Inicial (m&#179;)</th>" +
		            "			<th>Volume Final (m&#179;)</th>" +
		            "			<th>Consumo</th>" +
		            "			<th>Consumidor</th>" +
		            "			<th></th>" +
		            "		</tr>" +
		            "	</thead>" +
		            "	<tbody id='myTable'>" ;
            		for(i = 0; i < consumoCondominio.listRelConsumoCondominio.length; i++) {
	                	
	                	var relConsumoCondominio = consumoCondominio.listRelConsumoCondominio[i];	                		                
	                	consumo = consumo + relConsumoCondominio.consumo;
	                	
	                	texto +=
    		            "		<tr>" +
    		            "			<td><small>" + (i + 1) + "</small></td>" +
    		            "			<td><small>" + relConsumoCondominio.meterId + "</small></td>" +
    		            "			<td><small>" + relConsumoCondominio.endereco + " " + relConsumoCondominio.numero + " " + relConsumoCondominio.compl + "</small></td>" +
    		            "			<td><small>" + formatarTresDecimais(relConsumoCondominio.volumeInicio) + "</small></td>" +
    		            "			<td><small>" + formatarTresDecimais(relConsumoCondominio.volumeFim) + "</small></td>" +
    		            "			<td><small>" + formatarTresDecimais(relConsumoCondominio.consumo) + "</small></td>" +
	                	"			<td>" +
	                	"               <small>" ;
	                	var listRelUserMedidor = relConsumoCondominio.listRelUserMedidor;
	                	for(j = 0; j < listRelUserMedidor.length; j++) {
	                		
	                		var relUserMedidor = listRelUserMedidor[j];
	                		texto +=
	    	                "			" + relUserMedidor.cpfUser + " - " + relUserMedidor.nomeUser + "<br/>";
	                	}
	                	texto +=
	                	"               </small>" +
	                    "           </td>" +
    		            "		    <td align='right'></td>" +
    		            "		</tr>" ;
	                }
		            texto +=
					"        <tr>" +
					"	         <td colspan='8' style='text-align: center'>" +				
					"		         <label>Consumo total no período em m&#179; (1m&#179; = 1.000 Litros): " + formatarTresDecimais(Number(consumo)) + "</label>" +
					"	         </td>" +
					"        </tr>" +
					"        <tr>" +
					"	         <td>" +
					"		         <form action='ConsumoCondominioBO?acao=6' method='post' target='_blank'>" +
					"                    <input type='hidden' name='idEmpresa' value='" + idEmpresa.value + "'>" +
					"                    <input type='hidden' name='idCondominio' value='" + idCondominio.value + "'>" +
					"                    <input type='hidden' name='idBridge' value='" + idBridge.value + "'>" +
					"                    <input type='hidden' name='idMedidor' value='" + idMedidor.value + "'>" +
					"                    <input type='hidden' name='dtInicio' value='" + dtInicio.value + "'>" +
					"                    <input type='hidden' name='dtFim' value='" + dtFim.value + "'>" +
					"			         <button type='submit' class='btn btn-warning' title='Clique para visualizar o gráfico'>" +
					"		                 <i class='fa fa-bar-chart'></i>" +
					"		             </button>" +
					"		         </form>" +
					"	         </td>" +
					"	         <td colspan='8' style='text-align: left'>" +
					"		         <form action='ConsumoCondominioBO?acao=excel' method='post' target='_blank'>" +
					"                    <input type='hidden' name='idEmpresa' value='" + idEmpresa.value + "'>" +
					"                    <input type='hidden' name='idCondominio' value='" + idCondominio.value + "'>" +
					"                    <input type='hidden' name='idBridge' value='" + idBridge.value + "'>" +
					"                    <input type='hidden' name='idMedidor' value='" + idMedidor.value + "'>" +
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
		            
		            Highcharts.chart('graficoconsumodiario', {
				    	chart: {
				        	type: 'column'
				    	},
				    	title: {
				        	text: 'Gráfico de Consumo<br/><label> ' + consumoCondominio.nomeCondominio + '</label>'
				    	},
				    	subtitle: {
				        	text: 'Período ' + consumoCondominio.dtInicio + ' a ' + consumoCondominio.dtFim
				    	},
					    xAxis: {
					        crosshair: true,
					        categories: (function () {
					            // generate an array of random data
					            var data = [];

					            for(i = 0; i < consumoCondominio.listRelConsumoCondominio.length; i++) {
					                data.push([
					                	consumoCondominio.listRelConsumoCondominio[i].meterId
					                ]);
					            }
					            return data;
					        }())
					    },
					    yAxis: {
					        min: 0,
					        title: {
					            text: 'Consumo (m3)'
					        }
					    },
					    tooltip: {
					        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
					        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' + '<td style="padding:0"><b>{point.y:.3f} m3</b></td></tr>',
					        footerFormat: '</table>',
					        shared: true,
					        useHTML: true
					    },
					    plotOptions: {
					        column: {
					            pointPadding: 0.2,
					            borderWidth: 0
					        }
					    },
					    series: [{
					        name: 'Medidor',
					        data: (function () {
					            // generate an array of random data
					            var data = [];

					            for(i = 0; i < consumoCondominio.listRelConsumoCondominio.length; i++) {
					                data.push([
					                	consumoCondominio.listRelConsumoCondominio[i].meterId,
					                	consumoCondominio.listRelConsumoCondominio[i].consumo
					                ]);
					            }
					            return data;
					        }()) 
					    }]
					});
		            
		            setTimeout(listarConsumoMedidor, 600000);
	            }
	            else {
	                document.getElementById("divAviso").style.display = "block";
	                document.getElementById("divAviso").innerHTML = 
	                	"<strong><label id='aviso' name='aviso'/>Nenhum Resultado Encontrado!</strong>";
	            }
	            divTable.innerHTML = texto;
	            $.unblockUI();
	        },
	        error : function(){
	
	            $.unblockUI();
	            alert('erro');
	        }
	    });    
	}
}