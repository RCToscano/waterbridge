
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
	    <script src="./jsapp/consumomedidordia.js" type="text/javascript"></script>
	    
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
	    
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	    <style>
			#blocoendereco {
/* 			    border-radius: 15px; */
			    background: #337ab7;
			    padding: 20px; 
			    width: 100%;
			    height: 150px; 
			    margin: 0 auto;
			} 
			
			#blocoperiodo {
  		    	border-radius: 10px;
		    	background: #ec971f;
			    padding: 7px; 
			    width: 100%;
		    	margin: 0 auto;
		    	style="background-color: orange; padding: 5px;"
			}
			#blococonsumototal {
  		    	border-radius: 50px 0px;
		    	background: #4caf50;
			    padding: 20px; 
			    width: 200px;
		    	height: 150px;
		    	margin: 0 auto;
			} 		
			
			#blocomediadiaria {
  		    	border-radius: 50px 0px;
		    	background: #6c757d;
			    padding: 20px; 
			    width: 200px;
			    height: 150px;
		    	margin: 0 auto;
			}	
			
			#blocometamensal {
  		    	border-radius: 50px 0px;
		    	background: #17a2b8;
			    padding: 20px; 
			    width: 200px;
			    height: 150px;
		    	margin: 0 auto;
			}			
			
			#blocopressao {
  		    	border-radius: 50px 0px;
		    	background: #c9302c;
			    padding: 20px; 
			    width: 200px;
			    height: 150px;
		    	margin: 0 auto;
			}

		</style>
		
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js"></script>
	    <script type="text/javascript">
			$(function() {
				$("#meta").maskMoney({prefix:'', allowNegative: true, thousands:'.', decimal:',', affixesStay: false});
			});
	    </script>
		
    </head>
	<body>
        <div class="container-fluid" style="margin: 0px; padding: 0px;">      
  	   
			<div class="col-sm-12" style="margin: 0px; padding: 0px;">				
				<c:set var = "cont" value = "1"/>
				<c:set var = "consumo" value = "0"/>
				<c:set var = "colspan" value = "6"/>
				<c:set var = "bgColor" value = ""/>						
            	<div class="container-fluid text-center" style="margin: 2px; padding: 0px;">

            		<div class="panel-group">
            			<div class="panel panel-primary">

					      	<div class="panel-heading" style="text-align: left">
					      		<label> 
						      		${medidor.condominio}
		            				<br/>
		            				${medidor.enderecoMed} ${medidor.numeroMed} ${medidor.complMed}
		            				<br/>
		            				Medidor ${medidor.numeroMedidor}	            				
		            				<br/>            			
	            				</label>
					      	</div>
					      	<div class="panel-body">


								<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.3.5/css/swiper.min.css">
								<style>
						    		.swiper-container {
										width: 100%;
										height: 100%;
						    		}
						    		.swiper-slide {
										text-align: center;
										/*font-size: 18px;*/
										background: #fff;
										/* Center slide text vertically */
										display: -webkit-box;
										display: -ms-flexbox;
										display: -webkit-flex;
										display: flex;
										-webkit-box-pack: center;
										-ms-flex-pack: center;
										-webkit-justify-content: center;
										justify-content: center;
										-webkit-box-align: center;
										-ms-flex-align: center;
										-webkit-align-items: center;
										align-items: center;
									}
						  		</style>
<!-- 						  		<div id="blocoperiodo"> -->
<!-- 									<label style="color: #fff;">  -->
<!-- 							      		Per&iacute;odo  -->
<%-- 				            			${fn:substring(dtInicio, 8, 10)}/${fn:substring(dtInicio, 5, 7)}/${fn:substring(dtInicio, 0, 4)} --%>
<!-- 				            			&nbsp;&agrave;&nbsp; -->
<%-- 				            			${fn:substring(dtFim, 8, 10)}/${fn:substring(dtFim, 5, 7)}/${fn:substring(dtFim, 0, 4)}	            					           				            		 --%>
<!-- 									</label> -->
<!-- 								</div> -->
								
								<div style="text-align: right;">
									<form action='AndRelatorioBO?acao=5' method='post' onsubmit="return exibirBlock()" style="margin: 0px; padding: 0px;">
				           				<input type='hidden' name='idUser' value='${idUser}'>
					                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
					                    <input type='hidden' name='idCondominio' value='${idCondominio}'>
					                    <input type='hidden' name='idBridge' value='${idBridge}'>
					                    <input type='hidden' name='idMedidor' value='${idMedidor}'>
					                    <input type='hidden' name='data' value='${dtFim}'>
								    	<button type='submit' class='btn btn-success' title='Clique para visualizar o gráfico'>
							            	<i class='glyphicon glyphicon-refresh'></i>
							            </button>
							        </form>
						        </div>
								
								<h3>
									Medidor Água									
								</h3>
								<h5>
									Per&iacute;odo 
			            			${fn:substring(dtInicio, 8, 10)}/${fn:substring(dtInicio, 5, 7)}/${fn:substring(dtInicio, 0, 4)}
			            			&nbsp;&agrave;&nbsp;
			            			${fn:substring(dtFim, 8, 10)}/${fn:substring(dtFim, 5, 7)}/${fn:substring(dtFim, 0, 4)}
								</h5>
								
								<div class="swiper-container">
								    <div class="swiper-wrapper">
										<div class="swiper-slide">
											<div>
												<br/><br/>			           			
												<div id="blococonsumototal">
													<label style="color: #fff;">            			            						            		
														Consumo Total
														<br/>
														<h3><fmt:formatNumber value="${consumoTotal}" type="currency" currencySymbol="" minFractionDigits = "3"/></h3>
														<h3>m&#179;</h3>  
													</label>
												</div>
												<br/><br/>
											</div>	          	       			
										</div>
										<div class="swiper-slide">
											<div>
												<br/><br/>	
												<div id="blocomediadiaria">
													<label style="color: #fff;">            			            						            		
														Média Di&aacute;ria
														<br/>
														<h3><fmt:formatNumber value="${mediaDiaria}" type="currency" currencySymbol="" minFractionDigits = "3"/></h3>
														<h3>m&#179;</h3> 
													</label>
												</div>			           			
												<br/>	
											</div>
										</div>
										<div class="swiper-slide">
											<div>	
												<br/><br/>			
												<div id="blocometamensal">			           			
													<label style="color: #fff;">            			            						            		
														Meta Mensal<br/>														
														<c:choose>
															<c:when test="${metaConsumo != null}">
																<h3><fmt:formatNumber value="${metaConsumo.meta}" type="currency" currencySymbol="" minFractionDigits = "3"/></h3>
															</c:when>
															<c:otherwise>
																<h3>--</h3>
															</c:otherwise>
														</c:choose>
														<h3>m&#179;</h3> 
													</label>
												</div>
												<br/>	           		
												<div>					
													<form action='AndRelatorioBO?acao=6' method='post' onsubmit="return exibirBlock()">
														<input type='hidden' name='idUser' value='${idUser}'>
									                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
									                    <input type='hidden' name='idCondominio' value='${idCondominio}'>
									                    <input type='hidden' name='idBridge' value='${idBridge}'>
									                    <input type='hidden' name='idMedidor' value='${idMedidor}'>
														<label class="text-danger"> * Nova Meta (m&#179;)</label>									
														<div class="input-group input-sm">
															<input type="text" class="form-control" name="meta" id="meta" placeholder="Digite a nova meta" maxlength="10" required/>
															<div class="input-group-btn">
																<button class="btn btn-primary" type="submit"><i class="glyphicon glyphicon-pencil"></i> Gravar</button>
															</div>
														</div>
													</form>
												</div>
												<br/>	
											</div>												
										</div>
									</div>
								    <!-- Add Pagination -->
								    <div class="swiper-pagination"></div>
								</div>
						
								<!-- Swiper JS -->
								<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.3.5/js/swiper.min.js"></script>
							
								<!-- Initialize Swiper -->
								<script>
									var swiper = new Swiper('.swiper-container', {
										pagination: {
											el: '.swiper-pagination',
										},
									});
								</script>
			           			<br/><br/>			           			
			           			<form action='AndRelatorioBO?acao=3' method='post' onsubmit="return exibirBlock()">
			           				<input type='hidden' name='idUser' value='${idUser}'>
				                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
				                    <input type='hidden' name='idCondominio' value='${idCondominio}'>
				                    <input type='hidden' name='idBridge' value='${idBridge}'>
				                    <input type='hidden' name='idMedidor' value='${idMedidor}'>
				                    <input type='hidden' name='data' value='${dtFim}'>
							    	<button type='submit' class='btn btn-warning' style="width: 100%" title='Clique para visualizar o gráfico'>
						            	<i class='fa fa-bar-chart'></i> Gr&aacute;fico Consumo ${fn:substring(dtFim, 8, 10)}/${fn:substring(dtFim, 5, 7)}/${fn:substring(dtFim, 0, 4)}
						            </button>
						        </form>
						        <br/>
						        <hr style="border: 1px solid #337ab7"/> 
						        <div style="text-align: right;">
									<form action='AndRelatorioBO?acao=5' method='post' onsubmit="return exibirBlock()" style="margin: 0px; padding: 0px;">
				           				<input type='hidden' name='idUser' value='${idUser}'>
					                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
					                    <input type='hidden' name='idCondominio' value='${idCondominio}'>
					                    <input type='hidden' name='idBridge' value='${idBridge}'>
					                    <input type='hidden' name='idMedidor' value='${idMedidor}'>
					                    <input type='hidden' name='data' value='${dtFim}'>
								    	<button type='submit' class='btn btn-success' title='Clique para visualizar o gráfico'>
							            	<i class='glyphicon glyphicon-refresh'></i>
							            </button>
							        </form>
						        </div>
								<h3>Medidor Pressão</h3>
								<br/>				
								<div id="blocopressao">
									<label style="color: #fff;">            			            						            		
										27/08/2018 08:20
										<br/>
										<h3><fmt:formatNumber value="${mediaDiaria}" type="currency" currencySymbol="" minFractionDigits = "3"/></h3>
										<h3>MCA</h3> 
									</label>
								</div>
								<br/>
			           			<form action='AndRelatorioBO?acao=3' method='post' onsubmit="return exibirBlock()">
			           				<input type='hidden' name='idUser' value='${idUser}'>
				                    <input type='hidden' name='idEmpresa' value='${idEmpresa}'>
				                    <input type='hidden' name='idCondominio' value='${idCondominio}'>
				                    <input type='hidden' name='idBridge' value='${idBridge}'>
				                    <input type='hidden' name='idMedidor' value='${idMedidor}'>
				                    <input type='hidden' name='data' value='${dtFim}'>
							    	<button type='submit' class='btn btn-success' style="width: 100%" title='Clique para visualizar o gráfico'>
						            	<i class='glyphicon glyphicon-list'></i> <i class='fa fa-bar-chart'></i> Relat&oacute;rio / Gr&aacute;fico ${fn:substring(dtFim, 8, 10)}/${fn:substring(dtFim, 5, 7)}/${fn:substring(dtFim, 0, 4)}
						            </button>
						        </form>
					      	</div>
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