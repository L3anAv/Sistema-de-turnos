package sistVot;

public class MesaMayores extends Mesa {
	
	private int[] franjasHorarias;
	
	public MesaMayores(Presidente presidente, int numero, int[] franjasHorarias) {
		super(presidente, numero);
		this.franjasHorarias = franjasHorarias;
	}
	
	public int[] getFranjasHorarias() {
		return franjasHorarias;
	}

	@Override
	public boolean tieneCupo() {
		return true;
	}
	
	public int getNumero() {
		return numero;
	}
}
