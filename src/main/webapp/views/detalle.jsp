<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, java.util.ArrayList, model.VO.LibroVO, model.VO.PedidoVO, model.VO.DetalleVO, java.util.HashMap, java.util.Map"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="../fragmentos/head.jsp" />
</head>
<body>
	<jsp:include page="../fragmentos/header.jsp" />

	<main class="p-3 m-0 border-0 bd-example m-0 border-0">
		
		<div class="container mt-3 mb-3">
			<h3>Pedido</h3>
			
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
					                	<th>
										    <% 
										        String numFactura = pedido.getNumfactura();
										        if (numFactura != null && !numFactura.equals("null")) {
										    %>
										            Número de Factura
										    <% 
										        }
										    %>
					                    </th>
					                    <th>Fecha de pedido</th>
					                    <th>Método de pago</th>
					                    <th>Estado del pedido</th>
					                </tr>
					            </thead>
					            <tbody>
					            	<tr>
					            		<td>
										    <% 
										        if (numFactura != null && !numFactura.equals("null")) {
										    %>
										            <%= numFactura %>
										    <% 
										        }
										    %>
										</td>
					            		<td><%= pedido.getFecha() %></td>
					            		<td><%= pedido.getMetodopago() %></td>
					            		<td>
					            		<% 
									    HashMap<String, String> estadosPed = (HashMap<String, String>) session.getAttribute("estadosPed");
									    for (Map.Entry<String, String> entry : estadosPed.entrySet()) {
					                        String estado = entry.getKey();
					                        String valor = entry.getValue();
						                        if (pedido.getEstado().equals(estado)) { 
									    %>
									    	<%= valor %>
									    <% }} %>
					            		</td>
					            	</tr>
					            </tbody>
					        </table>
				        	<br>
					        <table class="table">
					        	<thead>
					        		<tr>
					        			<th>Imagen</th>
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
					            		<td><img src="<%= request.getContextPath()+producto.getUrl() %>" class="img-fluid" 
					                			style="max-width:50%; max-height: 50px">
					                	</td>
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
	
	<jsp:include page="../fragmentos/footer.jsp" />
