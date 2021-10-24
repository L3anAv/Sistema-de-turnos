package sistVot;
import java.util.*;


public class SistemaDeVotacion {
	
	private String nombreDelSistema;
	private ArrayList<Mesa> mesas;
	private ArrayList<Votante> votantes;
	private int[] franjasHorarias;

// Constructor  ---------------------------------
	public SistemaDeVotacion(String nombreDelSistema) throws Exception 
{
		//EXCEPTION
		if(nombreDelSistema == null)
			throw new Exception("El nombre del sistema de votacion no puede ser nulo.");
		
		this.nombreDelSistema = nombreDelSistema;
		votantes = new ArrayList<>();
		mesas = new ArrayList<>();
		franjasHorarias = new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16, 17}; 
}

// Metodos de la clase ---------------------------

	
	//Me agrega igual el presidente aunque tenga el mismo dni.
	//El equals me da false aunque presidente y votante tengo el mismo dni.
	
	public void registrarVotante(int dni, String nombre, int edad, boolean tieneEnfermedad, boolean esTrabajador) throws Exception
	{
		//EXCEPTION
		if(edad < 16)
			throw new Exception("El votante no puede tener menos de 16 aÃ±os.");
		
		//Crea la persona segun si es o no presidente.
		Votante votante = new Votante(dni, nombre, tieneEnfermedad, esTrabajador);
				
		if(!existePersona(votante)	) {
			votantes.add(votante);
		}
	}
	
	public boolean existePersona(Persona personaAEvaluar) 
	{
		
				
				boolean existe = false;
				for (Persona persona : votantes)
					existe = existe || personaAEvaluar.equals(persona); 
					
			
				return existe;
	}

	/* Agregar una nueva mesa del tipo dado en el parámetro y asignar el presidente
	* de cada una, el cual deberá estar entre los votantes registrados y sin turno asignado.
	* - Devuelve el número de mesa creada.
	* si el president es un votante que no está registrado debe generar una excepción
	* si el tipo de mesa no es válido debe generar una excepción
	* Los tipos válidos son: “Enf_Preex”, “Mayor65”, “General” y “Trabajador”
	*/
	public int agregarMesa(String tipoMesa, int dni) 
	{
		
		int numeroMesa = mesas.size() + 1;
		Votante votante = null;
		
		try {
			
			votante = obtenerVotante(dni);
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Presidente presidente = new Presidente(dni, votante.getNombre());
		
		Mesa mesa;
		
		switch (tipoMesa) {
			
			case "Enf_Preex":
				
				mesa = new MesaEnfPreex(presidente, numeroMesa, franjasHorarias);
				break;
	
			case "Mayor65":
				
				mesa = new MesaMayores(presidente, numeroMesa, franjasHorarias);
				break;
				
			case "Trabajador":
				
				mesa = new MesaTrabajadores(presidente, numeroMesa);
				break;
				
				
			default:

				mesa = new MesaGeneral(presidente, numeroMesa, franjasHorarias);
				break;
		}
		
		mesas.add(mesa);
		
		return numeroMesa;
	}

	private Votante obtenerVotante(int dni) throws Exception {
		
		
		Iterator<Votante> itVot = votantes.iterator();
		Boolean encontroVotante = false;
		Votante votante = null;
		
		while(itVot.hasNext() || !encontroVotante ) {
			
			votante = itVot.next();
			encontroVotante = votante.getDni() == dni; 
		}
		
		if(encontroVotante) {
			return votante;
		}
		
		else throw new Exception("Votante no registrado");
	}

	/* Asigna un turno a un votante determinado.
	* - Si el DNI no pertenece a un votante registrado debe generar una excepción.
	* - Si el votante ya tiene turno asignado se devuelve el turno como: Número de
	* Mesa y Franja Horaria.
	* - Si aún no tiene turno asignado se busca una franja horaria disponible en una
	* mesa del tipo correspondiente al votante y se devuelve el turno asignado, como
	* Número de Mesa y Franja Horaria.
	* - Si no hay mesas con horarios disponibles no modifica nada y devuelve null.
	* (Se supone que el turno permitirá conocer la mesa y la franja horaria asignada)
	*/

	public Tupla<Integer, Integer> asignarTurno(int dni) throws Exception
	{
		
		
		
		
		
		return null;
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

	
	public ArrayList<Votante> getVotantes() {
		// TODO Auto-generated method stub
		return this.votantes;
	}
	
	

}
