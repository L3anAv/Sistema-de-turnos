package sistVot;

public class MesaGeneral extends Mesa {

	private int[] franjasHorarias;

	public MesaGeneral(Presidente presidente, int numero, int[] franjasHorarias) {
		super(presidente, numero);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean tieneCupo() {
		// TODO Auto-generated method stub
		return false;
	}

}
