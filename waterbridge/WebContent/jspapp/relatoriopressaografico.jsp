
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<!-- 		<script src="https://code.highcharts.com/highcharts.js"></script> -->
		<script src="https://code.highcharts.com/stock/highstock.js"></script>
		<script src="https://code.highcharts.com/modules/series-label.js"></script>
		<script src="https://code.highcharts.com/maps/modules/map.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
	
	</head>
	<body>
		<jsp:include page="/menu/${sessionScope.user.perfil.menu}"></jsp:include>
		<div class="container">		
			<div class="col-sm-12" style="float: none; margin: 0 auto;">
				<div class="form-group" style="margin: 0px; padding: 0px;">
					<div class="col-sm-12" style="margin: 0px; padding: 0px;">
						<div id="graficopressaodiaria" style="margin: 0px; padding: 0px;"></div>						
						<script>
							Highcharts.chart('graficopressaodiaria', {
							    chart: {
							        type: 'line',
							        marginLeft: 70
							    },
							    title: {
							    	text: 'Gráfico de Pressão<br/><label>Bridge  ${bridge} </label>'
						    	},
						    	subtitle: {
						        	text: 'Data ${data}'
						    	},
							    xAxis: {
							        //type: 'category',
							        categories: [	
							        	<c:set var="count" value="0" scope="page" />
							        	<c:forEach var="relPressao" items='${listRelPressao}'>
							        	    <c:if test="${relPressao.alarmDesc != null && relPressao.alarm != 1}">
								        	    '${relPressao.horaInsert}'
							   			   		<c:if test="${(count + 1) < fn:length(listRelPressao)}">
													,
												</c:if>
							        	    </c:if>
						   			   		<c:set var="count" value="${count + 1}" scope="page"/>
							        	</c:forEach>
							        ],							        
							        title: {
							            text: null
							        },
							        min: 0,
							        max: 20,
							        scrollbar: {
							            enabled: true
							        },
							        tickLength: 0
							    },
							    yAxis: {
							    	max : pressaoMaxAlta,
							        title: {
							        	text: 'MCA'
							        }							        
							        <c:if test="${metaPressao != null}">
							        	,
								      	//minorGridLineWidth: 0,
								        //gridLineWidth: 0,
								        //alternateGridColor: null,
								        plotBands: [
								        	{ // Light air
									            from: 0,
									            to: ${metaPressao.pressaoMinBaixa},
									            color: 'rgb(238, 89, 91)'
								        	},
								        	{ 
									            from: ${metaPressao.pressaoMinBaixa},
									            to: ${metaPressao.pressaoMin},
									            color: 'rgb(248, 189, 146)'
								        	},
								        	{ 
									            from: ${metaPressao.pressaoMin},
									            to: ${metaPressao.pressaoMax},
									            color: 'rgb(247, 247, 247)'
								        	},
								        	{ 
									            from: ${metaPressao.pressaoMax},
									            to: 99,
									            color: 'rgb(105, 156, 250)'
								        	},
								        	{ 
									            from: 99,
									            to: 100,
									            color: 'rgb(174, 52, 235)'
								        	}
								        ]							        
							        </c:if>								        
							    },
							    tooltip: {
							        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
							        pointFormat: '<tr><td style="color:{series.color};padding:0"></td>' + '<td style="padding:0"><b>{point.y:.3f}</b></td></tr>',
							        footerFormat: '</table>',
							        shared: true,
							        useHTML: true
							    },
							    plotOptions: {
							        column: {
							            pointPadding: 0.3,
							            borderWidth: 0
							        }
							    },
							    legend: {
							        enabled: false
							    },
							    credits: {
							        enabled: false
							    },							    
							    series: [{
							    	name: '${bridge}',
							        data: [
							        	<c:set var="count" value="0" scope="page" />
							        	<c:forEach var="relPressao" items='${listRelPressao}'>
							        		<c:if test="${relPressao.alarmDesc != null && relPressao.alarm != 1}">
								        		${relPressao.pressure}
							   			   		<c:if test="${(count + 1) < fn:length(listRelPressao)}">
													,	
												</c:if>
							        		</c:if>
						   			   		<c:set var="count" value="${count + 1}" scope="page"/>
							        	</c:forEach>
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
