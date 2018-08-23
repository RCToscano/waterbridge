
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
<!-- 	    <script src="./jsapp/consumomedidordia.js" type="text/javascript"></script> -->
	    
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
    </head>
    <body>
        <div class="container-fluid" style="margin: 0px; padding: 0px;">                 	   
			<div class="col-sm-12" style="margin: 0px; padding: 0px;">				
				<c:set var = "cont" value = "1"/>
				<c:set var = "consumo" value = "0"/>
				<c:set var = "colspan" value = "7"/>
				<c:set var = "bgColor" value = ""/>					
				<table class='table table-hover table-striped' style="margin: 0px;">
            		<thead>
            			<tr>
            				<th style="text-align: left">
            			         <form action='AndRelatorioBO?acao=2' method='post'>
				                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
				                    <input type='hidden' name='idCondominio' value='${idCondominio}'>
				                    <input type='hidden' name='idBridge' value='${idBridge}'>
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
            				<th style="text-align: center">
            			         <form action='AndRelatorioBO?acao=4' method='post' target='_blank'>
				                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
				                    <input type='hidden' name='idCondominio' value='${idCondominio}'>
				                    <input type='hidden' name='idBridge' value='${idBridge}'>
				                    <input type='hidden' name='idMedidor' value='${idMedidor}'>
				                    <input type='hidden' name='data' value='${data}'>
							         <button type='submit' class='btn btn-warning' title='Clique para visualizar o gráfico'>
						                 <i class='fa fa-bar-chart'></i>
						             </button>
						         </form>
            				</th>
<!--             				<th style="text-align: center"> -->
<!--             			         <form action='ConsumoMedidorBO?acao=excel' method='post' target='_blank'> -->
<%--             	                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'> --%>
<%--             	                    <input type='hidden' name='idCondominio' value='${idCondominio}'> --%>
<%--             	                    <input type='hidden' name='idBridge' value='${idBridge}'> --%>
<%--             	                    <input type='hidden' name='idMedidor' value='${idMedidor}'> --%>
<%--             	                    <input type='hidden' name='dtInicio' value='${dtInicio}'> --%>
<%--             	                    <input type='hidden' name='dtFim' value='${dtFim}'> --%>
<!--             				         <button type='submit' class='btn btn-success' title='Clique para fazer o download em excel'> -->
<!--             			                 <i class='fa fa-file-excel'></i> -->
<!--             			             </button> -->
<!--             			         </form> -->
<!--             				</th>            				 -->
            				<th style="text-align: right;">
            			         <form action='AndRelatorioBO?acao=2' method='post'>
				                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
				                    <input type='hidden' name='idCondominio' value='${idCondominio}'>
				                    <input type='hidden' name='idBridge' value='${idBridge}'>
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
            	<div class="container-fluid text-center" style="margin": 0px; padding: 0px;">
            		<label>${fn:substring(data, 8, 10)}/${fn:substring(data, 5, 7)}/${fn:substring(data, 0, 4)}</label>
            	</div>
            	<fmt:setLocale value = "pt-BR"/>
            	<c:choose>
                  	<c:when test="${fn:length(listRelPressao) > 0}">
                  		 <div class="table-responsive" id="divTable">						
			            	<table class='table table-hover table-striped'>
				            	<thead>
				            		<tr>
				            			<th>Nº</th>
				            			<th>Data</th>
									    <th>Press&atilde;o (MCA)</th>
			            				<th>Alarme</th>
				            			<th></th>
				            		</tr>
				            	</thead>
				            	<tbody id='myTable'>				            	
				            		<c:forEach var="relPressao" items="${listRelPressao}">        		                
					                	<c:if test = "${relPressao.alarm != 0}">
					                		<c:set var = "bgColor" value = "bgcolor='#f2dede'"/>
					                	</c:if>				                
		    		            		<tr>
		    		            			<td ${bgColor}><small>${cont}</small></td>
		    		            			<td ${bgColor}><small>${relPressao.dtInsert}</small></td>
		    		            			<td ${bgColor}><small><fmt:formatNumber value="${relPressao.pressure}" type="currency" currencySymbol="" minFractionDigits = "3"/></small></td>
				    		            	<td ${bgColor}><small>${relPressao.alarmDesc}</small></td>
				    		            	<td ${bgColor} align='right'></td>
				    		            </tr>
					                	<c:set var = "bgColor" value = ""/>
					                	<c:set var = "cont" value = "${cont + 1}"/>            		
				            		</c:forEach>			            	
							        <tr>
								         <td>
									         <form action='AndRelatorioBO?acao=4' method='post' target='_blank'>
							                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
							                    <input type='hidden' name='idCondominio' value='${idCondominio}'>
							                    <input type='hidden' name='idBridge' value='${idBridge}'>
							                    <input type='hidden' name='idMedidor' value='${idMedidor}'>
							                    <input type='hidden' name='data' value='${data}'>
										         <button type='submit' class='btn btn-warning' title='Clique para visualizar o gráfico'>
									                 <i class='fa fa-bar-chart'></i>
									             </button>
									         </form>
								         </td>
								         <td colspan='4' style='text-align: left'>
									         <form action='ConsumoMedidorBO?acao=excel' method='post' target='_blank'>
							                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
							                    <input type='hidden' name='idCondominio' value='${idCondominio}'>
							                    <input type='hidden' name='idBridge' value='${idBridge}'>
							                    <input type='hidden' name='idMedidor' value='${idMedidor}'>
							                    <input type='hidden' name='dtInicio' value='${dtInicio}'>
							                    <input type='hidden' name='dtFim' value='${dtFim}'>
										         <button type='submit' class='btn btn-success' title='Clique para fazer o download em excel'>
									                 <i class='fa fa-file-excel'></i>
									             </button>
									         </form>
								         </td>
							        </tr>
				                </tbody>
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