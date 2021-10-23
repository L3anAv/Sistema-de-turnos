package sistVot;
import java.util.*;


public class SistemaDeVotacion {
	
	private String nombreDelSistema;
	private ArrayList<Mesa> mesas;
	private ArrayList<Persona> personasVotacion;

// Constructor  ---------------------------------
	public SistemaDeVotacion(String nombreDelSistema) throws Exception 
{
		//EXCEPTION
		if(nombreDelSistema == null)
			throw new Exception("El nombre del sistema de votacion no puede ser nulo.");
		
		this.nombreDelSistema = nombreDelSistema;
		personasVotacion = new ArrayList<>();
		mesas = new ArrayList<>();
		
}

// Metodos de la clase ---------------------------

	
	//Me agrega igual el presidente aunque tenga el mismo dni.
	//El equals me da false aunque presidente y votante tengo el mismo dni.
	public void registrarVotante(String dni, String nombre, int edad, boolean tieneEnfermedad, boolean esTrabajador, boolean esPresidente) throws Exception
{
		//EXCEPTION
		if(edad < 16)
			throw new Exception("El votante no puede tener menos de 16 años.");
		
		//Crea la persona segun si es o no presidente.
		Persona votante;
		if(esPresidente) {
			votante = new Presidente(dni, nombre);
		}else {
			votante = new Votante(dni, nombre, tieneEnfermedad, esTrabajador);
		}
		
		if(!existePersona(votante)	) {
			personasVotacion.add(votante);
		}
}
	public boolean existePersona(Persona personaAEvaluar) 
{
		
				boolean existe = false;
				for (Persona persona : personasVotacion){
					  if(personaAEvaluar.equals(persona))
						existe = true;
					}
			
				return existe;
	}

	public int agregarMesa(String tipoMesa, int dni) 
{
		if(tipoMesa == "Mayor65") 
{
//			Mesa mesaMayor = new MesaMayores(presidente);
//			return mesaMayor.getNumDeMesa();
}
		return 1;
}

	
	public Tupla<Integer, Integer> asignarTurno(int dni) throws Exception
{
		
		
		int num = 1;
		int num2 = 2;
		Tupla<Integer, Integer> tupla = new Tupla<Integer, Integer>(num, num2);
		
		return tupla;
		
}
	
	public int asignarTurno() 
	{
		//Retorna la cantidad de turnos asignados.
		return 1;
	}
	
	public boolean votar(int dni) 
	{
		//Retorna si ya voto o no, verificando con el dni del votante, o presidente.
		return false;
	};
	
	public int votantesConTurno(String tipoMesa) {return 1;};
	
	public Tupla<Integer, Integer> consultaTurno(int dni)
	{
		int num = 1;
		int num2 = 2;
		Tupla<Integer, Integer> tupla = new Tupla<Integer, Integer>(num, num2);
	
		return tupla;
	}; 

	
// Getters and Setters ---------------------------
	
	public ArrayList<Mesa> getMesas() 
{
		return mesas;
}


	public ArrayList<Persona> getPersonasVotacion() 
{
		return personasVotacion;
}

}
