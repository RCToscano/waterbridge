
var colorRed = '#e52213';

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
	        url: 'UsuarioCondominioBO?acao=2' +
	             '&idEmpresa=' + idEmpresa.value
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	
	        	$('#idCondominio option').remove();
                $('#idCondominio').append('<option value="" selected="selected">Selecione...</option>');
                
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

function listarUsuarioCondominio() {
	
	var divAviso = document.getElementById('divAviso');
	var idEmpresa = document.getElementById('idEmpresa');
	var idCondominio = document.getElementById('idCondominio');
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
	        url: 'UsuarioCondominioBO?acao=3' +
	             '&idEmpresa=' + idEmpresa.value +
	             '&idCondominio=' + idCondominio.value 
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	        	
	        	var texto = '';
	            var listRelCondominio = result;
	            if(listRelCondominio != null && listRelCondominio.length > 0) {
	            	
	            	texto +=
	            	"<table class='table table-hover table-striped'>" +
		            "	<thead>" +
		            "		<tr>" +
		            "			<th>Nº</th>" +
		            "			<th>Empresa</th>" +
		            "			<th>Condomínio</th>" +
		            "			<th>Endereço</th>" +
		            "			<th>Usuários</th>" +
		            "			<th></th>" +
		            "		</tr>" +
		            "	</thead>" +
		            "	<tbody id='myTable'>" ;
            		for(i = 0; i < listRelCondominio.length; i++) {
	                	
	                	var relCondominio = listRelCondominio[i];
	                	texto +=
    		            "		<tr>" +
    		            "			<td><small>" + (i + 1) + "</small></td>" +
    		            "			<td><small>" + relCondominio.empresa + "</small></td>" +
    		            "			<td><small>" + relCondominio.nome + "</small></td>" +
    		            "			<td><small>" + relCondominio.endereco + " " + relCondominio.numero + " " + nullParaVazio(relCondominio.compl) + "</small></td>" +
    		            "			<td>" +
    		            "               <small>" +
    		            "                   <div id='divusercondominio" + (i + 1) + "'>" ;
	                	var listRelUserCondominio = relCondominio.listRelUserCondominio;	                	
	                	for(j = 0; j < listRelUserCondominio.length; j++) {
	                		
	                		var relUserCondominio = listRelUserCondominio[j];
	                		texto += relUserCondominio.cpfUser + " - " + relUserCondominio.nomeUser + " - " + relUserCondominio.situacao + "<br/>";
	                	}
    		            texto +=
    		            "                   </div>" +
    		            "               </small>" +
    		            "           </td>" +
    		            "			<td align='right'>" +
    		            "               <button type='button' class='btn btn-info btn-xs' onclick='exibirUsuarioCondominio(" + (i + 1) + "," + relCondominio.idCondominio + ")'>" +
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

function exibirUsuarioCondominio(cont, idCondominio) {
	
	var divUsuarioCondominioLista = document.getElementById('divUsuarioCondominioLista');
	
    $.ajax({
        url: 'UsuarioCondominioBO?acao=4' +
             '&idCondominio=' + idCondominio 
        ,
        type: "POST",
        dataType: 'json',
        success: function(result) {
        	
        	var texto = "<input type='hidden' id='idCondominio1' name='idCondominio1' value='" + idCondominio + "' />" ;
            var listRelUserCondominio = result;
            if(listRelUserCondominio != null && listRelUserCondominio.length > 0) {
            	
            	texto +=
            	"<table class='table table-hover table-striped'>" +
	            "	<thead>" +
	            "		<tr>" +
	            "			<th>Nº</th>" +
	            "			<th>Usuário</th>" +
	            "			<th>Cpf</th>" +
	            "			<th>Inicio</th>" +
	            "			<th>Fim</th>" +
	            "			<th>Obs</th>" +
	            "			<th></th>" +
	            "		</tr>" +
	            "	</thead>" +
	            "	<tbody id='myTable'>" ;
        		for(i = 0; i < listRelUserCondominio.length; i++) {
                	
                	var relUserCondominio = listRelUserCondominio[i];
                	texto +=
		            "		<tr>" +
		            "			<td><small>" + (i + 1) + "</small></td>" +
		            "			<td><small>" + relUserCondominio.nomeUser + "</small></td>" +
		            "			<td><small>" + relUserCondominio.cpfUser + "</small></td>" +
		            "			<td><small>" + relUserCondominio.dtInicio + "</small></td>" +
		            "			<td><small>" + nullParaVazio(relUserCondominio.dtFim) + "</small></td>" +
		            "			<td><small><input type='text' class='form-control input-sm' id='obs" + (i + 1) + "' value='" + nullParaVazio(relUserCondominio.obs) + "' maxlength='60'></small></td>" +
		            "			<td align='right'>" ;
		            if(relUserCondominio.situacao == 'A') {
		            	texto +=
		                "               <small><button type='button' class='btn btn-danger btn-xs' onclick='inativarUsuarioCondominio(" + (i + 1) + "," + relUserCondominio.idUserCondominio + ")'>Inativar</button></small>" ;
		            }
		            else {
		            	texto +=
			            "               <small><button type='button' class='btn btn-default btn-xs disabled' onclick='inativarUsuarioCondominio(" + (i + 1) + "," + relUserCondominio.idUserCondominio + ")'>Inativo</button></small>" ;
		            }
                	texto +=
		            "			</td>" +
		            "		</tr>" ;
                }
	            texto +=
	            "	</tbody>" +
	            "</table>" ;	 
            }
            
            divUsuarioCondominioLista.innerHTML = texto;
            
            $( function() {
        	    $("#divUsuarioCondominio").dialog({
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

function inserirUsuarioCondominio() {

	var avisoDivUsuarioCondominio = document.getElementById('avisoDivUsuarioCondominio');
	var divUsuarioCondominioLista = document.getElementById('divUsuarioCondominioLista');
	var idCondominio = document.getElementById('idCondominio1');
	var cpf = document.getElementById('cpf');

	avisoDivUsuarioCondominio.innerHTML = '';
	
	if(idCondominio.value == '') {
		
		avisoDivUsuarioCondominio.innerHTML = 'Selecione o condomínio';
	}
	else if(cpf.value == '') {
		
		avisoDivUsuarioCondominio.innerHTML = 'Informe o CPF';
	}
	else if(cpf.value.length < 14) {
		
		avisoDivUsuarioCondominio.innerHTML = 'O cpf informado é inválido';
	}
	else {
		
	    $.ajax({
	        url: 'UsuarioCondominioBO?acao=6' +
	             '&idCondominio=' + idCondominio.value +
	             '&cpf=' + cpf.value 
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	        	
	        	var texto = "<input type='hidden' id='idCondominio1' name='idCondominio1' value='" + idCondominio.value + "' />" ;
	        	var listObject = result;	     
	        	avisoDivUsuarioCondominio.innerHTML = listObject[0];
	            var listRelUserCondominio = listObject[1];
	            if(listRelUserCondominio != null && listRelUserCondominio.length > 0) {
	            	
	            	texto +=
	            	"<table class='table table-hover table-striped'>" +
		            "	<thead>" +
		            "		<tr>" +
		            "			<th>Nº</th>" +
		            "			<th>Usuário</th>" +
		            "			<th>Cpf</th>" +
		            "			<th>Inicio</th>" +
		            "			<th>Fim</th>" +
		            "			<th>Obs</th>" +
		            "			<th></th>" +
		            "		</tr>" +
		            "	</thead>" +
		            "	<tbody id='myTable'>" ;
	        		for(i = 0; i < listRelUserCondominio.length; i++) {
	                	
	                	var relUserCondominio = listRelUserCondominio[i];
	                	texto +=
			            "		<tr>" +
			            "			<td><small>" + (i + 1) + "</small></td>" +
			            "			<td><small>" + relUserCondominio.nomeUser + "</small></td>" +
			            "			<td><small>" + relUserCondominio.cpfUser + "</small></td>" +
			            "			<td><small>" + relUserCondominio.dtInicio + "</small></td>" +
			            "			<td><small>" + nullParaVazio(relUserCondominio.dtFim) + "</small></td>" +
			            "			<td><small><input type='text' class='form-control input-sm' id='obs" + (i + 1) + "' value='" + nullParaVazio(relUserCondominio.obs) + "' maxlength='60'></small></td>" +
			            "			<td align='right'>" ;
			            if(relUserCondominio.situacao == 'A') {
			            	texto +=
			                "               <small><button type='button' class='btn btn-danger btn-xs' onclick='inativarUsuarioCondominio(" + (i + 1) + "," + relUserCondominio.idUserCondominio + ")'>Inativar</button></small>" ;
			            }
			            else {
			            	texto +=
				            "               <small><button type='button' class='btn btn-default btn-xs disabled' onclick='inativarUsuarioCondominio(" + (i + 1) + "," + relUserCondominio.idUserCondominio + ")'>Inativo</button></small>" ;
			            }
	                	texto +=
			            "			</td>" +
			            "		</tr>" ;
	                }
		            texto +=
		            "	</tbody>" +
		            "</table>" ;	 
	            }
	            
	            divUsuarioCondominioLista.innerHTML = texto;
	            
	            $( function() {
	        	    $("#divUsuarioCondominio").dialog({
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

function inativarUsuarioCondominio(cont, idUserCondominio) {

	var avisoDivUsuarioCondominio = document.getElementById('avisoDivUsuarioCondominio');
	var divUsuarioCondominioLista = document.getElementById('divUsuarioCondominioLista');
	var idCondominio = document.getElementById('idCondominio1');
	var obs = document.getElementById('obs' + cont);

	avisoDivUsuarioCondominio.innerHTML = '';
	
	if(idCondominio.value == '') {
		
		avisoDivUsuarioCondominio.innerHTML = 'Selecione o condomínio';
	}
	else {
		
	    $.ajax({
	        url: 'UsuarioCondominioBO?acao=7' +
	             '&idCondominio=' + idCondominio.value +
	             '&idUserCondominio=' + idUserCondominio +
	             '&obs=' + replaceSpecialChars(obs.value) 
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	        	
	        	var texto = "<input type='hidden' id='idCondominio1' name='idCondominio1' value='" + idCondominio.value + "' />" ;
	            var listRelUserCondominio = result;
	            if(listRelUserCondominio != null && listRelUserCondominio.length > 0) {
	            	
	            	texto +=
	            	"<table class='table table-hover table-striped'>" +
		            "	<thead>" +
		            "		<tr>" +
		            "			<th>Nº</th>" +
		            "			<th>Usuário</th>" +
		            "			<th>Cpf</th>" +
		            "			<th>Inicio</th>" +
		            "			<th>Fim</th>" +
		            "			<th>Obs</th>" +
		            "			<th></th>" +
		            "		</tr>" +
		            "	</thead>" +
		            "	<tbody id='myTable'>" ;
	        		for(i = 0; i < listRelUserCondominio.length; i++) {
	                	
	                	var relUserCondominio = listRelUserCondominio[i];
	                	texto +=
			            "		<tr>" +
			            "			<td><small>" + (i + 1) + "</small></td>" +
			            "			<td><small>" + relUserCondominio.nomeUser + "</small></td>" +
			            "			<td><small>" + relUserCondominio.cpfUser + "</small></td>" +
			            "			<td><small>" + relUserCondominio.dtInicio + "</small></td>" +
			            "			<td><small>" + nullParaVazio(relUserCondominio.dtFim) + "</small></td>" +
			            "			<td><small><input type='text' class='form-control input-sm' id='obs" + (i + 1) + "' value='" + nullParaVazio(relUserCondominio.obs) + "' maxlength='60'></small></td>" +
			            "			<td align='right'>" ;
			            if(relUserCondominio.situacao == 'A') {
			            	texto +=
			                "               <small><button type='button' class='btn btn-danger btn-xs' onclick='inativarUsuarioCondominio(" + (i + 1) + "," + relUserCondominio.idUserCondominio + ")'>Inativar</button></small>" ;
			            }
			            else {
			            	texto +=
				            "               <small><button type='button' class='btn btn-default btn-xs disabled' onclick='inativarUsuarioCondominio(" + (i + 1) + "," + relUserCondominio.idUserCondominio + ")'>Inativo</button></small>" ;
			            }
	                	texto +=
			            "			</td>" +
			            "		</tr>" ;
	                }
		            texto +=
		            "	</tbody>" +
		            "</table>" ;	 
	            }
	            
	            divUsuarioCondominioLista.innerHTML = texto;
	            
	            $( function() {
	        	    $("#divUsuarioCondominio").dialog({
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