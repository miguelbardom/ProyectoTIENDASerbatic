package model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ConfiguracionVO {
	
	int id;
	String clave;
	String valor;
	String tipo;

}
