<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, model.VO.LibroVO, java.util.HashMap, java.util.Map"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="../fragmentos/head.jsp" />
    <script src="js/enviarForm.js"></script>
	<script>
        // Función para enviar el formulario al cambiar la cantidad
        function enviarFormulario(id) {
            // Obtener el formulario por su ID
            let form = document.getElementById('form_' + id);
            if (form) {
                // Enviar el formulario al controlador /comprar
                form.submit();
            }
        }
    </script>
</head>
<body>
    <jsp:include page="../fragmentos/header.jsp" />

    <div class="container mt-3 mb-3">
        <h2>Carrito de compra</h2>
        <table class="table">
            <thead>
                <tr>
                	<th>Imagen</th>
                    <th>Título</th>
                    <th>Autor</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <% 
                HashMap<LibroVO, Integer> carrito = (HashMap<LibroVO, Integer>) request.getSession().getAttribute("carrito");

                if (carrito != null && !carrito.isEmpty()) {
                    for (Map.Entry<LibroVO, Integer> entry : carrito.entrySet()) {
                        LibroVO producto = entry.getKey();
                        Integer cantidad = entry.getValue();
                        
                %>
                <tr>
                	<td><img src="<%= request.getContextPath()+producto.getUrl() %>" class="img-fluid" 
                			style="max-width:50%; max-height: 50px">
                	</td>
                    <td><%= producto.getTitulo() %></td>
                    <td><%= producto.getAutor() %></td>
                    <td><%= producto.getPrecio() %> €</td>
                    <td>
                    	<form method="get" action="cantidad" id="form_<%= producto.getId() %>">
	                    	<input type="number" class="form-control mr-2" style="width: 70px;" id="cantidad" name="cantidad" 
    	                	min="1" max="<%= producto.getStock() %>" step="1" value="<%= cantidad %>"
    	                	onchange="enviarFormulario(<%= producto.getId() %>)">
        	            	<input type="hidden" id="id" name="id" value="<%= producto.getId() %>">
                    	</form>
                    </td>
                    <td>
<!--                         <form method="get" action="eliminarProducto"> -->
<%--                             <input type="hidden" name="productoId" value="<%= producto.getId() %>"> --%>
<!--                             <button type="submit" class="btn btn-danger">Eliminar</button> -->
                            <a href="<%=request.getContextPath()%>/eliminarProducto?id=<%= producto.getId()%>">Eliminar</a>
<!--                         </form> -->
                    </td>
                </tr>
                <% 
                    }
                %>
            </tbody>
            <tfoot>
                <tr>
                	<% Double precioTotal = (Double)request.getSession().getAttribute("precioTotal"); %>
                	<td><h5>Total: </h5></td>
                	<td></td>
                    <td colspan="5"><h5> <%= String.format("%.2f", precioTotal) %> €</h5></td>
                </tr>
                <% 
                } else {
                %>
                <tr>
                    <td colspan="5">No hay productos en el carrito.</td>
                </tr>
                <% 
                }
                %>
            </tfoot>
        </table>
        	<%
        	if (carrito != null && !carrito.isEmpty()) {
        	%>
            	<a href="<%=request.getContextPath()%>/procesarPedido" class="btn btn-warning">Procesar pedido</a>
            <%
        	}            
            %>
    </div>

    <jsp:include page="../fragmentos/footer.jsp" />
