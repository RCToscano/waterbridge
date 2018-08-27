var colorRed = '#e52213';


function listarConsumoMedidor() {
	
	var divAviso = document.getElementById("divAviso");
    var aviso = document.getElementById("aviso");
	var device = document.getElementById('device');
	var dtInicio = document.getElementById('dtInicio');
	var divTable = document.getElementById('divTable');

	divAviso.innerHTML = '';
	device.style.removeProperty('border');
	dtInicio.style.removeProperty('border');
	
    if(device.value == '') {
    	
    	device.style.borderColor = colorRed;
    }
    else if(dtInicio.value == '') {
    	
    	dtInicio.style.borderColor = colorRed;
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
	        url: 'ExclusaoConsumoMedidorBO?acao=2' +
	             '&device=' + device.value +
	             '&dtInicio=' + dtInicio.value 
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {

	        	var texto = '';
	        	var cont = 0;
	        	var colspan = 9;
	        	var consumo = 0;
	        	var bgColor = '';
	            var listRelConsumoMedidor = result;
	            if(listRelConsumoMedidor != null && listRelConsumoMedidor.length > 0) {

	            	texto +=
	            	"<form action='ExclusaoConsumoMedidorBO?acao=3' method='post' onsubmit='return validaForm()'> " +
	            	"<table class='table table-hover table-striped'>" +
		            "	<thead>" +
		            "		<tr>" +
		            "			<th>Nº</th>" +
		            "			<th></th>" +
		            "			<th>Data</th>" +
		            "			<th>Hora</th>" +
		            "			<th>Volume (m&#179;)</th>" +
	            	"			<th>Pressão (MCA)</th>" +
		            "			<th>Bateria (V)</th>" +
		            "			<th>Temperatura (ºC)</th>" +
		            "			<th></th>" +
		            "		</tr>" +
		            "	</thead>" +
		            "	<tbody id='myTable'>" ;
            		for(i = 0; i < listRelConsumoMedidor.length; i++) {
	                	cont = cont + 1;
	                	var relConsumoMedidor = listRelConsumoMedidor[i];	                		                
	                	consumo = consumo + relConsumoMedidor.consumo;
	                	if(relConsumoMedidor.alarm != 0) {
	                		bgColor = 'bgcolor="#f2dede"';
	                	}
	                	texto +=
    		            "		<tr>" +
    		            "			<td " + bgColor + "><small>" + (i + 1) + "</small></td>" +
    		            "			<td " + bgColor + "><input type='checkbox' class='hrchy-dt-checkboxes' name='check"+ i +"' id='check"+ i +"' value=''></td>" +
    		            "			<td " + bgColor + "><small>" + relConsumoMedidor.dtInsert.substring(0, 10) + "</small></td>" +
    		            "			<td " + bgColor + "><small>" + relConsumoMedidor.dtInsert.substring(11, 16) + "</small></td>" +
    		            "			<td " + bgColor + "><small>" + formatarTresDecimais(relConsumoMedidor.volume) + "</small></td>" +
    		            "			<td " + bgColor + "><small>" + formatarTresDecimais(relConsumoMedidor.pressure) + "</small></td>" +
    		            "			<td " + bgColor + "><small>" + substituirPonto(relConsumoMedidor.battery) + "</small></td>" +
    		            "			<td " + bgColor + "><small>" + relConsumoMedidor.temperature + "</small></td>" +
    		            "		    <td " + bgColor + " align='right'></td>" +
    		            "		</tr>" +
	                	"       <input type='hidden' name='idConsumo"+i+"' id='idConsumo"+ i +"' value="+ relConsumoMedidor.idConsumo +"> " ;
	                	bgColor = '';
	                }
		            texto +=
		            "    </tbody>" +
		            "</table>" +
		            "<div class='col-sm-12'>" +
					"	<div class='form-group'>" +
					"		<div class='col-md-12 text-center'>" +
					"			<button type='submit' class='btn btn-danger'>Excluir</button>" +
					"		</div>" +
					"	</div>" +
					"</div>" +
					"       <input type='hidden' name='cont' id='cont' value="+ cont +"> " ;
		            "</form>" ;
		            
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

function descricaoAlarme(alarme) {
	if(alarme == '1') {
		
	}
	else if(alarme == '2') {
		
	}
	else {
		
	}
}

function validaForm() {
	var cont = document.getElementById("cont");
	var divAviso = document.getElementById('divAviso');
	
	for(i = 0; i < cont.value; i++) {
		if (document.getElementById("check"+i).checked === true) {
			return true;
		}
	}
	divAviso.innerHTML ="<div class='alert alert-danger'>" +
						"  <strong><label id='aviso' name='aviso'/>Por favor, selecione pelo menos um registro</strong>" +
						"</div>";
	$('html, body').animate({ scrollTop: 0 }, 'fast');
	return false;
}


