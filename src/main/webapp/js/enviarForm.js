// formSubmit.js

// Función para enviar el formulario al cambiar la cantidad
function enviarFormulario(id) {
    // Obtener el formulario por su ID
    let form = document.getElementById('form_' + id);
    if (form) {
        // Enviar el formulario al controlador /comprar
        form.submit();
    }
}
