package sistVot;

public class Votante extends Persona{

	private int dni;
	private Turno turnoAsignado;
	private int edad;
	private boolean tieneEnfermedad;
	private boolean esTrabajador;
	private boolean esPresidente = false;
	
	public Votante(int dni, String nombre, boolean tieneEnfermedad, boolean esTrabajador){
		
		super(dni, nombre);
		this.tieneEnfermedad = tieneEnfermedad;
		this.esTrabajador = esTrabajador;
		
	}
	
	@Override
	public Turno consultarTurno() {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public void votar() {
		// TODO Esbozo de método generado automáticamente
		
	}

	@Override
	public boolean asistioAVotar() {
		// TODO Esbozo de método generado automáticamente
		return false;
	}

	@Override
	public void asignarTurno() {
		// TODO Esbozo de método generado automáticamente
		
	}
	
	

}
