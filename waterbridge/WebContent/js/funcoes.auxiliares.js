window.addEventListener("beforeunload", function (e) {
	$(function(){
		$.ajax({
			url: 'Login?r=sessao',
		    async: true
		}).done(function () {
		    //nothing
		});
	})
});

$(window).on('beforeunload', function(){ 
	$(function(){
		$.ajax({
			url: 'Login?r=sessao',
		    async: true
		}).done(function () {
		    //nothing
		});
	})
});

function replaceSpecialChars(str) {
	
	var specialChars = [
	   {val:"a",let:"áàãâä"},
	   {val:"e",let:"éèêë"},
	   {val:"i",let:"íìîï"},
	   {val:"o",let:"óòõôö"},
	   {val:"u",let:"úùûü"},
	   {val:"c",let:"ç"},
	   {val:"A",let:"ÁÀÃÂÄ"},
	   {val:"E",let:"ÉÈÊË"},
	   {val:"I",let:"ÍÌÎÏ"},
	   {val:"O",let:"ÓÒÕÔÖ"},
	   {val:"U",let:"ÚÙÛÜ"},
	   {val:"C",let:"Ç"},
	   {val:"",let:"?!()"}
	];
	
   //var $spaceSymbol = '-';
   var $spaceSymbol = ' ';	
   var regex;
   var returnString = str;
   for (var i = 0; i < specialChars.length; i++) {
      regex = new RegExp("["+specialChars[i].let+"]", "g");
      returnString = returnString.replace(regex, specialChars[i].val);
      regex = null;
   }
   return returnString.replace(/\s/g,$spaceSymbol);
};

function validarCPF(cpf) {

    cpf = cpf.replace(/[^\d]+/g, '');
    
    if (cpf == '')
        return false;
    // Elimina CPFs invalidos conhecidos    
    if (cpf.length != 11 ||
            cpf == "00000000000" ||
            cpf == "11111111111" ||
            cpf == "22222222222" ||
            cpf == "33333333333" ||
            cpf == "44444444444" ||
            cpf == "55555555555" ||
            cpf == "66666666666" ||
            cpf == "77777777777" ||
            cpf == "88888888888" ||
            cpf == "99999999999")
        return false;
    // Valida 1o digito 
    add = 0;
    for (i = 0; i < 9; i ++)
        add += parseInt(cpf.charAt(i)) * (10 - i);
    rev = 11 - (add % 11);
    if (rev == 10 || rev == 11)
        rev = 0;
    if (rev != parseInt(cpf.charAt(9)))
        return false;
    // Valida 2o digito 
    add = 0;
    for (i = 0; i < 10; i ++)
        add += parseInt(cpf.charAt(i)) * (11 - i);
    rev = 11 - (add % 11);
    if (rev == 10 || rev == 11)
        rev = 0;
    if (rev != parseInt(cpf.charAt(10)))
        return false;
    return true;
}

function validarCNPJ(cnpj) {

	cnpj = cnpj.replace(/[^\d]+/g,'');
	
	if(cnpj == '') return false;
	
	if (cnpj.length != 14)
		return false;

	// Elimina CNPJs invalidos conhecidos
	if (cnpj == "00000000000000" || 
		cnpj == "11111111111111" || 
		cnpj == "22222222222222" || 
		cnpj == "33333333333333" || 
		cnpj == "44444444444444" || 
		cnpj == "55555555555555" || 
		cnpj == "66666666666666" || 
		cnpj == "77777777777777" || 
		cnpj == "88888888888888" || 
		cnpj == "99999999999999")
		return false;
		
	// Valida DVs
	tamanho = cnpj.length - 2
	numeros = cnpj.substring(0,tamanho);
	digitos = cnpj.substring(tamanho);
	soma = 0;
	pos = tamanho - 7;
	for (i = tamanho; i >= 1; i--) {
	  soma += numeros.charAt(tamanho - i) * pos--;
	  if (pos < 2)
			pos = 9;
	}
	resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	if (resultado != digitos.charAt(0))
		return false;
		
	tamanho = tamanho + 1;
	numeros = cnpj.substring(0,tamanho);
	soma = 0;
	pos = tamanho - 7;
	for (i = tamanho; i >= 1; i--) {
	  soma += numeros.charAt(tamanho - i) * pos--;
	  if (pos < 2)
			pos = 9;
	}
	resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	if (resultado != digitos.charAt(1))
		  return false;
		  
	return true;
}

function validarEmail(sEmail) {
    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    if (filter.test(sEmail)) {
        return true;
    }
    else {
        return false;
    }
}

function numberParaReal(numero) {
    var numero = numero.toFixed(2).split('.');
    //numero[0] = "R$ " + numero[0].split(/(?=(?:...)*$)/).join('.');
    numero[0] = "" + numero[0].split(/(?=(?:...)*$)/).join('.');
    return numero.join(',');
}

function nullParaVazio(texto) {
    
    if(texto == null) {
        
        return '';
    }
    
    return texto;
}

function numerico(input) {
    if ((event.keyCode < 48) || (event.keyCode > 57)) {
        if ((event.keyCode == 13)) {
        }
        else {
            event.returnValue = false;
        }
    }
}

function validaTamanho(campo, tamanho) {
	if ((campo.value.length+1) <= tamanho) {
		return true;
	}
	else {
		var input = campo.value;
		input = input.substring(0, (campo.value.length-1));
		campo.value = input;
	}
}

function mascaraCpf(input){
    
    if ((event.keyCode<48)||(event.keyCode>57)){
        event.returnValue = false; 
    }
    else{
        if(input.value.length == 3){
            input.value = input.value + ".";
        }
        else if(input.value.length == 7){
            input.value = input.value + ".";
        }
        else if(input.value.length == 11){
            input.value = input.value + "-";
        }
    }
}

function mascaraCnpj(input){
    
    if ((event.keyCode<48)||(event.keyCode>57)){
        event.returnValue = false; 
    }
    else{
        if(input.value.length == 2){
            input.value = input.value + ".";
        }
        else if(input.value.length == 6){
            input.value = input.value + ".";
        }
        else if(input.value.length == 10){
            input.value = input.value + "/";
        }
        else if(input.value.length == 15){
            input.value = input.value + "-";
        }
    }
}

function formatarTresDecimais(numero) {
    var numero = numero.toFixed(3).split('.');
    numero[0] = numero[0].split(/(?=(?:...)*$)/).join('.');
    return numero.join(',');
}

function formatarDuasDecimais(numero) {
    var numero = numero.toFixed(2).split('.');
    numero[0] = numero[0].split(/(?=(?:...)*$)/).join('.');
    return numero.join(',');
}

function substituirPonto(input) {
	return input.toString().replace(".",",");
}
