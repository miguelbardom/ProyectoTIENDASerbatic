<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, java.util.ArrayList, model.VO.LibroVO, model.VO.PedidoVO, model.VO.UsuarioVO,  
	model.VO.DetalleVO, java.util.HashMap, java.util.Map"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="../../fragmentos/head.jsp" />
<title>Factura</title>
</head>
<body>

	<main class="p-3 m-0 border-0 bd-example m-0 border-0">
		<div class="container mt-2">
		    <div class="row">
		        <!-- Columna para la imagen (col-4 para ocupar 4 columnas en dispositivos pequeños) -->
		        <div class="col-4">
		            <img src="<%=request.getContextPath()%>/img/libreria-miguel-ok2.png" class="img-fluid" style="max-height: 100px;">
		        </div>
		        <div class="col-5"> 
		        </div>
		        <div class="col-3">
		        	<br><br>
		        	<div class="text-end">
			        	<p><%= request.getAttribute("nombre_tienda") %></p>
			            <p><%= request.getAttribute("cif") %></p>
			            <p><%= request.getAttribute("direccion") %></p>
		        	</div>
		        </div>
		    </div>
		</div>

		<div class="container mt-1">
			<h3>Factura:</h3>
			
			<div class="row mt-2">
            	<div class="col mb-4">
                	<div class="card">
                    	<div class="card-body">
                    		<% 
                    			UsuarioVO cliente = (UsuarioVO)request.getAttribute("cliente");
                    			PedidoVO pedido = (PedidoVO)request.getAttribute("pedido");
                    			List<DetalleVO> detalles = (List<DetalleVO>)request.getAttribute("detalles");
                    			List<LibroVO> productos = (List<LibroVO>)request.getAttribute("productos");
                    		%>
                    		<div class="row">
                    			<div class="col-2"></div>
                   				<div class="col-3">
                   					<p><%= cliente.getNombre() %> <%= cliente.getApellidos() %></p>
                   					<p><%= cliente.getEmail() %></p>
                   				</div>
                    			<div class="col-2">
                    			</div>
                    			<div class="col-3 text-end">
                    				<p><strong>Número de factura:</strong> &nbsp;&nbsp;&nbsp;&nbsp;<%= pedido.getNumfactura() %></p>
                    				<p><strong>Fecha de pedido:</strong> &nbsp;&nbsp;&nbsp;<%= pedido.getFecha() %></p>
                    				<p><strong>Método de pago:</strong> &emsp;&emsp;&emsp;<%= pedido.getMetodopago() %></p>
                    			</div>
                   			</div>
				        	<hr>
<!-- 				        	<br> -->
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
					            		<td><%= detalle.getImpuesto() %>%</td>
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
                    		<br>
                    		
                        </div>
                    </div>
                </div>
            </div>
        
			
		</div>
		
	</main>
	
