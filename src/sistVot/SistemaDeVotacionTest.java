package sistVot;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SistemaDeVotacionTest {
	
	/*
	 *
	 * @param nombreSistema
	 *
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
			
			e.printStackTrace();
		}
		
		assertEquals(
			"Se debio registrar un votante", 
			++cantidadVotantes, 
			sisv.getVotantes().size()
		);
	}
	
	
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
			e.printStackTrace();
		}
		
		assertEquals(
			"Se debio registrar una mesa", 
			++cantidadMesas, 
			sisv.getMesas().size()
		);
	}
	
	
	
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
	
	
	//No me funca por alguna razon en particular.
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
		
		// Bloque para ver printeado la cantidad de turnosAsignados a los que no tenian turnos.
		// Dentro de los votantes.
		System.out.println("=======================================");
		System.out.print("La cantidad de turnos asignados es: ");
		System.out.println(turnosAsignados);
		System.out.println("=======================================");
		System.out.println("\n");
		
		//Verficacion de turnos.
		assertEquals("Debió asignar cuatro turnos", 11, turnosAsignados);
	}
	
	@Test
	public void votanteEfectuaVoto_debeDevolverTrueAsistioAVotar() {
		
		try {
			//Nuevo sistema
			sisv = new SistemaDeVotacion("Sistema Test");
			
			//Agregar Votantes
			int nuevoDocumento = dni+1;
			sisv.registrarVotante(nuevoDocumento, "Votante1", 40, true, false);
			
			//La lista de votantes del sistema.
			ArrayList<Votante> votantes = new ArrayList<Votante>();
			votantes = sisv.getVotantes();
			
			// ---Resultado
			boolean resultado;
			
			//Verificacion del voto no hecho.
			resultado = votantes.get(0).getAsistioAVotar();
			//System.out.println(resultado);
			
			assertEquals("El votante no efectuo el voto", resultado, false);
			
			//Lo hago votar
			sisv.votar(nuevoDocumento);			
			
			//Verifico que si haya votado.
			resultado = votantes.get(0).getAsistioAVotar();
			assertEquals("El votante si efectuo el voto", resultado, true);
			
		} catch (Exception e) {
			
		}

	}
	
	//Funcionando.
	@Test
	public void DarcantidadDeTurnosAsignadosPorTipoDeMesa() {
		
		try {
			
			sisv = new SistemaDeVotacion("Sistema Test");

			// SetUp Mesa y Presidente
			sisv.registrarVotante(dni, "Mr. President", 40, false, true);
			sisv.agregarMesa("Enf_Preex", dni);

			//Agregar Votante Para Mesa Enfermedad Prenexa.
			sisv.registrarVotante(++dni, "Un Votante con Enf_Preex", 40, true, false);
			sisv.registrarVotante(++dni, "Otro Votante con Enf_Preex", 47, true, false);
			
			int ResultadoturnosDadosPorTipoDeMesa;
			
			//Asignar turno a los votantes.
			int cantidadDeTurnoAsignados = sisv.asignarTurno();
			
			//Prints para saber la cantidad de turno Asignados.
			System.out.println("==================================================================");
			System.out.print("La cantidad de turnos asignados para la mesa Enf_Preex es: ");
			System.out.println(cantidadDeTurnoAsignados + ".");
			System.out.println("==================================================================");
			
			// Comprobacion despues de usar  asignarTurnos ---Verificacion.
			// Cantidad de turnos esperada de asignacion, 3.
			ResultadoturnosDadosPorTipoDeMesa = sisv.votantesConTurno("Enf_Preex");
			assertEquals("El votante si efectuo el voto", ResultadoturnosDadosPorTipoDeMesa, 3);
			
		} 
		
		catch (Exception e) {

			System.out.println(e.getMessage());
			hasError = true;
		}
	}
	
	@Test
	public void asignadosAMesa_MapConClaveHorarioValorListaVotantes() {
		try {
			
			//Crear sistema de votacion.
			sisv = new SistemaDeVotacion("Sistema Test");
			
			// SetUp Mesa y Presidente
			sisv.registrarVotante(dni, "Mr. President", 40, false, true);
			sisv.agregarMesa("Enf_Preex", dni);
			
			//Lista de mesas
			ArrayList<Mesa> mesas = sisv.getMesas();
			
			//Unica mesa numDeMesa
			int numMesa = mesas.get(0).getNumero();
			
			//Agregar Votante Para Mesa Enfermedad Prenexa.
			sisv.registrarVotante(30123458, "Un Votante con Enf_Preex", 40, true, false);
			sisv.registrarVotante(31123456, "Otro Votante con Enf_Preex", 47, true, false);
			
			//Asignar turno a votantes.
			sisv.asignarTurno();
			
			//Validacion de funcion asignadosAMesa
			Map<Integer,List<Integer>> asignadosAMesaPorHorario = sisv.asignadosAMesa(numMesa);
			
			List<Integer> lista;
			 for (Integer clave: asignadosAMesaPorHorario.keySet()) {
				 lista = asignadosAMesaPorHorario.get(clave);
				 System.out.println("Clave: " + clave + ", valor: " + lista);
		       }
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}




	/* Asigna turnos autom�ticamente a los votantes sin turno.
	* El sistema busca si hay alguna mesa y franja horaria factible en la que haya disponibilidad.
	* Devuelve la cantidad de turnos que pudo asignar.
	*/
	
	// public int asignarTurno();
	
	/* Consulta el turno de un votante dado su DNI. Devuelve Mesa y franja horaria.
	* - Si el DNI no pertenece a un votante genera una excepci�n.
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
		assertFalse("HasError debe iniciar en false", hasError);
		
		try {
			
			Tupla<Integer, Integer>	turnoPorMetodo = sisv.consultaTurno(dni);
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



