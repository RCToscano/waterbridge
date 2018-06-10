var colorRed = '#e52213';

function listarCondominio() {

    var idEmpresa = document.getElementById('idEmpresa');

    if(idEmpresa.value == '') {
    	
    	$('#idCondominio option').remove();
        $('#idCondominio').append('<option value="" selected="selected">Selecione...</option>');
        
        $('#idConta option').remove();
        $('#idConta').append('<option value="" selected="selected">Selecione...</option>');
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
	        url: 'RateioBO?acao=2' +
	             '&idEmpresa=' + idEmpresa.value
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	
	        	$('#idCondominio option').remove();
                $('#idCondominio').append('<option value="" selected="selected">Selecione...</option>');
                
                $('#idConta option').remove();
                $('#idConta').append('<option value="" selected="selected">Selecione...</option>');
                
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

function listarConta() {

    var idCondominio = document.getElementById('idCondominio');

    if(idCondominio.value == '') {
    	
    	$('#idConta option').remove();
        $('#idConta').append('<option value="" selected="selected">Selecione...</option>');
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
	        url: 'RateioBO?acao=3' +
	             '&idCondominio=' + idCondominio.value
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	
	        	$('#idConta option').remove();
                $('#idConta').append('<option value="" selected="selected">Selecione...</option>');
                
	            var listConta = result;
	            if(listConta != null && listConta.length > 0) {
	                for(i = 0; i < listConta.length; i++) {
	                	var conta = listConta[i];
	                    $('#idConta').append('<option value=' + conta.idConta + '>' + conta.dtLeituraAnterior + ' à ' + conta.dtLeituraAtual + ' - Valor ' + conta.valor + ' - Consumo ' + conta.consumo + ' m3</option>');
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
	var idConta = document.getElementById('idConta');
	var divTable = document.getElementById('divTable');

	divAviso.innerHTML = '';
	idEmpresa.style.removeProperty('border');
	idCondominio.style.removeProperty('border');
	idConta.style.removeProperty('border');
	
    if(idEmpresa.value == '') {
    	
    	idEmpresa.style.borderColor = colorRed;
    }
    else if(idCondominio.value == '') {
    	
    	idCondominio.style.borderColor = colorRed;
    }
    else if(idConta.value == '') {
    	
    	idConta.style.borderColor = colorRed;
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
	        url: 'RateioBO?acao=4' +
	             '&idEmpresa=' + idEmpresa.value +
	             '&idCondominio=' + idCondominio.value +
	             '&idConta=' + idConta.value 
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	        	
	        	var cont = 1;
	        	var texto = '';
	        	var consumidor = '';
	        	var consumo = 0;
	            var listRelConsumoCondominio = result;
	            if(listRelConsumoCondominio != null && listRelConsumoCondominio.length > 0) {
	            	
	            	texto +=
            		"<form action='RateioBO?acao=5' method='post' accept-charset='iso-8859-1,utf-8' onsubmit='return validarForm()'>" +	
	            	"<table class='table table-hover table-striped'>" +
		            "	<thead>" +
		            "		<tr>" +
		            "			<th></th>" +
		            "			<th>Nº</th>" +
		            "			<th>Medidor</th>" +
		            "			<th>Endereço</th>" +
		            "			<th>Consumidor</th>" +
		            "			<th>Volume Inicial (m&#179;)</th>" +
		            "			<th>Volume Final (m&#179;)</th>" +
		            "			<th>Consumo</th>" +
		            "			<th>Obs</th>" +
		            "		</tr>" +
		            "	</thead>" +
		            "	<tbody id='myTable'>" ;
            		for(i = 0; i < listRelConsumoCondominio.length; i++) {
	                	
	                	var relConsumoCondominio = listRelConsumoCondominio[i];	                		                
	                	consumo = consumo + relConsumoCondominio.consumo;
	                	
	                	consumidor = '';
	                	var listRelUserMedidor = relConsumoCondominio.listRelUserMedidor;
	                	if(listRelUserMedidor != null && listRelUserMedidor.length > 0) {
	                		for(j = 0; j < listRelUserMedidor.length; j++) {
		                		var relUserMedidor = listRelUserMedidor[j];
		                		consumidor += relUserMedidor.cpfUser + " - " + relUserMedidor.nomeUser + "<br/>";
		                	}
	                	}
	                	else {
	                		consumidor = "<label class='text-danger'>SEM CONSUMIDOR VINCULADO</label>";
	                	}
	                	texto +=
    		            "		<tr>" +
    		            "			<td>" +
	        		    "               <small><input type='checkbox' id='idMedidor" + cont + "' name='idMedidor" + cont + "' value='" + relConsumoCondominio.idMedidor + "' checked='checked' /></small>" +
    		            "               <input type='hidden' id='idConta" + cont + "' name='idConta" + cont + "' value='" + idConta.value + "'>" +
    		            "               <input type='hidden' id='idEmpresa" + cont + "' name='idEmpresa" + cont + "' value='" + idEmpresa.value + "'>" +
    		            "               <input type='hidden' id='idCondominio" + cont + "' name='idCondominio" + cont + "' value='" + idCondominio.value + "'>" +    		          
    		            "               <input type='hidden' id='volumeInicio" + cont + "' name='volumeInicio" + cont + "' value='" + relConsumoCondominio.volumeInicio + "'>" +
    		            "               <input type='hidden' id='volumeFim" + cont + "' name='volumeFim" + cont + "' value='" + relConsumoCondominio.volumeFim + "'>" +
    		            "               <input type='hidden' id='consumo" + cont + "' name='consumo" + cont + "' value='" + relConsumoCondominio.consumo + "'>" +
    		            "           </td>" +
    		            "			<td><small>" + cont + "</small></td>" +
    		            "			<td><small>" + relConsumoCondominio.meterId + "</small></td>" +
    		            "			<td><small>" + relConsumoCondominio.endereco + " " + relConsumoCondominio.numero + " " + relConsumoCondominio.compl + "</small></td>" +
    		            "			<td><small>" + consumidor + "</small></td>" +
    		            "			<td><small>" + formatarTresDecimais(relConsumoCondominio.volumeInicio) + "</small></td>" +
    		            "			<td><small>" + formatarTresDecimais(relConsumoCondominio.volumeFim) + "</small></td>" +
    		            "			<td><small>" + formatarTresDecimais(relConsumoCondominio.consumo) + "</small></td>" +
    		            "		    <td align='right'><input type='text' class='form-control input-sm' name='obs" + cont + "' id='obs" + cont + "' maxlength='50'/></td>" +
    		            "		</tr>" ;
	                	
	                	cont = cont + 1;
	                }
		            texto +=
					"        <tr>" +
					"	         <td colspan='9' style='text-align: center'>" +				
					"		         <label>Consumo total no período em m&#179; (1m&#179; = 1.000 Litros): " + formatarTresDecimais(Number(consumo)) + "</label>" +
					"	         </td>" +
					"        </tr>" +
					"        <tr>" +
					"	         <td colspan='9' style='text-align: center'>" +					
					"                <input type='hidden' id='cont' name='cont' value='" + cont + "'>" +
					"			     <button type='submit' class='btn btn-warning'>" +
					"		             <i class='fa fa-bar-chart'></i> Executar Rateio" +
					"		         </button>" +					
					"	         </td>" +
					"        </tr>" +
		            "    </tbody>" +
		            "</table>" +
		            "</form>" ;	 
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

function validarForm() {
	
	var divAviso = document.getElementById('divAviso');
	var cont = document.getElementById('cont');

	divAviso.innerHTML = '';
	
	for(i = 1; i < Number(cont.value); i++) {

		var idMedidor = document.getElementById('idMedidor' + i);	
		var obs = document.getElementById('obs' + i);
		obs.style.removeProperty('border');
		if(idMedidor.checked == false && obs.value.trim() == '') {
			
			obs.style.borderColor = colorRed;
			exibirAviso('Preencha o campo de observações para medidores que não forem entrar no rateio');
			return false;
		}
	}
}

function exibirAviso(texto) {
	var divAviso = document.getElementById("divAviso");
	divAviso.innerHTML = "<div class='alert alert-danger'>" + texto + "</div>";
}