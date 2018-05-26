
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="Login?r=index"style="padding: 8px;"><img src="./images/logo_waterbridge_menu.png" alt="" style="margin: 0px;"></a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="#">Water Bridge</a></li>
                <li><a href="#">Contato</a></li>
            </ul>
            
            <ul class="nav navbar-nav navbar-right">
	        	<a href="Login?r=senha"><span class="navbar-text">Esqueci minha senha</span></a>
	        </ul>

            <form id="defaultForm" data-toggle="validator" class="navbar-form navbar-right" role="form" action="Login?r=login" method='POST'>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input type="tel" class="form-control" id="usuario" name="usuario" value="" placeholder="CPF" data-toggle="tooltip" data-placement="bottom" title="${loginErro}" required>
                        <script>
				            $(function () {
				                $("#usuario").mask("999.999.999-99");
				            });
				        </script>
                    </div>
                </div> 
                <div class="form-group">                   
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="password" type="password" class="form-control" name="password" value="" placeholder="Senha" required>                                        
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-success">Login</button>
                </div>                
            </form>
        </div>
    </div>
</nav>