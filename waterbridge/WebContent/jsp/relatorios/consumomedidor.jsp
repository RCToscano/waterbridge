
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
		<title>WaterBridge</title>
		<link rel="icon" type="image/png" href="./images/favicon.ico"/>
        
        <script src="./js/funcoes.auxiliares.js" type="text/javascript"></script>
	    <script src="./js/relatorios/consumomedidor.js" type="text/javascript"></script>
	    
   		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	    <script src="http://code.jquery.com/jquery-2.2.4.js" ></script>	
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script src="http://malsup.github.io/jquery.blockUI.js"></script>

		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
			
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment.min.js"></script>
	    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/locale/pt-br.js"></script>
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
	    
	    <link href="./css/menucustomcolor.css" rel="stylesheet"/>
	    <link href="./css/footercustom.css" rel="stylesheet"/>
	    
    </head>
    <body>
        <jsp:include page="/menu/${sessionScope.user.perfil.menu}" ></jsp:include>
        <div class="container">
        	<ul class="breadcrumb">
			    <li><a href="HomeBO?acao=home">Home</a></li>
			    <li class="active">Relatórios</li>
			    <li class="active">Consumo por Medidor</li>
			</ul>
			
			<fieldset>
				<legend class="text-left">Consumo por Medidor</legend>
			</fieldset>	
			<div id="divAviso">${aviso}</div>
			<form role="form" id="formUsuarioMedidor" action="#" method="POST" class="form-horizontal" accept-charset="iso-8859-1,utf-8">
				<div class="form-group">
					<div class="col-sm-4">
						<label>Empresa</label> 
						<select class="form-control input-sm" id="idEmpresa" name="idEmpresa" required onchange="listarCondominio()">
							<option value="" selected>Selecione...</option>
							<c:forEach var="empresa" items="${listEmpresa}">
	               		        <c:choose>
	                            	<c:when test="${empresa.idEmpresa eq condominio.idEmpresa}">
	                            		<option value="${empresa.idEmpresa}" selected="true">${empresa.nome}</option> 
	                                </c:when>
	                                <c:otherwise>
	                                  	<option value="${empresa.idEmpresa}">${empresa.nome}</option>
	                                </c:otherwise>
	                     		</c:choose>
	                     	</c:forEach>
						</select>
					</div>
					<div class="col-sm-4">
						<label>Local</label> 
						<select class="form-control input-sm" id="idCondominio" name="idCondominio" required onchange="listarBridge()">
							<option value="" selected>Selecione...</option>
						</select>					
					</div>
					<div class="col-sm-2">
						<label>Bridge</label> 
						<select class="form-control input-sm" id="idBridge" name="idBridge" onchange="listarMedidor()" required>
							<option value="" selected>Selecione...</option>
						</select>					
					</div>
					<div class="col-sm-2">
						<label>Medidor</label> 
						<select class="form-control input-sm" id="idMedidor" name="idMedidor" required >
							<option value="" selected>Selecione...</option>
						</select>					
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label>Data Início</label>
	                    <div class='input-group date' id='dpDtInicio'>
		                    <input type='text' class="form-control input-sm" id='dtInicio' name='dtInicio' required />
	            			<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
		                    <script type="text/javascript">
			                    $(function () {
		                            $('#dpDtInicio').datetimepicker({
		                            	format: 'DD/MM/YYYY'
		                            });
		                        });
		                    </script>
		                </div> 
					</div>
					<div class="col-sm-2">
						<label>Data Fim</label>
	                    <div class='input-group date' id='dpDtFim'>
		                    <input type='text' class="form-control input-sm" id='dtFim' name='dtFim' required />
	            			<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
		                    <script type="text/javascript">
			                    $(function () {
		                            $('#dpDtFim').datetimepicker({
		                            	format: 'DD/MM/YYYY'
		                            });
		                        });
		                    </script>
		                </div> 
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12 text-center">
						<button type="button" class="btn btn-primary" onclick="listarConsumoMedidor()">Consultar</button>
					</div>
				</div>
			</form>	
		
<%-- 			<c:if test="${not empty lista}"> --%>
				<div class="col-sm-8 col-md-offset-2">
					<div class="form-group">
<!-- 						<input class="form-control" id="myInput" type="text" placeholder="Utilize para procurar..."></input> <br /> -->
						<div class="table-responsive" id="divTable">
<!-- 							<table class="table table-hover table-striped"> -->
<!-- 								<thead> -->
<!-- 									<tr> -->
<!-- 										<th>Nº</th> -->
<!-- 										<th>Data</th> -->
<!-- 										<th>Volume (L)</th> -->
<!-- 										<th>Alarme</th> -->
<!-- 										<th>Bateria (V)</th> -->
<!-- 										<th>Temperatura (ºC)</th> -->
<!-- 									</tr> -->
<!-- 								</thead> -->
<!-- 								<tbody> -->
<!-- 									<tr><td>1</td><td>01/02/2018</td><td>360</td><td>DRY</td><td>3.6</td><td>26</td></tr> -->
<!-- 									<tr><td>2</td><td>02/02/2018</td><td>430</td><td></td><td>3.6</td><td>25</td></tr> -->
<!-- 									<tr><td>3</td><td>03/02/2018</td><td>389</td><td></td><td>3.6</td><td>27</td></tr> -->
<!-- 									<tr><td>4</td><td>04/02/2018</td><td>360</td><td></td><td>3.6</td><td>27</td></tr> -->
<!-- 									<tr><td>5</td><td>05/02/2018</td><td>355</td><td></td><td>3.6</td><td>24</td></tr> -->
<!-- 									<tr><td>6</td><td>06/02/2018</td><td>395</td><td></td><td>3.6</td><td>24</td></tr> -->
<!-- 									<tr><td>7</td><td>07/02/2018</td><td>372</td><td></td><td>3.6</td><td>27</td></tr> -->
<!-- 									<tr><td>8</td><td>08/02/2018</td><td>382</td><td></td><td>3.6</td><td>25</td></tr> -->
<!-- 									<tr><td>9</td><td>09/02/2018</td><td>391</td><td></td><td>3.6</td><td>27</td></tr> -->
<!-- 									<tr><td>10</td><td>10/02/2018</td><td>421</td><td></td><td>3.6</td><td>27</td></tr> -->
<!-- 									<tr><td>11</td><td>11/02/2018</td><td>426</td><td></td><td>3.6</td><td>24</td></tr> -->
<!-- 									<tr><td>12</td><td>12/02/2018</td><td>368</td><td></td><td>3.6</td><td>24</td></tr> -->
<!-- 									<tr><td>13</td><td>13/02/2018</td><td>361</td><td></td><td>3.6</td><td>24</td></tr> -->
<!-- 									<tr><td>14</td><td>14/02/2018</td><td>398</td><td></td><td>3.6</td><td>27</td></tr> -->
<!-- 									<tr><td>15</td><td>15/02/2018</td><td>369</td><td></td><td>3.6</td><td>25</td></tr> -->
<!-- 									<tr><td>16</td><td>16/02/2018</td><td>472</td><td></td><td>3.6</td><td>25</td></tr> -->
<!-- 									<tr><td>17</td><td>17/02/2018</td><td>462</td><td></td><td>3.6</td><td>27</td></tr> -->
<!-- 									<tr><td>18</td><td>18/02/2018</td><td>462</td><td></td><td>3.6</td><td>27</td></tr> -->
<!-- 									<tr><td>19</td><td>19/02/2018</td><td>395</td><td></td><td>3.6</td><td>24</td></tr> -->
<!-- 									<tr><td>20</td><td>20/02/2018</td><td>381</td><td></td><td>3.6</td><td>27</td></tr> -->
<!-- 									<tr><td>21</td><td>21/02/2018</td><td>397</td><td></td><td>3.6</td><td>25</td></tr> -->
<!-- 									<tr><td>22</td><td>22/02/2018</td><td>320</td><td>LEAK</td><td>3.6</td><td>27</td></tr> -->
<!-- 									<tr><td>23</td><td>23/02/2018</td><td>392</td><td></td><td>3.6</td><td>24</td></tr> -->
<!-- 									<tr><td>24</td><td>24/02/2018</td><td>438</td><td></td><td>3.6</td><td>24</td></tr> -->
<!-- 									<tr><td>25</td><td>25/02/2018</td><td>482</td><td></td><td>3.6</td><td>27</td></tr> -->
<!-- 									<tr><td>26</td><td>26/02/2018</td><td>435</td><td></td><td>3.6</td><td>25</td></tr> -->
<!-- 									<tr><td>27</td><td>27/02/2018</td><td>375</td><td></td><td>3.5</td><td>25</td></tr> -->
<!-- 									<tr><td>28</td><td>28/02/2018</td><td>320</td><td></td><td>3.5</td><td>27</td></tr> -->
<!-- 									<tr> -->
<!-- 										<td colspan="6" style="text-align: center"> -->
<!-- 											<label>Consumo total no período em m&#179; (1m&#179; = 1.000 Litros): 11,108</label> -->
<!-- 										</td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td colspan="6" style="text-align: center"> -->
<!-- 											<form action="GraficoConsumoBO?acao=1" method="post"> -->
<!-- 												<button type="submit" class="btn btn-warning"> -->
<!-- 											      <i class="fa fa-bar-chart"></i> Gráfico -->
<!-- 											    </button> -->
<!-- 											</form>     -->
<!-- 										</td> -->
<!-- 									</tr> -->
<!-- 								</tbody> -->
<!-- 							</table> -->
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