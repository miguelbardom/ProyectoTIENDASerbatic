<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.VO.LibroVO, java.util.HashMap, java.util.ArrayList, 
    model.VO.CategoriaVO, model.DAO.CategoriaDAO" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<jsp:include page="../fragmentos/head.jsp" />
</head>
<body>
    <jsp:include page="../fragmentos/header.jsp" />
    
    <main class="p-3 m-0 border-0 bd-example m-0 border-0">
      
      <div class="row">
		<div class="col-2">&nbsp;</div>
		<div class="col-7 container mt-3 mb-3">
<!-- 			<h3></h3> -->
			
			<div class="row mt-2">
            	<div class="col mb-4">
                	<div class="card">
                    	<div class="card-body">
                    		
                    	<%
                    	LibroVO producto = (LibroVO)request.getAttribute("producto");
                   		if (producto != null) {
                   			
                   		%>
                   		  <div class="row">
                   		  	<div class="col-4 ms-2 me-2">
						        <img src="<%= request.getContextPath()+producto.getUrl() %>" class="img-fluid" style="min-width:25%; max-height:450px">
		                        <br><br>
		                        <p class="card-text">Número de páginas: <%= producto.getPaginas() %></p>
		                        <p class="card-text">Año de publicación: <%= producto.getAño_publicacion() %></p>
                   		  	</div>
                   		  	<div class="col-4">
	                    		<h3 class="card-title text-uppercase justify-content"><%= producto.getTitulo() %></h3>
		                        <p class="card-text text-uppercase justify-content"><%= producto.getAutor() %></p>
		                        <p class="card-text"><%= producto.getEditorial() %> - <%= producto.getIsbn() %></p>
		                        <p class="card-text">Formato: <%= producto.getFormato() %></p>
		                        <p class="card-text">
		                        <%
		                            List<CategoriaVO> results = new ArrayList<>();
		                            results = CategoriaDAO.getAll();
		                                                                                    
		                            for (CategoriaVO categoria : results){
		                            	if (categoria.getId() == producto.getCategoria_id()) {
		                            %>
		                                Categoría: <%= categoria.getNombre() %>
		                            <% 
		                            }}
		                           %>
		                        </p>
		                        <p class="card-text">Descripción: <br><%= producto.getDescripcion() %></p>
                   		  	</div>
                   		  	<div class="col-3">
                   		  		<br><br><br><br><br><br><br><br><br>
		                        <p class="card-text text-end">Precio: <%= producto.getPrecio() %> €</p>
		                        <form method="get" action="comprar" class="d-flex justify-content-between align-items-center">
		                            <input type="number" id="cantidad" name="cantidad" value="1" min="1" max="<%= producto.getStock() %>" step="1"
		                                class="form-control mr-2" style="width: 70px;">
		                            <input type="hidden" id="id" name="id" value="<%= producto.getId() %>">
		                            <input type="hidden" id="vista" name="vista" value="producto">
		                            <button type="submit" class="btn btn-success">Añadir al carrito</button>
		                        </form>
                   		  	</div>
                   		  </div>
	                        <br>
						<% 
	    				HashMap<Integer, String> stockSup = (HashMap<Integer, String>)request.getAttribute("stockSuperado");
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
	    				} else {
	    					
						%>
						<p class="card-text">No existe el producto seleccionado</p>
						<%
	    				}
						%>
						</div>
					</div>
				</div>
			</div>
    	</div>
    	<div class="col-2">&nbsp;</div>
      </div>
    </main>
    
    <jsp:include page="../fragmentos/footer.jsp" />