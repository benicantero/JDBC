package com.iesvirgendelcarmen.teoria;

import java.util.List;

public interface LibroDAO {

	//Metodos a implementar por las clases que implementen la interfaz
	
	List<LibroDTO> listarTodosLibros();  //la clase que lo implemente tiene que hacer la busqueda en la BBDD
	List<LibroDTO> listarLibrosDisponibles();
	boolean borrarLibro(String nombreLibro, String nombreAutor);
	boolean actualizarCategoriaLibro (String nombreCategoria);
	boolean insertarLibro (LibroDTO libro);
	boolean insertarLibros (List<LibroDTO> listaLibros);
	
}
