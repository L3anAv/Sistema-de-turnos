package sistVot;

import java.util.*;

public class PruebasDelSistema {

	public static void main(String[] args) {
		
		try {
			SistemaDeVotacion sisv = new SistemaDeVotacion("Nombre");
			
			sisv.registrarVotante("2340", "Matias", 35, false, false,false);
			sisv.registrarVotante("2341", "Marcos", 25, false, false,false);
			sisv.registrarVotante("2342", "Pablo", 25, false, false,true);
			
			ArrayList<Persona> persona = sisv.getPersonasVotacion();
			
			System.out.println(persona.get(0).equals(persona.get(2)));
			
			Iterator<Persona> p = persona.iterator();
			while(p.hasNext()) {
				System.out.println(p.next().getNombre());
			}
			

			
		}catch(Exception e){
			System.out.println("Sucedio un error: " + e);
		}
		

	}

}
