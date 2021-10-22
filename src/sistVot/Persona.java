package sistVot;

public abstract class Persona {
	private int dni;
	private String nombre;
	private Turno turnoAsignado;
	private boolean esPresidente;
	
	public Persona(int dni, String nombre)
	{
		this.dni = dni;
		this.nombre = nombre;
		
	}
	
	public abstract Turno consultarTurno();
	public abstract void votar();
	public abstract void asignarTurno();
	public abstract boolean asistioAVotar();
	
	public boolean getEsPresidente() {
		return esPresidente;
	}
	
	public Turno getTurno() {
		return turnoAsignado;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	
}
