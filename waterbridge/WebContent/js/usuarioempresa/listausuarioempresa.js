
var colorRed = '#e52213';

function listarUsuarioEmpresa() {
	
	var divAviso = document.getElementById('divAviso');
	var idEmpresa = document.getElementById('idEmpresa');
	var divTable = document.getElementById('divTable');

	divAviso.innerHTML = '';
	idEmpresa.style.removeProperty('border');
	
    if(idEmpresa.value == '') {
    	
    	idEmpresa.style.borderColor = colorRed;
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
	        url: 'UsuarioEmpresaBO?acao=2' +
	             '&idEmpresa=' + idEmpresa.value 
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	        	
	        	var texto = '';
	            var listRelEmpresa = result;
	            if(listRelEmpresa != null && listRelEmpresa.length > 0) {
	            	
	            	texto +=
	            	"<table class='table table-hover table-striped'>" +
		            "	<thead>" +
		            "		<tr>" +
		            "			<th>Nº</th>" +
		            "			<th>Empresa</th>" +
		            "			<th>CNPJ</th>" +
		            "			<th>Usuários</th>" +
		            "			<th></th>" +
		            "		</tr>" +
		            "	</thead>" +
		            "	<tbody id='myTable'>" ;
            		for(i = 0; i < listRelEmpresa.length; i++) {
	                	
	                	var relEmpresa = listRelEmpresa[i];
	                	texto +=
    		            "		<tr>" +
    		            "			<td><small>" + (i + 1) + "</small></td>" +
    		            "			<td><small>" + relEmpresa.nome + "</small></td>" +
    		            "			<td><small>" + relEmpresa.cnp + "</small></td>" +
    		            "			<td>" +
    		            "               <small>" +
    		            "                   <div id='divuserempresa" + (i + 1) + "'>" ;
	                	var listRelUserEmpresa = relEmpresa.listRelUserEmpresa;	                	
	                	for(j = 0; j < listRelUserEmpresa.length; j++) {
	                		
	                		var relUserEmpresa = listRelUserEmpresa[j];
	                		texto += relUserEmpresa.cpfUser + " - " + relUserEmpresa.nomeUser + " - " + relUserEmpresa.situacao + "<br/>";
	                	}
    		            texto +=
    		            "                   </div>" +
    		            "               </small>" +
    		            "           </td>" +
    		            "			<td align='right'>" +
    		            "               <button type='button' class='btn btn-info btn-xs' onclick='exibirUsuarioEmpresa(" + (i + 1) + "," + relEmpresa.idEmpresa + ")'>" +
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

function exibirUsuarioEmpresa(cont, idEmpresa) {
	
	var divUsuarioEmpresaLista = document.getElementById('divUsuarioEmpresaLista');
	
    $.ajax({
        url: 'UsuarioEmpresaBO?acao=3' +
             '&idEmpresa=' + idEmpresa 
        ,
        type: "POST",
        dataType: 'json',
        success: function(result) {
        	
        	var texto = "<input type='hidden' id='idEmpresa1' name='idEmpresa1' value='" + idEmpresa + "' />" ;
            var listRelUserEmpresa = result;
            if(listRelUserEmpresa != null && listRelUserEmpresa.length > 0) {
            	
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
        		for(i = 0; i < listRelUserEmpresa.length; i++) {
                	
                	var relUserEmpresa = listRelUserEmpresa[i];
                	texto +=
		            "		<tr>" +
		            "			<td><small>" + (i + 1) + "</small></td>" +
		            "			<td><small>" + relUserEmpresa.nomeUser + "</small></td>" +
		            "			<td><small>" + relUserEmpresa.cpfUser + "</small></td>" +
		            "			<td><small>" + relUserEmpresa.dtInicio + "</small></td>" +
		            "			<td><small>" + nullParaVazio(relUserEmpresa.dtFim) + "</small></td>" +
		            "			<td><small><input type='text' class='form-control input-sm' id='obs" + (i + 1) + "' value='" + nullParaVazio(relUserEmpresa.obs) + "' maxlength='60'></small></td>" +
		            "			<td align='right'>" ;
		            if(relUserEmpresa.situacao == 'A') {
		            	texto +=
		                "               <small><button type='button' class='btn btn-danger btn-xs' onclick='inativarUsuarioEmpresa(" + (i + 1) + "," + relUserEmpresa.idUserEmpresa + ")'>Inativar</button></small>" ;
		            }
		            else {
		            	texto +=
			            "               <small><button type='button' class='btn btn-default btn-xs disabled' onclick='inativarUsuarioEmpresa(" + (i + 1) + "," + relUserEmpresa.idUserEmpresa + ")'>Inativo</button></small>" ;
		            }
                	texto +=
		            "			</td>" +
		            "		</tr>" ;
                }
	            texto +=
	            "	</tbody>" +
	            "</table>" ;	 
            }
            
            divUsuarioEmpresaLista.innerHTML = texto;
            
            $( function() {
        	    $("#divUsuarioEmpresa").dialog({
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

function inserirUsuarioEmpresa() {

	var avisoDivUsuarioEmpresa = document.getElementById('avisoDivUsuarioEmpresa');
	var divUsuarioEmpresaLista = document.getElementById('divUsuarioEmpresaLista');
	var idEmpresa = document.getElementById('idEmpresa1');
	var cpf = document.getElementById('cpf');

	avisoDivUsuarioEmpresa.innerHTML = '';
	
	if(idEmpresa.value == '') {
		
		avisoDivUsuarioEmpresa.innerHTML = 'Selecione a empresa';
	}
	else if(cpf.value == '') {
		
		avisoDivUsuarioEmpresa.innerHTML = 'Informe o CPF';
	}
	else if(cpf.value.length < 14) {
		
		avisoDivUsuarioEmpresa.innerHTML = 'O cpf informado é inválido';
	}
	else {
		
	    $.ajax({
	        url: 'UsuarioEmpresaBO?acao=5' +
	             '&idEmpresa=' + idEmpresa.value +
	             '&cpf=' + cpf.value 
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	        	
	        	var texto = "<input type='hidden' id='idEmpresa1' name='idEmpresa1' value='" + idEmpresa.value + "' />" ;
	        	var listObject = result;	     
	        	avisoDivUsuarioEmpresa.innerHTML = listObject[0];
	            var listRelUserEmpresa = listObject[1];
	            if(listRelUserEmpresa != null && listRelUserEmpresa.length > 0) {
	            	
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
	        		for(i = 0; i < listRelUserEmpresa.length; i++) {
	                	
	                	var relUserEmpresa = listRelUserEmpresa[i];
	                	texto +=
			            "		<tr>" +
			            "			<td><small>" + (i + 1) + "</small></td>" +
			            "			<td><small>" + relUserEmpresa.nomeUser + "</small></td>" +
			            "			<td><small>" + relUserEmpresa.cpfUser + "</small></td>" +
			            "			<td><small>" + relUserEmpresa.dtInicio + "</small></td>" +
			            "			<td><small>" + nullParaVazio(relUserEmpresa.dtFim) + "</small></td>" +
			            "			<td><small><input type='text' class='form-control input-sm' id='obs" + (i + 1) + "' value='" + nullParaVazio(relUserEmpresa.obs) + "' maxlength='60'></small></td>" +
			            "			<td align='right'>" ;
			            if(relUserEmpresa.situacao == 'A') {
			            	texto +=
			                "               <small><button type='button' class='btn btn-danger btn-xs' onclick='inativarUsuarioEmpresa(" + (i + 1) + "," + relUserEmpresa.idUserEmpresa + ")'>Inativar</button></small>" ;
			            }
			            else {
			            	texto +=
				            "               <small><button type='button' class='btn btn-default btn-xs disabled' onclick='inativarUsuarioEmpresa(" + (i + 1) + "," + relUserEmpresa.idUserEmpresa + ")'>Inativo</button></small>" ;
			            }
	                	texto +=
			            "			</td>" +
			            "		</tr>" ;
	                }
		            texto +=
		            "	</tbody>" +
		            "</table>" ;	 
	            }
	            
	            divUsuarioEmpresaLista.innerHTML = texto;
	            
	            $( function() {
	        	    $("#divUsuarioEmpresa").dialog({
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

function inativarUsuarioEmpresa(cont, idUserEmpresa) {

	var avisoDivUsuarioEmpresa = document.getElementById('avisoDivUsuarioEmpresa');
	var divUsuarioEmpresaLista = document.getElementById('divUsuarioEmpresaLista');
	var idEmpresa = document.getElementById('idEmpresa1');
	var obs = document.getElementById('obs' + cont);

	avisoDivUsuarioEmpresa.innerHTML = '';
	
	if(idEmpresa.value == '') {
		
		avisoDivUsuarioEmpresa.innerHTML = 'Selecione a empresa';
	}
	else {
		
	    $.ajax({
	        url: 'UsuarioEmpresaBO?acao=6' +
	             '&idEmpresa=' + idEmpresa.value +
	             '&idUserEmpresa=' + idUserEmpresa +
	             '&obs=' + replaceSpecialChars(obs.value)
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	        	
	        	var texto = "<input type='hidden' id='idEmpresa1' name='idEmpresa1' value='" + idEmpresa.value + "' />" ;
	            var listRelUserEmpresa = result;
	            if(listRelUserEmpresa != null && listRelUserEmpresa.length > 0) {
	            	
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
	        		for(i = 0; i < listRelUserEmpresa.length; i++) {
	                	
	                	var relUserEmpresa = listRelUserEmpresa[i];
	                	texto +=
			            "		<tr>" +
			            "			<td><small>" + (i + 1) + "</small></td>" +
			            "			<td><small>" + relUserEmpresa.nomeUser + "</small></td>" +
			            "			<td><small>" + relUserEmpresa.cpfUser + "</small></td>" +
			            "			<td><small>" + relUserEmpresa.dtInicio + "</small></td>" +
			            "			<td><small>" + nullParaVazio(relUserEmpresa.dtFim) + "</small></td>" +
			            "			<td><small><input type='text' class='form-control input-sm' id='obs" + (i + 1) + "' value='" + nullParaVazio(relUserEmpresa.obs) + "' maxlength='60'></small></td>" +
			            "			<td align='right'>" ;
			            if(relUserEmpresa.situacao == 'A') {
			            	texto +=
			                "               <small><button type='button' class='btn btn-danger btn-xs' onclick='inativarUsuarioEmpresa(" + (i + 1) + "," + relUserEmpresa.idUserEmpresa + ")'>Inativar</button></small>" ;
			            }
			            else {
			            	texto +=
				            "               <small><button type='button' class='btn btn-default btn-xs disabled' onclick='inativarUsuarioEmpresa(" + (i + 1) + "," + relUserEmpresa.idUserEmpresa + ")'>Inativo</button></small>" ;
			            }
	                	texto +=
			            "			</td>" +
			            "		</tr>" ;
	                }
		            texto +=
		            "	</tbody>" +
		            "</table>" ;	 
	            }
	            
	            divUsuarioEmpresaLista.innerHTML = texto;
	            
	            $( function() {
	        	    $("#divUsuarioEmpresa").dialog({
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