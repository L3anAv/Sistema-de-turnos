package sistVot;

public class MesaEnfPreex extends Mesa {

	private int[] franjasHorarias;

	public MesaEnfPreex(Presidente presidente, int numero, int[] franjasHorarias) {
		super(presidente, numero);
		this.franjasHorarias = franjasHorarias;
	}

	@Override
	public boolean tieneCupo() {
		// TODO Auto-generated method stub
		return false;
	}

}
