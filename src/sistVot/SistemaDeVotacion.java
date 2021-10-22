package sistVot;
import java.util.ArrayList;

public class SistemaDeVotacion {
	private String nombreDelSistema;
	private ArrayList<Mesa> mesas;
	private ArrayList<Persona> personasVotacion;
	
	public SistemaDeVotacion(String nombreDelSistema) throws Exception 
	{
		
		if(nombreDelSistema == null)
			throw new Exception("El nombre no puede ser nulo");
		
		this.nombreDelSistema = nombreDelSistema;
		personasVotacion = new ArrayList<>();
		mesas = new ArrayList<>();
		
	}
	
	public void registrarVotante(int dni, String nombre, int edad, boolean tieneEnfermedad, boolean esTrabajador) throws Exception
	{
		if(edad <= 16)
			throw new Exception("La edad no puede ser igual o menos a 16.");
		
		//Instancio el nuevo votante.
		Votante vot = new Votante(edad, nombre, tieneEnfermedad, esTrabajador);
		
		//Con el equals de votante debo verficar que son distintos (diferente DNI), para luego agregarlos a la lista de votantes.
		personasVotacion.add(vot);
		
	}

	public int agregarMesa(String tipoMesa, int dni) 
	{
		//Evaluacion del parametro tipoMesa
		if(tipoMesa == "Mesa trabajadores") {
			//Creo la mesa para trabajadores.
			//retorno numero de mesa.
			return 1;
		} else if(tipoMesa == "Mesa Enfermedades") {
			//creo la mesa para enfermedades.
			//retorno numero de mesa.
			return 1;
		} else if(tipoMesa == "Mesa Mayores") {
			//creo la mesa para mayores
			//Retorno numero de mesa.
			return 1;
		}
		
		//Retorna el numero de mesa de la mesa creada.
		return 1;
		
	}
	
//	hola
	
	public Tupla<Integer, Integer> asignarTurno(int dni)
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
	
//	public List<Tupla<String, Integer>> sinTurnoSegunTipoMesa(); 

}
