
var timeAtualizarRelatorios;
function atualizarReservatorios() {	
	if(timeAtualizarRelatorios != null) {
		clearTimeout(timeAtualizarRelatorios);
	}
	$.ajax({
        url: 'MapaConsumoPressaoBO?acao=2',
        type: "POST",
        dataType: 'json',
        success: function(result) {
            var listRelMapaConsumoPressao = result;            
            clearMarkers()
            if(listRelMapaConsumoPressao != null && listRelMapaConsumoPressao.length > 0) {	
            	
            	var cont = 1;

        		for(i = 0; i < listRelMapaConsumoPressao.length; i++) {        					                	
                	var relMapaConsumoPressao = listRelMapaConsumoPressao[i];                	
                	addPontoDispositivo(map,relMapaConsumoPressao);
                	cont = cont + 1;                	
                	//alert('relMapaConsumoPressao ' + relMapaConsumoPressao.device);
                }
            }		        
        },
        error : function() {		
            alert('Falha ao buscar informacoes');
            //window.location = "http://www.waterbridge.com.br/";
        }
    });
	
	timeAtualizarRelatorios = setTimeout(function() {
		atualizarReservatorios();
	}, 600000);
}