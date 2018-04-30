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
function replaceSpecialChars(str) {
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

