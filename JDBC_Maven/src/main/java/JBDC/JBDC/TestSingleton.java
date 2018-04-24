package JBDC.JBDC;

public class TestSingleton {

	public static void main(String[] args) {
		Singleton singleton1 = Singleton.getInstance("instancia1");
		Singleton singleton2 = Singleton.getInstance("instancia2");

		System.out.println(singleton1);
		System.out.println(singleton2);
		
		//Singleton singleton = new Singleton("instancia"); no se puede
		//constructor es privado
	}

}
