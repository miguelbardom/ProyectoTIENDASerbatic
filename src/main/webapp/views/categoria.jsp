<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.VO.LibroVO, model.VO.CategoriaVO, java.util.HashMap" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<jsp:include page="../fragmentos/head.jsp" />
</head>
<body>
    <jsp:include page="../fragmentos/header.jsp" />
    
<%--     <jsp:include page="layout.jsp" /> --%>


    <main role="main" class="d-flex flex-row container mt-3 mb-3">

		

        <div class="row ms-5">
        	<%
			CategoriaVO categoria = (CategoriaVO)request.getAttribute("categoria");
			if (categoria != null) {
			%>
			<h3 style="color: #dd6002">
				<%= categoria.getNombre() %>
			</h3>
			<% } %>
            <%
                List<LibroVO> productosCateg = (List<LibroVO>) request.getAttribute("catalogoCateg");
            	if (productosCateg != null && !productosCateg.isEmpty()) {
            		
	                for (LibroVO producto : productosCateg) {
	                	
            %>
            
            <div class="col-md-4 mb-4">
                <div class="card">
                    <div class="card-body">
	                	<a href="<%= request.getContextPath() %>/producto?id=<%= producto.getId() %>" class="nav-link">
	                        <h5 class="card-title"><%= producto.getTitulo() %></h5>
					        <img src="<%= request.getContextPath()+producto.getUrl() %>" class="img-fluid" style="max-width:50%; max-height: 200px">
	                    </a>
	                        <p class="card-text"><%= producto.getAutor() %></p>
	                        <p class="card-text"><%= producto.getEditorial() %></p>
	                        <p class="card-text"><%= producto.getFormato() %></p>
	                        <p class="card-text">Precio: <%= producto.getPrecio() %> €</p>
	                        <form method="get" action="comprar" class="d-flex justify-content-between align-items-center">
	                            <input type="number" id="cantidad" name="cantidad" value="1" min="1" max="<%= producto.getStock() %>" step="1"
	                                class="form-control mr-2" style="width: 70px;">
	                            <input type="hidden" id="id" name="id" value="<%= producto.getId() %>">
	                            <input type="hidden" id="vista" name="vista" value="categoria">
	                            <input type="hidden" id="categoria_id" name="categoria_id" value="<%= producto.getCategoria_id() %>">
	                            <button type="submit" class="btn btn-success">Añadir al carrito</button>
	                        </form>
	                        <br>
							<% 
	    					HashMap<Integer, String> stockSup = (HashMap<Integer, String>) request.getAttribute("stockSuperado");
	    					if (stockSup != null) { 
						        Integer productId = producto.getId(); // Obtener el ID del producto actual
						        String message = stockSup.get(productId); // Obtener el mensaje correspondiente al ID del producto
						        
							    if (message != null) { // Verificar si hay un mensaje para este producto
							%>
			                    <p class="alert alert-danger">
						            <%= message %>
								</p>
						    <% 
						        }
						    } 
							%>
	                </div>
                </div>
            </div>
            <%
	                }
                }
            %>
        </div>
        

    </main>

    <jsp:include page="../fragmentos/footer.jsp" />