<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List, model.VO.LibroVO, model.VO.CategoriaVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="../../fragmentos/head.jsp" />

<title>Dashboard: Librería Miguel</title>
</head>

<body>
	<jsp:include page="../../fragmentos/headerAdmin.jsp" />
	
	<div class="container mt-5">
        <%
		Object usuarioObj = session.getAttribute("usuarioA");
        model.VO.UsuarioVO usuario = null;
		if (usuarioObj != null) {
			usuario = (model.VO.UsuarioVO) usuarioObj;
		%>
		<% } %>
		
		
		<div class="row gx-8">
			<div class="col-6 offset-3 mb-4">
				<div class="card shadow">
					<div class="card-header border-0">
						<div class="row mt-3 ms-1">
							<div class="">
								<h3 class="black justify-content text-center">Editar Producto</h3>
							</div>
						</div>
					</div>
					<div class="card-body text-center">
					
						<form method="post" action="editarProducto" enctype="multipart/form-data">
							<%
			                LibroVO libro = (LibroVO)request.getAttribute("libro");
			            	if (libro != null) {
			            	%>
							<input type="hidden" name="id" id="id" value="<%= libro.getId() %>">
							<div class="form-floating mb-3">
								<input type="text" name="titulo" id="titulo" class="form-control"
									placeholder="Título" value="<%= libro.getTitulo() %>">
								<label for="titulo">Título</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<input type="text" name="autor" id="autor"
									class="form-control" placeholder="Autor" value="<%= libro.getAutor() %>">
								<label for="autor">Autor</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<input type="text" name="editorial" id="editorial" class="form-control"
									placeholder="Editorial" value="<%= libro.getEditorial() %>">
								<label for="editorial">Editorial</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<input type="number" name="isbn" id="isbn" class="form-control"
									placeholder="ISBN" value="<%= libro.getIsbn() %>">
								<label for="isbn">ISBN</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
	                            <select class="form-control" id="categoria_id" name="categoria_id">
								<%
	                            List<CategoriaVO> categorias = (List<CategoriaVO>)request.getAttribute("categorias");
	                                                                                    
	                            for (CategoriaVO categoria : categorias){
	                            	
	                            	if (categoria.getId() == libro.getCategoria_id()) {
	                            		
	                            %>
	                            	<option value="<%= categoria.getId() %>" selected><%= categoria.getNombre() %></option>
								<%
	                            	} else {
	                            %>
	                            	<option value="<%= categoria.getId() %>"><%= categoria.getNombre() %></option>
	                            <%
	                            	}
	                            }
								%>
	                            </select>
								<label for="categoria_id">Categoría</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<input type="number" name="precio" id="precio" class="form-control"
									placeholder="Precio" step="0.01" value="<%= libro.getPrecio() %>">
								<label for="precio">Precio</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<input type="number" name="impuesto" id="impuesto" class="form-control"
									placeholder="Impuesto" step="0.1" value="<%= libro.getImpuesto() %>">
								<label for="impuesto">Impuesto</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<input type="number" name="stock" id="stock" class="form-control"
									placeholder="Stock" value="<%= libro.getStock() %>">
								<label for="stock">Stock</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<input class="form-control" type="file" id="img" name="img">
								<label for="img">Imagen</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<select class="form-control" id="baja" name="baja">
							        <c:choose>
							            <c:when test="${libro.isBaja()}">
							                <option value="true" selected>Sí</option>
							                <option value="false">No</option>
							            </c:when>
							            <c:otherwise>
							                <option value="false" selected>No</option>
							                <option value="true">Sí</option>
							            </c:otherwise>
							        </c:choose>
                    			</select>
								<label for="baja">Dar de baja:</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<textarea class="form-control" id="descripcion" name="descripcion" style="height: 150px"><%= libro.getDescripcion() %></textarea>
								<label for="descripcion">Descripción</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<input type="text" name="formato" id="formato" class="form-control"
									placeholder="Formato" value="<%= libro.getFormato() %>">
								<label for="formato">Formato</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<input type="number" name="paginas" id="paginas" class="form-control"
									placeholder="Páginas" value="<%= libro.getPaginas() %>">
								<label for="paginas">Páginas</label>
							</div>
							<p class="error"></p>
							
							<div class="form-floating mb-3">
								<input type="number" name="ano_publicacion" id="ano_publicacion" class="form-control"
									placeholder="Año de Publicación" value="<%= libro.getAño_publicacion() %>">
								<label for="ano_publicacion">Año de Publicación</label>
							</div>
							<p class="error"></p>
							
							<p class="correcto"></p>

							<br>
							
							<input type="submit" value="Editar Producto" name="EditarProducto"
								class="btn btn-primary">
							
							<% } %>

						</form>

					</div>
				</div>
			</div>
		</div>
		
		
    </div>
	
	<jsp:include page="../../fragmentos/footerAdmin.jsp" />