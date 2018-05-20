
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
	            var listRelMedidor = result;
	            if(listRelMedidor != null && listRelMedidor.length > 0) {
	            	
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
            		for(i = 0; i < listRelMedidor.length; i++) {
	                	
	                	var relMedidor = listRelMedidor[i];
	                	texto +=
    		            "		<tr>" +
    		            "			<td><small>" + (i + 1) + "</small></td>" +
    		            "			<td><small>" + relMedidor.empresa + "</small></td>" +
    		            "			<td><small>" + relMedidor.condominio + "</small></td>" +
    		            "			<td><small>" + relMedidor.deviceNum + "</small></td>" +
    		            "			<td><small>" + relMedidor.meterId + "</small></td>" +
    		            "			<td>" +
    		            "               <small>" +
    		            "                   <div id='divusermedidor" + (i + 1) + "'>" ;
	                	var listRelUserMedidor = relMedidor.listRelUserMedidor;	                	
	                	for(j = 0; j < listRelUserMedidor.length; j++) {
	                		
	                		var relUserMedidor = listRelUserMedidor[j];
	                		texto += relUserMedidor.cpfUser + " - " + relUserMedidor.nomeUser + " - " + relUserMedidor.situacao + "<br/>";
	                	}
    		            texto +=
    		            "                   </div>" +
    		            "               </small>" +
    		            "           </td>" +
    		            "			<td align='right'>" +
    		            "               <button type='button' class='btn btn-info btn-xs' onclick='exibirUsuarioMedidor(" + (i + 1) + "," + relMedidor.idMedidor + ")'>" +
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

function exibirUsuarioMedidor(cont, idMedidor) {
	
	var divUsuarioMedidorLista = document.getElementById('divUsuarioMedidorLista');
	
    $.ajax({
        url: 'UsuarioMedidorBO?acao=6' +
             '&idMedidor=' + idMedidor 
        ,
        type: "POST",
        dataType: 'json',
        success: function(result) {
        	
        	var texto = "<input type='hidden' id='idMedidor1' name='idMedidor1' value='" + idMedidor + "' />" ;
            var listRelUserMedidor = result;
            if(listRelUserMedidor != null && listRelUserMedidor.length > 0) {
            	
            	texto +=
            	"<table class='table table-hover table-striped'>" +
	            "	<thead>" +
	            "		<tr>" +
	            "			<th>Nº</th>" +
	            "			<th>Usuário</th>" +
	            "			<th>Cpf</th>" +
	            "			<th>Inicio</th>" +
	            "			<th>Fim</th>" +
	            "			<th>Situação</th>" +
	            "			<th></th>" +
	            "		</tr>" +
	            "	</thead>" +
	            "	<tbody id='myTable'>" ;
        		for(i = 0; i < listRelUserMedidor.length; i++) {
                	
                	var relUserMedidor = listRelUserMedidor[i];
                	texto +=
		            "		<tr>" +
		            "			<td><small>" + (i + 1) + "</small></td>" +
		            "			<td><small>" + relUserMedidor.nomeUser + "</small></td>" +
		            "			<td><small>" + relUserMedidor.cpfUser + "</small></td>" +
		            "			<td><small>" + relUserMedidor.dtInicio + "</small></td>" +
		            "			<td><small>" + nullParaVazio(relUserMedidor.dtFim) + "</small></td>" +
		            "			<td><small>" + relUserMedidor.situacao + "</small></td>" +
		            "			<td align='right'>" ;
		            if(relUserMedidor.situacao == 'A') {
		            	texto +=
		                "               <small><button type='button' class='btn btn-danger btn-xs' onclick='inativarUsuarioMedidor(" + relUserMedidor.idUserMedidor + ")'>Inativar</button></small>" ;
		            }
		            else {
		            	texto +=
			            "               <small><button type='button' class='btn btn-default btn-xs disabled' onclick='inativarUsuarioMedidor(" + relUserMedidor.idUserMedidor + ")'>Inativo</button></small>" ;
		            }
                	texto +=
		            "			</td>" +
		            "		</tr>" ;
                }
	            texto +=
	            "	</tbody>" +
	            "</table>" ;	 
            }
            
            divUsuarioMedidorLista.innerHTML = texto;
            
            $( function() {
        	    $("#divUsuarioMedidor").dialog({
        	        height: 500,
        	        scrollable: true,
        	        width: 1000,
        	        modal: false,
        	        draggable: false
        	    });
        	});
        },
        error : function(){

            $.unblockUI();
            alert('erro');
        }
    });
}

function inserirUsuarioMedidor() {

	var avisoDivUsuarioMedidor = document.getElementById('avisoDivUsuarioMedidor');
	var divUsuarioMedidorLista = document.getElementById('divUsuarioMedidorLista');
	var idMedidor = document.getElementById('idMedidor1');
	var cpf = document.getElementById('cpf');

	avisoDivUsuarioMedidor.innerHTML = '';
	
	if(idMedidor.value == '') {
		
		avisoDivUsuarioMedidor.innerHTML = 'Selecione o medidor';
	}
	else if(cpf.value == '') {
		
		avisoDivUsuarioMedidor.innerHTML = 'Informe o CPF';
	}
	else if(cpf.value.length < 14) {
		
		avisoDivUsuarioMedidor.innerHTML = 'O cpf informado é inválido';
	}
	else {
		
	    $.ajax({
	        url: 'UsuarioMedidorBO?acao=8' +
	             '&idMedidor=' + idMedidor.value +
	             '&cpf=' + cpf.value 
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	        	
	        	var texto = "<input type='hidden' id='idMedidor1' name='idMedidor1' value='" + idMedidor.value + "' />" ;
	        	var listObject = result;	     
	        	avisoDivUsuarioMedidor.innerHTML = listObject[0];
	            var listRelUserMedidor = listObject[1];
	            if(listRelUserMedidor != null && listRelUserMedidor.length > 0) {
	            	
	            	texto +=
	            	"<table class='table table-hover table-striped'>" +
		            "	<thead>" +
		            "		<tr>" +
		            "			<th>Nº</th>" +
		            "			<th>Usuário</th>" +
		            "			<th>Cpf</th>" +
		            "			<th>Inicio</th>" +
		            "			<th>Fim</th>" +
		            "			<th>Situação</th>" +
		            "			<th></th>" +
		            "		</tr>" +
		            "	</thead>" +
		            "	<tbody id='myTable'>" ;
	        		for(i = 0; i < listRelUserMedidor.length; i++) {
	                	
	                	var relUserMedidor = listRelUserMedidor[i];
	                	texto +=
			            "		<tr>" +
			            "			<td><small>" + (i + 1) + "</small></td>" +
			            "			<td><small>" + relUserMedidor.nomeUser + "</small></td>" +
			            "			<td><small>" + relUserMedidor.cpfUser + "</small></td>" +
			            "			<td><small>" + relUserMedidor.dtInicio + "</small></td>" +
			            "			<td><small>" + nullParaVazio(relUserMedidor.dtFim) + "</small></td>" +
			            "			<td><small>" + relUserMedidor.situacao + "</small></td>" +
			            "			<td align='right'>" ;
			            if(relUserMedidor.situacao == 'A') {
			            	texto +=
			                "               <small><button type='button' class='btn btn-danger btn-xs' onclick='inativarUsuarioMedidor(" + relUserMedidor.idUserMedidor + ")'>Inativar</button></small>" ;
			            }
			            else {
			            	texto +=
				            "               <small><button type='button' class='btn btn-default btn-xs disabled' onclick='inativarUsuarioMedidor(" + relUserMedidor.idUserMedidor + ")'>Inativo</button></small>" ;
			            }
	                	texto +=
			            "			</td>" +
			            "		</tr>" ;
	                }
		            texto +=
		            "	</tbody>" +
		            "</table>" ;	 
	            }
	            
	            divUsuarioMedidorLista.innerHTML = texto;
	            
	            $( function() {
	        	    $("#divUsuarioMedidor").dialog({
	        	        height: 500,
	        	        scrollable: true,
	        	        width: 1000,
	        	        modal: false,
	        	        draggable: false
	        	    });
	        	});
	        },
	        error : function(){
	
	            $.unblockUI();
	            alert('erro');
	        }
	    });
	}
}

function inativarUsuarioMedidor(idUserMedidor) {

	var avisoDivUsuarioMedidor = document.getElementById('avisoDivUsuarioMedidor');
	var divUsuarioMedidorLista = document.getElementById('divUsuarioMedidorLista');
	var idMedidor = document.getElementById('idMedidor1');

	avisoDivUsuarioMedidor.innerHTML = '';
	
	if(idMedidor.value == '') {
		
		avisoDivUsuarioMedidor.innerHTML = 'Selecione o medidor';
	}
	else {
		
	    $.ajax({
	        url: 'UsuarioMedidorBO?acao=9' +
	             '&idMedidor=' + idMedidor.value +
	             '&idUserMedidor=' + idUserMedidor 
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	        	
	        	var texto = "<input type='hidden' id='idMedidor1' name='idMedidor1' value='" + idMedidor.value + "' />" ;
	            var listRelUserMedidor = result;
	            if(listRelUserMedidor != null && listRelUserMedidor.length > 0) {
	            	
	            	texto +=
	            	"<table class='table table-hover table-striped'>" +
		            "	<thead>" +
		            "		<tr>" +
		            "			<th>Nº</th>" +
		            "			<th>Usuário</th>" +
		            "			<th>Cpf</th>" +
		            "			<th>Inicio</th>" +
		            "			<th>Fim</th>" +
		            "			<th>Situação</th>" +
		            "			<th></th>" +
		            "		</tr>" +
		            "	</thead>" +
		            "	<tbody id='myTable'>" ;
	        		for(i = 0; i < listRelUserMedidor.length; i++) {
	                	
	                	var relUserMedidor = listRelUserMedidor[i];
	                	texto +=
			            "		<tr>" +
			            "			<td><small>" + (i + 1) + "</small></td>" +
			            "			<td><small>" + relUserMedidor.nomeUser + "</small></td>" +
			            "			<td><small>" + relUserMedidor.cpfUser + "</small></td>" +
			            "			<td><small>" + relUserMedidor.dtInicio + "</small></td>" +
			            "			<td><small>" + nullParaVazio(relUserMedidor.dtFim) + "</small></td>" +
			            "			<td><small>" + relUserMedidor.situacao + "</small></td>" +
			            "			<td align='right'>" ;
			            if(relUserMedidor.situacao == 'A') {
			            	texto +=
			                "               <small><button type='button' class='btn btn-danger btn-xs' onclick='inativarUsuarioMedidor(" + relUserMedidor.idUserMedidor + ")'>Inativar</button></small>" ;
			            }
			            else {
			            	texto +=
				            "               <small><button type='button' class='btn btn-default btn-xs disabled' onclick='inativarUsuarioMedidor(" + relUserMedidor.idUserMedidor + ")'>Inativo</button></small>" ;
			            }
	                	texto +=
			            "			</td>" +
			            "		</tr>" ;
	                }
		            texto +=
		            "	</tbody>" +
		            "</table>" ;	 
	            }
	            
	            divUsuarioMedidorLista.innerHTML = texto;
	            
	            $( function() {
	        	    $("#divUsuarioMedidor").dialog({
	        	        height: 500,
	        	        scrollable: true,
	        	        width: 1000,
	        	        modal: false,
	        	        draggable: false
	        	    });
	        	});
	        },
	        error : function(){
	
	            $.unblockUI();
	            alert('erro');
	        }
	    });
	}
}

function exibirAviso(texto) {
	var divAviso = document.getElementById("divAviso");
	divAviso.innerHTML = "<div class='alert alert-danger'>" + texto + "</div>";
}