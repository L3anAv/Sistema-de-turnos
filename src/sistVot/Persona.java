package sistVot;

public abstract class Persona extends Object {
	
	private String dni;
	private String nombre;
	private Turno turnoAsignado;
	private boolean esPresidente;
	
// Constructor  --------------------------------
	
	public Persona(String dni, String nombre)
{
		this.dni = dni;
		this.nombre = nombre;
		
}

// Metodos de la clase (Abstract) ---------------	
	
	public abstract Turno consultarTurno();
	public abstract void votar();
	public abstract void asignarTurno(Turno nuevoTurno);
	public abstract boolean asistioAVotar();
	

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
		if(other.getDni().equals(getDni()))
			return true;
		return false;
	}

// Getters and Setters --------------------------

	public boolean getEsPresidente() 
{
		return esPresidente;
}

	public Turno getTurno() 
{
		return turnoAsignado;
}

	public String getNombre() 
{
		return nombre;
}
	
	public String getDni() 
{
		return dni;
}

}
