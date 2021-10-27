package sistVot;

public abstract class Persona extends Object {
	
	private int dni;
	private String nombre;
	private Tupla<Integer, Integer> turnoAsignado;
	private boolean esPresidente;
	private boolean asistioAVotar;
	
// Constructor  --------------------------------
	
	public Persona(int dni, String nombre)
{
		this.dni = dni;
		this.nombre = nombre;
		
}

// Metodos de la clase 
	
	
	public boolean tieneTurnoAsignado( ) {
		return turnoAsignado != null;
	}
	
	
	
	
	
	//(Abstract) ---------------	
	
	// public abstract Turno consultarTurno();
	//public abstract void votar();
	
	
	
	
	

// Equals ---------------	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!(obj.getClass().equals(getClass())))
			return false;
		
		Persona other = (Persona) obj;
		if(other.getDni() == this.dni)
			return true;
		return false;
	}

// Getters and Setters --------------------------

	public boolean asistioAVotar() {
		return asistioAVotar;
	}
	
	public void asistioAVotar(boolean asistioAVotar) {
		this.asistioAVotar = asistioAVotar;
	}
	
	public void asignarTurno(Tupla<Integer, Integer> nuevoTurno) {
		turnoAsignado = nuevoTurno;
	}
	
	public boolean getEsPresidente() 
{
		return esPresidente;
}

	public Tupla<Integer, Integer> getTurnoAsignado() 
{
		return turnoAsignado;
}

	public String getNombre() 
{
		return nombre;
}
	
	public int getDni() 
{
		return dni;
}

}
