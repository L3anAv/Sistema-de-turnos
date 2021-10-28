package sistVot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SistemaDeVotacionTest {


	/**
	 * 
		
		HECHOS: (solo lo basico)
		
		public SistemaDeTurnos(String nombreSistema); 
		
		public void registrarVotante(int dni, String nombre, int edad, boolean enfPrevia, boolean
		trabaja);
		
		public int agregarMesa(String tipoMesa, int dni);
		
		
		public Tupla<Integer, Integer> asignarTurno(int dni);
		
		
		PENDIENTES:
		
		
		

		public int asignarTurno();
		
		public boolean votar(int dni);

		public int votantesConTurno(String tipoMesa);
		
		public Tupla<Integer, Integer> consultaTurno(int dni);
		
		public Map<Integer,List< Integer>> asignadosAMesa(int numMesa);
		
		public List<Tupla<String, Integer>> sinTurnoSegunTipoMesa();
		
		
	 * 
	 * @param nombreSistema
	 */
	
	
	SistemaDeVotacion sisv;
	int dni = 30123456;
	boolean hasError = false;
	
	@Test
	public void inicializaConNombre() {
		
		boolean hasError = false;
		
		try {
			
			sisv = new SistemaDeVotacion("Sistema Test");
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			hasError = true;
		}
		
		assertFalse("Error en el constructor", hasError);
		
	}
	
	@Test
	public void fallaSinNombre() {
		
		try {
			
			sisv = new SistemaDeVotacion(null);
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			hasError = true;
		}
		
		assertTrue("No hubo error en el constructor", hasError);
		
	}
	
	// Registrar Votatante. ToDo: test excepcion edad < 16
	
	@Test
	public void registraUnVotante() throws Exception {
		
		sisv = new SistemaDeVotacion("Sistema Test");

		// Condicion pre test:
		int cantidadVotantes = sisv.getVotantes().size();
		
		try {
			
			// registrarVotante(int dni, String nombre, int edad, boolean enfPrevia, boolean trabaja
			sisv.registrarVotante(dni, "Votante Test", 40, false, true);
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(
			"Se debio registrar un votante", 
			++cantidadVotantes, 
			sisv.getVotantes().size()
		);
	}
	
	// Agrega Mesa
	
	/* Agregar una nueva mesa del tipo dado en el parámetro y asignar el presidente
	* de cada una, el cual deberá estar entre los votantes registrados y sin turno asignado.
	* - Devuelve el número de mesa creada.
	* si el president es un votante que no está registrado debe generar una excepción
	* si el tipo de mesa no es válido debe generar una excepción
	* Los tipos válidos son: “Enf_Preex”, “Mayor65”, “General” y “Trabajador”
	*/
	
	@Test
	public void agregaUnaMesaEnf_Preex_RetornaSuNumero() throws Exception {
		
		sisv = new SistemaDeVotacion("Sistema Test");

		// Condicion pre test:
		int cantidadMesas = sisv.getMesas().size();
		
		sisv.registrarVotante(dni, "Mr. President", 40, false, true);
		
		try {

			sisv.agregarMesa("Enf_Preex", dni);
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(
			"Se debio registrar una mesa", 
			++cantidadMesas, 
			sisv.getMesas().size()
		);
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
	
	
	@Test
	public void dadoUnDNI_debeAsignarUnTurnoAlVotante() throws Exception {
		
		sisv = new SistemaDeVotacion("Sistema Test");

		// SetUp Mesa y Presidente
		sisv.registrarVotante(dni, "Mr. President", 40, false, true);
		sisv.agregarMesa("Enf_Preex", dni);


		sisv.registrarVotante(++dni, "Un Votante con Enf_Preex", 40, true, false);

		Tupla<Integer, Integer> turno = null;
		
		try {

			turno = sisv.asignarTurno(dni);
		} 
		
		catch (Exception e) {

			System.out.println(e.getMessage());
			hasError = true;
		}
		
		
		assertFalse("Error al asignar turno", hasError);
		
		assertNotNull("Debe devolver un turno", turno);
		
	}
	
	
	
	
	
	
	/* Asigna turnos automáticamente a los votantes sin turno.
	* El sistema busca si hay alguna mesa y franja horaria factible en la que haya disponibilidad.
	* Devuelve la cantidad de turnos que pudo asignar.
	*/
	
	// public int asignarTurno();
	
	@Test
	public void hayVotantesSinTurno_MesasDisponibles_devuelveCantidadDeTurnosAsignados() throws Exception {

		sisv = new SistemaDeVotacion("Sistema Test");

		// SetUp Mesas y Presidentes
		sisv.registrarVotante(dni, "Pres1", 40, false, true);
		sisv.registrarVotante(++dni, "Pres2", 70, true, true);
		sisv.registrarVotante(++dni, "Pres3", 40, true, false);
		sisv.agregarMesa("Enf_Preex", dni);
		sisv.agregarMesa("Mayor65", --dni);
		sisv.agregarMesa("General", --dni);

		
		// 2 van a recibir turno por dni
		sisv.registrarVotante((dni += 100), "Votante con Enf_Preex1", 40, true, false);
		sisv.registrarVotante(++dni, "Votante con Enf_Preex2", 40, true, false);
		
		// 2 con enf
		sisv.registrarVotante((dni += 100), "Votante con Enf_Preex3", 40, true, false);
		sisv.registrarVotante(++dni, "Votante con Enf_Preex4", 40, true, false);
		
		//un mayor y un general
		sisv.registrarVotante(++dni, "Un Votante Mayor", 70, false, false);
		sisv.registrarVotante(++dni, "Votante General", 40, false, false);
		
		// Estos no deben ser asignados ya que no hay mesa
		sisv.registrarVotante(++dni, "Trabajadores", 40, true, true);
		sisv.registrarVotante(++dni, "Trabajadores", 40, false, true);
		
		// En total se esperan 4 asignaciones
		
		
		int turnosAsignados = 0;
		try {
			turnosAsignados = sisv.asignarTurno();
		}
		
		catch (Exception e) {

			System.out.println(e.getMessage());
		
		}
				
		
		assertEquals("Debió asignar cuatro turnos", 4, turnosAsignados);
	}
	
	/* Consulta el turno de un votante dado su DNI. Devuelve Mesa y franja horaria.
	* - Si el DNI no pertenece a un votante genera una excepción.
	* - Si el votante no tiene turno devuelve null.
	*/
	// public Tupla<Integer, Integer> consultaTurno(int dni);
	
	@Test
	public void recibeDNI_devuelveTurnoDeVotante() throws Exception {
		
		sisv = new SistemaDeVotacion("Sistema Test");

		// SetUp Mesa y Presidente
		sisv.registrarVotante(dni, "Mr. President", 40, false, true);
		sisv.agregarMesa("Enf_Preex", dni);


		sisv.registrarVotante(++dni, "Un Votante con Enf_Preex", 40, true, false);

		Tupla<Integer, Integer> turno = sisv.asignarTurno(dni);
		
		Tupla<Integer, Integer> turnoPorMetodo = null;
		
		try {
			
			turnoPorMetodo = sisv.consultaTurno(dni);
		}
		
		catch (Exception e) {
		
			System.out.println(e.getMessage());
		}
		
		
		assertEquals("Nro de mesa incorrecto", turno.getValor1(), turnoPorMetodo.getValor1());
		assertEquals("Franja horaria incorrecta", turno.getValor2(), turnoPorMetodo.getValor2());
	}
	
	@Test
	public void siElVotanteNoEstaRegistradoGeneraExcepcion() throws Exception {
		
		sisv = new SistemaDeVotacion("Sistema Test");

		Tupla<Integer, Integer> turnoPorMetodo = null;
		
		assertFalse("HasError debe iniciar en false", hasError);
		
		try {
			
			turnoPorMetodo = sisv.consultaTurno(dni);
		}
		
		catch (Exception e) {
		
			hasError = true;
		}
		
		assertTrue("Debio generar exception", hasError);
	}
	
	@Test
	public void siElVotanteNoTieneTurnoDevuelveNull() throws Exception {
		
		sisv = new SistemaDeVotacion("Sistema Test");

		// SetUp Mesa y Presidente
		sisv.registrarVotante(dni, "Mr. President", 40, false, true);
		sisv.agregarMesa("Enf_Preex", dni);

		sisv.registrarVotante(++dni, "Un Votante con Enf_Preex", 40, true, false);
		
		assertNull(sisv.consultaTurno(dni));
	}
	
	
	
}
