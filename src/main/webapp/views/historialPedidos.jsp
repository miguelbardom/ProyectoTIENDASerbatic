<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, model.VO.ProductoVO, model.VO.PedidoVO, model.VO.DetalleVO, java.util.HashMap, java.util.Map"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="../fragmentos/head.jsp" />
</head>
<body>
	<jsp:include page="../fragmentos/header.jsp" />

	<main class="p-3 m-0 border-0 bd-example m-0 border-0 mb-2">
		
		<div class="container mt-5">
			<h3>Historial de Pedidos</h3>
			
			<div class="row mt-2">
            	<div class="col mb-4">
            	<table class="table text-center">
		            <thead>
					<tr>
						<th>Fecha de pedido</th>
						<th>Método de pago</th>
						<th>Total</th>
						<th>Estado del pedido</th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
					</thead>
		            <tbody>
	            	<%
	                List<PedidoVO> pedidos = (List<PedidoVO>) request.getAttribute("pedidos");
	            	if (pedidos != null) {
	            		
		                for (PedidoVO pedido : pedidos) {
		                	
	            	%>
		            <tr>
					    <td><%= pedido.getFecha() %></td>
					    <td><%= pedido.getMetodopago() %></td>
						<td><%= String.format("%.2f",pedido.getTotal()) %> €</td>
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
		            	<td><a href="<%=request.getContextPath()%>/detalle?id=<%= pedido.getId() %>"
		            			 class="btn btn-outline-warning btn-sm">Ver detalle</a>
		            	</td>
					    <td>
					    <% if (pedido.getEstado().equals("PE")) { %>
					    <form action="<%=request.getContextPath()%>/cancelarPedido" method="post">
    						<input type="hidden" name="id" value="<%= pedido.getId() %>">
    						<button type="submit" class="btn btn-outline-danger btn-sm">Cancelar Pedido</button>
						</form>
						<% } %>
		            	</td>
		            	<td>
					    <% if (pedido.getEstado().equals("E")) { 
					    %>
						<form action="<%=request.getContextPath()%>/factura" method="post" target="_blank">
    						<input type="hidden" name="id" value="<%= pedido.getId() %>">
    						<input type="hidden" name="vista" value="cliente">
    						<button type="submit" class="btn btn-danger btn-sm">Ver Factura</button>
						</form>
						<% } %>
		            	</td>
		            </tr>
	            	<%
		                }
	            	}
	            	%>
	            	</tbody>
	            </table>
                </div>
            </div>
        
		</div>
		
	</main>
	
	<jsp:include page="../fragmentos/footer.jsp" />
