
var colorRed = '#e52213';

function validarForm() {
	
	var divAviso = document.getElementById("divAviso");
	var nome = document.getElementById("nome");
	var cnpTp = document.getElementById("cnpTp");
	var cnp = document.getElementById("cnp");
	var responsavel = document.getElementById("responsavel");
	var telFixo = document.getElementById("telFixo");
	var telCel = document.getElementById("telCel");
	var email = document.getElementById("email");
	var contratoNum = document.getElementById("contratoNum");
	var contaCiclo = document.getElementById("contaCiclo");
	var idEmpresa = document.getElementById("idEmpresa");
	var situacao = document.getElementById("situacao");
	var latitude = document.getElementById("latitude");
	var longitude = document.getElementById("longitude");
	var endereco = document.getElementById("route");//ENDERECO
	var numero = document.getElementById("street_number");//NUMERO
	var compl = document.getElementById("compl");
	var municipio = document.getElementById("locality");//MUNICIPIO
	var estado = document.getElementById("administrative_area_level_1");//ESTADO
	var cep = document.getElementById("postal_code");//CEP

	nome.style.removeProperty('border');
	cnpTp.style.removeProperty('border');
	cnp.style.removeProperty('border');
	responsavel.style.removeProperty('border');
	telFixo.style.removeProperty('border');
	telCel.style.removeProperty('border');
	email.style.removeProperty('border');
	contratoNum.style.removeProperty('border');
	contaCiclo.style.removeProperty('border');
	idEmpresa.style.removeProperty('border');
	situacao.style.removeProperty('border');
	endereco.style.removeProperty('border');
	latitude.style.removeProperty('border');
	longitude.style.removeProperty('border');
	numero.style.removeProperty('border');
	compl.style.removeProperty('border');
	municipio.style.removeProperty('border');
	estado.style.removeProperty('border');
	cep.style.removeProperty('border');
	
    if(nome.value.trim() == '') {

    	nome.style.borderColor = colorRed;
        exibirAviso('Informe o nome do condominio');
        nome.focus();
        return false;
    }
    else if(cnpTp.value.trim() == '') {

    	cnpTp.style.borderColor = colorRed;
    	exibirAviso('Selecine o tipo de condomínio');
    	cnpTp.focus();
        return false;
    }
    else if(cnpTp.value == '1' && cnp.value.trim() == '') {

    	cnp.style.borderColor = colorRed;
    	exibirAviso('Informe o CPF');
    	cnp.focus();
        return false;
    }
    else if(cnpTp.value == '1' && validarCPF(cnp.value.trim()) == false) {

    	cnp.style.borderColor = colorRed;
    	exibirAviso('O CPF digitado não é válido');
    	cnp.focus();
        return false;
    }
    else if(cnpTp.value == '2' && cnp.value.trim() == '') {

    	cnp.style.borderColor = colorRed;
    	exibirAviso('Informe o CNPJ');
    	cnp.focus();
        return false;
    }
    else if(cnpTp.value == '2' && validarCNPJ(cnp.value.trim()) == false) {

    	cnp.style.borderColor = colorRed;
    	exibirAviso('O CNPJ digitado não é válido');
    	cnp.focus();
        return false;
    }
    else if(responsavel.value.trim() == '') {

    	responsavel.style.borderColor = colorRed;
    	exibirAviso('Informe o nome do responsável');
    	responsavel.focus();
        return false;
    }
    else if(telFixo.value.trim() == '' && telCel.value.trim() == '') {

    	telFixo.style.borderColor = colorRed;
    	telCel.style.borderColor = colorRed;
    	exibirAviso('Informe pelo menos um telefone');
    	telFixo.focus();
        return false;
    }
    else if(telFixo.value.trim() != '' && telFixo.value.trim().length < 14) {

    	telFixo.style.borderColor = colorRed;
    	exibirAviso('O telefone fixo digitado não é válido');
    	telFixo.focus();
        return false;
    }
    else if(telCel.value.trim() != '' && telCel.value.trim().length < 15) {

    	telCel.style.borderColor = colorRed;
    	exibirAviso('O telefone celular digitado não é válido');
    	telCel.focus();
        return false;
    }
    else if(email.value.trim() == '') {

    	email.style.borderColor = colorRed;
    	exibirAviso('Informe o e-mail');
    	email.focus();
        return false;
    }
    else if(validarEmail(email.value.trim()) == false) {

    	email.style.borderColor = colorRed;
    	exibirAviso('O e-mail digitado não é válido');
    	email.focus();
        return false;
    }
    else if(contratoNum.value.trim() == '') {

    	contratoNum.style.borderColor = colorRed;
    	exibirAviso('Informe o número do contrato');
    	contratoNum.focus();
        return false;
    }
    else if(contaCiclo.value.trim() == '') {

    	contaCiclo.style.borderColor = colorRed;
    	exibirAviso('Informe o (dia) do ciclo da conta');
    	contaCiclo.focus();
        return false;
    }
    else if(isNaN(contaCiclo.value.trim())) {

    	contaCiclo.style.borderColor = colorRed;
    	exibirAviso('O campo ciclo da conta só pode conter números');
    	contaCiclo.focus();
        return false;
    }
    else if(Number(contaCiclo.value.trim()) < 1 || Number(contaCiclo.value.trim()) > 30 ) {

    	contaCiclo.style.borderColor = colorRed;
    	exibirAviso('O ciclo da conta deve ser maior ou igual a 1 e menor ou igual a 30');
    	contaCiclo.focus();
        return false;
    }
    else if(idEmpresa.value.trim() == '') {

    	idEmpresa.style.borderColor = colorRed;
    	exibirAviso('Selecione a empresa');
    	idEmpresa.focus();
        return false;
    }    
    else if(situacao.value.trim() == '') {

    	situacao.style.borderColor = colorRed;
    	exibirAviso('Selecione a situação');
    	situacao.focus();
        return false;
    }    
    else if(municipio.value.trim() == '') {

    	municipio.style.borderColor = colorRed;
    	exibirAviso('Informe o município');
    	municipio.focus();
        return false;
    }
    else if(estado.value.trim() == '') {

    	estado.style.borderColor = colorRed;
    	exibirAviso('Informe o estado');
    	estado.focus();
        return false;
    }
    else if(cep.value.trim() == '') {

    	cep.style.borderColor = colorRed;
    	exibirAviso('Informe o CEP');
    	cep.focus();
        return false;
    }
    else if(cep.value.length < 9) {
    	
    	cep.style.borderColor = colorRed;
    	exibirAviso('O CEP digitado não é válido');
    	cep.focus();
    	return false;
    }
    else if(endereco.value.trim() == '' && numero.value.trim() == '' && latitude.value.trim() == '' && longitude.value.trim() == '' ) {
    
    	exibirAviso('Informe o endereço ou latitude/longitude');
    	endereco.focus();
        return false;
    }
    else if(endereco.value.trim() != '' && numero.value.trim() == '') {
    	
    	numero.style.borderColor = colorRed;
    	exibirAviso('Informe o número');
    	numero.focus();
    	return false;
    }
    else if(endereco.value.trim() == '' && numero.value.trim() != '') {
    	
    	endereco.style.borderColor = colorRed;
    	exibirAviso('Informe o endereço');
    	endereco.focus();
    	return false;
    }
    else if(latitude.value.trim() != '' && longitude.value.trim() == '') {
    	
    	longitude.style.borderColor = colorRed;
    	exibirAviso('Informe a longitude');
    	longitude.focus();
    	return false;
    }
    else if(latitude.value.trim() == '' && longitude.value.trim() != '') {
    	
    	latitude.style.borderColor = colorRed;
    	exibirAviso('Informe a latitude');
    	latitude.focus();
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

function mascaraCnp() {
	var cnpTp = document.getElementById("cnpTp");
	if(cnpTp.value == 1) {
		$("#cnp").val("");
		$("#cnp").attr("placeholder", "999.999.999-99");
		$("#cnp").mask("999.999.999-99");	
	}
	else if(cnpTp.value == 2) {
		$("#cnp").val("");
		$("#cnp").attr("placeholder", "99.999.999/9999-99");
		$("#cnp").mask("99.999.999/9999-99");	
	}
}

function abrirMapa() {
    
    var latitude = document.getElementById("latitude");
    var longitude = document.getElementById("longitude");
    
    if(latitude.value.trim() != '' && longitude.value.trim() != '') {
        
        window.open('https://www.google.com/maps?q=loc:' + latitude.value + '+' + longitude.value, '_blank');
    }
}

function exibirAviso(texto) {
	var divAviso = document.getElementById("divAviso");
	divAviso.innerHTML = "<div class='alert alert-danger'>" + texto + "</div>";
}