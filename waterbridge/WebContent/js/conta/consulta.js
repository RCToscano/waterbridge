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
	        url: 'RelatorioPressaoBO?acao=2' +
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


function listarConta() {
	
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
	        url: 'ContaBO?acao=pesquisar' +
	             '&idEmpresa=' + idEmpresa.value +
	             '&idCondominio=' + idCondominio.value
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	        	
	        	var texto = '';
	        	var consumo = 0;
	            var listRelConta = result;
	            if(listRelConta != null && listRelConta.length > 0) {
	            	document.getElementById("divAviso").style.display = "none";
	            	texto +=
	            	"<table class='table table-hover table-striped'>" +
		            "	<thead>" +
		            "		<tr>" +
		            "			<th>Nº</th>" +
		            "			<th>Data Início Medição</th>" +
		            "			<th>Data Fim Medição</th>" +
		            "			<th>Valor Total (R$)</th>" +
		            "			<th>Consumo Total (m³)</th>" +
		            "			<th>Detalhe</th>" +
		            "			<th>Fotos</th>" +
		            "			<th>Rateio</th>" +
		            "		</tr>" +
		            "	</thead>" +
		            "	<tbody id='myTable'>" ;
            		for(i = 0; i < listRelConta.length; i++) {
	                	
	                	var conta = listRelConta[i];	                		                
	                	texto +=
    		            "		<tr>" +
    		            "			<td>" + (i + 1) + "</small></td>" +
    		            "			<td>" + conta.dtLeituraAnterior + "</small></td>" +
    		            "			<td>" + conta.dtLeituraAtual + "</small></td>" +
    		            "			<td>" + formatarTresDecimais(conta.valor) + "</small></td>" +
    		            "			<td>" + formatarTresDecimais(conta.consumo) + "</small></td>" +
    		            "           <td>" +
				    	"	            <a href='ContaBO?acao=detalhe&idConta="+conta.idConta+"' target='_blank'>" +
						"					<button type='button' class='btn btn-info btn-sm' title='Clique para visualizar o detalhe da Conta'>" +
						"						<span class='glyphicon glyphicon-list-alt'></span>" +
						"					</button>" +
						"				</a>" +
    		            "		    </td>" +
	                	"           <td>" +
	                	"	            <a href='ContaBO?acao=foto&idConta="+conta.idConta+"' target='_blank'>" +
	                	"					<button type='button' class='btn btn-info btn-sm' title='Clique para visualizar a foto da conta'>" +
	                	"						<span class='glyphicon glyphicon-picture'></span>" +
	                	"					</button>" +
	                	"				</a>" +
	                	"		    </td>";
    		            if(conta.rateio > 0) {
    		            	texto +="   <td>" +
    		            	"	            <a href='RateioBO?acao=6&idConta="+conta.idConta+"' target='_blank'>" +
    		            	"					<button type='button' class='btn btn-info btn-sm' title='Clique para visualizar o Rateio'>" +
    		            	"						<span class='glyphicon glyphicon-search'></span>" +
    		            	"					</button>" +
    		            	"				</a>" +
    		            	"		    </td>";
    		            }
    		            else {
    		            	texto +="   <td>" +
    		            	"				<button type='button' class='btn btn-info btn-sm' disabled title='Não há Rateio para visualizar'>" +
    		            	"					<span class='glyphicon glyphicon-search'></span>" +
    		            	"				</button>" +
    		            	"		    </td>";
    		            }
    		            texto +="		</tr>" ;
	                	"    </tbody>" +
	                	"</table>" ;
	                }
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