package sistVot;

public class Presidente extends Persona {
	
	private boolean esPresidente = true;
	private Turno turnoAsignado;

// Constructor  ---------------------------------
	
	public Presidente(int dni, String nombre) 
{
		super(dni, nombre);
}

// Metodos de la clase ----------------------
	
	@Override
	public Turno consultarTurno() {
		return null;
	}

	@Override
	public void votar() {
		// TODO Esbozo de método generado automáticamente
		
	}

	@Override
	public boolean asistioAVotar() {
		return false;
	}

	@Override
	public void asignarTurno(Turno nuevoTurno) {
		turnoAsignado = nuevoTurno;
		
	}

// Getters and Setters ------------------

	public boolean isEsPresidente() {
		return esPresidente;
	}

	public Turno getTurnoAsignado() {
		return turnoAsignado;
	}

	
	

}
