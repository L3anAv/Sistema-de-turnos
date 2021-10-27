package sistVot;

public class Votante extends Persona{

	private int edad;
	private Turno turnoAsignado;
	private boolean tieneEnfermedad;
	private boolean esTrabajador;
	private boolean esPresidente = false;
	
// Constructor  ---------------------------------		
	
	public Votante(int dni, String nombre, boolean tieneEnfermedad, boolean esTrabajador)
{
		
		super(dni, nombre);
		this.tieneEnfermedad = tieneEnfermedad;
		this.esTrabajador = esTrabajador;
		
}

//Metodos de Votante (Sobre-Escritura | Heredados)
	
	@Override
	public Turno consultarTurno() {
		return turnoAsignado;
}

	@Override
	public void votar() {
		turnoAsignado.registrarVoto();

}

	@Override
	public void asignarTurno(Turno nuevoTurno) {
		turnoAsignado = nuevoTurno;
		
	}
	
	@Override
	public boolean asistioAVotar() {
		if(turnoAsignado.getAsistioAVotar()) {
			return true;
		}else {
			return false;
		}
	}
	
// Getters  ---------------------------


	public int getEdad() {
		return edad;
	}

	public boolean isTieneEnfermedad() {
		return tieneEnfermedad;
	}

	public boolean isEsTrabajador() {
		return esTrabajador;
	}

	public boolean isEsPresidente() {
		return esPresidente;
	}

}
