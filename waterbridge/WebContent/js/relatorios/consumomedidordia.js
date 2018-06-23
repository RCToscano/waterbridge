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
	        url: 'ConsumoMedidorBO?acao=2' +
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
	        url: 'ConsumoMedidorBO?acao=3' +
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
	        url: 'ConsumoMedidorBO?acao=4' +
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
	
	var divAviso = document.getElementById("divAviso");
    var aviso = document.getElementById("aviso");
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
    else if(idBridge.value == '') {
    	
    	idBridge.style.borderColor = colorRed;
    }
    else if(idMedidor.value == '') {
    	
    	idMedidor.style.borderColor = colorRed;
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
	        url: 'ConsumoMedidorBO?acao=5' +
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
	        	var colspan = 6;
	        	var consumo = 0;
	            var listRelConsumoMedidor = result;
	            if(listRelConsumoMedidor != null && listRelConsumoMedidor.length > 0) {
	            	
	            	texto +=	
	            	"<table class='table table-hover table-striped'>" +
		            "	<thead>" +
		            "		<tr>" +
		            "			<th>Nº</th>" +
		            "			<th>Data</th>" +
		            "			<th>Volume (m&#179;)</th>" ;
	            	if(listRelConsumoMedidor[i].idBridgeTp != null
	            		&& listRelConsumoMedidor[i].idBridgeTp != undefined
	            			&& listRelConsumoMedidor[i].idBridgeTp == 1) {
	            		texto += "			<th>Pressão</th>" ;
	            		colspan = colspan + 1;
	            	}
	            	texto +=
	            	"			<th>Alarme</th>" +
		            "			<th>Bateria (V)</th>" +
		            "			<th>Temperatura (ºC)</th>" +
		            "			<th></th>" +
		            "		</tr>" +
		            "	</thead>" +
		            "	<tbody id='myTable'>" ;
            		for(i = 0; i < listRelConsumoMedidor.length; i++) {
	                	
	                	var relConsumoMedidor = listRelConsumoMedidor[i];	                		                
	                	consumo = consumo + relConsumoMedidor.consumo;
	                	texto +=
    		            "		<tr>" +
    		            "			<td><small>" + (i + 1) + "</small></td>" +
    		            "			<td><small>" + relConsumoMedidor.dtInsert + "</small></td>" +
    		            "			<td><small>" + formatarTresDecimais(relConsumoMedidor.volume) + "</small></td>" ;
	                	if(relConsumoMedidor.idBridgeTp != null
		            		&& relConsumoMedidor.idBridgeTp != undefined
	                			&& relConsumoMedidor.idBridgeTp == 1) {
		            		texto += "			<td><small>" + formatarTresDecimais(relConsumoMedidor.pressure) + "</small></td>" ;
		            	}
	                	texto +=
    		            "			<td><small>" + relConsumoMedidor.alarmDesc + "</small></td>" +
    		            "			<td><small>" + relConsumoMedidor.battery + "</small></td>" +
    		            "			<td><small>" + relConsumoMedidor.temperature + "</small></td>" +
    		            "		    <td align='right'></td>" +
    		            "		</tr>" ;
	                }
		            texto +=
					"        <tr>" +
					"	         <td colspan='" + colspan + "' style='text-align: center'>" +				
					"		         <label>Consumo total no período em m&#179; (1m&#179; = 1.000 Litros): " + formatarTresDecimais(Number(consumo)) + "</label>" +
					"	         </td>" +
					"        </tr>" +
					"        <tr>" +
					"	         <td colspan='" + colspan + "' style='text-align: center'>" +
					"		         <form action='ConsumoMedidorBO?acao=6' method='post' target='_blank'>" +
					"                    <input type='hidden' name='idEmpresa' value='" + idEmpresa.value + "'>" +
					"                    <input type='hidden' name='idCondominio' value='" + idCondominio.value + "'>" +
					"                    <input type='hidden' name='idBridge' value='" + idBridge.value + "'>" +
					"                    <input type='hidden' name='idMedidor' value='" + idMedidor.value + "'>" +
					"                    <input type='hidden' name='dtInicio' value='" + dtInicio.value + "'>" +
					"                    <input type='hidden' name='dtFim' value='" + dtFim.value + "'>" +
					"			         <button type='submit' class='btn btn-warning'>" +
					"		                 <i class='fa fa-bar-chart'></i> Gráfico" +
					"		             </button>" +
					"		         </form>" +
					"	         </td>" +
					"        </tr>" +
		            "    </tbody>" +
		            "</table>" ;	 
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


