<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<footer>
        <div class="container">
            <p>&copy; 2024 Librería Miguel. Todos los derechos reservados.</p>
            <form action="<%=request.getContextPath()%>/tienda" method="post">
    			<button type="submit" class="btn btn-link">Acceso Tienda</button>
			</form>
        </div>
    </footer>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
    