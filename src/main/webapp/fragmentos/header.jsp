<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.VO.ProductoVO, java.util.HashMap, java.util.ArrayList, java.util.List,
	model.VO.CategoriaVO,model.DAO.CategoriaDAO"%>

<head>
    <script>
//     document.addEventListener("DOMContentLoaded", function() {
//         // Obtener referencia al elemento select
//         let selectIdioma = document.getElementById("idioma");

//         // Adjuntar un controlador de eventos para el evento change
//         selectIdioma.addEventListener("change", function() {
//             // Obtener el formulario
//             let form = document.getElementById("form_lang");

//             // Enviar el formulario
//             form.submit();
//         });
//     });
	</script>
</head>

<!-- <header class="d-flex flex-row bg-dark text-white"> -->
<!--         <div class="logo col-3 mx-auto d-flex align-items-center h-50"> -->
<%--             <a class="nav-link text-white" href="<%=request.getContextPath()%>"> --%>
<%-- 	            <img src="<%= request.getContextPath()%>/img/libreria-miguel-ok2.png" class="img-fluid w-75"> --%>
<!--             </a> -->
<!--         </div> -->
<!--             	<h3>Tienda Serbatic</h3> -->
        
<header class="d-flex flex-row bg-white text-dark align-items-center">
    <div class="col-2 mx-auto text-center">
        <a class="nav-link" href="<%=request.getContextPath()%>/?idioma=<%= session.getAttribute("idioma") %>">
            <img src="<%=request.getContextPath()%>/img/libreria-miguel-ok2.png" class="img-fluid" style="max-height: 50px;">
        </a>
    </div>
        
        
<!--         <nav class="col-4 navbar navbar-expand-xl text-light mx-auto d-flex align-items-center"> -->
<!-- 		  <div class="container-fluid"> -->
<!-- 		    <a class="navbar-brand" href="#">Navbar</a> -->
<!-- 		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarBasic" aria-controls="navbarBasic" aria-expanded="false" aria-label="Toggle navigation"> -->
<!-- 		      <span class="navbar-toggler-icon"></span> -->
<!-- 		    </button> -->
<!-- 		    <div class="collapse navbar-collapse show" id="navbarBasic"> -->
<!-- 		      <ul class="navbar-nav me-auto mb-2 mb-xl-0"> -->
<!-- 		        <li class="nav-item"> -->
<!-- 		          <a class="nav-link active" aria-current="page" href="#">Home</a> -->
<!-- 		        </li> -->
<!-- 		        <li class="nav-item"> -->
<!-- 		          <a class="nav-link" href="#">Link</a> -->
<!-- 		        </li> -->
<!-- 		      </ul> -->
<!-- 		    </div> -->
<!-- 		  </div> -->
<!-- 		</nav> -->
		
		<nav class="menuPrin col-4 navbar navbar-expand-lg mx-auto d-flex align-items-center text-dark"
            aria-label="Fourth navbar example">
            <div class="container-fluid text-dark">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarWithDropdown" aria-controls="navbarWithDropdown" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarWithDropdown">
                    <ul class="navbar-nav align-items-center">
                        <li class="nav-item btn-primary">
                            <a class="nav-link p-4 text-dark fs-5" href="<%=request.getContextPath()%>/?idioma=<%= session.getAttribute("idioma") %>">
                            	<% if (session.getAttribute("libros") != null) { %>
                            		<%= session.getAttribute("libros") %>
                            	<% } %>
<!--                             	Libros -->
                            </a>
                        </li>
                        <li class="nav-item dropdown btn-primary">
                            <a class="nav-link dropdown-toggle p-4 text-dark fs-5" href="#" id="navbarDropdownMenuLink"
                                role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <% if (session.getAttribute("categorias") != null) { %>
                            		<%= session.getAttribute("categorias") %>
                            	<% } %>
<!--                                 Categorías -->
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <%
//                          	CategoriaDAO categoriaDAO = new CategoriaDAO();
                            List<CategoriaVO> results = new ArrayList<>();
                            results = CategoriaDAO.getAll();
                                                                                    
                            for (CategoriaVO categoria : results){
                            %>
                                <li><a class="dropdown-item" href="<%= request.getContextPath()%>/categoria?id=<%= categoria.getId()%>">
                                	<%= categoria.getNombre() %>
                                	</a>
                                </li>
                            <% 
                            }
                            %>
                            </ul>
                        </li>
                        <li class="nav-item dropdown btn-primary">
                            <a class= "nav-link p-4 text-dark fs-5" href="<%=request.getContextPath()%>/contacto">
                            	<% if (session.getAttribute("contacto") != null) { %>
                            		<%= session.getAttribute("contacto") %>
                            	<% } %>
<!--                             	Contacto -->
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <div class="navbar">
	        <form action="<%=request.getContextPath()%>" method="get" id="form_lang" onchange="submit()">
	        	<select name="idioma" id="idioma" class="p-1">
	        		<option value="">
	        		<% if (session.getAttribute("lang") != null) { %>
                    	<%= session.getAttribute("lang") %>
                    <% } %>
	        		</option>
	            	<option value="es">ES</option>
            		<option value="en">EN</option>
	        	</select>
	    	</form>
        </div>
        
        <div class="col-3 navbar text-end d-flex flex-row align-items-end">
        	<div class="container-fluid m-3 text-end d-flex flex-wrap align-items-end">
	            <%
	            if (session.getAttribute("usuario") == null) {
	            %>
	            <a class="btn btn-secondary" href="<%=request.getContextPath()%>/login">
	            	<% if (session.getAttribute("login") != null) { %>
                    	<%= session.getAttribute("login") %>
                    <% } %>
<!-- 	            	Inicia Sesión -->
	            </a>
	            <a class="btn btn-success" href="<%=request.getContextPath()%>/registro">
	            	<% if (session.getAttribute("registro") != null) { %>
                    	<%= session.getAttribute("registro") %>
                    <% } %>
<!-- 	            	Regístrate -->
	            </a>
	            <%
	            } else {
	            %>
	            <a class="btn btn-light" href="<%=request.getContextPath()%>/perfil">
	            	<i class="fa fa-user-circle-o"></i>&nbsp;
	            	<% if (session.getAttribute("cuenta") != null) { %>
                    	<%= session.getAttribute("cuenta") %>
                    <% } %>
<!-- 	            	Mi Cuenta -->
	            </a>
	            <a class="btn btn-dark" href="<%=request.getContextPath()%>/logout">
	            	<% if (session.getAttribute("logout") != null) { %>
                    	<%= session.getAttribute("logout") %>
                    <% } %>
<!-- 	            	Cerrar Sesión -->
	            </a>
	            <%
	            }
	            %>
	        	<a href="<%=request.getContextPath()%>/carrito" class="btn btn-primary">
	            	<i class="fa fa-shopping-cart"></i>
	            	<span class="badge badge-danger">
	                	<%
	                	if (session.getAttribute("carrito") != null){
		                	HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>) request.getSession().getAttribute("carrito");
	                	%>
	                	<%= request.getSession().getAttribute("cantidadTotal") %>
						<%
	                	}
						%>                		
	<!--                 	out.print(carrito.size()); -->
	            	</span>
	        	</a>
        	</div>
        </div>
    </div>
</header>
