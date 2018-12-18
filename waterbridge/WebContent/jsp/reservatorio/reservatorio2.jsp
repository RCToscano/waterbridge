<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Bootstrap Example</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/highcharts-3d.js"></script>
	<script src="https://code.highcharts.com/modules/data.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>

</head>
<body>
	<div class="container-fluid">
		<div class="row" style="text-align: center;"><h3>Reservat&oacute;rios</h3></div>
		<a id="link" href="#">click me to scroll to id3</a><br>
		<div class="row">
		
			<div class="col-sm-1">
				<div id="container1"></div>
			</div>
			<div class="col-sm-1">
				<div id="container2"></div>
			</div>
			<div class="col-sm-1">
				<div id="container3"></div>
			</div>
			<div class="col-sm-1">
				<div id="container4"></div>
			</div>
			<div class="col-sm-1">
				<div id="container5"></div>
			</div>
			<div class="col-sm-1">
				<div id="container6"></div>
			</div>
			<div class="col-sm-1">
				<div id="container7"></div>
			</div>
			<div class="col-sm-1">
				<div id="container8"></div>
			</div>
			<div class="col-sm-1">
				<div id="container9"></div>
			</div>
			<div class="col-sm-1">
				<div id="container10"></div>
			</div>
			<div class="col-sm-1">
				<div id="container11"></div>
			</div>
			<div class="col-sm-1">
				<div id="container12"></div>
			</div>
		</div>
	
		<div class="row" style="padding-top: 15px">			
			<div class="col-sm-1">
				<div id="container13"></div>
			</div>
			<div class="col-sm-1">
				<div id="container14"></div>
			</div>
			<div class="col-sm-1">
				<div id="container15"></div>
			</div>
			<div class="col-sm-1">
				<div id="container16"></div>
			</div>
			<div class="col-sm-1">
				<div id="container17"></div>
			</div>
			<div class="col-sm-1">
				<div id="container18"></div>
			</div>
			<div class="col-sm-1">
				<div id="container19"></div>
			</div>
			<div class="col-sm-1">
				<div id="container20"></div>
			</div>
			<div class="col-sm-1">
				<div id="container21"></div>
			</div>
			<div class="col-sm-1">
				<div id="container22"></div>
			</div>
			<div class="col-sm-1">
				<div id="container23"></div>
			</div>
			<div class="col-sm-1">
				<div id="container24"></div>
			</div>
		</div>
		
		<div class="row" style="padding-top: 15px">			
			<div class="col-sm-1">
				<div id="container25"></div>
			</div>
			<div class="col-sm-1">
				<div id="container26"></div>
			</div>
			<div class="col-sm-1">
				<div id="container27"></div>
			</div>
			<div class="col-sm-1">
				<div id="container28"></div>
			</div>
			<div class="col-sm-1">
				<div id="container29"></div>
			</div>
			<div class="col-sm-1">
				<div id="container30"></div>
			</div>
			<div class="col-sm-1">
				<div id="container31"></div>
			</div>
			<div class="col-sm-1">
				<div id="container32"></div>
			</div>
			<div class="col-sm-1">
				<div id="container33"></div>
			</div>
			<div class="col-sm-1">
				<div id="container34"></div>
			</div>
			<div class="col-sm-1">
				<div id="container35"></div>
			</div>
			<div class="col-sm-1">
				<div id="container36"></div>
			</div>
		</div>		
		
		<div class="row" style="padding-top: 15px">			
			<div class="col-sm-1">
				<div id="container37"></div>
			</div>
			<div class="col-sm-1">
				<div id="container38"></div>
			</div>
			<div class="col-sm-1">
				<div id="container39"></div>
			</div>
			<div class="col-sm-1">
				<div id="container40"></div>
			</div>
			<div class="col-sm-1">
				<div id="container41"></div>
			</div>
			<div class="col-sm-1">
				<div id="container42"></div>
			</div>
			<div class="col-sm-1">
				<div id="container43"></div>
			</div>
			<div class="col-sm-1">
				<div id="container44"></div>
			</div>
			<div class="col-sm-1">
				<div id="container45"></div>
			</div>
			<div class="col-sm-1">
				<div id="container46"></div>
			</div>
			<div class="col-sm-1">
				<div id="container47"></div>
			</div>
			<div class="col-sm-1">
				<div id="container48"></div>
			</div>
		</div>		
		<a name="id3" id='id3'/>
		<b>ID3</b>
	</div>

	<script>
	
		function scrollToAnchor(aid){
		    var aTag = $("#id3");
		    $('html,body').animate({scrollTop: aTag.offset().top},10000);
		}
	
		$("#link").click(function() {
		   scrollToAnchor('id3');
		});
		
		function carregarDados() {
			
			for (i = 1; i < 49; i++) {

				var data = Math.floor((Math.random() * 100) + 1);
				var altura = 250;
				if(i > 15) {
					altura = 200;
				}

				Highcharts.chart('container' + i, {
					chart : {
						type : 'column',
						height : 250,
						options3d : {
							enabled : true,
							alpha : 0,
							beta : 30,
							depth : 30
						}
					},
					title : {
						text : ''
					},
					subtitle : {
						text : '' + i 
					},
					legend : {
						enabled : false
					},
					credits : {
						enabled : false
					},

					plotOptions : {
						bar : {
							colorByPoint : true
						},
						series : {
							zones : [ {
								color : '#F44336',
								value : 0
							}, {
								color : '#FF5722',
								value : 5
							}, {
								color : '#FF9800',
								value : 10
							}, {
								color : '#FFC107',
								value : 15
							}, {
								color : '#FFEB3B',
								value : 30
							}, {
								color : '#CDDC39',
								value : 40
							}, {
								color : '#8BC34A',
								value : 45
							}, {
								color : '#4CAF50',
								value : 50
							}, {
								color : '#8BC34A',
								value : 55
							}, {
								color : '#CDDC39',
								value : 60
							}, {
								color : '#FFEB3B',
								value : 65
							}, {
								color : '#FFC107',
								value : 80
							}, {
								color : '#FF9800',
								value : 85
							}, {
								color : '#FF5722',
								value : 90
							}, {
								color : '#FF5722',
								value : Number.MAX_VALUE
							} ],
							dataLabels : {
								enabled : true,
								format : '{point.y:.0f}%'
							}
						}
					},
					tooltip : {
						valueDecimals : 1,
						valueSuffix : '%'
					},
					xAxis : {
						type : 'category',
						categories : [ '' ],
						labels : {
							style : {
								fontSize : '10px'
							}
						}
					},
					yAxis : {
						max : 100,
						title : false,
						plotBands : [ {
							from : 0,
							to : 30,
							color : '#E8F5E9'
						}, {
							from : 30,
							to : 70,
							color : '#FFFDE7'
						}, {
							from : 70,
							to : 100,
							color : "#FFEBEE"
						} ]
					},
					series : [ {
						//name: 'Sales',
						data : [ data ]
					} ],
					exporting : {
						enabled : false
					}
				});

			}
			setTimeout(function() {
				carregarDados();
			}, 5000);
		}
		setTimeout(function() {
			carregarDados();
		}, 1000);
				
	</script>

</body>
</html>
