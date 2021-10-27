package sistVot;
import java.util.*;


public class SistemaDeVotacion {
	
	private String nombreDelSistema;
	private ArrayList<Mesa> mesas;
	
	//Aca van también presidentes tiene que ser de Personas.
	private ArrayList<Votante> votantes;
	private int[] franjasHorarias = {8, 9, 10, 11, 12, 13, 14, 15, 16, 17};

// Constructor  ---------------------------------
	
	public SistemaDeVotacion(String nombreDelSistema) throws Exception 
{
		//EXCEPTION
		if(nombreDelSistema == null)
			throw new Exception("El nombre del sistema de votacion no puede ser nulo.");
		
		this.nombreDelSistema = nombreDelSistema;
		votantes = new ArrayList<>();
		mesas = new ArrayList<>();
		//franjasHorarias = new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16, 17}; 
}

//  --------------------------- Metodos de la clase ---------------------------


	public void registrarVotante(int dni, String nombre, int edad, boolean tieneEnfermedad, boolean esTrabajador) throws Exception{
	
		//Exepcion para votantes menores de 16 años.
		if(edad < 16)
			throw new Exception("El votante no puede tener menos de 16 años.");
		
		//Creo un nuevo votante.
		Votante votante = new Votante(dni, nombre, tieneEnfermedad, esTrabajador);
		
		//Antes de agregar verifica que el votante no exista.
		if(!existePersona(votante)) 
			votantes.add(votante);
		else
			throw new Exception("No pueden existir dos votantes con el mismo DNI.");
		
		
}

	public int agregarMesa(String tipoMesa, int dni) 
{
		//Creo el numero de la nueva mesa.
		int numeroMesa = mesas.size() + 1;
		
		//Para almacenar al votante obtenido por el DNI.
		Votante votante = null;
		
		//Obtengo el votante utilizando una funcion aux.
		try{	
			votante = obtenerVotante(dni);
} 
		catch (Exception e) 
{
			e.printStackTrace();
}
		//Utilizo el votante obtenido y lo asigno como presidete de mesa.
		Presidente presidente = new Presidente(dni, votante.getNombre());
		
		//Creo la mesa, segun lo solicitado.
		Mesa mesa;
		switch (tipoMesa) 
{	
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
		//Agrego la nueva mesa a la lista de las mesas del sistema.
		mesas.add(mesa);		
		return numeroMesa;
}


	/* Asigna un turno a un votante determinado.
	* - Si el DNI no pertenece a un votante registrado debe generar una excepción.
	* - Si el votante ya tiene turno asignado se devuelve el turno como: Número de
	* Mesa y Franja Horaria.
	* - Si aún no tiene turno asignado se busca una franja horaria disponible en una
	* mesa del tipo correspondiente al votante y se devuelve el turno asignado, como
	* N�mero de Mesa y Franja Horaria.
	* - Si no hay mesas con horarios disponibles no modifica nada y devuelve null.
	* (Se supone que el turno permitirá conocer la mesa y la franja horaria asignada)
	*/

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
	

// FALTA TERMINAR.
	public int asignarTurno(){return 1;}

//Efectua el voto de un votante, del cual me pasan el DNI.
	public boolean votar(int dni){
		
		// --Retorno del voto.
		boolean votoEfectuado = false;
		try {
			//Busco al votante.
			Votante votante = obtenerVotante(dni);
			
			//Si no voto, efectua el voto.
			// -- Si ya voto, retorna false al final.
			if(!votante.asistioAVotar()) {
				votante.votar();
				votoEfectuado = true;
				return votoEfectuado;
			}
			
} 			catch (Exception e) 
{
			e.printStackTrace();
}		
		return votoEfectuado;
}


//Retorna la cantidad de votantes con turnos de una mesa.
	public int votantesConTurno(String tipoMesa){
		
		
		int CantidadDeVotantes = 0;
		Iterator<Votante> itVotante = votantes.iterator();
		
		//Recorrer los votantes y buscar en su turno si tienen mesa del tipo que buscan.
		//Si es asi, sumo 1 al contador de cantidad de votantes con esa mesa asignada.
		while(itVotante.hasNext()) {
			Votante votanteEvaluar = itVotante.next();
			
			switch (tipoMesa){
			
			case "Enf_Preex":
				
				Turno turnoVotante = votanteEvaluar.getTurno();
				Mesa MesaAsignada = turnoVotante.getMesaAsginado();
				
				if(MesaAsignada instanceof MesaEnfPreex) {
					CantidadDeVotantes++;
				}
				
				break;
				
			case "Mayor65":
				break;
				
			case "Trabajador":
				break;
				
			case "General":
					break;
}
			
		}
		
		
		return 1;
}
	
	public Tupla<Integer, Integer> consultaTurno(int dni)
{
		return null;
}

// --------------  Metodos Auxiliares Private. -----------------
	
	
// Metodo que obtiene un votante apartir del DNI.
		public Votante obtenerVotante(int dni) throws Exception {
			
			
			Iterator<Votante> itVot = votantes.iterator();
			Boolean encontroVotante = false;
			Votante votante = null;
			
			while(itVot.hasNext() || !encontroVotante ) 
{
				
				votante = itVot.next();
				encontroVotante = votante.getDni() == dni; 
}
			
			
			if(encontroVotante)
				return votante;
			else
				throw new Exception("El votante no esta REGISTRADO.");
}
		
//Metodo que devuelve si existe una persona dentro del sistema.
		private boolean existePersona(Persona personaAEvaluar){
						boolean existe = false;
						for (Persona persona : votantes)
							existe = existe || personaAEvaluar.equals(persona); 			
						return existe;
}
		
		
// Metodo que determina al tipo de mesa a la cual pertenece un votante.
// Ver asignacion de mesa cuando tiene enfermedad y es mayor.
		private String mesaCorrespondiente(Votante votante) {
			
			String tipoDeMesa;
			
			//evaluo los atributos del votante.
			if(votante.isEsTrabajador())
				tipoDeMesa = "Trabajador";
			else if(votante.isTieneEnfermedad())
				tipoDeMesa = "Enf_Preex";
			else if(votante.getEdad() >= 65)
				tipoDeMesa = "Mayor65";
			else if(votante.isTieneEnfermedad() && votante.getEdad() >= 65)
				tipoDeMesa = "Mayor65";
			else
				tipoDeMesa = "General";
			
			return tipoDeMesa;
}
		
// Metodo que retorna una mesa con cupo, según la mesa que corresponda para el votante,
// para asignarlo al turno.
		private Mesa buscarMesaDisponible(String tipoDeMesa) {
			
			Mesa mesaParaVotante = null;
			Iterator<Mesa> itMesa = mesas.iterator();
			switch (tipoDeMesa){	
			
						case "Enf_Preex":
							
							while(itMesa.hasNext()) {
								Mesa mesaEvaluar = itMesa.next();
								if(mesaEvaluar instanceof MesaEnfPreex && mesaEvaluar.tieneCupo()) {
									mesaParaVotante = mesaEvaluar;
									return mesaParaVotante;
								}
							}
							break;
							
						case "Mayor65":
							
							while(itMesa.hasNext()) {
								Mesa mesaEvaluar = itMesa.next();
								if(mesaEvaluar instanceof MesaMayores && mesaEvaluar.tieneCupo()) {
									mesaParaVotante = mesaEvaluar;
									return mesaParaVotante;
								}
							}
							break;
							
						case "Trabajador":
							
							while(itMesa.hasNext()) {
								
								Mesa mesaEvaluar = itMesa.next();
								if(mesaEvaluar instanceof MesaTrabajadores) {
									mesaParaVotante = mesaEvaluar;
									return mesaParaVotante;
								}
							}
							
							break;
							
						case "General":
							
							
							while(itMesa.hasNext()) {
								
								Mesa mesaEvaluar = itMesa.next();
								if(mesaEvaluar instanceof MesaTrabajadores && mesaEvaluar.tieneCupo()) {
									mesaParaVotante = mesaEvaluar;
									return mesaParaVotante;
								}
							}
							
								break;
							}
			
			return mesaParaVotante;
}
		
// Metodo que asigna la franjaHoraria para el turno, segun una mesa dada para los votantes.
	private int buscarFranjaDisponible(Mesa mesaParaBuscar) {
		
		int franjaAsignada;
		
		if(mesaParaBuscar.tieneCupo()) {
			franjaAsignada = mesaParaBuscar.darFranjaHorariaDisponible();
			return franjaAsignada;
		}
		
		return 0;
	}
	
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
