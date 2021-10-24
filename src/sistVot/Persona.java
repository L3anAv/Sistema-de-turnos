package sistVot;

public abstract class Persona extends Object {
	
	private int dni;
	private String nombre;
	private Turno turnoAsignado;
	private boolean esPresidente;
	
// Constructor  --------------------------------
	
	public Persona(int dni, String nombre)
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
		if(other.getDni() == this.dni)
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
	
	public int getDni() 
{
		return dni;
}

}
