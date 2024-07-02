<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="java.util.List, model.VO.LibroVO" %>

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="../../fragmentos/head.jsp" />
<title>Dashboard: Librería Miguel</title>
</head>

<body>
	<jsp:include page="../../fragmentos/headerAdmin.jsp" />
	
	
	<div class="container mt-5">
		<div class="row align-items-center">
	        <h2 class="col">Productos</h2>
	        <div class="col-auto">
	        	<a class="btn btn-primary mb-3" href="<%=request.getContextPath()%>/crearProducto">Crear Producto</a>
	        </div>
		</div>
        <%
		Object usuarioObj = session.getAttribute("usuarioA");
        model.VO.UsuarioVO usuario = null;
		if (usuarioObj != null) {
			usuario = (model.VO.UsuarioVO) usuarioObj;
		%>
		<% } %>
        <table class="table">
            <thead>
                <tr>
                	<th>Imagen</th>
                    <th>Título</th>
                    <th>Autor</th>
                    <th>Stock</th>
                    <th>Precio</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                List<LibroVO> catalogo = (List<LibroVO>) request.getAttribute("catalogo");
            	if (catalogo != null) {
            		
	                for (LibroVO producto : catalogo) {
	                	
            	%>
                <tr>
                	<td><img src="<%= request.getContextPath()+producto.getUrl() %>" class="img-fluid" 
                			style="max-width:50%; max-height: 50px">
                	</td>
                    <td><%= producto.getTitulo() %></td>
                    <td><%= producto.getAutor() %></td>
                    <td><%= producto.getStock() %></td>
                    <td><%= producto.getPrecio() %> €</td>
                    <td>
                    	<a class="btn btn-outline-primary mb-3" href="<%=request.getContextPath()%>/editarProducto?id=<%= producto.getId()%>">Editar</a>
                    </td>
                    <%
                    if (usuario != null){
                    	if (usuario.getRol_id() == 3){
                    %>
                    <td>
                    	<a class="btn btn-outline-danger mb-3" href="<%=request.getContextPath()%>/eliminarProd?id=<%= producto.getId()%>">Baja</a>
                    </td>                    
                    <%		
                    	}
                    }
                    %>
                </tr>
                <% 
                    }
                %>
            </tbody>
            <tfoot>
                <% 
                } else {
                %>
                <tr>
                    <td colspan="5">No hay productos en la base de datos</td>
                </tr>
                <% 
                }
                %>
            </tfoot>
        </table>
    </div>
	
	
	<jsp:include page="../../fragmentos/footerAdmin.jsp" />