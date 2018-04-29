
var colorRed = '#e52213';

function cadastrarBridge() {
    
	var divAviso = document.getElementById("divAviso");
	var deviceNum = document.getElementById("deviceNum");
	var dtAtivacao = document.getElementById("dtAtivacao");
	var validadeToken = document.getElementById("validadeToken");
	var tpAlimentacao = document.getElementById("tpAlimentacao");
	var custoMensal = document.getElementById("custoMensal");
	var taxaEnvio = document.getElementById("taxaEnvio");
	var situacao = document.getElementById("situacao");
	var descricao = document.getElementById("descricao");
    
	divAviso.innerHTML = "";
	deviceNum.style.removeProperty('border');
	dtAtivacao.style.removeProperty('border');
	validadeToken.style.removeProperty('border');
	tpAlimentacao.style.removeProperty('border');
	custoMensal.style.removeProperty('border');
	taxaEnvio.style.removeProperty('border');
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
    else if(tpAlimentacao.value.trim() == '0') {

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
    else if(isNaN(taxaEnvio.value.trim())) {

    	taxaEnvio.style.borderColor = colorRed;
    	exibirAviso('O campo taxa de envio só pode conter números');
        taxaEnvio.focus();
        return false;
    }
    else if(situacao.value.trim() == '0') {

    	situacao.style.borderColor = colorRed;
    	exibirAviso('Selecione a situação');
        situacao.focus();
        return false;
    }
    else if(descricao.value.trim() == '') {

    	descricao.style.borderColor = colorRed;
        exibirAviso('Preencha a descrição');
        descricao.focus();
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
        
//        $.ajax({
//            url: 'EscalaGer?acao=10',
//            data: $('#formrelatescala').serialize(),
//            type: "POST",
//            dataType: 'text',
//            success: function(texto){
//
//                document.getElementById('divrelatescala').innerHTML = texto;
//
//                //executar scripts dentro da div
//                var arr = document.getElementById('divscripttabela').getElementsByTagName('script')
//                for (var n = 0; n < arr.length; n++) {
//
//                    eval(arr[n].innerHTML);//run scripts inside div
//                }
//
//                $.unblockUI();
//            },
//            error : function(){
//
//                $.unblockUI();
//                alert('erro');
//            }
//        });
    }
}

function exibirAviso(texto) {
	var divAviso = document.getElementById("divAviso");
	divAviso.innerHTML = "<div class='alert alert-danger'>" + texto + "</div>";
}