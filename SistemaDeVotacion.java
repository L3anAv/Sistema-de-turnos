package sistVot;
import java.util.*;


public class SistemaDeVotacion {
	
	private String nombreDelSistema;
	private ArrayList<Mesa> mesas;
	private ArrayList<Votante> votantes;
	private int[] franjasHorarias;
	
	private static String ERROR_VOTANTE_NO_REGISTRADO = "Votante no registrado";
	private static String ERROR_PRESIDENTE_NO_REGISTRADO = "Presidente no registrado";
	private static String ERROR_TIPO_MESA_INEXISTENTE = "Tipo de Mesa Inexistente.";
	private static String ERROR_MESA_INEXISTENTE = "Mesa inexistente.";


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

//  --------------------------- Metodos de la clase ---------------------------

//Registra un votante en el sistema.
	public void registrarVotante(int dni, String nombre, int edad, boolean tieneEnfermedad, boolean esTrabajador) throws Exception{
	
		//Exepcion para votantes menores de 16 años.
		if(edad < 16)
			throw new Exception("El votante no puede tener menos de 16 años.");
		
		//Creo el votante con los datos dados.
		 Votante votante = new Votante(dni, nombre, edad, tieneEnfermedad, esTrabajador);
		
		 //Antes de agregar verifica que el votante no exista.
		if(!existePersona(votante))
			votantes.add(votante);
		else
			throw new Exception("No pueden existir dos votantes con el mismo DNI.");	
}

// Metodo que agrega una Nueva Mesa del tipo que se solicita.
	public int agregarMesa(String tipoMesa, int dni) throws Exception{
		
		int numeroMesa = mesas.size() + 1;
		Votante votante = null;
		
		try{
			
			votante = obtenerVotante(dni);
			
}		catch (Exception e) 
{
			if(e.getMessage().equals(ERROR_VOTANTE_NO_REGISTRADO))
				throw new Exception(ERROR_PRESIDENTE_NO_REGISTRADO);
}		
		
		Presidente presidente = new Presidente(dni, votante.getNombre());
		Mesa mesa;
		
		switch (tipoMesa){
		
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
		
		//Agrega la nueva mesa creada a la lista de mesas.
		mesas.add(mesa);
		
		return numeroMesa;
}

//Metodo que asigna turnos a votantes sin turno (segun su DNI), y retorna el turno asignado. 
	public Tupla<Integer, Integer> asignarTurno(int dni) throws Exception{

		Votante votante;
		Tupla<Integer, Integer> turno;

		
		// Valida que el votante exista.
		try{
			
		votante = obtenerVotante(dni);
} 
		catch (Exception e) {
			throw new Exception(ERROR_VOTANTE_NO_REGISTRADO);
}

		// Verifico si tiene turno asignado.
		if(votante.tieneTurnoAsignado())
			turno = votante.getTurnoAsignado(); 
		else 
			turno = obtenerTurno(votante);
		
		
			if(turno != null)
				votante.asignarTurno(turno);
		
		return turno;
}
	
//Metodo que asigna turnos automáticamente a los votantes sin turno.
	public int asignarTurno() throws Exception{
	
		int cantidadTurnosAsignados = 0;
		
		for(Votante votante : votantes) {
			
			if( (votante.tieneTurnoAsignado() == false) 
				 && (asignarTurno(votante.getDni()) != null)) {
				
				cantidadTurnosAsignados++;
			}
			//System.out.println(cantidadTurnosAsignados++);
			
		}
		
		return cantidadTurnosAsignados;
}

//Metodo que hace votar a un votante del cual conocemos el DNI.
	public boolean votar(int dni) throws Exception{
		
		boolean asistioAVotar = false;
		try{
			
		//Obtengo el votante con la funcion auxiliar.
		Votante votante = obtenerVotante(dni);
		
		if(votante.getAsistioAVotar() == false){
			votante.SetAsistioAVotar(true);
			asistioAVotar = true;
		}		
}       
		
		catch (Exception e) {
			throw new Exception(ERROR_VOTANTE_NO_REGISTRADO);
}
		
		return asistioAVotar;
}

//Retorna la cantidad de votantes que tienen turnos del tipo de mesa.
	public int votantesConTurno(String tipoMesa) throws Exception{
		
		//Exepcion de si la mesa no es de algun tipo existente.
		if(tipoMesa != "Enf_Preex" && tipoMesa !="Mayor65" && tipoMesa !="Trabajador" && tipoMesa != "General")
			throw new Exception(ERROR_TIPO_MESA_INEXISTENTE);
		
		//Cantidad de votantes del tipo de mesa.
		int CantidadDeVotantes = 0;
		
		//Obtener todos los numDeMesa, del tipo de mesa solicitado.
		LinkedList<Integer> numerosDeMesaDelTipo = new LinkedList<Integer>();
		numerosDeMesaDelTipo = obtenerNumDeMesasDelTipoSolicitado(tipoMesa);
		
		//Recorrer a los votantes.
		Iterator<Votante> itVotante = votantes.iterator();
		while(itVotante.hasNext()) {
			
			//Para evaluar el numDeMesa asignado en el turno del votante.
			Votante votanteEvaluar = itVotante.next();
			int turnoNumDeMesa = votanteEvaluar.getTurnoAsignado().getValor1();
			
			// Verificar si en la lista de numeroDeMesaDelTipo, existe el numDeMesa asignado del votante evaluado.
			// Si es asi, +1 por que tiene turno asignado a ese tipo de mesa.
			if(numerosDeMesaDelTipo.contains(turnoNumDeMesa))
				CantidadDeVotantes++;
		}
		
		
		return CantidadDeVotantes;
}

	/* Dado un número de mesa, devuelve una Map cuya clave es la franja horaria y 
	 *  el valor es una lista con los DNI de los votantes asignados a esa franja.   
	 *  Sin importar si se presentaron o no a votar. 
	 *  - Si el número de mesa no es válido genera una excepción. 
	 *  - Si no hay asignados devuelve null. 
	 */ 
	
	public Map<Integer,List<Integer>> asignadosAMesa(int numMesa) throws Exception{
		
			//Verifico que el numMesa exista dentro de las mesas creadas.
			if(!existeMesa(numMesa))
				throw new Exception(ERROR_MESA_INEXISTENTE);
			
		//Franja Horaria -- Lista de dni de votantes que votan en esa franja horaria.
		HashMap<Integer, List<Integer>> asignadosAMesaPorHorario = new HashMap<Integer, List<Integer>>();
		
		//Votantes que tienen asignada esa Mesa en el Turno.
		LinkedList<Votante> votantesDeMesa = new LinkedList <Votante>();
		 
		Tupla<Integer, Integer> turno;
		Iterator<Votante> itVotante = votantes.iterator();
		// Recorro lista de votantes, y agrego a la otra lista de votantesDeMesa
		// que tienen turno en esa mesa.
		while(itVotante.hasNext()) {
			Votante votante = itVotante.next();
			turno = votante.getTurnoAsignado();
			
			if(turno.getValor1() == numMesa) {
				votantesDeMesa.add(votante);
			}
		}
		
		//Rellenar el mapa.
		asignadosAMesaPorHorario = completarMapaVotantesPorHorario(asignadosAMesaPorHorario, votantesDeMesa);
		
		return asignadosAMesaPorHorario;
	}; 
	
	public Tupla<Integer, Integer> consultaTurno(int dni) throws Exception{
		return obtenerVotante(dni).getTurnoAsignado();
	}; 

// --------------  Metodos Auxiliares de Clase -----------------
	
	
// Metodo que obtiene un votante apartir del DNI.
	private Votante obtenerVotante(int dni) throws Exception {
		
		Boolean encontroVotante = false;
		Votante votante = null;
		Iterator<Votante> itVot = votantes.iterator();

		while(itVot.hasNext() && !encontroVotante){
			
			votante = itVot.next();
			encontroVotante = votante.getDni() == dni;
}
		
		if(encontroVotante) 
			return votante;
		
		else throw new Exception(ERROR_VOTANTE_NO_REGISTRADO);
}
	
	
//Metodo que devuelve si existe una persona dentro del sistema.
		private boolean existePersona(Persona personaAEvaluar){
			
			boolean existe = false;
			
				for (Persona persona : votantes)
					existe = existe || personaAEvaluar.equals(persona); 		
				
			return existe;
}

//Metodo que obtiene un turno para un votante.
	private Tupla<Integer, Integer> obtenerTurno(Votante votante) throws Exception{
			
			Tupla<Integer, Integer> turno = null;
			Iterator<Mesa> itMesa = mesas.iterator();
			Mesa mesa = null;
			int horarioDisponible = 0;
			
//			System.out.println("OBTENER TURNO");
			
			while(itMesa.hasNext() && horarioDisponible == 0) {
				
				mesa = itMesa.next();
//				System.out.println(mesa);
//				System.out.println(mesa.obtenerHorarioDisponible());
				
				if(mesa.aceptaVotante(votante))
					horarioDisponible = mesa.obtenerHorarioDisponible();
	}
			
			if(horarioDisponible >= 0)
				turno = new Tupla<Integer, Integer>(mesa.getNumero(), horarioDisponible); 
			else
				throw new Exception("No hay horarios disponibles para asignar al votante.");
			
			return turno;
		}


//Metodo que obtiene los numDeMesa del tipoDeMesa pasado por argumento.
	private LinkedList<Integer> obtenerNumDeMesasDelTipoSolicitado(String tipoDeMesa){
		
		//Para rellenar con numDeMesa.
		LinkedList<Integer> listaDeNum = new LinkedList<Integer>();
		
		Iterator<Mesa> itMesa = mesas.iterator();
			switch (tipoDeMesa){	
		
					case "Enf_Preex":
						
						while(itMesa.hasNext()) {
							Mesa mesaEvaluar = itMesa.next();
							if(mesaEvaluar instanceof MesaEnfPreex) {
								listaDeNum.add(mesaEvaluar.getNumero());
							}
						}
						break;
						
					case "Mayor65":
						
						while(itMesa.hasNext()) {
							Mesa mesaEvaluar = itMesa.next();
							if(mesaEvaluar instanceof MesaMayores) {
								listaDeNum.add(mesaEvaluar.getNumero());
							}
						}
						break;
						
					case "Trabajador":
						
						while(itMesa.hasNext()) {
							Mesa mesaEvaluar = itMesa.next();
							if(mesaEvaluar instanceof MesaTrabajadores) {
								listaDeNum.add(mesaEvaluar.getNumero());
							}
						}
						
						break;
						
					case "General":
						
						
						while(itMesa.hasNext()) {
							Mesa mesaEvaluar = itMesa.next();
							if(mesaEvaluar instanceof MesaGeneral) {
								listaDeNum.add(mesaEvaluar.getNumero());
							}
						}
						
							break;
						}
		
		
		return listaDeNum;
	}
	
//Metodo que comprueba si existe o no una mesa Por su numDeMesa
	
	private boolean existeMesa(int numMesa) {
		
		boolean existeMesa = false;	
		
		Iterator<Mesa> itMesa = mesas.iterator();
		
		while(itMesa.hasNext()) {
			Mesa mesaEvaluar = itMesa.next();
			if(mesaEvaluar.getNumero() == numMesa)
				return existeMesa = true;
		}
		
		return existeMesa;
	}
	
//	Metodo que rellena el map con las listar correspondientes segundo el horario 
//	Que tiene el votante.
	private HashMap<Integer, List<Integer>> completarMapaVotantesPorHorario(HashMap<Integer, List<Integer>> mapa, LinkedList<Votante> votantesDeMesa){
		
		//Relleno el mapa con las Franjas Horarias Disponibles.
		for(int i = 0; i < franjasHorarias.length; i++) {
			mapa.put(franjasHorarias[i], null);
		}
		
		//	Recorro la lista de votantes y me fijo cual es su franja horaria
		//  y la agrego al mapa.
		Iterator<Votante> itVot = votantesDeMesa.iterator();
		
		//16, 17
		while(itVot.hasNext()){
			
			//Busco el horario del votante.
			Votante votanteEvaluado = itVot.next();
			int horarioDelVotante = votanteEvaluado.getTurnoAsignado().getValor2();
			
			switch (horarioDelVotante) {		
			
			case 8:
				LinkedList<Integer> ListaParaFranja8 = new LinkedList<Integer>();
				ListaParaFranja8.add(votanteEvaluado.getDni());
				mapa.put(8, ListaParaFranja8);
				break;
			
			case 9:
				LinkedList<Integer> ListaParaFranja9 = new LinkedList<Integer>();
				ListaParaFranja9.add(votanteEvaluado.getDni());
				mapa.put(9, ListaParaFranja9);
				break;
			
			case 10:
				LinkedList<Integer> ListaParaFranja10 = new LinkedList<Integer>();
				ListaParaFranja10.add(votanteEvaluado.getDni());
				mapa.put(10, ListaParaFranja10);
				break;
			
			case 11:
				LinkedList<Integer> ListaParaFranja11 = new LinkedList<Integer>();
				ListaParaFranja11.add(votanteEvaluado.getDni());
				mapa.put(11, ListaParaFranja11);
				break;
			
			case 12:
				LinkedList<Integer> ListaParaFranja12 = new LinkedList<Integer>();
				ListaParaFranja12.add(votanteEvaluado.getDni());
				mapa.put(12, ListaParaFranja12);
				break;
			
			case 13:
				LinkedList<Integer> ListaParaFranja13 = new LinkedList<Integer>();
				ListaParaFranja13.add(votanteEvaluado.getDni());
				mapa.put(13, ListaParaFranja13);
				break;
			
			case 14:
				LinkedList<Integer> ListaParaFranja14 = new LinkedList<Integer>();
				ListaParaFranja14.add(votanteEvaluado.getDni());
				mapa.put(14, ListaParaFranja14);
				break;
				
			case 15:
				LinkedList<Integer> ListaParaFranja15 = new LinkedList<Integer>();
				ListaParaFranja15.add(votanteEvaluado.getDni());
				mapa.put(15, ListaParaFranja15);
				break;
			
			case 16:
				LinkedList<Integer> ListaParaFranja16 = new LinkedList<Integer>();
				ListaParaFranja16.add(votanteEvaluado.getDni());
				mapa.put(16, ListaParaFranja16);
				break;
				
			case 17:
				LinkedList<Integer> ListaParaFranja17 = new LinkedList<Integer>();
				ListaParaFranja17.add(votanteEvaluado.getDni());
				mapa.put(17, ListaParaFranja17);
				break;
				
			}
}
		return mapa;
		
	}
	
	
	

//------------------------------------------------------------------------
	
					// =+=+=+= DESCARTADOS =+=+=+=
	/*
	 * 
	 *  =+=+=+= Metodos para asignar Turno basados en el TAD Turno.=+=+=+= 
	 *  
	*/
	

//------------------------------------------------------------------------
	
//	public Tupla<Integer, Integer> asignarTurno(int dni){
//
//		Tupla<Integer, Integer> turnoDelVotante = null;
//		try{	
//			
//			//Variables Necesarias.
//			Mesa mesaTurno = null;
//			int horarioAsignado = -1;
//			Turno nuevoTurno = null;
//			Integer horarioAsig = 0;
//			Integer numDeMesaAsig = 0;
//			
//			//Busco al votante por DNI.
//			Votante votante = obtenerVotante(dni);
//			
//			// SI NO TIENE TURNO ASIGNADO ----
//				// -- Verifico que tenga no tenga turno asignado.
//			if(votante.getTurno() == null) {
//				String buscarCupoMesa = mesaCorrespondiente(votante);
//				
//				// -- Busco mesa del tipo correspondiente que tiene cupo para el votante.
//				// 		-- Y asigno esa mesa, como la mesa para el turno.
//				if(buscarCupoMesa != null) {
//					mesaTurno = buscarMesaDisponible(buscarCupoMesa);
//				}
//				
//				// -- Buscar Franja Horaria Disponible en la mesa.
//				if(mesaTurno != null) {
//					horarioAsignado = buscarFranjaDisponible(mesaTurno);
//				}
//				
//				// -- asigno el turno y horario al votante.
//				if(mesaTurno != null && horarioAsignado != -1)	
//					nuevoTurno = new Turno(mesaTurno, horarioAsignado);
//				
//						// -- Le asigno el turno al votante.
//					votante.asignarTurno(nuevoTurno);
//					
//				// -- Creo la tupla para enviar la informacion solicitada del turno.
//					// -- Se la deberia de pedir al votante. (Creo)
//				if(nuevoTurno != null)
//					horarioAsig = nuevoTurno.getHorarioAsginado();
//					numDeMesaAsig = nuevoTurno.getMesaAsginado().getNumero();
//					turnoDelVotante = new Tupla<Integer, Integer>(horarioAsig, numDeMesaAsig);
//				
//				// -- Retorno el turno creado para el votante.
//				
//				
//			}else {
//				// -- Aca tiene turno.
//				//		-- Entonces busco el turno del votante.
//				Turno turnoVotante = votante.getTurno();
//				
//				// -- Busco el horario y el numero de mesa correspondiente.
//				horarioAsig = turnoVotante.getHorarioAsginado();
//				numDeMesaAsig = turnoVotante.getMesaAsginado().getNumero();
//				
//				// -- Asigno eso la tupla con la info necesaria.
//				turnoDelVotante = new Tupla<Integer, Integer>(horarioAsig, numDeMesaAsig);
//				
//				// --Retorno el turno del votante.
//			}
//			// -- Aca termina el if que evalua si tiene turno o no el votante, que en tal caso de no tener.
//			// 		-- Asigna un nuevo turno.
//		
//} 				
//		catch (Exception e) 
//{
//			e.printStackTrace();
//}
//		
//		return turnoDelVotante;
//}
	
//------------------------------------------------------------------------
	
// Metodo que determina al tipo de mesa a la cual pertenece un votante.
// Ver asignacion de mesa cuando tiene enfermedad y es mayor.
		
//		private String mesaCorrespondiente(Votante votante) {
//			
//			String tipoDeMesa;
//			
//			//evaluo los atributos del votante.
//			if(votante.isEsTrabajador())
//				tipoDeMesa = "Trabajador";
//			else if(votante.isTieneEnfermedad())
//				tipoDeMesa = "Enf_Preex";
//			else if(votante.getEdad() >= 65)
//				tipoDeMesa = "Mayor65";
//			else if(votante.isTieneEnfermedad() && votante.getEdad() >= 65)
//				tipoDeMesa = "Mayor65";
//			else
//				tipoDeMesa = "General";
//			
//			return tipoDeMesa;
//}

//------------------------------------------------------------------------
	
//// Metodo que retorna una mesa con cupo, según la mesa que corresponda para el votante,
//// para asignarlo al turno.
//		private Mesa buscarMesaDisponible(String tipoDeMesa) {
//			
//			Mesa mesaParaVotante = null;
//			Iterator<Mesa> itMesa = mesas.iterator();
//			switch (tipoDeMesa){	
//			
//						case "Enf_Preex":
//							
//							while(itMesa.hasNext()) {
//								Mesa mesaEvaluar = itMesa.next();
//								if(mesaEvaluar instanceof MesaEnfPreex && mesaEvaluar.tieneCupo()) {
//									mesaParaVotante = mesaEvaluar;
//									return mesaParaVotante;
//								}
//							}
//							break;
//							
//						case "Mayor65":
//							
//							while(itMesa.hasNext()) {
//								Mesa mesaEvaluar = itMesa.next();
//								if(mesaEvaluar instanceof MesaMayores && mesaEvaluar.tieneCupo()) {
//									mesaParaVotante = mesaEvaluar;
//									return mesaParaVotante;
//								}
//							}
//							break;
//							
//						case "Trabajador":
//							
//							while(itMesa.hasNext()) {
//								
//								Mesa mesaEvaluar = itMesa.next();
//								if(mesaEvaluar instanceof MesaTrabajadores) {
//									mesaParaVotante = mesaEvaluar;
//									return mesaParaVotante;
//								}
//							}
//							
//							break;
//							
//						case "General":
//							
//							
//							while(itMesa.hasNext()) {
//								
//								Mesa mesaEvaluar = itMesa.next();
//								if(mesaEvaluar instanceof MesaTrabajadores && mesaEvaluar.tieneCupo()) {
//									mesaParaVotante = mesaEvaluar;
//									return mesaParaVotante;
//								}
//							}
//							
//								break;
//							}
//			
//			return mesaParaVotante;
//}
	
//------------------------------------------------------------------------
	
// Metodo que asigna la franjaHoraria para el turno, segun una mesa dada para los votantes.
//	private int buscarFranjaDisponible(Mesa mesaParaBuscar) {
//		
//		int franjaAsignada;
//		
//		if(mesaParaBuscar.tieneCupo()) {
//			franjaAsignada = mesaParaBuscar.darFranjaHorariaDisponible();
//			return franjaAsignada;
//		}
//		
//		return 0;
//	}

//------------------------------------------------------------------------

// Getters and Setters ---------------------------
	
	public ArrayList<Mesa> getMesas() 
{
		return mesas;
}
	
	public String getNombre() 
{
		return nombreDelSistema;
}
	
	public ArrayList<Votante> getVotantes() 
{
		return this.votantes;
}
	
	

}
