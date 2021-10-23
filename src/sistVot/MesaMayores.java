package sistVot;

public class MesaMayores extends Mesa {
	
	private int[] franjasHorarias;
	
	public MesaMayores(Presidente presidenteDeMesa, int[] franjasHorarias) {
		super(presidenteDeMesa);
		this.franjasHorarias = franjasHorarias;
	}
	
	public int[] getFranjasHorarias() {
		return franjasHorarias;
	}

	@Override
	public boolean tieneCupo() {
		return true;
	}
	
	public int getNumDeMesa() {
		return numDeMesa;
	}
}
