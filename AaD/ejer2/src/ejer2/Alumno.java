package ejer2;

public class Alumno extends Persona{
	private int curso;
	private double nota;
	
	public Alumno() {
		
	}
	
	public Alumno(String nombre, String apellido, String telefono, String DNI, int curso, double nota) {
		super();
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
	
	
	
}
