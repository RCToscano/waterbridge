var colorRed = '#e52213';

function validaForm(){
    var focar;
    var texto;
    var divAviso = document.getElementById("divAviso");
    var aviso = document.getElementById("aviso");
    var valida = true;
    
    
    if(validarDataAtual(document.getElementById("dtLeituraAtual").value)) {
    	texto = "Data Leitura Atual maior que Data Atual!";
        focar = document.getElementById("dtLeituraAtual");
        valida = false;
    }
    else if(validarDataAtual(document.getElementById("dtLeituraAnterior").value)) {
    	texto = "Data Leitura Anterior maior que Data Atual!";
    	focar = document.getElementById("dtLeituraAnterior");
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
	        url: 'ContaBO?acao=1' +
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

function validarDataAtual(input) {
	var numero = input.split('/');
	var data = new Date(numero[2], (numero[1]-1), numero[0]);
	var dataAtual = new Date();

	if(data > dataAtual) {
		return true;
	}
	else {
		return false;
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
            return true;
        }
    }
    divArquivo.style.display = "block";
    document.getElementById("botao").disabled = true;
    el.innerHTML="Arquivo com formato inválido. Formatos aceitos: .jpg .png .bmp";
    return false;
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
					document.getElementById("botao").disabled = false;
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