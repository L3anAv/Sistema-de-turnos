package sistVot;

public abstract class Mesa {
	
	protected int numero;
	private Presidente presidente;
	private int[] franjasHorarias;

	// Constructor  ----------------------------
	public Mesa(Presidente presidente, int numero)
	{
		this.presidente = presidente;
		this.numero = numero;
	}
	
	// Metodos de la clase (Abstract) ----------
	public abstract boolean tieneCupo();
	
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

	public int[] getFranjasHorarias() 
{
		return franjasHorarias;
}

	public int getNumero() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
