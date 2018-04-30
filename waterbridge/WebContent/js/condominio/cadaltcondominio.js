

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