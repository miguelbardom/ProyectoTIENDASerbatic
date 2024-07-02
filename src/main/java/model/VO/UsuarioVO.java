package model.VO;

import lombok.Data;

@Data
public class UsuarioVO {
	
	int id;
	int rol_id;
	String email;
	String clave;
	String nombre;
	String apellidos;
//	String direccion;
//	String provincia;
//	String localidad;
//	String telefono;
//	String dni;
	boolean baja;
}
