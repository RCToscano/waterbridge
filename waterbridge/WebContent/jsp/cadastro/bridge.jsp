
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
		<title>WaterBridge</title>
		<link rel="icon" type="image/png" href="./images/favicon.ico"/>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="./css/menucustomcolor.css" rel="stylesheet"/>
        <link href="./css/footercustom.css" rel="stylesheet"/>
        <script src="./js/jquery-1.11.3.min.js"></script>
        <script src="./js/jquery.mask.min.js" type="text/javascript"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
		<script type="text/javascript" src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
        <script>
            $(function () {
                $("#telefone").mask("(99) 99999-9999");
            });
        </script>
    </head>
    <body>
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Cadastro</li>
			    <li class="active">Bridge's</li>
			</ul>
			
<%-- 			<c:if test="${aviso != ''}"> --%>
<!-- 				<div class="alert alert-danger"> -->
<%-- 					<strong><c:out value="${aviso}"/></strong> --%>
<!-- 				</div> -->
<%-- 			</c:if> --%>
			
<%-- 			<c:if test="${sucesso != ''}"> --%>
<!-- 				<div class="alert alert-success"> -->
<%-- 					<strong><c:out value="${sucesso}"/></strong> --%>
<!-- 				</div> -->
<%-- 			</c:if> --%>
			
			<div class="row">
				<div class="col-md-7 col-md-offset-2">
					<form action="" method="post">
						<fieldset>
							<legend class="text-left">Cadastro de Bridge's</legend>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label" for="name">Device N&deg;:</label>
									<input type="text" class="form-control" id="device" name="device" required/>
								</div>
							</div>
							
							<div class="col-sm-5">
								<div class="form-group">
	                                <label>Data de Ativação</label>
	                                <div class='input-group date' id='datetimepicker1'>
	                                    <input type='text' class="form-control" name="dtAtivacao" id="dtAtivacao" value="" maxlength="19" placeholder="DD/MM/YYYY HH:MM:SS" required/>
	                                    <span class="input-group-addon">
	                                        <span class="glyphicon glyphicon-calendar"></span>
	                                    </span>
	                                    <script type="text/javascript">
	                                        $(function () {
	                                            $('#datetimepicker1').datetimepicker({
	                                                format: 'DD/MM/YYYY hh:mm:ss'
	                                            });
	                                        });
	                                    </script>
	                                </div>
                                </div>
                            </div>
                            
                            <div class="col-sm-4">
                            	<div class="form-group">
	                                <label>Validade do Token</label>
	                                <div class='input-group date' id='datetimepicker2'>
	                                    <input type='text' class="form-control" name="dtToken" id="dtToken" value="" maxlength="10" placeholder="DD/MM/YYYY" required/>
	                                    <span class="input-group-addon">
	                                        <span class="glyphicon glyphicon-calendar"></span>
	                                    </span>
	                                    <script type="text/javascript">
	                                        $(function () {
	                                            $('#datetimepicker2').datetimepicker({
	                                                format: 'DD/MM/YYYY'
	                                            });
	                                        });
	                                    </script>
	                                </div>
                                </div>
                            </div>
                            
                            <div class="col-sm-4">
								<div class="form-group">
									<label for="sel1">Tipo de Alimentação</label> 
									<select class="form-control" name="tpAlimentacao" id="tpAlimentacao" required>
										<option value="" selected>Selecione...</option>
										<option value="1">Bateria</option>
										<option value="2">Solar</option>
										<option value="3">AC</option>
									</select>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">Custo Mensal:</label>
									<input type="text" class="form-control" id="custo" name="custo" required/>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label" for="name">Taxa de Envio Diário:</label>
									<input type="text" class="form-control" id="taxa" name="taxa" required/>
								</div>
							</div>
							
	    					<div class="col-sm-12">
								<div class="form-group">
									<div class="col-md-12 text-center">
										<button type="submit" class="btn btn-primary">Cadastrar</button>
									</div>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
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
