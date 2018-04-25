package com.iesvirgendelcarmen.teoria;

import java.util.List;

public class TestLibros {

	public static void main(String[] args) {
		
		LibroDAO manipulacionLibros = new LibroDAOimp();
		List<LibroDTO> listaTotal = manipulacionLibros.listarTodosLibros();
		
		System.out.println(listaTotal);
		
		

	}

}
