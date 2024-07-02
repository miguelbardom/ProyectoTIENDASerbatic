<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, java.util.ArrayList, model.VO.LibroVO, model.VO.PedidoVO, 
	model.VO.DetalleVO, java.util.HashMap, java.util.Map"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="../../fragmentos/head.jsp" />
<title>Factura</title>
</head>
<body>

	<main class="p-3 m-0 border-0 bd-example m-0 border-0">
		<div class="container mt-2 mb-2">
		    <div class="row">
		        <!-- Columna para la imagen (col-4 para ocupar 4 columnas en dispositivos pequeños) -->
		        <div class="col-4">
		            <img src="<%=request.getContextPath()%>/img/libreria-miguel-ok2.png" class="img-fluid" style="max-height: 100px;">
		        </div>
		        <div class="col-5"> 
		        </div>
		        <div class="col-3">
		        	<br><br><br>
		        	<div class="text-end">
			        	<p><%= request.getAttribute("nombre_tienda") %></p>
			            <p><%= request.getAttribute("cif") %></p>
			            <p><%= request.getAttribute("direccion") %></p>
		        	</div>
		        </div>
		    </div>
		</div>
		
		<div class="container mt-5">
			<h3>Factura</h3>
			
			<div class="row mt-2">
            	<div class="col mb-4">
                	<div class="card">
                    	<div class="card-body">
                    		<% 
                    			PedidoVO pedido = (PedidoVO)request.getAttribute("pedido");
                    			List<DetalleVO> detalles = (List<DetalleVO>)request.getAttribute("detalles");
                    			List<LibroVO> productos = (List<LibroVO>)request.getAttribute("productos");
                    		%>
                    		<table class="table text-center">
		                		<h5>Estos son los detalles de su pedido:</h5>
                    			<br>
					            <thead>
					                <tr>
					                    <th>Número de Factura</th>
					                    <th>Fecha de pedido</th>
					                    <th>Método de pago</th>
					                </tr>
					            </thead>
					            <tbody>
					            	<tr>
					            		<td>
										    <% 
										        String numFactura = pedido.getNumfactura();
										        if (numFactura != null && !numFactura.equals("null")) {
										    %>
										            <%= numFactura %>
										    <% 
										        }
										    %>
										</td>
					            		<td><%= pedido.getFecha() %></td>
					            		<td><%= pedido.getMetodopago() %></td>
					            	</tr>
					            </tbody>
					        </table>
				        	<br>
					        <table class="table">
					        	<thead>
					        		<tr>
					        			<th>Producto</th>
					        			<th>Precio/unidad</th>
					        			<th>Cantidad</th>
					        			<th>Impuesto</th>
					        			<th>Total</th>
					        		</tr>
					        	</thead>
					        	<tbody>
						        <%
						        if (detalles != null && !detalles.isEmpty()) {
				                    for (DetalleVO detalle : detalles) {
				                    	
				                    	for (LibroVO producto : productos) {
				                    		
				                    		if (detalle.getLibro_id() == producto.getId()) {
				                    			
							                    if (producto != null) {
				                
						        %>
					            	<tr>
					            		<td><%= producto.getTitulo() %></td>
					            		<%
						                    } else {
							            %>
								        <td><%= detalle.getId() %></td>
								        <%        	
						                    }
					            		%>
					            		<td><%= detalle.getPreciounidad() %> €</td>
					            		<td><%= detalle.getUnidades() %></td>
					            		<td><%= detalle.getImpuesto() %></td>
					            		<td><%= detalle.getTotal() %> €</td>
					            	</tr>
					                    
					            <% 
				                    		}
				                    	}
                    				}
						        }
                				%>
					            </tbody>
					        </table>
					        <table class="table text-center">
					        	<tbody>
					        		<tr>
					        			<td></td>
					        			<td><strong>Total Pedido:</strong></td>
					        			<td><strong><%= String.format("%.2f", pedido.getTotal()) %> €</strong></td>
					        		</tr>
					        	</tbody>
					        </table>
                    		
                    		
                        </div>
                    </div>
                </div>
            </div>
        
			
		</div>
		
	</main>
	
