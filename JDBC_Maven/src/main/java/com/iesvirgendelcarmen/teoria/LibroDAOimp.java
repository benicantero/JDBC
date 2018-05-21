package com.iesvirgendelcarmen.teoria;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOimp implements LibroDAO {
	
	private static Connection conexion = Conexion.getConexion();
	
	@Override
	public List<LibroDTO> listarTodosLibros() {
		List <LibroDTO> listaLibros = new ArrayList<>();
		// Crear objeto Statement
		String sql= "SELECT * FROM libro";
		try (Statement statement = conexion.createStatement();){
			//Crear ogjeto resulset
			ResultSet resulset = statement.executeQuery(sql);
			while(resulset.next()) {
				LibroDTO libro = new LibroDTO(resulset.getString(2), resulset.getString(3), resulset.getString(4), resulset.getString(5));
				listaLibros.add(libro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaLibros;
	}

	@Override
	public List<LibroDTO> listarLibrosDisponibles() {
		List <LibroDTO> listaLibros = new ArrayList<>();
		String sql = "select * from libro where libro.id not in (select idLibro from prestamos);";
		try (Statement statement = conexion.createStatement();){
			ResultSet resultSet= statement.executeQuery(sql);
			while(resultSet.next()) {
				LibroDTO libro = new LibroDTO(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
				listaLibros.add(libro);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return listaLibros;
	}

	@Override
	public boolean borrarLibro(String nombreLibro, String nombreAutor) {
		String sql = "delete from libro where nombre=? and autor=?;";
		int updates = 0;
		try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);){
			preparedStatement.setString(1, nombreLibro);
			preparedStatement.setString(2, nombreAutor);
			updates = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
				
		return updates != 0;
	}

	@Override
	public boolean actualizarCategoriaLibro(LibroDTO libro, String nombreCategoria) {
		//UPDATE libro SET categoria ='Seguridad' WHERE nombre='Santa Tecla'
		int updates= 0;
		String sql = "UPDATE libro SET categoria =? WHERE nombre=?;";
		try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);){
			preparedStatement.setString(1, nombreCategoria);
			preparedStatement.setString(2, libro.getNombreLibro());
			updates = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return updates != 0;
	}

	
	@Override
	public boolean insertarLibro(LibroDTO libro) {
		//INSERT INTO libro (nombre, autor, editorial, categoria) VALUES ("Santa Tecla","Pepito","Editorial 1","Redes");
		String sql = "INSERT INTO libro (nombre, autor, editorial, categoria) VALUES (?,?,?,?);";
		int resultado = 0;
		try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);) {
			preparedStatement.setString(1, libro.getNombreLibro());
			preparedStatement.setString(2, libro.getNombreAutor());
			preparedStatement.setString(3, libro.getEditorial());
			preparedStatement.setString(4, libro.getNombreCategoria());
			resultado = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error insercion de datos");
		}
		System.out.println("Insertando datos" + (resultado == 1));
		return resultado == 1;
	}

	@Override
	public boolean insertarLibros(List<LibroDTO> listaLibros) {
		try {
			conexion.setAutoCommit(false);
			for (LibroDTO libroDTO : listaLibros) {
				insertarLibro(libroDTO);
			}
			conexion.commit();
			System.out.println("Insertando datos de lista correctamente");
			return true;
		} catch (SQLException e1) {
			try {
				conexion.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				System.out.println("No se puede insertar datos en lista");
				return false;
			}
		}
	}
}
