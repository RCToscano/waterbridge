
window.onload = function iniciarForm() {

	if(document.getElementById("ensino6").checked === false){
		var radios = document.getElementsByName('radioConsegueLer');
		for (var i = 0; i< radios.length;  i++){
			radios[i].disabled = true;
		}
		radios = document.getElementsByName('radioConsegueEscrever');
		for (var i = 0; i< radios.length;  i++){
			radios[i].disabled = true;
		}
	}
	
	if(document.getElementById("banco1").checked === true){
		document.getElementById("descBanco").disabled = false;
	}
	else {
		document.getElementById("descBanco").disabled = true;
		document.getElementById("descBanco").value = '';
	}

	if(document.getElementById("internet1").checked === true){
		document.getElementById("checkWifi").disabled = false;
		document.getElementById("check3g").disabled = false;
	}
	else {
		document.getElementById("checkWifi").disabled = true;
		document.getElementById("check3g").disabled = true;
		document.getElementById("check3g").checked = false;
		document.getElementById("checkWifi").checked = false;
	}
	
	if(document.getElementById("checkOutro").checked === true){
		document.getElementById("outroDoenca").disabled = false;
	}
	else {
		document.getElementById("outroDoenca").disabled = true;
		document.getElementById("outroDoenca").value = '';
	}
	
	 moeda(document.getElementById("rendaFamilia"));
	 moeda(document.getElementById("valorDoacao"));
	 moeda(document.getElementById("valorAposentadoria"));
	 moeda(document.getElementById("valorPensao"));
	 moeda(document.getElementById("valorSeguroDesemp"));
	 moeda(document.getElementById("valorSemCarteira"));
	 moeda(document.getElementById("valorComCarteira"));
	 moeda(document.getElementById("valorBolsaFamilia"));
	 moeda(document.getElementById("valorOutros"));
	 moeda(document.getElementById("valorParcelaEnergia"));
	 moeda(document.getElementById("valorParcelaAgua"));
	
}

function validaForm(){
    var focar;
    var texto;
    var divAviso = document.getElementById("divAviso");
    var aviso = document.getElementById("aviso");
    var valida = true;
    
    
    if(document.getElementById("cpfCliente").value != "") {
    	if(!consistenciaCPF(document.getElementById("cpfCliente").value)) {
	        texto = "CPF informado invalido!";
	        focar = document.getElementById("cpfCliente");
	        valida = false;
    	}
    }
    
    else if(document.getElementById("cpfNTitular").value != "") {
    	if(!consistenciaCPF(document.getElementById("cpfNTitular").value)) {
	        texto = "CPF informado invalido!";
	        focar = document.getElementById("cpfNTitular");
	        valida = false;
    	}
    }
    
    if (valida == false) {
    	divAviso.style.display = "block";
        aviso.innerHTML = texto;
        focar.focus();
        return false;
    }
}

function verificaAnalfabeto() {
	if(document.getElementById("ensino6").checked === true){
		var radios = document.getElementsByName('radioConsegueLer');
		for (var i = 0; i< radios.length;  i++){
			radios[i].disabled = false;
		}
		radios = document.getElementsByName('radioConsegueEscrever');
		for (var i = 0; i< radios.length;  i++){
			radios[i].disabled = false;
		}
	}
	else {
		var radios = document.getElementsByName('radioConsegueLer');
		for (var i = 0; i< radios.length;  i++){
			radios[i].disabled = true;
			radios[i].checked = false;
		}
		radios = document.getElementsByName('radioConsegueEscrever');
		for (var i = 0; i< radios.length;  i++){
			radios[i].disabled = true;
			radios[i].checked = false;
		}
	}
}

function verificaContaBanco() {
	if(document.getElementById("banco1").checked === true){
		document.getElementById("descBanco").disabled = false;
	}
	else {
		document.getElementById("descBanco").disabled = true;
		document.getElementById("descBanco").value = '';
	}
}

function verificaAcessoInternet() {
	if(document.getElementById("internet1").checked === true){
		document.getElementById("checkWifi").disabled = false;
		document.getElementById("check3g").disabled = false;
	}
	else {
		document.getElementById("checkWifi").disabled = true;
		document.getElementById("check3g").disabled = true;
		document.getElementById("check3g").checked = false;
		document.getElementById("checkWifi").checked = false;
	}
}

function verificaOutraDoenca() {
	if(document.getElementById("checkOutro").checked === true){
		document.getElementById("outroDoenca").disabled = false;
	}
	else {
		document.getElementById("outroDoenca").disabled = true;
		document.getElementById("outroDoenca").value = '';
	}
}

function addCampo(){
	var qtde = new Number (document.getElementById("cont").value);  
    var conc = "";
    
    if(document.getElementById("grauMembro"+(qtde-1)).value == ''
    	|| document.getElementById("parentescoMembro"+(qtde-1)).value == '') {
    	return false;
    }
    
    conc =  "<div class='form-group'>" +
			"	<div class='col-sm-8'>" +
			"		<input type='text' class='form-control' id='grauMembro"+qtde+"' name='grauMembro"+qtde+"' placeholder='Membro Familia' value=''></input>" +
			"	</div>" +
			"	<div class='col-sm-4'>" +
			"		<input type='text' class='form-control' id='parentescoMembro"+qtde+"' name='parentescoMembro"+qtde+"' placeholder='Parentesco' value=''></input>" +
			"	</div>" +
			"	<label class='radio-inline'>" +
			"		<input type='radio' name='radioEnsinoMembro"+qtde+"' id='radioEnsino1Membro"+qtde+"' value='1' >Ensino Fundamental</input>" +
			"	</label>" +
			"	<label class='radio-inline'>" +
			"		<input type='radio' name='radioEnsinoMembro"+qtde+"' id='radioEnsino2Membro"+qtde+"' value='2' >Ensino M&eacute;dio</input>" +
			"	</label>" +
			"	<label class='radio-inline'>" +
			"		<input type='radio' name='radioEnsinoMembro"+qtde+"' id='radioEnsino3Membro"+qtde+"' value='3' >Especializa&ccedil;&atilde;o (Cursos T&eacute;cnicos)</input>" +
			"	</label>" +
			"	<label class='radio-inline'>" +
			"		<input type='radio' name='radioEnsinoMembro"+qtde+"' id='radioEnsino4Membro"+qtde+"' value='4' >Ensino Superior</input>" +
			"	</label>" +
			"	<label class='radio-inline'>" +
			"		<input type='radio' name='radioEnsinoMembro"+qtde+"' id='radioEnsino5Membro"+qtde+"' value='5' >P&oacute;s Gradua&ccedil;&atilde;o</input>" +
			"	</label>" +
			"	<label class='radio-inline'>" +
			"		<input type='radio' name='radioEnsinoMembro"+qtde+"' id='radioEnsino6Membro"+qtde+"' value='6' >N&atilde;o Alfabetizado</input>" +
			"	</label>" +
			"</div>";

    document.getElementById("cont").value = qtde+1;
    document.getElementById("aqui"+qtde).innerHTML = conc;

    //cria outra div na sequencia
    var parentGuest = document.getElementById("aqui"+qtde);
    var div = document.createElement("div");
    div.id = "aqui"+(qtde+1)+"";

    if (parentGuest.nextSibling) {
        parentGuest.parentNode.insertBefore(div, parentGuest.nextSibling);
    }
    else {
        parentGuest.parentNode.appendChild(div);
    }
}

function moeda(z){ 
	if ((event.keyCode < 48) || (event.keyCode > 57)) {
        if ((event.keyCode == 13)) {
        }
        else {
            event.returnValue = false;
        }
    }
	else {
    	v = z.value; 
    	v=v.replace(/\D/g,"") // permite digitar apenas numero 
    	v=v.replace(/(\d{1})(\d{15})$/,"$1.$2") // coloca ponto antes dos ultimos digitos 
    	v=v.replace(/(\d{1})(\d{11})$/,"$1.$2") // coloca ponto antes dos ultimos 13 digitos 
    	v=v.replace(/(\d{1})(\d{8})$/,"$1.$2") // coloca ponto antes dos ultimos 10 digitos 
    	v=v.replace(/(\d{1})(\d{5})$/,"$1.$2") // coloca ponto antes dos ultimos 7 digitos 
    	v=v.replace(/(\d{1})(\d{1,2})$/,"$1,$2") // coloca virgula antes dos ultimos 4 digitos 
    	z.value = v; 
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