
var colorRed = '#e52213';

function validarForm() {
    
	var divAviso = document.getElementById("divAviso");
	var fabricNome = document.getElementById("fabricNome");
	var situacao = document.getElementById("situacao");
    
	divAviso.innerHTML = "";
	fabricNome.style.removeProperty('border');
	situacao.style.removeProperty('border');

    if(fabricNome.value.trim() == '') {

    	fabricNome.style.borderColor = colorRed;
        exibirAviso('Informe o nome do fabricante');
        fabricNome.focus();
        return false;
    }
    else if(situacao.value.trim() == '') {

    	situacao.style.borderColor = colorRed;
    	exibirAviso('Selecione a situação');
        situacao.focus();
        return false;
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
    }
}

function exibirAviso(texto) {
	var divAviso = document.getElementById("divAviso");
	divAviso.innerHTML = "<div class='alert alert-danger'>" + texto + "</div>";
}