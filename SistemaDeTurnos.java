package sistVot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SistemaDeTurnos {

	//private List<Mesa> mesas;
	private Map<Integer, Votante> votantesPorDNI;
	private Map<Integer, Mesa> mesasPorNro;

	public SistemaDeTurnos(String string) {
		mesasPorNro = new HashMap<Integer, Mesa>(); 
		votantesPorDNI = new HashMap<Integer, Votante>(); 
	}

	public void registrarVotante(int dni, String nombre, int edad, boolean tieneEnfermedad, boolean esTrabajador) throws Exception
	{
		if(edad < 16) {
			throw new Exception("El votante no puede tener menos de 16 aÃ±os.");
		
		} else if(votantesPorDNI.containsKey(dni)) {
			throw new Exception("Votante ya registrado");
		
		} else {
			
			Votante votante = new Votante(dni, nombre, edad, tieneEnfermedad, esTrabajador);

			votantesPorDNI.put(dni, votante);
		}
	}

	/* Dado un número de mesa, devuelve una Map cuya clave es la franja horaria y
	* el valor es una lista con los DNI de los votantes asignados a esa franja.
	* Sin importar si se presentaron o no a votar.
	* - Si el número de mesa no es válido genera una excepción.
	* - Si no hay asignados devuelve null.
	*/
	public Map<Integer, List<Integer>> asignadosAMesa(Integer nroMesa) throws Exception {
		
		if(mesasPorNro.containsKey(nroMesa)) {
		
			Mesa mesa = mesasPorNro.get(nroMesa);
			
			if(mesa.tieneAsignados()) {
				
				return mesa.getDNIsAsignadosPorFranjaHoraria();

			}
			else
				return null;
		}
		
		else 
			throw new Exception("NUMERO DE MESA INVALIDO");

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
		
		int numeroMesa = mesasPorNro.size() + 1;
		Votante votante = null;
		
		votante = obtenerVotante(dni);
		
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
				
			case "General":
				
				mesa = new MesaGeneral(presidente, numeroMesa);
				break;
				
				
			default:

				throw new Exception("Tipo de Mesa no válido");
		}

		// Asigna el turno al presidente
		
		int horario = mesa.getFranjasHorarias()[0];
		votante.asignarTurno(
			new Tupla<Integer,Integer>(
				mesa.getNumero(), horario 
			)
		);
		mesa.registrarVotante(horario, dni);
		
		mesasPorNro.put(mesa.getNumero(), mesa);
		return numeroMesa;
	}

	public int asignarTurnos() throws Exception 
	{
		int cantidadTurnosAsignados = 0;
		
		for(Votante votante : votantesPorDNI.values()) {

			Boolean sinTurno = !votante.tieneTurnoAsignado();
			Boolean turnoAsignado = sinTurno && asignarTurno(votante.getDni()) != null;

			if(turnoAsignado)
				cantidadTurnosAsignados++;
		}
		
		return cantidadTurnosAsignados;
	}

	public Tupla<Integer, Integer> consultaTurno(Integer dni) {
		
		Votante votante = votantesPorDNI.get(dni); 
		return votante.getTurnoAsignado();
	}

	/*
	* Consultar la cantidad de votantes sin turno asignados a cada tipo de mesa.
	* Devuelve una Lista de Tuplas donde se vincula el tipo de mesa con la cantidad
	* de votantes sin turno que esperan ser asignados a ese tipo de mesa.
	* La lista no puede tener 2 elementos para el mismo tipo de mesa.
	*/

	public List<Tupla<String, Integer>> sinTurnoSegunTipoMesa() {

		int cantEnf_Preex = 0; 
		int cantMayor65 = 0; 
		int cantTrabajador = 0; 
		int cantGeneral = 0;
		
		for(Votante votante : votantesPorDNI.values()) {
			
			if(!votante.tieneTurnoAsignado()) {
				
				if(votante.esTrabajador()) {
					cantTrabajador++;
				}
				
				else if(votante.tieneEnfermedad()) {
					cantEnf_Preex++;
				}
				
				else if(votante.esMayor()) {
					cantMayor65++;
				}
				
				else
					cantGeneral++;
				
			}
		}

		List<Tupla<String, Integer>> sinTurnoSegunTipoMesa = new ArrayList<Tupla<String, Integer>>();  
		
		sinTurnoSegunTipoMesa.add(new Tupla<String, Integer>("Enf_Preex", cantEnf_Preex));
		sinTurnoSegunTipoMesa.add(new Tupla<String, Integer>("Mayor65", cantMayor65));
		sinTurnoSegunTipoMesa.add(new Tupla<String, Integer>("Trabajador", cantTrabajador));
		sinTurnoSegunTipoMesa.add(new Tupla<String, Integer>("General", cantGeneral));
		
		return sinTurnoSegunTipoMesa;
	}

	// Asigna un turno a un votante determinado. 
	public Tupla<Integer, Integer> asignarTurno(Integer dni) throws Exception {
		
		Votante votante;
		Tupla<Integer, Integer> turno;
		
		// Si el DNI no pertenece a un votante registrado debe generar una excepción.
		votante = obtenerVotante(dni);
		
		//Si el votante ya tiene turno asignado se devuelve el turno como: Número de
		//Mesa y Franja Horaria.
		if(votante.tieneTurnoAsignado()) {
			return votante.getTurnoAsignado(); 
		}
		
		// Si aún no tiene turno asignado se busca una franja horaria disponible en una
		// mesa del tipo correspondiente al votante y se devuelve el turno asignado, como
		// Número de Mesa y Franja Horaria
		else {

			
			turno = obtenerTurno(votante);
			
			if(turno != null) {
				
				votante.asignarTurno(turno);
				Mesa mesa = mesasPorNro.get(turno.getX());
				mesa.registrarVotante(turno.getY(), dni);
			}
			
		}
		
		return turno;
	}

	private Tupla<Integer, Integer> obtenerTurno(Votante votante) {
		
		Tupla<Integer, Integer> turno = null;
		
		
		Iterator<Mesa> itMesa = mesasPorNro.values().iterator();
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

	/* Hace efectivo el voto del votante determinado por su DNI.
	* Si el DNI no está registrado entre los votantes debe generar una excepción
	* Si ya había votado devuelve false.
	* Si no, efectúa el voto y devuelve true.
	*/

	private Votante obtenerVotante(int dni) throws Exception {
	
		if(votantesPorDNI.containsKey(dni)) {
			return votantesPorDNI.get(dni);
		}
		
		else
			throw new Exception("Votante no registrado");
	}
	
	/* Hace efectivo el voto del votante determinado por su DNI.
	 * 
	* Si el DNI no está registrado entre los votantes debe generar una excepción
	* Si ya había votado devuelve false.
	* Si no, efectúa el voto y devuelve true.
	*/
	public boolean votar(int dni) throws Exception {
		
		Votante votante = obtenerVotante(dni);
		
		boolean votoEfectuado = false;
		if(!votante.asistioAVotar()) {
			votante.asistioAVotar(true);
			votoEfectuado = true;
		}
		
		return votoEfectuado;
	}

}
