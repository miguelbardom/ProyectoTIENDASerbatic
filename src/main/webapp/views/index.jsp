<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.VO.LibroVO, java.util.HashMap" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<jsp:include page="../fragmentos/head.jsp" />
	<style>
		/* Establecer el ancho de los input number */
		input[type="number"] {
		    width: 100px; /* Define el ancho deseado */
		}
	</style>
</head>
<body>
    <jsp:include page="../fragmentos/header.jsp" />
    
<%--     <jsp:include page="layout.jsp" /> --%>

	<h4>
		<%
		Object usuarioObj = session.getAttribute("usuario");
		if (usuarioObj != null) {
			model.VO.UsuarioVO usuario = (model.VO.UsuarioVO) usuarioObj;
		%>
<!-- 			Bienvenido,	 -->
<%-- 			<%= usuario.getNombre() %> --%>
		<% } %>
	</h4>

    <main role="main" class="d-flex flex-row container mt-3 mb-3">
        
        <div class="col-md-3 me-5 mt-2" style="margin-left: -4%">
            <aside class="bg-light p-1">
                <h4 style="color: #dd6002">Filtrar Productos</h4>
                
                <!-- Filtro por Editorial -->
                <div class="mb-3">
                	<form action="editorial" method="GET" class="form-control">
                    	<label class="form-label" for="categoria">Editorial:</label>
                    	<select class="form-control" id="editorial" name="editorial">
                    		<option value="0">Elegir:</option>
                        <%
                        	List<String> editoriales = (List<String>)request.getAttribute("editoriales");
                        	for (String editorial : editoriales){
                        %>		
                        	<option value="<%= editorial %>"><%= editorial %></option>
                        <%		
                        	}
                        %>
                    	</select>
                    	<p></p>
                    	<button class="btn btn-outline-primary btn-sm" type="submit">Filtrar por Editorial</button>
                    </form>
                </div>
                
                <!-- Filtro por Precio -->
                <div class="mb-3">
	                <form action="precio" method="GET" class="form-control">
	    				<label class="form-label" for="precio_min">Precio Mínimo (€):</label>
	    				<input class="form-control" type="number" id="precio_min" name="precio_min" min="0" step="0.01" value="0.00">
						<p></p>
	    				<label class="form-label" for="precio_max">Precio Máximo (€):</label>
	    				<input class="form-control" type="number" id="precio_max" name="precio_max" min="0" step="0.01" value="0.00">
						<p></p>
	    				<button class="btn btn-outline-primary btn-sm" type="submit">Filtrar por Precio</button>
					</form>
                </div>
                
            </aside>
        </div>

        <div class="row">
        <%
        	List<LibroVO> librosEdito = (List<LibroVO>) request.getAttribute("librosEdito");
        	String editorial = (String)request.getAttribute("editorial");
        	List<LibroVO> librosFiltro = (List<LibroVO>) request.getAttribute("librosFiltro");
        	Double precioMin = (Double)request.getAttribute("precioMin");
        	Double precioMax = (Double)request.getAttribute("precioMax");
        	if (librosFiltro == null && librosEdito == null) {
        		
        %>
        	<div class="card-body">
        		<h3 style="color: #dd6002">Libros más vendidos</h3>
        	</div>
        	<%
                List<LibroVO> masVendidos = (List<LibroVO>) request.getAttribute("masVendidos");
            	if (masVendidos != null) {
            		
	                for (LibroVO producto : masVendidos) {
	                	
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
	                            <input type="hidden" id="vista" name="vista" value="index">
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
        	
        	<span class="mb-2"> </span>
        	<div class="card-body">
        		<h3 style="color: #dd6002">Catálogo de libros</h3>
        	</div>
            <%
                List<LibroVO> catalogo = (List<LibroVO>) request.getAttribute("catalogo");
            	if (catalogo != null) {
            		
	                for (LibroVO producto : catalogo) {
	                	
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
	                            <input type="hidden" id="vista" name="vista" value="index">
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
            <%
            } else {
            	
            	if (librosFiltro != null) {
            %>
            <div class="card-body">
        		<h3 style="color: #dd6002">Libros entre <%= precioMin %>€ y <%= precioMax %>€</h3>
        	</div>
        	<%
	                for (LibroVO producto : librosFiltro) {
	                	
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
	                            <input type="hidden" id="vista" name="vista" value="index">
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
            	} else if (librosEdito != null) {
            %>
            <div class="card-body">
        		<h3 style="color: #dd6002">
        		<%  if (editorial.equals("0")) {
        		%>
        		Todas las editoriales
        		<%
        			} else {
        		%>
        		<%= editorial %>
        		<%
        			}
        		%>
        		</h3>
        	</div>
        	<%
	                for (LibroVO producto : librosEdito) {
	                	
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
	                            <input type="hidden" id="vista" name="vista" value="index">
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
            }
            %>
        </div>
        

    </main>

    <jsp:include page="../fragmentos/footer.jsp" />