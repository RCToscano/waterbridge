
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
		<title>WaterBridge</title>
		<link rel="icon" type="image/png" href="./images/favicon.ico"/>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
	    <script src="http://code.jquery.com/jquery-2.2.4.js" ></script>	
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="./js/jquery.mask.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment.min.js"></script>
	    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/locale/pt-br.js"></script>
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
	    <link href="./css/menucustomcolor.css" rel="stylesheet"/>
	    <link href="./css/footercustom.css" rel="stylesheet"/>
	    <script src='./js/usuario/consulta.js'></script>
    </head>
    <body>
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Condomínio</li>
			    <li class="active">Consumo</li>
			</ul>
			
			
			<div id="divAviso" name="divAviso" class="alert alert-danger" style="display:${display};">
				<strong><label id='aviso' name='aviso'/>${aviso}</strong>
			</div>
			
			<c:if test="${not empty sucesso}">
				<div class="alert alert-success">
					<strong><c:out value="${sucesso}"/></strong>
				</div>
			</c:if>
			
			<c:if test="${not empty informacao}">
				<div class="alert alert-warning">
					<strong><c:out value="${informacao}"/></strong>
				</div>
			</c:if>
			
			<div class="form-group">
<!-- 				<form action="#" method="post" accept-charset="iso-8859-1,utf-8"> -->
					<fieldset>
						<legend class="text-left">Consulta de Consumo por Período</legend>
						
						<div class="col-sm-8">
							<div class="form-group">
								<label>Condomínio</label> 
								<select class="form-control" id="idCondominio" name="idCondominio" required >
									<option value="1" selected>Condomínio Vertentes do Morumbi - Av. Francisco Morato, 2000</option>
									<c:forEach var="condominio" items="${listCondominio}">
		                   		        <c:choose>
		                                  	<c:when test="${condominio.idCondominio == medidor.idCondominio}">
		                                 		<option value="${condominio.idCondominio}" selected="true">${condominio.nome} - ${condominio.endereco} ${condominio.numero} ${condominio.compl}</option>
		                                   	</c:when>
		                                   	<c:otherwise>
		                                   		<option value="${condominio.idCondominio}">${condominio.nome} - ${condominio.endereco} ${condominio.numero} ${condominio.compl}</option>
		                                   	</c:otherwise>
		                            	</c:choose>
			                     	</c:forEach>
								</select>	
							</div>					
						</div>
						
						<div class="col-sm-4">
							<div class="form-group">
								<label>Período</label>
								<div class="col-sm-4 col-md-12" style="padding: 0px; margin: 0px;">
									<div class="col-sm-4 col-md-6" style="padding-left: 0px;">
										<input type="text" class="form-control" data-date-format="DD/MM/YYYY" name="dtInicio" id="dtInicio" placeholder="Início" maxlength="10" value="01/04/2018" required/>
									</div>
									<div class="col-sm-4 col-md-6" style="padding-left: 0px;">
										<input type="text" class="form-control" data-date-format="DD/MM/YYYY" name="dtFim" id="dtFim" placeholder="Fim" maxlength="10" value="30/04/2018" required/>
									</div>
								</div>
								 <script type="text/javascript">
						            $(function () {
						                $('#dtInicio').datetimepicker({locale: 'pt-br'});
						                $('#dtFim').datetimepicker({locale: 'pt-br'});
						            });
						        </script>
					        </div>
						</div>
						
						<div class="form-group">
							<div class="col-md-12 text-center">
								<button class="btn btn-primary">Consultar</button>
							</div>
						</div>
						
					</fieldset>
<!-- 				</form> -->
			</div>
		
<%-- 			<c:if test="${not empty lista}"> --%>
				<div class="col-sm-8 col-md-offset-2">
					<div class="form-group">
						<input class="form-control" id="myInput" type="text" placeholder="Utilize para procurar..."></input> <br />
						<div class="table-responsive" id="divTable">
							<table class="table table-hover table-striped">
								<thead>
									<tr>
										<th style="width:1%;">Nº</th>
										<th style="width:30%;">Consumidor</th>
										<th style="width:15%;">Medidor</th>
										<th style="width:15%;">Consumo (m³)</th>
									</tr>
								</thead>
								<tbody id="myTable">
									<tr><td>1</td><td>Consumidor 1</td><td>522132349</td><td>10</td></tr>
									<tr><td>2</td><td>Consumidor 2</td><td>143860292</td><td>14</td></tr>
									<tr><td>3</td><td>Consumidor 3</td><td>910061356</td><td>13</td></tr>
									<tr><td>4</td><td>Consumidor 4</td><td>86501894</td><td>21</td></tr>
									<tr><td>5</td><td>Consumidor 5</td><td>193243852</td><td>34</td></tr>
									<tr><td>6</td><td>Consumidor 6</td><td>448325841</td><td>53</td></tr>
									<tr><td>7</td><td>Consumidor 7</td><td>477691803</td><td>70</td></tr>
									<tr><td>8</td><td>Consumidor 8</td><td>138518373</td><td>12</td></tr>
									<tr><td>9</td><td>Consumidor 9</td><td>913207692</td><td>45</td></tr>
									<tr><td>10</td><td>Consumidor 10</td><td>553681505</td><td>24</td></tr>
									<tr><td>11</td><td>Consumidor 11</td><td>95287595</td><td>24</td></tr>
									<tr><td>12</td><td>Consumidor 12</td><td>903116494</td><td>24</td></tr>
									<tr><td>13</td><td>Consumidor 13</td><td>081613625</td><td>82</td></tr>
									<tr><td>14</td><td>Consumidor 14</td><td>101529775</td><td>22</td></tr>
									<tr><td>15</td><td>Consumidor 15</td><td>569838026</td><td>23</td></tr>
									<tr><td>16</td><td>Consumidor 16</td><td>789299604</td><td>23</td></tr>
									<tr><td>17</td><td>Consumidor 17</td><td>660519307</td><td>13</td></tr>
									<tr><td>18</td><td>Consumidor 18</td><td>109241396</td><td>14</td></tr>
									<tr><td>19</td><td>Consumidor 19</td><td>456263977</td><td>31</td></tr>
									<tr><td>20</td><td>Consumidor 20</td><td>033224158</td><td>24</td></tr>
									<tr><td>21</td><td>Consumidor 21</td><td>071699058</td><td>22</td></tr>
									<tr><td>22</td><td>Consumidor 22</td><td>902875305</td><td>23</td></tr>
									<tr><td>23</td><td>Consumidor 23</td><td>161013639</td><td>24</td></tr>
									<tr><td>24</td><td>Consumidor 24</td><td>687569959</td><td>21</td></tr>
									<tr><td>25</td><td>Consumidor 25</td><td>509340778</td><td>43</td></tr>
									<tr><td>26</td><td>Consumidor 26</td><td>944235675</td><td>22</td></tr>
									<tr><td>27</td><td>Consumidor 27</td><td>118606262</td><td>22</td></tr>
									<tr><td>28</td><td>Consumidor 28</td><td>648843107</td><td>24</td></tr>
									<tr><td>29</td><td>Consumidor 29</td><td>172560759</td><td>23</td></tr>
									<tr><td>30</td><td>Consumidor 30</td><td>030282276</td><td>13</td></tr>
									<tr>
										<td colspan="4" style="text-align: center">
											<label>Consumo total no período em m³: 813</label>
										</td>
									</tr>
									<tr>
										<td colspan="4" style="text-align: center">
											<form action="CondominioBO?acao=consumoGrafico" method="post" target="_blank">
												<button type="submit" class="btn btn-warning">
											    	<i class="fa fa-bar-chart"></i> Gráfico
											    </button>
											</form>    
										</td>
									</tr>

								</tbody>
							</table>
						</div>
					</div>
				</div>
<%-- 			</c:if> --%>
					
				
		</div>
        <footer class="footer" style="background-color: #fff">
            <div class="container-fluid text-center" style="background-color: #fff; padding: 10px">
            	<div class="col-sm-4 text-center"><p class="text-muted">Todos os direitos reservados</p></div>
            	<div class="col-sm-4 text-center"><p class="text-muted"><label>Desenvolvido por Desoltec Engenharia</label></p></div>
            	<div class="col-sm-4 text-center"><img class="img-responsive center-block" src="./images/logo_desoltec_rodape.png" alt=""></div>
            </div>
        </footer>
		<script>
        	$(document).ready(function(){
			  $("#myInput").on("keyup", function() {
			    var value = $(this).val().toLowerCase();
			    $("#myTable tr").filter(function() {
			      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			    });
			  });
			});
        
	        jQuery(document).ready(function($) {
	            $(".clickable-row").click(function() {
	                window.location = $(this).data("href");
	            });
	        });
	        
	        $(document).ready(function(){
	            $('[data-toggle="tooltip"]').tooltip(); 
	        });
		</script>
    </body>
</html>