package sistVot;

public abstract class Mesa {
	
	protected int numDeMesa;
	private Presidente presidenteDeMesa;
	private int[] franjasHorarias;

// Constructor  ----------------------------
	
	public Mesa(Presidente presidenteDeMesa)
{
		this.presidenteDeMesa = presidenteDeMesa;
		numDeMesa = numDeMesaGenerador();
}
	
// Metodos de la clase (Abstract) ----------
	
	public abstract boolean tieneCupo();
	
	//Generador de numero de mesa
	public int numDeMesaGenerador() 
{
		int prime = 31;
		int dniPresidente = Integer.parseInt(presidenteDeMesa.getDni()); 
		int resultado = prime * dniPresidente;
		return resultado;
		
}

// Getters and Setters ---------------------
	
	public int getNumDeMesa() 
{
		return numDeMesa;
}

	public Presidente getPresidenteDeMesa() 
{
		return presidenteDeMesa;
}

	public int[] getFranjasHorarias() 
{
		return franjasHorarias;
}
	
	
}
