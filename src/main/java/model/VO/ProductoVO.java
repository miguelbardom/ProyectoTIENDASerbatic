package model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProductoVO {
	
	int id;
	String nombre;
	String descripcion;
	Double precio;
	Double impuesto;
	int stock;
	boolean baja;
	String url;
	int categoria_id;

}
