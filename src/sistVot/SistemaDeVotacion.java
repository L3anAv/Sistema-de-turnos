package sistVot;
import java.util.*;


public class SistemaDeVotacion {
	
	private String nombreDelSistema;
	private ArrayList<Mesa> mesas;
	private ArrayList<Votante> votantes;
	private int[] franjasHorarias;
	
	private static String ERROR_VOTANTE_NO_REGISTRADO = "Votante no registrado";
	private static String ERROR_PRESIDENTE_NO_REGISTRADO = "Presidente no registrado";

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
		Votante votante = new Votante(dni, nombre, edad, tieneEnfermedad, esTrabajador);
				
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
	public int agregarMesa(String tipoMesa, int dni) throws Exception
	{
		
		int numeroMesa = mesas.size() + 1;
		Votante votante = null;
		
		try {
			
			votante = obtenerVotante(dni);
		}
		
		catch (Exception e) {

			if(e.getMessage().equals(ERROR_VOTANTE_NO_REGISTRADO))
				throw new Exception(ERROR_PRESIDENTE_NO_REGISTRADO);
		}		
		
		Presidente presidente = new Presidente(dni, votante.getNombre());
		
		Mesa mesa;
		
		switch (tipoMesa) {
			
			case "Enf_Preex":
				
				mesa = new MesaEnfPreex(presidente, numeroMesa);
				break;
	
			case "Mayor65":
				
				mesa = new MesaMayores(presidente, numeroMesa);
				break;
				
			case "Trabajador":
				
				mesa = new MesaTrabajadores(presidente, numeroMesa);
				break;
				
				
			default:

				mesa = new MesaGeneral(presidente, numeroMesa);
				break;
		}

		// Asigna el turno al presidente
		votante.asignarTurno(
			new Tupla<Integer,Integer>(
				mesa.getNumero(), mesa.getFranjasHorarias()[0]
			)
		);
		mesa.registrarVotante();
		
		mesas.add(mesa);
		return numeroMesa;
	}

	private Votante obtenerVotante(int dni) throws Exception {
		
		
		Iterator<Votante> itVot = votantes.iterator();
		Boolean encontroVotante = false;
		Votante votante = null;
		
		while(itVot.hasNext() && !encontroVotante ) {
			
			votante = itVot.next();
			encontroVotante = votante.getDni() == dni; 
		}
		
		if(encontroVotante) {
			return votante;
		}
		
		else throw new Exception(ERROR_VOTANTE_NO_REGISTRADO);
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
		
		
		Votante votante;
		Tupla<Integer, Integer> turno;

		
		// Valida votante
		try {
			
			votante = obtenerVotante(dni);
		} 
		
		catch (Exception e) {
			
			throw new Exception(ERROR_VOTANTE_NO_REGISTRADO);
		}

		
		// Tiene turno
		if(votante.tieneTurnoAsignado()) {

			turno = votante.getTurnoAsignado(); 
		}

		else {

			turno = obtenerTurno(votante);
			
			if(turno != null)
				votante.asignarTurno(turno);
			
		}
		
		return turno;
	}
	
	private Tupla<Integer, Integer> obtenerTurno(Votante votante) {
		
		Tupla<Integer, Integer> turno = null;
		
		
		Iterator<Mesa> itMesa = mesas.iterator();
		Mesa mesa = null;
		int horarioDisponible = 0;
		
		while(itMesa.hasNext() && horarioDisponible == 0) {
			
			mesa = itMesa.next();
			
			if(mesa.aceptaVotante(votante))
				horarioDisponible = mesa.obtenerHorarioDisponible();
		}
		
		if(horarioDisponible > 0) {
			turno = new Tupla<Integer, Integer>(mesa.getNumero(), horarioDisponible); 
		}
		
		return turno;
	}

	public int asignarTurno() throws Exception 
	{
	
		int cantidadTurnosAsignados = 0;
		
		for(Votante votante : votantes) {
			
			if( (!votante.tieneTurnoAsignado())
				&& (asignarTurno(votante.getDni()) != null)) {
				
				cantidadTurnosAsignados++;
			}
			
			System.out.println(cantidadTurnosAsignados++);
			
		}
		
		return cantidadTurnosAsignados;
	}
	
	public boolean votar(int dni) 
	{
		//Retorna si ya voto o no, verificando con el dni del votante, o presidente.
		return false;
	};
	
	public int votantesConTurno(String tipoMesa) {return 1;};
	
	public Tupla<Integer, Integer> consultaTurno(int dni) throws Exception
	{
		return obtenerVotante(dni).getTurnoAsignado();
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
