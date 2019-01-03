
function validaForm(){
    var focar;
    var texto;
    var divAviso = document.getElementById("divAviso");
    var aviso = document.getElementById("aviso");
    var valida = true;
    

    if (document.getElementById("usuario").value == ""
		&& document.getElementById("cpf").value == ""
		&& document.getElementById("endereco").value == ""
    	&& document.getElementById("perfil").value == "") {

    	texto = "Por favor, informe pelo menos um campo para consultar!";
		focar = document.getElementById("usuario");
		valida = false;
	}
    
    else if(document.getElementById("cpf").value != "") {
    	if(!consistenciaCPF(document.getElementById("cpf").value)) {
    		texto = "CPF informado inv&aacute;lido!";
    		focar = document.getElementById("cpf");
    		valida = false;
    	}
    }
    
    if (valida == false) {
    	divAviso.style.display = "block";
        aviso.innerHTML = texto;
    	focar.focus();
    	$('html, body').animate({ scrollTop: 0 }, 'fast');
        return false;
    }
}

function verificaUsuario() {
	document.getElementById("usuario").disabled = false;
	document.getElementById("cpf").disabled = false;
	document.getElementById("endereco").disabled = false;
	document.getElementById("perfil").disabled = false;
	
	if(document.getElementById("usuario").value != "") {
		document.getElementById("usuario").disabled = false;
		document.getElementById("cpf").disabled = true;
		document.getElementById("endereco").disabled = true;
		document.getElementById("perfil").disabled = true;
	}
	else if(document.getElementById("cpf").value != "") {
		document.getElementById("cpf").disabled = false;
		document.getElementById("usuario").disabled = true;
		document.getElementById("endereco").disabled = true;
		document.getElementById("perfil").disabled = true;
	}
	else if(document.getElementById("endereco").value != "") {
		document.getElementById("endereco").disabled = false;
		document.getElementById("usuario").disabled = true;
		document.getElementById("cpf").disabled = true;
	}
	else if(document.getElementById("perfil").value != "") {
		document.getElementById("perfil").disabled = false;
		document.getElementById("endereco").disabled = true;
		document.getElementById("usuario").disabled = true;
		document.getElementById("cpf").disabled = true;
	}
}

function enviarAcesso(cont,idUser) {

	var tdenvio = document.getElementById("divAviso");
	
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
        url: 'UsuarioBO?acao=enviaracesso' +
             '&idUser=' + idUser
        ,
        type: "POST",
        dataType: 'json',
        success: function(result) {

            var envio = result;           
            if(envio != null && envio.trim() != '') {
            	$('#tdenvio' + cont).html(envio);
            }
            $.unblockUI();
        },
        error : function(){

            $.unblockUI();
            alert('Falha no envio. Tente novamente');
        }
    });
}

function consistenciaCPF(campo) {
    cpf = campo.replace(/\./g, "").replace(/\-/g, "");
    erro = new String;

    if (cpf.length < 11)
        erro += "Por favor informe o CPF corretamente";
    var nonNumbers = /\D/;
    if (cpf === "00000000000" || cpf === "11111111111"
            || cpf === "22222222222" || cpf === "33333333333"
            || cpf === "44444444444" || cpf === "55555555555"
            || cpf === "66666666666" || cpf === "77777777777"
            || cpf === "88888888888" || cpf === "99999999999") {

        erro += "Numero de CPF invalido"
    }
    var a = [];
    var b = new Number;
    var c = 11;
    for (i = 0; i < 11; i++) {
        a[i] = cpf.charAt(i);
        if (i < 9)
            b += (a[i] * --c);
    }
    if ((x = b % 11) < 2) {
        a[9] = 0
    } else {
        a[9] = 11 - x
    }
    b = 0;
    c = 11;
    for (y = 0; y < 10; y++)
        b += (a[y] * c--);
    if ((x = b % 11) < 2) {
        a[10] = 0;
    } else {
        a[10] = 11 - x;
    }
    if ((cpf.charAt(9) != a[9]) || (cpf.charAt(10) != a[10])) {
        erro += "Numero de CPF invalido";
    }
    if (erro.length > 0) {
        return false;
    }
    else {
    	return true;
    }
}