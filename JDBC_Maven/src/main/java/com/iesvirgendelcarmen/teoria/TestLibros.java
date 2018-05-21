package com.iesvirgendelcarmen.teoria;

import java.util.ArrayList;
import java.util.List;

public class TestLibros {

	public static void main(String[] args) {
		
		LibroDAO manipulacionLibros = new LibroDAOimp();
		
		LibroDTO libro1 = new LibroDTO("Kubuntu", "H.C.Petersen", "Sistemas operativos", "Editorial 1");
		LibroDTO libro2 = new LibroDTO("Nociones de Kotlin", "Y.H.Norghies", "Programacion", "Editorial 2");
		LibroDTO libro3 = new LibroDTO("LibreOffice", "R.J.Restren", "Ofimatica", "Editorial 3");
		
		List<LibroDTO> listaDeLibros = new ArrayList<>();
		listaDeLibros.add(libro1);
		listaDeLibros.add(libro2);
		listaDeLibros.add(libro3);
		
		//Listar todos los libros
		List<LibroDTO> listaTotal = manipulacionLibros.listarTodosLibros();
		System.out.println(listaTotal);
		//Listar libros disponibles
		System.out.println(manipulacionLibros.listarLibrosDisponibles());
		//Insertar libro
		System.out.println(manipulacionLibros.insertarLibro(libro1));
		//manipulacionLibros.insertarLibro(libro2);
		//manipulacionLibros.insertarLibro(libro3);
		//Borrar libro
		//manipulacionLibros.borrarLibro(libro1.getNombreLibro(), libro1.getNombreAutor());
		//manipulacionLibros.borrarLibro(libro2.getNombreLibro(), libro2.getNombreAutor());
		//manipulacionLibros.borrarLibro(libro3.getNombreLibro(), libro3.getNombreAutor());
		//Insetar lista libros
		//System.out.println(manipulacionLibros.insertarLibros(listaDeLibros));
		
	}

}
