<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.VO.UsuarioVO, model.VO.ProductoVO, java.util.HashMap" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<jsp:include page="../fragmentos/head.jsp" />
</head>
<body>
    <jsp:include page="../fragmentos/header.jsp" />
    
    <main class="p-3 m-0 border-0 bd-example m-0 border-0">
		
		<div class="container mt-5 mb-4">
		    <div class="row gx-8">
		        <div class="col-6 offset-3 mb-4">
		            <div class="card shadow">
		                <div class="card-header border-0">
		                    <div class="row mt-3 ms-1">
		                        <div class="">
		                            <% UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario"); %>
		                            <h3 class="black justify-content text-center">Perfil de 
		                            	<b><%= usuario.getNombre() %></b>
		                            </h3>
		                        </div>
		                    </div>
		                </div>
		                <div class="card-body text-center">
		                
		                	<a href="<%=request.getContextPath()%>/historialPedidos" class="btn btn-primary">Ver historial de Pedidos</a>
		                	<a href="<%=request.getContextPath()%>/editarPerfil" class="btn btn-warning">Editar datos de Perfil</a>
<%-- 		                	<a href="<%=request.getContextPath()%>/editarPassword" class="btn btn-warning">Cambiar Contraseña</a> --%>
		                	
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		
		
    </main>
    
    <jsp:include page="../fragmentos/footer.jsp" />