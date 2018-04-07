
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>WaterBridge</title>
<link rel="icon" type="image/png" href="./images/favicon.ico">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
<link href="./css/menucustomcolor.css" rel="stylesheet"/>
<link href="./css/footercustom.css" rel="stylesheet"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" />

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script type="text/javascript" src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
</head>
<body>
	<jsp:include page="/menu/${sessionScope.user.perfil.menu}"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-4 col-md-2">
				<br/>
<!-- 				<div class="thumbnail"> -->
<!-- 					<div class="caption"> -->
<!-- 						<img src="./images/logo_implement.png" alt="" width="150px" style="margin: 0px;"> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<img src="./images/logo_waterbridge_corpo.png" alt="" style="margin: 0px;">
			</div>
			<div class="col-sm-4 col-md-10">
			    <br/>
				<h4>Consulta de Consumos por Período</h4>
				<div class="thumbnail">
					<div class="caption">
						<div class="col-sm-4 col-md-12">
						
							<div class="col-sm-4 col-md-6 text-left">
								<label>Período</label>
								<div class="col-sm-4 col-md-12" style="padding: 0px; margin: 0px;">
									<div class="col-sm-4 col-md-6" style="padding-left: 0px;">
										<input type="text" class="form-control" data-date-format="DD/MM/YYYY" name="dtInicio" id="dtInicio" placeholder="Início" maxlength="10" value="01/02/2018" required/>
									</div>
									<div class="col-sm-4 col-md-6" style="padding-left: 0px;">
										<input type="text" class="form-control" data-date-format="DD/MM/YYYY" name="dtFim" id="dtFim" placeholder="Fim" maxlength="10" value="28/02/2018" required/>
									</div>
								</div>
							</div>

					        <script type="text/javascript">
					            $(function () {
					                $('#dtInicio').datetimepicker({locale: 'pt-br'});
					                $('#dtFim').datetimepicker({locale: 'pt-br'});
					            });
					        </script>
							<div class="col-sm-4 col-md-3">
								<label>Medidor</label>
								<select class="form-control" id="sel1">
									<option>Selecione</option>
								    <option>74474706</option>
							  	</select>
							</div>
							<div class="col-sm-4 col-md-3" style="padding-top: 25px;">
								<button type="button" class="btn btn-primary">Buscar</button>
							</div>
						</div>
						<br/><br/><br/><hr></hr>
						
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Nº</th>
									<th>Data</th>
									<th>Volume (L)</th>
									<th>Alarme</th>
									<th>Bateria (V)</th>
									<th>Temperatura (ºC)</th>
								</tr>
							</thead>
							<tbody>
								<tr><td>1</td><td>01/02/2018</td><td>360</td><td>DRY</td><td>3.6</td><td>26</td></tr>
								<tr><td>2</td><td>02/02/2018</td><td>430</td><td></td><td>3.6</td><td>25</td></tr>
								<tr><td>3</td><td>03/02/2018</td><td>389</td><td></td><td>3.6</td><td>27</td></tr>
								<tr><td>4</td><td>04/02/2018</td><td>360</td><td></td><td>3.6</td><td>27</td></tr>
								<tr><td>5</td><td>05/02/2018</td><td>355</td><td></td><td>3.6</td><td>24</td></tr>
								<tr><td>6</td><td>06/02/2018</td><td>395</td><td></td><td>3.6</td><td>24</td></tr>
								<tr><td>7</td><td>07/02/2018</td><td>372</td><td></td><td>3.6</td><td>27</td></tr>
								<tr><td>8</td><td>08/02/2018</td><td>382</td><td></td><td>3.6</td><td>25</td></tr>
								<tr><td>9</td><td>09/02/2018</td><td>391</td><td></td><td>3.6</td><td>27</td></tr>
								<tr><td>10</td><td>10/02/2018</td><td>421</td><td></td><td>3.6</td><td>27</td></tr>
								<tr><td>11</td><td>11/02/2018</td><td>426</td><td></td><td>3.6</td><td>24</td></tr>
								<tr><td>12</td><td>12/02/2018</td><td>368</td><td></td><td>3.6</td><td>24</td></tr>
								<tr><td>13</td><td>13/02/2018</td><td>361</td><td></td><td>3.6</td><td>24</td></tr>
								<tr><td>14</td><td>14/02/2018</td><td>398</td><td></td><td>3.6</td><td>27</td></tr>
								<tr><td>15</td><td>15/02/2018</td><td>369</td><td></td><td>3.6</td><td>25</td></tr>
								<tr><td>16</td><td>16/02/2018</td><td>472</td><td></td><td>3.6</td><td>25</td></tr>
								<tr><td>17</td><td>17/02/2018</td><td>462</td><td></td><td>3.6</td><td>27</td></tr>
								<tr><td>18</td><td>18/02/2018</td><td>462</td><td></td><td>3.6</td><td>27</td></tr>
								<tr><td>19</td><td>19/02/2018</td><td>395</td><td></td><td>3.6</td><td>24</td></tr>
								<tr><td>20</td><td>20/02/2018</td><td>381</td><td></td><td>3.6</td><td>27</td></tr>
								<tr><td>21</td><td>21/02/2018</td><td>397</td><td></td><td>3.6</td><td>25</td></tr>
								<tr><td>22</td><td>22/02/2018</td><td>320</td><td>LEAK</td><td>3.6</td><td>27</td></tr>
								<tr><td>23</td><td>23/02/2018</td><td>392</td><td></td><td>3.6</td><td>24</td></tr>
								<tr><td>24</td><td>24/02/2018</td><td>438</td><td></td><td>3.6</td><td>24</td></tr>
								<tr><td>25</td><td>25/02/2018</td><td>482</td><td></td><td>3.6</td><td>27</td></tr>
								<tr><td>26</td><td>26/02/2018</td><td>435</td><td></td><td>3.6</td><td>25</td></tr>
								<tr><td>27</td><td>27/02/2018</td><td>375</td><td></td><td>3.5</td><td>25</td></tr>
								<tr><td>28</td><td>28/02/2018</td><td>320</td><td></td><td>3.5</td><td>27</td></tr>
								<tr>
									<td colspan="6" style="text-align: center">
										<label>Consumo total no período em m&#179; (1m&#179; = 1.000 Litros): 11,108</label>
									</td>
								</tr>
								<tr>
									<td colspan="6" style="text-align: center">
										<form action="GraficoConsumoBO?acao=1" method="post">
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
