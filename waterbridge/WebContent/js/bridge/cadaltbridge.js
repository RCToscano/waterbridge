
var colorRed = '#e52213';

function validarForm() {
    
	var divAviso = document.getElementById("divAviso");
	var deviceNum = document.getElementById("deviceNum");
	var dtAtivacao = document.getElementById("dtAtivacao");
	var validadeToken = document.getElementById("validadeToken");
	var tpAlimentacao = document.getElementById("tpAlimentacao");
	var custoMensal = document.getElementById("custoMensal");
	var taxaEnvio = document.getElementById("taxaEnvio");
	var idBridgeTp = document.getElementById("idBridgeTp");
	var idCondominio = document.getElementById("idCondominio");
	
	var minimoPressaoBaixa = document.getElementById("minimoPressaoBaixa");
	var minimoPressaoNormal = document.getElementById("minimoPressaoNormal");
	var maximoPressaoNormal = document.getElementById("maximoPressaoNormal");
	var maximoPressaoAlta = document.getElementById("maximoPressaoAlta");
	
	var situacao = document.getElementById("situacao");
	var descricao = document.getElementById("descricao");
    
	divAviso.innerHTML = "";
	deviceNum.style.removeProperty('border');
	dtAtivacao.style.removeProperty('border');
	validadeToken.style.removeProperty('border');
	tpAlimentacao.style.removeProperty('border');
	custoMensal.style.removeProperty('border');
	taxaEnvio.style.removeProperty('border');
	idBridgeTp.style.removeProperty('border');
	idCondominio.style.removeProperty('border');
	
	minimoPressaoBaixa.style.removeProperty('border');
	minimoPressaoNormal.style.removeProperty('border');
	maximoPressaoNormal.style.removeProperty('border');
	maximoPressaoAlta.style.removeProperty('border');
	
	situacao.style.removeProperty('border');
	descricao.style.removeProperty('border');

    if(deviceNum.value.trim() == '') {

    	deviceNum.style.borderColor = colorRed;
        exibirAviso('Informe o número do device');
        deviceNum.focus();
        return false;
    }
    else if(dtAtivacao.value.trim() == '') {

    	dtAtivacao.style.borderColor = colorRed;
    	exibirAviso('Informe a data de ativação');
        dtAtivacao.focus();
        return false;
    }
    else if(validadeToken.value.trim() == '') {

    	validadeToken.style.borderColor = colorRed;
    	exibirAviso('Informe a validade do token');
        validadeToken.focus();
        return false;
    }
    else if(tpAlimentacao.value.trim() == '') {

    	tpAlimentacao.style.borderColor = colorRed;
    	exibirAviso('Informe o tipo de alimentação');
        tpAlimentacao.focus();
        return false;
    }
    else if(custoMensal.value.trim() == '') {

    	custoMensal.style.borderColor = colorRed;
    	exibirAviso('Informe o custo mensal');
        custoMensal.focus();
        return false;
    }
    else if(taxaEnvio.value.trim() == '') {

    	taxaEnvio.style.borderColor = colorRed;
    	exibirAviso('Informe a taxa de envio');
        taxaEnvio.focus();
        return false;
    }
    else if(taxaEnvio.value.trim() == '') {

    	idBridgeTp.style.borderColor = colorRed;
    	exibirAviso('Informe o tipo de bridge');
    	idBridgeTp.focus();
        return false;
    }
    else if(isNaN(taxaEnvio.value.trim())) {

    	taxaEnvio.style.borderColor = colorRed;
    	exibirAviso('O campo taxa de envio só pode conter números');
        taxaEnvio.focus();
        return false;
    }
    else if(idCondominio.value.trim() == '') {

    	situacao.style.borderColor = colorRed;
    	exibirAviso('Selecione o condomínio');
        situacao.focus();
        return false;
    }
    
    else if(idBridgeTp.value == '2'
    		&& minimoPressaoBaixa.value.trim() == '') {

    	minimoPressaoBaixa.style.borderColor = colorRed;
    	exibirAviso('Informe todos os limites de pressão');
    	minimoPressaoBaixa.focus();
        return false;
    }
    else if(idBridgeTp.value == '2'
			&& minimoPressaoNormal.value.trim() == '') {
	
    	minimoPressaoNormal.style.borderColor = colorRed;
		exibirAviso('Informe todos os limites de pressão');
		minimoPressaoNormal.focus();
	    return false;
	}
    else if(idBridgeTp.value == '2'
			&& maximoPressaoNormal.value.trim() == '') {
	
    	maximoPressaoNormal.style.borderColor = colorRed;
		exibirAviso('Informe todos os limites de pressão');
		maximoPressaoNormal.focus();
	    return false;
	}
    else if(idBridgeTp.value == '2'
			&& maximoPressaoAlta.value.trim() == '') {
	
    	maximoPressaoAlta.style.borderColor = colorRed;
		exibirAviso('Informe todos os limites de pressão');
		maximoPressaoAlta.focus();
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

function exibirDivLimitesPressao() {
	
	var idBridgeTp = document.getElementById("idBridgeTp");
	var divLimitesPressao = document.getElementById("divLimitesPressao");
	
	if(idBridgeTp.value == 2 || idBridgeTp.value == 4) {
		
		//divLimitesPressao.style.display = 'block';
		$("#divLimitesPressao").fadeIn(300);
	}
	else {
		
		//divLimitesPressao.style.display = 'none';
		$("#divLimitesPressao").fadeOut(300);
	}
}

function exibirAviso(texto) {
	var divAviso = document.getElementById("divAviso");
	divAviso.innerHTML = "<div class='alert alert-danger'>" + texto + "</div>";
}