
function validaForm() {

	var divAviso = document.getElementById('divAviso');
	var aviso = document.getElementById('aviso');
	var posicao = document.getElementById('posicao');
	
	divAviso.style.display = "none";
	aviso.innerHTML = "";
	
	if(posicao.value.trim() == '') {
		
		divAviso.style.display = "block";
		aviso.innerHTML = "Informe a posição do medidor";
		return false;
	}
	else if(document.getElementById("bateria").value <= 0) {
    	texto = "Validade Bateria informada inválida!";
        focar = document.getElementById("bateria");
        valida = false;
    }
	else if(Number(posicao.value) < 1 || Number(posicao.value) > 64 ) {
		
		divAviso.style.display = "block";
		aviso.innerHTML = "A posição do medidor só pode ser de 1 a 64";
		return false;
	}
}

function listarBridgeCadastro() {

    var idCondominio = document.getElementById('idCondominio');
    var bridge = document.getElementById('bridge');

    if(idCondominio.value == '') {
    	
    	$('#bridge option').remove();
        $('#bridge').append('<option value="" selected="selected">Selecione...</option>');
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
	        url: 'MedidorBO?acao=listarbridge' +
	             '&idCondominio=' + idCondominio.value
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	
	            var listBridge = result;
	            if(listBridge != null && listBridge.length > 0) {
	
	                $('#bridge option').remove();
	                $('#bridge').append('<option value="" selected="selected">Selecione...</option>');
	                for(i = 0; i < listBridge.length; i++) {
	                	var bridge = listBridge[i];
	                    $('#bridge').append('<option value=' + bridge.idBridge + ';' + bridge.deviceNum + '>' + bridge.deviceNum + '</option>');
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

function listarBridgeAlteracao(idBridge) {

    var idCondominio = document.getElementById('idCondominio');
    var bridge = document.getElementById('bridge');

    if(idCondominio.value == '') {
    	
    	$('#bridge option').remove();
        $('#bridge').append('<option value="" selected="selected">Selecione...</option>');
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
	        url: 'MedidorBO?acao=listarbridge' +
	             '&idCondominio=' + idCondominio.value
	        ,
	        type: "POST",
	        dataType: 'json',
	        success: function(result) {
	
	            var listBridge = result;
	            if(listBridge != null && listBridge.length > 0) {
	
	                $('#bridge option').remove();
	                $('#bridge').append('<option value="">Selecione...</option>');
	                for(i = 0; i < listBridge.length; i++) {
	                	var bridge = listBridge[i];
	                	if(bridge.idBridge == idBridge) {	
	                		$('#bridge').append('<option value=' + bridge.idBridge + ';' + bridge.deviceNum + ' selected="selected">' + bridge.deviceNum + '</option>');
	                	}
	                	else {
	                		$('#bridge').append('<option value=' + bridge.idBridge + ';' + bridge.deviceNum + '>' + bridge.deviceNum + '</option>');
	                	}	                    
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