package model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class DetalleVO {
	
	int id;
	int pedido_id;
	int libro_id;
	int unidades;
	Double preciounidad;
	Double impuesto;
	Double total;

}
