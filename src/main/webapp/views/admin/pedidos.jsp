<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List, model.VO.LibroVO, model.VO.PedidoVO, model.VO.DetalleVO,
	java.util.HashMap, java.util.Map" %>

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="../../fragmentos/head.jsp" />
<title>Dashboard: Librería Miguel</title>
</head>

<body>
	<jsp:include page="../../fragmentos/headerAdmin.jsp" />
	
	
	<main class="p-3 m-0 border-0 bd-example m-0 border-0">
		<%
		Object usuarioObj = session.getAttribute("usuarioA");
        model.VO.UsuarioVO usuario = null;
		if (usuarioObj != null) {
			usuario = (model.VO.UsuarioVO) usuarioObj;
		%>
		<% } %>
		<div class="container mt-5">
			<h3>Pedidos</h3>
			
			<div class="row mt-2">
            	<div class="col mb-4">
            	<table class="table text-center">
		            <thead>
					<tr>
						<th>Fecha de pedido</th>
						<th>Método de pago</th>
						<th>Total</th>
						<th>Cliente</th>
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
		            	<td><%= pedido.getUsuario_id() %></td>
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
					    <td>
					    <% if (pedido.getEstado().equals("PE")) { 
					    		if (usuario.getRol_id() == 2 || usuario.getRol_id() == 3){
					    %>
					    <form action="<%=request.getContextPath()%>/validarEnvio" method="post">
    						<input type="hidden" name="id" value="<%= pedido.getId() %>">
    						<button type="submit" class="btn btn-outline-success btn-sm">Validar Envío</button>
						</form>
						<% }	}%>
		            	</td>
		            	<td>
					    <% if (pedido.getEstado().equals("PC")) { 
					    		if (usuario.getRol_id() == 3){
					    %>
					    <form action="<%=request.getContextPath()%>/validarCancelacion" method="post">
    						<input type="hidden" name="id" value="<%= pedido.getId() %>">
    						<button type="submit" class="btn btn-outline-danger btn-sm">Validar Cancelación</button>
						</form>
						<% }	}%>
		            	</td>
		            	<td>
					    <% if (pedido.getEstado().equals("E")) { 
					    		if (usuario.getRol_id() == 2 || usuario.getRol_id() == 3){
					    %>
					    <form action="<%=request.getContextPath()%>/factura" method="post" target="_blank">
    						<input type="hidden" name="id" value="<%= pedido.getId() %>">
    						<input type="hidden" name="vista" value="admin">
    						<button type="submit" class="btn btn-danger btn-sm">Ver Factura</button>
						</form>
						<% }	}%>
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
	
	
	<jsp:include page="../../fragmentos/footerAdmin.jsp" />