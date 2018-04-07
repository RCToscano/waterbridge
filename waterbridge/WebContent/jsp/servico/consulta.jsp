
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Deep</title>
        <link rel="icon" type="image/png" sizes="32x32" href="./images/favicon-32x32.png">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="./css/menucustomcolor.css" rel="stylesheet"/>
        <link href="./css/footercustom.css" rel="stylesheet"/>
        <script src="./js/jquery-1.11.3.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" />
		<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
		<script type="text/javascript" src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
        
        
    </head>
    <body>
    	<jsp:include page="../../menu/${sessionScope.user.perfil.menu}" ></jsp:include>
    	<div class="container">
	    	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Servi&ccedil;o</a></li>
			    <li class="active">Consulta</li>
			</ul>
			
			<c:if test="${aviso != ''}">
				<div class="alert alert-danger">
					<strong><c:out value="${aviso}"/></strong>
				</div>
			</c:if>
		
			<h2>Consulta de Servi&ccedil;o</h2>
			
			<div class="col-sm-12">
				<label>Informe o per&iacute;odo de execu&ccedil;ao: </label> 
			</div>
			
			<form class="form-horizontal" action="ServicoBO?acao=pesquisar" method="post">
				<div class="form-group">
					<label class="control-label col-sm-2" for="dtInicio">Data Inicio</label>
					<div class="col-sm-2">
						<input type="text" class="form-control input-sm" data-date-format="DD/MM/YYYY" name="dtInicio" id="dtInicio" placeholder="dd/mm/aaaa" maxlength="10" value="" required/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="dtFim">Data Fim</label>
					<div class="col-sm-2">
						<input type="text" class="form-control input-sm" data-date-format="DD/MM/YYYY" name="dtFim" id="dtFim" placeholder="dd/mm/aaaa" maxlength="10" value="" required/>
					</div>
				</div>
				
				<div class="form-group">
			    	<div class="col-sm-offset-2 col-sm-10">
			        	<button type="submit" class="btn btn-primary btn-primary">Consultar</button>
			      	</div>
			    </div>
			</form>
		</div>
		<footer class="footer">
			<div class="container text-center">
				<p class="text-muted">©Copyright 2018</p>
            </div>
        </footer>
        
        <script type="text/javascript">
            $(function () {
                $('#dtInicio').datetimepicker();
                $('#dtFim').datetimepicker();
            });
        </script>
    </body>
</html>