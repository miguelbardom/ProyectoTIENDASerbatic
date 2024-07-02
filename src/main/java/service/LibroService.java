package service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.DAO.LibroDAO;
import model.VO.LibroVO;

public class LibroService {
	
	public static List<LibroVO> obtenerTresLibrosMenorStock() {
        // Obtener todos los libros
        List<LibroVO> libros = LibroDAO.findAll();

        // Ordenar la lista de libros por stock de menor a mayor
        Collections.sort(libros, Comparator.comparingInt(LibroVO::getStock));

        // Obtener los primeros 3 libros (los que tienen menor stock)
        List<LibroVO> tresLibrosMenorStock = libros.subList(0, Math.min(3, libros.size()));

        return tresLibrosMenorStock;
    }

}
