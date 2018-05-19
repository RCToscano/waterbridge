
function validaForm() {
	var focar;
    var texto;
    var divAviso = document.getElementById("divAviso");
    var aviso = document.getElementById("aviso");
    var valida = true;
	var id = document.getElementById('id');
	var logo = document.getElementById('logo');
	
	divAviso.style.display = "none";
	aviso.innerHTML = "";
	
	if(id.value == '' && logo.value == '') {
		texto = "Por favor, selecione o logo da empresa!";
        focar = document.getElementById("logo");
        valida = false;
	}
	if(document.getElementById("street_number").value <= 0){
    	texto = "Número do endereço inválido!";
        focar = document.getElementById("street_number");
        valida = false;
    }
    if(document.getElementById("telefoneFixo").value != '' 
    		&& document.getElementById("telefoneFixo").value.length < 14) {
    	texto = "Telefone informado inválido!";
        focar = document.getElementById("telefoneFixo");
        valida = false;
    }
    if(document.getElementById("telefoneCelular").value != '' 
    	&& document.getElementById("telefoneCelular").value.length < 14) {
    	texto = "Celular informado inválido!";
    	focar = document.getElementById("telefoneCelular");
    	valida = false;
    }
	
	if (valida == false) {
    	divAviso.style.display = "block";
        aviso.innerHTML = texto;
    	focar.focus();
    	$('html, body').animate({ scrollTop: 0 }, 'fast');
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


function validate_fileupload(fileName) {
	var el = document.getElementById("feedback");
	var divArquivo = document.getElementById("divArquivo");
    var allowed_extensions = new Array("jpg","png","bmp");
    var file_extension = fileName.split('.').pop().toLowerCase();

    for(var i = 0; i <= allowed_extensions.length; i++) {
        if(allowed_extensions[i]==file_extension) {
            divArquivo.style.display = "none";
            validar_dimensao();
            return true;
        }
    }
    divArquivo.style.display = "block";
    el.innerHTML="Arquivo com formato inválido. Formatos aceitos: .jpg .png .bmp";
    return false;
}

function validar_dimensao() {
	var el = document.getElementById("feedback");
	var divArquivo = document.getElementById("divArquivo");
	window.URL = window.URL || window.webkitURL;
	var fileInput = document.getElementById("logo");
	file = fileInput.files && fileInput.files[0];
	
	if(file) {
        var img = new Image();
        img.src = window.URL.createObjectURL( file );
        
        img.onload = function() {
            var width = img.naturalWidth,
                height = img.naturalHeight;

            window.URL.revokeObjectURL( img.src );

            if(width <= 122 && height <= 35 ) {
                //OK
            }
            else {
            	divArquivo.style.display = "block";
	            el.innerHTML="Arquivo com dimensão maior que o permitido. Dimensão máxima: 122x35";
            }
        };
        
        if(file.size > 3000000) {
        	divArquivo.style.display = "block";
            el.innerHTML="Arquivo maior que o permitido. Tamanho máximo: 3MB";
        }
        
    }
    else { //No file was input or browser doesn't support client side reading
        
    }
}

function bs_input_file() {
	$(".input-file").before(
		function() {
			if ( ! $(this).prev().hasClass('input-ghost') ) {
				var element = $("<input type='file' class='input-ghost' style='visibility:hidden; height:0' id='logo' name='logo'>");
				element.attr("name",$(this).attr("name"));
				element.change(function(){
					element.next(element).find('input').val((element.val()).split('\\').pop());
					validate_fileupload(element.val());
				});
				$(this).find("button.btn-choose").click(function(){
					element.click();
				});
				$(this).find("button.btn-reset").click(function(){
					document.getElementById("divArquivo").style.display = "none";
					element.val(null);
					$(this).parents(".input-file").find('input').val('');
				});
				$(this).find('input').css("cursor","pointer");
				$(this).find('input').mousedown(function() {
					$(this).parents('.input-file').prev().click();
					return false;
				});
				return element;
			}
		}
	);
}

$(function() {
	bs_input_file();
});

