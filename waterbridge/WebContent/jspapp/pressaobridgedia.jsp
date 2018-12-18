
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
	    <script src="./jsapp/pressaobridgedia.js" type="text/javascript"></script>
	    
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
	    
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous"/>
	    
        <script src='./js/validator.min.js'></script>
	    <link href="./css/menucustomcolor.css" rel="stylesheet"/>
	    <link href="./css/footercustom.css" rel="stylesheet"/>
	    
	    <style>
			  /* Note: Try to remove the following lines to see the effect of CSS positioning */
			  .affix {
			      top: 0;
			      width: 100%;
			      z-index: 9999 !important;
			  }
			
			  .affix + .container-fluid {
			      padding-top: 70px;
			  }
	  	</style>
	  	
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<!-- 		<script src="https://code.highcharts.com/highcharts.js"></script> -->
		<script src="https://code.highcharts.com/stock/highstock.js"></script>
		<script src="https://code.highcharts.com/modules/series-label.js"></script>
		<script src="https://code.highcharts.com/maps/modules/map.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
    </head>
    <body>
        <div class="container-fluid" style="margin: 2px; padding: 0px;">     
        	<div class="panel-group">
       			<div class="panel panel-default">
			      	<div class="panel-heading" style="text-align: left">
			      		<label> 
				      		${condominio.nome}
            				<br/>
            				${condominio.endereco} ${condominio.numero} ${condominio.compl}
            				<br/>
            				Bridge ${bridge.deviceNum}	            
            				<br/>            			
           				</label>
			      	</div>
			      	<div class="panel-body" style="padding: 0px;">
			      	
			      		<table class='table table-hover table-striped' style="margin: 0px;">
		            		<thead>
		            			<tr>
		            				<th style="text-align: left">
		            			         <form action='AndRelatorioBO?acao=2' method='post' onsubmit="return exibirBlock()">
						                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
						                    <input type='hidden' name='idCondominio' value='${condominio.idCondominio}'>
						                    <input type='hidden' name='idBridge' value='${bridge.idBridge}'>
						                    <input type='hidden' name='idMedidor' value='${idMedidor}'>
						                    <input type='hidden' name='data' value='${data}'>
						                    <input type='hidden' name='sinal' value='-'>
						                    <input type='hidden' name='data' value='${dtInicio}'>
						                    <input type='hidden' name='dtFim' value='${dtFim}'>
									         <button type='submit' class='btn btn-info' title='Clique para visualizar o gráfico'>
								                 <i class='glyphicon glyphicon-chevron-left'></i>
								             </button>
								         </form>
		            				</th>
<!-- 		            				<th style="text-align: center"> -->
<!-- 		            			         <form action='AndRelatorioBO?acao=4' method='post' onsubmit="return exibirBlock()"> -->
<%-- 						                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'> --%>
<%-- 						                    <input type='hidden' name='idCondominio' value='${condominio.idCondominio}'> --%>
<%-- 						                    <input type='hidden' name='idBridge' value='${bridge.idBridge}'> --%>
<%-- 						                    <input type='hidden' name='idMedidor' value='${idMedidor}'> --%>
<%-- 						                    <input type='hidden' name='data' value='${data}'> --%>
<!-- 									         <button type='submit' class='btn btn-warning' title='Clique para visualizar o gráfico'> -->
<!-- 								                 <i class='fa fa-bar-chart'></i> -->
<!-- 								             </button> -->
<!-- 								         </form> -->
<!-- 		            				</th>			            				 -->
		            				<th style="text-align: center">
		            			         <form action='AndRelatorioBO?acao=2' method='post' onsubmit="return exibirBlock()" style="margin: 0px; padding: 0px;">
					           				<input type='hidden' name='idUser' value='${idUser}'>
						                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
						                    <input type='hidden' name='idCondominio' value='${condominio.idCondominio}'>
						                    <input type='hidden' name='idBridge' value='${bridge.idBridge}'>
						                    <input type='hidden' name='idMedidor' value='${idMedidor}'>							                    
									    	<button type='submit' class='btn btn-success' title='Clique para atualizar o gráfico'>
								            	<i class='glyphicon glyphicon-refresh'></i>
								            </button>
								        </form>
		            				</th>            				
		            				<th style="text-align: right;">
		            			         <form action='AndRelatorioBO?acao=2' method='post' onsubmit="return exibirBlock()">
						                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
						                    <input type='hidden' name='idCondominio' value='${condominio.idCondominio}'>
						                    <input type='hidden' name='idBridge' value='${bridge.idBridge}'>
						                    <input type='hidden' name='idMedidor' value='${idMedidor}'>
						                    <input type='hidden' name='data' value='${data}'>
						                    <input type='hidden' name='sinal' value='+'>
						                    <input type='hidden' name='dtInicio' value='${dtInicio}'>
						                    <input type='hidden' name='dtFim' value='${dtFim}'>
									        <button type='submit' class='btn btn-info' title='Clique para visualizar o gráfico'>
								                <i class='glyphicon glyphicon-chevron-right'></i>
								            </button>
								         </form>
		            				</th>
		            			</tr>
		            		</thead>
		            	</table>
			      				      	
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
										    	text: ''
									    	},
									    	subtitle: {
									        	text: 'Gráfico de Pressão ${fn:substring(data, 8, 10)}/${fn:substring(data, 5, 7)}/${fn:substring(data, 0, 4)}'
									    	},
// 										    title: {
// 										    	text: 'Gráfico de Pressão<br/><label>Bridge  ${bridge.deviceNum} </label>'
// 									    	},
// 									    	subtitle: {
// 									        	text: 'Data ${fn:substring(data, 8, 10)}/${fn:substring(data, 5, 7)}/${fn:substring(data, 0, 4)}'
// 									    	},
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
										        min: 0,
										        title: {
										        	text: 'MCA'
										        }							        
										        <c:if test="${metaPressao != null}">
										        	,
											      	//minorGridLineWidth: 0,
											        //gridLineWidth: 0,
											        //alternateGridColor: null,
											        plotBands: [{ // limite pressao baixa
											            from: ${metaPressao.pressaoMinBaixa},
											            to: ${metaPressao.pressaoMin},
											            color: 'rgba(255, 153, 153, 0.1)',
											            label: {
											                text: ' ',
											                style: {
											                    color: '#606060'
											                }
											            }
											        },
											        { // limite pressao normal
											            from: ${metaPressao.pressaoMin},
											            to: ${metaPressao.pressaoMax},
											            color: 'rgba(68, 170, 213, 0.1)',
											            label: {
											                text: ' ',
											                style: {
											                    color: '#606060'
											                }
											            }
											        },
											        { //limite maximo pressao alta
											            from: ${metaPressao.pressaoMax},
											            to: ${metaPressao.pressaoMaxAlta},
											            color: 'rgba(255, 153, 153, 0.1)',
											            label: {
											                text: ' ',
											                style: {
											                    color: '#606060'
											                }
											            }
											        }]							        
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
										    	name: '${bridge.deviceNum}',
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
			    
						<div class="col-sm-12" style="margin-top: 20px; margin-left: 0px; margin-right: 0px; padding: 0px;">				
							<c:set var = "cont" value = "1"/>
							<c:set var = "consumo" value = "0"/>
							<c:set var = "colspan" value = "7"/>
							<c:set var = "bgColor" value = ""/>													
<!-- 			            	<div class="container-fluid text-center" style="margin": 0px; padding: 0px;"> -->
<%-- 			            		<label>${fn:substring(data, 8, 10)}/${fn:substring(data, 5, 7)}/${fn:substring(data, 0, 4)}</label> --%>
<!-- 			            	</div> -->
			            	<fmt:setLocale value = "pt-BR"/>
			            	<c:choose>
			                  	<c:when test="${fn:length(listRelPressao) > 0}">
			                  		 <div class="table-responsive" id="divTable">						
						            	<table class='table table-hover table-striped'>
							            	<thead>
							            		<tr>
							            			<th>Hora</th>
												    <th>Press&atilde;o (MCA)</th>
						            				<th>Alarme</th>
							            			<th></th>
							            		</tr>
							            	</thead>
							            	<tbody id='myTable'>				            	
							            		<c:forEach var="relPressao" items="${listRelPressao}">
							            		
							            			<c:set var = "bgTd" value = ""/>
								                	<c:set var = "alarmPressao" value = ""/>								                								                
							            			<c:choose>         
											        	<c:when test = "${metaPressao != null && metaPressao.pressaoMinBaixa > relPressao.pressure}">
											            	<c:set var = "bgTd" value = "bgcolor='#f2dede'"/>
											            	<c:set var = "alarmPressao" value = "Pressão Baixa (Nível Crítico)"/>
											         	</c:when>											         
											         	<c:when test = "${metaPressao != null && metaPressao.pressaoMin > relPressao.pressure}">
											           	 	<c:set var = "bgTd" value = "bgcolor='#f2dede'"/>
											           	 	<c:set var = "alarmPressao" value = "Pressão Baixa"/>
											         	</c:when>
											         	<c:when test = "${metaPressao != null && metaPressao.pressaoMaxAlta < relPressao.pressure}">
											           	 	<c:set var = "bgTd" value = "bgcolor='#f2dede'"/>
											           	 	<c:set var = "alarmPressao" value = "Pressão Alta (Nível Crítico)"/>
											         	</c:when>
											         	<c:when test = "${metaPressao != null && metaPressao.pressaoMax < relPressao.pressure}">
											           	 	<c:set var = "bgTd" value = "bgcolor='#f2dede'"/>
											           	 	<c:set var = "alarmPressao" value = "Pressão Alta"/>
											         	</c:when>
											      	</c:choose>

													<c:set var = "alarmPadrao" value = ""/>	
													<c:choose>         
											        	<c:when test = "${relPressao.alarmDesc != null && relPressao.alarmDesc ne 'NO ALARM'}">
											            	<c:set var = "bgTd" value = "bgcolor='#f2dede'"/>
											            	<c:set var = "alarmPadrao" value = "${relPressao.alarmDesc}"/>
											         	</c:when>											         
											         	<c:when test = "${alarmPressao eq ''}">
											           	 	<c:set var = "alarmPadrao" value = "Sem Alarme"/>
											         	</c:when>											         	
											      	</c:choose>
							            		    
							            		    <c:set var = "separador" value = ""/>
							         				<c:choose>         
											        	<c:when test = "${alarmPadrao ne 'Sem Alarme' && alarmPadrao ne '' && alarmPressao ne ''}">
											            	<c:set var = "separador" value = "/"/>
											         	</c:when>											         			         	
											      	</c:choose>
							            		      
					    		            		<tr>
					    		            			<td ${bgTd}><small>${relPressao.horaInsert}</small></td>
					    		            			<td ${bgTd}><small><fmt:formatNumber value="${relPressao.pressure}" type="currency" currencySymbol="" minFractionDigits = "3"/></small></td>					    		            			
							    		            	<td ${bgTd}><small>${alarmPadrao} ${separador} ${alarmPressao}</small></td>
							    		            	<td ${bgTd} align='right'></td>
							    		            </tr>								                	
								                	<c:set var = "cont" value = "${cont + 1}"/>            		
							            		</c:forEach>			            	
							                </tbody>
							            </table>							           
										<table class='table table-hover table-striped' style="margin: 0px;">
						            		<thead>
						            			<tr>
						            				<th style="text-align: left">
						            			         <form action='AndRelatorioBO?acao=2' method='post' onsubmit="return exibirBlock()">
										                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
										                    <input type='hidden' name='idCondominio' value='${condominio.idCondominio}'>
										                    <input type='hidden' name='idBridge' value='${bridge.idBridge}'>
										                    <input type='hidden' name='idMedidor' value='${idMedidor}'>
										                    <input type='hidden' name='data' value='${data}'>
										                    <input type='hidden' name='sinal' value='-'>
										                    <input type='hidden' name='data' value='${dtInicio}'>
										                    <input type='hidden' name='dtFim' value='${dtFim}'>
													         <button type='submit' class='btn btn-info' title='Clique para visualizar o gráfico'>
												                 <i class='glyphicon glyphicon-chevron-left'></i>
												             </button>
												         </form>
						            				</th>
<!-- 						            				<th style="text-align: center"> -->
<!-- 						            			         <form action='AndRelatorioBO?acao=4' method='post' onsubmit="return exibirBlock()"> -->
<%-- 										                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'> --%>
<%-- 										                    <input type='hidden' name='idCondominio' value='${condominio.idCondominio}'> --%>
<%-- 										                    <input type='hidden' name='idBridge' value='${bridge.idBridge}'> --%>
<%-- 										                    <input type='hidden' name='idMedidor' value='${idMedidor}'> --%>
<%-- 										                    <input type='hidden' name='data' value='${data}'> --%>
<!-- 													         <button type='submit' class='btn btn-warning' title='Clique para visualizar o gráfico'> -->
<!-- 												                 <i class='fa fa-bar-chart'></i> -->
<!-- 												             </button> -->
<!-- 												         </form> -->
<!-- 						            				</th>			            				 -->
						            				<th style="text-align: center">
						            			         <form action='AndRelatorioBO?acao=2' method='post' onsubmit="return exibirBlock()" style="margin: 0px; padding: 0px;">
									           				<input type='hidden' name='idUser' value='${idUser}'>
										                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
										                    <input type='hidden' name='idCondominio' value='${condominio.idCondominio}'>
										                    <input type='hidden' name='idBridge' value='${bridge.idBridge}'>
										                    <input type='hidden' name='idMedidor' value='${idMedidor}'>							                    
													    	<button type='submit' class='btn btn-success' title='Clique para atualizar o gráfico'>
												            	<i class='glyphicon glyphicon-refresh'></i>
												            </button>
												        </form>
						            				</th>            				
						            				<th style="text-align: right;">
						            			         <form action='AndRelatorioBO?acao=2' method='post' onsubmit="return exibirBlock()">
										                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
										                    <input type='hidden' name='idCondominio' value='${condominio.idCondominio}'>
										                    <input type='hidden' name='idBridge' value='${bridge.idBridge}'>
										                    <input type='hidden' name='idMedidor' value='${idMedidor}'>
										                    <input type='hidden' name='data' value='${data}'>
										                    <input type='hidden' name='sinal' value='+'>
										                    <input type='hidden' name='dtInicio' value='${dtInicio}'>
										                    <input type='hidden' name='dtFim' value='${dtFim}'>
													        <button type='submit' class='btn btn-info' title='Clique para visualizar o gráfico'>
												                <i class='glyphicon glyphicon-chevron-right'></i>
												            </button>
												         </form>
						            				</th>
						            			</tr>
						            		</thead>
						            	</table>
							            	
									</div>
			                    </c:when>
			                    <c:otherwise>
			                      	<div class="alert alert-danger text-center">
									  	Nenhum registro encontrado
									</div>
			                    </c:otherwise>
			           		</c:choose>
						</div>
			      	</div>
   				</div>
			</div>	
		</div>
        <footer class="footer" style="background-color: #fff">
            <div class="container-fluid text-center" style="background-color: #fff; padding: 10px;">
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