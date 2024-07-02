package model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class LibroVO {
	
	int id;
	String titulo;
	String autor;
	String editorial;
	String isbn;
	int categoria_id;
	Double precio;
	Double impuesto;
	int stock;
	String url;
	boolean baja;
	String descripcion;
	String formato;
	int paginas;
	int año_publicacion;

}
