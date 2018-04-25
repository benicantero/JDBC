package com.iesvirgendelcarmen.teoria;

public class Singleton {
	
	private String nombre;
	private static Singleton singleton;
	
	private Singleton(String nombre) {
		this.nombre = nombre;
	}
	
	public static Singleton getInstance(String nombre) {
		if (singleton == null)
			singleton = new Singleton(nombre);
		else
			System.out.println("el objeto ya existe");
		return singleton;
	}

	@Override
	public String toString() {
		return "Singleton [nombre=" + nombre + "]";
	}
	
	
}
