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
		
		
		
		
		PENDIENTES:
		
		
		
		public Tupla<Integer, Integer> asignarTurno(int dni);

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
	public void someTest() {
		
		System.out.println(9 % 10);
		System.out.println(10 % 10);
		System.out.println(11 % 10);
		
		System.out.println(9 / 10);
		System.out.println(10 / 10);
		System.out.println(11 / 10);
		
	}
	
	
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
		
		System.out.println(sisv.getMesas());
		
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
	
	
	
	
	

	
}
