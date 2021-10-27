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
	public abstract int darFranjaHorariaDisponible();
	
	
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
		return numero;
	}
	
	
}
