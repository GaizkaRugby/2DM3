package ejer1;

/*
Realizar un programa Java, en el que se dan de alta alumnos de la escuela, y posteriormente mostrar sus datos.

1.Preguntar cuántos alumnos matriculados hay y guardar esa cantidad.
2.Según el número de alumnos matriculados, preguntar sus nombres y sus edades.
3.Mostrar la información obtenida anteriormente: Nombre y edad de cada alumno
4.Obtener la media de la edades de los alumnos
5.Mostrar qué alumno es el que tiene mayor edad (mostrando el alumno y su edad)
6.Mostrar qué alumno es el que tiene menor edad (mostrando el alumno y su edad)
7.Obtener el alumno con el nombre más largo.
*/

import java.util.Scanner;

public class Ejer1 {

	
	public static int pedirM(Scanner sc) {
		int c;
		boolean esEntero;
			System.out.println("Introduce la cantidad de alumnos matriculados:");
			esEntero=sc.hasNextInt();
			while(!esEntero) {
				sc.nextLine();
				System.out.println("Introduce un número válido:");
				esEntero=sc.hasNextInt();
			};
			
			c=sc.nextInt();
		return c;
	}
	
	public static String pedirNom(Scanner sc) {
		String nombre;
			System.out.println("Escribe tu nombre:");
			nombre=sc.next();
			
			/*while(!sc.hasNextLine()) {
				System.out.println("Escribe un nombre válido");
				nombre=sc.nextLine();
			};*/
		
		return nombre;
	}
	
	public static int pedirEdad(Scanner sc, String []n, int i) {
		int e;
			
			System.out.println("Introduce la edad de el alumno "+n[i]+":");
			e=sc.nextInt();
		
			/*while(sc.hasNextInt()) {
				System.out.println("Introduce una edad válida:");
				e=sc.nextInt();
			};*/
		
		return e;
	}
	
	public static float calcularMedia(int []edades,int cant) {
		int suma=0;
		float m;
			for(int i=0;i<cant;i++) {
				suma+=edades[i];
			}
		
			m=(float)suma/cant;
			
		return m;
	}
	
	public static int calcularMayor(int []edades,int cant) {
		int m=edades[0];
			
			for(int i=1;i<cant;i++) {
				if(edades[i]>m) {
					m=edades[i];
				}
			}
			
		return m;
	}
	
	public static int calcularMenor(int []edades,int cant) {
		int m=edades[0];
			
			for(int i=1;i<cant;i++) {
				if(edades[i]<m) {
					m=edades[i];
				}
			}
			
		return m;
	}
	
	public static int calcularNL(String []nombres,int cant) {
		int m=nombres[0].length();
			
			for(int i=1;i<cant;i++) {
				if(nombres[i].length()>m) {
					m=nombres[i].length();
				}
			}
			
		return m;
	}
	
	public static void mostrarResult(String []nombres, int []edades, int cant, float m,int may, int men, int largo) {
		
		for(int i=0;i<cant;i++) {//mostrar datos de los alumnos
			System.out.println("-"+nombres[i]+"  "+edades[i]+".");
			System.out.println();
		}
		
		System.out.println("La media de edad es de "+m+" años.");
		System.out.println();
		
		System.out.println("El alumno mas mayor de todos es:");
		System.out.println();
		for(int i=0;i<cant;i++) {//mostrar datos de los alumnos
			if(edades[i]==may) {
				System.out.println("-"+nombres[i]+"  "+edades[i]+".");
				System.out.println();
			}
		}
		
		System.out.println("El alumno mas joven de todos es:");
		System.out.println();
		for(int i=0;i<cant;i++) {//mostrar datos de los alumnos
			if(edades[i]==men) {
				System.out.println("-"+nombres[i]+"  "+edades[i]+".");
				System.out.println();
			}
		}
		
		System.out.println("El alumno con el nombre mas largo de todos es:");
		System.out.println();
		for(int i=0;i<cant;i++) {//mostrar datos de los alumnos
			if(nombres[i].length()==largo) {
				System.out.println("-"+nombres[i]+"  "+edades[i]+".");
				System.out.println();
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System. in);
		
		int cant, edades[], mayor, menor, l;
		float mediaE;
		String nombres[];
		
		cant=pedirM(sc);//metodo para pedir la cantidad de alumnos matriculados
		
		nombres=new String[cant];
		edades=new int [cant];
		
		for(int i=0;i<cant;i++) {//pedir datos de los alumnos
			nombres[i]=pedirNom(sc);
			edades[i]=pedirEdad(sc, nombres, i);
		}
		
		mediaE=calcularMedia(edades, cant);
		
		
		mayor=calcularMayor(edades, cant);
		
		menor=calcularMenor(edades, cant);
		
		l=calcularNL(nombres, cant);
		
		mostrarResult(nombres, edades, cant, mediaE, mayor, menor, l);
		
		sc.close();
		
	}

}
