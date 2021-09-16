package ejer2;

public class Alumno extends Persona{
	private int curso;
	private double nota;
	
	public Alumno() {
		
	}
	
	public Alumno(String nombre, String apellido, String telefono, String DNI, int curso, double nota) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.DNI = DNI;
		this.curso = curso;
		this.nota = nota;
	}
	
	public int getCurso() {
		return curso;
	}
	public void setCurso(int curso) {
		this.curso = curso;
	}
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", DNI=" + DNI
				+ ", curso=" + curso + ", nota=" + nota + "]";
	}
	
	public void mostrarPersona() {
		System.out.println("El nombre es: "+this.nombre+".");
		System.out.println("El apellido es: "+this.apellido+".");
		System.out.println("El telefono es: "+this.telefono+".");
		System.out.println("El DNI es: "+this.DNI+".");
		System.out.println("Esta en el curso "+this.curso+".");
		System.out.println("Su nota es un "+this.nota+".");
	}
	
	
	
}
