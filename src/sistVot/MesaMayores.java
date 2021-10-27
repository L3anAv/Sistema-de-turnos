package sistVot;

public class MesaMayores extends Mesa {
	
	private int[] franjasHorarias;
	private int cupoPorFranja = 10;
	private int cuposTotales = franjasHorarias.length * cupoPorFranja;
	
	public MesaMayores(Presidente presidente, int numero, int[] franjasHorarias) {
		super(presidente, numero);
		this.franjasHorarias = franjasHorarias;
	}
	
	public int[] getFranjasHorarias() {
		return franjasHorarias;
	}

	@Override
	public boolean tieneCupo() {
		
		if(cuposTotales > 0)
			return true;
		else
			return false;
		
	}
	
	public int getNumero() {
		return numero;
	}

	@Override
	public int darFranjaHorariaDisponible() {
		return 0;
	}
}
