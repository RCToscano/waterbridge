
function exibirBlock() {
	
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

