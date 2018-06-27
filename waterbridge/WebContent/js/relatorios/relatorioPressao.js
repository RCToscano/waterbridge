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

	divAviso.innerHTML = '';
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
	            var listRelPressao = result;
	            if(listRelPressao != null && listRelPressao.length > 0) {
	            	document.getElementById("divAviso").style.display = "none";
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
					"			<th colspan='6'>" +
					"		         <form action='RelatorioPressaoBO?acao=excel' method='post' target='_blank'>" +
					"                    <input type='hidden' name='idEmpresa' value='" + idEmpresa.value + "'>" +
					"                    <input type='hidden' name='idCondominio' value='" + idCondominio.value + "'>" +
					"                    <input type='hidden' name='idBridge' value='" + idBridge.value + "'>" +
					"                    <input type='hidden' name='dtInicio' value='" + dtInicio.value + "'>" +
					"                    <input type='hidden' name='dtFim' value='" + dtFim.value + "'>" +
					"			         <button type='submit' class='btn btn-warning' title='Clique para fazer o download em excel'>" +
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
		            "			<th>Pressão</th>" +
		            "			<th>Alarme</th>" +
		            "			<th>Bateria (V)</th>" +
		            "			<th>Temperatura (ºC)</th>" +
		            "			<th></th>" +
		            "		</tr>" +
		            "	</thead>" +
		            "	<tbody id='myTable'>" ;
            		for(i = 0; i < listRelPressao.length; i++) {
	                	
	                	var relatPressao = listRelPressao[i];	                		                
	                	texto +=
    		            "		<tr>" +
    		            "			<td><small>" + (i + 1) + "</small></td>" +
    		            "			<td><small>" + relatPressao.dtInsert + "</small></td>" +
    		            "			<td><small>" + formatarTresDecimais(relatPressao.pressure) + "</small></td>" +
    		            "			<td><small>" + relatPressao.alarmDesc + "</small></td>" +
    		            "			<td><small>" + relatPressao.battery + "</small></td>" +
    		            "			<td><small>" + relatPressao.temperature + "</small></td>" +
    		            "		    <td align='right'></td>" +
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
					"	         <td colspan='6' style='text-align: left'>" +
					"		         <form action='RelatorioPressaoBO?acao=excel' method='post' target='_blank'>" +
					"                    <input type='hidden' name='idEmpresa' value='" + idEmpresa.value + "'>" +
					"                    <input type='hidden' name='idCondominio' value='" + idCondominio.value + "'>" +
					"                    <input type='hidden' name='idBridge' value='" + idBridge.value + "'>" +
					"                    <input type='hidden' name='dtInicio' value='" + dtInicio.value + "'>" +
					"                    <input type='hidden' name='dtFim' value='" + dtFim.value + "'>" +
					"			         <button type='submit' class='btn btn-warning' title='Clique para fazer o download em excel'>" +
					"		                 <i class='fa fa-file-excel'></i>" +
					"		             </button>" +
					"		         </form>" +
					"	         </td>" +
					"        </tr>" +
		            "    </tbody>" +
		            "</table>" ;	 
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