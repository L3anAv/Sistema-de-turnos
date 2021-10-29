package sistVot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class Mesa {
	
	protected int numero;
	private Presidente presidente;
	private int[] franjasHorarias;
	private int maximoVotantesPorFranjaHoraria;
	private int votantesRegistrados;
	private String tipoDeMesa;
	private Map<Integer, List<Integer>> DNIsAsignadosPorFranjaHoraria;

	// Constructor  ----------------------------
		
	public Mesa(Presidente presidente, int numero, String tipoDeMesa, int maximoVotantesPorFranjaHoraria) {
		// TODO Auto-generated constructor stub
		this.presidente = presidente;
		this.numero = numero;
		this.franjasHorarias = new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
		// this.votantesRegistrados = 0;
		this.maximoVotantesPorFranjaHoraria = maximoVotantesPorFranjaHoraria;
		this.tipoDeMesa = tipoDeMesa;
		
		DNIsAsignadosPorFranjaHoraria = new HashMap<Integer, List<Integer>>();
		for(Integer i : getFranjasHorarias()) {
			DNIsAsignadosPorFranjaHoraria.put(i, new ArrayList<Integer>());
		}
		
	}

	@Override
	public String toString() {
		return "Mesa para " + tipoDeMesa + " n° " + getNumero();
	}
	
<<<<<<< HEAD
		// votantes registrados % franjasHorarias
	}
	*/
	
	
	// Metodos de la clase (Abstract) ----------
	
	
	//Generador de numero de mesa
	/*
	public int numDeMesaGenerador() 
	{
			int prime = 31;
			int dniPresidente = presidenteDeMesa.getDni(); 
			int resultado = prime * dniPresidente;
			return resultado;
			
	}
	*/

// Getters and Setters ---------------------
	
	public Presidente getPresidente() 
	{
		return presidente;
	}

	public int[] getFranjasHorarias() {
		return franjasHorarias;
	}

	public int getNumero() {
		return numero;
	}

=======
// Metodos Abstrac -----------
>>>>>>> cb941799f8ad5e1cddcc430205e0a3ebbb6afbdc
	protected abstract boolean aceptaVotante(Votante votante);
	
// Metodos de Mesa  -----------
	public int obtenerHorarioDisponible() {

<<<<<<< HEAD
		boolean horarioDisponible = false;
		int horario = 0;
		
		Iterator<Integer> itHorarios= DNIsAsignadosPorFranjaHoraria.keySet().iterator();
		
		while( itHorarios.hasNext() && !horarioDisponible ) {
			
			horario = itHorarios.next();
			
			horarioDisponible = 
					DNIsAsignadosPorFranjaHoraria.get(horario).size() < maximoVotantesPorFranjaHoraria;
		}
		
		if(!horarioDisponible)
			horario = 0;
		
		return horario;
		
	}

	public void registrarVotante(int horario, int dni) {
		// Se asume que los horarios son correctos y los dnis unicos
		DNIsAsignadosPorFranjaHoraria.get(horario).add(dni);
	}

	/*
	public Map<Integer, List<Votante>> obtenerVotantesPorFranjaHoraria() {

		return votantesPorFranjaHoraria;
	}
	*/

	public boolean tieneAsignados() {
		// TODO Auto-generated method stub
		boolean tieneAsignados = false;
		for(List<Integer>dnisAsignados : DNIsAsignadosPorFranjaHoraria.values())
			tieneAsignados = tieneAsignados || (!dnisAsignados.isEmpty());
			
		return tieneAsignados;
	}

	public Map<Integer, List<Integer>> getDNIsAsignadosPorFranjaHoraria() {
		return DNIsAsignadosPorFranjaHoraria;
	}
	
=======
		// Como la division de enteros trunca el resultado, sirve para obtener la posicion 
		// de franjas horarias libre.
		int arrayPosition = votantesRegistrados / maximoVotantesPorFranjaHoraria;
		if(franjasHorarias.length == arrayPosition) {
			return -1;
		} else 
			return (
				franjasHorarias[
	                votantesRegistrados / maximoVotantesPorFranjaHoraria
	            ]
			);
}

	public void registrarVotante() {
		votantesRegistrados++;
}
>>>>>>> cb941799f8ad5e1cddcc430205e0a3ebbb6afbdc
	
// Getters and Setters ---------------------
	
	public Presidente getPresidente() {
			return presidente;
}

	public int[] getFranjasHorarias() {
			return franjasHorarias;
}


	public int getNumero(){
			return numero;
}
	
}
