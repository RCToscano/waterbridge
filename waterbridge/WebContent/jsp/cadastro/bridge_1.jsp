<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<title>WaterBridge</title>	
	<link rel="icon" type="image/png" href="./images/favicon.ico"/>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-2.2.4.js" ></script>	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/locale/pt-br.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
    
    <link href="./css/menucustomcolor.css" rel="stylesheet"/>
    <link href="./css/footercustom.css" rel="stylesheet"/>   
    	
   	<script>
		$(function () {
	   		//$("#telefone").mask("(99) 99999-9999");
		});
	</script>	
</head>
<body>
	<jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
	<div class="container">
		<h3>Cadastro de Bridge</h3>
		<form role="form" id="form1" action="#" method="POST" class="form-horizontal" accept-charset="iso-8859-1,utf-8">
			<div class="form-group">
			    <div class="col-sm-3">
					<label class="control-label" for="name">Device N&deg;:</label>
					<input type="text" class="form-control" id="device" name="device" value="" required/>
				</div>
				<div class="col-sm-3">
					<label>Data de Ativação</label>
					<input type='text' class="form-control" name='dtAtivacao' id='dtAtivacao' value="" />
                    <script type="text/javascript">
                        $(function () {
                            $('#dtAtivacao').datetimepicker({
                                //inline: true,
                                sideBySide: true
                            });
                        });
                    </script>
				</div>
				<div class="col-sm-3">
	            	<label>Validade do Token</label>
                    <input type='text' class="form-control" name='validToken' id='validToken' value="" />
                    <script type="text/javascript">
                        $(function () {
                            $('#validToken').datetimepicker({
                                //inline: true,
                                //sideBySide: true
                                format: 'DD/MM/YYYY'
                            });
                        });
                    </script>
				</div>
				<div class="col-sm-3">
					<label for="sel1">Tipo de Alimentação</label> 
					<select class="form-control" name="tpAlimentacao" id="tpAlimentacao" required>
						<option value="" selected>Selecione...</option>
						<option value="1">Bateria</option>
						<option value="2">Solar</option>
						<option value="3">AC</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-3">
					<label class="control-label" for="name">Custo Mensal:</label>
					<input type="text" class="form-control" id="custo" name="custo" value="" required/>
				</div>
				<div class="col-sm-3">
					<label class="control-label" for="name">Taxa de Envio Diário:</label>
					<input type="text" class="form-control" id="taxa" name="taxa" value="" required/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12">
					<label class="control-label" for="descricao">Descri&ccedil;&atilde;o:</label>
					<textarea class="form-control" rows="3" name="descricao" id="descricao"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<button type="submit" class="btn btn-primary">Cadastrar</button>
				</div>
			</div>
		</form>
	</div>
	<footer class="footer">
		<div class="container text-center">
			<p class="text-muted">
				Todos os direitos reservados&emsp;&emsp;-&emsp;&emsp;<label>Desenvolvido por Desoltec Engenharia</label>&emsp;&emsp;<img src="./images/logo_desoltec_rodape.png" alt="" style="margin: 0px;">
			</p>
		</div>
	</footer>
</body>
</html>