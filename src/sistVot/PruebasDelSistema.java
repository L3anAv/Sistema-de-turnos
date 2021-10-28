package sistVot;

import java.util.*;

public class PruebasDelSistema {

	public static void main(String[] args) {
		 int dni = 39490535;
		 int[] franjasHorarias = {8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
	        
	     HashMap<Integer, List<Integer>> mapaDeVotantesDeMesa = new HashMap<Integer, List<Integer>>();

		//Para llenar la lista.

		for(int i = 0; i < franjasHorarias.length; i++) {
			mapaDeVotantesDeMesa.put(franjasHorarias[i], null);
		}
		
		LinkedList<Integer> ListaParaFranja8 = new LinkedList<Integer>();
		ListaParaFranja8.add(10);
		mapaDeVotantesDeMesa.put(8, ListaParaFranja8);
		
		//Para printer los valores.
		List<Integer> lista;
		 for (Integer clave: mapaDeVotantesDeMesa.keySet()) {
			 lista = mapaDeVotantesDeMesa.get(clave);
			 System.out.println("Clave: " + clave + ", valor: " + lista);
	       }

	}

		
}
