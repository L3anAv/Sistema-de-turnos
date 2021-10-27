package sistVot;
import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class SistemaDeVotacionTest {

	static SistemaDeVotacion sisv;
	static int dni = 30123456;
	
	@Before
	public void setUp() throws Exception {
		
		sisv = new SistemaDeVotacion("Sistema Test");
		
			
	}
	// Registrar Votatante. ToDo: test excepcion edad < 16
	
	@Test
	public void registraUnVotante() {
		
		// Condicion pre test:
		int cantidadVotantes = sisv.getVotantes().size();
		
		try {
			
			sisv.registrarVotante (dni, "Votante Test", 40, false, true);
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
	
	// Agrega Mesa
	
	
	@Test
	public void agregaUnaMesaEnf_Preex_RetornaSuNumero() {
		
		// Condicion pre test:
		int cantidadMesas = sisv.getMesas().size();
		
		try {
			
			sisv.registrarVotante(dni, "Mr. President", 40, false, true);

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
	public void votar_VotarPorElVotante_VotanteTengoAsistioAVotarEnTrue() {
		
		// Condicion pre test:
			// -- Registro un votante.
			Votante votante; 
			boolean voto = false;
			
			try {	
					//Votante
					sisv.registrarVotante (dni, "Votante Test", 40, false, true);
					
					sisv.registrarVotante(++dni, "Mr. President", 40, false, true);
					sisv.agregarMesa("Enf_Preex", dni);
					ArrayList<Mesa> mesas = sisv.getMesas();
					
					votante = sisv.obtenerVotante(dni);
					Turno turnoNuevo = new Turno(mesas.get(0), 9);
					votante.asignarTurno(turnoNuevo);
					
					voto = votante.asistioAVotar();
					assertEquals(false, voto);
					
					sisv.votar(dni);
					
					//Si fue o no a votar.
					voto = votante.asistioAVotar();
					
			} catch (Exception e) {
					e.printStackTrace();
			}
			
			/* Hace efectivo el voto del votante determinado por su DNI. 
			 *  Si el DNI no está registrado entre los votantes debe generar una excepción. 
			 *  Si ya había votado devuelve false. 
			 *  Si no, efectúa el voto y devuelve true. 
			 */ 
			
			assertEquals(true, voto);
			
}



	
//	@Test
//	public void dadoUnDNI_debeAsignarUnTurnoAlVotante() {
//		
//		int dni = 30123456;
//		
//		try {
//			
//			sisv.registrarVotante(dni, "Votante Test", 40, false, true);
//			
//			Tupla<Integer, Integer> turno = sisv.asignarTurno(dni);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
	
	

