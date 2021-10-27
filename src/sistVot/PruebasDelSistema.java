package sistVot;

import java.util.*;

public class PruebasDelSistema {

	public static void main(String[] args) {
		
		try {
			SistemaDeVotacion sisv = new SistemaDeVotacion("Nombre");
			

			sisv.registrarVotante(2340, "Matias", 35, false, false);
			sisv.registrarVotante(2342, "Emiliano", 25, false, false);
			sisv.registrarVotante(2343, "Pablo", 25, false, false);
			sisv.registrarVotante(2345, "Juan",20, false, false);
			
			ArrayList<Votante> persona = sisv.getVotantes();
			Iterator<Votante> p = persona.iterator();
			
			//Recorrer la lista.
			while(p.hasNext()) {
				System.out.println(p.next().getNombre());
			}
			
			sisv.agregarMesa("Enf_Preex", 2345);
			
			ArrayList<Mesa> mesas = sisv.getMesas();
			Iterator<Mesa> m = mesas.iterator(); 
			
			//Recorrer la lista.
			while(m.hasNext()) {
				System.out.println(m.next().getNumero());
			}
			
		}catch(Exception e){
			System.out.println("Sucedio un error: " + e);
		}
		
		

	}

		
}
