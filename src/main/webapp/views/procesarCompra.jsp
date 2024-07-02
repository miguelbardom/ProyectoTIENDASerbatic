<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, model.VO.LibroVO, java.util.HashMap, java.util.Map"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="../fragmentos/head.jsp" />
<script>
	// Función para enviar el formulario al pulsar el enlace "Confirmar pedido"
	function enviarForm() {
		// Obtener los radio buttons por su nombre
		let tarjeta = document.getElementById('tarjeta');
		let paypal = document.getElementById('paypal');
		let errorDiv = document.querySelector('.alert');

		// Verificar si al menos un radio button está seleccionado
		if (tarjeta.checked || paypal.checked) {
// 			console.log(tarjeta);
// 			console.log(paypal);
			
			// Obtener el formulario por su ID
			let form = document.getElementById('formPago');
// 			console.log(form);
			
			if (form) {
	            // Crear un elemento input para el método de pago seleccionado
// 	            let metodoPagoValue = tarjeta.checked ? 'Tarjeta' : 'Paypal';
// 	            let metodopagoInput = document.createElement('input');
// 	            metodopagoInput.type = 'hidden';
// 	            metodopagoInput.name = 'metodopago';
// 	            metodopagoInput.value = metodoPagoValue;

	            // Agregar el campo oculto al formulario
// 	            form.appendChild(metodopagoInput);
	            
	            // Obtener el valor del radio button seleccionado
            	let metodoPagoValue = tarjeta.checked ? 'Tarjeta' : 'Paypal';
            	// Actualizar la URL del enlace con el valor del método de pago seleccionado
            	let confirmarPedidoLink = document.querySelector('.btn.btn-warning');
            	if (confirmarPedidoLink) {
            	    confirmarPedidoLink.href += metodoPagoValue;
            	}
								
				// Enviar el formulario
				form.submit();
// 				console.log("hola");
			}
// 			console.log(form);
			return true;
			
		} else {
			// Mostrar mensaje al usuario indicando que debe seleccionar un método de pago
// 			alert('Por favor, selecciona un método de pago antes de confirmar tu pedido.');
			
			let mensaje = "Debes seleccionar un método de pago antes de confirmar tu pedido";
			// Mostrar el div de alerta solo si se muestra la alerta
	        if (errorDiv) {
	            errorDiv.innerText = mensaje;
	            errorDiv.style.display = 'block'; // Mostrar el div de alerta
	        }
			
// 	        console.log(tarjeta);
// 			console.log(paypal);
			return false;
		}
	}
</script>

</head>
<body>
	<jsp:include page="../fragmentos/header.jsp" />

	<main class="p-3 m-0 border-0 bd-example m-0 border-0 mb-1">

		<div class="container mt-5">
			<h2>Procesar pedido</h2>

			<div class="accordion" id="accordionPanelsStayOpen">
				<div class="accordion-item">
					<h2 class="accordion-header">
						<button class="accordion-button" type="button"
							data-bs-toggle="collapse"
							data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true"
							aria-controls="panelsStayOpen-collapseOne">Selecciona un método de pago
						</button>
					</h2>
					<div id="panelsStayOpen-collapseOne"
						class="accordion-collapse collapse show" style="">
						<div class="accordion-body">

							<!-- SACARLO DE LA BASE DE DATOS -->

							<form method="post" action="confirmarPedido" id="formPago">
								<label for="tarjeta" class="form-label"> 
									<input class="form-check-input" type="radio" name="metodoPago"
									id="tarjeta" value="Tarjeta"> Tarjeta
								</label>
								<br>
								<label for="paypal" class="form-label"> 
									<input class="form-check-input" type="radio" name="metodoPago"
									id="paypal" value="Paypal"> Paypal
								</label>
<!-- 								<input type="submit" value="Confirmar" name="confirmar" class="btn btn-primary"> -->
							</form>
							
							<div class="alert alert-danger" role="alert" style="display: none;"></div>

						</div>
					</div>
				</div>
				<div class="accordion-item">
					<h2 class="accordion-header">
						<button class="accordion-button collapsed" type="button"
							data-bs-toggle="collapse"
							data-bs-target="#panelsStayOpen-collapseTwo"
							aria-expanded="true" aria-controls="panelsStayOpen-collapseTwo">
							Resumen del pedido
						</button>
					</h2>
					<div id="panelsStayOpen-collapseTwo"
						class="accordion-collapse collapse" style="">
						<div class="accordion-body">

							<div class="container mt-0">
								<table class="table">
									<thead>
										<tr>
											<th>Imagen</th>
											<th>Título</th>
											<th>Autor</th>
											<th>Formato</th>
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
											<td><%=producto.getTitulo()%></td>
											<td><%=producto.getAutor() %></td>
											<td><%=producto.getFormato() %></td>
											<td><%=producto.getPrecio()%> €</td>
											<td><%=cantidad%></td>
											<td></td>
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
							</div>


						</div>
					</div>
				</div>
			</div>
			<br>
			<a href="<%=request.getContextPath()%>/confirmarPedidoX?metodoPago=" class="btn btn-warning"
				 onclick="return enviarForm()">Confirmar pedido
			</a>

		</div>


	</main>

	<jsp:include page="../fragmentos/footer.jsp" />