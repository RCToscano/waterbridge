<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Deep</title>
        <link rel="icon" type="image/png" sizes="32x32" href="./images/favicon-32x32.png"/>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="./css/menucustomcolor.css" rel="stylesheet"/>
        <link href="./css/footercustom.css" rel="stylesheet"/>
        <script src="./js/jquery-1.11.3.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
		<script type="text/javascript" src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
		<script src='./js/funcoes.auxiliares.js'></script>
		<script src='./js/servico/formulario.js'></script>
    </head>
    <body>
        <jsp:include page="../../menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<c:choose>
	        	<c:when test="${botao == 'Cadastrar'}">
	        		<ul class="breadcrumb">
					    <li><a href="HomeBO?acao=home">Home</a></li>
					    <li class="active">Servi&ccedil;o</li>
					    <li class="active">Cadastro</li>
					    <li class="active">Formul&aacute;rio</li>
					</ul>
	        	</c:when>
	        	<c:otherwise>
	        		<ul class="breadcrumb">
					    <li><a href="HomeBO?acao=home">Home</a></li>
					    <li><a href="ServicoBO?acao=consultar">Servi&ccedil;o</a></li>
					    <li><a href="ServicoBO?acao=consultar">Consulta</a></li>
					    <li><a href="ServicoBO?acao=pesquisar&dtInicio=${dtInicio}&dtFim=${dtFim}">Lista</a></li>
					    <li class="active">Formul&aacute;rio</li>
					</ul>
	        	</c:otherwise>
        	</c:choose>
			
<%-- 			<c:if test="${aviso != ''}"> --%>
				<div id="divAviso" name="divAviso" class="alert alert-danger" style="display:${display};">
					<strong><label id='aviso' name='aviso'>${aviso}</strong>
				</div>
<%-- 			</c:if> --%>
			
			<c:if test="${sucesso != ''}">
				<div class="alert alert-success">
					<strong><c:out value="${sucesso}"/></strong>
				</div>
			</c:if>
		
			<form action="ServicoBO?acao=inserir" method="post" onSubmit="return validaForm()">
				<input type="hidden" id="dtInicio" name="dtInicio" value="${dtInicio}" />
				<input type="hidden" id="dtFim" name="dtFim" value="${dtFim}" />
				<input type="hidden" id="id" name="id" value="${modelo.idDiagnostico}" />
				
				
				<h2>Formul&aacute;rio de Diagn&oacute;stico</h2>
				<div class="panel-group">
				
					<div class="panel panel-primary">
						<div class="panel-heading"><label>Servi&ccedil;o</label></div>
						<div class="panel-body">
						
							<div class="col-sm-2">
								<div class="form-group">
									<label for="id">ID: </label> 
									<input type="text" class="form-control input-sm" name="id" id="id" value="${modelo.idDiagnostico}" disabled />
								</div>
							</div>
						
							<div class="col-sm-12"></div>
							
							<div class="col-sm-6">
								<div class="form-group has-error has-feedback">
									<label for="sel1">Cadastrante *:</label> 
									<select class="form-control" name="cadastrante" id="cadastrante" required>
	                                	<option value="" selected>Selecione...</option>
	                                    <c:forEach var="listaEquipe" items="${listaEquipe}">
	                                    	<c:choose>
		                                        <c:when test="${modelo.idEquipe == listaEquipe.idEquipe}">
		                                            <option value="${listaEquipe.idEquipe}" selected="true">${listaEquipe.equipe}</option>
		                                        </c:when>
		                                        <c:otherwise>
		                                        	<option value="${listaEquipe.idEquipe}" >${listaEquipe.equipe}</option>
		                                        </c:otherwise>
	                                        </c:choose>
	                                    </c:forEach>
	                                </select>
								</div>
							</div>
							
							<div class="col-sm-2">
								<div class="form-group has-error has-feedback">
									<label for="dtExecucao">Data de Execu&ccedil;&atilde;o *: </label> 
									<input type="text" class="form-control input-sm" name="dtExecucao" id="dtExecucao" data-date-format="DD/MM/YYYY" placeholder="dd/mm/aaaa" maxlength="10" value="${modelo.data}" required />
								</div>
							</div>
							
							<div class="col-sm-12"></div>
							
							<div class="col-sm-2"> 
								<div class="form-group">
									<label for="codServico">C&oacute;digo do Servi&ccedil;o: </label> 
									<input type="text" class="form-control input-sm" name="codServico" id="codServico" maxlength="9" value="${modelo.codServ}" onKeyPress="numerico(this)" />
								</div>
							</div>
							
							<div class="col-sm-6">
								<div class="form-group has-error has-feedback">
									<label for="sel1">Comunidade *:</label> 
									<select class="form-control" name="comunidade" id="comunidade" required>
	                                    <option value="" selected>Selecione...</option>
	                                    <c:forEach var="listaComunidades" items="${listaComunidades}">
	                                    	<c:choose>
		                                        <c:when test="${modelo.idComunidade == listaComunidades.idComunidade}">
		                                            <option value="${listaComunidades.idComunidade}" selected="true">${listaComunidades.nome}</option>
		                                        </c:when>
		                                        <c:otherwise>
		                                        	<option value="${listaComunidades.idComunidade}" >${listaComunidades.nome}</option>
		                                        </c:otherwise>
	                                        </c:choose>
	                                    </c:forEach>
	                                </select>
									</select>
								</div>
							</div>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label>Tipo de Instala&ccedil;&atilde;o: </label>
									<c:forEach items="${listaTpInstalacao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.idTpInstal}">
													<input type="radio" name="radioTpInst" id="tpInstRegular${total.descricao}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioTpInst" id="tpInstRegular${total.descricao}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label for="email">Tipo de Constru&ccedil;&atilde;o: </label> 
									<c:forEach items="${listaTpContrucao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.idTpConstr}">
													<input type="radio" name="radioTpConstrucao" id="tpConstrucao${total.descricao}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioTpConstrucao" id="tpConstrucao${total.descricao}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group">
									<label for="email">Situa&ccedil;&atilde;o do Im&oacute;vel: </label> 
									<c:forEach items="${listaSituacaoImovel}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.idSitImovel}">
													<input type="radio" name="radioSitImovel" id="sitImovel${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioSitImovel" id="sitImovel${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label for="email">Categoria do Im&oacute;vel: </label> 
									<label class="checkbox-inline">
										<c:choose>
											<c:when test="${not empty modelo.categoria1}">
												<input type="checkbox" name="checkTpImovelResidencia" id="checkTpImovelResidencia" checked>Resid&ecirc;ncia</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkTpImovelResidencia" id="checkTpImovelResidencia" >Resid&ecirc;ncia</input>
											</c:otherwise>
										</c:choose>
									</label>
									<label class="checkbox-inline">
										<c:choose>
											<c:when test="${not empty modelo.categoria2}">
												<input type="checkbox" name="checkTpImovelComercio" id="checkTpImovelComercio" checked >Com&eacute;rcio</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkTpImovelComercio" id="checkTpImovelComercio" >Com&eacute;rcio</input>
											</c:otherwise>
										</c:choose>
									</label>
									<label class="checkbox-inline">
										<c:choose>
											<c:when test="${not empty modelo.categoria3}">
												<input type="checkbox" name="checkTpImovelIndustria" id="checkTpImovelIndustria" checked >Ind&uacute;stria</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkTpImovelIndustria" id="checkTpImovelIndustria" >Ind&uacute;stria</input>
											</c:otherwise>
										</c:choose>
									</label>
									<label class="checkbox-inline">
										<c:choose>
											<c:when test="${not empty modelo.categoria4}">
												<input type="checkbox" name="checkTpImovelPublica" id="checkTpImovelPublica" checked >P&uacute;blica</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkTpImovelPublica" id="checkTpImovelPublica" >P&uacute;blica</input>
											</c:otherwise>
										</c:choose>
									</label>
								</div>
							</div>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label for="imovel">Im&oacute;vel: </label> 
									<c:forEach items="${listaTipoImovel}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.idTpUso}">
													<input type="radio" name="radioImovel" id="imovel${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioImovel" id="imovel${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							 
							<div class="col-sm-4">
								<div class="form-group">
									<label for="qtdeCasas">Qtde. de Casas no Mesmo Endere&ccedil;o/Terreno</label> 
									<input type="text" class="form-control input-sm" name="qtdeCasas" id="qtdeCasas" maxlength="2" value="${modelo.qtdeCasas}" onKeyPress="numerico(this)" />
								</div>
							</div>
							 
							<div class="col-sm-2">
								<div class="form-group">
									<label for="ocupacao">Tempo de Ocupa&ccedil;&atilde;o</label> 
									<input type="text" class="form-control input-sm" name="ocupacao" id="ocupacao" maxlength="9" value="${modelo.tempoOcup}" onKeyPress="numerico(this)"/>
								</div>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group">
									<label for="atividade">Atividade</label> 
									<input type="text" class="form-control input-sm" name="atividade" id="atividade" maxlength="100" value="${modelo.atividade}" />
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label for="irregularidade">Energia El&eacute;trica: </label> 
									<c:forEach items="${listaSimNao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.energEletr}">
													<input type="radio" name="radioEnergia" id="energia${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioEnergia" id="energia${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
	
							<div class="col-sm-6">
								<div class="form-group">
									<label for="irregularidade">Suspeito de Irregularidade: </label> 
									<c:forEach items="${listaSimNao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.energEletrIrreg}">
													<input type="radio" name="radioEnergiaIrregular" id="irregularEnerg${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioEnergiaIrregular" id="irregularEnerg${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label for="numeroInstalacao">N&deg; Instala&ccedil;&atilde;o</label> 
									<input type="text" class="form-control input-sm" name="numeroInstalacao" id="numeroInstalacao" maxlength="50" value="${modelo.instalacao}" />
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label for="numeroMedidor">N&deg; Medidor</label> 
									<input type="text" class="form-control input-sm" name="numeroMedidor" id="numeroMedidor" maxlength="50" value="${modelo.medidor}"  />
								</div>
							</div>
							
							<div class="col-sm-12"></div>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label for="pavimento">Cal&ccedil;amento / Pavimenta&ccedil;&atilde;o em Frente a Resid&ecirc;ncia: </label>
									<c:forEach items="${listaSimNao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.pavimeExiste}">
													<input type="radio" name="radioPavimento" id="pavimento${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioPavimento" id="pavimento${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach> 
								</div>
							</div>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label for="lixo">Coleto de Lixo: </label> 
									<c:forEach items="${listaSimNao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.colLixoExiste}">
													<input type="radio" name="radioLixo" id="lixo${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioLixo" id="lixo${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group">
									<label for="abastecimento">Forma de Abastecimento do Im&oacute;vel: </label> 
									<c:forEach items="${listaAbastecimentoAgua}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.idAbastAgua}">
													<input type="radio" name="radioAbastecimento" id="abast${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioAbastecimento" id="abast${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group">
									<label for="irregularidade">Suspeito de Irregularidade: </label> 
									<c:forEach items="${listaSimNao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.abastAguaIrreg}">
													<input type="radio" name="radioAguaIrregular" id="irregularAgua${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioAguaIrregular" id="irregularAgua${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label for="hidrometro">Hidr&ocirc;metro</label> 
									<input type="text" class="form-control input-sm" name="hidrometro" id="hidrometro" maxlength="15" value="${modelo.hidrometro}"  />
								</div>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group">
									<label for="esgoto">Destino Esgoto: </label> 
									<c:forEach items="${listaDestinoEsgoto}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.idDestEsgoto}">
													<input type="radio" name="radioEsgoto" id="esgoto${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioEsgoto" id="esgoto${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
						</div> <!-- Fim Painel Body -->
					</div> <!-- Fim Painel -->
						
					<div class="panel panel-primary">
						<div class="panel-heading"><label>Dados do Cliente</label></div>
						<div class="panel-body">
							<div class="col-sm-12">
								<div class="form-group">
									<label for="nomeCliente">Nome Completo</label> 
									<input type="text" class="form-control input-sm" name="nomeCliente" id="nomeCliente" maxlength="100" value="${modelo.nome}" />
								</div>
							</div>
						
							<div class="col-sm-2">
								<div class="form-group">
									<label for="cpfCliente">CPF</label> 
									<input type="text" class="form-control input-sm" name="cpfCliente" id="cpfCliente" maxlength="14" value="${modelo.cpf}" onKeyPress="mascaraCpf(this)"/>
								</div>
							</div>
								
							<div class="col-sm-2">
								<div class="form-group">
									<label for="rgCliente">RG</label> 
									<input type="text" class="form-control input-sm" name="rgCliente" id="rgCliente" maxlength="45" value="${modelo.rg}"  />
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label for="municipioNascimento">Munic&iacute;pio de Nascimento</label> 
									<input type="text" class="form-control input-sm" name="municipioNascimento" id="municipioNascimento" maxlength="100" value="${modelo.munNasc}" />
								</div>
							</div>
	
						<div class="col-sm-3">
							<div class="form-group">
								<label for="sel1">Estado de Nascimento</label> 
								<select class="form-control" name="estadoNascimento" id="estadoNascimento" >
									<option value="" selected>Selecione...</option>
									<c:forEach items="${listaEstados}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.ufNasc}">
													<option value="${total.id}" selected>${total.descricao}</option>
												</c:when>
												<c:otherwise>
													<option value="${total.id}">${total.descricao}</option>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</select>
							</div>
						</div>
	
						<div class="col-sm-2">
								<div class="form-group">
									<label for="dtNascimento">Data de Nascimento</label> 
									<input type="text" class="form-control input-sm" name="dtNascimento" id="dtNascimento" maxlength="10" value="${modelo.dtNasc}" data-date-format="DD/MM/YYYY" placeholder="dd/mm/aaaa"  />
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label for="sexo">Nacionalidade: </label> 
									<c:forEach items="${listaNacionalidade}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.nacionalidade}">
													<input type="radio" name="radioNacionalidade" id="nacionalidade${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioNacionalidade" id="nacionalidade${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label for="sexo">Sexo: </label> 
									<c:forEach items="${listaSexo}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.sexo}">
													<input type="radio" name="radioSexo" id="sexo${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioSexo" id="sexo${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label for="imovel">Estado Civil: </label> 
									<c:forEach items="${listaEstadoCivil}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.idEstadoCivil}">
													<input type="radio" name="radioEstadoCivil" id="estCivil${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioEstadoCivil" id="estCivil${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-2">
								<div class="form-group">
									<label for="telefone">Telefone Resid&ecirc;ncial</label> 
									<input type="text" class="form-control input-sm" name="telefone" id="telefone" maxlength="11" value="${modelo.telRes}"  onKeyPress="numerico(this)"/>
								</div>
							</div>
							
							<div class="col-sm-2">
								<div class="form-group">
									<label for="celular">Celular</label> 
									<input type="text" class="form-control input-sm" name="celular" id="celular" maxlength="11" value="${modelo.telCel}"  onKeyPress="numerico(this)"/>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label for="email">E-mail</label> 
									<input type="email" class="form-control input-sm" name="email" id="email" maxlength="50" value="${modelo.email}"  />
								</div>
							</div>
							
							<div class="col-sm-10">
								<div class="form-group">
									<label for="endereco">Endere&ccedil;o</label> 
									<input type="text" class="form-control input-sm" name="endereco" id="endereco" maxlength="100" value="${modelo.endereco}"  />
								</div>
							</div>
							
							<div class="col-sm-2">
								<div class="form-group">
									<label for="numeroAtualEndereco">N&deg; Atual</label> 
									<input type="text" class="form-control input-sm" name="numeroAtualEndereco" id="numeroAtualEndereco" maxlength="5" value="${modelo.numAtual}"  onKeyPress="numerico(this)"/>
								</div>
							</div>
							
							<div class="col-sm-2">
								<div class="form-group">
									<label for="numeroAntigoEndereco">N&deg; Antigo</label> 
									<input type="text" class="form-control input-sm" name="numeroAntigoEndereco" id="numeroAntigoEndereco" maxlength="5" value="${modelo.numAntigo}"  onKeyPress="numerico(this)"/>
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label for="complementoEndereco">Complemento</label> 
									<input type="text" class="form-control input-sm" name="complementoEndereco" id="complementoEndereco" maxlength="30" value="${modelo.compl}"  />
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label for="bairroEndereco">Bairro</label> 
									<input type="text" class="form-control input-sm" name="bairroEndereco" id="bairroEndereco" maxlength="30" value="${modelo.bairro}"  />
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label for="municipioEndereco">Munic&iacute;pio</label> 
									<input type="text" class="form-control input-sm" name="municipioEndereco" id="municipioEndereco" maxlength="100" value="${modelo.mun}"  />
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label for="sel1">Estado</label> 
									<select class="form-control" name="estadoEndereco" id="estadoEndereco" >
										<option value="" selected>Selecione...</option>
										<c:forEach items="${listaEstados}" var="total">
											<label class="radio-inline">
												<c:choose>
													<c:when test="${total.id == modelo.uf}">
														<option value="${total.id}" selected>${total.descricao}</option>
													</c:when>
													<c:otherwise>
														<option value="${total.id}">${total.descricao}</option>
													</c:otherwise>
												</c:choose>
											</label> 
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="col-sm-2">
								<div class="form-group">
									<label for="cepEndereco">CEP</label> 
									<input type="text" class="form-control input-sm" name="cepEndereco" id="cepEndereco" maxlength="9" value="${modelo.cep}"  />
								</div>
							</div>
							
							<div class="col-sm-2">
								<div class="form-group">
									<label for="qtdeAdultos">Qtde. Adultos</label> 
									<input type="text" class="form-control input-sm" name="qtdeAdultos" id="qtdeAdultos" maxlength="2" value="${modelo.qtdeAdulto}" onKeyPress="numerico(this)" />
								</div>
							</div>
							
							<div class="col-sm-2">
								<div class="form-group">
									<label for="qtdeCriancas">Qtde. Crian&ccedil;as</label> 
									<input type="text" class="form-control input-sm" name="qtdeCriancas" id="qtdeCriancas" maxlength="2" value="${modelo.qtdeCrianca}" onKeyPress="numerico(this)"  />
								</div>
							</div>
							
							<div class="col-sm-12"></div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label for="banco">Tem Conta Banc&aacute;ria: </label> 
									<c:forEach items="${listaSimNao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.possuiConta}">
													<input type="radio" name="radioBanco" id="banco${total.id}" value="${total.id}" checked onClick="verificaContaBanco()">${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioBanco" id="banco${total.id}" value="${total.id}" onClick="verificaContaBanco()">${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label for="cartaoDebito">Possui Cart&atilde;o de D&eacute;bito: </label>
									<c:forEach items="${listaSimNao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.possuiCDeb}">
													<input type="radio" name="radioCartaoDebito" id="cartaoDebito${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioCartaoDebito" id="cartaoDebito${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach> 
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label for="descBanco">Qual Banco</label> 
									<input type="text" class="form-control input-sm" name="descBanco" id="descBanco" maxlength="100" value="${modelo.banco}"  />
								</div>
							</div>
							
							<div class="col-sm-2">
								<div class="form-group">
									<label for="rendaFamilia">Renda Total da Fam&iacute;lia</label> 
									<input type="text" class="form-control input-sm" name="rendaFamilia" id="rendaFamilia" maxlength="12" value="${modelo.rendaTotal}" onBlur="moeda(this)" onKeyPress="moeda(this)"/>
								</div>
							</div>
	
							<div class="col-sm-6">
								<div class="form-group">
									<label for="financeiroMensal">Qual &eacute; o % M&eacute;dio de Comprometimento  Financeiro Mensal</label> 
									<input type="text" class="form-control input-sm" name="financeiroMensal" id="financeiroMensal" maxlength="2" value="${modelo.rendaPerceUtil}"  onKeyPress="numerico(this)"/>
								</div>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group">
									<label for="sel3">Meios de Transporte: </label> 
									<label class="checkbox-inline">
										<c:choose>
											<c:when test="${not empty modelo.meioTransp1}">
												<input type="checkbox" name="checkTransporteCarro" id="checkTransporteCarro" checked >Carro Pr&oacute;prio</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkTransporteCarro" id="checkTransporteCarro" >Carro Pr&oacute;prio</input>
											</c:otherwise>
										</c:choose>
									</label>
									<label class="checkbox-inline">
										<c:choose>
											<c:when test="${not empty modelo.meioTransp2}">
												<input type="checkbox" name="checkTransporteOnibus" id="checkTransporteOnibus" checked >&Ocirc;nibus</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkTransporteOnibus" id="checkTransporteOnibus" >&Ocirc;nibus</input>
											</c:otherwise>
										</c:choose>
									</label>
									<label class="checkbox-inline">
										<c:choose>
											<c:when test="${not empty modelo.meioTransp3}">
												<input type="checkbox" name="checkTransporteTrem" id="checkTransporteTrem" checked >Trem</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkTransporteTrem" id="checkTransporteTrem" >Trem</input>
											</c:otherwise>
										</c:choose>
									</label>
									<label class="checkbox-inline">
										<c:choose>
											<c:when test="${not empty modelo.meioTransp4}">
												<input type="checkbox" name="checkTransporteMetro" id="checkTransporteMetro" checked >Metro</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkTransporteMetro" id="checkTransporteMetro" >Metro</input>
											</c:otherwise>
										</c:choose>
									</label>
									<label class="checkbox-inline">
										<c:choose>
											<c:when test="${not empty modelo.meioTransp5}">
												<input type="checkbox" name="checkTransporteTaxi" id="checkTransporteTaxi" checked >Taxi</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkTransporteTaxi" id="checkTransporteTaxi" >Taxi</input>
											</c:otherwise>
										</c:choose>
									</label>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label for="cartaoDebito">Acesso a Internet: </label> 
									<c:forEach items="${listaSimNao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.internetAcess}">
													<input type="radio" name="radioInternet" id="internet${total.id}" value="${total.id}" checked onClick="verificaAcessoInternet()">${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioInternet" id="internet${total.id}" value="${total.id}" onClick="verificaAcessoInternet()">${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="form-group">
									<label for="email">Tipo de Internet: </label> 
									<label class="checkbox-inline">
										<c:choose>
											<c:when test="${not empty modelo.internetWifi}">
												<input type="checkbox" name="checkWifi" id="checkWifi" checked>WI-FI</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkWifi" id="checkWifi" >WI-FI</input>
											</c:otherwise>
										</c:choose>
									</label>
									<label class="checkbox-inline">
										<c:choose>
											<c:when test="${not empty modelo.internet3g}">
												<input type="checkbox" name="check3g" id="check3g" checked >3G</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="check3g" id="check3g" >3G</input>
											</c:otherwise>
										</c:choose>
									</label>
								</div>
							</div>
	
							<div class="col-sm-12"></div>
							
							<div class="col-sm-12">
								<label>Algum familiar que reside na moradia possui (Quantidade): </label> 
							</div>
							
							<div class="col-sm-1">
								<div class="form-group">
									<label for="carro">Carro</label> 
									<input type="text" class="form-control input-sm" name="qtdeCarro" id="qtdeCarro" placeholder="Qtde" maxlength="2" value="${modelo.qtdeCarro}" onKeyPress="numerico(this)"/>
								</div>
							</div>
							
							<div class="col-sm-1">
								<div class="form-group">
									<label for="moto">Moto</label> 
									<input type="text" class="form-control input-sm" name="qtdeMoto" id="qtdeMoto" placeholder="Qtde" maxlength="2" value="${modelo.qtdeMoto}" onKeyPress="numerico(this)"/>
								</div>
							</div>
							
							<div class="col-sm-1">
								<div class="form-group">
									<label for="bicicleta">Bicicleta</label> 
									<input type="text" class="form-control input-sm" name="qtdeBicicleta" id="qtdeBicicleta" placeholder="Qtde" maxlength="2" value="${modelo.qtdeBicicleta}" onKeyPress="numerico(this)"/>
								</div>
							</div>
						</div>
					</div>
					
					<div class="panel panel-primary">
						<div class="panel-heading"><label>Benef&iacute;cios Sociais</label></div>
						<div class="panel-body">
							
							<div class="col-sm-6">
								<div class="form-group">
									<label for="numeroBPC">N&uacute;mero do Benef&iacute;cio (BENEF&Iacute;CIO DE PRESTA&Ccedil;&Atilde;O CONTINUADA-BPC)</label> 
									<input type="text" class="form-control input-sm" name="numeroBPC" id="numeroBPC" maxlength="50" value="${modelo.benefBCPNum}"  />
								</div>
							</div>
	
							<div class="col-sm-6">
								<div class="form-group">
									<label for="nis">NIS - N&uacute;mero de Inscri&ccedil;&atilde;o Social (CAD&Uacute;NICO)</label> 
									<input type="text" class="form-control input-sm" name="nis" id="nis" maxlength="50" value="${modelo.benefNISNum}"  />
								</div>
							</div>
	
							<div class="col-sm-4">
								<div class="form-group">
									<label for="cartaoDebito">Possui Tarifa Social de &Aacute;gua: </label> 
									<c:forEach items="${listaSimNao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.possuiTarSocial}">
													<input type="radio" name="radioTarifaAgua" id="tarifaAgua${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioTarifaAgua" id="tarifaAgua${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
	
							<div class="col-sm-4">
								<div class="form-group">
									<label for="cartaoDebito">Bolsa Fam&iacute;lia: </label> 
									<c:forEach items="${listaSimNao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.possuiBolsaFamil}">
													<input type="radio" name="radioBolsaFamilia" id="bolsaFamilia${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioBolsaFamilia" id="bolsaFamilia${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
						</div>
					</div>
					
					<div class="panel panel-primary">
						<div class="panel-heading"><label>Dados Pessoais Benef&iacute;ciario N&atilde;o Titular</label></div>
						<div class="panel-body">
						
							<div class="col-sm-12">
								<div class="form-group">
									<label for="nomeNTitular">Nome Completo</label> 
									<input type="text" class="form-control input-sm" name="nomeNTitular" id="nomeNTitular" maxlength="100" value="${modelo.benefNome}" />
								</div>
							</div>
						
							<div class="col-sm-2">
								<div class="form-group">
									<label for="cpfNTitular">CPF</label> 
									<input type="text" class="form-control input-sm" name="cpfNTitular" id="cpfNTitular" maxlength="14" value="${modelo.benefCpf}" onKeyPress="mascaraCpf(this)"/>
								</div>
							</div>
								
							<div class="col-sm-2">
								<div class="form-group">
									<label for="rgNTitular">RG</label> 
									<input type="text" class="form-control input-sm" name="rgNTitular" id="rgNTitular" maxlength="45" value="${modelo.benefRg}"  />
								</div>
							</div>
								
							<div class="col-sm-2">
								<div class="form-group">
									<label for="dtNascimentoNTitular">Data de Nascimento</label> 
									<input type="text" class="form-control input-sm" name="dtNascimentoNTitular" id="dtNascimentoNTitular" maxlength="10" value="${modelo.benefDtNasc}" data-date-format="DD/MM/YYYY" placeholder="dd/mm/aaaa"  />
								</div>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group">
									<label for="sexoNTitular">Sexo: </label> 
									<c:forEach items="${listaSexo}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.benefSexo}">
													<input type="radio" name="radioSexoNTitular" id="sexo${total.id}NTitular" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioSexoNTitular" id="sexo${total.id}NTitular" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group">
									<label for="observacoes">Observa&ccedil;&otilde;es</label> 
									<textarea class="form-control" rows="3" id="observacoes" name="observacoes" style="resize:none;" >${modelo.benefObs}</textarea>
								</div>
							</div>
						</div>
					</div>
					
					<div class="panel panel-primary">
						<div class="panel-heading"><label>Membro Familiar Vulner&aacute;vel</label></div>
						<div class="panel-body">
						
							<div class="col-sm-2">
								<div class="form-group">
									<label for="qtdeIdosos">Idosos com idade de 60 anos ou mais</label> 
									<input type="text" class="form-control input-sm" name="qtdeIdosos" id="qtdeIdosos" placeholder="Digite a quantidade" maxlength="2" value="${modelo.maior59Qtde}" onKeyPress="numerico(this)"/>
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label for="qtdeAdolescente">Adolescentes com idade de 18 anos ou menos</label> 
									<input type="text" class="form-control input-sm" name="qtdeAdolescente" id="qtdeAdolescente" placeholder="Digite a quantidade" maxlength="2" value="${modelo.menor19Qtde}" onKeyPress="numerico(this)"/>
								</div>
							</div>
							
							<div class="col-sm-2">
								<div class="form-group">
									<label for="qtdeAdolescente">Bebes com menos de 12 meses de idade</label> 
									<input type="text" class="form-control input-sm" name="qtdeBebes" id="qtdeBebes" placeholder="Digite a quantidade" maxlength="2" value="${modelo.menor1Qtde}" onKeyPress="numerico(this)"/>
								</div>
							</div>
							
							<div class="col-sm-12">
								<label>Pessoas com defici&ecirc;ncia: </label>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group row">
									<label for="inputPassword" class="col-sm-2 col-form-label" style="padding-top: 0.5%;">Visual:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control input-sm" name="defVisual" id="defVisual" placeholder="Digite a quantidade" maxlength="2" value="${modelo.defVisualQtde}" onKeyPress="numerico(this)"/>
									</div>
								</div>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group row">
									<label for="inputPassword" class="col-sm-2 col-form-label" style="padding-top: 0.5%;">Auditivo:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control input-sm" name="defAuditivo" id="defAuditivo" placeholder="Digite a quantidade" maxlength="2" value="${modelo.defAuditQtde}" onKeyPress="numerico(this)"/>
									</div>
								</div>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group row">
									<label for="inputPassword" class="col-sm-2 col-form-label" style="padding-top: 0.5%;">F&iacute;sico:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control input-sm" name="defFisico" id="defFisico" placeholder="Digite a quantidade" maxlength="2" value="${modelo.defFisQtde}" onKeyPress="numerico(this)"/>
									</div>
								</div>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group row">
									<label for="inputPassword" class="col-sm-2 col-form-label" style="padding-top: 0.5%;">Defici&ecirc;ncia Intelectual:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control input-sm" name="defIntelectual" id="defIntelectual" placeholder="Digite a quantidade" maxlength="2" value="${modelo.defIntelecQtde}" onKeyPress="numerico(this)"/>
									</div>
								</div>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group row">
									<label for="inputPassword" class="col-sm-2 col-form-label" style="padding-top: 0.5%;">Outros:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control input-sm" name="defOutros" id="defOutros" placeholder="Digite a quantidade" maxlength="2" value="${modelo.defOutrosQtde}" onKeyPress="numerico(this)"/>
									</div>
								</div>
							</div>
							
						    <div class="col-sm-12">
								<div class="form-group">
									<label for="tratamento">Membro Fam&iacute;lia em Tratamento: </label> 
								</div>
							</div>
						
							<div class="col-sm-12">
								<label class="checkbox-inline">
									<c:choose>
										<c:when test="${not empty modelo.tratCancer}">
											<input type="checkbox" name="checkCancer" id="checkCancer" checked >C&acirc;ncer</input>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="checkCancer" id="checkCancer" >C&acirc;ncer</input>
										</c:otherwise>
									</c:choose>
							    </label>
							</div> 
							<div class="col-sm-12"> 
								<label class="checkbox-inline">
									<c:choose>
										<c:when test="${not empty modelo.tratTuber}">
											<input type="checkbox" name="checkTuberculose" id="checkTuberculose" checked >Tuberculose</input>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="checkTuberculose" id="checkTuberculose" >Tuberculose</input>
										</c:otherwise>
									</c:choose>
							    </label>
							</div> 
							<div class="col-sm-12"> 
								<label class="checkbox-inline">
									<c:choose>
										<c:when test="${not empty modelo.tratHansen}">
											<input type="checkbox" name="checkHanseniase" id="checkHanseniase" checked >Hansen&iacute;ase</input>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="checkHanseniase" id="checkHanseniase" >Hansen&iacute;ase</input>
										</c:otherwise>
									</c:choose>
							    </label>
							</div> 
							<div class="col-sm-12"> 
								<label class="checkbox-inline">
									<c:choose>
										<c:when test="${not empty modelo.tratMental}">
											<input type="checkbox" name="checkMental" id="checkMental" checked >Aliena&ccedil;&atilde;o Mental</input>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="checkMental" id="checkMental" >Aliena&ccedil;&atilde;o Mental</input>
										</c:otherwise>
									</c:choose>
							    </label>
							</div> 
							<div class="col-sm-12"> 
								<label class="checkbox-inline">
									<c:choose>
										<c:when test="${not empty modelo.tratEscler}">
											<input type="checkbox" name="checkEsclerose" id="checkEsclerose" checked >Esclerose M&uacute;ltipla</input>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="checkEsclerose" id="checkEsclerose" >Esclerose M&uacute;ltipla</input>
										</c:otherwise>
									</c:choose>
							    </label>
							</div> 
							<div class="col-sm-12"> 
								<label class="checkbox-inline">
									<c:choose>
										<c:when test="${not empty modelo.tratParal}">
											<input type="checkbox" name="checkParalisia" id="checkParalisia" checked >Paralisia Irrevers&iacute;vel ou Incapacitante</input>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="checkParalisia" id="checkParalisia" >Paralisia Irrevers&iacute;vel ou Incapacitante</input>
										</c:otherwise>
									</c:choose>
							    </label>
							</div> 
							<div class="col-sm-12"> 
								<label class="checkbox-inline">
									<c:choose>
										<c:when test="${not empty modelo.tratHepat}">
											<input type="checkbox" name="checkHepatica" id="checkHepatica" checked >Doen&ccedil;as Hep&aacute;ticas (F&iacute;gado)</input>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="checkHepatica" id="checkHepatica" >Doen&ccedil;as Hep&aacute;ticas (F&iacute;gado)</input>
										</c:otherwise>
									</c:choose>
							    </label>
							</div> 
							<div class="col-sm-12"> 
								<label class="checkbox-inline">
									<c:choose>
										<c:when test="${not empty modelo.tratCardiac}">
											<input type="checkbox" name="checkCardiaca" id="checkCardiaca" checked >Doen&ccedil;as Card&iacute;acas</input>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="checkCardiaca" id="checkCardiaca" >Doen&ccedil;as Card&iacute;acas</input>
										</c:otherwise>
									</c:choose>
							    </label>
							</div> 
							<div class="col-sm-12"> 
								<label class="checkbox-inline">
									<c:choose>
										<c:when test="${not empty modelo.tratParkins}">
											<input type="checkbox" name="checkParkinson" id="checkParkinson" checked >Doen&ccedil;as de Parkinson</input>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="checkParkinson" id="checkParkinson" >Doen&ccedil;as de Parkinson</input>
										</c:otherwise>
									</c:choose>
							    </label>
							</div> 
							<div class="col-sm-12"> 
								<label class="checkbox-inline">
									<c:choose>
										<c:when test="${not empty modelo.tratRenal}">
											<input type="checkbox" name="checkRenais" id="checkRenais" checked >Doen&ccedil;as Renais (Rins)</input>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="checkRenais" id="checkRenais" >Doen&ccedil;as Renais (Rins)</input>
										</c:otherwise>
									</c:choose>
							    </label>
						    </div>
						    <div class="col-sm-12"> 
								<label class="checkbox-inline">
									<c:choose>
										<c:when test="${not empty modelo.tratOutro}">
											<input type="checkbox" name="checkOutro" id="checkOutro" checked onClick="verificaOutraDoenca()">Outro</input>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="checkOutro" id="checkOutro" onClick="verificaOutraDoenca()">Outro</input>
										</c:otherwise>
									</c:choose>
							    </label>
						    </div>
						    
						    <div class="col-sm-6">
								<div class="form-group">
									<label for="outroDoenca">Qual</label> 
									<input type="text" class="form-control input-sm" name="outroDoenca" id="outroDoenca" maxlength="60" value="${modelo.tratOutroDesc}" />
								</div>
							</div>
	
							<div class="col-sm-12">
								<label>O Entrevistado ou Algum Membro Da Familia J&aacute; Teve Alguma Das Doen&ccedil;as Listadas Abaixo: </label> 
							</div>
							
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline">
										<c:choose>
											<c:when test="${not empty modelo.amebiase}">
												<input type="checkbox" name="checkAmebiase" id="checkAmebiase" checked >Ameb&iacute;ase</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkAmebiase" id="checkAmebiase" >Ameb&iacute;ase</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="amebiase" id="amebiase" placeholder="Membro Familiar" maxlength="100" value="${modelo.amebiaseMemb}" />
									</div>
								</div>
						    </div>
							
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline"> 
										<c:choose>
											<c:when test="${not empty modelo.gastroent}">
												<input type="checkbox" name="checkGastroenterite" id="checkGastroenterite" checked >Gastroenterite</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkGastroenterite" id="checkGastroenterite" >Gastroenterite</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="gastroenterite" id="gastroenterite" placeholder="Membro Familiar" maxlength="100" value="${modelo.gastroentMemb}" />
									</div>
								</div>
						    </div>
							
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline"> 
										<c:choose>
											<c:when test="${not empty modelo.giardiase}">
												<input type="checkbox" name="checkGiardiase" id="checkGiardiase" checked >Giard&iacute;ase e Criptosporid&iacute;ase</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkGiardiase" id="checkGiardiase" >Giard&iacute;ase e Criptosporid&iacute;ase</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="giardiase" id="giardiase" placeholder="Membro Familiar" maxlength="100" value="${modelo.giardiaseMemb}" />
									</div>
								</div>
						    </div>
						    
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline"> 
										<c:choose>
											<c:when test="${not empty modelo.febreTifo}">
												<input type="checkbox" name="checkTifoide" id="checkTifoide" checked >Febres Tif&oacute;ide e Paratif&oacute;ide</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkTifoide" id="checkTifoide" >Febres Tif&oacute;ide e Paratif&oacute;ide</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="tifoide" id="tifoide" placeholder="Membro Familiar" maxlength="100" value="${modelo.febreTifoMemb}" />
									</div>
								</div>
						    </div>
						    
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline"> 
										<c:choose>
											<c:when test="${not empty modelo.hepatite}">
												<input type="checkbox" name="checkHepatite" id="checkHepatite" checked >Hepatite Infecciosa</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkHepatite" id="checkHepatite" >Hepatite Infecciosa</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="hepatite" id="hepatite" placeholder="Membro Familiar" maxlength="100" value="${modelo.hepatiteMemb}" />
									</div>
								</div>
						    </div>
						    
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline"> 
										<c:choose>
											<c:when test="${not empty modelo.colera}">
												<input type="checkbox" name="checkColera" id="checkColera" checked >C&oacute;lera</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkColera" id="checkColera" >C&oacute;lera</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="colera" id="colera" placeholder="Membro Familiar" maxlength="100" value="${modelo.coleraMemb}" />
									</div>
								</div>
						    </div>
						    
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline"> 
										<c:choose>
											<c:when test="${not empty modelo.esquitosso}">
												<input type="checkbox" name="checkEsquistossomose" id="checkEsquistossomose" checked >Esquistossomose (XISTOSA)</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkEsquistossomose" id="checkEsquistossomose" >Esquistossomose (XISTOSA)</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="esquistossomose" id="esquistossomose" placeholder="Membro Familiar" maxlength="100" value="${modelo.esquitossoMemb}" />
									</div>
								</div>
						    </div>
						    
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline"> 
										<c:choose>
											<c:when test="${not empty modelo.ascaridiase}">
												<input type="checkbox" name="checkAscaridiase" id="checkAscaridiase" checked >Ascarid&iacute;ase (Lombrigas ou Bichas)</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkAscaridiase" id="checkAscaridiase" >Ascarid&iacute;ase (Lombrigas ou Bichas)</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="ascaridiase" id="ascaridiase" placeholder="Membro Familiar" maxlength="100" value="${modelo.ascaridiaseMemb}" />
									</div>
								</div>
						    </div>
						    
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline"> 
										<c:choose>
											<c:when test="${not empty modelo.teniase}">
												<input type="checkbox" name="checkTeniase" id="checkTeniase" checked >Ten&iacute;ase (Solit&aacute;ria)</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkTeniase" id="checkTeniase" >Ten&iacute;ase (Solit&aacute;ria)</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="teniase" id="teniase" placeholder="Membro Familiar" maxlength="100" value="${modelo.teniaseMemb}" />
									</div>
								</div>
						    </div>
						    
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline"> 
										<c:choose>
											<c:when test="${not empty modelo.leptospirose}">
												<input type="checkbox" name="checkLeptospirose" id="checkLeptospirose" checked >Leptospirose</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkLeptospirose" id="checkLeptospirose" >Leptospirose</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="leptospirose" id="leptospirose" placeholder="Membro Familiar" maxlength="100" value="${modelo.leptospiroseMemb}" />
									</div>
								</div>
						    </div>
						    
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline"> 
										<c:choose>
											<c:when test="${not empty modelo.malaria}">
												<input type="checkbox" name="checkMalaria" id="checkMalaria" checked >Mal&aacute;ria</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkMalaria" id="checkMalaria" >Mal&aacute;ria</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="malaria" id="malaria" placeholder="Membro Familiar" maxlength="100" value="${modelo.malariaMemb}" />
									</div>
								</div>
						    </div>
						    
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline"> 
										<c:choose>
											<c:when test="${not empty modelo.dengue}">
												<input type="checkbox" name="checkDengue" id="checkDengue" checked >Dengue</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkDengue" id="checkDengue" >Dengue</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="dengue" id="dengue" placeholder="Membro Familiar" maxlength="100" value="${modelo.dengueMemb}" />
									</div>
								</div>
						    </div>
						    
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline"> 
										<c:choose>
											<c:when test="${not empty modelo.febreAmar}">
												<input type="checkbox" name="checkFebreAmarela" id="checkFebreAmarela" checked >Febre Amarela</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkFebreAmarela" id="checkFebreAmarela" >Febre Amarela</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="febreAmarela" id="febreAmarela" placeholder="Membro Familiar" maxlength="100" value="${modelo.febreAmarMemb}" />
									</div>
								</div>
						    </div>
						    
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline">
										<c:choose>
											<c:when test="${not empty modelo.chikung}">
												<input type="checkbox" name="checkChikungunya" id="checkChikungunya" checked >Chikungunya</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkChikungunya" id="checkChikungunya" >Chikungunya</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="chikungunya" id="chikungunya" placeholder="Membro Familiar" maxlength="100" value="${modelo.chikungMemb}" />
									</div>
								</div>
						    </div>
						    
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline">
										<c:choose>
											<c:when test="${not empty modelo.zicaVirus}">
												<input type="checkbox" name="checkZika" id="checkZika" checked >Zika V&iacute;rus</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkZika" id="checkZika" >Zika V&iacute;rus</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="zika" id="zika" placeholder="Membro Familiar" maxlength="100" value="${modelo.zicaVirusMemb}" />
									</div>
								</div>
						    </div>
						    
							<div class="col-sm-12">
								<div class="col-sm-3" style="padding-top: 0.5%;">
									<label class="checkbox-inline">
										<c:choose>
											<c:when test="${not empty modelo.cianobacter}">
												<input type="checkbox" name="checkCianobacterias" id="checkCianobacterias" checked >Cianobact&eacute;rias</input>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="checkCianobacterias" id="checkCianobacterias" >Cianobact&eacute;rias</input>
											</c:otherwise>
										</c:choose>
								    </label>
							    </div>
							    <div class="col-sm-3">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="cianobacterias" id="cianobacterias" placeholder="Membro Familiar" maxlength="100" value="${modelo.cianobacterMemb}" />
									</div>
								</div>
						    </div>
						</div>
					</div>
					
					<div class="panel panel-primary">
						<div class="panel-heading"><label>Detalhamento de Recebimento Financeiro</label></div>
						<div class="panel-body">

							<div class="col-sm-12">
								<div class="form-group row">
									<label for="inputPassword" class="col-sm-3 col-form-label" style="padding-top: 0.5%;">Ajuda ou Doa&ccedil;&atilde;o:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control input-sm" name="valorDoacao" id="valorDoacao" placeholder="Valor R$" maxlength="12" value="${modelo.valDoacao}" onBlur="moeda(this)"  onKeyPress="moeda(this)"/>
									</div>
								</div>
							</div>
	
							<div class="col-sm-12">
								<div class="form-group row">
									<label for="inputPassword" class="col-sm-3 col-form-label" style="padding-top: 0.5%;">Aposentadoria, Pens&atilde;o, Benef&iacute;cio de Presta&ccedil;&atilde;o Continuada-BPC:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control input-sm" name="valorAposentadoria" id="valorAposentadoria" placeholder="Valor R$" maxlength="12" value="${modelo.valAposent}" onBlur="moeda(this)"  onKeyPress="moeda(this)"/>
									</div>
								</div>
							</div>
	
							<div class="col-sm-12">
								<div class="form-group row">
									<label for="inputPassword" class="col-sm-3 col-form-label" style="padding-top: 0.5%;">Pens&atilde;o Aliment&iacute;cia:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control input-sm" name="valorPensao" id="valorPensao" placeholder="Valor R$" maxlength="12" value="${modelo.valPensaoAlimen}" onBlur="moeda(this)"  onKeyPress="moeda(this)"/>
									</div>
								</div>
							</div>
	
							<div class="col-sm-12">
								<div class="form-group row">
									<label for="inputPassword" class="col-sm-3 col-form-label" style="padding-top: 0.5%;">Seguro Desemprego:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control input-sm" name="valorSeguroDesemp" id="valorSeguroDesemp" placeholder="Valor R$" maxlength="12" value="${modelo.valSegDesempr}" onBlur="moeda(this)"  onKeyPress="moeda(this)"/>
									</div>
								</div>
							</div>
	
							<div class="col-sm-12">
								<div class="form-group row">
									<label for="inputPassword" class="col-sm-3 col-form-label" style="padding-top: 0.5%;">Empregado sem Carteira Assinada:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control input-sm" name="valorSemCarteira" id="valorSemCarteira" placeholder="Valor R$" maxlength="12" value="${modelo.valEmprInformal}" onBlur="moeda(this)"  onKeyPress="moeda(this)"/>
									</div>
								</div>
							</div>
	
							<div class="col-sm-12">
								<div class="form-group row">
									<label for="inputPassword" class="col-sm-3 col-form-label" style="padding-top: 0.5%;">Empregado com Carteira Assinada:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control input-sm" name="valorComCarteira" id="valorComCarteira" placeholder="Valor R$" maxlength="12" value="${modelo.valEmprFormal}" onBlur="moeda(this)"  onKeyPress="moeda(this)"/>
									</div>
								</div>
							</div>
	
							<div class="col-sm-12">
								<div class="form-group row">
									<label for="inputPassword" class="col-sm-3 col-form-label" style="padding-top: 0.5%;">Bolsa Familia:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control input-sm" name="valorBolsaFamilia" id="valorBolsaFamilia" placeholder="Valor R$" maxlength="12" value="${modelo.valBolsaFamil}" onBlur="moeda(this)"  onKeyPress="moeda(this)"/>
									</div>
								</div>
							</div>
	
							<div class="col-sm-12">
								<div class="form-group row">
									<label for="inputPassword" class="col-sm-3 col-form-label" style="padding-top: 0.5%;">Outro:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control input-sm" name="valorOutros" id="valorOutros" placeholder="Valor R$" maxlength="12" value="${modelo.valOutro}" onBlur="moeda(this)"  onKeyPress="moeda(this)"/>
									</div>
								</div>
							</div>

							<div class="col-sm-6">
								<div class="form-group">
									<label for="qtdeAdolescente">Qual</label> 
									<input type="text" class="form-control input-sm" name="valorOutrosDesc" id="valorOutrosDesc" maxlength="60" value="${modelo.valOutroDescr}" />
								</div>
							</div>
							
						</div>
					</div>
					
					<div class="panel panel-primary">
						<div class="panel-heading"><label>Grau de Escolaridade</label></div>
						<div class="panel-body">
	
							<div class="col-sm-12">
								<div class="form-group">
									<label for="ler">Grau: </label> 
									<c:forEach items="${listaGrauEnsino}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.idGrauEscol}">
													<input type="radio" name="radioEnsino" id="ensino${total.id}r" value="${total.id}" checked onclick="verificaAnalfabeto()">${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioEnsino" id="ensino${total.id}" value="${total.id}" onclick="verificaAnalfabeto()">${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
					    	<div class="col-sm-6">
						    	<div class="form-group">
						    		<label for="ler">Situa&ccedil;&atilde;o: </label> 
						    		<c:forEach items="${listaSituacaoEnsino}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.grauEscolCompl}">
													<input type="radio" name="radioEnsinoSituacao" id="situacaoEnsino${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioEnsinoSituacao" id="SituacaoEnsino${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-12">
								<label>Se Analfabeto, informar: </label> 
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label for="ler">Consegue Ler: </label> 
									<c:forEach items="${listaSimNao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.sabeLer}">
													<input type="radio" name="radioConsegueLer" id="consegueLer${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioConsegueLer" id="consegueLer${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
	
							<div class="col-sm-3">
								<div class="form-group">
									<label for="ler">Consegue Escrever: </label> 
									<c:forEach items="${listaSimNao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.sabeEscrever}">
													<input type="radio" name="radioConsegueEscrever" id="consegueEscrever${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioConsegueEscrever" id="consegueEscrever${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-12">
								<label>Grau de Escolaridade Dos Membros da Fam&iacute;lia: </label> 
							</div>
							
							<div class="col-sm-12">
								<% int cont = 1;%>
								<c:choose>
									<c:when test="${not empty listaGrauMembros}">
										<c:forEach items="${listaGrauMembros}" var="listaMembro">
											<input type="hidden" id="idGrauMembro<%=cont%>" name="idGrauMembro<%=cont%>" value="${listaMembro.idDiagnoEscolParente}" />
											<div class="form-group">
												<div class="col-sm-8">
													<input type="text" class="form-control" id="grauMembro<%=cont%>" name="grauMembro<%=cont%>" placeholder="Membro Familia" value="${listaMembro.nome}"></input>
												</div>
												<div class="col-sm-4">
													<input type="text" class="form-control" id="parentescoMembro<%=cont%>" name="parentescoMembro<%=cont%>" placeholder="Parentesco" value="${listaMembro.parentesco}"></input>
												</div>
											
												<c:forEach items="${listaGrauEnsino}" var="total">
													<label class="radio-inline">
														<c:choose>
															<c:when test="${total.id == listaMembro.idGrauEscol}">
																<input type="radio" name="radioEnsinoMembro<%=cont%>" id="radioEnsinoMembro<%=cont%>" value="${total.id}" checked >${total.descricao}</input>
															</c:when>
															<c:otherwise>
																<input type="radio" name="radioEnsinoMembro<%=cont%>" id="radioEnsinoMembro<%=cont%>" value="${total.id}" >${total.descricao}</input>
															</c:otherwise>
														</c:choose>
													</label> 
												</c:forEach>
											</div>
											<%cont++;%>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<div class="form-group">
											<div class="col-sm-8">
												<input type="text" class="form-control" id="grauMembro<%=cont%>" name="grauMembro<%=cont%>" placeholder="Nome Membro"></input>
											</div>
											<div class="col-sm-4">
												<input type="text" class="form-control" id="parentescoMembro<%=cont%>" name="parentescoMembro<%=cont%>" placeholder="Parentesco"></input>
											</div>
											<c:forEach items="${listaGrauEnsino}" var="total">
												<label class="radio-inline">
													<c:choose>
														<c:when test="${total.id == listaMembro.idGrauEscol}">
															<input type="radio" name="radioEnsinoMembro<%=cont%>" id="radioEnsino${total.id}Membro<%=cont%>" value="${total.id}" checked >${total.descricao}</input>
														</c:when>
														<c:otherwise>
															<input type="radio" name="radioEnsinoMembro<%=cont%>" id="radioEnsino${total.id}Membro<%=cont%>" value="${total.id}" >${total.descricao}</input>
														</c:otherwise>
													</c:choose>
												</label> 
											</c:forEach>
									    </div>
									    <%cont++;%>
									</c:otherwise>
								</c:choose>
								<div id="aqui<%=cont%>"></div>
								<div class="form-group">
									<div class="col-sm-offset-0">
							        	<button type="button" class="btn btn-primary" onClick="addCampo()">Adicionar</button>
							      	</div>
						      	</div>
								<input type="hidden" id="cont" name="cont" value="<%=cont%>" />
						  	</div> 
						  	
							<div class="col-sm-12">
								<div class="form-group">
									<label for="profissaoResponsavel">Qual &eacute; a Profiss&atilde;o do Membro da Fam&iacute;lia Respons&aacute;vel pela Resid&ecirc;ncia</label> 
									<input type="text" class="form-control input-sm" name="profissaoResponsavel" id="profissaoResponsavel" maxlength="60" value="${modelo.profissRespFamil}"  />
								</div>
							</div>
							
						</div>
					</div>
					
					<div class="panel panel-primary">
						<div class="panel-heading"><label>Negocia&ccedil;&atilde;o</label></div>
						<div class="panel-body">
						
							<div class="col-sm-12">
								<label>Negocia&ccedil;&atilde;o D&eacute;bitos Energia El&eacute;trica: </label> 
							</div>
						
							<div class="col-sm-12">
								<div class="form-group">
									<label for="negociacao">Existe Negocia&ccedil;&atilde;o De D&eacute;bitos em Aberto: </label> 
									<c:forEach items="${listaSimNao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.energNegoc}">
													<input type="radio" name="radioNegociacaoEnergia" id="negociacaoEnergia${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioNegociacaoEnergia" id="negociacaoEnergia${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach>
								</div>
							</div>
							
							<div class="col-sm-2">
								<div class="form-group">
									<label for="qtdeParcelaEnergia">Qtde de Parcelas</label> 
									<input type="text" class="form-control input-sm" name="qtdeParcelaEnergia" id="qtdeParcelaEnergia" maxlength="4" value="${modelo.energNegocParcQtde}" onKeyPress="numerico(this)"/> 
								</div>
							</div>
							
							<div class="col-sm-2">
								<div class="form-group">
									<label for="valorParcelaEnergia">Valor da Parcela</label> 
									<input type="text" class="form-control input-sm" name="valorParcelaEnergia" id="valorParcelaEnergia" maxlength="12" value="${modelo.energNegocParcVal}" onBlur="moeda(this)"  onKeyPress="moeda(this)"/> 
								</div>
							</div>
							
							<div class="col-sm-2">
								<div class="form-group">
									<label for="diaParcelaEnergia">Dia do Vencimento</label> 
									<input type="text" class="form-control input-sm" name="diaParcelaEnergia" id="diaParcelaEnergia" maxlength="2" value="${modelo.energNegocDia}" onKeyPress="numerico(this)"/> 
								</div>
							</div>
						
							<div class="col-sm-12">
								<label>Negocia&ccedil;&atilde;o D&eacute;bitos Ligacao de &Aacute;gua: </label>
							</div>
						
							<div class="col-sm-12">
								<div class="form-group">
									<label for="negociacao">Existe Negocia&ccedil;&atilde;o De D&eacute;bitos em Aberto: </label>
									<c:forEach items="${listaSimNao}" var="total">
										<label class="radio-inline">
											<c:choose>
												<c:when test="${total.id == modelo.aguaNegoc}">
													<input type="radio" name="radioNegociacaoAgua" id="negociacaoAgua${total.id}" value="${total.id}" checked >${total.descricao} </input>
												</c:when>
												<c:otherwise>
													<input type="radio" name="radioNegociacaoAgua" id="negociacaoAgua${total.id}" value="${total.id}" >${total.descricao} </input>
												</c:otherwise>
											</c:choose>
										</label> 
									</c:forEach> 
								</div>
							</div>
							
							<div class="col-sm-2">
								<div class="form-group">
									<label for="qtdeParcelaAgua">Qtde de Parcelas</label> 
									<input type="text" class="form-control input-sm" name="qtdeParcelaAgua" id="qtdeParcelaAgua" maxlength="4" value="${modelo.aguaNegocParcQtde}" onKeyPress="numerico(this)"/> 
								</div>
							</div>
							
							<div class="col-sm-2">
								<div class="form-group">
									<label for="valorParcelaAgua">Valor da Parcela</label> 
									<input type="text" class="form-control input-sm" name="valorParcelaAgua" id="valorParcelaAgua" maxlength="12" value="${modelo.aguaNegocParcVal}" onBlur="moeda(this)"  onKeyPress="moeda(this)"/> 
								</div>
							</div>
							
							<div class="col-sm-2">
								<div class="form-group">
									<label for="diaParcelaAgua">Dia do Vencimento</label> 
									<input type="text" class="form-control input-sm" name="diaParcelaAgua" id="diaParcelaAgua" maxlength="2" value="${modelo.aguaNegocDia}" onKeyPress="numerico(this)"/> 
								</div>
							</div>
						</div>
					</div>
						
					<div class="panel panel-primary">
						<div class="panel-heading"><label>Demais Informa&ccedil;&otilde;es</label></div>
						<div class="panel-body">
							
							<div class="col-sm-10">
								<div class="form-group">
									<label for="empresaExecutar">Tem Conhecimento de Alguma Obra de Saneamento que Acontecer&aacute; na &Aacute;rea em que Reside? Qual Empresa ir&aacute; Executar?</label> 
									<input type="text" class="form-control input-sm" name="empresaExecutar" id="empresaExecutar" maxlength="100" value="${modelo.obraSaneamConhe}" /> 
								</div>
							</div>
							
							<div class="col-sm-12">
								<label>Quais os Benef&iacute;cios que voc&ecirc; Acredita que as Obras de Saneamento Podem Trazer para a Comunidade?</label> 
							</div>
							
							<div class="col-sm-12"> 
								<label class="checkbox-inline">
									<c:choose>
										<c:when test="${not empty modelo.benefObraSanSaude}">
											<input type="checkbox" name="checkBeneficioPrevencaoDoenca" id="checkBeneficioPrevencaoDoenca" checked >Preven&ccedil;&atilde;o de Doen&ccedil;as e Promoc&atilde;o da Sa&uacute;de</input>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="checkBeneficioPrevencaoDoenca" id="checkBeneficioPrevencaoDoenca" >Preven&ccedil;&atilde;o de Doen&ccedil;as e Promoc&atilde;o da Sa&uacute;de</input>
										</c:otherwise>
									</c:choose>
							    </label>
							</div>
							
							<div class="col-sm-12"> 
								<label class="checkbox-inline">
									<c:choose>
										<c:when test="${not empty modelo.benefObraSanEco}">
											<input type="checkbox" name="checkBeneficioFortalecimento" id="checkBeneficioFortalecimento" checked >Fortalecimento da Economia Local</input>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="checkBeneficioFortalecimento" id="checkBeneficioFortalecimento" >Fortalecimento da Economia Local</input>
										</c:otherwise>
									</c:choose>
							    </label>
							</div>
							
							<div class="col-sm-12"> 
								<label class="checkbox-inline">
									<c:choose>
										<c:when test="${not empty modelo.benefObraSanImob}">
											<input type="checkbox" name="checkBeneficioValorizacaoImovel" id="checkBeneficioValorizacaoImovel" checked >Valoriza&ccedil;&atilde;o Imobili&aacute;ria</input>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="checkBeneficioValorizacaoImovel" id="checkBeneficioValorizacaoImovel" >Valoriza&ccedil;&atilde;o Imobili&aacute;ria</input>
										</c:otherwise>
									</c:choose>
							    </label>
							</div>
							
							<div class="col-sm-12"> 
								<label class="checkbox-inline">
									<c:choose>
										<c:when test="${not empty modelo.benefObraSanTuri}">
											<input type="checkbox" name="checkBeneficioValorizacaoTurismo" id="checkBeneficioValorizacaoTurismo" checked >Valoriza&ccedil;&atilde;o do Turismo Local</input>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="checkBeneficioValorizacaoTurismo" id="checkBeneficioValorizacaoTurismo" >Valoriza&ccedil;&atilde;o do Turismo Local</input>
										</c:otherwise>
									</c:choose>
							    </label>
							</div>
							
							<div class="col-sm-12">
								<div class="form-group">
									<label>Observa&ccedil;&otilde;es</label> 
									<textarea class="form-control" rows="3" id="observacoesInformacoes" name="observacoesInformacoes" style="resize:none;" >${modelo.obsGerais}</textarea>
								</div>
							</div>
					
						</div>
					</div>
					
					
				</div>
				<div class="form-group">
			    	<div class="col-sm-offset-6">
			        	<button type="submit" class="btn btn-primary">${botao}</button>
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
                $('#dtExecucao').datetimepicker();
                $('#dtNascimento').datetimepicker();
                $('#dtNascimentoNTitular').datetimepicker();
            });
        </script>
    </body>
</html>