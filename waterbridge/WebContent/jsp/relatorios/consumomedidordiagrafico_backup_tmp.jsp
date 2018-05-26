
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1"/>
		<title>WaterBridge</title>
		<link rel="icon" type="image/png" href="./images/favicon.ico"/>
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
		<link href="./css/menucustomcolor.css" rel="stylesheet"/>
		<link href="./css/footercustom.css" rel="stylesheet"/>
		<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" />
		
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
		<script type="text/javascript" src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script src="https://code.highcharts.com/highcharts.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
	
	</head>
	<body>
		<jsp:include page="/menu/${sessionScope.user.perfil.menu}"></jsp:include>
		
		<div class="container">		
			<div class="col-sm-12" style="float: none; margin: 0 auto;">
				${medidor}<br/>
				${dtInicio}<br/>
				${dtFim}<br/>
				${listData}<br/>
				${listConsumo}<br/>
				
				<div class="form-group">
					<div class="col-sm-12">
						<div id="graficoconsumodiario" style="margin-top: 30px;"></div>
						<script>
							Highcharts.chart('graficoconsumodiario', {
						    	chart: {
						        	type: 'column'
						    	},
						    	title: {
						        	text: 'Gráfico de Consumo Diário<br/><label>Medidor  74474706</label>'
						    	},
						    	subtitle: {
						        	text: 'Período 01/02/2018 a 28/02/2018'
						    	},
							    xAxis: {
							        categories: [
							            '01/03/2018',
							            '02/03/2018',
							            '03/03/2018',
							            '04/03/2018',
							            '05/03/2018',
							            '06/03/2018',
							            '07/03/2018',
							            '08/03/2018',
							            '09/03/2018',
							            '10/03/2018',
							            '11/03/2018',
							            '12/03/2018',
							            '13/03/2018',
							            '14/03/2018',
							            '15/03/2018',
							            '16/03/2018',
							            '17/03/2018',
							            '18/03/2018',
							            '19/03/2018',
							            '20/03/2018',
							            '21/03/2018',
							            '22/03/2018',
							            '23/03/2018',
							            '24/03/2018',
							            '25/03/2018',
							            '26/03/2018',
							            '27/03/2018',
							            '28/03/2018'
							        ],
							        crosshair: true
							    },
							    yAxis: {
							        min: 0,
							        title: {
							            text: 'Consumo (L)'
							        }
							    },
		// 					    xAxis: {
		// 					    	min: 2,
		// 					        title: {
		// 					            text: 'Dias'
		// 					        }
		// 					    },
							    tooltip: {
							        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
							        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' + '<td style="padding:0"><b>{point.y:.0f} m3</b></td></tr>',
							        footerFormat: '</table>',
							        shared: true,
							        useHTML: true
							    },
							    plotOptions: {
							        column: {
							            pointPadding: 0.2,
							            borderWidth: 0
							        }
							    },
							    series: [{
							        name: '74474706',
							        data: [
							        	360,
							        	430,
							        	389,
							        	360,
							        	355,
							        	395,
							        	372,
							        	382,
							        	391,
							        	421,
							        	426,
							        	368,
							        	361,
							        	398,
							        	369,
							        	472,
							        	462,
							        	462,
							        	395,
							        	381,
							        	397,
							        	320,
							        	392,
							        	438,
							        	482,
							        	435,
							        	375,
							        	320
							        ]
							    }]
							});
						
						</script>	
					</div>
				</div>
			</div>
		</div>
		<footer class="footer" style="background-color: #fff">
            <div class="container-fluid text-center" style="background-color: #fff; padding: 10px">
            	<div class="col-sm-4 text-center"><p class="text-muted">Todos os direitos reservados</p></div>
            	<div class="col-sm-4 text-center"><p class="text-muted"><label>Desenvolvido por Desoltec Engenharia</label></p></div>
            	<div class="col-sm-4 text-center"><img class="img-responsive center-block" src="./images/logo_desoltec_rodape.png" alt=""></div>
            </div>
        </footer>
	</body>
</html>
