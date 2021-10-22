package sistVot;

public class Presidente extends Persona {
	
	private int dni;
	private int nombre;
	private boolean esPresidente = true;
	
	public Presidente(int dni, String nombre) {
		super(dni, nombre);
		// TODO Esbozo de constructor generado automáticamente
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
	public void asignarTurno() {
		// TODO Esbozo de método generado automáticamente
		
	}

	@Override
	public boolean asistioAVotar() {
		// TODO Esbozo de método generado automáticamente
		return false;
	}

}
