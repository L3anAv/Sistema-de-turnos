package sistVot;

public class Votante extends Persona{

	private int edad;
	private boolean tieneEnfermedad;
	private boolean esTrabajador;
	// private boolean esPresidente = false;
	
// Constructor  ---------------------------------		
	
	public Votante(int dni, String nombre, int edad, boolean tieneEnfermedad, boolean esTrabajador)
{
		
		super(dni, nombre);
		this.edad = edad;
		this.tieneEnfermedad = tieneEnfermedad;
		this.esTrabajador = esTrabajador;
		
}
	
	@Override
	public String toString() {

		String toString = "NOMBRE: " + getNombre();
		toString += " | DNI: " + getDni();
		toString += " | EDAD : " + edad;
		toString += " | TIENE ENFERMEDAD: " + tieneEnfermedad;
		toString += " | ES TRABAJADOR: " + esTrabajador;
		toString += " | ES MAYOR: " + esMayor();
		
		return toString;
	}

//Metodos de Votante (Sobre-Escritura | Heredados)
	
	
	/*	 TODOS DEBEN SER DEL PADRE
	@Override
	public void votar() {
		getTurnoAsignado().registrarVoto();
		
	}
	*/

	/* Debe ser un set del padre
	@Override
	public void asignarTurno(Turno nuevoTurno) {
		turnoAsignado = nuevoTurno;
		
	}
	*/
	
	/*
	@Override
	public boolean asistioAVotar() {
		if(getTurnoAsignado().getAsistioAVotar()) {
			return true;
		}else {
			return false;
		}
	}
	*/
	
// Getters  ---------------------------


	/*
	public Turno getTurnoAsignado() {
		return turnoAsignado;
	}
	*/

	public int getEdad() {
		return edad;
	}

	public boolean tieneEnfermedad() {
		return tieneEnfermedad;
	}

	public boolean esTrabajador() {
		return esTrabajador;
	}

	public boolean esMayor() {
		return edad >= 65;
	}

	/*
	public boolean esPresidente() {
		return esPresidente;
	}
	*/

}
