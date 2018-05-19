
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
	        url: 'UsuarioMedidorBO?acao=2' +
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
	                    $('#idCondominio').append('<option value=' + condominio.idCondominio + '>' + condominio.nome + '</option>');
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
	        url: 'UsuarioMedidorBO?acao=3' +
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
	        url: 'UsuarioMedidorBO?acao=4' +
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

function listarUsuarioMedidor() {
	
	var divAviso = document.getElementById('divAviso');
	var idEmpresa = document.getElementById('idEmpresa');
	var idCondominio = document.getElementById('idCondominio');
	var idBridge = document.getElementById('idBridge');
	var idMedidor = document.getElementById('idMedidor');
	var divTable = document.getElementById('divTable');

	divAviso.innerHTML = '';
	idEmpresa.style.removeProperty('border');
	idCondominio.style.removeProperty('border');
	
    if(idEmpresa.value == '') {
    	
    	idEmpresa.style.borderColor = colorRed;
    }
    else if(idCondominio.value == '') {
    	
    	idCondominio.style.borderColor = colorRed;
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
	        url: 'UsuarioMedidorBO?acao=5' +
	             '&idEmpresa=' + idEmpresa.value +
	             '&idCondominio=' + idCondominio.value +
	             '&idBridge=' + idBridge.value +
	             '&idMedidor=' + idMedidor.value 
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	        	
	        	var texto = '';
	            var listRelUsuarioMedidor = result;
	            if(listRelUsuarioMedidor != null && listRelUsuarioMedidor.length > 0) {
	            	
	            	texto +=
	            	"<table class='table table-hover table-striped'>" +
		            "	<thead>" +
		            "		<tr>" +
		            "			<th>Nº</th>" +
		            "			<th>Empresa</th>" +
		            "			<th>Condomínio</th>" +
		            "			<th>Bridge</th>" +
		            "			<th>Medidor</th>" +
		            "			<th>Usuários</th>" +
		            "			<th></th>" +
		            "		</tr>" +
		            "	</thead>" +
		            "	<tbody id='myTable'>" ;
            		for(i = 0; i < listRelUsuarioMedidor.length; i++) {
	                	
	                	var relUsuarioMedidor = listRelUsuarioMedidor[i];
	                	texto +=
    		            "		<tr>" +
    		            "			<td><small>" + (i + 1) + "</small></td>" +
    		            "			<td><small>" + relUsuarioMedidor.empresa + "</small></td>" +
    		            "			<td><small>" + relUsuarioMedidor.condominio + "</small></td>" +
    		            "			<td><small>" + relUsuarioMedidor.deviceNum + "</small></td>" +
    		            "			<td><small>" + relUsuarioMedidor.meterId + "</small></td>" +
    		            "			<td><small></small></td>" +
    		            "			<td align='right'>" +
    		            "               <button type='button' class='btn btn-info btn-xs' onclick='vincularUsuarioMedidor(1)'>" +
    		        	"	                <span class='glyphicon glyphicon-user'></span> Add" +
    		        	"               </button>" +
    		            "			</td>" +
    		            "		</tr>" ;
	                }
		            texto +=
		            "	</tbody>" +
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

function vincularUsuarioMedidor(cont) {
	
	$( function() {
	    $("#divUsuarioMedidor").dialog({
	        height: 500,
	        scrollable: true,
	        width: 1000,
	        modal: false,
	        draggable: false
	    });
	});
}

function exibirAviso(texto) {
	var divAviso = document.getElementById("divAviso");
	divAviso.innerHTML = "<div class='alert alert-danger'>" + texto + "</div>";
}