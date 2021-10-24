package sistVot;

import static org.junit.Assert.*;

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
	
	
	@Test
	public void agregaUnaMesaEnf_Preex_RetornaSuNumero() {
		
		// Condicion pre test:
		int cantidadMesas = sisv.getMesas().size();
		
		try {
			
			sisv.registrarVotante(dni, "Mr. President", 40, false, true);

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
	
	

	@Test
	public void dadoUnDNI_debeAsignarUnTurnoAlVotante() {
		
		int dni = 30123456;
		
		try {
			
			sisv.registrarVotante(dni, "Votante Test", 40, false, true);
			
			Tupla<Integer, Integer> turno = sisv.asignarTurno(dni);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	
}
