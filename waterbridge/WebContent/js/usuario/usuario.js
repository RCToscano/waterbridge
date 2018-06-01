
function validaForm(){
    var focar;
    var texto;
    var divAviso = document.getElementById("divAviso");
    var aviso = document.getElementById("aviso");
    var valida = true;
    
    
    if(document.getElementById("cpf").value != "") {
    	if(!consistenciaCPF(document.getElementById("cpf").value)) {
	        texto = "CPF informado inv&aacute;lido!";
	        focar = document.getElementById("cpf");
	        valida = false;
    	}
    }
    else if(validarDataAtual(document.getElementById("dtNascimento").value)) {
    	texto = "Data de Nascimento maior que Data Atual!";
        focar = document.getElementById("dtNascimento");
        valida = false;
    }
    else if(document.getElementById("street_number").value <= 0) {
    	texto = "Número do endereço inválido!";
        focar = document.getElementById("street_number");
        valida = false;
    }
    else if(document.getElementById("telefoneFixo").value != ''
    	&& document.getElementById("telefoneCelular").value != '') {
    	texto = "Informe pelo menos um telefone para contato!";
        focar = document.getElementById("telefoneFixo");
        valida = false;
    }
    else if(document.getElementById("telefoneFixo").value != '' 
    		&& document.getElementById("telefoneFixo").value.length < 14) {
    	texto = "Telefone informado inválido!";
        focar = document.getElementById("telefoneFixo");
        valida = false;
    }
    else if(document.getElementById("telefoneCelular").value != '' 
    	&& document.getElementById("telefoneCelular").value.length < 14) {
    	texto = "Celular informado inválido!";
    	focar = document.getElementById("telefoneCelular");
    	valida = false;
    }
    else if(document.getElementById("postal_code").value.length < 9) {
    	texto = "CEP informado inválido!";
    	focar = document.getElementById("postal_code");
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

function validaPerfil(){
    var focar;
    var texto;
    var divAviso = document.getElementById("divAviso");
    var aviso = document.getElementById("aviso");
    var valida = true;
    
    if(document.getElementById("cpf").value != "") {
    	if(!consistenciaCPF(document.getElementById("cpf").value)) {
	        texto = "CPF informado inv&aacute;lido!";
	        focar = document.getElementById("cpf");
	        valida = false;
    	}
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
    if(document.getElementById("postal_code").value.length < 9) {
    	texto = "CEP informado inválido!";
    	focar = document.getElementById("postal_code");
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

function validaSenha(){
    var focar;
    var texto;
    var divAviso = document.getElementById("divAviso");
    var aviso = document.getElementById("aviso");
    var valida = true;
    
    if(document.getElementById("novaSenha").value != document.getElementById("confSenha").value) {
    	texto = "Nova Senha e Confirmação de Nova Senha não conferem!";
        focar = document.getElementById("novaSenha");
        valida = false;
    }
    else if(document.getElementById("novaSenha").value.length < 6) {
    	texto = "Nova Senha deve conter no mínimo 6 caracteres!";
        focar = document.getElementById("novaSenha");
        valida = false;
    }
    else if(document.getElementById("novaSenha").value.length > 50) {
    	texto = "Nova Senha deve conter no máximo 50 caracteres!";
        focar = document.getElementById("novaSenha");
        valida = false;
    }
    	
    
    if (valida == false) {
    	divAviso.style.display = "block";
        aviso.innerHTML = texto;
    	focar.focus();
        return false;
    }
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