package ejer2;

public class Profesores extends Persona{
	private boolean esTutor;
	private int cantidadAsignaturas;

	public Profesores() {
		
	}
	
	public Profesores(String nombre, String apellido, String telefono, String DNI, boolean esTutor,
			int cantidadAsignaturas) {
		super();
		this.esTutor = esTutor;
		this.cantidadAsignaturas = cantidadAsignaturas;
	}

	public boolean isEsTutor() {
		return esTutor;
	}
	public void setEsTutor(boolean esTutor) {
		this.esTutor = esTutor;
	}
	public int getCantidadAsignaturas() {
		return cantidadAsignaturas;
	}
	public void setCantidadAsignaturas(int cantidadAsignaturas) {
		this.cantidadAsignaturas = cantidadAsignaturas;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
