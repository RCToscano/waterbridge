
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>
    .dropdown-submenu {
        position: relative;
    }

    .dropdown-submenu .dropdown-menu {
        top: 0;
        left: 100%;
        margin-top: -1px;
    }
</style>
<div id="1">
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="HomeBO?acao=home"style="padding: 8px;"><img src="./images/logo_waterbridge_menu.png" alt="" style="margin: 0px;"></a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Relatórios <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="HomeBO?acao=home">Consumos Período</a></li>
                         <li class="divider"></li>
<!--                         <li><a href="ServicoBO?acao=cadastrar"></a></li> -->
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav">
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Cadastros <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="CadastroBO?acao=usuario">Usuários</a></li>
                         <li class="divider"></li>
                        <li><a href="BridgeBO?acao=1">Bridge</a></li>
                         <li class="divider"></li>
                        <li><a href="CadastroBO?acao=condominio">Condomínios</a></li>
                         <li class="divider"></li>
                        <li><a href="CadastroBO?acao=medidor">Medidores</a></li>
						<li class="divider"></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
	        	<span class="navbar-text">Ol&aacute; ${sessionScope.user.nome}, seja bem-vindo!</span>
<!-- 		    	<li><a href="#"><span class="glyphicon glyphicon-user"></span> Perfil</a></li> -->
		    	<li><a href="Login?r=logout"><span class="glyphicon glyphicon-log-in"></span> Sair</a></li>
<!-- 		    	<li><a data-toggle="modal" data-target="#myModal" href="Login?r=logout"><span class="glyphicon glyphicon-log-in"></span> Sair</a></li> -->
		    </ul>
        </div>
    </div>
</nav>
</div>

<script>
    $(document).ready(function () {
        $('.dropdown-submenu a.test').on("click", function (e) {
            $(this).next('ul').toggle();
            e.stopPropagation();
            e.preventDefault();
        });
    });
</script>