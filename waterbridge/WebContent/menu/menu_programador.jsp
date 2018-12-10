<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <c:choose>
				<c:when test="${empty sessionScope.user.empresa.logoPNome}">
            		<a class="navbar-brand" href="HomeBO?acao=home"style="padding: 8px;"><img src="./images/logo_waterbridge_menu.png" alt="" style="margin: 0px;"></a>
				</c:when>
				<c:otherwise>
            		<a class="navbar-brand" href="HomeBO?acao=home"style="padding: 8px;"><img src="Serializacao?path=${sessionScope.user.empresa.logoPDir}&imagem=${sessionScope.user.empresa.logoPNome}" alt="" style="max-width: 122px; max-height: 35px; margin: 0px;"></a>
				</c:otherwise>
			</c:choose>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        	<ul class="nav navbar-nav">
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Empresa <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="EmpresaBO?acao=cadastro">Cadastro</a></li>
                         <li class="divider"></li>
                        <li><a href="EmpresaBO?acao=consulta">Consulta</a></li>
                         <li class="divider"></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav">
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Local <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="CondominioBO?acao=1">Cadastro</a></li>
                         <li class="divider"></li>
                        <li><a href="CondominioBO?acao=5">Consulta</a></li>
                         <li class="divider"></li>
                    </ul>
                </li>
            </ul>            
            <ul class="nav navbar-nav">
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Bridge <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="BridgeBO?acao=1">Cadastro</a></li>
                        <li class="divider"></li>
                        <li><a href="BridgeBO?acao=5">Consulta</a></li>
                        <li class="divider"></li>
                        <li><a href="ExclusaoConsumoMedidorBO?acao=1">Exclusão Consumo</a></li>
                        <li class="divider"></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav">
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Medidor <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="MedidorBO?acao=cadastro">Cadastro</a></li>
                         <li class="divider"></li>
                        <li><a href="MedidorBO?acao=consulta">Consulta</a></li>
                         <li class="divider"></li>
                         <li><a href="FabricMedidorBO?acao=1">Cadastro Fabricante</a></li>
                         <li class="divider"></li>
                        <li><a href="FabricMedidorBO?acao=5">Consulta Fabricante</a></li>
                         <li class="divider"></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav">
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Usuário <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="UsuarioBO?acao=cadUsuario">Cadastro</a></li>
                         <li class="divider"></li>
                        <li><a href="UsuarioBO?acao=consulta">Consulta</a></li>
                         <li class="divider"></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav">
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Vínculos <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">                        
                        <li><a href="UsuarioEmpresaBO?acao=1">Usuário x Empresa</a></li>
                        <li class="divider"></li>
                        <li><a href="UsuarioCondominioBO?acao=1">Usuário x Local</a></li>
                        <li class="divider"></li>
                        <li><a href="UsuarioMedidorBO?acao=1">Usuário x Medidor</a></li>
                        <li class="divider"></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav">
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Consulta <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="ConsumoMedidorBO?acao=1">Consumos por Medidor</a></li>
                        <li class="divider"></li>
                        <li><a href="ConsumoCondominioBO?acao=1">Consumos por Local</a></li>
                        <li class="divider"></li>
                        <li><a href="RelatorioPressaoBO?acao=1">Pressure Bridge</a></li>
                        <li class="divider"></li>
                        <li><a href="MapaConsumoPressaoBO?acao=1" target="_blank">Mapa Dispositivos</a></li>
                        <li class="divider"></li>
                        <li><a href="ReservatorioBO?acao=1" target="_blank">Reservat&oacute;rios</a></li>
                        <li class="divider"></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav">
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Rateio <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="ContaBO?acao=cadastro">Cadastrar Conta</a></li>
                        <li class="divider"></li>
                        <li><a href="ContaBO?acao=consulta">Consultar Conta</a></li>
                        <li class="divider"></li>
                        <li><a href="RateioBO?acao=1">Executar Rateio</a></li>
                        <li class="divider"></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
	        	<span class="navbar-text">Ol&aacute; ${sessionScope.user.nome}, seja bem-vindo!</span>
		    	<li><a href="UsuarioBO?acao=perfil"><span class="glyphicon glyphicon-user"></span> Perfil</a></li>
		    	<li><a href="Login?r=logout"><span class="glyphicon glyphicon-log-in"></span> Sair</a></li>
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