package ejer2;
import java.util.ArrayList;
import java.util.Scanner;
public class Ejer2 {
	

	public static void menu(Scanner sc, ArrayList<Alumno> alumnos, ArrayList<Profesores> profes, int opciones) {
		
		
		do {
			do {
				System.out.println("¿Que deseas hacer?");
				System.out.println("1.Dar de alta a un alumno.");
				System.out.println("2.Dar de alta a un profesor.");
				System.out.println("3.Ver alumnos de primero");
				System.out.println("4.Ver alumnos de segundo.");
				System.out.println("5.Mostrar porcentaje de alumnos aprobados.");
				System.out.println("6.Mostrar el profesor con más asignaturas y que además sea tutor");
				System.out.println("7.Salir");
				opciones=sc.nextInt();
				
				if(opciones!=1&opciones!=2&opciones!=3&opciones!=4&opciones!=5&opciones!=6&opciones!=7&opciones!=8) {
					System.out.println("Introduce un numero entre el 1 y el 8.");
				}
			}while(opciones!=1&opciones!=2&opciones!=3&opciones!=4&opciones!=5&opciones!=6&opciones!=7&opciones!=8);
			
			switch(opciones){
				case 1: alumnos.add(crearAlumno(sc));
				break;
				case 2: profes.add(crearProfesor(sc));
				break;
				case 3: mostrarAlumnosPrimero(alumnos);
				break;
				case 4: mostrarAlumnosSegundo(alumnos);
				break;
				case 5: mostrarPorcentajeAprobados(alumnos);
				break;
				case 6: mostrarProfeMasAsignaturas(profes);
				break;
				default: System.out.println("Fin de programa.");
				break;
			}
			
		}while(opciones!=7);
	}
	public static Alumno crearAlumno(Scanner sc) {
		
		String n,a,t,d;
		int c;
		double nota;
		
		System.out.println("¿Cual es el nombre del nuevo alumno?");
		n=sc.next();
	
		System.out.println("¿Cual es el apellido del nuevo alumno?");
		a=sc.next();
		
		System.out.println("¿Cual es el telefono del nuevo alumno?");
		t=sc.next();
		
		System.out.println("¿Cual es el DNI del nuevo alumno?");
		d=sc.next();
		
		System.out.println("¿Que curso esta cursando el nuevo alumno(1 o 2)?");
		c=sc.nextInt();
		
		System.out.println("¿Cual es la nota del nuevo alumno?");
		nota=sc.nextDouble();
		System.out.println();
		
		Alumno alum=new Alumno(n,a,t,d,c,nota);
		return alum;
	}
	public static Profesores crearProfesor(Scanner sc) {
		String n,a,t,d;
		boolean tut=true;
		int cantidad, tutor;
		
		System.out.println("¿Cual es el nombre del nuevo profesor?");
		n=sc.next();
		
		System.out.println("¿Cual es el apellido del nuevo profesor?");
		a=sc.next();
		
		System.out.println("¿Cual es el telefono del nuevo profesor?");
		t=sc.next();
		
		System.out.println("¿Cual es el DNI del nuevo profesor?");
		d=sc.next();
		
		System.out.println("¿Es el nuevo profesor tutor(1 para si, 2 para no)?");
		tutor=sc.nextInt();
		
		switch(tutor){
			case 1:tut=true;
			break;
			case 2:tut=false;
			break;
		}
		
		System.out.println("¿Cuantas asignaturas da el nuevo profesor?");
		cantidad=sc.nextInt();
		System.out.println();
		Profesores profe=new Profesores(n,a,t,d,tut,cantidad);
		return profe;
	}
	
	public static void mostrarAlumnosPrimero(ArrayList<Alumno> alumnos) {
		
		for(int i=0;i<alumnos.size();i++) {
			
			if(alumnos.get(i).getCurso()==1) {
			
					System.out.println(alumnos.get(i).toString());
					System.out.println();
			}
		
		}
		
	}
	
	public static void mostrarAlumnosSegundo(ArrayList<Alumno> alumnos) {
		
		for(int i=0;i<alumnos.size();i++) {
			
			if(alumnos.get(i).getCurso()==2) {
			
					System.out.println(alumnos.get(i).toString());
					System.out.println();
		
			}
		
		}
		
	}
	
	public static void mostrarPorcentajeAprobados(ArrayList<Alumno> alumnos)  {
	
		double porcentaje;
		
		int cont=0;
		
		for(int i=0;i<alumnos.size();i++) {
			
			if(alumnos.get(i).getNota()>=5) {
				cont++;
			}
			
		}
		porcentaje=(double)100*(double)cont/(double)alumnos.size();
		
		System.out.println("El porcentaje de aprobados es de: "+porcentaje+"%");
		System.out.println();
		
	}
	
	public static void mostrarProfeMasAsignaturas(ArrayList<Profesores> profes)  {
		
		int mayor=0;
		if(profes.size()>0) {
			for(int i=0;i<profes.size();i++) {
				
				if(profes.get(i).getCantidadAsignaturas()>mayor) {
					
					mayor=profes.get(i).getCantidadAsignaturas();
		
				}
			
			}
			
			for(int i=0;i<profes.size();i++) {
				
				if(profes.get(i).isEsTutor() & profes.get(i).getCantidadAsignaturas()==mayor) {
				
						System.out.println(profes.get(i).toString());
						System.out.println();
				}
			
			}
		}else {
			System.out.println("No hay profesores.");
			System.out.println();
		}
		
	}
	
	public static void main(String[] args) {
	
		Scanner sc= new Scanner(System. in);
		ArrayList<Alumno>alumnos=new ArrayList<>();
		ArrayList<Profesores>profes=new ArrayList<>();
		int opciones=0;
		
		menu(sc, alumnos, profes, opciones);
		
		sc.close();
		
	}
	
}
