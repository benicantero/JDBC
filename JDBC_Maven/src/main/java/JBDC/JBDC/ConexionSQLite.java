package JBDC.JBDC;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.sqlite.SQLiteConfig;

public class ConexionSQLite {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String BD = null;
		String DB_URL = null;
		String DRIVER = null;
		Statement st;
		PreparedStatement pst;
		Usuario usuario = null;
		List<Usuario> listaUsuario = new ArrayList<>();
		Properties p = new Properties();
		System.out.println("Introduce un login: ");
		String login =  sc.nextLine();
		try {
			p.load(new FileReader("BD1.properties"));
			BD = p.getProperty("BD");
			DB_URL = p.getProperty("DB_URL");
			DRIVER = p.getProperty("DRIVER");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		/*final String BD = "BD/libreria";
		final String DB_URL = "jdbc:sqlite:";
		final String DRIVER = "org.sqlite.JDBC";*/

		Connection connection = null;

		try {
			Class.forName (DRIVER);
			try {
				SQLiteConfig config = new SQLiteConfig();
				config.enforceForeignKeys(true);
				connection = DriverManager.getConnection(
						DB_URL + BD, config.toProperties());

				String sqlCrearTablas = "DROP TABLE IF EXISTS usuario;\n" + 
						"CREATE TABLE usuario (\n" + 
						"        id INTEGER PRIMARY KEY AUTOINCREMENT,\n" + 
						"        login UNIQUE TEXT,\n" + 
						"        edad INTEGER\n" + 
						");";
				st = connection.createStatement();
				//st.executeUpdate(sqlCrearTablas);
				String sqlInsertarUsuario = "INSERT INTO usuario (login, edad) "
						+ "VALUES ('susana', 21);";
				//st.executeUpdate(sqlInsertarUsuario);

				String sqlUpdateUsuario = "UPDATE usuario SET edad = 35"
						+ " WHERE login = 'joaquin';";
				//	st.executeUpdate(sqlUpdateUsuario);
				String sqlDeleteUsuario = "DELETE FROM usuario WHERE"
						+ " login = 'felipe';";
				//	st.executeUpdate(sqlDeleteUsuario);

				String sqlConsultarUsuario = "SELECT * FROM usuario "
						+ "WHERE login = '" + login +"';";
				String sqlCosultarUsuarioPS = "SELECT * FROM usuario "
						+ "WHERE login = ?;";
				pst = connection.prepareStatement(sqlCosultarUsuarioPS);
				pst.setString(1, login);
				//pst.close();
				ResultSet resultSet = pst.executeQuery();
				//ResultSet resultSet = st.executeQuery(sqlConsultarUsuario);



				while (resultSet.next()) {
					listaUsuario.add(new Usuario(
							resultSet.getString("login"),
							resultSet.getInt("edad")));
				}

				System.out.println(listaUsuario);

				/*try {
					connection.setAutoCommit(false);*/


					

				String sql1 = "UPDATE usuario SET login = ? where id = ?;";
					pst = connection.prepareStatement(sql1);
					pst.setString(1, "susana");
					pst.setInt(2, 2);
					pst.executeUpdate();
					
					String sql2 = "UPDAT usuario SET edad = ? where id = ?;";
					pst = connection.prepareStatement(sql2);
					pst.setInt(1, 55);
					pst.setInt(2, 2);
					pst.executeUpdate();

			/*		connection.commit();
				}
				catch (SQLException e) {
					connection.rollback();
				}*/

			} catch (SQLException e) {

				System.out.println("Error SQL");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("No accedo al driver");
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sc.close();
	}

}
class Usuario {

	private String login;
	private int edad;

	public Usuario(String login, int edad) {
		this.login = login;
		this.edad = edad;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", edad=" + edad + "]";
	}


}




